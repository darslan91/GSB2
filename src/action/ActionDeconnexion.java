package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.Vue;

public class ActionDeconnexion implements ActionListener{
	
	//Attributs
		//vue
	private Vue vue;
	
	public ActionDeconnexion(Vue uneVue){
		this.vue = uneVue;
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		this.vue.dispose();
		Vue uneVue = new Vue();
		
		
	}
	
}
