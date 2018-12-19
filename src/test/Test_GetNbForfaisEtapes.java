package test;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import modele.validation.ModeleValider;

public class Test_GetNbForfaisEtapes {

	@Test
	public void test() {
		//Element neccessaire pour le test
		String mois = "201810";
		String id = "a131";
		//Element initialiser dans le modele
		int nb= 0;
		String forfaitEtapes = "ETP";
		
		nb = ModeleValider.getNbForfaisEtape(mois, id);
		
		assertEquals("Le nombre de forfait étape n'est pas bon", ModeleValider.getNbForfaisEtape(mois, id), 20);
		
	}

}
