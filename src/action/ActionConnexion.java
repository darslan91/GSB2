package action;

import panel.Panel_Connecter;
import panel.Vue;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modele.Modele;
import controleurs.Menu;

public class ActionConnexion implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	private JLabel lblErreur;
	private JTextField jtfLogin;
	private JPasswordField jpfMdp;
	private int nb;
	
	/* CONSTRUCTEURS */
	public ActionConnexion(Vue uneVue, JLabel lblErreur, JTextField unLogin, JPasswordField unMdp){
		this.vue = uneVue;
		this.lblErreur = lblErreur;
		this.jtfLogin = unLogin;
		this.jpfMdp = unMdp;
	}
	
	public ActionConnexion(Vue uneVue, JLabel lblErreur, JTextField unLogin, JPasswordField unMdp, int nb){
		this.vue = uneVue;
		this.lblErreur = lblErreur;
		this.jtfLogin = unLogin;
		this.jpfMdp = unMdp;
		this.nb = nb;
	}
	
	/* ACTION */
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//vérifie la connexion utilisateur
		
		if(Modele.connexionSession(jpfMdp.getPassword(), jtfLogin.getText())){
				//Si appuis sur le btn
			if(this.nb == 1) {
				Modele.serialise(this.jtfLogin.getText(), this.jpfMdp.getText());
			}
			if(this.nb == 0) {
				if(Modele.resteConnecter()) {
					File fichier = new File("stock.txt");
					fichier.delete();
				}
			}
			
			
			//vide la fenetre
			this.vue.remove(this.vue.getContentPane());
			//ajouter un panel
			this.vue.setContentPane(new Panel_Connecter(this.vue, this.jtfLogin.getText()));
			//ajouter le MenuBar
			this.vue.setJMenuBar(new Menu(this.vue, this.jtfLogin.getText()));
			//rafraichit la page
			this.vue.revalidate();
		}
		else {
			this.lblErreur.setText("Erreur Identifiant non valide");
			this.lblErreur.setForeground(Color.WHITE);
			this.lblErreur.setBackground(Color.RED);
			this.lblErreur.setOpaque(true);
		}
		
	}

}
