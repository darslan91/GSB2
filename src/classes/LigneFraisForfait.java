package classes;

public class LigneFraisForfait {

	//attribut privé
	private String idVisiteur;
	private String mois;
	private String idFraisFofait;
	private float quantite;
	
	
	//Constructeur
		//par défaut
	public LigneFraisForfait (){
	}	
				
				
		//attributs
	public LigneFraisForfait (String unIdVisiteur, String unMois, String unIdFraisForfait, float uneQuantite){
		this.idVisiteur = unIdVisiteur;
		this.mois = unMois;
		this.idFraisFofait = unIdFraisForfait;
		this.quantite = uneQuantite;
	}
				
				
				
	//Accesseurs
		//idVisiteur
	public String getIdVisiteur() {
		return idVisiteur;
	}
	public void setIdVisiteur(String idVisiteur) {
		this.idVisiteur = idVisiteur;
	}
	
		//mois
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	
		//idFraisForfais
	public String getIdFraisFofait() {
		return idFraisFofait;
	}
	public void setIdFraisFofait(String idFraisFofait) {
		this.idFraisFofait = idFraisFofait;
	}
	
		//quantite
	public float getQuantite() {
		return quantite;
	}

	public void setQuantite(float quantite) {
		this.quantite = quantite;
	}
	
	
}