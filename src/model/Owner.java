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
	
	//Attributes
	private String id;
	private String name;
	private String lastName;
	private String birthdate;
	private String favoritePetType;
	private ArrayList<Pet> pets;
	
	//Constructor
	public Owner(String id, String name, String lastName, String birthdate, String favoritePetType){
		
		this.id=id;
		this.name=name;
		this.lastName=lastName;
		this.birthdate=birthdate;
		this.favoritePetType=favoritePetType;
		
		this.pets=new ArrayList<Pet>();
		
	}
	
	//Show
	public String showPetsReport(int type){//Call??//Cuando esta vacio lo muestro????
		
		String report="Mascotas ordenadas por ";
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
				report+="fecha de nacimiento:";
				birthdateSort();
			break;
			case 4:
				report+="genero:";
				genderSort();
			break;
			case 5:
				report+="tipo:";
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
		return report;
		
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
	
	//Sorting
	private void idSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareTo(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		
	}
	
	private void nameSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compare(pets.get(j-1), pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		
	}
	
	private void birthdateSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareBirthdate(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		
	}
	
	private void genderSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareGender(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		
	}
	
	private void typeSort(){
		
		for(int i=1; i<pets.size(); i++){
			for(int j=i; (j>0)&&(pets.get(j-1).compareType(pets.get(j))>0); j--){
				Pet actual=pets.get(j);
				pets.set(j, pets.get(j-1));
				pets.set(j-1, actual);
			}
		}
		
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
		try {birthdate=format.parse(this.birthdate);} catch (ParseException e) {e.printStackTrace();}
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
	
}