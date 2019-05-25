package threat;

import java.time.LocalDateTime;

import bank.Bank;
import bank.Konto;

public class MonitoringAktivierer extends Thread {

	Bank bank;
	private LoggerPrinter loggerPrinter = new Logger();

	public MonitoringAktivierer(Bank bank) {
		this.bank = bank;
		start();
	}

	/**
	 * Die Klasse Bank enthaelt meine KundenKonten HashMap alle Werte darin addieren
	 * 
	 * 
	 * Vorlesung: Verschiedene Zugriffe auf Konten muss lock enhalten lock.lock();
	 * und lock.unlock(); Wenn jemand von außen auf mich zukommt, dann darf kein
	 * anderer mehr etwas schreiben Es geht aber auch mit synchronized - > kann man
	 * sowohl eine Mehtode, als auch einen Block als synchronisiert ansehen
	 * ThreadSicher -> StringBuffer, Vector, HashTable Nicht ThreadSicher ->
	 * StringBuilder, ArrayList, HashMap Trotzdem die unsicheren sprich
	 * threadunsicheren verwenden und mit lock() - Methoden arbeiten solange man in
	 * eine nicht static methode geht, so wird vor dem Start der Methode, blockiert
	 * 
	 * Ich kann nur eine synchronized Methode pro Klasse aufrufen (gelb) und es
	 * können keine zwei Objekte auf zwei synchronized Methoden auf einer Klasse
	 * (gelb) zugreifen
	 * 
	 * Wenn ich ein zweites Objekt einer anderen Klasse oeffne, so ist das Objekt
	 * nicht blockiert, da noch kein Threat vorhanden ist, sprich es gilt immer nur
	 * fuer das aktuelle Objekt
	 * 
	 */
	public void log(String log) {
		loggerPrinter.log(getTime(), log);
	}

	private String getTime() {
		return LocalDateTime.now().toString();
	}

	public void setLoggerPrinter(LoggerPrinter loggerPrinter) {
		this.loggerPrinter = loggerPrinter;
	}

	public double summeDerKundenkontenAufBank() {
		double kontostand = 0;
		for (java.util.Map.Entry<String, Konto> alleKonten : bank.getKontenKunden().entrySet()) {

			Konto summe = alleKonten.getValue();
			kontostand += summe.getKontostand();
		}
		return kontostand;

	}

	public void run() {
		// for (int i = 0; i < bank.getKontenKunden().size(); i++) {
		boolean run = true;
		while (run) {
			try {
				loggerPrinter.log(getTime(), "Die Bank verwaltet einen Gesamtkontostand in Höhe von "
						+ summeDerKundenkontenAufBank() + "\n\n");
				summeDerKundenkontenAufBank();
				sleep(10000);// Druckt alle zehn Sekunden den gesamten Kontostand der Bank aus
			} catch (InterruptedException e) {
				run = false;
			}
			System.out.println("MonitoringAktivierer");
		}
	}

}
