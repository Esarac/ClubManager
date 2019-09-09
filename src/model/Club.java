package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import model.ImpossibleDateException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Club implements Comparable<Club>, Comparator<Club>{
	
	//Constant
	public static final String[] OWNERS_ORDER={"Disordered", "Id", "Name", "Last Name", "Birthdate", "Favorite Pet Type", "Pets Quantity"};
	
	//Attributes
	private String id;
	private String name;
	private String creationDate;
	private String petType;
	private ArrayList<Owner> owners;
	private String order;//Code
	
	//Constructor
	public Club(String id, String name, String creationDate, String petType){
		
		this.id=id;
		this.name=name;
		this.petType=petType;
		
		this.owners=new ArrayList<Owner>();
		loadOwners();
		
		this.order=OWNERS_ORDER[0];
		
		String[] date=creationDate.split("/");
		if(date.length==3)
			this.creationDate=creationDate;
		else{
			try {throw new ImpossibleDateException();}
			catch (ImpossibleDateException e) {
				e.printStackTrace();
				this.creationDate="1/1/1000";
			}
		}
		
	}
	
	//Add
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
	public String saveClub(){//[File]
		
		String message="Se pudo guardar el club en el archivo.";
		
		try{
			File clubs=new File(ClubManager.CLUBS_PATH);
			String text=readClubs();
			text+=id+","+name+","+creationDate+","+petType;
			PrintWriter writer=new PrintWriter(clubs);
			writer.append(text);
			writer.close();
		}
		catch (IOException e) {message="No se encontro el archivo.";}
		
		
		return message;
		
	}
	
	public String saveOwners(){//[File]
		
		String message="Se pudo guardar los owners en el archivo.";
		
		try {
			FileOutputStream file=new FileOutputStream("info/"+id+ClubManager.FILE_TYPE);
			ObjectOutputStream creator=new ObjectOutputStream(file);
			creator.writeObject(owners);
			creator.close();
		}
		catch (IOException e) {message="No se encontro el archivo.";}
		
		return message;
		
	}
	
	//Load
	private String loadOwners(){//[File]
		
		String message="Se pudo cargar los owners del archivo.";
		
		try{
			FileInputStream owners=new FileInputStream("info/"+id+ClubManager.FILE_TYPE);
			ObjectInputStream creator=new ObjectInputStream(owners);
			this.owners=(ArrayList<Owner>)creator.readObject();
			creator.close();
		}
		catch (IOException e) {message="No se encotro el archivo."; saveOwners();} 
		catch (ClassNotFoundException e) {message="No se encontro la clase que se quiere cargar.";}
		
		return message;
		
	}
	
	//Read
	private String readClubs() throws IOException{//[File]
		
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
	public String showOwnersReport(int type){
		
		String report="Duenos ordenados por ";
		boolean okay=true;
		switch(type){
			case 1:
				report+="id:";
				if(!order.equals(OWNERS_ORDER[1]))
					idSort();
			break;
			case 2:
				report+="nombre:";
				if(!order.equals(OWNERS_ORDER[2]))
					nameSort();
			break;
			case 3:
				report+="apellido:";
				if(!order.equals(OWNERS_ORDER[3]))
					lastNameSort();
			break;
			case 4:
				report+="fecha de nacimiento:";
				if(!order.equals(OWNERS_ORDER[4]))
					birthdateSort();
			break;
			case 5:
				report+="tipo de mascota favorita:";
				if(!order.equals(OWNERS_ORDER[5]))
					favoritePetTypeSort();
			break;
			case 6:
				report+="cantidad de mascotas:";
				if(!order.equals(OWNERS_ORDER[6]))
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
		if(owners.size()==0){
			report+="\n No hay duenos.";
		}
		return report;
		
	}
	
	public String showOwnerSearch(String info, int type){//[Call]
		
		String message="Tipo de busqueda invalido.";
		switch(type){
			case 1:
				message=searchId(info);
			break;
			case 2:
				message=searchName(info);
			break;
			case 3:
				message=searchLastName(info);
			break;
			case 4:
				message=searchBirthdate(info);
			break;
			case 5:
				message=searchFavoritePetType(info);
			break;
		}
		return message;
		
	}
	
	//Sort (Bubble)
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
		this.order=OWNERS_ORDER[1];
		
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
		this.order=OWNERS_ORDER[2];
		
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
		this.order=OWNERS_ORDER[3];
		
	}
	
	public void birthdateSort(){
		
		for(int i=0; i<owners.size(); i++){
			for(int j=0; j<owners.size()-(i+1); j++){
				if(owners.get(j+1).compareBirthdate(owners.get(j))<0){
					Owner actual=owners.get(j);
					owners.set(j, owners.get(j+1));
					owners.set(j+1, actual);
				}
			}
		}
		this.order=OWNERS_ORDER[4];
		
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
		this.order=OWNERS_ORDER[5];
		
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
		this.order=OWNERS_ORDER[6];
		
	}
	
	//Search
	public String searchId(String id){
		
		String message="";
		Owner testOwner=new Owner(id,"","","1/1/1000","");
		
		//Binary
		if(!order.equals(OWNERS_ORDER[1]))
			idSort();
		boolean foundB=false;
		int start=0;
		int end=owners.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(owners.get(middle).compareTo(testOwner)==0){
				foundB=true;
			}
			else if(owners.get(middle).compareTo(testOwner)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		long endTimeB=System.nanoTime();
		long deltaTimeB=endTimeB-startTimeB;
		//...
		
		//Sequential
		boolean foundS=false;
		long startTimeS=System.nanoTime();
		for(int i=0; (i<owners.size()) && !foundS; i++){
			if(owners.get(i).compareTo(testOwner)==0){
				foundS=true;
			}
		}
		long endTimeS=System.nanoTime();
		long deltaTimeS=endTimeS-startTimeS;
		//...
		
		if(!foundB && !foundS)
			message+="No ";
		message+="Existe\n -Tiempo Binario:"+deltaTimeB+"ns\n -Tiempo Secuencial:"+deltaTimeS+"ns";
		
		return message;
		
	}
	
	public String searchName(String name){
		
		String message="";
		Owner testOwner=new Owner("",name,"","1/1/1000","");
		
		//Binary
		if(!order.equals(OWNERS_ORDER[2]))
			nameSort();
		boolean foundB=false;
		int start=0;
		int end=owners.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(owners.get(middle).compare(owners.get(middle),testOwner)==0){
				foundB=true;
			}
			else if(owners.get(middle).compare(owners.get(middle),testOwner)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		long endTimeB=System.nanoTime();
		long deltaTimeB=endTimeB-startTimeB;
		//...
		
		//Sequential
		boolean foundS=false;
		long startTimeS=System.nanoTime();
		for(int i=0; (i<owners.size()) && !foundS; i++){
			if(owners.get(i).compare(owners.get(i),testOwner)==0){
				foundS=true;
			}
		}
		long endTimeS=System.nanoTime();
		long deltaTimeS=endTimeS-startTimeS;
		//...
		
		if(!foundB && !foundS)
			message+="No ";
		message+="Existe\n -Tiempo Binario:"+deltaTimeB+"ns\n -Tiempo Secuencial:"+deltaTimeS+"ns";
		
		return message;
		
	}
	
	public String searchLastName(String lastName){
		
		String message="";
		Owner testOwner=new Owner("","",lastName,"1/1/1000","");
		
		//Binary
		if(!order.equals(OWNERS_ORDER[3]))
			lastNameSort();
		boolean foundB=false;
		int start=0;
		int end=owners.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(owners.get(middle).compareLastName(testOwner)==0){
				foundB=true;
			}
			else if(owners.get(middle).compareLastName(testOwner)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		long endTimeB=System.nanoTime();
		long deltaTimeB=endTimeB-startTimeB;
		//...
		
		//Sequential
		boolean foundS=false;
		long startTimeS=System.nanoTime();
		for(int i=0; (i<owners.size()) && !foundS; i++){
			if(owners.get(i).compareLastName(testOwner)==0){
				foundS=true;
			}
		}
		long endTimeS=System.nanoTime();
		long deltaTimeS=endTimeS-startTimeS;
		//...
		
		if(!foundB && !foundS)
			message+="No ";
		message+="Existe\n -Tiempo Binario:"+deltaTimeB+"ns\n -Tiempo Secuencial:"+deltaTimeS+"ns";
		
		return message;
		
	}
	
	public String searchBirthdate(String birthdate){
		
		String message="";
		Owner testOwner=new Owner("","","",birthdate,"");
		
		//Binary
		if(!order.equals(OWNERS_ORDER[4]))
			birthdateSort();
		boolean foundB=false;
		int start=0;
		int end=owners.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(owners.get(middle).compareBirthdate(testOwner)==0){
				foundB=true;
			}
			else if(owners.get(middle).compareBirthdate(testOwner)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		long endTimeB=System.nanoTime();
		long deltaTimeB=endTimeB-startTimeB;
		//...
		
		//Sequential
		boolean foundS=false;
		long startTimeS=System.nanoTime();
		for(int i=0; (i<owners.size()) && !foundS; i++){
			if(owners.get(i).compareBirthdate(testOwner)==0){
				foundS=true;
			}
		}
		long endTimeS=System.nanoTime();
		long deltaTimeS=endTimeS-startTimeS;
		//...
		
		if(!foundB && !foundS)
			message+="No ";
		message+="Existe\n -Tiempo Binario:"+deltaTimeB+"ns\n -Tiempo Secuencial:"+deltaTimeS+"ns";
		
		return message;
		
	}
	
	public String searchFavoritePetType(String favoritePetType){
		
		String message="";
		Owner testOwner=new Owner("","","","1/1/1000",favoritePetType);
		
		//Binary
		if(!order.equals(OWNERS_ORDER[5]))
			favoritePetTypeSort();
		boolean foundB=false;
		int start=0;
		int end=owners.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(owners.get(middle).compareFavoritePetType(testOwner)==0){
				foundB=true;
			}
			else if(owners.get(middle).compareFavoritePetType(testOwner)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		long endTimeB=System.nanoTime();
		long deltaTimeB=endTimeB-startTimeB;
		//...
		
		//Sequential
		boolean foundS=false;
		long startTimeS=System.nanoTime();
		for(int i=0; (i<owners.size()) && !foundS; i++){
			if(owners.get(i).compareFavoritePetType(testOwner)==0){
				foundS=true;
			}
		}
		long endTimeS=System.nanoTime();
		long deltaTimeS=endTimeS-startTimeS;
		//...
		
		if(!foundB && !foundS)
			message+="No ";
		message+="Existe\n -Tiempo Binario:"+deltaTimeB+"ns\n -Tiempo Secuencial:"+deltaTimeS+"ns";
		
		return message; 
		
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
		catch (ParseException e) {creationDate=new Date();}
		return creationDate;
		
	}
	
	public ArrayList<Owner> getOwners(){
		
		return owners;
		
	}
	
	public String getOrder(){
		
		return order;
		
	}
	
	public String toString(){
		
		String toString="[id:"+id+" name:"+name+" creationDate:"+creationDate+" petType:"+petType+"]";
		return toString;
		
	}
	
	//Plus
//	public String addOwner(Owner o){
//		String message="Se ha agregado el dueno \""+o.getName()+"\".";
//		owners.add(o);
//		return message;
//	}
	
}