package co.com.datatools.seguridad.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import co.com.datatools.util.dto.EntidadC2;

/**
 * Definicion de Menu de una aplicacion asociados a recursos
 * 
 * @author Felipe Martinez <br>
 *         rodrigo.cruz (mod: 21/07/2015) - Auditoria
 */
@Entity
@Table(name = "recurso_menu")
// @Audited
@NamedQueries({
        @NamedQuery(name = "RecursoMenu.findAll", query = "SELECT r FROM RecursoMenu r"),
        @NamedQuery(
                name = "RecursoMenu.findByRecurso",
                query = "SELECT r FROM RecursoMenu r WHERE r.recurso.idRecurso= :idRecurso") })
public class RecursoMenu implements EntidadC2 {
    private static final long serialVersionUID = 1L;

    public static final String SQ_RECURSOMENU_BY_RECURSO = "RecursoMenu.findByRecurso";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso_menu")
    private Integer idRecursoMenu;

    @Column(name = "orden")
    private short orden;

    @Column(name = "recurso_data")
    private String recursoData;

    @JoinColumn(name = "id_recurso", referencedColumnName = "id_recurso")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Recurso recurso;

    // @AuditMappedBy(mappedBy = "menuPadre")
    @OneToMany(mappedBy = "menuPadre", fetch = FetchType.LAZY)
    @OrderBy("orden")
    private List<RecursoMenu> submenuList;

    @JoinColumn(name = "id_menu_padre", referencedColumnName = "id_recurso_menu")
    @ManyToOne(fetch = FetchType.LAZY)
    private RecursoMenu menuPadre;

    public RecursoMenu() {
    }

    public Integer getIdRecursoMenu() {
        return idRecursoMenu;
    }

    public void setIdRecursoMenu(Integer idRecursoMenu) {
        this.idRecursoMenu = idRecursoMenu;
    }

    public short getOrden() {
        return orden;
    }

    public void setOrden(short orden) {
        this.orden = orden;
    }

    public String getRecursoData() {
        return recursoData;
    }

    public void setRecursoData(String recursoData) {
        this.recursoData = recursoData;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public List<RecursoMenu> getSubmenuList() {
        return submenuList;
    }

    public void setSubmenuList(List<RecursoMenu> submenuList) {
        this.submenuList = submenuList;
    }

    public RecursoMenu getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(RecursoMenu menuPadre) {
        this.menuPadre = menuPadre;
    }
}
