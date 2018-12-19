package test;

import static org.junit.Assert.*;

import org.junit.Test;

import generation.ModeleGeneration;

public class Test_GetElementHorsForfait {

	@Test
	public void test() {
		//Element pour le test
		String mois = "201810";
		String id = "a131";
		Object rs = null;
		
		rs = ModeleGeneration.getElementHorsForfait(mois, id);
		
		assertNotNull("Résultat envoyé est null", rs);
		
	}

}
