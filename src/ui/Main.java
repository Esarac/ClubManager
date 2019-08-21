package ui;

import java.util.InputMismatchException;
import java.util.Scanner;
import model.ClubManager;

public class Main{
	
	//Attributes
	private Scanner scanner;
	private ClubManager manager;
	
	//Menu
	public void menu(){
		
		int option=0;
		
		boolean run=true;
		
		while(run){
			
			System.out.println("Club Manager:\n 1.Registrar club\n 2.Registrar dueno\n 3.Registrar mascota\n 10.Salir");
			
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
					try{
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
					catch(InputMismatchException e){}
				break;
				
				case 2:
					
				break;
				
				case 3:
					
				break;
				
				case 10:
					System.out.println("Hasta la proxima!");
					run=false;
				break;
				
				default:
					System.out.println("Opcion Invalida!");
				break;
				
			}
			
			
		}
		
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