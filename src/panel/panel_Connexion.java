package panel;
import java.awt.*;

import javax.swing.*;

import action.ActionConnexion;
import action.ActionExit;
import panel.Vue;

public class panel_Connexion extends JPanel{
	
	/* ATTRIBUTS PRIVEES */
		//Label
	private JLabel lblMessage;
	private JLabel lblLogin;
	private JLabel lblMdp;
	private JLabel lblErreur;
	
		//Champs de textes (JTextField / JPassword)
	private JTextField jtfLogin;
	private JPasswordField jpfMdp;
	
		//Boutons
	private JButton btnSeConnecter;
	private JButton btnExit;
	
		//Vue
	private Vue vue;
	
	/* CONSTRUCTEURS */
	public panel_Connexion(Vue uneVue){
		
		//Double nvb
		this.vue = uneVue;
		
		this.setLayout(new GridBagLayout());
		
		/* DECLARATIONS */
			//Labels
		this.lblMessage = new JLabel("Connexion Comptable");
		this.lblLogin = new JLabel("Login : ");
		this.lblMdp = new JLabel("Password : ");
		this.lblErreur = new JLabel(" ");
		
			//Champs de textes
		this.jtfLogin = new JTextField();
		this.jpfMdp = new JPasswordField();
		
			//Boutons
		this.btnSeConnecter = new JButton("Se Connecter");
		this.btnSeConnecter.addActionListener(new ActionConnexion(this.vue, this.lblErreur, this.jtfLogin, this.jpfMdp));
		this.btnExit = new JButton("Exit");
		this.btnExit.addActionListener(new ActionExit(this.vue));
		
		/* AJOUTER AU PANEL */
		
			//Contrainte
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
			//Message
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,10,0);
		this.add(this.lblMessage, gc);
		
			//Login
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = new Insets(0,0,0,0);
		this.add(this.lblLogin, gc);
		gc.gridx = 1;
		gc.gridy = 1;
		this.add(this.jtfLogin, gc);
		
			//Password
		gc.gridx = 0;
		gc.gridy = 2;
		this.add(this.lblMdp, gc);
		gc.gridx = 1;
		gc.gridy = 2;
		this.add(this.jpfMdp, gc);
		
			//Label Erreur
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 2;
		this.add(this.lblErreur, gc);
		
			//Boutons
		gc.gridx = 0;
		gc.gridy = 4;
		gc.gridwidth = 1;
		this.add(this.btnSeConnecter, gc);
		gc.gridx = 1;
		gc.gridy = 4;
		this.add(this.btnExit, gc);
		
	}
}
