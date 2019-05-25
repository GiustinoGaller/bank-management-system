package controller;
import java.util.Comparator;


import kunde.Kunde;

public class Sortierer implements Comparator<Kunde> {//Eine Gesamtreihenfolge der Objekte einer Klasse kann man mit dem Interface Comperator festlegen

	@Override
	public int compare(Kunde o1, Kunde o2) {
		return ((Integer)o1.getKundennummer()).compareTo(o2.getKundennummer());//Findet heraus, ob die Kundennummern uebereinstimmen
	}
}
	
	
	
	/**@Override
	public int compareTo(Object o) {
		if (o instanceof Kunde) {
			Kunde other = (Kunde)o;
		
		
		if (kunde.getKundennumer() < other.getKundennummer()) {
				return -1;
		}
		if (kunde.getKundennummer() > other.getKundennummer()) {
			return 1;
		}
		return 0;
	}
	return 0;
	*/
		



