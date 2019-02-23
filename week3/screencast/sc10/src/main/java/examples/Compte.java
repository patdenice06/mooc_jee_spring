package examples;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Compte {

	@Id
	private String nom;	
	private Double solde;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Double getSolde() {
		return solde;
	}
	
	// Contrainte solde positif ou nul 
	public void setSolde(Double montant) {
		this.solde = montant;
		if(this.solde < 0)
			throw new RuntimeException();
	}
	
	public void decrementAccount(double amnt) {
		setSolde( getSolde() - amnt );
	}
	
	public void incrementAccount(double amnt) {
		setSolde( getSolde() + amnt );		
	}
	
	
	
	
	
}
