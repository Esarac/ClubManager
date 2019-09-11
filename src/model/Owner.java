package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Owner implements Serializable, Comparable<Owner>, Comparator<Owner>{
	
	//Constant
	private static final long serialVersionUID = -4413223154133382615L;
	public static final String[] PETS_ORDER={"Disordered", "Id", "Name", "Birthdate", "Gender", "Type"};
	
	//Attributes
	private String id;
	private String name;
	private String lastName;
	private String birthdate;
	private String favoritePetType;
	private ArrayList<Pet> pets;
	private String order;//Code
	
	//Constructor
	public Owner(String id, String name, String lastName, String birthdate, String favoritePetType){
		
		this.id=id;
		this.name=name;
		this.lastName=lastName;
		this.favoritePetType=favoritePetType;
		
		this.pets=new ArrayList<Pet>();
		
		this.order=PETS_ORDER[0];
		
		String[] date=birthdate.split("/");
		if(date.length==3)
			this.birthdate=birthdate;
		else{
			try {throw new ImpossibleDateException();}
			catch (ImpossibleDateException e) {
				e.printStackTrace();
				this.birthdate="1/1/1000";
			}
		}
		
	}
	
	//Add
	public String addPet(String id, String name, String birthdate, char gender, String type){
		
		String message="Se ha agregado la mascota \""+name+"\".";
		boolean exist=false;
		
		for(int i=0; (i<pets.size()) && !exist; i++){
			if(pets.get(i).getName().equals(name)){
				message="La mascota con nombre \""+name+"\" ya existe.";
				exist=true;
			}
		}
		if(!exist){
			pets.add(new Pet(id, name, birthdate, gender, type));
		}
		
		return message;
		
	}
	
	//Delete
	public String deletePetId(String id){
		
		String message="La mascota con id \""+id+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<pets.size()) && !found; i++){
			if(pets.get(i).getId().equals(id)){
				pets.remove(i);
				message="Eliminaste la mascota con id \""+id+"\".";
				found=true;
			}
		}
		
		return message;
		
	}
	
	public String deletePetName(String name){
		
		String message="La mascota con nombre \""+name+"\" no existe.";
		boolean found=false;
		
		for(int i=0; (i<pets.size()) && !found; i++){
			if(pets.get(i).getName().equals(name)){
				pets.remove(i);
				message="Eliminaste la mascota con nombre \""+name+"\".";
				found=true;
			}
		}
		
		return message;
		
	}
	
	
	//Show
	public String showPetsReport(int type){
		
		String report="Mascotas ordenadas por ";
		boolean okay=true;
		switch(type){
			case 1:
				report+="id:";
				if(!order.equals(PETS_ORDER[1]))
					idSort();
			break;
			case 2:
				report+="nombre:";
				if(!order.equals(PETS_ORDER[2]))
					nameSort();
			break;
			case 3:
				report+="fecha de nacimiento:";
				if(!order.equals(PETS_ORDER[3]))
					birthdateSort();
			break;
			case 4:
				report+="genero:";
				if(!order.equals(PETS_ORDER[4]))
					genderSort();
			break;
			case 5:
				report+="tipo:";
				if(!order.equals(PETS_ORDER[5]))
					typeSort();
			break;
			default:
				okay=false;
				report="Metodo de organizacion invalido.";
			break;
		}
		if(okay){
			for(int i=0; i<pets.size(); i++){
				report+="\n -"+pets.get(i);
			}
		}
		if(pets.size()==0){
			report+="\n No hay mascotas.";
		}
		return report;
		
	}
	
	public String showPetSearch(String info, int type){//[Call]
		
		String message="Tipo de busqueda invalido.";
		switch(type){
			case 1:
				message=searchId(info);
			break;
			case 2:
				message=searchName(info);
			break;
			case 3:
				message=searchBirthdate(info);
			break;
			case 4:
				message=searchGender(info.charAt(0));
			break;
			case 5:
				message=searchType(info);
			break;
		}
		return message;
		
	}
	
	//Sorting (Insertion)
	public void idSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareTo(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		this.order=PETS_ORDER[1];
		
	}
	
	public void nameSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compare(pets.get(j-1), pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		this.order=PETS_ORDER[2];
		
	}
	
	public void birthdateSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareBirthdate(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		this.order=PETS_ORDER[3];
		
	}
	
	public void genderSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareGender(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		this.order=PETS_ORDER[4];
		
	}
	
	public void typeSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareType(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		this.order=PETS_ORDER[5];
		
	}
	
	//Search
	public String searchId(String id){
		
		String message="";
		Pet testPet=new Pet(id,"","1/1/1000",'M',"");//Cambiar
		
		//Binary
		if(!order.equals(PETS_ORDER[1]))//Cambiar
			idSort();//Cambiar
		boolean foundB=false;
		int start=0;
		int end=pets.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(pets.get(middle).compareTo(testPet)==0){//Cambiar
				foundB=true;
			}
			else if(pets.get(middle).compareTo(testPet)>0){//Cambiar
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
		for(int i=0; (i<pets.size()) && !foundS; i++){
			if(pets.get(i).compareTo(testPet)==0){//Cambiar
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
		Pet testPet=new Pet("",name,"1/1/1000",'M',"");
		
		//Binary
		if(!order.equals(PETS_ORDER[2]))
			nameSort();
		boolean foundB=false;
		int start=0;
		int end=pets.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(pets.get(middle).compare(pets.get(middle),testPet)==0){
				foundB=true;
			}
			else if(pets.get(middle).compare(pets.get(middle),testPet)>0){
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
		for(int i=0; (i<pets.size()) && !foundS; i++){
			if(pets.get(i).compare(pets.get(i),testPet)==0){
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
		Pet testPet=new Pet("","",birthdate,'M',"");
		
		//Binary
		if(!order.equals(PETS_ORDER[3]))
			birthdateSort();
		boolean foundB=false;
		int start=0;
		int end=pets.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(pets.get(middle).compareBirthdate(testPet)==0){
				foundB=true;
			}
			else if(pets.get(middle).compareBirthdate(testPet)>0){
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
		for(int i=0; (i<pets.size()) && !foundS; i++){
			if(pets.get(i).compareBirthdate(testPet)==0){
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
	
	public String searchGender(char gender){
		
		String message="";
		Pet testPet=new Pet("","","1/1/1000",gender,"");
		
		//Binary
		if(!order.equals(PETS_ORDER[4]))
			genderSort();
		boolean foundB=false;
		int start=0;
		int end=pets.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(pets.get(middle).compareGender(testPet)==0){
				foundB=true;
			}
			else if(pets.get(middle).compareGender(testPet)>0){
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
		for(int i=0; (i<pets.size()) && !foundS; i++){
			if(pets.get(i).compareGender(testPet)==0){
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
	
	public String searchType(String type){
		
		String message="";
		Pet testPet=new Pet("","","1/1/1000",'M',type);
		
		//Binary
		if(!order.equals(PETS_ORDER[5]))
			typeSort();
		boolean foundB=false;
		int start=0;
		int end=pets.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(pets.get(middle).compareType(testPet)==0){
				foundB=true;
			}
			else if(pets.get(middle).compareType(testPet)>0){
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
		for(int i=0; (i<pets.size()) && !foundS; i++){
			if(pets.get(i).compareType(testPet)==0){
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
	public int compareTo(Owner owner) {//Id
		
		int delta=id.compareTo(owner.getId());
		return delta;
		
	}
	
	public int compare(Owner owner1, Owner owner2) {//Name

		int delta=owner1.getName().compareTo(owner2.getName());
		return delta;
		
	}
	
	public int compareLastName(Owner owner){
		
		int delta=lastName.compareTo(owner.getLastName());
		return delta;
		
	}
	
	public int compareBirthdate(Owner owner){
		
		int delta=getBirthdate().compareTo(owner.getBirthdate());
		return delta;
		
	}
	
	public int compareFavoritePetType(Owner owner){
		
		int delta=favoritePetType.compareTo(owner.getFavoritePetType());
		return delta;
		
	}
	
	public int comparePetQuantity(Owner owner){
		
		int delta=pets.size()-owner.getPets().size();
		return delta;
		
	}
	
	//Get
	public String getId(){
		
		return id;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public String getLastName(){
		
		return lastName;
		
	}
	
	public Date getBirthdate(){
		
		Date birthdate=null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {birthdate=format.parse(this.birthdate);}
		catch (ParseException e) {birthdate=new Date();}
		return birthdate;
		
	}
	
	public String getFavoritePetType(){
		
		return favoritePetType;
		
	}
	
	public ArrayList<Pet> getPets(){
		
		return pets;
		
	}
	
	public String toString(){
		
		String toString="[id:"+id+" name:"+name+" lastName:"+lastName+" birthdate:"+birthdate+" favoritePetType:"+favoritePetType+"]";
		return toString;
		
	}
	
	//Plus
	public void addPet(Pet p){
		
		pets.add(p);
		
	}
	
}