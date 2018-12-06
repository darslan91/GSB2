package classes;

public class FraisForfait {
	
	//attribut privé
	private String id;
	private String libelle;
	private float montant;
	
	
	//Constructeur
		//par défaut
	public FraisForfait (){
		
	}
			
				
		//attributs
	public FraisForfait (String unId, String unLibelle, float unMontant){
		this.id = unId;
		this.libelle = unLibelle;
		this.montant = unMontant;
	}
			
			
			
	//Accesseurs
		//id
	public void setId (String unId){
		this.id = unId;
	}
	
	public String getId (){
		return this.id;
	}
	
	
		//libelle
	public void setLibelle (String unLibelle){
		this.libelle = unLibelle;
	}
	
	public String getLibelle (){
		return this.libelle;
	}
	
	
		//montant
	public void setMontant (float unMontant){
		this.montant = unMontant;
	}
	
	public float getMontant (){
		return this.montant;
	}
	
	
}
