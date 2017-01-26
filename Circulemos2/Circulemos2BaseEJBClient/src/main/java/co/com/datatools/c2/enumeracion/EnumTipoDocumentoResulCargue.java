package co.com.datatools.c2.enumeracion;

import co.com.datatools.c2.util.SearchableEnumeration;

public enum EnumTipoDocumentoResulCargue implements SearchableEnumeration<Integer> {

	UBICABILIDAD_DIRECCION(1), //
	UBICABILIDAD_TELEFONO(2), //
	UBICABILIDAD_CORREO(3), //
	SIN_IDENTIFICAR(4), //
	UBICABILIDAD_INCONSISTENCIAS(5), //
    ;

    Integer tipoDocumetoResulCargue;

    EnumTipoDocumentoResulCargue(Integer tipoTelefono) {
        this.tipoDocumetoResulCargue = tipoTelefono;
    }

    @Override
    public Integer getValue() {
        // TODO Auto-generated method stub
        return tipoDocumetoResulCargue;
    }//

}
