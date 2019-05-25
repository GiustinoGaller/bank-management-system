package kunde;

import java.util.ArrayList;

import bank.Konto;



/**
 * 
 * @author Giustino Galler Matrikel - Nr.: s0545597
 * @version 1.0 mit jdk 1.8.0 Eclipse 4.7
 */

public class Firmenkunde extends Kunde {

	private String firmenname;
	private Kundenart kundenart;
	
	/**
	 * 
	 * @param kundenart
	 * @param i
	 * @param firmenname
	 * @param adressen
	 * @param telefonnumer
	 * @param email
	 * @param konten
	 */
	
	public Firmenkunde(Kundenart kundenart,int i,  String firmenname,
			Adresse adressen, String telefonnumer, String email, ArrayList <Konto> konten) {
		super(kundenart, i, adressen, telefonnumer, email, konten);
		this.firmenname = firmenname;
		this.kundenart = kundenart;
	}

	@Override
	public String getName() {
		String firma = firmenname;
		return firma;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getFirmenname() {
		return firmenname;
	}

	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}

	public Kundenart getKundenart() {
		return kundenart;
	}

	public void setKundenart(Kundenart kundenart) {
		this.kundenart = kundenart;
	}
	
	
	
	
	
	
	

}
