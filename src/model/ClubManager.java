package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ClubManager{
	
	//Attributes
	private Club actualClub;
	private Owner actualOwner;
	private ArrayList<Club> clubs;
	
	
	//Constructor
	public ClubManager(){
		
		this.actualClub=null;
		this.actualOwner=null;
		
		this.clubs=new ArrayList<Club>();
		loadClubs();
		
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
	
	//Load
	public void loadClubs(){
		try{//Buenas practicas
			File info=new File("info/");
			String[] folders=info.list();
			for(int i=0; i<folders.length; i++){
				File club=new File("info/"+folders[i]+"/"+folders[i]+".txt");
				
				FileReader fr=new FileReader(club);
				BufferedReader reader= new BufferedReader(fr);
				String[] variable=new String[4];
				for(int j=0; j<variable.length; j++){
					variable[j]=reader.readLine();
				}
				addClub(variable[0], variable[1], variable[2], variable[3]);
				reader.close();
				
			}
		}
		catch(FileNotFoundException e){e.printStackTrace();}
		catch(IOException e){e.printStackTrace();}
		
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
		actualClub.saveOwners();
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