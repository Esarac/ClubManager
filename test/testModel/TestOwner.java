package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import model.Owner;
import model.Pet;

class TestOwner {

	//Tested Class
	private Owner owner;
	
	//Scene
	private void setUpSceneOwner1(){
		
		owner=new Owner("1", "Alberto", "Ariza", "12/12/2005", "Ardilla");
		
	}
	
	private void setUpSceneOwner2(){
		
		owner=new Owner("2", "Zara", "Zamudio", "1/1/2010", "Zorro");
		owner.addPet(new Pet("1","1","1/1/1",'M',"1"));
		
	}
	
	private void setUpSceneOwnerError1(){
		
		owner=new Owner("1", "Alberto", "Ariza", "hola", "Ardilla");
		
	}
	
	private void setUpSceneOwnerError2(){
		
		owner=new Owner("1", "Alberto", "Ariza", "a/a/a", "Ardilla");
		
	}
	
	//Test
	@Test
	void testAddPet(){
		
		setUpSceneOwner1();
		assertEquals(owner.addPet("1","1","1/1/1",'M',"1"),"Se ha agregado la mascota \"1\".");
		assertEquals(owner.addPet("1","1","1/1/1",'M',"1"),"La mascota con nombre \"1\" ya existe.");
		
	}
	
	@Test
	void testDeletePetId(){
		
		setUpSceneOwner1();
		owner.addPet("1","1","1/1/1",'M',"1");
		assertEquals(owner.deletePetId("1"),"Eliminaste la mascota con id \"1\".");
		assertEquals(owner.deletePetId("1"),"La mascota con id \"1\" no existe.");
		
	}

	@Test
	void testDeletePetName(){
		
		setUpSceneOwner1();
		owner.addPet("1","1","1/1/1",'M',"1");
		assertEquals(owner.deletePetName("1"),"Eliminaste la mascota con nombre \"1\".");
		assertEquals(owner.deletePetName("1"),"La mascota con nombre \"1\" no existe.");
		
	}
	
	@Test
	void testIdSort(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("3","1","1/1/1",'M',"1");
		Pet p2=new Pet("2","1","1/1/1",'M',"1");
		Pet p3=new Pet("1","1","1/1/1",'M',"1");
		ArrayList<Pet> pets=new ArrayList<Pet>();
		pets.add(p3);
		pets.add(p2);
		pets.add(p1);
		
		owner.addPet(p1);
		owner.addPet(p2);
		owner.addPet(p3);
		owner.idSort();
		
		assertArrayEquals(pets.toArray(),owner.getPets().toArray());
		
	}
	
	@Test
	void testNameSort(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("3","3","1/1/1",'M',"1");
		Pet p2=new Pet("2","2","1/1/1",'M',"1");
		Pet p3=new Pet("1","1","1/1/1",'M',"1");
		ArrayList<Pet> pets=new ArrayList<Pet>();
		pets.add(p3);
		pets.add(p2);
		pets.add(p1);
		
		owner.addPet(p1);
		owner.addPet(p2);
		owner.addPet(p3);
		owner.nameSort();
		
		assertArrayEquals(pets.toArray(),owner.getPets().toArray());
		
	}
	
	@Test
	void testBirthdateSort(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("3","3","1/1/2015",'M',"1");
		Pet p2=new Pet("2","2","1/5/2014",'M',"1");
		Pet p3=new Pet("1","1","2/1/2014",'M',"1");
		ArrayList<Pet> pets=new ArrayList<Pet>();
		pets.add(p3);
		pets.add(p2);
		pets.add(p1);
		
		owner.addPet(p1);
		owner.addPet(p2);
		owner.addPet(p3);
		owner.birthdateSort();
		
		assertArrayEquals(pets.toArray(),owner.getPets().toArray());
		
	}
	
	@Test
	void testGenderSort(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("3","1","1/1/1",'M',"1");
		Pet p2=new Pet("1","1","1/1/1",'F',"1");
		ArrayList<Pet> pets=new ArrayList<Pet>();
		pets.add(p2);
		pets.add(p1);
		
		owner.addPet(p1);
		owner.addPet(p2);
		owner.genderSort();
		
		assertArrayEquals(pets.toArray(),owner.getPets().toArray());
		
	}
	
