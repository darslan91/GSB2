package classes;

public class Etat {
	
	//attribut privé
	private String id;
	private String libelle;
	
	
	
	//Constructeur
		//par défaut
	public Etat (){
		
	}
	
	
		//attributs
	public Etat (String unId, String unLibelle){
		this.id = unId;
		this.libelle = unLibelle;
	}
	
	
	
	//Accesseurs
		//id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
		//libelle
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	

	
}