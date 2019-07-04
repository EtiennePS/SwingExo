package com.cgi.udev2.swing.exo.model;

import java.awt.Graphics;

public class Ovale extends Forme {

	@Override
	public void draw(Graphics g) {
		g.fillOval(getX(), getY(), getWidth(), getHeight());
	}

}
