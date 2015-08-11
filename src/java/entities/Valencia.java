package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "valencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valencia.findAll", query = "SELECT v FROM Valencia v"),
    @NamedQuery(name = "Valencia.findByIdValencia", query = "SELECT v FROM Valencia v WHERE v.idValencia = :idValencia"),
    @NamedQuery(name = "Valencia.findByValor", query = "SELECT v FROM Valencia v WHERE v.valor = :valor")})
public class Valencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_valencia")
    private Long idValencia;
    @Column(name = "valor")
    private Integer valor;
    @JoinColumn(name = "elemento", referencedColumnName = "simbolo")
    @ManyToOne(optional = false)
    private Elemento elemento;

    public Valencia() {
    }

    public Valencia(Long idValencia) {
        this.idValencia = idValencia;
    }

    public Long getIdValencia() {
        return idValencia;
    }

    public void setIdValencia(Long idValencia) {
        this.idValencia = idValencia;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idValencia != null ? idValencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Valencia)) {
            return false;
        }
        Valencia other = (Valencia) object;
        return !((this.idValencia == null && other.idValencia != null) || (this.idValencia != null && !this.idValencia.equals(other.idValencia)));
    }

    @Override
    public String toString() {
        return elemento.toString() + " => " + valor;
    }
    
}
