package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Observable;
import java.util.Observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bank.Konto;

class einUndAuszahlen {
	
	private static Konto konto;
	
	@BeforeEach
	void setUp() {
	Observer observer = new Observer() {
		@Override
		public void update(Observable o, Object arg) {
		}
	
	};
	konto = new Konto(null, 20d, observer, observer);
	}
	
	

	@Test
	void einzahlenTest() {
		konto.einzahlen(30d);
		assertEquals(50, konto.getKontostand());
	}
	
	@Test
	void auszahlenTest() {
		konto.auszahlen(10d);
		assertEquals(10, konto.getKontostand());
	}
	

}
