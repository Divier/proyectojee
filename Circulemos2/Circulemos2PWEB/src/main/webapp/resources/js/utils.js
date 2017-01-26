/**
 * Esta funcion permite centrar un componente <code>dialog</code> de
 * <i>PrimeFaces<i>. Ejemplo:
 * <code>oncomplete="jqCenterDialog('dlgAddGar');"</code>
 * 
 * @param widgetVar -
 *            Una cadena con el nombre de la variable <code>widgetVar</code>
 *            del objeto <code>dialog</code>
 * @author Rodrigo Cruz H
 */
function centerDialog(widgetVar) {
	PF(widgetVar).jq.position({
		of : $(window),
		my : "center",
		at : "center",
		collision : "none none"
	});
}

/**
 * Esta funcion permite validar si se debe mostrar una ventana emergente segun
 * el parametro enviado, el cual tiene el mismo nombre que la funcion.
 * 
 * @param args -
 *            Referencia que contiene el nombre de la variable
 *            <code>validarMostrarPopup</code>
 * @param widgetVar -
 *            Una cadena con el nombre de la variable <code>widgetVar</code>
 *            del objeto <code>dialog</code>
 * @author Rodrigo Cruz H
 */
function validarMostrarPopup(args, widgetVar) {
	if (!args.validarMostrarPopup) {
		PF(widgetVar).hide();
	} else {
		PF(widgetVar).show();
	}
}

/*
 * @author : givanny.rey
 */
function zoomComparendo() {
	jQuery("img.zoom").elevateZoom({
		scrollZoom : true,
		zoomType : "lens",
		containLensZoom : true,
		lensShape : "round",
		lensSize : 470
	});
}