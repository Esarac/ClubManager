package model;

import java.util.ArrayList;

public class Owner{
	
	//Attributes
	private String id;
	private String name;
	private String lastName;
	private String birthdate;
	private String favoritePetType;
	private ArrayList<Pet> pets;
	
	//Constructor
	public Owner(String id, String name, String lastName, String birthdate, String favoritePetType){
		
		this.id=id;
		this.name=name;
		this.lastName=lastName;
		this.birthdate=birthdate;
		this.favoritePetType=favoritePetType;
		
		this.pets=new ArrayList<Pet>();
		
	}
	
	//Adds
	public String addPet(String id, String name, String birthdate, char gender, String type){
		
		String message="Se ha agregado la mascota \""+name+"\".";
		boolean exist=false;
		
		for(int i=0; (i<pets.size()) && !exist; i++){
			if(pets.get(i).getName().equals(name)){
				message="La mascota con nombre \""+name+"\" ya existe.";
				exist=true;
			}
		}
		
		if(!exist){
			pets.add(new Pet(id, name, birthdate, gender, type));
		}
		
		return message;
		
	}
	
	//Gets
	public String getId(){
		
		return id;
		
	}
	
}