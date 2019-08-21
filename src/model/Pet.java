package model;

public class Pet{
	
	//Constants
	public static final char MALE='M';
	public static final char FEMALE='F';
	
	//Attributes
	private String id;
	private String name;
	private String birthdate;
	private char gender;
	private String type;
	
	//Constructor
	public Pet(String id, String name, String birthdate, char gender, String type){
		
		this.id=id;
		this.name=name;
		this.birthdate=birthdate;
		this.gender=gender;
		this.type=type;
		
	}
	
	//Gets
	public String getName(){
		
		return name;
		
	}
	
}