package start;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

import bank.Bank;
import bank.Konto;
import controller.EingabeZuweisung;
import controller.Sortierer;
import controller.SortiererString;
import gui.GuiEinUndAuszahlungen;
import kunde.Adresse;
import kunde.Firmenkunde;
import kunde.Kunde;
import kunde.Kundenart;
import kunde.Privatkunde;
import kunde.Strings;
import logging.PrintInConsole;
import logging.PrintInLogger;
import logging.SingletonLogger;
import threat.MonitoringAktivierer;


public class Menu {
	/**
	 * Drei Objekte werden erstellt.
	 */
	private EingabeZuweisung eingabe = new EingabeZuweisung();
	private Scanner scanner = new Scanner(System.in);
	private Bank bank = new Bank("Postbank", "10010010", "PBNKDEFF", null);
	private String filename = "data.txt";
	private String backSlash = File.separator;
	private Path path = Paths.get("");
	private MonitoringAktivierer monitoringAktivierer;
	
	public Menu(String str) {
		
	}
		
	
	/**
	 * Ueberpruef, ob der angegebene Pfad existiert
	 * @return wiederholen
	 */
	private boolean pfadAuswahl() {
		boolean wiederholen = true;
		while (wiederholen) {
		if(new File(path.toAbsolutePath().toString()).exists()) {
			this.loadBankData();
			wiederholen = false;
//			bank.setKunden(bank.getKunden());
		}else {
			wiederholen = true;
		}
		}
		return wiederholen;
	}
	
	public Menu() {
		/**
		 * Wenn das Programm gestartet wird, so wird dies dem Benutzer mitgeteilt.
		 * Die Allgemeinen Informationen ueber die Bank können ganz oben nach dem Start im Programm eingesehen werden.
		 * Im Menü kann man dann von 1 - 7 den gewünschten Aufruf tätigen.
		 */
		System.out.println("Programm Start" + "\n");
		dieBankInfo();
		boolean wiederholen = true;
		
		
		
		while (wiederholen) {//mit jedem Schleifendurchlauf jeweils ein neues Element des String Arrays befüllt wird
			System.out.println("Postbank Menü [\n 1 = Privatkunden Anlegen (Stammdaten) \n 2 = Geschaeftskunden anlegen (Stammdaten) \n 3 = Privatkunden anlegen \n 4 = Geschaeftskunden anlegen \n 5 = Konten anlegen und Kundennummer zuweisen \n 6 = Kunde mit Konten anzeigen (Auswahl durch Kundennummer) \n 7 = Kunde und Konten anzeigen (Auswahl durch Name) \n 8 = Kunde und Konten anzeigen (Auswahl durch IBAN) \n 9 = Alle Kunden sortiert nach aufsteigender Kundennummer anzeigen \n 10 = Alle Kunden sortiert nach aufsteigendem Namen anzeigen \n 11 = Alle Konten unsortiert anzeigen \n 12 = Bankdaten speichern \n 13 = Bankdaten laden \n 14 = Kunden nach Namen sortiert als CSV - Datei exportieren \n 15 = Geld einzahlen \n 16 = Geld auszahlen \n 17 = Log- Strategie waehlen \n 18 = GUI fuer Ein- und Auszahlungen oeffnen \n 19 = Monitoring aktivieren \n 20 = Monitoring deaktivieren \n 21 = Programm beenden \\n ]  \n");
			
			int menuAuswahl = scanner.nextInt();
			
			if (menuAuswahl == 1) {
				privatKundenAnlegenStammdaten();
			}else if (menuAuswahl == 2){
			geschaeftskundeAnlegenStammdaten();
			}else if (menuAuswahl == 3) {
				privatKundeAnlegen();
			}else if (menuAuswahl == 4) {
				geschaeftskundeAnlegen();
			}else if (menuAuswahl == 5) {
				kontoAnlegenUndKundennummerZuweisen();
			}else if (menuAuswahl == 6) {
				kundeMitKontenAnzeigenKundennummer();
			}else if (menuAuswahl == 7) {
				kundeMitKontenAnzeigenName();
			}else if (menuAuswahl == 8) {
				kundeMitKontenAnzeigenIBAN();
			}else if (menuAuswahl == 9) {
				kundeSortAufsteigendKundennummer();
			}else if (menuAuswahl == 10) {
				kundeSortAufsteigendName();
			}else if (menuAuswahl == 11) {
				alleKontenUnsortiertAnzeigen();
			}else if (menuAuswahl == 12) {
				saveBankData();
			}else if (menuAuswahl == 13) {
				loadBankData();
			}else if (menuAuswahl == 14) {
				export();
			}else if (menuAuswahl == 15) {
				geldEinzahlen();
			}else if (menuAuswahl == 16) {
				geldAuszahlen();
			}else if (menuAuswahl == 17) {
				logStrategieWaehlen();
			}else if (menuAuswahl == 18) {
				guiFuerEinUndAuszahlungenOeffnen();
			}else if (menuAuswahl == 19) {
				monitoringAktivieren();
			}else if (menuAuswahl == 20) {
				monitoringDeaktivieren();
			
			}else if (menuAuswahl == 21) {
				wiederholen = false;
			
			}else {
				System.out.println("Auswahl nicht vorhanden!");
			}
		System.out.println("\n Programm Ende \n");
		}
	
	}
	
		
		
