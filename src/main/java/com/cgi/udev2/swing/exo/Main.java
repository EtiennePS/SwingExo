package com.cgi.udev2.swing.exo;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.cgi.udev2.swing.exo.presentation.DonneesPersonelles;
import com.cgi.udev2.swing.exo.presentation.EditeurTexte;
import com.cgi.udev2.swing.exo.presentation.ExempleListener;
import com.cgi.udev2.swing.exo.presentation.ExempleMenu;
import com.cgi.udev2.swing.exo.presentation.ExempleOptionPane;
import com.cgi.udev2.swing.exo.presentation.MondrianFrame;

public class Main {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		int xCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().width/2;
		
		JFrame p = new DonneesPersonelles();		
		ajouterIcone(p, "contact.png");
		p.setLocationRelativeTo(null);
		p.setLocation((int) (xCenter - p.getSize().getWidth()),(int) p.getLocation().getY());
		p.setVisible(true);
		
		JFrame m = new MondrianFrame();
		ajouterIcone(m, "mondrian.png");
		m.setLocationRelativeTo(null);
		m.setLocation(xCenter, (int) m.getLocation().getY());
		m.setVisible(true);
	}
	
	private static void ajouterIcone(JFrame f, String path) {
		try {
			f.setIconImage(ImageIO.read(f.getClass().getClassLoader().getResource(path)));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static JFrame lancerFenetre(JFrame f, JFrame f2) {
		f.setLocationRelativeTo(f2);
		f.setVisible(true);
		return f;
	}
	
	private static JFrame lancerFenetre(JFrame f) {
		return lancerFenetre(f, null);
	}
	
	private static JFrame donneesPersonelles() {
		return lancerFenetre(new DonneesPersonelles());
	}
	
	private static JFrame mondrian() {
		return lancerFenetre(new MondrianFrame());
	}
	
	private static void exempleListener() {
		lancerFenetre(new ExempleListener());  
	}
	
	private static void exempleMenu() {
		lancerFenetre(new ExempleMenu());
	}
	
	private static void exempleOptionPane() {
		lancerFenetre(new ExempleOptionPane());
	}
	
	private static void exempleEditeurTexte() {
		lancerFenetre(new EditeurTexte());
	}
}
