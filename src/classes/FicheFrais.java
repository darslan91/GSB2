package classes;

import java.sql.Date;



public class FicheFrais {

	//attribut privé
	private String idVisiteur;
	private String nom;
	private String prenom;
	private String mois;
	private int nbJustificatifs;
	private float montantValide;
	private java.util.Date dateModif;
	private String idEtat;
	
	
	
	//Constructeur
		//par défaut
	public FicheFrais (){
		
	}
	
		
		//attributs
	public FicheFrais(String unIdVisiteur, String unNom, String unPrenom, String unMois, int unNbJustificatifs, float unMontantValide, Date uneDateModif, String unIdEtat ){
		this.idVisiteur = unIdVisiteur;
		this.nom =unNom;
		this.prenom = unPrenom;
		this.mois = unMois;
		this.nbJustificatifs = unNbJustificatifs;
		this.montantValide = unMontantValide;
		this.dateModif = uneDateModif;
		this.idEtat = unIdEtat;
	}
	
	
		//Constructeur avec le nom, le prénom, le mois, le montant, la date de mofication, le nb de justificatif
	public FicheFrais(String unNom, String unPrenom, String unMois, float unMontantValide, java.util.Date dateModif, int unNbJustificatif){
		this.nom = unNom;
		this.prenom = unPrenom;
		this.mois = unMois;
		this.montantValide = unMontantValide;
		this.dateModif = dateModif;
		this.nbJustificatifs =unNbJustificatif;
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
	
		//nbJustificatifs
	public int getNbJustificatifs() {
		return nbJustificatifs;
	}
	public void setNbJustificatifs(int nbJustificatifs) {
		this.nbJustificatifs = nbJustificatifs;
	}
	
		//montantValide
	public float getMontantValide() {
		return montantValide;
	}
	public void setMontantValide(float montantValide) {
		this.montantValide = montantValide;
	}
	
		//dateModif
	public java.util.Date getDateModif() {
		return dateModif;
	}
	public void setDateModif(Date dateModif) {
		this.dateModif = dateModif;
	}
	
		//idEtat
	public String getIdEtat() {
		return idEtat;
	}
	public void setIdEtat(String idEtat) {
		this.idEtat = idEtat;
	}
	
		//nom
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
		//prenom
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
		
	
}