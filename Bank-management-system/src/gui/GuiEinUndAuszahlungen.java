package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import bank.Bank;
import bank.Konto;
import kunde.Kunde;

public class GuiEinUndAuszahlungen extends JFrame implements ActionListener, Observer {

	
	
	private static final long serialVersionUID = 1L;
	private Bank bank;
//	private KundenModel model;
	public JTextArea textArea = new JTextArea();
	private JPanel gesamtDepotContainer = new JPanel();
	private JPanel kontenContainer = new JPanel();
	
	public GuiEinUndAuszahlungen(Bank bank){
		this.setVisible(true);
		this.bank = bank;
//		this.model = bank.getKundenModel();
		
		
		
		kreiereEinHauptfenster(bank.getKunden());
		fuegeMenubarHinzu();
//		gesamtDepotContainer.setBackground(Color.red);
//		gesamtDepotContainer.setPreferredSize(new Dimension(1000, 2000));
		this.getContentPane().add(gesamtDepotContainer);
		
//		kontenContainer.setBackground(Color.red);
//		kontenContainer.setPreferredSize(new Dimension(1000, 2000));
		this.getContentPane().add(kontenContainer);
		//setBottomFrame();
	}

	private void kreiereEinHauptfenster(ArrayList<Kunde> kundes) {
		
		setSize(2800, 1400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
		
		setLayout(new FlowLayout());
		
	}
	
	private JMenuBar fuegeMenubarHinzu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu kunden = erstelleMenu("Kunden", 'K');
		menuBar.add(kunden);
		JMenuItem item = erstelleMenuUnterpunkt("Kunde waehlen", 'w', "kundewaehlen");
		item.addActionListener(e -> {
			KundeWaehlenView view = new KundeWaehlenView(bank, this);
		});
		kunden.add(item);
		
		JMenuItem info = new JMenuItem("Info", 'I');
		info.addActionListener(e -> JOptionPane.showMessageDialog(null, "Giustino Galler, s0545597", "Hinweis", JOptionPane.INFORMATION_MESSAGE));
		menuBar.add(info);
		
		kunden.add(new JSeparator());
		kunden.add(erstelleMenuUnterpunkt("Beenden", 'B', "beenden"));
		
		menuBar.add(kunden);
		menuBar.add(info);
		return menuBar;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void setTransaktionFenster(Kunde kunde) {
		double[] AnfangsSumme = new double [kunde.getKonten().size()];
		double summeArray = 0;
//		double test = 0;
		GridBagConstraints gbc = new GridBagConstraints();
		gesamtDepotContainer.removeAll();
		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(1000, 500));
		center.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		center.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
//		double summeGesamt = kunde.getKonten().get(i).getKontostand();
//		for (Konto summen : kunde.getKonten()) {
		for (int i = 0; i < kunde.getKonten().size(); i++) {
//		for (Konto konten : kunde.getKonten()) {
			
			AnfangsSumme [i] =kunde.getKonten().get(i).getKontostand();
			summeArray = AnfangsSumme[i] + summeArray;
			System.out.println(summeArray + " summeArray");
			textArea.setText("Summe Gesamtdepot: " + summeArray + "\n" + "Transaktionen: \n" + "Anzahl der Konten: " + kunde.getKonten().size() + " " + Arrays.toString(AnfangsSumme).toString().replace("[", "\nKontostand der einzelnen Konten: ").replace("]", ""));
			
//			beendet += AnfangsSumme;
//			System.out.println(AnfangsSumme + " SummeGesamt");
//			System.out.println(beendet + " Endsumme");
			
		}
		
//		String zwischenSummeKonvertiert = Arrays.toString(AnfangsSumme);
//		String summeGesamtKonvertiert = Double.toString(summeArray);
		
			
		textArea.setPreferredSize(new Dimension(2000, 500));
		textArea.setMaximumSize(new Dimension(2999, 500));
//		textArea.setWrapStyleWord(true);

		JScrollPane scrollpane = new JScrollPane(textArea);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setPreferredSize(new Dimension(1000, 500));
		scrollpane.setMaximumSize(new Dimension(1000, 500));

		setConstraints(gbc, 0, true, 0);
		center.add(scrollpane, gbc);

		gbc.gridwidth = 3;
		this.getContentPane().add(center, gbc);
		gbc.gridwidth = 1;
		gesamtDepotContainer.add(center, gbc);
		
		
			

	}
	
