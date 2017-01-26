/*
 * Scripts que permite cambiar el atributo height y width de un componente
 * 
 * @author Ivan Arteaga
 * */
function cambiarTamanioImagen(etiqueta, opcion, minimo, incremento) {
	try {
		var img = document.getElementById(etiqueta);
		if (opcion == 1) {// incremento
			img.height = img.height + incremento;
			img.width = img.width + incremento;
		} else if (opcion == 0) {//valores normales
			img.height = '350';
			img.width = '600';
		} else if (opcion == 2) { // decremento
			// Si alguno de los lados es inferior al minimo sale
			if (img.height <= minimo && img.width <= minimo) {
				return;
			} else {
				img.height = img.height - incremento;
				img.width = img.width - incremento;
			}
		}
	} catch (err) {
		alert("Error al obtener el elemento...");
	}
}

function aumentarTamanio(etiqueta) {
	cambiarTamanioImagen(etiqueta, 1, 600, 100);
}

function disminuirTamanio(etiqueta) {
	cambiarTamanioImagen(etiqueta, 2, 600, 100);
}

function resturarTamanio(etiqueta) {
	cambiarTamanioImagen(etiqueta, 0, 0, 0);
}