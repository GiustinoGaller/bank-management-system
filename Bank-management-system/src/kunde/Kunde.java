package kunde;




import java.io.Serializable;
import java.util.ArrayList;

import bank.Konto;

/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

public abstract class Kunde implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//	private Konto konten[] = new Konto [MAX_ANZAHL]; //Ein Kunde hat auch ein Konto
	private ArrayList<Konto> konten = new ArrayList <>();

	
	private int kundennummer;
	private Adresse adressen; //Ein Kunde hat auch eine Adresse und wir haben fünf Kunden angelegt
	private String telefonnummer;
	private String email;
	private Kundenart kundenart;
	static int anzahlKunden;
	static int anzahlKonten;
//	public final static int MAX_ANZAHL = 100; //Maximale Anzahl an Kunden im Speicher
	
	/**
	 * 
	 * @param kundennummer
	 * @param adressen
	 * @param telefonnummer
	 * @param email
	 * @param konten
	 */

	public Kunde(Kundenart kundenart, int kundennummer,
			Adresse adressen, String telefonnummer, String email, ArrayList<Konto> konten) {
				this.kundenart = kundenart;
				this.kundennummer = kundennummer;
				this.adressen = adressen;
				this.telefonnummer = telefonnummer;
				this.email = email;
				this.konten = konten;
				anzahlKunden++;
				anzahlKunden();

	}
	
	public void anzahlKunden() {
		System.out.println("Anzahl der angelegten Kunden: " + anzahlKunden);
		
	}
	
	public void addKonto(Konto konto) {
		if (konten.size() < 10) {
			konten.add(konto);
			
		}else {
			throw new IndexOutOfBoundsException();
		}
		
	}
	
	
	
	
	
	/**Gibt die Objektvariablen als String zurueck
	@Override
	public String toString() {
		return " Adresse: " + adressen
				+ ", Telefonnummer: " + telefonnummer + ", E-mail: " + email + ", Kundennummer: " + kundennummer;
	}*/
	
	
	/**
	 * Eine abstrakte Methode, welche nicht implementiert wird, da diese sich in der Oberklasse befindet. Diese gibt nur den Methoden-
	 * koerper vor für die Subklassen (Unterklassen oder auch Kindsklassen genannt). Implementiert werden diese dann in den Subklassen.
	 */
	public abstract String getName();
	

	public void setName() {
		
	}
	/**
	 * 
	 * @return Gibt die Werte der Instanzvariablen als formatierten String zurück.
	 */

	/**public Konto[] getKonten() {
		return konten;
	}*/
	/**
	 * 
	 * @param konten Arrayobjekt zurückgeben lassen.
	 */
	
	/**public void setKonten(Konto[] konten) {
		this.konten = konten;
	}*/
	


	public int getKundennummer() {
		return kundennummer;
	}


	public ArrayList<Konto> getKonten() {
		return konten;
	}

	public void setKonten(ArrayList<Konto> konten) {
		this.konten = konten;
	}

	public void setKundennummer(int kundennummer) {
		this.kundennummer = kundennummer;
	}


	public String getTelefonnumer() {
		return telefonnummer;
	}


	public void setTelefonnumer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Adresse getAdressen() {
		return adressen;
	}


	public void setAdressen(Adresse adressen) {
		this.adressen = adressen;
	}

	public Kundenart getKundenart() {
		return kundenart;
	}

	public void setKundenart(Kundenart kundenart) {
		this.kundenart = kundenart;
	}

	public boolean contains(ArrayList<Kunde> kunden) {
		/**boolean result = false;

        for(Konto i : konten){
            if(i.equals(cs1)){
                result = true;
                break;
            }
        } Notiz */
		return false;
	}
	public boolean contains(String cs2) {
		
		return false;
	}

	
}
