package com.cgi.udev2.swing.exo.presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class EditeurTexte extends JFrame {

  private JEditorPane editor;

  @Override
  protected void frameInit() {
    super.frameInit();
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setTitle("Simple éditeur de texte");

    this.setJMenuBar(new JMenuBar());
    this.getJMenuBar().add(createMenu());

    editor = new JEditorPane();
    editor.setEditable(false);
    this.add(new JScrollPane(editor));
    this.setSize(800, 600);;
  }

  private JMenu createMenu() {
    JMenu menu = new JMenu("Fichier");
    menu.add(new JMenuItem("Ouvrir...")).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        open();
      }
    });
    menu.addSeparator();
    menu.add(new JMenuItem("Fermer")).addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dispose();
      }
    });
    return menu;
  }

  private void open() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.setMultiSelectionEnabled(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Fichiers texte (txt, html, rtf)",
                                                                   "txt", "html", "xhtml", "rtf"));
    int choix = fileChooser.showOpenDialog(this);
    if (choix == JFileChooser.APPROVE_OPTION) {
      try {
        editor.setPage(fileChooser.getSelectedFile().toURI().toString());
      } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Une erreur est survenue :\n" + e.getMessage(),
                                      "Erreur", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

}