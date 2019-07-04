package com.cgi.udev2.swing.exo.entity;

import java.util.Date;

import com.cgi.udev2.swing.exo.exception.InvalidContactException;

public class Contact {

	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String mail;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String pays;
	
	public Contact() {
		mail = "";
		telephone = "";
		rue = "";
		codePostal = "";
		ville = "";
		pays = "";
	}
	
	public Contact(String nom, String prenom, Date dateNaissance, String mail, String telephone, 
			String rue, String codePostal, String ville, String pays) {
		setNom(nom);
		setPrenom(prenom);
		setDateNaissance(dateNaissance);
		setMail(mail);
		setTelephone(telephone);
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setPays(pays);
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		if(nom == null) {
			throw new InvalidContactException("Le nom doit être renseigné.");
		}
		else if(nom.length() <= 1 || nom.length() > 50) {
			throw new InvalidContactException("Le nom doit avoir entre 2 et 50 caractère.");
		}
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		if(prenom == null) {
			throw new InvalidContactException("Le prénom doit être renseigné.");
		}
		else if(prenom.length() <= 1 || prenom.length() > 50) {
			throw new InvalidContactException("Le prénom doit avoir entre 2 et 50 caractère.");
		}
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	
}
