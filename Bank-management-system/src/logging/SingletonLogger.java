package logging;


import java.time.LocalDateTime;

public class SingletonLogger {
	
	
	
	private static SingletonLogger instance;
	private LoggerPrint loggerPrint= new PrintInLogger();// Wir erstellen ein Objekt von unserem Interface "LoggerPrint"

	
	
	private SingletonLogger() {
		
		
	}
	
	public static synchronized SingletonLogger getInstance() {
		if (instance == null) {//Ueberprueft, ob es schon eine Instanz von SingletonLogger gibt
			instance = new SingletonLogger();//erstelle eine Instanz von SingletonLogger, falls noch nicht vorhanden (Lazy Creation)
		}
		return instance;
	}
	
	public void log(String log) {
		loggerPrint.printOut(getTime(), log);
	}
	
	private  String getTime() {
		return LocalDateTime.now().toString();
		
	}
	
	public void setLoggerPrint(LoggerPrint loggerPrint) {
		this.loggerPrint = loggerPrint;
	}
}
