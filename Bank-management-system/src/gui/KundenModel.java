package gui;

import java.util.ArrayList;
import java.util.Observable;

import bank.Konto;

public class KundenModel extends Observable {
	
	
	
	private ArrayList <Konto> kunden = new ArrayList<>();
//	private Bank bank;
	
	
/**	private KundenModel(ArrayList <Kunde> kunden, Bank bank) {
		this.kunden = kunden;
		this.bank = bank;
	}*/
	
	public void kundenHinzufuegen(Konto kunde) {
		kunden.add(kunde);
		setChanged();
		notifyObservers(kunden);
		}
	
	public ArrayList <Konto> getKunden(){
		return kunden;
	}
	
	/**
	 * Observer auch benachrichtigen, wenn sich nichts getan hat.
	 */
	public void erzwingeUpdate() {
		setChanged();//erkennt die Veraenderung des Observable- Objektes
		notifyObservers(kunden);//bewirkt, dass die als Observer (Beobachter) registrierten, das Objekt als Parameter geliefert bekommen
	}

}
