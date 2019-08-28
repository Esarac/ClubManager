package model;

import java.util.ArrayList;

public class Club{
	
	//Attributes
	private String id;
	private String name;
	private String creationDate;
	private String petType;
	private ArrayList<Owner> owners;
	
	//Constructor
	public Club(String id, String name, String creationDate, String petType){
		
		this.id=id;
		this.name=name;
		this.creationDate=creationDate;
		this.petType=petType;
		
		this.owners=new ArrayList<Owner>(); 
		
	}
	
	//Adds
	public String addOwner(String id, String name, String lastName, String birthdate, String favoritePetType){
		
		String message="Se ha agregado el dueno \""+name+"\".";
		boolean exist=false;
		
		for(int i=0; (i<owners.size()) && !exist; i++){
			if(owners.get(i).getId().equals(id)){
				message="El dueno con id \""+id+"\" ya existe.";
				exist=true;
			}
		}
		
		if(!exist){
			owners.add(new Owner(id, name, lastName, birthdate, favoritePetType));
		}
		
		return message;
		
	}
	
	//Gets
	public String getId(){
		
		return id;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public ArrayList<Owner> getOwners(){
		
		return owners;
		
	}
	
}