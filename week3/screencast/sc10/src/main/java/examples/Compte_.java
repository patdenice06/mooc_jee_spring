package examples;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Compte.class)
public class Compte_ {
	public static volatile SingularAttribute<Compte, String> nom; 
	public static volatile SingularAttribute<Compte, Double> solde; 
}
