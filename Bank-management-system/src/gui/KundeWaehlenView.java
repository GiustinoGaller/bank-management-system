package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.BevelBorder;
import javax.xml.bind.Marshaller.Listener;

import bank.Bank;

//import bank.Bank;


public class KundeWaehlenView extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList<Object> kundenListe;
	private Bank bank;
//	private KundenModel model;

	public KundeWaehlenView(Bank bank, Observer obs) {
		this.setVisible(true);
		this.bank = bank;
//		this.model = bank.getKundenModel();
		this.setLayout(new GridBagLayout());
		setTitle("Kunde waehlen");
		setSize(1400, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screen.width / 2 - getWidth() / 2, screen.height / 2 - getHeight() / 2);
		
		fuegeMenubarHinzu();
		setCenterFrame(obs);
	}
	
	private JMenuBar fuegeMenubarHinzu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu kunden = erstelleMenu("Kunden", 'K');
		menuBar.add(kunden);
		
		JMenuItem info = new JMenuItem("Info", 'I');
		info.addActionListener(e -> JOptionPane.showMessageDialog(null, "Giustino Galler, s0545597", "Hinweis", JOptionPane.INFORMATION_MESSAGE));
		menuBar.add(info);
		
		kunden.add(new JSeparator());
		kunden.add(erstelleMenuUnterpunkt("Beenden", 'B', "beenden"));
		
		
		
		
		menuBar.add(kunden);
		menuBar.add(info);
		return menuBar;
	}
	
	private JMenu erstelleMenu(String label, char mnemonic) {
		JMenu menu = new JMenu(label);
		menu.setMnemonic(mnemonic);
		return menu;
	}
	
	
	private JButton fuegeButtonsHinzu(Observer obs) {
		JButton hinzufuegen = new JButton("auswaehlen");
		hinzufuegen.addActionListener(new Listener(obs, kundenListe));
		hinzufuegen.setPreferredSize(new Dimension(330, 100));
//		setConstraints(gbc, 2 + horizontalAddition, true, 1);
		 return hinzufuegen;
	}
	
	
	private class Listener extends Observable implements ActionListener{ //Anonyme Klasse für meinen Observer, um die Daten zu uebergeben
		
		public Listener(Observer obs, JList<Object> kundenListe) {
			this.addObserver(obs);
			this.kundenListe = kundenListe;
		}

		JList<Object> kundenListe;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			this.setChanged();
			this.notifyObservers(kundenListe.getSelectedValue());
		}
		
	}
	
	
	
	
	
	private JMenuItem erstelleMenuUnterpunkt(String label, char mnemonic, String actionCommand) {
		JMenuItem menuItem = new JMenuItem(label);
		menuItem.setMnemonic(mnemonic);
		menuItem.setActionCommand(actionCommand);
//		menuItem.addActionListener(this);
		return menuItem;
	}
	
	
	
	private JList<Object> fuegeKundenListeHinzu() {
		kundenListe = new JList<>();
		kundenListe.setFixedCellWidth(getWidth());
		kundenListe.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 28));
		kundenListe.setPreferredSize(new Dimension(1000, 500));
		/**for (Kunde kunden : bank.getKunden()) {
			kundenListe.setListData(kunden.getName());
		}*/
		/**model.addObserver((o,a) ->*/ 
		kundenListe.setListData(bank.getKunden().toArray());
		return kundenListe;
	}
	
	public void setCenterFrame(Observer obs) {
		GridBagConstraints gbc = new GridBagConstraints();
		JPanel center = new JPanel();
		center.setPreferredSize(new Dimension(1000, 500));
		center.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
		center.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));

		JList kundenListe = this.fuegeKundenListeHinzu();
		kundenListe.setPreferredSize(new Dimension(1000, 500));
		kundenListe.setMaximumSize(new Dimension(1000, 500));
//		kundenListe.setWrapStyleWord(true);
		
		setConstraints(gbc, 0, 0, true);
		gbc.weighty = 0.7;

		JScrollPane scrollpane = new JScrollPane(kundenListe);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setPreferredSize(new Dimension(1000, 500));
		scrollpane.setMaximumSize(new Dimension(1000, 500));

		center.add(scrollpane, gbc);
		
		JButton hinzuefuegen = this.fuegeButtonsHinzu(obs);
		setConstraints(gbc, 0, 1, true);
		gbc.weighty = 0.3;
		
		center.add(hinzuefuegen, gbc);

		gbc.gridwidth = 3;
		this.getContentPane().add(center, gbc);
		gbc.gridwidth = 1;

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
	
}

