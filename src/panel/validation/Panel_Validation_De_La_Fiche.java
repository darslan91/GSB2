package panel.validation;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import action.validation.ActionAnnulerLaFiche;
import action.validation.ActionValiderLaFiche;
import modele.Modele;
import modele.validation.ModeleValider;
import panel.Vue;

public class Panel_Validation_De_La_Fiche extends JPanel{
	
	//Attribut
		//Vue
	private Vue vue;
		//label
	private JLabel lblMsg;
	private JLabel lblNom;
	private JLabel lblMois;
	
	//Affichage personnalisé
		//Forfait
	private JLabel lblForfait;
		//Information Complémentaire
	private JLabel lblMessageDuHaut;
	private JLabel lblTotalFrais;
		//Forfait - haut
	private JLabel lblMontantForfait;
	private JLabel lblForfaitEtape;
	private JLabel lblNbKm;
	private JLabel lblNuitHotel;
	private JLabel lblRepasResto;
		//Forfait - information
	private JLabel lblForfaitEtapeInfo;
	private JLabel lblNbKmInfo;
	private JLabel lblNuitHotelInfo;
	private JLabel lblRepasRestoInfo;
		//Montant
	private JLabel lblMontantEtape;
	private JLabel lblMontantKm;
	private JLabel lblMontantNuite;
	private JLabel lblMontantRepas;
	private JLabel lblMontantTotalForfais;
	
		//Hors forfait
	private JLabel lblHorsForfait;
	private JLabel lblDateHorsForfait;
	private JLabel lblLibelleHorsForfait;
	private JLabel lblJustifHorsForfait;
	private JLabel lblMontantHorsForfait;
	private JLabel lblMontantTotalHF;
		//Information des element hors forfait
	//ModeleValider.getHorsForfait renvoie un tableau de JLabele
	
		//Boutton
	private JButton btnValider;
	private JButton btnAnnuler;
	
		//String
	private String mois;
	private String nom;
	
