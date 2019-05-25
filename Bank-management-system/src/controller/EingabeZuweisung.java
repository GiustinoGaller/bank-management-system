package controller;

import java.util.Scanner;



/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

import kunde.Strings;

public class EingabeZuweisung {

	private Scanner scanner = new Scanner(System.in);

/**
 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
 * @return kundennummer
 */
	

	public String getKundennummer() {
		String kundennummer;
		do {
			System.out.print("Bitte legen Sie eine Kundennummer an. Diese muss vier Zahlen enthalten: > ");
			kundennummer = scanner.next();
			}while (!kundennummer.matches("^[0-9]*$") || kundennummer.length() < 4 || kundennummer.length() > 4);
		return kundennummer;
		}
	
	/**
	 * Wir pruefen mit der switch - case Anweisung, ob die Bedingung erfuellt wird.
	 * @return anrede
	 */
	
	public Strings printAnrede (){
	System.out.print("Bitte wählen Sie das Geschlecht aus [1 = Frau 2 = Herr]: > ");
	Strings anrede = null;
	int  auswahl = scanner.nextInt();
	boolean wiederholung = true;
	do{
		
		switch (auswahl) {
		case 1: anrede = Strings.Frau;
		wiederholung = false;
		break;
		case 2: anrede = Strings.Herr;
		wiederholung = false;
		break;
		
		default:
			System.out.println("Bitte geben Sie eine [1 = Frau oder 2 = Herr] ein.");
			wiederholung = true;
			printAnrede();
		
	
	}
	}while(!wiederholung && !anrede.equals(Strings.Frau)&&!anrede.equals(Strings.Herr));
	return anrede;
	}
	
/**
 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
 * @return vorname
 */
	
	public String getVorname() {
		String vorname;
	do {
		System.out.print("Bitte geben Sie den Vornamen ein: > ");
		vorname = scanner.next();
		}while (!vorname.matches("[A-Z][a-zA-Z]*") && vorname.length() < 2 || vorname.length() > 105);
	return vorname;
	}
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
	 * @return nachname
	 */
	
	public String getNachname() {
		String nachname;
		do {
		System.out.print("Bitte geben Sie den Nachnamen ein: > ");
		nachname = scanner.next();
		}while (!nachname.matches("[a-zA-z]+([ '-][a-zA-Z]+)*") && nachname.length() < 2 || nachname.length() > 105);
		return nachname;
	}
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird. 
	 * @return geburtsdatum
	 */
	
	public String getGeburtsdatum() {
		String geburtsdatum;
		do {
		System.out.print("Bitte geben Sie das Geburtsdatum ein (tt/mm/jjjj): > ");
		geburtsdatum = scanner.next();
		}while (!geburtsdatum.matches("[0-9]{2}[/]{1}[0-9]{2}[/]{1}[0-9]{4}"));
		return geburtsdatum;
	}
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
	 * @return adresszeile1
	 */
	public String getAdresszeile1() {
		String adresszeile1;
		do {
		System.out.print("Adresszeile 1 (nur die Straße angeben und alles ausgeschrieben): > ");
		adresszeile1 = scanner.next();
		}while (!adresszeile1.matches("^([A-ZÄÖÜ a-zäöüß])*$"));
		return adresszeile1;
	}
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
	 * @return hausnummer
	 */
	public String getHausnummer() {
		String hausnummer;
		do {
		System.out.print("Hausnummer: > ");
		hausnummer = scanner.next();
		}while (!hausnummer.matches("([0-9]+)([^0-9]*)"));
		return hausnummer;
	}
		
	/**public String getAdresszeile2() {
		String adresszeile2;
		do {
		System.out.print("Adresszeile 2: > ");
		adresszeile2 = scanner.next();
		}while (!adresszeile2.matches("^([A-ZÄÖÜ a-zäöüß])*$"));
		return adresszeile2;
	}*/
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
	 * @return plz
	 */
	public String getPlz() {
		String plz;
		do {
		System.out.print("Postleitzahl: > ");
		plz = scanner.next();
		}while (!plz.matches("^[0-9]{5}$"));
		return plz;
	}
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
	 * @return ort
	 */
	public String getOrt() {
		String ort;
		do {
		System.out.print("Ort: > ");
		ort = scanner.next();
		}while (!ort.matches("[A-z]*"));
		return ort;
	}
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
	 * @return telefonnummer
	 */
	public String getTelefonnummer() {
		String telefonnummer;
		do {
		System.out.println("Telefonnummer: > ");
		telefonnummer = scanner.next();
		}while (!telefonnummer.matches("^(\\d[ /-]?)+\\d$"));
		return telefonnummer;
	}
	
	/**
	 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
	 * @return email
	 */
		public String getEmail() {
			String email;
		do{
		System.out.println("E - Mail: > ");
		email = scanner.next();
	}while(!email.matches("^[\\w\\.=-]+@[\\w\\.-]+\\.[\\w]{2,4}$"));
		return email;
		}
	
		/**
		 * Wir pruefen mit der do - while Schleife, ob die Bedingung erfuellt wird.
		 * @return firmenname
		 */
	public String getFirmenname() {
		String firmenname;
		do {
			System.out.print("Bitte geben Sie den Firmennamen ein: > ");
			firmenname = scanner.next();
			}while (!firmenname.matches("[A-Z][a-zA-Z]*") && firmenname.length() < 2 || firmenname.length() > 105);
		return firmenname;
		}
}