		private void monitoringDeaktivieren() {
			monitoringAktivierer.interrupt();
			System.out.println("Der Thread wurde beendet!");

		
	}

		private void monitoringAktivieren() {
		monitoringAktivierer = new MonitoringAktivierer(bank);
		
	}

		private void guiFuerEinUndAuszahlungenOeffnen() {
		new GuiEinUndAuszahlungen(bank);
		
		
//		GuiEinUndAuszahlungen fenster = new GuiEinUndAuszahlungen(bank);
		}
	
		
		
		/**
		 * Hier können wir mit dem Singleton, sprich dem Objekt, welches sich erzeugt
		 */

		private void logStrategieWaehlen() {
		System.out.println("Bitte waehlen Sie die gewünschte Ausgabe: 1 = Ausgabe in Log- Datei oder 2 = Ausgabe in Console ");
		boolean wiederholen = true;
		int menuAuswahl = scanner.nextInt();
		while (wiederholen) {
			
		if (menuAuswahl == 1) {
			SingletonLogger.getInstance().setLoggerPrint(new PrintInLogger());
			System.out.println("Es wird nun in die log.txt gespeichert!");
			wiederholen = false ;
		}else if (menuAuswahl == 2) {
			SingletonLogger.getInstance().setLoggerPrint(new PrintInConsole());
			System.out.println("Es wird nun in der Console ausgegeben!");
			wiederholen = false ;
		}
		}
		
	}

		private synchronized HashMap<String, Konto> geldAuszahlen() {
			HashMap <String, Konto> kontenKunden = bank.getKontenKunden();//HashMap kontenKunden
			System.out.println(kontenKunden + "HashMap kontenKunden");
			String currentKey = null;
			System.out.println("Geld einzahlen!");
			System.out.println("\n Bitte geben Sie eine IBAN ein: > ");
			String cs2 = scanner.next();
			System.out.println("Bitte geben Sie den gewünschten Betrag ein, welchen Sie ausbezahlt haben möchten: > ");
			double cs3 = scanner.nextDouble();
			double newBalance = 0;
			for (Entry<String, Konto> alleDaten : kontenKunden.entrySet()) {
//				System.out.println("Schlüssel: " + alleDaten.getKey());
//				System.out.println("Wert: " + alleDaten.getValue());
						if (alleDaten.getKey().equals(cs2)) {
							currentKey = alleDaten.getKey();
							System.out.println("Das ist der Key: " + currentKey);
							Konto currentValue = alleDaten.getValue();
							System.out.println("Alter Kontostand: " + currentValue.getKontostand());
							System.out.println("Das ist der Wert: " + currentValue);
							newBalance = currentValue.getKontostand();
							newBalance -= cs3;
							System.out.println("Der neue Kontostand lautet: " + newBalance);
							bank.getKontenKunden().get(currentKey).setKontostand(newBalance);
//							bank.getKontenKunden().put(currentKey, new Konto(currentKey, newBalance, bank.getVorsdtand(), bank.getBankangestellter() ));
							SingletonLogger.getInstance().log(currentKey + currentValue);
							System.out.println("Der Betrag wurde dem Konto gutgeschrieben!");
						
						}
			}
			return kontenKunden;
		}

