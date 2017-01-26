package co.com.datatools.c2.mail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.com.datatools.c2.mail.dto.ParametrosCorreoDTO;
import co.com.datatools.c2.mail.util.EnumErrorParametrosCorreo;
import co.com.datatools.c2.mail.util.EnvioCorreoHelper;

public class ParametrosCorreoTest {

    List<EnumErrorParametrosCorreo> lErroresParametros;

    @Before
    public void setUp() throws Exception {
        lErroresParametros = new ArrayList<EnumErrorParametrosCorreo>();
    }

    @Test
    public void ValidarParametrosObligatoriosTest() {
        ParametrosCorreoDTO msg = new ParametrosCorreoDTO();
        msg.setEmailAccount("pepito74747@gmail.com");
        msg.setEmailPassword("75757fhfhsksk");
        msg.setSmtpHostName("smtp.gmail.com");
        msg.setSmtpPort("589");

        lErroresParametros = EnvioCorreoHelper.erroresParametrosCorreo(msg);
        Assert.assertEquals(0, lErroresParametros.size());
    }
}
