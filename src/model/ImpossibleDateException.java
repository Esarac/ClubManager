package model;

import java.lang.Exception;

public class ImpossibleDateException extends Exception{

	private static final long serialVersionUID = 1L;

	public ImpossibleDateException(){
		super("It's not a real date! Date:1/1/1000");
	}
	
}