		private synchronized HashMap<String, Konto> geldEinzahlen() {
			HashMap <String, Konto> kontenKunden = bank.getKontenKunden();//HashMap kontenKunden
			String currentKey = null;
			System.out.println("Geld einzahlen!");
			System.out.println("\n Bitte geben Sie eine IBAN ein: > ");
			String cs2 = scanner.next();
			System.out.println("Bitte geben Sie den gewünschten Betrag ein, welchen Sie einbezahlen möchten: > ");
			double cs3 = scanner.nextDouble();
			double newBalance = 0;
			for (Entry<String, Konto> alleDaten : kontenKunden.entrySet()) {
//				System.out.println("Schlüssel: " + alleDaten.getKey());
//				System.out.println("Wert: " + alleDaten.getValue());
						if (alleDaten.getKey().equals(cs2)) {
							currentKey = alleDaten.getKey();
							System.out.println("Das ist der Key: " + currentKey);
							Konto currentValue = alleDaten.getValue();
							System.out.println("Alter Kontostand: " + currentValue.getKontostand());
							System.out.println("Das ist der Wert: " + currentValue);
							newBalance = currentValue.getKontostand();
							newBalance += cs3;
							System.out.println("Der neue Kontostand lautet: " + newBalance);
							bank.getKontenKunden().get(currentKey).setKontostand(newBalance);
							//bank.getKontenKunden().put(currentKey, new Konto(currentKey, newBalance, bank.getVorsdtand(), bank.getBankangestellter() ));
							SingletonLogger.getInstance().log(currentKey + currentValue);
							System.out.println("Der Betrag wurde dem Konto gutgeschrieben!");
						
						}
			}
			return kontenKunden;
		}
			
							
		
	

		/**
		 * Gibt die Informationen ueber die Bank aus
		 */
		private void dieBankInfo() {
			Adresse adresseBank = new Adresse("Taubenstraße", "7-9", "10117", "Berlin");
			Bank postbank = new Bank("Postbank", "10010010", "PBNKDEFF", new Adresse[]{adresseBank});
			postbank.informationenBank();
			
		}
		/**
		 * Die Methode privatKundeAnlegen() erhaelt alle geforderten Rueckgabewerte aus der Klasse EingabeZuweisung.java
		 * Die Rückgabewerte der einzelenen Methoden in der Klasse EingabeZuweisung.java werden in das unten erzeugte
		 * Objekt privatkunde gespeichert. Dies ist durch die Vorgaben im Konstruktor der Oberklasse Kunde und der Subklasse
		 * Privatkunde möglich. Die Klasse Kunde ist hierbei abstract, sowie die Methode getName().
		 */
		
