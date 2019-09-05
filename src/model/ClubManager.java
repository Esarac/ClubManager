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
	
	//Show
	public String showClubsReport(int type){
		
		String report="Clubes ordenados por ";
		boolean okay=true;
		switch(type){
			case 1:
				report+="id:";
				idSort();
			break;
			case 2:
				report+="nombre:";
				nameSort();
			break;
			case 3:
				
			break;
			case 4:
				report+="tipo de mascota:";
				petTypeSort();
			break;
			case 5:
				report+="cantidad de duenos:";
				ownerQuantitySort();
			break;
			default:
				okay=false;
				report="Metodo de organizacion invalido.";
			break;
		}
		if(okay){
			for(int i=0; i<clubs.size(); i++){
				report+="\n -"+clubs.get(i);
			}
		}
		return report;
		
	}
	
	public String showOwnersReport(int type){
		
		String message;
		try{message=actualClub.showOwnersReport(type);}
		catch(NullPointerException e){message="No estas posicionado en un club.";}
		return message;
		
	}
	
	public String showPetsReport(int type){
		
		String message;
		try{message=actualOwner.showPetsReport(type);}
		catch(NullPointerException e){message="No estas posicionado en un dueno.";}
		return message;
		
	}
	
	//Add
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
	
	//Delete
	public String deleteClubId(String id){
		String message="El club con id \""+id+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<clubs.size()) && !found; i++){
			if(clubs.get(i).getId().equals(id)){
				deleteClubFile(id);
				clubs.remove(i);
				message="Eliminaste el club con id \""+id+"\".";
				found=true;
			}
		}
		
		return message;
	}
	
	public String deleteClubName(String name){
		String message="El club con nombre \""+name+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<clubs.size()) && !found; i++){
			if(clubs.get(i).getName().equals(name)){
				String id=clubs.get(i).getId();
				deleteClubFile(id);
				clubs.remove(i);
				message="Eliminaste el club con nombre \""+name+"\".";
				found=true;
			}
		}
		
		return message;
	}
	
	private void deleteClubFile(String id){
		
		File info=new File("info/");
		String[] folders=info.list();
		
		boolean found=false;
		for(int i=0; (i<folders.length) && !found; i++){
			if(folders[i].equals(id)){
				File club=new File("info/"+folders[i]);
				
				String[] files=club.list();
				for(int j=0; j<files.length; j++){
					File file=new File("info/"+folders[i]+"/"+files[j]);
					file.delete();
				}
				
				club.delete();
				found=true;
			}
		}
		
	}
	
	public String deleteOwner(String info, int type){
		
		String message="Tipo de busqueda invalido.";
		switch(type){
			case 1:
				message=actualClub.deleteOwnerId(info);
			break;
			case 2:
				message=actualClub.deleteOwnerName(info);
			break;
		}
		return message;
		
	}
	
	public String deletePet(String info, int type){
		
		String message="Tipo de busqueda invalido.";
		switch(type){
			case 1:
				message=actualOwner.deletePetId(info);
			break;
			case 2:
				message=actualOwner.deletePetName(info);
			break;
		}
		return message;
		
	}
	
	//Sorting
	public void idSort(){
		
		for(int i=0; i<clubs.size(); i++){
			Club min=clubs.get(i);
			int minPos=i;
			for(int j=i+1; j<clubs.size(); j++){
				if(clubs.get(j).compareTo(min)<0){
					min=clubs.get(j);
					minPos=j;
				}
			}
			Club actual=clubs.get(i);
			clubs.set(i, min);
			clubs.set(minPos, actual);
		}
		
	}
	
	public void nameSort(){
		
		for(int i=0; i<clubs.size(); i++){
			Club min=clubs.get(i);
			int minPos=i;
			for(int j=i+1; j<clubs.size(); j++){
				if(clubs.get(j).compare(clubs.get(j), min)<0){
					min=clubs.get(j);
					minPos=j;
				}
			}
			Club actual=clubs.get(i);
			clubs.set(i, min);
			clubs.set(minPos, actual);
		}
		
	}
	
	public void petTypeSort(){
		
		for(int i=0; i<clubs.size(); i++){
			Club min=clubs.get(i);
			int minPos=i;
			for(int j=i+1; j<clubs.size(); j++){
				if(clubs.get(j).comparePetType(min)<0){
					min=clubs.get(j);
					minPos=j;
				}
			}
			Club actual=clubs.get(i);
			clubs.set(i, min);
			clubs.set(minPos, actual);
		}
		
	}
	
	public void ownerQuantitySort(){
		
		for(int i=0; i<clubs.size(); i++){
			Club min=clubs.get(i);
			int minPos=i;
			for(int j=i+1; j<clubs.size(); j++){
				if(clubs.get(j).compareOwnerQuantity(min)<0){
					min=clubs.get(j);
					minPos=j;
				}
			}
			Club actual=clubs.get(i);
			clubs.set(i, min);
			clubs.set(minPos, actual);
		}
		
	}
	
	//Check
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
	public String setActualClub(String id){
		
		idSort();
		String message="El club con id \""+id+"\" no existe.";
		boolean found=false;
		int start=0;
		int end=clubs.size()-1;
		
		while((start<=end) && !found){
			int midle=(start+end)/2;
			if(clubs.get(midle).getId().compareTo(id)==0){
				this.actualClub=clubs.get(midle);
				message="Estas posicionado en el club con id \""+id+"\".";
				found=true;
			}
			else if(clubs.get(midle).getId().compareTo(id)>0){
				end=midle-1;
			}
			else{
				start=midle+1;
			}
		}
		
		return message;
		
	}
	
	public void setActualClubNull(){
		this.actualClub=null;
	}
	
	public String setActualOwner(String id){//Este deberia repartirlo en las diferentes clases?
		
		String message="El dueno con id \""+id+"\" no existe.";
		try{
			ArrayList<Owner> owners=actualClub.getOwners();
			boolean found=false;
			int start=0;
			int end=owners.size()-1;
			
			while((start<=end) && !found){
				int midle=(start+end)/2;
				if(owners.get(midle).getId().compareTo(id)==0){
					this.actualOwner=owners.get(midle);
					message="Estas posicionado en el dueno con id \""+id+"\".";
					found=true;
				}
				else if(owners.get(midle).getId().compareTo(id)>0){
					end=midle-1;
				}
				else{
					start=midle+1;
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
	
}