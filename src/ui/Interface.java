package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.ClubManager;
import model.Owner;
import model.Pet;

public class Interface {

	//Attributes
	private Scanner scanner;
	private ClubManager manager;
	
	//Menu
	public void menu(){
		
		int option=0;
		
		boolean run=true;
		
		while(run){
			
			if(!manager.inClub()){//###
				
				System.out.println("Club Manager:\n 1.Registrar club\n 2.Eliminar club\n 3.Lista ordenada de clubes\n 4.Buscar club\n 5.Ingresar a club\n 6.Salir");
				option=askInt(1,6);
				
				switch(option){
						
					case 1:
						
						System.out.println("Id del club:");
						String a1=scanner.next();
						scanner.nextLine();
						
						System.out.println("Nombre del club:");
						String a2=scanner.nextLine();
						
						System.out.println("Fecha de creacion del club:");
						System.out.println(" Dia:");
						int a3=askInt(1,31);
						System.out.println(" Mes:");
						int a4=askInt(1,12);
						System.out.println(" Anio:");
						int a5=askInt(-9999,9999);
						
						System.out.println("Tipo de mascota del club:");
						String a6=scanner.nextLine();
						
						System.out.println(manager.addClub(a1, a2, a3+"/"+a4+"/"+a5, a6));
						
					break;
					
					case 2:
						
						System.out.println("Parametro de eliminacion:\n 1.Id\n 2.Nombre");
						int b1=askInt(1,2);
						
						switch(b1){
							case 1:
								System.out.println("Id del club:");
								String b2=scanner.next();
								scanner.nextLine();
								
								System.out.println(manager.deleteClub(b2, b1));
							break;
							case 2:
								System.out.println("Nombre del club:");
								String b3=scanner.nextLine();
								
								System.out.println(manager.deleteClub(b3, b1));
							break;
						}
						
					break;
					
					case 3:
						
						System.out.println("Parametro de organizacion:\n 1.Id\n 2.Nombre\n 3.Fecha de creacion\n 4.Tipo de mascota\n 5.Cantidad de duenos");
						int c1=askInt(1,5);
						
						System.out.println(manager.showClubsReport(c1));
						
					break;
					
					case 4:
						
						System.out.println("Parametro de busqueda:\n 1.Id\n 2.Nombre\n 3.Fecha de creacion\n 4.Tipo de mascota");
						int d1=askInt(1,4);
						
						switch(d1){
							case 1:
								System.out.println("Id del club:");
								String d2=scanner.next();
								scanner.nextLine();
								
								System.out.println(manager.showClubSearch(d2, d1));
							break;
							case 2:
								System.out.println("Nombre del club:");
								String d3=scanner.nextLine();
								
								System.out.println(manager.showClubSearch(d3, d1));
							break;
							case 3:
								System.out.println("Fecha de creacion del club:");
								System.out.println(" Dia:");
								int d4=askInt(1,31);
								System.out.println(" Mes:");
								int d5=askInt(1,12);
								System.out.println(" Anio:");
								int d6=askInt(-9999,9999);
								
								System.out.println(manager.showClubSearch(d4+"/"+d5+"/"+d6, d1));
							break;
							case 4:
								System.out.println("Tipo de mascota del club:");
								String d7=scanner.nextLine();
								
								System.out.println(manager.showClubSearch(d7, d1));
							break;
						}
						
					break;
					
					case 5:
						
						System.out.println("Id del club:");
						String e1=scanner.next();
						scanner.nextLine();
						
						System.out.println(manager.setActualClub(e1));
						
					break;
					
					case 6:
						
						System.out.println("Hasta la proxima!");
						run=false;
						
					break;
					
					default:
						
						System.out.println("Opcion Invalida!");
						
					break;
					
				}
				
			}
			else if(!manager.inOwner()){//###
				
				System.out.println("Club:\n 1.Registrar dueno\n 2.Eliminar dueno\n 3.Lista ordenada de duenos\n 4.Buscar dueno\n 5.Ingresar a dueno\n 6.Regresar");
				option=askInt(1,6);

				switch(option){
					
					case 1:
						
						System.out.println("Id del dueno:");
						String a1=scanner.next();
						scanner.nextLine();
						
						System.out.println("Nombre del dueno:");
						String a2=scanner.nextLine();
						
						System.out.println("Apellido del dueno:");
						String a3=scanner.nextLine();
						
						System.out.println("Fecha de nacimiento del dueno:");
						System.out.println(" Dia:");
						int a4=askInt(1,31);
						System.out.println(" Mes:");
						int a5=askInt(1,12);
						System.out.println(" Anio:");
						int a6=askInt(-9999,9999);
						
						System.out.println("Tipo de mascota favorita del dueno:");
						String a7=scanner.nextLine();
						
						System.out.println(manager.addOwner(a1, a2, a3, a4+"/"+a5+"/"+a6, a7));
						
					break;
					
					case 2:
						
						System.out.println("Parametro de eliminacion:\n 1.Id\n 2.Nombre");
						int b1=askInt(1,2);
						
						switch(b1){
							case 1:
								System.out.println("Id del dueno:");
								String b2=scanner.next();
								scanner.nextLine();
								
								System.out.println(manager.deleteOwner(b2, b1));
							break;
							case 2:
								System.out.println("Nombre del dueno:");
								String b3=scanner.nextLine();
								
								System.out.println(manager.deleteOwner(b3, b1));
							break;
						}
						
					break;
					
					case 3:
						
						System.out.println("Parametro de organizacion:\n 1.Id\n 2.Nombre\n 3.Apellido\n 4.Fecha de nacimiento\n 5.Tipo de mascota favorita\n 6.Cantidad de mascotas");
						int c1=askInt(1,6);
						
						System.out.println(manager.showOwnersReport(c1));
						
					break;
					
					case 4:
						
						System.out.println("Parametro de busqueda:\n 1.Id\n 2.Nombre\n 3.Apellido\n 4.Fecha de nacimiento\n 5.Tipo de mascota favorita");
						int d1=askInt(1,5);
						
						switch(d1){
							case 1:
								System.out.println("Id del dueno:");
								String d2=scanner.next();
								scanner.nextLine();
								
								System.out.println(manager.showOwnerSearch(d2, d1));
							break;
							case 2:
								System.out.println("Nombre del dueno:");
								String d3=scanner.nextLine();
								
								System.out.println(manager.showOwnerSearch(d3, d1));
							break;
							case 3:
								System.out.println("Apellido del dueno:");
								String d4=scanner.nextLine();
								
								System.out.println(manager.showOwnerSearch(d4, d1));
							break;
							case 4:
								System.out.println("Fecha de nacimiento del dueno:");
								System.out.println(" Dia:");
								int d5=askInt(1,31);
								System.out.println(" Mes:");
								int d6=askInt(1,12);
								System.out.println(" Anio:");
								int d7=askInt(-9999,9999);
								
								System.out.println(manager.showOwnerSearch(d5+"/"+d6+"/"+d7, d1));
							break;
							case 5:
								System.out.println("Tipo de mascota favorita del dueno:");
								String d8=scanner.nextLine();
								
								System.out.println(manager.showOwnerSearch(d8, d1));
							break;
						}
						
					break;
					
					case 5:
						
						System.out.println("Id del dueno:");
						String e1=scanner.next();
						scanner.nextLine();
						
						System.out.println(manager.setActualOwner(e1));
						
					break;
					
					case 6:
						
						manager.setActualClubNull();
						
					break;
					
				}
				
			}
			else{//###
				
				System.out.println("Owner:\n 1.Registrar mascota\n 2.Eliminar mascota\n 3.Lista ordenada de mascotas\n 4.Buscar mascota\n 5.Regresar");
				option=askInt(1,5);
				
				switch(option){
					
					case 1:
						
						System.out.println("Id de la mascota:");
						String a1=scanner.next();
						scanner.nextLine();
						
						System.out.println("Nombre de la mascota:");
						String a2=scanner.nextLine();
						
						System.out.println("Fecha de nacimiento de la mascota:");
						System.out.println(" Dia:");
						int a3=askInt(1,31);
						System.out.println(" Mes:");
						int a4=askInt(1,12);
						System.out.println(" Anio:");
						int a5=askInt(-9999,9999);
						
						System.out.println("Genero de la mascota:\n 1.Macho\n 2.Hembra");
						char a6=askGender();
						
						System.out.println("Tipo de la mascota:");
						String a7=scanner.nextLine();
						
						System.out.println(manager.addPet(a1, a2, a3+"/"+a4+"/"+a5, a6, a7));
						
					break;
					
					case 2:
						
						System.out.println("Parametro de eliminacion:\n 1.Id\n 2.Nombre");
						int b1=askInt(1,2);
						
						switch(b1){
							case 1:
								System.out.println("Id de la mascota:");
								String b2=scanner.next();
								scanner.nextLine();
								
								System.out.println(manager.deletePet(b2, b1));
							break;
							case 2:
								System.out.println("Nombre de la mascota:");
								String b3=scanner.nextLine();
								
								System.out.println(manager.deletePet(b3, b1));
							break;
						}
						
					break;
					
					case 3:
						
						System.out.println("Parametro de organizacion:\n 1.Id\n 2.Nombre\n 3.Fecha de nacimiento\n 4.Genero\n 5.Tipo");
						int c1=askInt(1,5);
						
						System.out.println(manager.showPetsReport(c1));
						
					break;
					
					case 4:
						
						System.out.println("Parametro de busqueda:\n 1.Id\n 2.Nombre\n 3.Fecha de nacimiento\n 4.Genero\n 5.Tipo");
						int d1=askInt(1,5);
						
						switch(d1){
							case 1:
								System.out.println("Id de la mascota:");
								String d2=scanner.next();
								scanner.nextLine();
								
								System.out.println(manager.showPetSearch(d2, d1));
							break;
							case 2:
								System.out.println("Nombre de la mascota:");
								String d3=scanner.nextLine();
								
								System.out.println(manager.showPetSearch(d3, d1));
							break;
							case 3:
								System.out.println("Fecha de nacimiento de la mascota:");
								System.out.println(" Dia:");
								int d4=askInt(1,31);
								System.out.println(" Mes:");
								int d5=askInt(1,12);
								System.out.println(" Anio:");
								int d6=askInt(-9999,9999);
								
								System.out.println(manager.showPetSearch(d4+"/"+d5+"/"+d6, d1));
							break;
							case 4:
								System.out.println("Genero de la mascota:\n 1.Macho\n 2.Hembra");
								char d7=askGender();
								
								System.out.println(manager.showPetSearch(d7+"", d1));
							break;
							case 5:
								System.out.println("Tipo de la mascota:");
								String d8=scanner.nextLine();
								
								System.out.println(manager.showPetSearch(d8, d1));
							break;
						}
						
					break;
					
					case 5:
						
						manager.setActualOwnerNull();
						
					break;
				
				}
				
			}
			
		}
		
	}
	
