/*package test;
import static org.junit.Assert.*;

import model.Item.Piege;
import model.personnages.Heros;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class HerosTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Right() {
		Heros h = new Heros(1, 1, "Manger");
		h.goHaut();
		
		assertEquals(h.getDirectionActu(), 6);
		
		h.setTailleInventaire(0);
		
		h.setTailleInventaire(10);
		
		for (int i = 0; i < 5; i++) {
			h.ajouterAInventaire(new Piege(1,1));
		}
		
		assertEquals(10, h.getTailleInventaire());
		
		
		h.setTailleInventaire(6);
		assertEquals(6, h.getTailleInventaire());
		
		// set taille inferieure au nombre dobjet dans linventaire
		h.setTailleInventaire(2);
		assertEquals(10, h.getTailleInventaire());
		
		// set taille inventaire avec un negatif

		h.setTailleInventaire(-2);
		assertEquals(6, h.getTailleInventaire());
	}


	
}*/