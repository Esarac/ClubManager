package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Pet implements Serializable, Comparable<Pet>, Comparator<Pet>{
	
	//Constants
	private static final long serialVersionUID = -3133665233686332587L;
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
		this.type=type;
		
		if(gender==MALE || gender==FEMALE)
			this.gender=gender;
		else{
			try {throw new ImpossibleGenderException();}
			catch (ImpossibleGenderException e) {
				e.printStackTrace();
				this.gender=FEMALE;
			}
		}
		
		String[] date=birthdate.split("/");
		if(date.length==3)
			this.birthdate=birthdate;
		else{
			try {throw new ImpossibleDateException();}
			catch (ImpossibleDateException e) {
				e.printStackTrace();
				this.birthdate="1/1/1000";
			}
		}
		
	}
	
	//Compare
	public int compareTo(Pet pet) {//Id
		
		int delta=id.compareTo(pet.getId());
		return delta;
	}
	
	public int compare(Pet pet1, Pet pet2) {//Name
		
		int delta=pet1.getName().compareTo(pet2.getName());
		return delta;
		
	}
	
	public int compareBirthdate(Pet pet){
		
		int delta=getBirthdate().compareTo(pet.getBirthdate());
		return delta;
		
	}
	
	public int compareGender(Pet pet){
		
		int delta=((int)gender)-((int)pet.getGender());
		return delta;
		
	}
	
	public int compareType(Pet pet){
		
		int delta=type.compareTo(pet.getType());
		return delta;
		
	}
	
	//Get
	public String getId(){
		
		return id;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public Date getBirthdate(){
		
		Date birthdate=null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {birthdate=format.parse(this.birthdate);}
		catch (ParseException e) {birthdate=new Date();}
		return birthdate;
		
	}
	
	public char getGender(){
		
		return gender;
		
	}
	
	public String getType(){
		
		return type;
		
	}
	
	public String toString(){
		
		String toString="[id:"+id+" name:"+name+" birthdate:"+birthdate+" gender:";
		if(gender==MALE)toString+="Male";
		else if(gender==FEMALE)toString+="Female";
		else toString+="Undefined";
		toString+=" type:"+type+"]";
		return toString;
		
	}
	
}