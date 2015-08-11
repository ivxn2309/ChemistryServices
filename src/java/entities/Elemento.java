package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "elementos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elemento.findAll", query = "SELECT e FROM Elemento e"),
    @NamedQuery(name = "Elemento.findBySimbolo", query = "SELECT e FROM Elemento e WHERE e.simbolo = :simbolo"),
    @NamedQuery(name = "Elemento.findByNumero", query = "SELECT e FROM Elemento e WHERE e.numero = :numero"),
    @NamedQuery(name = "Elemento.findByNombre", query = "SELECT e FROM Elemento e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Elemento.findByPeso", query = "SELECT e FROM Elemento e WHERE e.peso = :peso"),
    @NamedQuery(name = "Elemento.findByCaracter", query = "SELECT e FROM Elemento e WHERE e.caracter = :caracter")})
public class Elemento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "simbolo")
    private String simbolo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private int numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "peso")
    private double peso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "caracter")
    private String caracter;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "elemento")
    private Collection<Valencia> valenciasCollection;

    public Elemento() {
    }

    public Elemento(String simbolo) {
        this.simbolo = simbolo;
    }

    public Elemento(String simbolo, int numero, String nombre, double peso, String caracter) {
        this.simbolo = simbolo;
        this.numero = numero;
        this.nombre = nombre;
        this.peso = peso;
        this.caracter = caracter;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }

    @XmlTransient
    public Collection<Valencia> getValenciasCollection() {
        return valenciasCollection;
    }

    public void setValenciasCollection(Collection<Valencia> valenciasCollection) {
        this.valenciasCollection = valenciasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (simbolo != null ? simbolo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Elemento)) {
            return false;
        }
        Elemento other = (Elemento) object;
        return !((this.simbolo == null && other.simbolo != null) || (this.simbolo != null && !this.simbolo.equals(other.simbolo)));
    }

    @Override
    public String toString() {
        return simbolo + " : " + nombre;
    }
    
}
