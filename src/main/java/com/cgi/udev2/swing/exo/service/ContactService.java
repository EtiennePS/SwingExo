package com.cgi.udev2.swing.exo.service;

import java.io.IOException;

import com.cgi.udev2.swing.exo.entity.Contact;
import com.cgi.udev2.swing.exo.exception.InvalidContactException;
import com.cgi.udev2.swing.exo.repository.ContactVCardRepository;

public class ContactService {
	
	private ContactVCardRepository repository;
	
	public ContactService() {
		repository = new ContactVCardRepository();
	}
	
	public void sauver(Contact c, String filename) {
		if(c.getNom() == null) {
			throw new InvalidContactException("Le nom doit être renseigné.");
		}
		else if(c.getPrenom() == null) {
			throw new InvalidContactException("Le prénom doit être renseigné.");
		}
		
		try {
			repository.sauver(c, filename);
		}
		catch(IOException e) {
			throw new RuntimeException("Une erreur est survenu lors de l'enregistrement du contact.", e);
		}
	}
	
	public Contact getContact(String filename) {
		try {
			return this.repository.getContact(filename);
		}
		catch (IOException e) {
			throw new RuntimeException("Impossible de lire le fichier : " + filename);
		}
	}
}
