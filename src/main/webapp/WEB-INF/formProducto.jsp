<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Cargar Producto</title>
<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
	
	<div class="contenedor">
		<div>
			<h2 class="tituloPag">Registro</h2>
				<!-- "enctype="multipart/form-data" se utiliza esto para que el formulario funcione si se guarda una imagen-->
				<form:form action="/guardarProducto" enctype="multipart/form-data" method="POST" modelAttribute="newProduct" class="form" >
					<div  class="divForm">
						<form:label path="productName" class="label">Nombre producto:</form:label>
						<form:input path="productName" class="input"  required="required"/>
						<form:errors path="productName" class="error" />
					</div>
					
					<div  class="divForm">
						<form:label path="price" class="label">Precio:</form:label>
						<form:input path="price" class="input" required="required"/>
						<form:errors path="price" class="error" />
					</div>
					
					<div  class="divForm">
						<form:label path="stock" class="label">Stock:</form:label>
						<form:input path="stock" class="input" required="required"/>
						<form:errors path="stock" class="error" />
					</div>
					
					<div class="divForm">
						<form:label path="category" class="label"> Tipo de Usuario :</form:label>
						<form:select path="category" class="select">
							<c:forEach items="${ categorias }" var="categoria">
								<form:option value="${ categoria }">${ categoria }</form:option>
							</c:forEach>
						</form:select>
					</div>
					
					<div  class="divForm">
						<label for="file" class="label">image:</label>
						<input id="file" name="file" class="input" type="file">
					</div>
					
					<img alt="imagenSeleccionada" id="imagenSeleccionada" src="blob:">

					<form:hidden path="seller" value="${ userTemp.getId() }"/>
					
					<input type="submit" class="submit" value="guardar">
				</form:form>
		</div>
	</div>


	<script type="text/javascript" src="/js/mostrarImagen.js"></script>

</body>

</html>