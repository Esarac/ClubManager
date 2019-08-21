package model;

import java.util.ArrayList;

public class ClubManager{
	
	//Attributes
	private ArrayList<Club> clubs;
	
	//Constructor
	public ClubManager(){
		
		this.clubs=new ArrayList<Club>();
		
	}
	
	//Adds
	public String addClub(String id, String name, String creationDate, String petType){
		
		String message="Se ha agregado el club \""+name+"\".";
		boolean exist=false;
		
		for(int i=0; (i<clubs.size()) && !exist; i++){//?? colocar un try catch por si es null (Mala practica colocar un catch vacio?)
			if(clubs.get(i).getId().equals(id)){
				message="El club con id \""+id+"\" ya existe.";
				exist=true;
			}
			else if(clubs.get(i).getName().equals(name)){
				message="El club con nombre \""+name+"\" ya existe.";
				exist=true;
			}
		}
		
		if(!exist){
			clubs.add(new Club(id, name, creationDate, petType));//?? Mala practica colocar el new de una?
		}
		
		return message;
		
	}
	
	public String addOwner(String clubId, String id, String name, String lastName, String birthdate, String favoritePetType){
		
		String message="El club con id \""+clubId+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<clubs.size()) && !found; i++){
			if(clubs.get(i).getId().equals(clubId)){
				message=clubs.get(i).addOwner(id, name, lastName, birthdate, favoritePetType);
			}
		}
		
		return message;
		
	}
	
	public String addPet(String clubId, String ownerId, String id, String name, String birthdate, char gender, String type){
		
		String message="El club con id \""+clubId+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<clubs.size()) && !found; i++){
			if(clubs.get(i).getId().equals(clubId)){
				message=clubs.get(i).addPet(ownerId, id, name, birthdate, gender, type);
			}
		}
		
		return message;
		
	}
	
}