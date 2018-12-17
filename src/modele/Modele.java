package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import classes.FicheFrais;
import classes.Visiteur;
import panel.Vue;
import panel.panel_Connexion;
import classes.Visiteur;

import java.security.MessageDigest;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

public class Modele {

	//attribut privé
	private static Statement st;
	private static ResultSet rs;
	private static PreparedStatement statement;
	
	private static Connection connexion ;
	
	//méthode publiques statiques connexion
	public static void connexionBD() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://172.16.203.100/2018foulley", "tfoulley", "123456");
	//		connexion = DriverManager.getConnection("jdbc:mysql://localhost/gsbv2", "root", "");
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
			// TODO Auto-generated catch block
			erreur.printStackTrace();
		} 
	}
	
	
	//sha1 décrypter mdp
/*	public static String decrypterMdp (String mdp) {
		String sha1 = "";
		try {
			MessageDigest cryptage = MessageDigest.getInstance("SHA-1");
			cryptage.reset();
			cryptage.update(mdp.getBytes("utf8"));
			sha1 = String.format("%040x", new BigInteger(1, cryptage.digest()));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return sha1;
	}*/
	
	
	//connexionSession
	public static boolean connexionSession(char [] unMdp ,String login){
		boolean result = false;
		connexionBD();
		String sql;
		sql = "SELECT mdp FROM visiteur WHERE login ='" + login + "' AND role = 1";
		String strMdp = "";
		
		try {
			for (int i = 0; i < unMdp.length; i = i + 1){
				strMdp = strMdp + unMdp[i];
			}
			rs = st.executeQuery(sql);
			while (rs.next()){
				if(strMdp.equals(rs.getString(1))){
				/*	decrypterMdp(strMdp);*/
					result = true;
				}
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
/* *********************************************************** */
	//Par thibault
/* *********************************************************** */
		//savoir si un object est serialisé
	public static boolean resteConnecter() {
		boolean bool = false;
		
		ObjectInputStream ois = null;
		try {
			FileInputStream fichier = new FileInputStream("stock.ser");
			ois = new ObjectInputStream(fichier);
		} catch (final java.io.IOException e) {
			System.out.println("Fichier non trouvé donc pas de sérialisation");
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return bool;
	}
	
		//Récupération du nom
	public static String getNomS() {
		String nom = "";
		
		ObjectInputStream ois = null;
		
		try {
			final FileInputStream fichier = new FileInputStream("stock.ser");
			ois = new ObjectInputStream(fichier);
			final Visiteur visiteur = (Visiteur) ois.readObject();
			
			nom = visiteur.getLogin();
			
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return nom;
	}
	
		//Récupération du mdp
	public static String getMdpS() {
		String mdp = "";
		
		ObjectInputStream ois = null;
		
		try {
			final FileInputStream fichier = new FileInputStream("stock.ser");
			ois = new ObjectInputStream(fichier);
			final Visiteur visiteur = (Visiteur) ois.readObject();
			
			mdp = visiteur.getMdp();
			
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return mdp;
	}
	
		//Sérialisation
	public static void serialise(String login, char[] mdp) {
		
		String mdpC = "";
		for(int i =0; i < mdpC.length(); i++) {
			mdpC = mdpC + mdp[i];
		}
		
		Visiteur visiteur = new Visiteur(login, mdpC, 1);
		ObjectOutputStream oos = null;
		
		try {
			final FileOutputStream fichier = new FileOutputStream("stock.ser");
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(visiteur);
			oos.flush();
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}