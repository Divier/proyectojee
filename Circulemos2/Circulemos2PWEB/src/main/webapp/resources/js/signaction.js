var imgWidth;
var imgHeight;
var refCampoResultado;
function StartSign(campoResultado) {
	refCampoResultado = document.getElementsByName(campoResultado)[0];
	var canvasObj = document.getElementById('cnv');
	canvasObj.getContext('2d').clearRect(0, 0, canvasObj.width,
			canvasObj.height);
	refCampoResultado.value = "";
	imgWidth = canvasObj.width;
	imgHeight = canvasObj.height;
	var message = {
		"firstName" : "",
		"lastName" : "",
		"eMail" : "",
		"location" : "",
		"imageFormat" : 1,
		"imageX" : imgWidth,
		"imageY" : imgHeight,
		"imageTransparency" : false,
		"imageScaling" : false,
		"maxUpScalePercent" : 0.0,
		"rawDataFormat" : "ENC",
		"minSigPoints" : 25,
		"penThickness" : 3,
		"penColor" : "#000000"
	};

	document
			.addEventListener('SigCaptureWeb_SignResponse', SignResponse, false);
	var messageData = JSON.stringify(message);
	var element = document.createElement("SigCaptureWeb_ExtnDataElem");
	element.setAttribute("SigCaptureWeb_MsgAttribute", messageData);
	document.documentElement.appendChild(element);
	var evt = document.createEvent("Events");
	evt.initEvent("SigCaptureWeb_SignStartEvent", true, false);
	element.dispatchEvent(evt);
}
function SignResponse(event) {
	var str = event.target.getAttribute("SigCaptureWeb_msgAttri");
	var obj = JSON.parse(str);
	SetValues(obj, imgWidth, imgHeight);
}
function SetValues(objResponse, imageWidth, imageHeight) {
	var obj = JSON.parse(JSON.stringify(objResponse));
	var ctx = document.getElementById('cnv').getContext('2d');

	if (obj.errorMsg != null && obj.errorMsg != ""
			&& obj.errorMsg != "undefined") {
		alert(obj.errorMsg);
	} else {
		if (obj.isSigned) {
			refCampoResultado.value += obj.imageData;
			var img = new Image();
			img.onload = function() {
				ctx.drawImage(img, 0, 0, imageWidth, imageHeight);
			}
			img.src = "data:image/png;base64," + obj.imageData;
		}
	}
}
