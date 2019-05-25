package bank;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

public class Konto extends Observable implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String iban;
	private double kontostand;
	public final static int MAX_ANZAHL = 9;
	/**
	 * Konstruktor
	 * @param iban
	 * @param kontostand
	 */
	
	
	public Konto (String iban, double kontostand, Observer obs1, Observer obs2) {
		this.iban = iban;
		this.kontostand = kontostand;
		addObserver(obs1);
		addObserver(obs2);
	}
	
	@Override
	public String toString() {
		return "[IBAN: " + iban + ", Kontostand: " + Geld.toEuro(kontostand)+ "]";
	}

	/**
	 * Getters und Setters
	 * @return iban
	 */
	public String getIban() {
		return iban;
	}
	/**
	 * 
	 * @param iban
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}
	/**
	 * 
	 * @return kontostand
	 */
	public double getKontostand() {
		return kontostand;
	}
	/**
	 * 
	 * @param kontostand
	 */
	public void setKontostand(double kontostand) {//Hier wird geguckt, welche Summe ein oder ausbezahlt werden
		double betrag = kontostand;
		
		if(betrag >= 10000) {
			this.setChanged();
			this.notifyObservers(betrag);
		}
		
		this.kontostand = kontostand;
	}

	public boolean contains(String cs2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void einzahlen(double betrag) {
		this.kontostand += betrag;
	}
	
	public void auszahlen(double betrag) {
		this.kontostand -= betrag;
		
	}
	
}
