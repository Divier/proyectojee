package co.com.datatools.c2.entidades;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the evidencia_obligacion_sac database table.
 * 
 */
@Entity
@Table(name="evidencia_obligacion_sac")
@NamedQuery(name="EvidenciaObligacionSac.findAll", query="SELECT e FROM EvidenciaObligacionSac e")
public class EvidenciaObligacionSac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_evidencia_obligacion_sac")
	private Long id;

	private String nombre;

	private String url;
	
    @Column(name = "numero_citacion")
    private String numeroCitacion;
    
    @Column(name = "numero_factura")
    private String numeroFactura;

	//bi-directional many-to-one association to ObligacionSac
	@ManyToOne
	@JoinColumn(name="id_obligacion_sac")
	private ObligacionSac obligacionSac;

	public EvidenciaObligacionSac() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ObligacionSac getObligacionSac() {
		return this.obligacionSac;
	}

	public void setObligacionSac(ObligacionSac obligacionSac) {
		this.obligacionSac = obligacionSac;
	}

	public String getNumeroCitacion() {
		return numeroCitacion;
	}

	public void setNumeroCitacion(String numeroCitacion) {
		this.numeroCitacion = numeroCitacion;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
}