		public void privatKundenAnlegenStammdaten() {
			Privatkunde privatkunde1 = new Privatkunde(Kundenart.Privatkunde, erzeugeKundennummer(), Strings.Herr, "Alfons", "Frise", "23/11/2000", new Adresse("Sebelstraße", "14", "14512", "Berlin"), "0176824242", "g@g.de", kontoAnlegenUndKundennummerZuweisen());
			Privatkunde privatkunde2 = new Privatkunde(Kundenart.Privatkunde, erzeugeKundennummer(), Strings.Frau, "Berter", "Langbein", "02/11/1992", new Adresse("Reinbeinstraße", "23", "52353", "Bremen"), "0174423452", "f.siegberd@peter.de", kontoAnlegenUndKundennummerZuweisen());
			Privatkunde privatkunde3 = new Privatkunde(Kundenart.Privatkunde, erzeugeKundennummer(), Strings.Frau, "Serter", "Tangbein", "02/11/1992", new Adresse("Feinbeinstraße", "21", "22353", "Braunbein"), "0144423452", "g.siegberd@peter.de", kontoAnlegenUndKundennummerZuweisen());
		
			if (privatkunde1.getKonten().size() <= 10 || privatkunde2.getKonten().size() <= 10 || privatkunde3.getKonten().size() <= 10) {
				bank.addKunden(privatkunde1);
				bank.addKunden(privatkunde2);
				bank.addKunden(privatkunde3);
				System.out.println("Privatkunde hat maximal zehn Konten!");
			}else{
				throw new StackOverflowError("Privatkunde hat zu viele Konten!");
			}
		
			
		}
		private void privatKundeAnlegen() {
			
			Strings anrede = eingabe.printAnrede();
			String vorname = eingabe.getVorname();
			String nachname = eingabe.getNachname();
			String geburtsdatum = eingabe.getGeburtsdatum();
			String adresszeile1 = eingabe.getAdresszeile1();
			//String adresszeile2 = eingabe.getAdresszeile2();
			String hausnummer = eingabe.getHausnummer();
			String plz = eingabe.getPlz();
			String ort = eingabe.getOrt();
			String telefonnummer = eingabe.getTelefonnummer();
			String email = eingabe.getEmail();
			Privatkunde privatkunde = new Privatkunde(Kundenart.Privatkunde, erzeugeKundennummer(), anrede, vorname, nachname, geburtsdatum, new Adresse(adresszeile1, hausnummer, plz, ort), telefonnummer, email, kontoAnlegenUndKundennummerZuweisen());
			if (privatkunde.getKonten().size() <= 10) {
				bank.addKunden(privatkunde);
				System.out.println("Privatkunde hat maximal zehn Konten!");
			}throw new IndexOutOfBoundsException("Privatkunde hat zu viele Konten!");
			
		}
		/**
		 * Die Methode geschaeftkundeAnlegen() erhaelt alle geforderten Rueckgabewerte aus der Klasse EingabeZuweisung.java
		 * Die Rückgabewerte der einzelenen Methoden in der Klasse EingabeZuweisung.java werden in das unten erzeugte
		 * Objekt firmenkunde gespeichert. Dies ist durch die Vorgaben im Konstruktor der Oberklasse Kunde und der Subklasse
		 * Firmenkunde möglich. Die Klasse Kunde ist hierbei abstract, sowie die Methode getName().
		 */
		private void geschaeftskundeAnlegenStammdaten() {
			Firmenkunde firmenkunde1 = new Firmenkunde(Kundenart.Geschäftskunde, erzeugeKundennummer(), "Testfirma GmbH", new Adresse("Sebelstraße", "14", "14512", "Berlin"), "0176824242", "g@g.de", kontoAnlegenUndKundennummerZuweisen());
			Firmenkunde firmenkunde2 = new Firmenkunde(Kundenart.Geschäftskunde, erzeugeKundennummer(), "Peterfirma UG", new Adresse("Eberleiterstraße", "21", "14512", "Berlin"), "0176824242", "g@g.de", kontoAnlegenUndKundennummerZuweisen());
			Firmenkunde firmenkunde3 = new Firmenkunde(Kundenart.Geschäftskunde, erzeugeKundennummer(), "Angolffirma KG", new Adresse("Fingerrasselstraße", "28", "14512", "Berlin"), "0176824242", "g@g.de", kontoAnlegenUndKundennummerZuweisen());
			bank.addKunden(firmenkunde1);
			bank.addKunden(firmenkunde2);
			bank.addKunden(firmenkunde3);
			
			if (firmenkunde1.getKonten().size() <= 10 || firmenkunde2.getKonten().size() <= 10 || firmenkunde3.getKonten().size() <= 10) {
				bank.addKunden(firmenkunde1);
				bank.addKunden(firmenkunde2);
				bank.addKunden(firmenkunde3);
				System.out.println("Firmenkunde hat maximal zehn Konten!");
			}else{
				throw new StackOverflowError("Firmenkunde hat zu viele Konten!");
			}
		}
		private void geschaeftskundeAnlegen() {
			
			String firmenname = eingabe.getFirmenname();
			String adresszeile1 = eingabe.getAdresszeile1();
			//String adresszeile2 = eingabe.getAdresszeile2();
			String hausnummer = eingabe.getHausnummer();
			String plz = eingabe.getPlz();
			String ort = eingabe.getOrt();
			String telefonnummer = eingabe.getTelefonnummer();
			String email = eingabe.getEmail();
			
			Firmenkunde firmenkunde = new Firmenkunde(Kundenart.Geschäftskunde, erzeugeKundennummer(), firmenname, new Adresse(adresszeile1, hausnummer, plz, ort), telefonnummer, email, kontoAnlegenUndKundennummerZuweisen());
//			bank.addKunden(firmenkunde);
			
			if (firmenkunde.getKonten().size() <= 10) {
				bank.addKunden(firmenkunde);
				System.out.println("Kunde hat maximal zehn Konten!");
			}throw new IndexOutOfBoundsException("Kunde hat zu viele Konten!");
			

			
			
		}
		/**
		 * Erstellt eine Kundennummer für jeden Kunden und weist diese mit return in den Konstruktor des Kunden zu.
		 * Die geschieht ebenfalls per Zufall mit der Math.random() - Methode. Ob dies wirklich Zufall ist, das kann man wohl sicher
		 * abstreiten. Der Computer kennt die Werte.
		 * @return kundennummer
		 */
		private int erzeugeKundennummer() {
			int kundennummer = (int) (Math.random() * 300);
			return kundennummer;
		}
		/**
		 * Es werden pro Kunden bis zu drei Konten mit der Math.random - Methode generiert. Das gleiche gilt auch für den Betrag, welcher
		 * ebenfalls mit der Math.random() - Methode generiert wird. Die ersten Stellen der IBAN sind immer gleich und aendern sich nur am
		 * Ende mit der Math.random() - Methode.
		 * Dann werden diese in das Array - Objekt konten gespeichert.
		 * @return konten
		 */
		private ArrayList <Konto> kontoAnlegenUndKundennummerZuweisen() {
			try {
			ArrayList <Konto> konten = new ArrayList <> ();//Hier fehlt mir die Begrenzung auf zehn, welche ich ja in eine
			for(int i = 0; i < (Math.random() * 2) +0;i++) {//Erstelle mit Math.random () * 3 maximal drei Konten pro Kunden zufällig
				int betrag = (int) (Math.random() * 300000);//Erstelle maximal bis zu 300.000 € auf ein Konto eines Kunden der Bank zufällig
				konten.add(new Konto("DE41500105170123456" + (int)Math.floor(Math.random() * 1000), betrag, bank.getVorsdtand(), bank.getBankangestellter()));
				String iban = konten.get(konten.size()-1).getIban();//Speicher alle automatisch generierten konten mit IBAN in der Variable IBAN
				System.out.println(iban);//Gib die Konten aus
				//Nimm die HashMap KontenKunden aus der Klasse Bank und stecke dort die IBAN und die Konten rein
				bank.getKontenKunden().put(iban, konten.get(konten.size()-1));
			}
			return konten; 
			/**
			 * @return Gibt die erstellten Inhalte in "konten" zurück. Es werden pro Kunden 0 bis 3 Konten per random erstellt,
			 * aber es können pro Kunden maximal zehn Konten bei der Bank erstellt werden.
			 * Sollte man versuchen (hier die random Methode aus Java) mehr als zehn Konten zu erstellen,
			 * so wird eine Exception geworfen.
			 * @exception Sollte versucht werden mehr als zehn Konten pro Kunden anzulgen, so wird eine Meldung
			 * ausgegeben, das dies nicht möglich ist und das Programm wird nicht angehalten.
			 */
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Es können nicht mehr als zehn Konten pro Kunden angelegt werden!");
			}
			return null;
			/**
			 *@return gibt null zurück.
			 *
			 */
		}
		
