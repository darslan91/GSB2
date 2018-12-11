package generation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JLabel;

import org.omg.Messaging.SyncScopeHelper;

import classes.FicheFrais;
import classes.Visiteur;
import panel.Vue;
import classes.Visiteur;

public class ModeleGeneration {

	//attribut priv�
	private static Statement st = null;
	private static ResultSet rs = null;
	private static PreparedStatement pst = null;

	private static Connection connexion = null;

/* *************************************************************************************************************** */	

	//m�thode publiques statiques connexion
	public static void connexionBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018foulley", "tfoulley", "123456");
		//	connexion = DriverManager.getConnection("jdbc:mysql://172.16.252.10/slam2", "slam2", "GiPo94h");
		//	connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsbv2", "root", "");
			st = connexion.createStatement();
		} 
		catch (ClassNotFoundException erreur) {
			System.out.println("Driver non charg�!" + erreur);
		}
		catch (SQLException erreur){
			System.out.println("La connexion � la base de donn�es a �chou� ou Erreur SQL" + erreur);
		}
	}


	//deconnexion
	public static void deconnexionBD() {
		try {
			st.close();
			connexion.close();
		} catch (SQLException erreur) {
			// TODO Auto-generated catch block
			erreur.printStackTrace();
		} 
	}
/* *************************************************************************************************************** */
	//Affichage des utilisateurs dans les JComboBox
