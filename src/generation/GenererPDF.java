package generation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Vector;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFGraphics;
import com.qoppa.pdfWriter.PDFPage;
import com.qoppa.pdfWriter.PDFPrinterJob;

public class GenererPDF implements ActionListener, Printable{
	
	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
		//Page
	private int m_CurrentPage;
		//Constante
	private final static int DEFAULT_COLUMN_WIDTH = 72;
	private final static int CELL_MARGIN_X = 4;
	private final static int CELL_MARGIN_Y = 4;
		//Vecteurs
	private Vector m_Data;
	private int m_ColumnWidths [];
	private boolean m_DrawGrid;
	
		//info
	private String id;
	private String mois;
	
	/* CONSTRUCTEURS */
	public GenererPDF(Vector data, int [] colWidths, boolean drawGrid, String id, String mois){
		super();
		m_Data = data;
		m_ColumnWidths = colWidths;
		m_DrawGrid = drawGrid;
		this.id = id;
		this.mois = mois;
	}

/* *************************************************************************************************************** */



/* *************************************************************************************************************** */
//Méthodes
/* *************************************************************************************************************** */
	
	public static Vector initData(){
		Vector data = new Vector ();		
		return data;
	}
	
	public int print(Graphics g, PageFormat pf, int pageIndex){
		try
        {
            // Create a document and a page in default Locale format
            PDFDocument pdfDoc = new PDFDocument();
            PDFPage newPage = pdfDoc.createPage(new PageFormat());
            
            // Draw to the page
            Graphics2D g2d = newPage.createGraphics();
            g2d.setFont (PDFGraphics.HELVETICA.deriveFont(24f));
            g2d.drawString("Hello World", 100, 100);
            
            // Add the page to the document and save it
            pdfDoc.addPage(newPage);
            pdfDoc.saveDocument(""/*ici le nom*/);
        }
        catch (Throwable t)
        {
            t.printStackTrace();
        }
		return Printable.PAGE_EXISTS;
	}
	
/* *************************************************************************************************************** */



/* *************************************************************************************************************** */
//Action listener
/* *************************************************************************************************************** */	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GenererPDF tablePrinter = new GenererPDF(initData(), null, true, "a131", "201812");
        PrinterJob printerJob = PDFPrinterJob.getPrinterJob();
        printerJob.setPrintable(tablePrinter);
        
        try
        {
            printerJob.print();
        }
        catch (PrinterException pe)
        {
            pe.printStackTrace();
        }
	}

}
