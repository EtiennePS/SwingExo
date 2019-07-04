package com.cgi.udev2.swing.exo.model;

import java.awt.Graphics;

public class Cercle extends FormeReguliere {
	@Override
	public void draw(Graphics g) {
		g.fillOval(getX(), getY(), getLength(), getLength());
	}
}
