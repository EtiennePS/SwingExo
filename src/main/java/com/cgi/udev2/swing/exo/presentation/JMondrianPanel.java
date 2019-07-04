package com.cgi.udev2.swing.exo.presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import com.cgi.udev2.swing.exo.model.Carre;
import com.cgi.udev2.swing.exo.model.Cercle;
import com.cgi.udev2.swing.exo.model.Forme;
import com.cgi.udev2.swing.exo.model.FormeEnum;
import com.cgi.udev2.swing.exo.model.Ovale;
import com.cgi.udev2.swing.exo.model.Rectangle;

public class JMondrianPanel extends JComponent {

	private List<Forme> formes;
	private FormeEnum forme;
	private Color currentColor;

	public JMondrianPanel() {
		forme = FormeEnum.RECTANGLE;
		currentColor = Color.BLACK;
		formes = new ArrayList<Forme>();

		setBackground(Color.WHITE);
		MouseForme m = new MouseForme();
		this.addMouseListener(m);
		this.addMouseMotionListener(m);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Forme f : formes) {
			g.setColor(f.getColor());
			f.draw(g);
		}

	}

	public void removeLastForme() {
		this.formes.remove(formes.size() - 1);
		this.repaint();
	}

	public FormeEnum getForme() {
		return forme;
	}

	public void setForme(FormeEnum forme) {
		this.forme = forme;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}

	public void setCurrentColor(Color color) {
		this.currentColor = color;
	}

	private class MouseForme implements MouseListener, MouseMotionListener {

		private Forme currentForme;

		@Override
		public void mousePressed(MouseEvent e) {
			currentForme = this.createForme();
			currentForme.setStartX(e.getX());
			currentForme.setStartY(e.getY());
			formes.add(currentForme);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			currentForme.setEndX(e.getX());
			currentForme.setEndY(e.getY());
			repaint();
		}
		
		private Forme createForme() {
			Forme f;
			switch(forme) {
				case CARRE:
					f = new Carre();
					break;
				case CERCLE:
					f = new Cercle();
					break;
				case OVALE:
					f = new Ovale();
					break;
				case RECTANGLE:
				default:
					f = new Rectangle();
					break;
			}
			f.setColor(currentColor);
			return f;
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseReleased(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) { }
		
		@Override
		public void mouseClicked(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }

	}

}
