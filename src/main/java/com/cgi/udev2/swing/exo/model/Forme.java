package com.cgi.udev2.swing.exo.model;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Forme {
	protected int startX;
	protected int startY;
	protected int endX;
	protected int endY;
	protected Color color;
	
	public Color getColor() { return color; }
	public void setColor(Color color) { this.color = color; }
	
	public void setStartX(int startX) { this.startX = startX; }
	public void setStartY(int startY) { this.startY = startY; }
	public void setEndX(int endX) { this.endX = endX; }
	public void setEndY(int endY) { this.endY = endY; }
	
	protected int getX() { return Math.min(startX, endX); }
	protected int getY() { return Math.min(startY, endY); }
	protected int getWidth() { return Math.max(startX, endX) - Math.min(startX, endX); }
	protected int getHeight() { return Math.max(startY, endY) - Math.min(startY, endY); }
	
	public abstract void draw(Graphics g);
}
