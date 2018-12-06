package modele.consulter;


import java.sql.*;
import java.util.*;
import java.util.Date;

import classes.FicheFrais;
import panel.Vue;


public class ModeleConsulter {

	//attribut privé
	private static Statement st;
	private static ResultSet rs;
	//private static PreparedStatement statement;
	private static Connection connexion ;
	
	//méthode publiques statiques connexion
	public static void connexionBD() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018foulley", "tfoulley", "123456");
		//	connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsbv2", "root", "");
			st = connexion.createStatement();
		} 
		catch (ClassNotFoundException erreur) {
			System.out.println("Driver non chargé!" + erreur);
		}
		catch (SQLException erreur){
			System.out.println("La connexion à la base de données a échoué ou Erreur SQL" + erreur);
		}
	}
	
	
	//deconnexion
	public static void deconnexionBD() {
		try {
			connexion.close();
			//System.out.println("Deconnexion réussi");
		} catch (SQLException erreur) {
			erreur.printStackTrace();
		} 
	}
	
	
	//connexionSession
	public static boolean connexionSession(String mdp, char [] unMdp ,String login, Vue uneVue){
		boolean result = false;
		connexionBD();
		String sql;
		sql = "Select mdp From visiteur Where login ='" + login + " ' ";
		Statement st;
		String strMdp = "";
		for (int i = 0; i < mdp.length() ; i = i + 1){
			strMdp = strMdp + unMdp[i];
		}
		
		if(mdp == strMdp){
			result = true;
		}
		
		try {
			st = connexion.createStatement();
			rs = st.executeQuery(sql);
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//getLesFicheFraisValider à DEPLACER DANS UN AUTRE MODELE
	/*public static ArrayList<FicheFrais> getLesFichesFraisValider(){
		ArrayList<FicheFrais> lesFichesFraisValider = new ArrayList<FicheFrais>();
		connexionBD();
		// variables
		String mois;
		float montantValider;
		Date dateModif;
		
		try{
			st = connexion.createStatement();
			String req ="SELECT * FROM fichefrais";
			rs = st.executeQuery(req);
			
			while(rs.next()//vrai){
				mois= rs.getString("mois");
				montantValider=rs.getFloat("montantValide");
				dateModif=rs.getDate("dateModif");
				
				lesFichesFraisValider.add(new FicheFrais(mois, montantValider, dateModif));
				System.out.println("Code = " + num + ", Nom = " + nom + ", Prénom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
		}
		catch(SQLException erreur){
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		return lesFichesFraisValider;
		
	}*/
	
	
/* ******************************************************************************************************** */
	// Récupération des fiche de frais à l'état valider 'VA'. Cette méthode retourne une LISTE
/* ******************************************************************************************************** */	
	public static ArrayList<FicheFrais> getLesFichesFraisValider(){
		ArrayList<FicheFrais> lesFichesFraisValider = new ArrayList<FicheFrais>();
		//connexioin a la base de données
		connexionBD();
		// variables
		String nom;
		String prenom;
		String mois;
		float montantValide;
		Date dateModif;
		int nbJustificatif;
		
		try{
			//st = connexion.createStatement();
			String req ="SELECT nom, prenom, mois, montantValide, dateModif, nbJustificatifs "
					+"FROM visiteur V, fichefrais F, etat E "
					+"WHERE V.id = F.idVisiteur AND E.id = F.idEtat "
					+"AND F.idEtat = 'VA' ";
			rs = st.executeQuery(req);
			
			while(rs.next()/*vrai*/){
				
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				mois = rs.getString("mois");
				montantValide = rs.getFloat("montantValide");
				dateModif = rs.getDate("dateModif");
				nbJustificatif = rs.getInt("nbJustificatifs");
				
				//Ajout d'un élément FicheFrais dans la liste declarer pour pouvoir l'utiliser dans le panel_fiche_valider
				lesFichesFraisValider.add(new FicheFrais(nom, prenom, mois, montantValide, dateModif, nbJustificatif));
				
				
				//System.out.println("Code = " + num + ", Nom = " + nom + ", Prénom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
		}
		catch(SQLException erreur){
			//Requete fausse ou problème dans l'execution
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		return lesFichesFraisValider;
		
	}
	
/* ******************************************************************************************************** */
	// Récupération des fiche de frais à l'état rembourser 'RB'. Cette méthode retourne une LISTE
/* ******************************************************************************************************** */	
	public static ArrayList<FicheFrais> getLesFichesFraisRembourser(){
		ArrayList<FicheFrais> lesFichesFraisRembourser = new ArrayList<FicheFrais>();
		//connexioin a la base de données
		connexionBD();
		// variables
		String nom;
		String prenom;
		String mois;
		float montantValide;
		Date dateModif;
		int nbJustificatif;
		
		try{
			//st = connexion.createStatement();
			String req ="SELECT nom, prenom, mois, montantValide, dateModif, nbJustificatifs "
					+"FROM visiteur V, fichefrais F, etat E "
					+"WHERE V.id = F.idVisiteur AND E.id = F.idEtat "
					+"AND F.idEtat = 'RB' ";
			rs = st.executeQuery(req);
			
			while(rs.next()/*vrai*/){
				
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				mois = rs.getString("mois");
				montantValide = rs.getFloat("montantValide");
				dateModif = rs.getDate("dateModif");
				nbJustificatif = rs.getInt("nbJustificatifs");
				
				//Ajout d'un élément FicheFrais dans la liste declarer pour pouvoir l'utiliser dans le panel_fiche_rembourser
				lesFichesFraisRembourser.add(new FicheFrais(nom, prenom, mois, montantValide, dateModif, nbJustificatif));
				
				
				//System.out.println("Code = " + num + ", Nom = " + nom + ", Prénom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
		}
		catch(SQLException erreur){
			//Requete fausse ou problème dans l'execution
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		return lesFichesFraisRembourser;
		
	}
	
/* *************************************************************************************************************** */
	// Récupération des fiche de frais à l'état "fiche crée, saisir en cours" 'CR'. Cette méthode retourne une LISTE
/* *****************************************************************************************************************/	
	public static ArrayList<FicheFrais> getLesFichesFraisCoursValidation(){
		ArrayList<FicheFrais> lesFichesFraisCoursValidation = new ArrayList<FicheFrais>();
		//connexioin a la base de données
		connexionBD();
		// variables
		String nom;
		String prenom;
		String mois;
		float montantValide;
		Date dateModif;
		int nbJustificatif;
		
		try{
			//st = connexion.createStatement();
			String req ="SELECT nom, prenom, mois, montantValide, dateModif, nbJustificatifs "
					+"FROM visiteur V, fichefrais F, etat E "
					+"WHERE V.id = F.idVisiteur AND E.id = F.idEtat "
					+"AND F.idEtat = 'CR' ";
			rs = st.executeQuery(req);
			
			while(rs.next()/*vrai*/){
				
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				mois = rs.getString("mois");
				montantValide = rs.getFloat("montantValide");
				dateModif = rs.getDate("dateModif");
				nbJustificatif = rs.getInt("nbJustificatifs");
				
				//Ajout d'un élément FicheFrais dans la liste declarer pour pouvoir l'utiliser dans le panel_fiche_rembourser
				lesFichesFraisCoursValidation.add(new FicheFrais(nom, prenom, mois, montantValide, dateModif, nbJustificatif));
				
				
				//System.out.println("Code = " + num + ", Nom = " + nom + ", Prénom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
		}
		catch(SQLException erreur){
			//Requete fausse ou problème dans l'execution
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		return lesFichesFraisCoursValidation;
		
	}
	
/* *************************************************************************************************************** */
	// Récupération
/* *****************************************************************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}