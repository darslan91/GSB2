package generation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.qoppa.pdfWriter.PDFPrinterJob;

public class GenererPDF implements ActionListener, Printable{
	
	/* ATTRIBUTS PRIVEES */
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
	
	public static Vector initData(String id, String mois){
		Vector data = new Vector ();
		Vector rowData = new Vector ();
		
		rowData.add("Forfait Etape");
		rowData.add("Frais Kilometrique");
		rowData.add("Nuitée Hotel");
		rowData.add("Repas Restaurant");
		data.addElement(rowData);
		
		Vector rowData2 = new Vector ();
		String nbForfaitEtp = ModeleGeneration.getNbForfaisEtape(mois, id);
		String nbKiloM = ModeleGeneration.getNbKilometre(mois, id);
		String nbNuite = ModeleGeneration.getNbNuite(mois, id);
		String nbRepas = ModeleGeneration.getNbRepas(mois, id);
		
		System.out.println("Forfait etp "+nbForfaitEtp );
		System.out.println("Frais klm " +nbKiloM);
		System.out.println("Nuité hotel " +nbNuite);
		System.out.println("Repas " +nbRepas);
		
		rowData2.addElement(nbForfaitEtp);
		rowData2.addElement(nbKiloM);
		rowData2.addElement(nbNuite);
		rowData2.addElement(nbRepas);
		data.addElement(rowData2);
		
		Vector rowData3 = new Vector ();
		rowData3.add("Hors Forfait");
		data.addElement(rowData3);
		
		Vector rowData4 = new Vector();
		rowData4.add("Date");
		rowData4.add("Libelle");
		rowData4.add("Justification");
		rowData4.add("Montant");
		data.addElement(rowData4);
		
		try{
			ResultSet rs = ModeleGeneration.getElementHorsForfait(mois, id);
			int i = 0;
			while(rs.next()){
				String justif = "oui";
				if(rs.getString(4).equals("")){
					justif = "non";
				}
				
				Vector nom = new Vector();
				nom.add(rs.getString(1));
				nom.add(rs.getString(2));
				nom.add(justif);
				nom.add(rs.getString(3));
				data.addElement(nom);
			}
			rs.close();
			ModeleGeneration.deconnexionBD();
		}
		catch(SQLException e){
			System.out.println("Erreur : \n" + e);
		}
		
		return data;
	}
	
	public int print (Graphics g, PageFormat pf, int pageIndex)
	{
		int y = 20;
		int lineHeight = g.getFontMetrics().getHeight() + 7;
		g.drawString("Fiche de remboursement du mois de " + this.mois, 175, y);
		g.drawString(ModeleGeneration.getNom(this.id), 500, y = y + 17);
		g.drawString(ModeleGeneration.getPrenom(this.id), 500, y = y + 17);
		g.drawString(ModeleGeneration.getAdresse(this.id), 475, y = y + 17);
		g.drawString("Elément forfaitisé :", 20, y = y + 30);
		// Reset current pos
		int currentRow = 0;
		if (pageIndex == 0)
		{
			// Need to do this in case the instance of this class
			// gets used multiple times to print a string
			m_CurrentPage = 0;
			m_CurrentPageStartRow = 0;
		}
		// Need to do this because Java PrinterJob can call this
		// method multiple times for the same page;
		else if (m_CurrentPage == pageIndex)
		{
			currentRow = m_CurrentPageStartRow;
		}
		else
		{
			currentRow = m_CurrentPageEndRow + 1;
			m_CurrentPageStartRow = currentRow;
		}
		
		// If we're out of lines, tell the PrinterJob we're done
		if (currentRow >= m_Data.size())
		{
			return Printable.NO_SUCH_PAGE;
		}

		// Loop through lines until we fill the page
		int currentY = (int)(pf.getImageableY() + lineHeight);
		while (currentRow < m_Data.size() && 
				currentY + lineHeight < pf.getImageableY() + pf.getImageableHeight())
		{
			// Draw the next line
			int currentX = (int)pf.getImageableX() + 20;
			Vector nextRow = (Vector)m_Data.elementAt (currentRow);
			y = y + 20;
			for (int col = 0; col < nextRow.size(); ++col)
			{
				String cellString = (String)nextRow.elementAt (col);
				g.drawString (cellString, currentX, y + (lineHeight / 2));
				
				int colWidth = 135;
				if (m_ColumnWidths != null && m_ColumnWidths.length > col)
				{
					colWidth = 150;
				}
				
				// Draw grid if needed
				if (m_DrawGrid)
				{
					g.drawRect (currentX - 5, y - (lineHeight / 2) + 4, colWidth, lineHeight);
				}
				
				// Advance x
				currentX += colWidth;
			}
			
			// Advance to the next line
			++currentRow;
			currentY += lineHeight;
		}
		
		// Save the ned line and current page
		// Again, we have to do this because of multiple calls for the same page.
		m_CurrentPageEndRow = currentRow;
		m_CurrentPage = pageIndex;
		
		return Printable.PAGE_EXISTS;
	}
	
/* *************************************************************************************************************** */



/* *************************************************************************************************************** */
//Action listener
/* *************************************************************************************************************** */	
	
	//@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		GenererPDF tablePrinter = new GenererPDF(initData(this.id, this.mois), null, true, this.id, this.mois);
        
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
