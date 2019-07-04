package com.cgi.udev2.swing.exo.presentation;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class ExempleListener extends JFrame {

  private class ExempleMouseListener implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
      printEvent(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      printEvent(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
      printEvent(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
      printEvent(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      printEvent(e);
    }

  }

  private class ExempleKeyListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
      printEvent(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
      printEvent(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
      printEvent(e);
    }

  }

  @Override
  protected void frameInit() {
    super.frameInit();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setTitle("Exemple listener");
    JComponent component = new JEditorPane();
    component.setPreferredSize(new Dimension(300, 300));
    component.addMouseListener(new ExempleMouseListener());
    component.addKeyListener(new ExempleKeyListener());
    this.add(component);
    this.pack();
  }

  private void printEvent(EventObject e) {
    System.out.println(e);
  }

}
