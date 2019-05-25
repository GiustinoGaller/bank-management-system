package junittest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bank.Bank;
import bank.Konto;
import kunde.Adresse;
import kunde.Kunde;
import kunde.Kundenart;
import kunde.Privatkunde;
import kunde.Strings;

class gesamtSumme {

	private static Bank bank;
	private static Kunde kunde1;
	private static Kunde kunde2;
	private static Kunde kunde3;

	@BeforeEach
	void setUp() {

		ArrayList<Konto> kunde1konten = new ArrayList<>();
		ArrayList<Konto> kunde2konten = new ArrayList<>();
		ArrayList<Konto> kunde3konten = new ArrayList<>();
		Observer observer = new Observer() {
			@Override
			public void update(Observable o, Object arg) {
			}
		};

		for (int i = 0; i < 10; i++) {
			kunde1konten.add(new Konto("", 10, observer, observer));
			kunde2konten.add(new Konto("", 10, observer, observer));
			kunde3konten.add(new Konto("", 10, observer, observer));

		}
		bank = new Bank(null, null, null, null);
		kunde1 = new Privatkunde(Kundenart.Privatkunde, 1, Strings.Herr, "Alfons", "Frise", "23/11/2000",
				new Adresse("Sebelstraße", "14", "14512", "Berlin"), "0176824242", "g@g.de", kunde1konten);
		kunde2 = new Privatkunde(Kundenart.Privatkunde, 2, Strings.Frau, "Berter", "Langbein", "02/11/1992",
				new Adresse("Reinbeinstraße", "23", "52353", "Bremen"), "0174423452", "f.siegberd@peter.de",
				kunde2konten);
		kunde3 = new Privatkunde(Kundenart.Privatkunde, 3, Strings.Frau, "Serter", "Tangbein", "02/11/1992",
				new Adresse("Feinbeinstraße", "21", "22353", "Braunbein"), "0144423452", "g.siegberd@peter.de",
				kunde3konten);
		
		bank.addKunden(kunde1);
		bank.addKunden(kunde2);
		bank.addKunden(kunde3);
	}

	@Test
	void gesamtSummeTest() throws Exception {

		assertEquals(300, bank.berechneBankVermoegen());

	};

}
