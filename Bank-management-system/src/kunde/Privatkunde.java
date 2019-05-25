package kunde;

import java.io.Serializable;
import java.util.ArrayList;

import bank.Konto;



/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

public class Privatkunde extends Kunde{
	
	private Strings anrede;
	private String vorname;
	private String nachname;
	private String geburtsdatum;
	private Kundenart kundenart;
	
	
	/**
	 * 
	 * @param kundenart
	 * @param i
	 * @param anrede
	 * @param vorname
	 * @param nachname
	 * @param geburtsdatum
	 * @param adressen
	 * @param telefonnumer
	 * @param email
	 * @param konten
	 */
	

	public Privatkunde(Kundenart kundenart, int i, Strings anrede, String vorname, String nachname, String geburtsdatum,
			Adresse adressen, String telefonnumer, String email, ArrayList <Konto> konten) {
		super(kundenart, i, adressen, telefonnumer, email, konten);
		this.anrede = anrede;
		this.vorname =vorname;
		this.nachname = nachname;
		this.geburtsdatum = geburtsdatum;
		this.kundenart = kundenart;
	}
	
	/**
	 * @return gibt die einzelnen Werte der Instanzvariable privatkunde als String zurueck.
	 */
	@Override
	public String getName() {
		String name =vorname + " " + nachname;
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public Strings getAnrede() {
		return anrede;
	}

	public void setAnrede(Strings anrede) {
		this.anrede = anrede;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(String geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Kundenart getKundenart() {
		return kundenart;
	}

	public void setKundenart(Kundenart kundenart) {
		this.kundenart = kundenart;
	}

	
	
	

}
