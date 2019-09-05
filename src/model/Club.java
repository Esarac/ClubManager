package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Club implements Comparable<Club>, Comparator<Club>{
	
	//Constant
	public static final String FILE_TYPE=".al";
	
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
		loadOwners();
		
	}
	
	//Add
	public String addOwner(String id, String name, String lastName, String birthdate, String favoritePetType){//Tambien verificar que no tengan el mismo nombre???
		
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
				owners.remove(i);
				saveOwners();
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
				owners.remove(i);
				saveOwners();
				message="Eliminaste el dueno con nombre \""+name+"\".";
				found=true;
			}
		}
		
		return message;
		
	}
	
	//Save
	public void saveClub(){//[File]
		
		try{
			File clubs=new File(ClubManager.CLUBS_PATH);
			String text=ClubManager.readClubs();
			text+=id+","+name+","+creationDate+","+petType;
			PrintWriter writer=new PrintWriter(clubs);
			writer.append(text);
			writer.close();
		}
		catch (IOException e) {e.printStackTrace();}
		
	}
	
	public void saveOwners(){//[File]
		
		try {
			FileOutputStream file=new FileOutputStream("info/"+id+ClubManager.FILE_TYPE);
			ObjectOutputStream creator=new ObjectOutputStream(file);
			creator.writeObject(owners);
			creator.close();
		}
		catch (IOException e) {e.printStackTrace();}
		
	}
	
	//Load
	private void loadOwners(){//[File]
		
		try{
			FileInputStream owners=new FileInputStream("info/"+id+FILE_TYPE);
			ObjectInputStream creator=new ObjectInputStream(owners);
			this.owners=(ArrayList<Owner>)creator.readObject();//Trate de hacer un instanceof???
			creator.close();
		}
		catch (IOException e) {saveOwners();} 
		catch (ClassNotFoundException e) {e.printStackTrace();}
		
	}
	
	//Show
	public String showOwnersReport(int type){//Call??//Cuando esta vacio lo muestro????
		
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
				report+="fecha de nacimiento:";
				birthdateSort();
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
	
	//Sort
	private void idSort(){
		
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
	
	private void nameSort(){
		
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
	
	private void lastNameSort(){
		
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
	
	private void birthdateSort(){
		
		for(int i=0; i<owners.size(); i++){
			for(int j=0; j<owners.size()-(i+1); j++){
				if(owners.get(j+1).compareBirthdate(owners.get(j))<0){
					Owner actual=owners.get(j);
					owners.set(j, owners.get(j+1));
					owners.set(j+1, actual);
				}
			}
		}
		
	}
	
	private void favoritePetTypeSort(){
		
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
	
	private void petQuantitySort(){
		
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
	
	//Compare
	public int compareTo(Club club) {//Id
		
		int delta=id.compareTo(club.getId());
		return delta;
		
	}
	
	public int compare(Club club1, Club club2) {//Name
		
		int delta=club1.getName().compareTo(club2.getName());
		return delta;
		
	}
	
	public int compareCreationDate(Club club){
		
		int delta=getCreationDate().compareTo(club.getCreationDate());
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
	
	//Get
	public String getId(){
		
		return id;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public String getPetType(){
		
		return petType;
		
	}
	
	public Date getCreationDate(){
		
		Date creationDate=null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {creationDate=format.parse(this.creationDate);}
		catch (ParseException e) {e.printStackTrace();}
		return creationDate;
		
	}
	
	public ArrayList<Owner> getOwners(){
		
		return owners;
		
	}
	
	public String toString(){
		
		String toString="[id:"+id+" name:"+name+" creationDate:"+creationDate+" petType:"+petType+"]";
		return toString;
		
	}
	
}