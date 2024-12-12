<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<title>dashboard</title>
</head>
<body>
	<header>
		<nav>
			<img src="/img/logo.png" class="logo" alt="logo">
			<form action="#" method="post">
				<input type="text" name="buscador" id="buscador"> 
				<input type="submit" id="buscar" value="">
			</form>

				<a href="/carrito" class="aCarrito">
					<img src="/img/carrito.png" alt="carrito" class="carrito">
					<span>${ cantProductCarr }</span>
				</a>

				<c:if test="${userTemp.type == 'Vendedor'}">
					<a href="/crearProducto"><img alt="sumProduct" src="/img/mas.png" class="crearProducto"></a>
				</c:if>
		</nav>
	</header>

	<div class="contenedor">
		<!--
		<div>
			<p>Filtros</p>
			<form action="/filtros" method="post">
				<div>
					<label for=""></label>
					<input type="text" name="" id="">
				</div>
				<div>
					<label for=""></label>
					<input type="text" name="" id="">
				</div>
				<div>
					<label for=""></label>
					<input type="text" name="" id="">
				</div>
				<div>
					<label for=""></label>
					<input type="text" name="" id="">
				</div>
			</form>
		</div>
	-->

		<div class="pagina">
			
			<div class="muestraProductos">
				<c:forEach items="${ productos }" var="producto">
					
					<div class="producto">
						
						<c:if test="${ userTemp.id == producto.seller.id }">
							<form action="/borrarProducto/${ producto.id }" method="post">
									<input type="hidden" name="_method" value="DELETE">
									<label for="submit"><img class="borrar" alt="borrar.png" src="/img/borrar.png"></label>
									<input type="submit" id="submit" class="oculto">
								</form>
						</c:if>
					
  			      		<img class="imagenProducto" src="/img/productImage/${producto.image}" alt="producto ${producto.productName}">
 			       		<h2 class="nombreProducto">${producto.productName}</h2>
			       	 	<p class="cantidad">stock <span>${producto.stock}</span></p>
			        	<p class="precio">precio <span>$${producto.price}</span></p>
			        	
			        	<c:if test="${ producto.stock <= 0 }">
			        		<p class="nullStock">Sin Stock</p>
			        	</c:if>
			        	
			        	<c:if test="${ producto.stock > 0 }">
			        		<button class="agregarCarrito" onclick="agregarCarrito(${producto.id})">Agregar al carrito</button>
			        	</c:if>
			    	</div>
				</c:forEach>
			</div>
		</div>

	</div>
	
	<script type="text/javascript" src="/js/llamarControlador.js"></script>
	
</body>
</html>