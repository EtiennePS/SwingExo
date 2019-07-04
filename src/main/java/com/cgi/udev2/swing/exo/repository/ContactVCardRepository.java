package com.cgi.udev2.swing.exo.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import com.cgi.udev2.swing.exo.entity.Contact;

public class ContactVCardRepository {
	
	private static final String DEFAULT_FILENAME = "vcard.vcf";
	
	public void sauver(Contact c, String filename) throws IOException {
		String vcard = contactToVcardV21(c);
		write(vcard, filename);
	}
	
	public Contact getContact(String filename) throws IOException {
		System.out.println(read(filename));
		//TODO Réellement parser le fichier
		Contact c = new Contact();
		c.setNom("Non implémenté");
		c.setPrenom("Non implémenté");
		c.setDateNaissance(new Date(1994, 2, 11));
		c.setMail("Non implémenté");
		c.setRue("Adresse mémoire");
		
		String s = c.toString();
		s = s.substring(s.length() - 9, s.length());
		c.setCodePostal(s);
		return c;
	}
	
	private String contactToVcardV21(Contact c) {
		StringBuffer sb = new StringBuffer();
		sb.append("BEGIN:VCARD").append("\n")
		.append("VERSION:2.1").append("\n")
		.append("FN:").append(c.getPrenom()).append(" ").append(c.getNom()).append("\n")
		.append("N:").append(c.getNom()).append(";").append(c.getPrenom()).append("\n")
		.append("ADR;WORK;PREF;QUOTED-PRINTABLE:;")
		.append(c.getVille()).append(" ").append(c.getCodePostal()).append("=")
		.append(c.getPays()).append(";").append(c.getRue()).append("\n")
		.append("LABEL;QUOTED-PRINTABLE;WORK;PREF:")
		.append(c.getRue()).append("=").append(c.getVille()).append(" ")
		.append(c.getCodePostal()).append("=").append(c.getPays()).append("\n")
		.append("TEL;CELL:").append(c.getTelephone()).append("\n")
		.append("EMAIL;INTERNET:").append(c.getMail()).append("\n")
		.append("UID:").append("\n")
		.append("END:VCARD").append("\n");
		
		return sb.toString();
	}
	
	private void write(String str, String filename) throws IOException {
		filename = filename == null ? DEFAULT_FILENAME : filename;
		BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
		writer.append('\n');
	    writer.append(str);
	    writer.close();
	}
	
	private String read(String filename) throws IOException {
		filename = filename == null ? DEFAULT_FILENAME : filename;
		byte[] data = Files.readAllBytes(Paths.get(filename));
		return new String(data);
	}

}