	public void setConstraints(GridBagConstraints gbc, int x, int y, boolean fill) {
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.fill = fill ? GridBagConstraints.HORIZONTAL: GridBagConstraints.NONE;
	}
	
	public void setConstraints(GridBagConstraints gbc, int x, boolean fillBoth, int y) {
		
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;
		gbc.fill = GridBagConstraints.BOTH;
	}
	
	

	private JMenu erstelleMenu(String label, char mnemonic) {
		JMenu menu = new JMenu(label);
		menu.setMnemonic(mnemonic);
		return menu;
	}
	
	private JMenuItem erstelleMenuUnterpunkt(String label, char mnemonic, String actionCommand) {
		JMenuItem menuItem = new JMenuItem(label);
		menuItem.setMnemonic(mnemonic);
		menuItem.setActionCommand(actionCommand);
		menuItem.addActionListener(this);
		return menuItem;
	}
	
	/**private void zeigeKunden() {
		for (Kunde kunden : bank.getKunden()) {
			kunden.getName();
		}
	}*/

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("beenden")) {
			System.exit(0);
		}
		
	}
	
	/**private void addButtons() {
		JButton hinzuFuegen = new JButton("Kunde hinzufuegen");
		hinzuFuegen.addActionListener(event -> new KundeWaehlenView());
		
	}*/
	/**private void fuegeKundenListeHinzu() {
		kundenListe = new JList<>();
		kundenListe.setFixedCellWidth(getWidth());
		kundenListe.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		/**model.addObserver((o, a) -> kundenListe.setListData(model.getKunden().toArray()));
		add(kundenListe);
	 * @param kunde */
	
	public void setBottomFrame(Kunde kunde) {

		int anzahlKonten = kunde.getKonten().size();
		System.out.println(anzahlKonten);
		GridBagConstraints gbc = new GridBagConstraints();
		
		kontenContainer.removeAll();

		for (int i = 0; i < anzahlKonten; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridBagLayout());
			panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
			panel.setPreferredSize(new Dimension(1000, 500));

			String iban1 = kunde.getKonten().get(i).getIban();
			JLabel iban = getIbanLabel(gbc, i, iban1);
			panel.add(iban, gbc);

			gbc.gridwidth = 1;

			JLabel kontoStandLabel = getKontostandLabel(gbc, i);
			panel.add(kontoStandLabel, gbc);
			
			JLabel EinzahlungLabel = getEinzahlungLabel(gbc, i);
			panel.add(EinzahlungLabel, gbc);
			
			JLabel AuszahlungLabel = getAuszahlungLabel(gbc, i);
			panel.add(AuszahlungLabel, gbc);
			
			double kontoStand = kunde.getKonten().get(i).getKontostand();
			JTextField kontoStandText = getKontostand(kontoStand, gbc, i);
			panel.add(kontoStandText, gbc);
			
			double einzahlung = kunde.getKonten().get(i).getKontostand();
			JTextField einzahlungText = getEinzahlung(einzahlung, gbc, i, kunde, bank);
			panel.add(einzahlungText, gbc);
			
			double auszahlung = kunde.getKonten().get(i).getKontostand();
			JTextField auszahlungText = getAuszahlung(auszahlung, gbc, i, kunde, bank);
			panel.add(auszahlungText, gbc);

			JLabel euro = getEuroLabel(gbc, i);
			panel.add(euro, gbc);
			
			JLabel euroEinzahlung = getEuroEinzahlungLabel(gbc, i);
			panel.add(euroEinzahlung, gbc);

			JLabel euroAuszahlung = getEuroAuszahlungLabel(gbc, i);
			panel.add(euroAuszahlung, gbc);

			

			setConstraints(gbc, 0 + i, true, 1);
			kontenContainer.add(panel, gbc);
		}
		
	}

	private JLabel getEuroLabel(GridBagConstraints gbc, int horizontalAddition) {
		JLabel euro = new JLabel("Euro");
		euro.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		euro.setPreferredSize(new Dimension(330, 100));
		setConstraints(gbc, 2 + horizontalAddition, true, 1);
		return euro;
	}
	
	private JLabel getEuroEinzahlungLabel(GridBagConstraints gbc, int horizontalAddition) {
		JLabel euro = new JLabel("Euro");
		euro.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		euro.setPreferredSize(new Dimension(330, 100));
		setConstraints(gbc, 2 + horizontalAddition, true, 2);
		return euro;
	}
	
	private JLabel getEuroAuszahlungLabel(GridBagConstraints gbc, int horizontalAddition) {
		JLabel euro = new JLabel("Euro");
		euro.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		euro.setPreferredSize(new Dimension(330, 100));
		setConstraints(gbc, 2 + horizontalAddition, true, 3);
		return euro;
	}

	private JTextField getKontostand(double kontostand, GridBagConstraints gbc, int horizontalAddition) {
		JTextField kontoStandText = new JTextField();
		kontoStandText.setText("" + kontostand);
		kontoStandText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		kontoStandText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, kontoStandText.getText());

			}
		});
		kontoStandText.setPreferredSize(new Dimension(330, 100));
		setConstraints(gbc, 1 + horizontalAddition, true, 1);
		return kontoStandText;
	}
	private JTextField getEinzahlung(double einzahlung, GridBagConstraints gbc, int horizontalAddition, Kunde kunde, Bank bank) {
		JTextField einzahlungText = new JTextField();
		einzahlungText.setText("Geben Sie einen Betrag ein!");
		einzahlungText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		einzahlungText.addActionListener(new AbstractAction() {
			
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			

			@Override
			    public void actionPerformed(ActionEvent e) {
				
				
				ArrayList<Konto> kundeAlterKontostand  = kunde.getKonten();
				
				
				try {
					double einzahlung = Double.parseDouble(einzahlungText.getText()); //zeigt die Einzahlungen an
					double gesamtDepotWert = Double.parseDouble(einzahlungText.getText());
					System.out.println(einzahlung + " einzahlung");
					for (int a = 0; a < kunde.getKonten().size(); a++) {
						double neuerKontostand =einzahlung + kundeAlterKontostand.get(a).getKontostand();
						kundeAlterKontostand.get(a).setKontostand(neuerKontostand);
						gesamtDepotWert+= neuerKontostand;
//						kundeKontoKontostand.putValue(kunde.getKonten().get(a).getIban(), kunde.getKonten().get(a).setKontostand(neuerKontostand));
						/**if (kunde.getKonten().get(a).getIban().equals(kundeAlterKontostand.get(a).getIban())) {
							
							kundeAlterKontostand.get(a).setKontostand(neuerKontostand);
							System.out.println("Test" + kundeAlterKontostand);
						}*/
					

			    	   
				textArea.setText("Summe Gesamtdepot: " + gesamtDepotWert + "\n\n" + "Transaktionen: \n" + "Einzahlung in Hoehe von: " + einzahlung + " €\nauf das Konto: " 
			    	   +kundeAlterKontostand.toString().replace("[", "\n").replace("]", "") + "\n\nDatum und Uhrzeit der Einzahlung: " + LocalDateTime.now() + "\n" + "Anzahl der Konten: " 
						+ kunde.getKonten().size() + " " 
			    	  );

					}
			    }catch (NumberFormatException ex) {
			    	JOptionPane.showMessageDialog(null, "Es wurde keine Zahl eingegeben!", "Fehler!", JOptionPane.WARNING_MESSAGE);
			    } 
			}
			    
			});

			
			
		einzahlungText.setPreferredSize(new Dimension(330, 100));
		setConstraints(gbc, 1 + horizontalAddition, true, 2);
		return einzahlungText;
	}
	
	private JTextField getAuszahlung(double auszahlung, GridBagConstraints gbc, int horizontalAddition, Kunde kunde, Bank bank) {
		JTextField auszahlungText = new JTextField();
		auszahlungText.setText("Geben Sie einen Betrag ein!");
		auszahlungText.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		auszahlungText.addActionListener(new AbstractAction() {
			
			 /**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			

			@Override
			    public void actionPerformed(ActionEvent e) {
				
				
				ArrayList<Konto> kundeAlterKontostand  = kunde.getKonten();
				
				
				try {
					double auszahlung = Double.parseDouble(auszahlungText.getText()); //zeigt die Einzahlungen an
					double gesamtDepotWert = Double.parseDouble(auszahlungText.getText());
					System.out.println(auszahlung + " auszahlung");
					for (int a = 0; a < kunde.getKonten().size(); a++) {
						double neuerKontostand =-auszahlung + kundeAlterKontostand.get(a).getKontostand();
						kundeAlterKontostand.get(a).setKontostand(neuerKontostand);
						gesamtDepotWert+=neuerKontostand- auszahlung;
//						kundeKontoKontostand.putValue(kunde.getKonten().get(a).getIban(), kunde.getKonten().get(a).setKontostand(neuerKontostand));
						/**if (kunde.getKonten().get(a).getIban().equals(kundeAlterKontostand.get(a).getIban())) {
							
							kundeAlterKontostand.get(a).setKontostand(neuerKontostand);
							System.out.println("Test" + kundeAlterKontostand);
						}*/
					

			    	   
				textArea.setText("Summe Gesamtdepot: " + gesamtDepotWert + "\n\n" + "Transaktionen: \n" + "Auszahlung in Hoehe von: " + auszahlung + " €\nvon dem Konto: " 
			    	   +kundeAlterKontostand.toString().replace("[", "\n").replace("]", "") + "\n\nDatum und Uhrzeit der Auszahlung: " + LocalDateTime.now() + "\n" + "Anzahl der Konten: " 
						+ kunde.getKonten().size() + " " 
			    	   );

					}
			    }catch (NumberFormatException ex) {
			    	JOptionPane.showMessageDialog(null, "Es wurde keine Zahl eingegeben!", "Fehler!", JOptionPane.WARNING_MESSAGE);
			    } 
			}
			    
			});

			
			
		auszahlungText.setPreferredSize(new Dimension(330, 100));
		setConstraints(gbc, 1 + horizontalAddition, true, 3);
		return auszahlungText;
	}
	

	private JLabel getKontostandLabel(GridBagConstraints gbc, int horizontalAddition) {
		JLabel kontoStand = new JLabel("Kontostand");
		kontoStand.setPreferredSize(new Dimension(330, 100));
		kontoStand.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		setConstraints(gbc, 0 + horizontalAddition, true, 1);
		return kontoStand;
	}
	private JLabel getEinzahlungLabel(GridBagConstraints gbc, int horizontalAddition) {
		JLabel kontoStand = new JLabel("Einzahlung: ");
		kontoStand.setPreferredSize(new Dimension(330, 100));
		kontoStand.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		setConstraints(gbc, 0 + horizontalAddition, true, 2);
		return kontoStand;
	}
	private JLabel getAuszahlungLabel(GridBagConstraints gbc, int horizontalAddition) {
		JLabel kontoStand = new JLabel("Auszahlung: ");
		kontoStand.setPreferredSize(new Dimension(330, 100));
		kontoStand.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		setConstraints(gbc, 0 + horizontalAddition, true, 3);
		return kontoStand;
	}

	private JLabel getIbanLabel(GridBagConstraints gbc, int horizontalAddition, String iban1) {
		JLabel iban = new JLabel(iban1);
		iban.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		gbc.gridwidth = 3;
		setConstraints(gbc, 0 + horizontalAddition, true, 0);
		return iban;
	}

	@Override
	public void update(Observable o, Object arg) {
//		System.out.println(arg);
		this.setTitle(((Kunde) arg).getName());
		Kunde kunde = (Kunde) arg;
		
		
		setTransaktionFenster(kunde);
		setBottomFrame(kunde);
		
		/**for(Component component :this.getComponents()) {
			if(component instanceof JLabel) {
				((JLabel)component).setText(kunde.getKonten());
				
			})*/
		}
		
	

	
}
