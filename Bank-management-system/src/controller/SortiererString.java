package controller;

import java.util.Comparator;

import kunde.Kunde;

public class SortiererString implements Comparator<Kunde> {//Fuer eine Gesamtreihenfolge der Objekte einer Klasse kann man das Interface Comperator implementieren

	@Override
	public int compare(Kunde o1, Kunde o2) {
		return o1.getName().compareTo(o2.getName());//Findet heraus, ob die Namen gleich sind
	}

	

	

	

}