	//Ask
	public int askInt(int start, int end){
		
		int ask=0;
		boolean run=true;
		
		while(run){
			try{
				ask=scanner.nextInt();
				scanner.nextLine();
				if((ask>=start)&&(ask<=end)){run=false;}
				else{System.out.println("Opcion Invalida!");}
			}
			catch(InputMismatchException e){
				System.out.println("Opcion Invalida!");
				scanner=new Scanner(System.in);
			}
		}
		
		return ask;

	}
	
	public char askGender(){
		
		char gender=' ';
		boolean run=true;
		
		while(run){
			try{
				int option=scanner.nextInt();
				scanner.nextLine();
				switch(option){
					case 1:
						gender=Pet.MALE;
						run=false;
					break;
					
					case 2:
						gender=Pet.FEMALE;
						run=false;
					break;
					
					default:
						System.out.println("Opcion Invalida!");
					break;
				}
			}
			catch(InputMismatchException e){
				System.out.println("Opcion Invalida!");
				scanner=new Scanner(System.in);
			}
		}
		
		return gender;
		
	}
	
	//Constructor
	public Interface(){
		
		init();
		
	}
	
	//Initializer
	public void init(){
		
		scanner=new Scanner(System.in);
		manager=new ClubManager();
		//dataGenerator();
		
	}
	
