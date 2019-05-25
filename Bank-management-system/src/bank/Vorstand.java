package bank;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class Vorstand implements Observer, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String nachname;
	
	
	public Vorstand(String name, String nachname) {
		this.name = name;
		this.nachname = nachname;
	}
	

	@Override
	public void update(Observable kontoObj, Object betrag) {
		Konto konto = (Konto) kontoObj;
		System.out.println("Vorstand " + getName() + getNachname() + " hat die Information erhalten, dass eine Einzahlung/ Auszahlung in Höhe von " + konto + "stattgefunden hat");
		
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNachname() {
		return nachname;
	}


	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	
	

}
