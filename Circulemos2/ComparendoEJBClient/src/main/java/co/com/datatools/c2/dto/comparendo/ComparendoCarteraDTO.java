package co.com.datatools.c2.dto.comparendo;
import java.util.List;
import java.util.Date;
import java.math.*;
import co.com.datatools.c2.dto.*;
import co.com.datatools.c2.util.EntidadDtoC2;
/**
 * @author Generated
 * @version 3.0 - Wed Dec 16 11:02:08 COT 2015
 */
public class ComparendoCarteraDTO implements EntidadDtoC2 { 
  private static final long serialVersionUID = 1L;
// --- Atributos
private Long idComparendoCartera;
private long idCartera;
private ComparendoDTO comparendo;

// --- Constructor
public ComparendoCarteraDTO () {
}

public ComparendoCarteraDTO (Long idComparendoCartera ) {
  this.idComparendoCartera=idComparendoCartera;

}


// --- Getters-Setters
public Long getIdComparendoCartera () {
  return this.idComparendoCartera;
}

public void setIdComparendoCartera (Long idComparendoCartera) {
  this.idComparendoCartera=idComparendoCartera;
}

public long getIdCartera () {
  return this.idCartera;
}

public void setIdCartera (long idCartera) {
  this.idCartera=idCartera;
}

public ComparendoDTO getComparendo () {
  return this.comparendo;
}

public void setComparendo (ComparendoDTO comparendo) {
  this.comparendo=comparendo;
}


}
