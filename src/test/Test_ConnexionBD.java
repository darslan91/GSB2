package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import modele.consulter.ModeleConsulter;

public class Test_ConnexionBD {

	@Test
	public void test() {
		boolean result = ModeleConsulter.connexionBD();
		Assert.assertTrue("Connexion Echoué", result);
		
	}

}