		/**
		 * Die Methode kundeMitKontenAnzeigenKundennummer() erhaelt den angelegten Kunden in der for - each Schleife und speichert
		 * diesen in der lokalen Variablen Kunde kunde ab. Mit der if - Abfrage wird kontrolliert, ob der kunde einen Kunden enthaelt.
		 * Ist dieser null, so wird keine NullpointerException geworfen, sondern null zurueckgegeben. Das gleiche Verfahren wiederholt
		 * wiederholt sich auch in der naechsten for - each Schleife. Hier verwenden wir die lokal angelegte Variable aus der
		 * vorherigen for - each Schleife und packen diese in die zweite for - each Schleife. Dann setzen wir der lokalen Variablen
		 * kunde, die Methode getKonten an. Jetzt wird jeder Kunde mit seinem Konto in die lokale Variable konto gepackt. Am Ende
		 * geben wir die Kunden mit Ihren Konten und Ihrer Kundennummer aus.
		 * 
		 * Danach können wir gucken, ob die Kundennummer vorhanden ist (Eingabe in der Console), welche wir uns zuvor einmal auslesen müssen, da diese per
		 * random generiert wird. Sonst wissen wir die Kundennummer nicht. Mit der Methode contains wird dann geguckt, ob Bestandteile der
		 * generierten Kundennummer vorhanden sind.
		 */
		private void kundeMitKontenAnzeigenKundennummer() {
			for (Kunde ku : bank.getKunden()) {
				if(ku == null) return;
				for (Konto konto : ku.getKonten()) {
					if (konto == null) return;
					System.out.println("Kunde: " + ku + " Kundennummer: " + ku.getKundennummer() + " Konto: " + konto);
					
			System.out.println("Bitte geben Sie eine Kundennummer ein: > ");
			
			int cs1 = scanner.nextInt();
				if (ku.getKundennummer()==(cs1)) {
					System.out.println("Kundennummer: " + ku.getKundennummer() + " Kunde: " + ku + " Konto: " + konto);
				}else {
					System.out.println("Kundennummer ist nicht vorhanden!");
				}
			}
				}
			}
		
				
		/**
		 * Die Methode kundeMitKontenAnzeigenName() erhaelt den angelegten Kunden in der for - each Schleife und speichert
		 * diesen in der lokalen Variablen Kunde kunde ab. Mit der if - Abfrage wird kontrolliert, ob der kunde einen Kunden enthaelt.
		 * Ist dieser null, so wird keine NullpointerException geworfen, sondern null zurueckgegeben. Das gleiche Verfahren wiederholt
		 * wiederholt sich auch in der naechsten for - each Schleife. Hier verwenden wir die lokal angelegte Variable aus der
		 * vorherigen for - each Schleife und packen diese in die zweite for - each Schleife. Dann setzen wir der lokalen Variablen
		 * kunde, die Methode getKonten an. Jetzt wird jeder Kunde mit seinem Konto in die lokale Variable konto gepackt. Am Ende
		 * geben wir die Kunden mit Ihren Konten und Ihrer Kundennummer aus.
		 * 
		 * Danach können wir gucken, ob die Name vorhanden ist (Eingabe in der Console), welche wir zuvor einmal auslesen können,
		 * falls wir den Namen vergessen haben sollten.
		 * Mit der Methode contains wird dann geguckt, ob Bestandteile der
		 * Namen vorhanden sind.
		 */
		private void kundeMitKontenAnzeigenName() {
			for (Kunde ku : bank.getKunden()) {
				if(ku == null) return;
				for (Konto konto : ku.getKonten()) {
					if (konto == null) return;
					System.out.println("Kunde: " + ku + " Name: " + ku.getName() + " Konto: " + konto);
					System.out.println("\n Bitte geben Sie eine Namen ein: > ");
					String cs2 = scanner.next();
						if (ku.getName().contains(cs2)) {
							System.out.println("Name: " + ku.getName() + " Kunde: " + ku + " Konto: " + konto);
						}else {
							System.out.println("Name ist nicht vorhanden!");
						}
				}
				
			}
		}
		/**
		 * Die Methode kundeMitKontenAnzeigenIBAN () erhaelt den angelegten Kunden in der for - each Schleife und speichert
		 * diesen in der lokalen Variablen Kunde kunde ab. Mit der if - Abfrage wird kontrolliert, ob der kunde einen Kunden enthaelt.
		 * Ist dieser null, so wird keine NullpointerException geworfen, sondern null zurueckgegeben. Das gleiche Verfahren wiederholt
		 * wiederholt sich auch in der naechsten for - each Schleife. Hier verwenden wir die lokal angelegte Variable aus der
		 * vorherigen for - each Schleife und packen diese in die zweite for - each Schleife. Dann setzen wir der lokalen Variablen
		 * kunde, die Methode getKonten an. Jetzt wird jeder Kunde mit seinem Konto in die lokale Variable konto gepackt. Am Ende
		 * geben wir die Kunden mit Ihren Konten und Ihrer Kundennummer aus.
		 * 
		 * Danach können wir gucken, ob die IBAN vorhanden ist (Eingabe in der Console), welche wir uns zuvor einmal auslesen müssen, da diese per
		 * random generiert wird. Sonst wissen wir die IBAN nicht. Mit der Methode contains wird dann geguckt, ob Bestandteile der
		 * generierten IBAN vorhanden sind.
		 */
		private void kundeMitKontenAnzeigenIBAN() {
			for (Kunde ku : bank.getKunden()) {
				if(ku == null) return;
				for (Konto konto : ku.getKonten()) {
					if (konto == null) return; 
					System.out.println("Kunde: " + ku + " IABN: " + konto.getIban() + " Konto: " + konto);
					System.out.println("\n Bitte geben Sie eine IBAN ein: > ");
					String cs2 = scanner.next();
						if (konto.getIban().contains(cs2)) {
							System.out.println("IBAN: " + konto.getIban() + " Kunde: " + ku + " Konto: " + konto);
						}else {
							System.out.println("IBAN ist nicht vorhanden!");
						}
				}
			}
		}
		