	//Constructeur
	public Panel_Validation_De_La_Fiche(Vue uneVue, String unMois, String unNom){
		
		this.setLayout(new GridBagLayout());
		
		//Récupération de l'id de la personne
		String id = ModeleValider.getId(unNom);
		//Affichage Personnalisé
		this.lblForfait = new JLabel("Eléments Forfaitisées :");
			//Information Complémentaire
		String dateModif = ModeleValider.getDateModif(unMois, id);
		this.lblMessageDuHaut = new JLabel("Etat : Fiche Crée, saisie en cours depuis le " + dateModif);
			//Forfait - Haut
		this.lblMontantForfait = new JLabel("Montant");
		this.lblForfaitEtape = new JLabel("Forfait Etape");
		this.lblNbKm = new JLabel("Nombre Kilomètre");
		this.lblNuitHotel = new JLabel("Nuitée Hotel");
		this.lblRepasResto = new JLabel("Repas Restaurant");
		this.lblTotalFrais = new JLabel("Total");
			//Forfait - information
		String nbForfaitEtape = String.valueOf(ModeleValider.getNbForfaisEtape(unMois, id));
		this.lblForfaitEtapeInfo = new JLabel(nbForfaitEtape);
		String nbKm = String.valueOf(ModeleValider.getNbKilometre(unMois, id));
		this.lblNbKmInfo = new JLabel(nbKm);
		String nbNuite = String.valueOf(ModeleValider.getNbNuite(unMois, id));
		this.lblNuitHotelInfo = new JLabel(nbNuite);
		String nbRepas = String.valueOf(ModeleValider.getNbRepas(unMois, id));
		this.lblRepasRestoInfo = new JLabel(nbRepas);
			//Montant
		String montantEtape = String.valueOf(ModeleValider.getMontantEtape(unMois, id));
		this.lblMontantEtape = new JLabel(montantEtape);
		String montantKm = String.valueOf(ModeleValider.getTotalMontantKm(unMois, id));
		this.lblMontantKm = new JLabel(montantKm);
		String montantNuite = String.valueOf(ModeleValider.getMontantNuite(unMois, id));
		this.lblMontantNuite = new JLabel(montantNuite);
		String montantRepas = String.valueOf(ModeleValider.getMontantRepas(unMois, id));
		this.lblMontantRepas = new JLabel(montantRepas);
		String montantTotal = String.valueOf(ModeleValider.getMontantTotalForfait(unMois, id));
		this.lblMontantTotalForfais = new JLabel(montantTotal);
		
			//Hors forfait
		String justificatif = ModeleValider.getNbJustif(unMois, id);
		this.lblHorsForfait = new JLabel("Descriptif des éléments HorsForfait -" + justificatif + " justificatif(s) reçus.");
		this.lblDateHorsForfait = new JLabel("Date");
		this.lblLibelleHorsForfait = new JLabel("Libellé");
		this.lblJustifHorsForfait = new JLabel("Justificatif");
		this.lblMontantHorsForfait = new JLabel("Montant");
		String montantTotalHF = String.valueOf(ModeleValider.getMontantTotalHF(unMois, id));
		this.lblMontantTotalHF = new JLabel(montantTotalHF);
		
			//Montant des deux
		float montantFull = ModeleValider.getMontantTotalHF(unMois, id) + ModeleValider.getMontantTotalForfait(unMois, id);
		//Declaration
			//Vue
		this.vue = uneVue;
			//String
		this.mois = unMois;
		this.nom = unNom;
			//JLabel
		this.lblMsg = new JLabel(" ");
		this.lblMois = new JLabel(this.mois);
		this.lblNom = new JLabel(this.nom);
		
			//Btn
		this.btnValider = new JButton("Valider");
		this.btnValider.addActionListener(new ActionValiderLaFiche(this.vue, this.lblMsg, justificatif, montantFull, unMois, id));
		this.btnAnnuler = new JButton("Annuler");
		this.btnAnnuler.addActionListener(new ActionAnnulerLaFiche(this.vue, this.lblMsg));
		
		//Ajout
			//Contrainte
		//FORFAIT
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(5,0,0,0);
		gc.ipadx = 20;
		gc.ipady = 10;
		gc.gridx = 0;
		gc.gridy = 0;
		gc.gridwidth = 6;
		this.add(this.lblForfait, gc);
		gc.gridx = 0;
		gc.gridy = 1;
		this.add(this.lblMessageDuHaut, gc);
		
		gc.insets = new Insets(1,1,1,1);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.gridwidth = 1;
		this.add(this.lblMontantForfait, gc);
		this.lblMontantForfait.setBorder(BorderFactory.createLineBorder(Color.black));
		
		gc.gridx = 1;
		gc.gridy = 2;
		this.add(this.lblForfaitEtape, gc);
		this.lblForfaitEtape.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 2;
		gc.gridy = 2;
		this.add(this.lblNbKm, gc);
		this.lblNbKm.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 3;
		gc.gridy = 2;
		this.add(this.lblNuitHotel, gc);
		this.lblNuitHotel.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 4;
		gc.gridy = 2;
		this.add(this.lblRepasResto, gc);
		this.lblRepasResto.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 5;
		gc.gridy = 2;
		this.add(this.lblTotalFrais, gc);
		this.lblTotalFrais.setBorder(BorderFactory.createLineBorder(Color.black));
		
		gc.gridx = 1;
		gc.gridy = 3;
		this.add(this.lblForfaitEtapeInfo, gc);
		this.lblForfaitEtapeInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 2;
		gc.gridy = 3;
		this.add(this.lblNbKmInfo, gc);
		this.lblNbKmInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 3;
		gc.gridy = 3;
		this.add(this.lblNuitHotelInfo, gc);
		this.lblNuitHotelInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 4;
		gc.gridy = 3;
		this.add(this.lblRepasRestoInfo, gc);
		this.lblRepasRestoInfo.setBorder(BorderFactory.createLineBorder(Color.black));
		
		gc.gridx = 1;
		gc.gridy = 4;
		this.add(this.lblMontantEtape, gc);
		this.lblMontantEtape.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 2;
		gc.gridy = 4;
		this.add(this.lblMontantKm, gc);
		this.lblMontantKm.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 3;
		gc.gridy = 4;
		this.add(this.lblMontantNuite, gc);
		this.lblMontantNuite.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 4;
		gc.gridy = 4;
		this.add(this.lblMontantRepas, gc);
		this.lblMontantRepas.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx = 5;
		gc.gridy = 4;
		this.add(this.lblMontantTotalForfais, gc);
		this.lblMontantTotalForfais.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//HORS FORFAIT
		gc.insets = new Insets(10,0,0,0);
		gc.gridx = 0;
		gc.gridy++;
		gc.gridwidth = 6;
		this.add(this.lblHorsForfait, gc);
		gc.insets = new Insets(1,1,1,1);
		gc.gridwidth = 1;
		gc.gridx = 1;
		gc.gridy++;
		this.add(this.lblDateHorsForfait, gc);
		this.lblDateHorsForfait.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx++;
		this.add(this.lblLibelleHorsForfait, gc);
		this.lblLibelleHorsForfait.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx++;
		this.add(this.lblJustifHorsForfait, gc);
		this.lblJustifHorsForfait.setBorder(BorderFactory.createLineBorder(Color.black));
		gc.gridx++;
		this.add(this.lblMontantHorsForfait, gc);
		this.lblMontantHorsForfait.setBorder(BorderFactory.createLineBorder(Color.black));
		try {
			ResultSet rs = ModeleValider.getElementHorsForfait(unMois, id);
			gc.gridx = 1;
			gc.gridy = 7;

			while(rs.next()) {
				this.add(new JLabel(rs.getString(1)), gc);
				gc.gridx++;
				this.add(new JLabel(rs.getString(2)), gc);
				gc.gridx++;
				this.add(new JLabel(rs.getString(4)), gc);
				gc.gridx++;
				this.add(new JLabel(rs.getString(3)), gc);
				gc.gridx = 1;
				gc.gridy++;
			}
			//Attention pas recommandé, Madame veuillez faire abstraction des ceci x))) 
			//Le DSI Thibault Foulley
			ModeleValider.deconnexionBD();
		} catch (SQLException e) {
			System.out.println("Erreur : \n");
			e.printStackTrace();
		}
		gc.gridx = 5;
		gc.gridy ++;
		this.add(lblMontantTotalHF, gc);
		this.lblMontantTotalHF.setBorder(BorderFactory.createLineBorder(Color.black));
		//BOUTTON
		gc.insets = new Insets(20,0,0,0);
		gc.gridx = 0;
		gc.gridy  ++;
		gc.gridwidth = 3;
		this.add(this.btnValider, gc);
		gc.gridx = 3;
		this.add(this.btnAnnuler, gc);
	}
}
