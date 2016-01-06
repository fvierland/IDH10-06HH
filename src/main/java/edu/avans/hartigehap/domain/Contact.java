package edu.avans.hartigehap.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Contact extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "{validation.firstname.NotEmpty.message}")
    @Size(min = 3, max = 60, message = "{validation.firstname.Size.message}")
    private String firstName;

    @NotEmpty(message = "{validation.lastname.NotEmpty.message}")
    @Size(min = 1, max = 40, message = "{validation.lastname.Size.message}")
    private String lastName;

    @NotEmpty(message = "{validation.email.NotEmpty.message}")
    @Email
    private String email;

    private boolean gender; // 0 = man, 1 = vrouw
    
	public Contact(String firstName, String lastName, String email, boolean gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }
	
	 public void updateEditableFields(Contact contact) {
	        firstName = contact.firstName;
	        lastName = contact.lastName;
	        email = contact.email;
	        gender = contact.gender;
	    }
	
	public String getFullName(){
		String fullNameString = "";
		if (firstName != null && lastName != null){
			fullNameString = firstName + " " + lastName;
		}
		return fullNameString;
	}
}
