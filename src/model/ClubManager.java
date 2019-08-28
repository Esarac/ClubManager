package model;

import java.util.ArrayList;

public class ClubManager{
	
	//Attributes
	private ArrayList<Club> clubs;
	private Club actualClub;
	private Owner actualOwner;
	
	//Constructor
	public ClubManager(){
		
		this.clubs=new ArrayList<Club>();
		
	}
	
	//Adds
	public String addClub(String id, String name, String creationDate, String petType){
		
		String message="Se ha agregado el club \""+name+"\".";
		boolean exist=false;
		
		for(int i=0; (i<clubs.size()) && !exist; i++){
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
			clubs.add(new Club(id, name, creationDate, petType));
		}
		
		return message;
		
	}
	
	public String addOwner(String id, String name, String lastName, String birthdate, String favoritePetType){
		
		String message=actualClub.addOwner(id, name, lastName, birthdate, favoritePetType);
		return message;
		
	}
	
	public String addPet(String id, String name, String birthdate, char gender, String type){
		
		String message=actualOwner.addPet(id, name, birthdate, gender, type);
		return message;
		
	}
	
	//Set
	public String setActualClub(String clubId){
		
		String message="El club con id \""+clubId+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<clubs.size()) && !found; i++){
			if(clubs.get(i).getId().equals(clubId)){
				this.actualClub=clubs.get(i);
				message="Estas posicionado en el club con id \""+clubId+"\".";
			}
		}
		
		return message;
		
	}
	
	public void setActualClubNull(){
		this.actualClub=null;
	}
	
	public String setActualOwner(String ownerId){//Este deberia repartirlo en las diferentes clases?
		
		String message="El dueno con id \""+ownerId+"\" no existe.";
		boolean found=false;
		try{
			ArrayList<Owner> owners=actualClub.getOwners();
			for(int i=0; (i<owners.size()) && !found; i++){
				if(owners.get(i).getId().equals(ownerId)){
					this.actualOwner=owners.get(i);
					message="Estas posicionado en el dueno con id \""+ownerId+"\".";
				}
			}
		}
		catch(NullPointerException e){
			message="No estas posicionado en un club.";
		}
		
		return message;
		
	}
	
	public void setActualOwnerNull(){
		this.actualOwner=null;
	}
	
	//Do
	public boolean inClub(){
		boolean exist=true;
		if(actualClub==null)
			exist=false;
		return exist;
	}
	
	public boolean inOwner(){
		boolean exist=true;
		if(actualOwner==null)
			exist=false;
		return exist;
	}
	
}