	//Plus
//	private void dataGenerator(){
//		String[] nombres={"Esteban","Alejandro","Carlos","Guillermo","John","Jose Daniel","Juan Andres","Juan Felipe","Juan Jose","Juan Sebastian","Santiago","Joshua","Nicolas","Isabella","Natalia","Sofia","Maria Jose","Tatiana","Andrea","Mariana","Catalina","Maria Fernanda","Valeria","Gabriela","Diana","Valentina"};
//		String[] apellidos={"Ariza","Valencia","Mira","Quevedo","Mendoza","Zamudio","Sanchez","Gonzales","Arenas","Serpa","Barriga","Lasciche","Corredor","Villamil","Giraldo","Soto","Losada","Betancur","Munos","Quintero","Restrepo","Lopez","Cardenas","Benavides","Acosta","Ramirez"};
//		String[] animales={"Perro","Gato","Hamster","Pajaro","Tortuga","Pez","Lagarto","Conejo","Serpiente","Cerdo","Ardilla","Erizo","Huron"};
//		for(int j=1; j<=10; j++){
//			String codigo="A";
//			if(j!=10)codigo+="0"+j;
//			else codigo+="10";
//			System.out.println(manager.setActualClub(codigo));
//			for(int i=0; i<=10000;i++){
//				int id=(100000*j)+i;
//				int name=(int)(Math.random()*nombres.length);
//				int apellido=(int)(Math.random()*apellidos.length);
//				int dia=(int)(Math.random()*31)+1;
//				int mes=(int)(Math.random()*12)+1;
//				int ano=(int)((Math.random()*20)+2000);
//				int animal=(int)(Math.random()*animales.length);
//				Owner o=new Owner(id+"", nombres[name], apellidos[apellido], dia+"/"+mes+"/"+ano, animales[animal]);
//				Pet p1=new Pet("1", "Bruno", "20/3/2012", 'M', "Perro");
//				o.addPet(p1);
//				Pet p2=new Pet("2", "Impa", "1/5/2016", 'F', animales[animal]);
//				o.addPet(p2);
//				System.out.println(manager.addOwner(o));
//			}
//			manager.saveOwner();
//		}
//	}
	
}
