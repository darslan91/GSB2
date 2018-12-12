package generation;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.omg.Messaging.SyncScopeHelper;

import classes.FicheFrais;
import classes.Visiteur;
import panel.Vue;
import classes.Visiteur;

public class ModeleGeneration {

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
	
	//getTabNomVisiteur à DEPLACER DANS UN AUTRE MODELE
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
	
	//getvisiteur à un certain mois
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
	
	//getIdFicheFrais
	public static String getIdFicheFrais(String mois, String id) {
		connexionBD();
		String nb = "";
		
		try {
			pst = connexion.prepareStatement("SELECT idVisiteur FROM fichefrais WHERE mois = ? AND idvisiteur = ?");
			pst.setString(1, mois);
			pst.setString(2, id);
			
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
	
	//GetIdEtat
	public static String getIdEtat(String mois, String id) {
		connexionBD();
		String nb = "";
		
		try {
			pst = connexion.prepareStatement("SELECT idEtat FROM fichefrais WHERE mois = ? AND idvisiteur = ?");
			pst.setString(1, mois);
			pst.setString(2, id);
			
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
	
	//getMontantValide
	public static String getMontantValide(String mois, String id) {
		connexionBD();
		String nb = "";
		
		try {
			pst = connexion.prepareStatement("SELECT montantvalide FROM fichefrais WHERE mois = ? AND idvisiteur = ?");
			pst.setString(1, mois);
			pst.setString(2, id);
			
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
//Récupération info
/* *************************************************************************************************************** */

	//Récupération du nom
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
	
	//Récupération du prenom
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
	
	//cp
		public static String getCP(String id) {
			connexionBD();
			String adr = "";
			
			try {
				pst = connexion.prepareStatement("SELECT cp FROM visiteur WHERE id = ?");
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
	//ville
		public static String getVille(String id) {
			connexionBD();
			String adr = "";
			
			try {
				pst = connexion.prepareStatement("SELECT ville FROM visiteur WHERE id = ?");
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
	//dateembauche
		public static String getDateEmbauche(String id) {
			connexionBD();
			String adr = "";
			
			try {
				pst = connexion.prepareStatement("SELECT dateembauche FROM visiteur WHERE id = ?");
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



/* *************************************************************************************************************** */
//Récupération nom et prénom
/* *************************************************************************************************************** */

	//getidFraisForfait
	public static String[] getIdFraisForfait(String mois, String id){
		String result[] = new String[4];
		connexionBD();
		
		try {
			pst = connexion.prepareStatement("SELECT idfraisforfait FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			
			rs= pst.executeQuery();
			if(rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = rs.getString(4);
			}
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		
		return result;
	}
	//getQuantite
	public static String[] getQuantite(String mois, String id){
		String result[] = new String[4];
		connexionBD();
		
		try {
			pst = connexion.prepareStatement("SELECT quantite FROM lignefraisforfait WHERE idvisiteur = ? AND mois = ?");
			pst.setString(1, id);
			pst.setString(2, mois);
			
			rs= pst.executeQuery();
			if(rs.next()) {
				result[0] = rs.getString(1);
				result[1] = rs.getString(2);
				result[2] = rs.getString(3);
				result[3] = rs.getString(4);
			}
		}
		catch(SQLException e) {
			System.out.println("Erreur : \n" + e);
		}
		
		deconnexionBD();
		
		return result;
	}	
		
/* *************************************************************************************************************** */



/* *************************************************************************************************************** */
//Récupération nom et prénom
/* *************************************************************************************************************** */
	
	//Génération du XML
	public static void genererXML(String mois, String id){
		//Instanciation de la frame permettant d'afficher l'explorateur
		JFrame parentFrame = new JFrame();

		//Création de l'explorateur
		JFileChooser fileChooser = new JFileChooser();

		//Définition du titre de l'explorateur
		fileChooser.setDialogTitle("Choisissez où vous souhaitez enregistrer votre PDF");

		//Nous appliquons un filtre pour enregistrer en pdf
		FileFilter filter = new FileNameExtensionFilter("Fichier XML","xml");
		fileChooser.setFileFilter(filter);

		//Booléen qui permet de vérifier si le comptable a validé l'enregistrement
		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			if (path.substring(path.length()-4, path.length()).equals(".xml")) {
				path = fileChooser.getSelectedFile().getAbsolutePath();
				
				try{
					FileOutputStream fichier = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
					ObjectOutputStream s = new ObjectOutputStream(fichier);
					s.writeObject(creationXML(mois, id));
					s.flush();
					s.close();			
				}
				catch(IOException e){
					System.out.println("Erreur : \n" + e);
				}
				//pdfDoc.saveDocument(fileChooser.getSelectedFile().getAbsolutePath());		
				
			} 
			else {
				path = fileChooser.getSelectedFile().getAbsolutePath()+".xml";
				
				try{
					FileOutputStream fichier = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
					ObjectOutputStream s = new ObjectOutputStream(fichier);
					s.writeObject(creationXML(mois, id));
					s.flush();
					s.close();			
				}
				catch(IOException e){
					System.out.println("Erreur : \n" + e);
				}
				//pdfDoc.saveDocument(fileChooser.getSelectedFile().getAbsolutePath()+".pdf");
				
			}
		}
	}
	
	//Création du xml
	public static String creationXML(String mois, String id){
		String result = "";
		result = result +"<GSB2>\n";
		
			//visiteur
		result = result + "    <VISITEUR>\n";
		
		result = result + "        <NOM>\n";
		result = result + "            "+getNom(id)+"\n";
		result = result + "        </NOM>\n";
		
		result = result + "        <PRENOM>\n";
		result = result + "            "+getPrenom(id)+"\n";
		result = result + "        </PRENOM>\n";
		
		result = result + "        <ADRESSE>\n";
		result = result + "            "+getAdresse(id)+"\n";
		result = result + "        </ADRESSE>\n";
		
		result = result + "        <CP>\n";
		result = result + "            "+getCP(id)+"\n";
		result = result + "        </CP>\n";
		
		result = result + "        <VILLE>\n";
		result = result + "            "+getVille(id)+"\n";
		result = result + "        </VILLE>\n";
		
		result = result + "        <DATEEMBAUCHE>\n";
		result = result + "            "+getDateEmbauche(id)+"\n";
		result = result + "        </DATEEMBAUCHE>\n";
		
		result = result + "        <ROLE>\n";
		result = result + "            0"+"\n";
		result = result + "        </ROLE>\n";
		
		result = result + "    </VISITEUR>\n";
		
			//fichefrais
		result = result + "    <FICHEFRAIS>\n";
		
		result = result + "        <ID>\n";
		result = result + "            "+getIdFicheFrais(mois, id)+"\n";
		result = result + "        </ID>\n";
		
		result = result + "        <MOIS>\n";
		result = result + "            "+mois+"\n";
		result = result + "        </MOIS>\n";
		
		result = result + "        <IDETAT>\n";
		result = result + "            "+getIdEtat(mois, id)+"\n";
		result = result + "        </IDETAT>\n";
		
		result = result + "        <NBJUSTIFICATIF(S)>\n";
		result = result + "            "+getNbJustif(mois, id)+"\n";
		result = result + "        </NBJUSTIFICATIF(S)>\n";
		
		result = result + "        <MONTANTVALIDE>\n";
		result = result + "            "+getMontantValide(mois, id)+"\n";
		result = result + "        </MONTANTVALIDE>\n";
		
		result = result + "        <DATEMODIF>\n";
		result = result + "            "+getDateModif(mois, id)+"\n";
		result = result + "        </DATEMODIF>\n";
		
		result = result + "    </FICHEFRAIS>\n";
		
			//fraisforfait
		result = result + "    <LIGNEFRAISFORFAITS>\n";
		
		for(int i = 0; i<4 ; i++){
		result = result + "        <LIGNEFRAISFORFAIT>\n";
		
		result = result + "            <ID>\n";
		result = result + "                "+id+"\n";
		result = result + "            </ID>\n";
		
		result = result + "            <MOIS>\n";
		result = result + "                "+mois+"\n";
		result = result + "            </MOIS>\n";
		
		result = result + "            <IDFRAISFORFAIT>\n";
		result = result + "                "+getIdFraisForfait(mois, id)[i]+"\n";
		result = result + "            </IDFRAISFORFAIT>\n";
		
		result = result + "            <QUANTITE>\n";
		result = result + "                "+getQuantite(mois, id)[i]+"\n";
		result = result + "            </QUANTITE>\n";
		
		result = result + "        </LIGNEFRAISFORFAIT>\n";
		}
		
		result = result + "    </LIGNEFRAISFORFAITS>\n";
		
			//fraisHorsforfait
		
		result = result + "    <LIGNEFRAISHORSFORFAITS>\n";
		
		result = result + "        <LIGNEFRAISHORSFORFAIT>\n";
		
		result = result + "            <ID>\n";
		result = result + "            </ID>\n";
		
		result = result + "            <IDVISITEUR>\n";
		result = result + "            </IDVISITEUR>\n";
		
		result = result + "            <MOIS>\n";
		result = result + "            </MOIS>\n";
		
		result = result + "            <LIBELLE>\n";
		result = result + "            </LIBELLE>\n";
		
		result = result + "            <DATE>\n";
		result = result + "            </DATE>\n";
		
		result = result + "            <MONTANT>\n";
		result = result + "            </MONTANT>\n";
		
		result = result + "            <LIEN>\n";
		result = result + "            </LIEN>\n";
		
		result = result + "        </LIGNEFRAISHORSFORFAIT>\n";
		
		result = result + "    </LIGNEFRAISHORSFORFAITS>\n";
		
		result = result + "</GSB2>";
		
		
		return result;
	}
	
}