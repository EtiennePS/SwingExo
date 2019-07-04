package com.cgi.udev2.swing.exo.presentation;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import com.cgi.udev2.swing.exo.model.FormeEnum;


public class MondrianFrame extends JFrame {
	
	JMondrianPanel mondrian;
	
	JMenuBar menuBar;
	JMenu actionMenu;
	JMenuItem annulerMenu;
	JMenuItem couleurMenu;
	JMenu formeMenu;
	JCheckBoxMenuItem carreMenu;
	JCheckBoxMenuItem rectangleMenu;
	JCheckBoxMenuItem cercleMenu;
	JCheckBoxMenuItem ovalMenu;
	
	Action annulerAction;
	Action carreAction;
	Action rectangleAction;
	Action cercleAction;
	Action ovaleAction;
	Action couleurAction;
	
	private void actionInit() {
		annulerAction = new AnnulerAction();
		couleurAction = new CouleurAction();
		carreAction = new CarreAction();
		rectangleAction = new RectangleAction();
		cercleAction = new CercleAction();
		ovaleAction = new OvaleAction();
	}
	
	private void menuInit() {
		menuBar = new JMenuBar();
		actionMenu = new JMenu("Action");
		annulerMenu = new JMenuItem(annulerAction);
		couleurMenu = new JMenuItem(couleurAction);
		formeMenu = new JMenu("Forme");
		carreMenu = new JCheckBoxMenuItem(carreAction);
		rectangleMenu = new JCheckBoxMenuItem(rectangleAction);
		cercleMenu = new JCheckBoxMenuItem(cercleAction);
		ovalMenu = new JCheckBoxMenuItem(ovaleAction);
			
		menuBar.add(actionMenu);
		menuBar.add(formeMenu);
		actionMenu.add(annulerMenu);
		actionMenu.add(couleurMenu);
		formeMenu.add(carreMenu);
		formeMenu.add(rectangleMenu);
		formeMenu.add(cercleMenu);
		formeMenu.add(ovalMenu);
	}
	
	@Override
	protected void frameInit() {
		super.frameInit();
		mondrian = new JMondrianPanel();
		actionInit();
		menuInit();
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setTitle("Mondrian");
	    this.setJMenuBar(menuBar);
	    this.add(mondrian);
	    this.pack();
	    this.checkMenuForm();
	}
	
	private void checkMenuForm() {
		carreMenu.setState(false);
		cercleMenu.setState(false);
		rectangleMenu.setState(false);
		ovalMenu.setState(false);
		
		switch (mondrian.getForme()) {
		case CARRE:
			carreMenu.setState(true);
			break;
		case CERCLE: 
			carreMenu.setState(true);
			break;
		case OVALE:
			ovalMenu.setState(true);
			break;
		case RECTANGLE:
		default:
			rectangleMenu.setState(true);
			break;
		}
	}
	
	private class AnnulerAction extends AbstractAction {

	    public AnnulerAction() {
	    	super("Annuler");
	    	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
	    }
		
		@Override 
		public void actionPerformed(ActionEvent e) { 
			mondrian.removeLastForme();
		}
	}
	
	private class CouleurAction extends AbstractAction {

	    public CouleurAction() {
	    	super("Couleur");
	    	putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
	    }
		
		@Override 
		public void actionPerformed(ActionEvent e) { 
			Color newColor = JColorChooser.showDialog(null, "Choose a color", mondrian.getCurrentColor());
            mondrian.setCurrentColor(newColor);
		}
	}
	
	private class CarreAction extends AbstractAction {
	    public CarreAction() { super("Carre"); }
		@Override public void actionPerformed(ActionEvent e) { mondrian.setForme(FormeEnum.CARRE); checkMenuForm(); }
	}
	
	private class RectangleAction extends AbstractAction {
	    public RectangleAction() { super("Rectangle"); }
		@Override public void actionPerformed(ActionEvent e) { mondrian.setForme(FormeEnum.RECTANGLE); checkMenuForm(); }
	}
	
	private class CercleAction extends AbstractAction {
	    public CercleAction() { super("Cercle"); }
		@Override public void actionPerformed(ActionEvent e) { mondrian.setForme(FormeEnum.CERCLE); checkMenuForm(); }
	}
	
	private class OvaleAction extends AbstractAction {
	    public OvaleAction() { super("Ovale"); }
		@Override public void actionPerformed(ActionEvent e) { mondrian.setForme(FormeEnum.OVALE); checkMenuForm(); }
	}
}
