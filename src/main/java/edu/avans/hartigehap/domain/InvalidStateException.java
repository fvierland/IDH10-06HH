package edu.avans.hartigehap.domain;

@SuppressWarnings("serial")
public class InvalidStateException extends Exception{
	   public InvalidStateException(String message) {
	        super(message);
	    }
	}