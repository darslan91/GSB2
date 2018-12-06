package classes;

import java.util.Date;

public class Comptable {

	//attribut privé
	private String id;
	private String nom;
	private String prenom;
	private String login;
	private String mdp;
	private String adresse;
	private String cp;
	private String ville;
	private Date dateEmbauche;
	private String role;
	
	
	//Constructeurs
			//par défaut
		public Comptable (){
		}
						
								
			//attributs
		public Comptable (String unId, String unNom, String unPrenom, String unLogin, String unMdp, String uneAdresse, String unCp, String uneVille, Date uneDateEmbauche, String unRole){
			this.id = unId;
			this.nom = unNom;
			this.prenom = unPrenom;
			this.login = unLogin;
			this.mdp = unMdp;
			this.adresse = uneAdresse;
			this.cp = unCp;
			this.ville = uneVille;
			this.dateEmbauche = uneDateEmbauche;
			this.role = unRole;
		}

		
						
		//Accesseurs
			//id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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

			//login
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
	
			//mdp
		public String getMdp() {
			return mdp;
		}
		public void setMdp(String mdp) {
			this.mdp = mdp;
		}

			//adresse
		public String getAdresse() {
			return adresse;
		}
		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}

			//cp
		public String getCp() {
			return cp;
		}
		public void setCp(String cp) {
			this.cp = cp;
		}

			//ville
		public String getVille() {
			return ville;
		}
		public void setVille(String ville) {
			this.ville = ville;
		}

			//dateEmbauche
		public Date getDateEmbauche() {
			return dateEmbauche;
		}
		public void setDateEmbauche(Date dateEmbauche) {
			this.dateEmbauche = dateEmbauche;
		}

			//role
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
			
}
