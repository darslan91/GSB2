package action.consulter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import panel.Vue;

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
		
		System.out.println(this.login);
		
	}
}
