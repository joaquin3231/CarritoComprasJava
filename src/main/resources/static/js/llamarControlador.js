async function agregarCarrito(idProducto) {

	fetch(`/agregarCarrito/${idProducto}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({})
	})
		.then(response => response.json())
		.then(data => console.log(data))
		.catch(error => console.error(error));

	setTimeout(() => {
		location.reload();
	}, 200);
}

async function sumCarrito(idProducto) {

	fetch(`/sumCarrito/${idProducto}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({})
	})
		.then(response => response.json())
		.then(data => console.log(data))
		.catch(error => console.error(error));

	setTimeout(() => {
		location.reload();
	}, 300);

}

async function borrarCarrito(idProducto) {

	fetch(`/borrarCarrito/${idProducto}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({})
	})
		.then(response => response.json())
		.then(data => console.log(data))
		.catch(error => console.error(error));

	setTimeout(() => {
		location.reload();
	}, 300);

}

async function restCarrito(idProducto) {

	fetch(`/restCarrito/${idProducto}`, {
		method: "POST",
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify({})
	})
		.then(response => response.json())
		.then(data => console.log(data))
		.catch(error => console.error(error));

	setTimeout(() => {
		location.reload();
	}, 300);

}
