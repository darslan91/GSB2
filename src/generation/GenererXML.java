package generation;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GenererXML implements ActionListener{
	
	/* ATTRIBUTS PRIVEES */
	private String id;
	private String mois;
	
	/* CONSTRUCTEUR */
	public GenererXML(String id, String mois){
		this.id = id;
		this.mois = mois;
	}

	//@Override
	public void actionPerformed(ActionEvent arg0) {
		
		ModeleGeneration.genererXML(this.mois, this.id);
		
	}
	//test
	
}
