package test;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import modele.consulter.ModeleConsulter;
import modele.validation.ModeleRembourser;

public class Test_GetId {

	@Test
	public void test() {
		String nom = "Villechalane";
		String id= "";
		
		id = ModeleRembourser.getId(nom);
		assertEquals("Pas le bon ID", ModeleRembourser.getId(nom), "a131");
	}

}