			private void kundeSortAufsteigendKundennummer() {
			ArrayList<Kunde> kunden = bank.getKunden();//Speicher mir den Kunden in der ArrayList kunden
			kunden.sort(new Sortierer());//ArrayList sortiere, und erstelle ein Objekt von der Klasse Sortierer
			
			for (Kunde kundenDaten : kunden) {//Gehe die ArrayList durch und speicher die Kunden in der kundenDatenVariable ab
				System.out.println(kundenDaten.getName() + kundenDaten.getKundennummer());//Gib kundenDaten aus
			}
			
			
			
			
		}
			private void kundeSortAufsteigendName() {
				ArrayList <Kunde> kunden = bank.getKunden();//Speicher mir den Kunden in der ArrayList ab
				kunden.sort(new SortiererString());//Sortiere die Kunden aufsteigend nach ihren Namen und erstelle ein neues Objekt der Klasse SortiererString
				
				for (Kunde kundenDaten : kunden) {//Befuelle die kundenDaten Variable mit den Daten aus der ArrayList kunden
					System.out.println(kundenDaten);//Gebe die kundenDaten aus
				}
				
				
			}
			private void alleKontenUnsortiertAnzeigen() {
				
				for (Entry<String, Konto> alleDaten : bank.getKontenKunden().entrySet()) {//Saemtliche Konten aus der HashMap kundenKonten in ein Map.Entry packen
					System.out.println("Konten: " + alleDaten);//Gebe die Daten aus der HashMap aus, welche wir zuvor in das Entry alleDaten gepackt haben
					System.out.println(alleDaten.getKey() + " key");
					System.out.println(alleDaten.getValue() + " value");
					
				}
				
			}
			
