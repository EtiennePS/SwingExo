package com.cgi.udev2.swing.exo.model;

import java.awt.Graphics;

public class Rectangle extends Forme {

	@Override
	public void draw(Graphics g) {
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
}
