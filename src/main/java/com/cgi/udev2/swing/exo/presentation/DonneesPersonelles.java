package com.cgi.udev2.swing.exo.presentation;

import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.JDatePicker;

import com.cgi.udev2.swing.exo.entity.Contact;
import com.cgi.udev2.swing.exo.service.ContactService;

public class DonneesPersonelles extends JFrame implements AutoCloseable {

	private ContactService contactService;
	
	private JButton boutonSauver;
	private JButton boutonAnnuler;
	private JComboBox<String> titreField;
	private JTextField nomField;
	private JTextField prenomField;
	private JTextField mailField;
	private JTextField telField;
	private JDatePicker dateNaissanceField;
	private JTextArea rueField;
	private JTextField codePostalField;
	private JTextField villeField;
	private JTextField paysField;
	
	private JMenuBar menuBar;
	private JMenu actionMenu;
	private JMenuItem ouvrirMenu;
	private JMenuItem sauverMenu;
	private JMenuItem reinitialiserMenu;
	private JMenuItem fermerMenu;
	
	private Action fermerAction;
	private Action sauverAction;
	private Action resetAction;
	private Action ouvrirAction;
	private KeyListener keyListener;
	
	public DonneesPersonelles() {
		super();
		this.contactService = new ContactService();
	}
	
	private void initAction() {
		fermerAction = new FermerAction();
		sauverAction = new SauverAction();
		resetAction = new ResetAction();
		ouvrirAction = new OuvrirAction();
		
		// Conservé pour garder un exemple de code
		keyListener = new KeyListener() {
			
			private boolean isCtrlPressed;
			private boolean isOPressed;
			private boolean isSPressed;
			private boolean isQPressed;
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_CONTROL:
					isCtrlPressed = true;
					break;
				case KeyEvent.VK_O:
					isOPressed = true;
					break;
				case KeyEvent.VK_S:
					isSPressed = true;
					break;
				case KeyEvent.VK_Q:
					isQPressed = true;
					break;
				}
				this.doAction();
			}
			
			@Override
			public void keyReleased(KeyEvent e) { 
				switch(e.getKeyCode()) {
				case KeyEvent.VK_CONTROL:
					isCtrlPressed = false;
					break;
				case KeyEvent.VK_O:
					isOPressed = false;
					break;
				case KeyEvent.VK_S:
					isSPressed = false;
					break;
				case KeyEvent.VK_Q:
					isQPressed = false;
					break;
				}
			}
			
