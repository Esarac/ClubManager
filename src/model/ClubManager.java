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
	public static final String[] CLUBS_ORDER={"Disordered", "Id", "Name", "Creation Date", "Pet Type", "Owners Quantity"};
	
	//Attributes
	private Club actualClub;//Code
	private Owner actualOwner;//Code
	private ArrayList<Club> clubs;
	private String order;//Code
	
	//Constructor
	public ClubManager(){
		
		this.actualClub=null;
		this.actualOwner=null;
		
		this.clubs=new ArrayList<Club>();
		loadClubs();
		
		this.order=CLUBS_ORDER[0];
		
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
		
		String message="";
		try{message=actualClub.addOwner(id, name, lastName, birthdate, favoritePetType);}
		catch(NullPointerException e){message="No estas posicionado en un club.";}
		return message;
		
	}
	
	public String addPet(String id, String name, String birthdate, char gender, String type){//[Call]
		
		String message="";
		try{
			message=actualOwner.addPet(id, name, birthdate, gender, type);
			actualClub.saveOwners();
		}
		catch(NullPointerException e){message="No estas posicionado en un club o un dueno.";}
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
	
	private String deleteClubFile(String id){//[File]
		
		String message="Se pudo borrar el club del archivo.";
		
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
		catch(IOException e){message="No se encontro el archivo.";}
		
		return message;
		
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
		try{
			switch(type){
				case 1:
					message=actualClub.deleteOwnerId(info);
				break;
				case 2:
					message=actualClub.deleteOwnerName(info);
				break;
			}
			
		}
		catch(NullPointerException e){message="No estas posicionado en un club.";}
		return message;
		
	}
	
	public String deletePet(String info, int type){//[Call]
		
		String message="Tipo de busqueda invalido.";
		try{
			switch(type){
				case 1:
					message=actualOwner.deletePetId(info);
					actualClub.saveOwners();
				break;
				case 2:
					message=actualOwner.deletePetName(info);
					actualClub.saveOwners();
				break;
			}
		}
		catch(NullPointerException e){message="No estas posicionado en un club o un dueno.";}
		
		return message;
		
	}
	
	//Load
	private String loadClubs(){//[File]
		
		String message="Se pudo cargar el club del archivo.";
		
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
		catch(IOException e){message="No se encontro el archivo.";}
		catch(IndexOutOfBoundsException e){message="No se pudo cargar el club del archivo porque esta escrito de manera incorrecta.";}
		
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
	public String showClubsReport(int type){
		
		String report="Clubes ordenados por ";
		boolean okay=true;
		switch(type){
			case 1:
				report+="id:";
				if(!order.equals(CLUBS_ORDER[1]))
					idSort();
			break;
			case 2:
				report+="nombre:";
				if(!order.equals(CLUBS_ORDER[2]))
					nameSort();
			break;
			case 3:
				
				report+="fecha de creacion:";
				if(!order.equals(CLUBS_ORDER[3]))
					creationDateSort();
				
			break;
			case 4:
				report+="tipo de mascota:";
				if(!order.equals(CLUBS_ORDER[4]))
					petTypeSort();
			break;
			case 5:
				report+="cantidad de duenos:";
				if(!order.equals(CLUBS_ORDER[5]))
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
		if(clubs.size()==0){
			report+="\n No hay clubes.";
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
	
	public String showClubSearch(String info, int type){//[Call]
		
		String message="Tipo de busqueda invalido.";
		switch(type){
			case 1:
				message=searchId(info);
			break;
			case 2:
				message=searchName(info);
			break;
			case 3:
				message=searchCreationDate(info);
			break;
			case 4:
				message=searchPetType(info);
			break;
		}
		return message;
		
	}
	
	public String showOwnerSearch(String info, int type){//[Call]
		
		String message;
		try{message=actualClub.showOwnerSearch(info, type);}
		catch(NullPointerException e){message="No estas posicionado en un club.";}
		return message;
		
	}
	
	public String showPetSearch(String info, int type){//[Call]
		
		String message;
		try{message=actualOwner.showPetSearch(info, type);}
		catch(NullPointerException e){message="No estas posicionado en un dueno.";}
		return message;
		
	}
	
	//Sort (Selection)
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
		this.order=CLUBS_ORDER[1];
		
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
		this.order=CLUBS_ORDER[2];
		
	}
	
	public void creationDateSort(){
		
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
		this.order=CLUBS_ORDER[3];
		
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
		this.order=CLUBS_ORDER[4];
		
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
		this.order=CLUBS_ORDER[5];
		
	}
	
	//Search
	public String searchId(String id){
		
		String message="";
		Club testClub=new Club(id,"","1/1/1000","");
		
		//Binary
		if(!order.equals(CLUBS_ORDER[1]))
			idSort();
		boolean foundB=false;
		int start=0;
		int end=clubs.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(clubs.get(middle).compareTo(testClub)==0){
				foundB=true;
			}
			else if(clubs.get(middle).compareTo(testClub)>0){
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
		for(int i=0; (i<clubs.size()) && !foundS; i++){
			if(clubs.get(i).compareTo(testClub)==0){
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
		Club testClub=new Club("",name,"1/1/1000","");
		
		//Binary
		if(!order.equals(CLUBS_ORDER[2]))
			nameSort();
		boolean foundB=false;
		int start=0;
		int end=clubs.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(clubs.get(middle).compare(clubs.get(middle),testClub)==0){
				foundB=true;
			}
			else if(clubs.get(middle).compare(clubs.get(middle),testClub)>0){
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
		for(int i=0; (i<clubs.size()) && !foundS; i++){
			if(clubs.get(i).compare(clubs.get(i),testClub)==0){
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
	
	public String searchCreationDate(String creationDate){
		
		String message="";
		Club testClub=new Club("","",creationDate,"");
		
		//Binary
		if(!order.equals(CLUBS_ORDER[3]))
			creationDateSort();
		boolean foundB=false;
		int start=0;
		int end=clubs.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(clubs.get(middle).compareCreationDate(testClub)==0){
				foundB=true;
			}
			else if(clubs.get(middle).compareCreationDate(testClub)>0){
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
		for(int i=0; (i<clubs.size()) && !foundS; i++){
			if(clubs.get(i).compareCreationDate(testClub)==0){
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
	
	public String searchPetType(String petType){
		
		String message="";
		Club testClub=new Club("","","1/1/1000",petType);
		
		//Binary
		if(!order.equals(CLUBS_ORDER[4]))
			petTypeSort();
		boolean foundB=false;
		int start=0;
		int end=clubs.size()-1;
		long startTimeB=System.nanoTime();
		while((start<=end) && !foundB){
			int middle=(start+end)/2;
			if(clubs.get(middle).comparePetType(testClub)==0){
				foundB=true;
			}
			else if(clubs.get(middle).comparePetType(testClub)>0){
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
		for(int i=0; (i<clubs.size()) && !foundS; i++){
			if(clubs.get(i).comparePetType(testClub)==0){
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
		
		if(!order.equals(CLUBS_ORDER[1]))
			idSort();
		String message="El club con id \""+id+"\" no existe.";
		boolean found=false;
		int start=0;
		int end=clubs.size()-1;
		
		while((start<=end) && !found){
			int middle=(start+end)/2;
			if(clubs.get(middle).getId().compareTo(id)==0){
				this.actualClub=clubs.get(middle);
				message="Estas posicionado en el club con id \""+id+"\".";
				found=true;
			}
			else if(clubs.get(middle).getId().compareTo(id)>0){
				end=middle-1;
			}
			else{
				start=middle+1;
			}
		}
		
		return message;
		
	}
	
	public void setActualClubNull(){
		
		this.actualOwner=null;
		this.actualClub=null;
		
	}
	
	public String setActualOwner(String id){
		
		String message="El dueno con id \""+id+"\" no existe.";
		try{
			if(actualClub.getOrder().equals(Club.OWNERS_ORDER[1]))
				actualClub.idSort();
			ArrayList<Owner> owners=actualClub.getOwners();
			boolean found=false;
			int start=0;
			int end=owners.size()-1;
			
			while((start<=end) && !found){
				int middle=(start+end)/2;
				if(owners.get(middle).getId().compareTo(id)==0){
					this.actualOwner=owners.get(middle);
					message="Estas posicionado en el dueno con id \""+id+"\".";
					found=true;
				}
				else if(owners.get(middle).getId().compareTo(id)>0){
					end=middle-1;
				}
				else{
					start=middle+1;
				}
			}
		}
		catch(NullPointerException e){
			message="No estas posicionado en un club.";
		}
		
		return message;
		
	}
	
	public String setActualOwnerNull(){
		
		String message="Se guardaron los duenos y las mascotas en el archivo.";
		
		try{actualClub.saveOwners();}
		catch(NullPointerException e){message="No estas posicionado en un club.";}
		this.actualOwner=null;
		
		return message;
		
	}
	
	//Plus
//	public String addOwner(Owner o){
//		String message=actualClub.addOwner(o);
//		return message;
//	}
//	
//	public void saveOwner(){
//		actualClub.saveOwners();
//	}
	
}