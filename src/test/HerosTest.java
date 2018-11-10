package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Item.Item;
import model.Item.Piege;
import model.Item.Tresor;
import model.personnages.Heros;

class HerosTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void Right() {
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
	void setTailleInventaireTest() {
		Heros h = new Heros(1, 1, "Manger");
		for (int i = 0; i < 10; i++) {
			h.ajouterAInventaire(new Piege(1,1));
		}
		h.setTailleInventaire(2);
		fail("10 objets ne doit pas pouvoir reduire linventaire a une taille inferieure");
		
	}
	
	@Test
	void setTailleInventairNegatifeTest() {
		Heros h = new Heros(1, 1, "Manger");
		h.setTailleInventaire(-2);
		fail("10 objets ne doit pas pouvoir reduire linventaire a une taille inferieure");
		
	}
	

}
