package com.cgi.udev2.swing.exo.model;

import java.awt.Graphics;

public class Carre extends FormeReguliere {

	@Override
	public void draw(Graphics g) {
		g.fillRect(getX(), getY(), getLength(), getLength());
	}

}
