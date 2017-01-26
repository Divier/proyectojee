/**
 * 
 */
package co.com.datatools.documentos.cm.interfaces;

import javax.ejb.Local;

import co.com.datatools.documentos.cm.dto.Documento;

/**
 * @author sergio.torres
 * 
 */
@Local
public interface ILGestorArchivos {

	/**
	 * Permite crear un documento nuevo o una nueva versi�n del documento en el
	 * repositorio
	 * 
	 * @param documento
	 *            con los valores de nombre, data, descripcion, fecha, folder
	 * @return Documento con el id asignado por el sistema de gesti�n de
	 *         archivos y dem�s propiedades asignadas luego de la creaci�n
	 */
	Documento guardarDocumento(Documento documento);

	/**
	 * Permite consultar y recuperar la �ltima versi�n de un documento por el id
	 * asignado en el sistema
	 * 
	 * @param <strong>idDocumento</strong> id asignado obligatorio.
	 * @param <strong>version</strong> null para la versi�n m�s reciente
	 * @return <strong>Documento</strong>
	 *         <p>
	 *         con el contenido binario y alfanum�rico del documento
	 *         </p>
	 */
	Documento obtenerDocumento(String idDocumento, String version);

	/**
	 * Actualiza los datos de un documento ya existente genera una nueva versi�n
	 * del documento
	 * 
	 * @param documento
	 * @return
	 */
	Documento actualizarDocumento(Documento documento);

}
