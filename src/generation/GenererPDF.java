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

import com.qoppa.pdfWriter.PDFPrinterJob;

public class GenererPDF implements ActionListener, Printable{
	
	/* ATTRIBUTS PRIVEES */
	private panel.Vue vue;
		//Page
	private int m_CurrentPage;
	private int m_CurrentPageStartRow;
	private int m_CurrentPageEndRow;
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
//		String nom = ModeleGeneration.getNom(id);
//		String prenom = ModeleGeneration.getPrenom(id);
		// Initialize data
//		for (int row = 0; row < 100; ++row){
//			String test = new String();
//			test ="test";
//			Vector rowData = new Vector ();
//			for (int col = 0; col < 5; ++col){	
//				rowData.addElement (test);
//				rowData.add("test");
//				rowData.addElement("Test 2");
//			}
//			data.addElement (rowData);
//		}
			
//		Vector rowData1 = new Vector();
//		rowData1.add("Fiche de frais de " + nom + "  " + prenom);
//		data.addElement(rowData1);
		
//		rowData2.add("");
//		rowData1.add("Forfait Etape");
//		rowData1.add("Nombre Kilometre");
//		rowData1.add("Nuitée Hotel");
//		rowData1.add("Reâs Restaurant");
//		rowData1.add("Total");
//		data.addElement(rowData1);
		
/*		Vector rowData3 = new Vector();
		rowData3.add("");
		rowData3.add(ModeleGeneration.getNbForfaisEtape(mois, id));
		rowData3.add(ModeleGeneration.getNbKilometre(mois, id));
		rowData3.add(ModeleGeneration.getNbNuite(mois, id));
		rowData3.add(ModeleGeneration.getNbRepas(mois, id));
		data.addElement(rowData3);
		
		Vector rowData4 = new Vector();
		rowData4.add("Montant");
		rowData4.add(ModeleGeneration.getMontantEtape(mois, id));
		rowData4.add(ModeleGeneration.getTotalMontantKm(mois, id));
		rowData4.add(ModeleGeneration.getMontantNuite(mois, id));
		rowData4.add(ModeleGeneration.getMontantRepas(mois, id));
		rowData4.add(ModeleGeneration.getMontantTotalHF(mois, id));*/
		
		return data;
	}
	
	public int print(Graphics g, PageFormat pf, int pageIndex){
	
		
		return Printable.PAGE_EXISTS;
	}
	
/* *************************************************************************************************************** */



/* *************************************************************************************************************** */
//Action listener
/* *************************************************************************************************************** */	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GenererPDF tablePrinter = new GenererPDF(initData(), null, true, idParam, moisParam);
        
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
