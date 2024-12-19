package com.proyecto.joaquin.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.joaquin.models.Category;
import com.proyecto.joaquin.models.Product;
import com.proyecto.joaquin.models.Purchased_product;
import com.proyecto.joaquin.models.User;
import com.proyecto.joaquin.services.ProductServ;
import com.proyecto.joaquin.services.PurchasedProductServ;
import com.proyecto.joaquin.services.UserServ;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("https://carritocomprasjava.onrender.com")
@CrossOrigin(origins = "https://carritocomprasjava.onrender.com")
public class ProjectControllers {
	@Autowired
	private UserServ userServ;
	
	@Autowired
	private ProductServ productServ;
	
	@Autowired
	private PurchasedProductServ purchasedServ;
	
	@GetMapping("/") /*PAGINA DE REGISTRO*/
	public String pagRegister(@ModelAttribute("newUser") User newUser) {
		return "registro.jsp";
	}
	
	@PostMapping("/register") /*COMPROBAR Y GUARDAR LA INFO DE REGISTRO*/
	public String saveUser(	@Valid @ModelAttribute("newUser") User newUser,
							BindingResult result, HttpSession session) {
		
		User userTryLogin = userServ.registrer(newUser, result);
		
		if (result.hasErrors()) {
			return "registro.jsp";
		} else {
			session.setAttribute("userInSession", userTryLogin);
			return"redirect:/dashboard";
		}
	}
	
	@GetMapping("/inicioSesion") /*PAGINA DE INICIO DE SESION*/
	public String login(){
		return "inicioSesion.jsp";
	}
	
	@PostMapping("/login") /*VERIFICA EL INICIO DE SESION*/
	public String loginUser(@RequestParam("email") String email,
							@RequestParam("password") String password,
							RedirectAttributes redirectAttributes,/*permite usar mensajes de flash*/
							HttpSession session) {
		
		User userTryLogin = userServ.login(email, password); //Obj User or Null
		
		if(userTryLogin == null) {
			redirectAttributes.addFlashAttribute("errorLogin", "Wrong E-mail/Password");
			return "redirect:/inicioSesion";
		} else {
			session.setAttribute("userInSession", userTryLogin); 
			return "redirect:/dashboard";
		}
	}
	
	@GetMapping("/dashboard") /*PAGINA PRINCIPAL*/
	public String Dashboard(HttpSession session, Model model){
		
		User userTemp = (User) session.getAttribute("userInSession");
		
		if(userTemp == null) {
			return "redirect:/";
		}
		
		List<Purchased_product> pursProd = purchasedServ.findByUser(userTemp);
		
		model.addAttribute("cantProductCarr", pursProd.size());
		
		model.addAttribute("userTemp", userTemp);
		model.addAttribute("productos", productServ.getAllProduct());
		return "dashboard.jsp";
	}
	
	@GetMapping("/carrito") /*PAGINA DE LOS PRODUCTOS*/
	public String prueba(HttpSession session, Model model){
		
		User userTemp = (User) session.getAttribute("userInSession");
		
		if(userTemp == null) {
			return "redirect:/";
		}
		
		List<Product> carritoProductos = new ArrayList<Product>();
		List<Purchased_product> pursProd = purchasedServ.findByUser(userTemp);
		int totalCarrito = 0;
		
		for(int i = 0; i < pursProd.size(); i++) {
			Product product = pursProd.get(i).getProductPurchased();
			product.setCant(pursProd.get(i).getCant());
			
			totalCarrito += (product.getCant() * product.getPrice());
			
			carritoProductos.add(product);
		}
		model.addAttribute("productosCarritos", carritoProductos);
		model.addAttribute("TotalCarrito", totalCarrito);
		model.addAttribute("userTemp", userTemp);
		return "carrito.jsp";
	}
	
	
	
	@GetMapping("/crearProducto") /*FORMULARIO PARA CARGAR UN PRODUCTO*/
	public String crearProducto(@ModelAttribute("newProduct") Product newProduct, 
								Model model, HttpSession session) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		
		if(userTemp == null) {
			return "redirect:/";
		}
		
		if(!"Vendedor".equals(userTemp.getType())) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("categorias", Category.Categories);
		model.addAttribute("userTemp", userTemp);
		
		return "formProducto.jsp";
	}
	
	@PostMapping("/guardarProducto") /*VALIDACION DE LA INFORMACION DEL FORM DE PRODUCTO*/
	public String guardarProducto(	@Valid @ModelAttribute("newProduct") Product newProduct,
									BindingResult result, @RequestPart("file") MultipartFile imagen,
									Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("categorias", Category.Categories);
			return "formProducto.jsp";
		} else {
			if(!imagen.isEmpty()) {
				Path directorioImagenes = Paths.get("src//main//resources//static/img/productImage");
				String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();
				
				try {
					byte[] bitesImg = imagen.getBytes();
					Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());
					Files.write(rutaCompleta, bitesImg);
					
					newProduct.setImage(imagen.getOriginalFilename());
				} catch (IOException e) { e.printStackTrace(); }
			}
			
			productServ.saveProduct(newProduct);
			return "redirect:/dashboard";
		}
	}
	
	@DeleteMapping("/borrarProducto/{id}")
	public String borrarProducto(@PathVariable("id") Long id) {
		productServ.deleteProduct(id);
		
		return "redirect:/dashboard";
	}
	
	@PostMapping("/agregarCarrito/{id}") /*funcion para guardar el producto al carrito*/
	public void agregarCarrito(@PathVariable("id") Long id, HttpSession session) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		
		if(userTemp == null) {
			return ;
		}
		
		Product product = productServ.getProduct(id);
		
		purchasedServ.agregarProductoCarrito(userTemp, product);
		
		return ;
	}	
	
	@PostMapping("/sumCarrito/{id}") /*AGREGA UNO MAS DEL PRODUCTO SELECCIONADO*/
	public void sumCarrito(@PathVariable("id") Long id, HttpSession session) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		
		if(userTemp == null) {
			return ;
		}
		
		Product product = productServ.getProduct(id);
		
		purchasedServ.agregarProductoCarrito(userTemp, product);
	}
	
	@PostMapping("/restCarrito/{id}") /*QUITA UNO DEL PRODUCTO SELECCIONADO*/
	public void restCarrito(@PathVariable("id") Long id, HttpSession session) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		
		if(userTemp == null) {
			return ;
		}
		
		Product product = productServ.getProduct(id);
		
		purchasedServ.restarProductCarrito(userTemp, product);
	}
	
	@PostMapping("/borrarCarrito/{id}") /*ELIMINA EL PRODUCTO SELECCIONADO DEL CARRITO*/
	public void borrarProductoCarrito(@PathVariable("id") Long id, HttpSession session) {
		
		User userTemp = (User) session.getAttribute("userInSession");
		
		if(userTemp == null) {
			return ;
		}
		
		Product product = productServ.getProduct(id);
		
		purchasedServ.quitarProductCarrito(userTemp, product);
	}
}
