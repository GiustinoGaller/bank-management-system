package threat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger implements LoggerPrinter {
	
	
	
	private static PrintWriter out;

	@Override
	public void log(String time, String log) {
		
		if (out == null) {
			FileOutputStream fileOutputStream;
			try {
				fileOutputStream = new FileOutputStream(new File("VermoegenAllerKundenInBank.txt"),true);
				out = new PrintWriter(fileOutputStream);
//				out.append(time + " Logger gestartet!");
				out.println("\n");
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		out.append(time + log);
		out.flush();
		out = null;
		
		
	}

}
