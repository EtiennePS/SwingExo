package com.cgi.udev2.swing.exo.presentation;


import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.URI;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class ExempleMenu extends JFrame {

  private Action exempleAction;

  private class ExempleAction extends AbstractAction {

    public ExempleAction() {
      super("Java", UIManager.getIcon("FileView.fileIcon"));
      putValue(SHORT_DESCRIPTION, "Cliquez pour en savoir plus sur Java");
      putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        // on ouvre la page Web dans le navigateur par défaut
        Desktop.getDesktop().browse(new URI("https://fr.wikipedia.org/wiki/Java_(langage)"));
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }

  }

  @Override
  protected void frameInit() {
    super.frameInit();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setTitle("Exemple Menus");

    this.exempleAction = new ExempleAction();

    this.setJMenuBar(new JMenuBar());
    this.getJMenuBar().add(createMenu());

    JPanel panel = new JPanel();
    panel.add(new JButton(exempleAction));
    this.add(panel);

    this.setSize(500, 300);
  }

  private JMenu createMenu() {
    JMenu menu = new JMenu("Menu");
    menu.add(new JMenuItem(exempleAction));
    JCheckBoxMenuItem checkBox = new JCheckBoxMenuItem("Activer", true);
    checkBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        exempleAction.setEnabled(checkBox.getState());
      }
    });
    menu.add(checkBox);
    menu.addSeparator();
    menu.add(new JMenuItem("Fermer")).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        ExempleMenu.this.dispose();
      }
    });
    return menu;
  }

}