	@Test
	void testTypeSort(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("3","1","1/1/1",'M',"3");
		Pet p2=new Pet("2","1","1/1/1",'M',"2");
		Pet p3=new Pet("1","1","1/1/1",'M',"1");
		ArrayList<Pet> pets=new ArrayList<Pet>();
		pets.add(p3);
		pets.add(p2);
		pets.add(p1);
		
		owner.addPet(p1);
		owner.addPet(p2);
		owner.addPet(p3);
		owner.typeSort();
		
		assertArrayEquals(pets.toArray(),owner.getPets().toArray());
		
	}
	
	@Test
	void testSearchId(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("1","1","1/1/1",'M',"1");
		owner.addPet(p1);
		
		assertEquals(owner.searchId("1").charAt(0), 'E');
		assertEquals(owner.searchId("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchName(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("1","1","1/1/1",'M',"1");
		owner.addPet(p1);
		
		assertEquals(owner.searchName("1").charAt(0), 'E');
		assertEquals(owner.searchName("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchBirthdate(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("1","1","1/1/1",'M',"1");
		owner.addPet(p1);
		
		assertEquals(owner.searchBirthdate("1/1/1").charAt(0), 'E');
		assertEquals(owner.searchBirthdate("2/2/2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchGender(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("1","1","1/1/1",'M',"1");
		owner.addPet(p1);
		
		assertEquals(owner.searchGender('M').charAt(0), 'E');
		assertEquals(owner.searchGender('F').charAt(0), 'N');
		
	}
	
	@Test
	void testSearchType(){
		
		setUpSceneOwner1();
		Pet p1=new Pet("1","1","1/1/1",'M',"1");
		owner.addPet(p1);
		
		assertEquals(owner.searchType("1").charAt(0), 'E');
		assertEquals(owner.searchType("2").charAt(0), 'N');
		
	}
	
	@Test
	void testCompareTo(){
		
		setUpSceneOwner1();
		Owner o1=owner;
		setUpSceneOwner2();
		assertTrue(o1.compareTo(owner)<0);
		setUpSceneOwner1();
		assertEquals(o1.compareTo(owner),0);
		
	}
	
	@Test
	void testCompare(){
		
		setUpSceneOwner1();
		Owner o1=owner;
		setUpSceneOwner2();
		assertTrue(owner.compare(o1,owner)<0);
		setUpSceneOwner1();
		assertEquals(owner.compare(o1,owner),0);
		
	}
	
	@Test
	void testCompareLastName(){
		
		setUpSceneOwner1();
		Owner o1=owner;
		setUpSceneOwner2();
		assertTrue(o1.compareLastName(owner)<0);
		setUpSceneOwner1();
		assertEquals(o1.compareLastName(owner),0);
		
	}
	
	@Test
	void testCompareBirthdate(){
		
		setUpSceneOwner1();
		Owner o1=owner;
		setUpSceneOwner2();
		assertTrue(o1.compareBirthdate(owner)<0);
		setUpSceneOwner1();
		assertEquals(o1.compareBirthdate(owner),0);
		
	}
	
	@Test
	void testCompareFavoritePetType(){
		
		setUpSceneOwner1();
		Owner o1=owner;
		setUpSceneOwner2();
		assertTrue(o1.compareFavoritePetType(owner)<0);
		setUpSceneOwner1();
		assertEquals(o1.compareFavoritePetType(owner),0);
		
	}
	
	@Test
	void testComparePetQuantity(){
		
		setUpSceneOwner1();
		Owner o1=owner;
		setUpSceneOwner2();
		assertTrue(o1.comparePetQuantity(owner)<0);
		setUpSceneOwner1();
		assertEquals(o1.comparePetQuantity(owner),0);
		
	}
	
	@Test
	void testGetBirthdate(){
		
		setUpSceneOwnerError1();
		assertTrue(owner.getBirthdate() instanceof Date);
		setUpSceneOwnerError2();
		assertTrue(owner.getBirthdate() instanceof Date);
		
	}
	
}
