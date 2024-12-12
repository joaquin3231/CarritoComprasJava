<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>Carrito</title>
</head>
<body>
	<header>
		<nav>
			<img src="/img/logo.png" class="logo" alt="logo">
			<form action="#" method="post">
				<input type="text" name="buscador" id="buscador"> <input type="submit" id="buscar" value="">
			</form>
			<a href="/dashboard" class="aCarrito">
				<img src="/img/volver.png" alt="carrito" class="carrito">
			</a>
		</nav>
	</header>

	<div class="contenedor">
		<div class="carritoCompras">
			<div class="muestraProducts">
				<c:forEach items="${ productosCarritos }" var="carrProducto">
					<div class="cardProduct">
						<h2 class="nameProduct">${carrProducto.productName}</h2>
						<div class="infoProduct">
							<img src="/img/productImage/${carrProducto.image}" alt="producto ${carrProducto.productName}" class="imgProduct">
							<div class="textProduct">
								<div class="priceCant">
									<p class="priceProduct">$${carrProducto.price}</p>
									<p class="cantProduct">Cant ${carrProducto.cant}</p>
								</div>
								<p class="totalProduct">$${carrProducto.price *carrProducto.cant}</p>
								<div class="functions">
									<button onclick="sumCarrito(${ carrProducto.id })" class="funcar sum"> +1 </button>
									<button onclick="borrarCarrito(${ carrProducto.id })" class="funcar borrar"> X </button> 
									<button onclick="restCarrito(${ carrProducto.id })" class="funcar res"> -1 </button>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="Total">
				<h2>
					Total $<span>${ TotalCarrito }</span>
				</h2>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="/js/llamarControlador.js"></script>
</body>
</html>