/* *************************************************************************************************************** */	
	//getLesDate
	public static String[] getDateFicheFrais(){
		String tab[] = new String[getNbMois()];
		connexionBD();
		String etat = "LA JE MET UN TRUCK INCONGRU POUR QU'IL PRENNE TOUT";
		try {
			pst = connexion.prepareStatement("SELECT distinct(mois) FROM fichefrais WHERE idetat != ?");
			pst.setString(1, etat);
			rs = pst.executeQuery();
			int i = 0;
			while(rs.next()) {
				tab[i] = rs.getString(1);
				i++;
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e );
			e.printStackTrace();
		}
		connexionBD();
		return tab;
	}
	
	//getNbMois
	public static int getNbMois() {
		connexionBD();
		int nb = 0;
		String etat = "LA JE MET UN TRUCK INCONGRU POUR QU'IL PRENNE TOUT";
		try {
			pst = connexion.prepareStatement("SELECT COUNT(DISTINCT(mois)) FROM fichefrais WHERE idetat != ? ");
			pst.setString(1, etat);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getInt(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e );
		}
		deconnexionBD();
		return nb;
	}
	
	//getTabNomVisiteur � DEPLACER DANS UN AUTRE MODELE
	public static String[] getTabNomVisiteur(String date, int nb){
		String tab[] = new String[nb];
		connexionBD();
		String etat = "LA JE MET UN TRUCK INCONGRU POUR QU'IL PRENNE TOUT";
		int i = 0;
		try {
			pst = connexion.prepareStatement("SELECT nom FROM visiteur v, fichefrais f WHERE v.id = f.idvisiteur AND mois = ? AND idetat != ?");
			pst.setString(1, date);
			pst.setString(2, etat);
			rs = pst.executeQuery();
			while(rs.next()) {
				tab[i] = rs.getString(1);
				i++;
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		return tab;
	}
	
	//getvisiteur � un certain mois
	public static int getNbVisiteurMois(String val) {
		connexionBD();
		int nb = 0;
		String etat = "LA JE MET UN TRUCK INCONGRU POUR QU'IL PRENNE TOUT";
		
		try {
			pst = connexion.prepareStatement("SELECT COUNT(DISTINCT(idVisiteur))FROM fichefrais WHERE idetat != ? AND mois = ?");
			pst.setString(1, etat);
			pst.setString(2, val);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getInt(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			
		}
		
		deconnexionBD();
		return nb;
	}
/* *************************************************************************************************************** */
	
	

/* *************************************************************************************************************** */
	//R�cup�ration de l'id et de la date de modification
/* *************************************************************************************************************** */
	
	//getId du visiteur
	public static String getId(String nom) {
		connexionBD();
		String id = "";
		
		try {
			pst = connexion.prepareStatement("SELECT id FROM visiteur WHERE nom = ?");
			pst.setString(1, nom);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				id = rs.getString(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return id;
	}
	
	//getDateModif de la fiche
	public static String getDateModif(String mois, String id) {
		connexionBD();
		String moisModif = "";
		
		try {
			pst = connexion.prepareStatement("SELECT datemodif FROM fichefrais WHERE mois = ? AND idvisiteur = ?");
			pst.setString(1, mois);
			pst.setString(2, id);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				moisModif = rs.getString(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return moisModif;
	}
/* *************************************************************************************************************** */
	


/* *************************************************************************************************************** */
	//R�cup�ration des valeur des elements forfaitis�
/* *************************************************************************************************************** */
	
	//getNbForfaisEtape du visiteur
	public static String getNbForfaisEtape(String mois, String id) {
		connexionBD();
		String nb = "";
		String idFrais = "ETP";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getString(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
	
	//getNbKilometre du visiteur
	public static String getNbKilometre(String mois, String id) {
		connexionBD();
		String nb = "";
		String idFrais = "KM";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getString(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
	
	//getNbNuite du visiteur
	public static String getNbNuite(String mois, String id) {
		connexionBD();
		String nb = "";
		String idFrais = "NUI";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getString(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
	
	//getNbRepas du visiteur
	public static String getNbRepas(String mois, String id) {
		connexionBD();
		String nb = "";
		String idFrais = "REP";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getString(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
/* *************************************************************************************************************** */
	

	
/* *************************************************************************************************************** */
//HORS FORFAIT
/* *************************************************************************************************************** */
	//getNbHF
	public static int getMontantTotalHF(String mois, String id) {
		connexionBD();
		int nb = 0;
		String val = " ";
		try {
			pst = connexion.prepareStatement("SELECT sum(montant) FROM lignefraishorsforfait WHERE mois = ? AND idvisiteur = ? AND lien != ?");
			pst.setString(1, mois);
			pst.setString(2, id);
			pst.setString(3, val);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getInt(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
	
	//getElementHorsForfait
	public static ResultSet getElementHorsForfait(String mois, String id) {
		connexionBD();		
		try {
			pst = connexion.prepareStatement("SELECT date,libelle,montant,lien FROM lignefraishorsforfait WHERE mois = ? AND idvisiteur = ?");
			pst.setString(1, mois);
			pst.setString(2, id);
			
			rs = pst.executeQuery();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		return rs;
	}
	
	//getNbJustificatif
	public static String getNbJustif(String mois, String id) {
		connexionBD();
		String nb = "";
		String justif = " ";
		
		try {
			pst = connexion.prepareStatement("SELECT COUNT(lien) FROM lignefraishorsforfait WHERE mois = ? AND idvisiteur = ? AND lien != ?");
			pst.setString(1, mois);
			pst.setString(2, id);
			pst.setString(3, justif);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				nb = rs.getString(1);
			}
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
/* *************************************************************************************************************** */



/* *************************************************************************************************************** */
//R�cup�ration nom et pr�nom
/* *************************************************************************************************************** */

	//R�cup�ration du nom
	public static String getNom(String id) {
		connexionBD();
		String nom = "";
		
		try {
			pst = connexion.prepareStatement("SELECT nom FROM visiteur WHERE id = ?");
			pst.setString(1, id);
			
			rs = pst.executeQuery();
			if(rs.next()) {
				nom = rs.getString(1);
			}
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nom;
	}
	
	//R�cup�ration du prenom
		public static String getPrenom(String id) {
			connexionBD();
			String nom = "";
			
			try {
				pst = connexion.prepareStatement("SELECT prenom FROM visiteur WHERE id = ?");
				pst.setString(1, id);
				
				rs= pst.executeQuery();
				if(rs.next()) {
					nom = rs.getString(1);
				}
			}
			catch(SQLException e) {
				System.out.println("Erreur : \n" + e);
			}
			
			deconnexionBD();
			return nom;
		}
		
	//adresse
		public static String getAdresse(String id) {
			connexionBD();
			String adr = "";
			
			try {
				pst = connexion.prepareStatement("SELECT adresse FROM visiteur WHERE id = ?");
				pst.setString(1, id);
				
				rs= pst.executeQuery();
				if(rs.next()) {
					adr = rs.getString(1);
				}
			}
			catch(SQLException e) {
				System.out.println("Erreur : \n" + e);
			}
			
			deconnexionBD();
			return adr;
		}	
/* *************************************************************************************************************** */	
}