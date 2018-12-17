package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.Vue;
import panel.panel_ConnexionSave;

public class ActionResterCo implements ActionListener{

	/* ATTRIBUTS PRIVEES */
		//info param
	private panel.Vue vue;
	
	public ActionResterCo(Vue uneVue){
		this.vue = uneVue;
	}
	
	/* ACTION */
	public void actionPerformed(ActionEvent e) {
		
		this.vue.setContentPane(new panel_ConnexionSave(this.vue));
		
		this.vue.revalidate();
		
	}

}
