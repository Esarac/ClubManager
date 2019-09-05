package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Club implements Comparable<Club>, Comparator<Club>{
	
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
		
		saveClub();
		loadOwners();
		
	}
	
	//Show
	public String showOwnersReport(int type){
		
		String report="Duenos ordenados por ";
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
				report+="apellido:";
				lastNameSort();
			break;
			case 4:
				
			break;
			case 5:
				report+="tipo de mascota favorita:";
				favoritePetTypeSort();
			break;
			case 6:
				report+="cantidad de mascotas:";
				petQuantitySort();
			break;
			default:
				okay=false;
				report="Metodo de organizacion invalido.";
			break;
		}
		if(okay){
			for(int i=0; i<owners.size(); i++){
				report+="\n -"+owners.get(i);
			}
		}
		return report;
		
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
			saveOwners();
		}
		
		return message;
		
	}
	
	//Delete
	public String deleteOwnerId(String id) {
		String message="El dueno con id \""+id+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<owners.size()) && !found; i++){
			if(owners.get(i).getId().equals(id)){
				deleteOwnerFile(id);
				owners.remove(i);
				message="Eliminaste el dueno con id \""+id+"\".";
				found=true;
			}
		}
		
		return message;
	}
	
	public String deleteOwnerName(String name) {
		String message="El dueno con nombre \""+name+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<owners.size()) && !found; i++){
			if(owners.get(i).getName().equals(name)){
				String id=owners.get(i).getId();
				deleteOwnerFile(id);
				owners.remove(i);
				message="Eliminaste el dueno con nombre \""+name+"\".";
				found=true;
			}
		}
		
		return message;
	}
	
	private void deleteOwnerFile(String id){
		
		File file=new File("info/"+this.id+"/"+id+Owner.FILE_TYPE);
		file.delete();
		
	}
	
	//Sorting
	public void idSort(){
		
		for(int i=0; i<owners.size(); i++){
			for(int j=0; j<owners.size()-(i+1); j++){
				if(owners.get(j+1).compareTo(owners.get(j))<0){
					Owner actual=owners.get(j);
					owners.set(j, owners.get(j+1));
					owners.set(j+1, actual);
				}
			}
		}
		
	}
	
	public void nameSort(){
		
		for(int i=0; i<owners.size(); i++){
			for(int j=0; j<owners.size()-(i+1); j++){
				if(owners.get(j+1).compare(owners.get(j+1), owners.get(j))<0){
					Owner actual=owners.get(j);
					owners.set(j, owners.get(j+1));
					owners.set(j+1, actual);
				}
			}
		}
		
	}
	
	public void lastNameSort(){
		
		for(int i=0; i<owners.size(); i++){
			for(int j=0; j<owners.size()-(i+1); j++){
				if(owners.get(j+1).compareLastName(owners.get(j))<0){
					Owner actual=owners.get(j);
					owners.set(j, owners.get(j+1));
					owners.set(j+1, actual);
				}
			}
		}
		
	}
	
	public void favoritePetTypeSort(){
		
		for(int i=0; i<owners.size(); i++){
			for(int j=0; j<owners.size()-(i+1); j++){
				if(owners.get(j+1).compareFavoritePetType(owners.get(j))<0){
					Owner actual=owners.get(j);
					owners.set(j, owners.get(j+1));
					owners.set(j+1, actual);
				}
			}
		}
		
	}
	
	public void petQuantitySort(){
		
		for(int i=0; i<owners.size(); i++){
			for(int j=0; j<owners.size()-(i+1); j++){
				if(owners.get(j+1).comparePetQuantity(owners.get(j))<0){
					Owner actual=owners.get(j);
					owners.set(j, owners.get(j+1));
					owners.set(j+1, actual);
				}
			}
		}
		
	}
	
	//Save
	public void saveClub(){//Se puede hacer en un solo archivo
		try{//Quito el try cacth (Nunca me va a dar error)
			File folder=new File("info//"+id+"//");
			folder.mkdir();
			File file=new File("info/"+id+"/"+id+".txt");
			PrintWriter writer=new PrintWriter(file);
			writer.append(id+"\r\n"+name+"\r\n"+creationDate+"\r\n"+petType);
			writer.close();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}
	
	public void saveOwners(){
		try {
			for(int i=0; i<owners.size(); i++){
				FileOutputStream file=new FileOutputStream("info/"+id+"/"+owners.get(i).getId()+Owner.FILE_TYPE);
				ObjectOutputStream creator=new ObjectOutputStream(file);
				creator.writeObject(owners.get(i));
				creator.close();
			}
		}
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	//Load
	public void loadOwners(){
		try{
			File folder=new File("info/"+id+"/");
			String[] owners=folder.list();
			for(int i=0; i<owners.length; i++){
				if(!owners[i].equals(id+".txt")){
					FileInputStream owner=new FileInputStream("info/"+id+"/"+owners[i]);
					ObjectInputStream creator=new ObjectInputStream(owner);
					this.owners.add((Owner)creator.readObject());
					creator.close();
				}
			}
		}
		catch(FileNotFoundException e){e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (ClassNotFoundException e) {e.printStackTrace();}

	}
	
	//Compare
	public int compareTo(Club club) {//Id
		
		int delta=id.compareTo(club.getId());
		return delta;
		
	}
	
	public int compare(Club club1, Club club2) {//Name
		
		int delta=club1.getName().compareTo(club2.getName());
		return delta;
		
	}
	
	public int comparePetType(Club club){
		
		int delta=petType.compareTo(club.getPetType());
		return delta;
		
	}
	
	public int compareOwnerQuantity(Club club){
		
		int delta=owners.size()-club.getOwners().size();
		return delta;
		
	}
	
	//Gets
	public String getId(){
		
		return id;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public String getPetType(){
		
		return petType;
		
	}
	
	public ArrayList<Owner> getOwners(){
		
		return owners;
		
	}
	
	//+
	public String toString(){
		String toString="[id:"+id+" name:"+name+" creationDate:"+creationDate+" petType:"+petType+"]";
		return toString;
	}
	
}