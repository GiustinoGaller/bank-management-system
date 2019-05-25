package logging;

public class PrintInConsole implements LoggerPrint {
	
	
	
	
	public void printOut(String log, String time) {
		System.out.println(time + ": Logger gestartet!\n" + log);
	}

}
