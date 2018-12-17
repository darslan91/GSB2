package action.consulter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

import panel.Vue;
import panel.recherche.Panel_Pdf_Recherche;
import panel.recherche.Panel_Validation_Recherche;

import com.qoppa.pdfWriter.PDFPrinterJob;

import controleurs.Menu;

public class ActionConsulterPDF implements ActionListener{
	
	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
	
	public ActionConsulterPDF(Vue vue){
		this.vue = vue;
	}
	
	
	//@Override
	public void actionPerformed(ActionEvent e) {
		
		//Remove
		this.vue.remove(this.vue.getContentPane());
		
			//Ajout menu
//		this.vue.setJMenuBar(new Menu(this.vue));
		
			//Ajout recherche du panel
		this.vue.setContentPane(new Panel_Pdf_Recherche(this.vue));
			
			//Revalidation
		this.vue.revalidate();
	}

}
