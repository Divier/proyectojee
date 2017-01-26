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
	 * Permite crear un documento nuevo o una nueva versión del documento en el
	 * repositorio
	 * 
	 * @param documento
	 *            con los valores de nombre, data, descripcion, fecha, folder
	 * @return Documento con el id asignado por el sistema de gestión de
	 *         archivos y demás propiedades asignadas luego de la creación
	 */
	Documento guardarDocumento(Documento documento);

	/**
	 * Permite consultar y recuperar la última versión de un documento por el id
	 * asignado en el sistema
	 * 
	 * @param <strong>idDocumento</strong> id asignado obligatorio.
	 * @param <strong>version</strong> null para la versión más reciente
	 * @return <strong>Documento</strong>
	 *         <p>
	 *         con el contenido binario y alfanumérico del documento
	 *         </p>
	 */
	Documento obtenerDocumento(String idDocumento, String version);

	/**
	 * Actualiza los datos de un documento ya existente genera una nueva versión
	 * del documento
	 * 
	 * @param documento
	 * @return
	 */
	Documento actualizarDocumento(Documento documento);

}
