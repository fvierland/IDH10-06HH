package edu.avans.hartigehap.domain;

import java.lang.reflect.Array;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Getter
@Setter

public class Mail extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private String sender;
	private String senderAddress;
	
	private ArrayList<Contact> contacts;
	
	public Mail(String sender, String senderAddress, ArrayList<Contact> contacts) {
        this.sender = sender;
        this.senderAddress = senderAddress;
        this.contacts = contacts;
    }
}
