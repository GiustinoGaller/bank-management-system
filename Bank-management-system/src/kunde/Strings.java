package kunde;

/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

public enum Strings {
	/**
	 * Aufzählungstypen weiblich und maennlich
	 * mit weiblich "Frau" als String Parameter und maennlich als String "Herr".
	 */
	Frau ("Frau"),
	Herr ("Herr");
	
	/**
	 * Die String Variable "anrede" als unveränderliche Konstante definiert.
	 */
	private final String anrede;
	
	
	
	/**
	 * Konstruktor
	 * @param anrede
	 */
	Strings (String anrede){
		this.anrede = anrede;
	}
	/**
	 * @return anrede
	 * gibt die Anrede mit return zurück an
	 */
	@Override
	public String toString() {
		
		return anrede;
	}

}