			private void doAction() {
				if(this.isCtrlPressed) {
					if(this.isQPressed) {
						fermerAction.actionPerformed(null);
					}
					else if(this.isSPressed) {
						sauverAction.actionPerformed(null);
					}
					else if(this.isOPressed){
						//TODO 
					}
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) { }
		};
		
		
	}
	
	private void initField() {
		menuBar = new JMenuBar();
		actionMenu = new JMenu("Action");
		ouvrirMenu = new JMenuItem(ouvrirAction);
		sauverMenu = new JMenuItem(sauverAction);
		reinitialiserMenu = new JMenuItem(resetAction);
		fermerMenu = new JMenuItem(fermerAction);
		
		menuBar.add(actionMenu);
		actionMenu.add(ouvrirMenu);
		actionMenu.add(sauverMenu);
		actionMenu.add(reinitialiserMenu);
		actionMenu.add(fermerMenu);
		
		titreField = new JComboBox<String>(new String[] { "Madame", "Monsieur", "Docteur", "Professeur" });
		rueField = new JTextArea(5, 10);
		dateNaissanceField = new JDatePicker(Calendar.getInstance());
		
		nomField = new JTextField();
		prenomField = new JTextField();
		mailField = new JTextField();
		telField = new JTextField();
		codePostalField = new JTextField();
		villeField = new JTextField();
		paysField = new JTextField();
		
		boutonAnnuler = new JButton(fermerAction);
		
		boutonSauver = new JButton("Sauver");
		boutonSauver.addActionListener(sauverAction);
	}
	
	@Override
	protected void frameInit() {
		super.frameInit();
		this.initAction();
		this.initField();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//paysField.addKeyListener(keyListener);
		this.setTitle("Données Personelles");
		this.getContentPane().setLayout(new GridBagLayout());
		
		this.setJMenuBar(menuBar);
		
		int rowIndex = 0;
		addRow(rowIndex++, "Titre", titreField);
		addRow(rowIndex++, "Nom", nomField);
		addRow(rowIndex++, "Prénom", prenomField);
		addRow(rowIndex++, "Date naissance", dateNaissanceField);
		addRow(rowIndex++, "Email", mailField);
		addRow(rowIndex++, "Téléphone", telField);
		addRow(rowIndex++, "Rue", new JScrollPane(rueField));
		addRow(rowIndex++, "Code postal", codePostalField);
		addRow(rowIndex++, "Ville", villeField);
		addRow(rowIndex++, "Pays", paysField);
		addButtons(rowIndex++, boutonSauver, boutonAnnuler);
		this.pack();
		this.setResizable(false);
	}

	private void addRow(int rowIndex, String titre, JComponent component) {
		// création des contraintes de positionnement
		GridBagConstraints cst = new GridBagConstraints();
		// le composant doit occuper tout l'espace horizontal de sa case
		cst.fill = GridBagConstraints.HORIZONTAL;
		// le composant doit être aligné sur le haut de la case
		cst.anchor = GridBagConstraints.NORTH;
		// on définit la marge en pixels pour le haut, la gauche, le bas et la droite
		cst.insets = new Insets(5, 20, 5, 20);
		// on définit la position verticale
		cst.gridy = rowIndex;
		// on définit la position horizontale
		cst.gridx = 0;
		// poids relatif à l'horizontal
		cst.weightx = .3;

		JLabel label = new JLabel(titre);
		label.setLabelFor(component);
		this.add(label, cst);

		// on définit la position horizontale
		cst.gridx = 1;
		// poids relatif à l'horizontal
		cst.weightx = .7;
		this.add(component, cst);
	}

	private void addButtons(int rowIndex, JButton... buttons) {
		JPanel panel = new JPanel();
		for (JButton button : buttons) {
			panel.add(button);
		}
		// création des contraintes de positionnement
		GridBagConstraints cst = new GridBagConstraints();
		// on définit la marge en pixels pour le haut, la gauche, le bas et la droite
		cst.insets = new Insets(5, 10, 0, 0);
		// le composant doit occuper tout l'espace horizontal de sa case
		cst.fill = GridBagConstraints.HORIZONTAL;
		// on définit la position verticale
		cst.gridy = rowIndex;
		// on définit la position horizontale
		cst.gridx = 0;
		// le composant s'étend à l'horizontal sur deux cases de la grille
		cst.gridwidth = 2;
		this.add(panel, cst);
	}

	@Override
	public void close() {
		setVisible(false); // évite les bugs graphiques
		dispose();
	}
	
	private Contact creerContact() {
		Calendar selectedValue = (Calendar) dateNaissanceField.getModel().getValue();
		Date selectedDate = selectedValue.getTime();
		return new Contact(
				nomField.getText(), 
				prenomField.getText(),
				selectedDate,
				mailField.getText(), 
				telField.getText(), 
				rueField.getText(), 
				codePostalField.getText(), 
				villeField.getText(), 
				paysField.getText());
	}
	
	private void chargerContact(Contact c) {
		nomField.setText(c.getNom());
		prenomField.setText(c.getPrenom());
		mailField.setText(c.getMail());
		telField.setText(c.getTelephone());
		rueField.setText(c.getRue());
		codePostalField.setText(c.getCodePostal());
		villeField.setText(c.getVille());
		paysField.setText(c.getPays());
		dateNaissanceField.getModel().setDate(
				c.getDateNaissance().getYear(),
				c.getDateNaissance().getMonth(), 
				c.getDateNaissance().getDay());
	}
	
	private void resetForm() {
		JTextField[] fields = {prenomField, nomField, mailField, telField, codePostalField, villeField, paysField};
		for(JTextField f : fields) {
			f.setText("");
		}
		rueField.setText("");
		dateNaissanceField.getModel().setValue(null);
	}
	
	private String openFileChooser() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    fileChooser.setMultiSelectionEnabled(false);
	    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Fichiers vcard (.vcf)",".vcf"));
	    int choix = fileChooser.showOpenDialog(null);
	    return choix != JFileChooser.APPROVE_OPTION ? null : fileChooser.getSelectedFile().getAbsolutePath();
	}
	
	private class FermerAction extends AbstractAction {

	    public FermerAction() {
	    	super("Fermer");
	    	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
	    }
		
		@Override 
		public void actionPerformed(ActionEvent e) { 
			close();
		}
	}
	
	private class ResetAction extends AbstractAction {

	    public ResetAction() {
	    	super("Réinitialiser");
	    }
		
		@Override 
		public void actionPerformed(ActionEvent e) { 
			resetForm();
		}
	}
	
	private class SauverAction extends AbstractAction {

	    public SauverAction() {
	    	super("Sauver");
	    	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
	    }
			
		@Override 
		public void actionPerformed(ActionEvent e) { 
			
			try {
				Contact c = creerContact();
				String filename = openFileChooser();
				contactService.sauver(c, filename);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
	
	private class OuvrirAction extends AbstractAction {

	    public OuvrirAction() {
	    	super("Ouvrir");
	    	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
	    }
			
		@Override 
		public void actionPerformed(ActionEvent e) { 
			
			try {
				String filename = openFileChooser();
				Contact c = contactService.getContact(filename);
				chargerContact(c);
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}
