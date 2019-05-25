package logging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintInLogger implements LoggerPrint {
	private static PrintWriter out;

	public void printOut(String time, String log) {
		if (out == null) {
			FileOutputStream fiis;
		try {
			fiis = new FileOutputStream(new File("log.txt"), true);
			out = new PrintWriter(fiis);
			out.append(time + ": Logger gestartet!\n");
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		}
		out.append(time + " : " + log + "\n");
		out.flush();
	}
}
