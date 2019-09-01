package model;

import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		
		saveClub();
		loadOwners();
		
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
				FileOutputStream file=new FileOutputStream("info/"+id+"/"+owners.get(i).getId()+".ea");
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
	
	//###
	public String toString(){
		String toString="[id:"+id+" name:"+name+" creationDate:"+creationDate+" petType:"+petType+"]";
		return toString;
	}
	
}