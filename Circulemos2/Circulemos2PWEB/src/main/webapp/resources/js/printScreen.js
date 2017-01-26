/**
 * Encargado de realizar el print Screen de una consulta
 */

function printScreen(i) {
	html2canvas(

			document.getElementById("form-contenido:campos-consulta"),
			{
				onrendered : function(canvas) {
					document.body.appendChild(canvas);
					var ruta = "C:\\captura\\" + i + ".jpg";

					var imageDataURL = ruta + canvas.toDataURL('image/jpeg');

					var xhr = new XMLHttpRequest();
					xhr.open("post", "/Circulemos2PWEB/PrintScreenServlet",
							false);
					var boundary = Math.random().toString().substr(2);
					xhr.setRequestHeader("content-type",
							"multipart/form-data; charset=utf-8; boundary="
									+ boundary);
					var multipart = "--"
							+ boundary
							+ "\r\n"
							+ "Content-Disposition: form-data; name=printScreen\r\n"
							+ "Content-type: image/jpeg\r\n\r\n" + imageDataURL
							+ "\r\n" + "--" + boundary + "--\r\n";
					xhr.send(multipart);
					$("canvas").remove();
				}
			});
}

function superfuncion() {
	if (document.getElementById('form-contenido:imprimirInforme').value === 'true') {
		var tabIs = PF('tabView').getLength() + 1;
		var tabI = 0;
		capturaImagen(tabI, tabIs);
	}
}

function capturaImagen(tab, tabs) {
	var millisecondsToWait = 100;
	if (tabs == tab) {
		PF('popUpConfirIPAT').show();
		return;
	}

	if (tab == 0) {
		cambiarTab(tab);
	} else {
		cambiarTab(tab - 1);
	}

	setTimeout(function() {
		if (tab >= 1) {
			printScreen(tab - 1);
		}

		if (tabs > 0) {
			capturaImagen(++tab, tabs);
		}
	}, millisecondsToWait);
}
function cambiarTab(tab) {
	var millisecondsToWait = 100;
	setTimeout(function() {
		PF('tabView').select(tab);
	}, millisecondsToWait)
}
