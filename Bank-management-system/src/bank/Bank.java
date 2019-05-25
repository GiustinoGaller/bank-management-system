package bank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import kunde.Adresse;
import kunde.Kunde;
import bank.Konto;

public class Bank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * @author Giustino Galler Matrikel - Nr.: s0545597
	 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
	 */

	private String name;
	private String bankleitzahl;
	private String bic;
	private Adresse adresse[] = new Adresse[0];
	// private Kunde kunden [] = new Kunde [KUNDEMAXZAHL];
	private ArrayList<Kunde> kunden = new ArrayList<>();
	private HashMap<String, Konto> kontenKunden = new HashMap<String, Konto>();// HashMap kontenKunden
	// private final static int KUNDEMAXZAHL = 100;
	// private int count = 0;
	private int bankvermoegen;
	private Vorstand vorsdtand = new Vorstand("Hans- Peter", "Wurst");

	public void setVorsdtand(Vorstand vorsdtand) {
		this.vorsdtand = vorsdtand;
	}

	private Bankangestellter bankangestellter = new Bankangestellter("Hans- Wurst", "Fleisch");

	public int berechneBankVermoegen() {
		for (Kunde kunde : kunden) {
			for (int i = 0; i < kunde.getKonten().size(); i++) {

				bankvermoegen += kunde.getKonten().get(i).getKontostand();
			}
		}

		return bankvermoegen;
	}

	public Vorstand getVorsdtand() {
		return vorsdtand;
	}

	public Bankangestellter getBankangestellter() {
		return bankangestellter;
	}

	/**
	 * 
	 * @param name
	 * @param bankleitzahl
	 * @param bic
	 * @param adresse
	 */
	public Bank(String name, String bankleitzahl, String bic, Adresse adresse[]) {
		this.name = name;
		this.bankleitzahl = bankleitzahl;
		this.bic = bic;
		this.adresse = adresse;

	}

	/**
	 * Die Methode addKunden (Kunde kunde) legt jeden erzeugten Kunden in das
	 * ArrayList kunden ab. Durchgefuehrt wird dies durch die oben definierte int
	 * Variable count, welche als Zaehler oder laeufer funktioniert und über die
	 * einzelnen Felder des Array - Objekte laeuft. Jeder Kunde wir in einem Feld im
	 * Array gespeichert. Somit muss sich die Variable count mit ++ immer um eins
	 * erhoehen.
	 * 
	 * @param kunde
	 */
	public void addKunden(Kunde kunde) {
		/**
		 * Fuer Arrays kunden[count] = kunde; count ++;
		 */
		kunden.add(kunde);
	}

	/**
	 * @return gibt die Werte der Instanzvariablen "postbank" als String zurück.
	 */
	@Override
	public String toString() {
		return name + " " + bankleitzahl + " " + bic + " " + Arrays.toString(adresse);
	}

	/**
	 * Die Methode "informationenBank" zeigt die Informationen zur Postbank
	 * übersichtlich an.
	 */
	public void informationenBank() {
		System.out.println("Herzlich Willkommen bei der " + name + "\n");

		System.out.println("Die Bankleitzahl lautet: " + bankleitzahl);
		System.out.println("Die BIC lautet: " + bankleitzahl + "\n");
		for (Adresse adresseBank : adresse) {
			System.out.println(adresseBank);
		}
		System.out.println("**********************************************************************");
	}

	public ArrayList<Kunde> getKunden() {
		return kunden;
	}

	public void setKunden(ArrayList<Kunde> kunden) {
		this.kunden = kunden;
	}

	public Adresse[] getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse[] adresse) {
		this.adresse = adresse;
	}

	public HashMap<String, Konto> getKontenKunden() {
		return kontenKunden;
	}

	public void setKontenKunden(HashMap<String, Konto> kontenKunden) {
		this.kontenKunden = kontenKunden;
	}

}
