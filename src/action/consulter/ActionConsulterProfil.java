package action.consulter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.Vue;
import panel.consulter.Panel_Mon_Profil;

public class ActionConsulterProfil implements ActionListener{
	
	//Attribut
		//Vue
	private Vue vue;
	private String login;
	
	//Constructeur
	public ActionConsulterProfil(Vue uneVue, String login){
		this.vue = uneVue;
		this.login = login;
	}

	
	public void actionPerformed(ActionEvent e) {
		
		this.vue.remove(this.vue.getContentPane());
		
		this.vue.setContentPane(new Panel_Mon_Profil(this.vue, this.login));
		
		this.vue.revalidate();
	}
}
