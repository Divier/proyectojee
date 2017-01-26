/**
 * 
 */
package co.com.datatools.documentos.cm.interfaces;

import javax.ejb.Remote;

import co.com.datatools.documentos.cm.dto.Documento;

/**
 * @author sergio.torres
 *
 */
@Remote
public interface IRGestorArchivos {

	/**
	 * @see ILGestorArchivos.guardarDocumento
	 */
	Documento guardarDocumento(Documento documento);

	/**
	 * @see ILGestorArchivos.obtenerDocumento
	 */
	Documento obtenerDocumento(String idDocumento, String version);
	
	/*
	 * @see (ILGestorArchivos.obtenerDocumento)
	 * 
	 */
	Documento actualizarDocumento(Documento documento);


}
