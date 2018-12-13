package modele.consulter;


import java.sql.*;
import java.util.*;
import java.util.Date;

import classes.FicheFrais;
import classes.LigneFraisForfait;
import panel.Vue;


public class ModeleConsulter {

	//attribut priv�
	//private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement statement;
	private static Connection connexion ;
	
	
	
/*	--------------------------------------------------------------------------------------------------------
/										CONNEXION A LA BASE DE DONNEES 
/   --------------------------------------------------------------------------------------------------------
/		Permet la connexion � la base de donnees. Plusieur infos sont � renseigne :
/			- adresse serveur, (172.16.203.100)
/			- nom de la BDD, (2018foulley)
/			- utilisateur et  (tfoulley)
/			- mot de passe.	 (123456)
 */
	public static void connexionBD() {
		
		//Gestion des exceptions possibless 
		try {
			//Chargment du driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//Connexion � la BDD
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018foulley", "tfoulley", "123456");
		//	connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsbv2", "root", "");
		} 
		catch (ClassNotFoundException erreur) {
			System.out.println("Driver non charg�!" + erreur);
		}
		catch (SQLException erreur){
			System.out.println("La connexion � la base de donn�es a �chou� ou Erreur SQL" + erreur);
		}
	}
	
	
/*  ---------------------------------------------------------------------------------------------------------
/										DECONNEXION DE LA BASE DE DONNEES
/  ----------------------------------------------------------------------------------------------------------
/ 		Deconnexion a la base de donnees pour permettre de ne pas surcharger le serveur
 */
	public static void deconnexionBD() {
		try {
			connexion.close();
			//System.out.println("Deconnexion r�ussi");
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
	
	//getLesFicheFraisValider � DEPLACER DANS UN AUTRE MODELE
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
				System.out.println("Code = " + num + ", Nom = " + nom + ", Pr�nom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
		}
		catch(SQLException erreur){
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		return lesFichesFraisValider;
		
	}*/
	
	
/* ******************************************************************************************************** */
	// R�cup�ration des fiche de frais � l'�tat "valider" 'VA'. Cette m�thode retourne une LISTE
/* ********************************************************************************************************
/ Cette m�thode r�cup�re les fiches de frais � l'�tat valider et fait l'insertion dans la liste lesFichesFraisValider.
*/

	public static ArrayList<FicheFrais> getLesFichesFraisValider(){
		ArrayList<FicheFrais> lesFichesFraisValider = new ArrayList<FicheFrais>();
		//connexioin a la base de donn�es
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
			String req ="SELECT nom, prenom, mois, montantValide, dateModif, nbJustificatifs FROM visiteur V, fichefrais F, etat E WHERE V.id = F.idVisiteur AND E.id = F.idEtat AND F.idEtat = 'VA' ";
			statement = connexion.prepareStatement(req);
			rs = statement.executeQuery(req);
			
			while(rs.next()/*vrai*/){
				
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				mois = rs.getString("mois");
				montantValide = rs.getFloat("montantValide");
				dateModif = rs.getDate("dateModif");
				nbJustificatif = rs.getInt("nbJustificatifs");
				
				//Ajout d'un �l�ment FicheFrais dans la liste declarer pour pouvoir l'utiliser dans le panel_fiche_valider
				lesFichesFraisValider.add(new FicheFrais(nom, prenom, mois, montantValide, dateModif, nbJustificatif));
				
				
				//System.out.println("Code = " + num + ", Nom = " + nom + ", Pr�nom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
			statement.close();
		}
		catch(SQLException erreur){
			//Requete fausse ou probl�me dans l'execution
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		deconnexionBD();
		return lesFichesFraisValider;
		
	}
	
/* ******************************************************************************************************** */
	// R�cup�ration des fiche de frais � l'�tat "rembourser" 'RB'. Cette m�thode retourne une LISTE
/* ******************************************************************************************************** */	
	public static ArrayList<FicheFrais> getLesFichesFraisRembourser(){
		ArrayList<FicheFrais> lesFichesFraisRembourser = new ArrayList<FicheFrais>();
		//connexioin a la base de donn�es
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
			String req = "SELECT nom, prenom, mois, montantValide, dateModif, nbJustificatifs FROM visiteur V, fichefrais F, etat E WHERE V.id = F.idVisiteur AND E.id = F.idEtat AND F.idEtat = 'RB' ";
			statement = connexion.prepareStatement(req);
			rs = statement.executeQuery();
			
			while(rs.next()/*vrai*/){
				
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				mois = rs.getString("mois");
				montantValide = rs.getFloat("montantValide");
				dateModif = rs.getDate("dateModif");
				nbJustificatif = rs.getInt("nbJustificatifs");
				
				//Ajout d'un �l�ment FicheFrais dans la liste declarer pour pouvoir l'utiliser dans le panel_fiche_rembourser
				lesFichesFraisRembourser.add(new FicheFrais(nom, prenom, mois, montantValide, dateModif, nbJustificatif));
				
				
				//System.out.println("Code = " + num + ", Nom = " + nom + ", Pr�nom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
			statement.close();
		}
		catch(SQLException erreur){
			//Requete fausse ou probl�me dans l'execution
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		
		deconnexionBD();
		return lesFichesFraisRembourser;
		
	}
	
/* *************************************************************************************************************** */
	// R�cup�ration des fiche de frais � l'�tat "fiche cr�e, saisir en cours" 'CR'. Cette m�thode retourne une LISTE.
/* *****************************************************************************************************************/	
	public static ArrayList<FicheFrais> getLesFichesFraisCoursValidation(){
		ArrayList<FicheFrais> lesFichesFraisCoursValidation = new ArrayList<FicheFrais>();
		//connexion a la base de donn�es
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
			String req = "SELECT nom, prenom, mois, montantValide, dateModif, nbJustificatifs FROM visiteur V, fichefrais F, etat E WHERE V.id = F.idVisiteur AND E.id = F.idEtat AND F.idEtat = 'CR' ";
			statement = connexion.prepareStatement(req);
			rs = statement.executeQuery();
			
			while(rs.next()/*vrai*/){
				
				nom = rs.getString("nom");
				prenom = rs.getString("prenom");
				mois = rs.getString("mois");
				montantValide = rs.getFloat("montantValide");
				dateModif = rs.getDate("dateModif");
				nbJustificatif = rs.getInt("nbJustificatifs");
				
				//Ajout d'un �l�ment FicheFrais dans la liste declarer pour pouvoir l'utiliser dans le panel_fiche_rembourser
				lesFichesFraisCoursValidation.add(new FicheFrais(nom, prenom, mois, montantValide, dateModif, nbJustificatif));
				
				
				//System.out.println("Code = " + num + ", Nom = " + nom + ", Pr�nom = " + prenom + ", Email = " + email + ", Commentaire = " + commentaire);
			}
			rs.close();
			statement.close();
		}
		catch(SQLException erreur){
			//Requete fausse ou probl�me dans l'execution
			System.out.println("Erreur dans l'execution de la requete " + erreur);
		}
		
		deconnexionBD();
		return lesFichesFraisCoursValidation;
		
	}
	
	
/* -------------------------------------------------------------------------------------
/                            RECUPERATION DE L'ID
/  -------------------------------------------------------------------------------------
/   On vas pouvoir l'id du visiteur gr�ce au nom et au prenom, r�cuperer par le tableau
 */
	public static String getId(Object nom, Object prenom){
		connexionBD();
		String id = "";
		
		try{
			
			String req = "SELECT id FROM visiteur WHERE nom = ? AND prenom = ?";
			PreparedStatement statement2 = connexion.prepareStatement(req);
			statement2.setString(1, nom.toString());
			statement2.setString(2, prenom.toString());
			ResultSet rs2 = statement2.executeQuery();
			rs2.next();
			
			id = rs2.getString("id");
			
			rs.close();
			statement.close();
		}
		catch(SQLException erreur){
			//Pas le bon ID, probl�me dans l'ex�cution de la requ�te
			System.out.println("Erreur dans l'ex�cution de la requ�te " + erreur);
		}
		
		deconnexionBD();
		return id;
				
	}
	
	
/* ----------------------------------------------------------------------------------------
/							RECUPERER LE MONTANT VALIDER
/  ----------------------------------------------------------------------------------------	
/   Cette m�thode retourne le montant valider pour une fiche qui appartient a cet id et
/   a ce mois passer en param�tre
 */
	public static float getMontantValider(Object mois, String id){
		float monMontantValide = 0;
		
		try{
			
			String req = "SELECT montantValider FROM fichefrais WHERE idVisiteur = ? AND mois = ?";
			statement = connexion.prepareStatement(req);
			statement.setString(1, mois.toString());
			statement.setString(2, id);
			rs = statement.executeQuery(req);
			rs.next();
			
			monMontantValide = rs.getFloat("montantValide");
			rs.close();
			statement.close();
		}
		catch(SQLException erreur){
			//Pas le bon montantValide, probl�me dans l'ex�cution de la requ�te
			System.out.println("Erreur dans l'ex�cution de la requ�te " + erreur);
		}
		
		deconnexionBD();
		return monMontantValide;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
}