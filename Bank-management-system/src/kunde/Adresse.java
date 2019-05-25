package kunde;

import java.io.Serializable;

/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

public class Adresse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adresszeile1;
//	private String adresszeile2;
	private String hausnummer;
	private String plz;
	private String ort;
	/**
	 * 
	 * @param adresszeile1
	 * @param adresszeile2
	 * @param plz
	 * @param ort 
	 */
	
	public Adresse (String adresszeile1, String hausnummer, String plz, String ort) {
		this. adresszeile1 = adresszeile1;
//		this. adresszeile2 = adresszeile2;
		this. hausnummer = hausnummer;
		this. plz = plz;
		this. ort = ort;
	}
	
	/**
	 * @return gibt die Werte an die Instanzvariablen der Klasse "Adresse" als String zurück.
	 */
	@Override
	public String toString() {
		return "Straße: " + adresszeile1 + " " +  hausnummer + ", Postleitzahl : " + plz + ", Ort: " + ort;
	}
	
	
	
}
