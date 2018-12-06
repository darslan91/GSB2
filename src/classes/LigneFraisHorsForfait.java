package classes;

import java.util.Date;

public class LigneFraisHorsForfait {

	//attribut privé
	private int id;
	private String idVisiteur;
	private String mois;
	private String libelle;
	private Date date;
	private float montant;
	private String lien;
	
	

	//Constructeur
		//par défaut
	public LigneFraisHorsForfait (){
	}
				
						
		//attributs
	public LigneFraisHorsForfait (int unId, String unIdVisiteur, String unMois, String unLibelle, Date uneDate, float unMontant, String unLien){
		this.id = unId;
		this.idVisiteur = unIdVisiteur;
		this.mois = unMois;
		this.libelle = unLibelle;
		this.date = uneDate;
		this.montant = unMontant;
		this.lien = unLien;
	}
				
				
				
	//Accesseurs
		//id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
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
	
		//libelle
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
		//date
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
		//montant
	public float getMontant() {
		return montant;
	}
	public void setMontant(float montant) {
		this.montant = montant;
	}
	
		//lien
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}
	
}
