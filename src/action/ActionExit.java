package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.Vue;

public class ActionExit implements ActionListener{

	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	
	/* CONSTRUCTEURS */
	public ActionExit(Vue uneVue){
		this.vue = uneVue;
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.vue.dispose();
	}
}