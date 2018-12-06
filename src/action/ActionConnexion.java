package action;

import panel.Panel_Connecter;
import panel.Vue;

import java.awt.Color;
import java.awt.event.*;

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
	
	/* CONSTRUCTEURS */
	public ActionConnexion(Vue uneVue, JLabel lblErreur, JTextField unLogin, JPasswordField unMdp){
		this.vue = uneVue;
		this.lblErreur = lblErreur;
		this.jtfLogin = unLogin;
		this.jpfMdp = unMdp;
	}
	
	/* ACTION */
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//vérifie la connexion utilisateur

		if(Modele.connexionSession(jpfMdp.getPassword(), jtfLogin.getText(), vue)){
			//vide la fenetre
			this.vue.remove(this.vue.getContentPane());
			//ajouter un panel
			this.vue.setContentPane(new Panel_Connecter(this.vue));
			//ajouter le MenuBar
			this.vue.setJMenuBar(new Menu(this.vue));
			//rafraichit la page
			this.vue.revalidate();
	//	}
	//	else {
			this.lblErreur.setText("Erreur Identifiant non valide");
			this.lblErreur.setForeground(Color.WHITE);
			this.lblErreur.setBackground(Color.RED);
			this.lblErreur.setOpaque(true);
	//	}
		
		
		
		/*
		this.vue.remove(this.vue.getContentPane());
		
		this.vue.setContentPane(new Panel_Connecter(this.vue));
		
		this.vue.setJMenuBar(new Menu(this.vue));
		
		this.vue.revalidate();
		*/
	}

}