			/**Speichert die Daten aus der Bank
			 * 
			 */
			public void saveBankData() {
				System.out.println("Bitte geben Sie den Speicherort an: " );
				
				
				String speicherPfad = scanner.next();
				System.out.println(speicherPfad + backSlash + filename);
//				String errPathNotExist = "Pfad existiert nicht!";
				String string = "";
				
				File file = new File(string);
				file.exists();
				
				try {
					FileOutputStream fos = new FileOutputStream( speicherPfad + backSlash + filename);
					/**if (speicherPfad == null) {
						System.out.println(errPathNotExist);
					}*/
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(bank);
					
					oos.close();
					fos.close();
					
					System.out.println("Serialized data is saved!");
				}catch (IOException e) {
					System.err.println("Die Datei kann nicht beschrieben werden oder sie existiert nicht!");
//					System.out.println("Die Datei kann nicht beschrieben werden oder sie existiert nicht!");
					e.printStackTrace();
				}
			}
			
			
			/**
			 * Lädt die gespeicherten Daten der Bank aus der data.txt
			 */
			public void loadBankData() {
				System.out.println("Bitte geben Sie den Speicherort an: ");
				String speicherpfad = scanner.next();
				
				File datei = new File(speicherpfad + backSlash + filename);//Wird in Konstruktor aufgerufen
//				System.out.println(speicherpfad + backSlash + filename);
				if (!datei.exists()) {//Prueft, ob die Datei vorhanden ist
					System.out.println("Die Datei existiert nicht, oder der Pfad stimmt nicht! \n");
					pfadAuswahl();
				}else {
				try {
					FileInputStream fis = new FileInputStream(datei);
					ObjectInputStream ois = new ObjectInputStream(fis);
					bank = (Bank) ois.readObject();
		
					ois.close();
					fis.close();
					System.out.println("Data from ArrayList & HashMap are loaded!");
					System.out.println("Daten aus Datei wurden geladen aus: " + path.toAbsolutePath().toString() + backSlash + filename +"\n");
				}catch (IOException | ClassNotFoundException e) {
					System.out.println("Es konnten keine Daten geladen werden!");
					e.printStackTrace();
				}
				}
			}
			
