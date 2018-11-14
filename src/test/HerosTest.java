package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.Item.Piege;
import model.personnages.Heros;

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
		
		assertEquals(h.getTailleInventaire(), 10);
		
		h.setTailleInventaire(6);
		assertEquals(h.getTailleInventaire(), 6);
		
		
	}
	
	@Test
	public void setTailleInventaireTest() {
		Heros h = new Heros(1, 1, "Manger");
		for (int i = 0; i < 10; i++) {
			h.ajouterAInventaire(new Piege(1,1));
		}
		h.setTailleInventaire(2);
		fail("10 objets ne doit pas pouvoir reduire linventaire a une taille inferieure");
		
	}
	
	@Test
	public void setTailleInventairNegatifeTest() {
		Heros h = new Heros(1, 1, "Manger");
		h.setTailleInventaire(-2);
		fail("10 objets ne doit pas pouvoir reduire linventaire a une taille inferieure");
		
	}

}
