package ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.ClubManager;
import model.Pet;

public class Main{
	
	//Attributes
	private Scanner scanner;
	private ClubManager manager;
	
	//Menu
	public void menu(){
		
		int option=0;
		
		boolean run=true;
		
		while(run){
			
			if(!manager.inClub()){//###
				
				System.out.println("Club Manager:\n 1.Registrar club\n 2.\n 3.Ingresar a club \n 10.Salir");
				try{
					option=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){//Mejor practica hacer parceInt??
					option=0;
					scanner=new Scanner(System.in);
				}
				
				switch(option){
						
					case 1:
						try{//Try esta bien?
							System.out.println("Id del club:");
							String a1=scanner.next();
							scanner.nextLine();
							
							System.out.println("Nombre del club:");
							String a2=scanner.nextLine();
							
							System.out.println("Fecha de creacion del club:");
							String a3=scanner.nextLine();
							
							System.out.println("Tipo de mascota del club:");
							String a4=scanner.nextLine();
							
							System.out.println(manager.addClub(a1, a2, a3, a4));
						}
						catch(InputMismatchException e){
							System.out.println("Opcion Invalida!");
						}
					break;
					
					case 2:
						
					break;
					
					case 3:
						try{
							System.out.println("Id del club:");
							String c1=scanner.next();
							scanner.nextLine();
							
							System.out.println(manager.setActualClub(c1));
						}
						catch(InputMismatchException e){
							System.out.println("Opcion Invalida!");
						}
					break;
					
					case 10:
						System.out.println("Hasta la proxima!");
						run=false;
					break;
					
					default:
						System.out.println("Opcion Invalida!");
					break;
					
				}
				
			}//###
			else if(!manager.inOwner()){
				
				System.out.println("Club:\n 1.Registrar dueno\n 2.\n 3.Ingresar a dueno \n 5.Regresar");
				try{
					option=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					option=0;
					scanner=new Scanner(System.in);
				}
				
				switch(option){
					
					case 1:
						try{
							System.out.println("Id del dueno:");
							String a1=scanner.next();
							scanner.nextLine();
							
							System.out.println("Nombre del dueno:");
							String a2=scanner.nextLine();
							
							System.out.println("Apellido del dueno:");
							String a3=scanner.nextLine();
							
							System.out.println("Fecha de nacimiento del dueno:");
							String a4=scanner.nextLine();
							
							System.out.println("Tipo de mascota favorita del dueno:");
							String a5=scanner.nextLine();
							
							System.out.println(manager.addOwner(a1, a2, a3, a4, a5));
						}
						catch(InputMismatchException e){
							System.out.println("Opcion Invalida!");
						}
					break;
					
					case 2:
						
					break;
					
					case 3:
						try{
							System.out.println("Id del dueno:");
							String c1=scanner.next();
							scanner.nextLine();
							
							System.out.println(manager.setActualOwner(c1));
						}
						catch(InputMismatchException e){
							System.out.println("Opcion Invalida!");
						}
					break;
					
					case 4:
						
					break;
					
					case 5:
						manager.setActualClubNull();
					break;
					
					default:
						System.out.println("Opcion Invalida!");
					break;
					
				
				}
				
			}
			else{
				
				System.out.println("Owner:\n 1.Registrar mascota\n 2.\n 3. \n 4.Regresar");
				try{
					option=scanner.nextInt();
					scanner.nextLine();
				}
				catch(InputMismatchException e){
					option=0;
					scanner=new Scanner(System.in);
				}
				
				switch(option){
					
					case 1:
						try{
							System.out.println("Id de la mascota:");
							String a1=scanner.next();
							scanner.nextLine();
							
							System.out.println("Nombre de la mascota:");
							String a2=scanner.nextLine();
							
							System.out.println("Fecha de nacimiento de la mascota:");
							String a3=scanner.nextLine();
							
							System.out.println("Genero de la mascota:\n 1.Macho\n 2.Hembra");
							char a4=askGender();
							
							System.out.println("Tipo de la mascota:");
							String a5=scanner.nextLine();
							
							System.out.println(manager.addPet(a1, a2, a3, a4, a5));
						}
						catch(InputMismatchException e){
							System.out.println("Opcion Invalida!");
						}
					break;
					
					case 2:
						
					break;
					
					case 3:
						
					break;
					
					case 4:
						manager.setActualOwnerNull();
					break;
					
					default:
						System.out.println("Opcion Invalida!");
					break;
					
				
				}
				
			}
			
		}
		
	}
	
	//Asker
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
			}
		}
		return gender;
		
	}
	
	//Starter
	public static void main(String[]args){
		
		Main m=new Main();
		m.menu();
		
	}
	
	//Constructor
	public Main(){
		
		init();
		
	}
	
	//Initializer
	public void init(){
		
		scanner=new Scanner(System.in);
		manager=new ClubManager();
		
	}
	
}