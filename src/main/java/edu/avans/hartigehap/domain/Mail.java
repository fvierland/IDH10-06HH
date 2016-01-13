package edu.avans.hartigehap.domain;

import java.util.ArrayList;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Mail extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private String sender;
	private String senderAddress;
	
	private ArrayList<Contact> contact;
	
	public Mail(String sender, String senderAddress, ArrayList<Contact> contact ) {
        this.sender = sender;
        this.senderAddress = senderAddress;
        this.contact = contact;
    }
}