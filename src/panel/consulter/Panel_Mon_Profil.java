package panel.consulter;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.consulter.ModeleConsulter;
import panel.Vue;

public class Panel_Mon_Profil extends JPanel{

	/*Attribut privé*/
		//Info
	private Vue vue;
	private String login;
		
		//Labels
	private JLabel lblMessages;
	
	private JLabel lblId;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblAdresse;
	private JLabel lblCp;
	private JLabel lblVille;
	private JLabel lblDateEmbauche;
	private JLabel lblRole;
	
	private JLabel lblId1;
	private JLabel lblNom1;
	private JLabel lblPrenom1;
	private JLabel lblAdresse1;
	private JLabel lblVille1;
	private JLabel lblDateEmbauche1;
	private JLabel lblRole1;
	
	/*Constructeur*/
	public Panel_Mon_Profil(Vue vue, String login){
		this.vue = vue;
		this.login = login;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		
		String id = ModeleConsulter.getId(this.login);
		String nom = ModeleConsulter.getNom(id);
		String prenom = ModeleConsulter.getPrenom(id);
		String adresse = ModeleConsulter.getAdresse(id);
		String cp = ModeleConsulter.getCp(id);
		String ville = ModeleConsulter.getVille(id);
		String dateEmbauche = ModeleConsulter.getDateEmbauche(id);
		
		//Instanciation
			//label
		this.lblId1 = new JLabel("Votre id :");
		this.lblNom1 = new JLabel("Nom :");
		this.lblPrenom1 = new JLabel("Prenom :");
		this.lblAdresse1 = new JLabel("Adresse :");
		this.lblVille1 = new JLabel("Ville :");
		this.lblDateEmbauche1 = new JLabel("Date d'embauche :");
		this.lblRole1 = new JLabel("Role :");
		
		this.lblMessages = new JLabel("Mon profil");
		this.lblId = new JLabel(id);
		this.lblNom = new JLabel(nom);
		this.lblPrenom = new JLabel(prenom);
		this.lblAdresse = new JLabel(adresse);
		this.lblCp = new JLabel(", "+cp);
		this.lblVille = new JLabel(ville);
		this.lblDateEmbauche = new JLabel(dateEmbauche);
		this.lblRole = new JLabel("Comptable");
		
		
		gc.insets = new Insets(0,0,20,0);
		gc.gridx = 0;
		gc.gridy = 0;
		this.add(this.lblMessages, gc);
		
		gc.insets = new Insets(0,0,5,0);
		gc.gridx = 0;
		gc.gridy++;
		this.add(this.lbl,gc);
		gc.gridx++;
		gc.gridy++;
		this.add(this.lblId, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		this.add(this.,gc);
		gc.gridx++;
		gc.gridy++;
		this.add(this.lblNom, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		this.add(this.,gc);
		gc.gridx++;
		gc.gridy++;
		this.add(this.lblPrenom, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		this.add(this.,gc);
		gc.gridx++;
		gc.gridy++;
		this.add(this.lblAdresse, gc);
		gc.gridx++;
		this.add(this.lblCp, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		this.add(this.,gc);
		gc.gridx++;
		gc.gridy++;
		this.add(this.lblVille, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		this.add(this.,gc);
		gc.gridx++;
		gc.gridy++;
		this.add(this.lblRole, gc);
		
	}
}
