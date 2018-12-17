package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import modele.Modele;
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
		
		if(Modele.resteConnecter() && this.nb == 0) {
			Path path = Paths.get("stock.txt");
			try {
			    Files.delete(path);
			} catch (NoSuchFileException x) {
			    System.err.format("%s:" + " chemin introuvable %n", path);
			} catch (DirectoryNotEmptyException x) {
			    System.err.format("%s n'est pas vide %n", path);
			} catch (IOException x) {
			    // problèmes de permission
			    System.err.println(x);
			}
		}
		
		this.vue.revalidate();

	}
}
