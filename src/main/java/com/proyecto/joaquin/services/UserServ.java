package com.proyecto.joaquin.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.proyecto.joaquin.models.Product;
import com.proyecto.joaquin.models.User;
import com.proyecto.joaquin.repositories.UserRepo;

@Service
public class UserServ {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ProductServ productServ;
	
	//USERS
	//Metodo que registra a un nuevo usuario
	public User registrer(	User newUser, BindingResult result) {
		
		//Comparar las contraseñas
		String password = newUser.getPassword();
		String confirm = newUser.getComfirm();
		if(!password.equals(confirm)) {
			//Si no son iguales
			//rejectValue(input(path), clave, mensaje de error)
			result.rejectValue("comfirm", "Matches", "contraseña/confirmacion son diferentes");
		}
		
		//Revisar que el emil no este registrado
		String email = newUser.getEmail();
		User userExist = userRepo.findByEmail(email);
		
		if(userExist != null) {
			//El correo ya esta registrado
			result.rejectValue("email", "Unique", "E-mail already exists");
		}
		
		//Si existe error, regreso null
		if(result.hasErrors()) {
			return null;
		} else {
			String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
			newUser.setPassword(passHash);//Establecemos el password hassheado
			
			return userRepo.save(newUser);
		}
	}
	
	/*Metdo que revisa que los datos sean correcto para Iniciar Sesion*/
	public User login(String email, String password) {
		//Revisamos que el correro exista en la BD
		User userTryngLogin = userRepo.findByEmail(email);//Objeto User o NULL
		
		if(userTryngLogin == null) {
			return null;
		}
		
		//Comparar las contraseñas;
		//BCrypt.checkpw(contraseña no encriptada, contraseña SI encriptada) -> True or False
		if(BCrypt.checkpw(password, userTryngLogin.getPassword())) {
			return userTryngLogin;
		} else {
			return null;
		}
	}
	
	//Metodo que en base al id regresa un objeto de User
	public User getUser(Long id) {
		return userRepo.findById(id).orElse(null);
	}
}
