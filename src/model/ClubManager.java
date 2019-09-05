package model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ClubManager{
	
	//Constants
	public static final String FILE_TYPE=".al";
	public static final String CLUBS_PATH="info/clubs.txt";
	
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
			Club club=new Club(id, name, creationDate, petType);
			clubs.add(club);
			club.saveClub();
		}
		
		return message;
		
	}
	
	public String addOwner(String id, String name, String lastName, String birthdate, String favoritePetType){//[Call]
		
		String message=actualClub.addOwner(id, name, lastName, birthdate, favoritePetType);
		return message;
		
	}
	
	public String addPet(String id, String name, String birthdate, char gender, String type){//[Call]
		
		String message=actualOwner.addPet(id, name, birthdate, gender, type);
		return message;
		
	}
	
	//Delete
	private String deleteClubId(String id){
		
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
	
	private String deleteClubName(String name){
		
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
	
	private void deleteClubFile(String id){//[File]
		
		try{
			File clubs=new File(CLUBS_PATH);
			
			String newText="";
			String text=readClubs();
			String[] clubsData=text.split("\r");
			for(String clubData: clubsData){
				String[] data=clubData.split(",");
				if(!data[0].equals(id)){
					newText+=clubData+"\r";
				}
			}
			
			File[] als=(new File("info/")).listFiles();
			for(File al: als){
				if(al.getName().equals(id+FILE_TYPE)){
					al.delete();
				}
			}
			
			PrintWriter writer=new PrintWriter(clubs);
			writer.append(newText);
			writer.close();
		}
		catch(IOException e){e.printStackTrace();}
		
	}
	
	public String deleteClub(String info, int type){//[Call]
		
		String message="Tipo de busqueda invalido.";
		switch(type){
			case 1:
				message=deleteClubId(info);
			break;
			case 2:
				message=deleteClubName(info);
			break;
		}
		return message;
		
	}
	
	public String deleteOwner(String info, int type){//[Call]
		
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
	
	public String deletePet(String info, int type){//[Call]
		
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
	
	//Load
	private void loadClubs(){//[File]
		
		try{
			String text=readClubs();
			String[] clubsData=text.split("\r");
			for(String clubData: clubsData){
				String[] data=clubData.split(",");
				if(data.length!=1){
					clubs.add(new Club(data[0],data[1],data[2],data[3]));
				}
			}
		}
		catch(IOException e){e.printStackTrace();}
		catch(IndexOutOfBoundsException e){e.printStackTrace();}
		
	}
	
	//Read
	public static String readClubs() throws IOException{//[File]//Buena practica???
		
		File dir=new File("info//");
		dir.mkdir();
		File clubs=new File(ClubManager.CLUBS_PATH);
		clubs.createNewFile();
		FileReader fileReader=new FileReader(clubs);
		BufferedReader reader=new BufferedReader(fileReader);
		String actualLine;
		String text="";
		while((actualLine=reader.readLine())!=null){
			text+=actualLine+"\r";
		}
		reader.close();
		return text;
		
	}
	
	//Show
	public String showClubsReport(int type){//Call??//Cuando esta vacio lo muestro??
		
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
				
				report+="fecha de creacion:";
				creationDateSort();
				
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
	
	public String showOwnersReport(int type){//[Call]
		
		String message;
		try{message=actualClub.showOwnersReport(type);}
		catch(NullPointerException e){message="No estas posicionado en un club.";}
		return message;
		
	}
	
	public String showPetsReport(int type){//[Call]
		
		String message;
		try{message=actualOwner.showPetsReport(type);}
		catch(NullPointerException e){message="No estas posicionado en un dueno.";}
		return message;
		
	}
	
	//Sort
	private void idSort(){
		
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
	
	private void nameSort(){
		
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
	
	private void creationDateSort(){
		
		for(int i=0; i<clubs.size(); i++){
			Club min=clubs.get(i);
			int minPos=i;
			for(int j=i+1; j<clubs.size(); j++){
				if(clubs.get(j).compareCreationDate(min)<0){
					min=clubs.get(j);
					minPos=j;
				}
			}
			Club actual=clubs.get(i);
			clubs.set(i, min);
			clubs.set(minPos, actual);
		}
		
	}
	
	private void petTypeSort(){
		
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
	
	private void ownerQuantitySort(){
		
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
		
		this.actualOwner=null;
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
		
		try{actualClub.saveOwners();}
		catch(NullPointerException e){e.printStackTrace();}
		this.actualOwner=null;
		
	}
	
}