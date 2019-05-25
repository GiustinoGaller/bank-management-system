package bank;

import java.text.NumberFormat;
import java.util.Locale;

public class Geld {
	public static String toEuro (double Preis) {
		double dPreis = (double) Preis;
		NumberFormat formatPreis = NumberFormat.getCurrencyInstance(Locale.GERMANY);
		return formatPreis.format(dPreis);
	}

}
