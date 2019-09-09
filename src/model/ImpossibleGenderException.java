package model;

import java.lang.Exception;

public class ImpossibleGenderException extends Exception{

	private static final long serialVersionUID = 1L;

	public ImpossibleGenderException(){
		super("It's not a real gender! Gender:Female");
	}
	
}
