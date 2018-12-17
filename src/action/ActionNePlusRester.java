package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.Vue;
import panel.panel_Connexion;
import panel.panel_ConnexionSave;

public class ActionNePlusRester implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	//info param
	private panel.Vue vue;
	private int nb;
	
	public ActionNePlusRester(Vue uneVue, int nb){
		this.vue = uneVue;
		this.nb = nb;
	}

	/* ACTION */
	public void actionPerformed(ActionEvent e) {

		this.vue.setContentPane(new panel_Connexion(this.vue, this.nb));

		this.vue.revalidate();

	}
}