			/**
			 * Sortiert die Namen der Bankkunden und exportiert diese in eine csv - Datei und gibt die Anzahl der Objekte aus, sowie den Speicherpfad
			 */
			public void export() {
				
				ArrayList <Kunde> kunden = bank.getKunden();//Speicher mir den Kunden in der ArrayList ab
				HashMap <String, Konto> kontenKunden = bank.getKontenKunden();
				int counter = 0;//Zaehler
				
				try {
					kunden.sort(new SortiererString());//Sortiere die Kunden aufsteigend nach ihren Namen und erstelle ein neues Objekt der Klasse SortiererString
					FileWriter writer = new FileWriter("export.csv");
					writer.write("Kundennummer; Kundentyp; Name; Kontostand \n");
					for (Kunde daten : kunden) {
//						daten.sort(new SortiererString());
						writer.write(daten.getKundennummer() + ";" + daten.getKundenart() + ";" + daten.getName() + ";" + daten.getKonten() + "\n");
					}
					writer.close();
					
					
			       
			         
//			        Die Elemente der ArrayList werden dem Konstruktor der HashSet uebergeben
			        Set<Kunde> anzahlKundenInBank = new HashSet<>(kunden);
//			        System.out.println("Unique: " + anzahlKundenInBank.toString());
			         
			        for(Kunde setElement : anzahlKundenInBank) {
//			            Uebergibt die Elemente aus dem Set an das Objekt setElement
			           
//			        	Gibt die Anzahl der Elemente zurueck, welche zu einem Suchobjekt -equals() gleich sind Collections.frequency (Collection<?>, Object)
			        	int elementCtr = Collections.frequency(kunden, setElement);
			            if(elementCtr > 1);
//			            System.out.println(setElement + ": " + elementCtr);
			            counter++;
			        }
			       
			         
			        
			   System.out.println("Es wurden " + counter + " Datensätze in den Pfad \\ Datei exportiert: " + path.toAbsolutePath().toString() + backSlash + "export.csv");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
}

