package modele.validation;

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

public class ModeleValider {

	//attribut privé
	private static Statement st = null;
	private static ResultSet rs = null;
	private static PreparedStatement pst = null;

	private static Connection connexion = null;

/* *************************************************************************************************************** */	

	//méthode publiques statiques connexion
	public static void connexionBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		//	connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018foulley", "tfoulley", "123456");
		//	connexion = DriverManager.getConnection("jdbc:mysql://172.16.252.10/slam2", "slam2", "GiPo94h");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsbv2", "root", "");
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
			st.close();
			connexion.close();
		} catch (SQLException erreur) {
			// TODO Auto-generated catch block
			erreur.printStackTrace();
		} 
	}
/* *************************************************************************************************************** */
	
	
	
/* *************************************************************************************************************** */
	//Affichage des utilisateurs dans les JComboBox
/* *************************************************************************************************************** */	
	//getLesDate
	public static String[] getDateFicheFrais(){
		String tab[] = new String[getNbMois()];
		connexionBD();
		String etat = "CR";
		try {
			pst = connexion.prepareStatement("SELECT distinct(mois) FROM fichefrais WHERE idetat = ?");
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
		String etat = "CR";
		try {
			pst = connexion.prepareStatement("SELECT COUNT(DISTINCT(mois)) FROM fichefrais WHERE idetat = ? ");
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
	
	//getTabNomVisiteur à DEPLACER DANS UN AUTRE MODELE
	public static String[] getTabNomVisiteur(String date, int nb){
		String tab[] = new String[nb];
		connexionBD();
		String etat = "CR";
		int i = 0;
		try {
			pst = connexion.prepareStatement("SELECT nom FROM visiteur v, fichefrais f WHERE v.id = f.idvisiteur AND mois = ? AND idetat = ?");
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
	
	//getvisiteur à un certain mois
	public static int getNbVisiteurMois(String val) {
		connexionBD();
		int nb = 0;
		String etat = "CR";
		
		try {
			pst = connexion.prepareStatement("SELECT COUNT(DISTINCT(idVisiteur))FROM fichefrais WHERE idetat = ? AND mois = ?");
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
	//Récupération de l'id et de la date de modification
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
	//Récupération des valeur des elements forfaitisé
/* *************************************************************************************************************** */
	
	//getNbForfaisEtape du visiteur
	public static int getNbForfaisEtape(String mois, String id) {
		connexionBD();
		int nb = 0;
		String idFrais = "ETP";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
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
	
	//getNbKilometre du visiteur
	public static int getNbKilometre(String mois, String id) {
		connexionBD();
		int nb = 0;
		String idFrais = "KM";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
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
	
	//getNbNuite du visiteur
	public static int getNbNuite(String mois, String id) {
		connexionBD();
		int nb = 0;
		String idFrais = "NUI";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
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
	
	//getNbRepas du visiteur
	public static int getNbRepas(String mois, String id) {
		connexionBD();
		int nb = 0;
		String idFrais = "REP";		
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
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
/* *************************************************************************************************************** */
	

	
/* *************************************************************************************************************** */
	//Récupération des Montant forfaitisé
/* *************************************************************************************************************** */
	//getMontantEtape du visiteur
	public static int getMontantEtape(String mois, String id) {
		connexionBD();
		int nb = 0;
		String idFrais = "ETP";
		
		try {
			pst = connexion.prepareStatement("SELECT montant FROM fraisforfait WHERE id = ?");
			pst.setString(1, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getInt(1)*getNbForfaisEtape(mois, id);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
	
	//getMontantKm du visiteur
	public static float getMontatKm(String mois, String id) {
		connexionBD();
		float nb = 0;
		String idFrais = "CAT";

		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur =  ? AND mois = ? AND idfraisforfait = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			pst.setString(3, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getFloat(1);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		deconnexionBD();
		return nb;
	}
	public static float getTotalMontantKm(String mois, String id) {
		return getNbKilometre(mois, id) * getMontatKm(mois, id);
	}
	
	
	//getMontantNuite du visiteur
	public static int getMontantNuite(String mois, String id) {
		connexionBD();
		int nb = 0;
		String idFrais = "NUI";		

		try {
			pst = connexion.prepareStatement("SELECT montant FROM fraisforfait WHERE id = ?");
			pst.setString(1, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getInt(1)*getNbNuite(mois, id);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
	
	//getMontantRepas du visiteur
	public static int getMontantRepas(String mois, String id) {
		connexionBD();
		int nb = 0;
		String idFrais = "REP";		

		try {
			pst = connexion.prepareStatement("SELECT montant FROM fraisforfait WHERE id = ?");
			pst.setString(1, idFrais);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				nb = rs.getInt(1)*getNbRepas(mois, id);
			}
			rs.close();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		return nb;
	}
	
	//getMontantTotal du visiteur
	public static float getMontantTotalForfait(String mois, String id) {
		connexionBD();
		float nb = 0;
		
		nb = getMontantEtape(mois, id);
		nb = nb + getTotalMontantKm(mois, id);
		nb = nb + getMontantNuite(mois, id);
		nb = nb + getMontantRepas(mois, id);
		
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
		String nb = "0";
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
//Validation de la fiche
/* *************************************************************************************************************** */

	//validerFiche
	public static void validerLaFiche(String justificatif, float montant , String mois, String id) {
		connexionBD();
		String idEtat = "VA";
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		try {
			pst = connexion.prepareStatement("UPDATE fichefrais set nbjustificatifs = ?, montantvalide = ?, datemodif = ?, idetat = ? WHERE mois = ? AND idvisiteur = ?");
			pst.setString(1, justificatif);
			pst.setFloat(2, montant);
			pst.setString(3, date);
			pst.setString(4, idEtat);
			pst.setString(5, mois);
			pst.setString(6, id);
			
			pst.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
	}
/* *************************************************************************************************************** */	
}