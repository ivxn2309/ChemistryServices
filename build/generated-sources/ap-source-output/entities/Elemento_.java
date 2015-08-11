package entities;

import entities.Valencia;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-07-20T21:33:29")
@StaticMetamodel(Elemento.class)
public class Elemento_ { 

    public static volatile SingularAttribute<Elemento, String> simbolo;
    public static volatile CollectionAttribute<Elemento, Valencia> valenciasCollection;
    public static volatile SingularAttribute<Elemento, Integer> numero;
    public static volatile SingularAttribute<Elemento, Double> peso;
    public static volatile SingularAttribute<Elemento, String> caracter;
    public static volatile SingularAttribute<Elemento, String> nombre;

}