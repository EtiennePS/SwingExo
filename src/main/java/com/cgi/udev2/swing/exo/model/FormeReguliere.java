package com.cgi.udev2.swing.exo.model;

public abstract class FormeReguliere extends Forme {
	
	protected int getLength() {
		return Math.min(getWidth(), getHeight());
	}
	
	@Override
	protected int getX() {
		if(startX <= endX) {
			return startX;
		}
		else {
			return endX + (getWidth() - getLength());
		}
	}
	
	@Override
	protected int getY() { 
		if(startY <= endY) {
			return startY;
		}
		else {
			return endY + (getHeight() - getLength());
		}
	}
	
}
