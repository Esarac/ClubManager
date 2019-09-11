package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import model.Pet;

class TestPet {

	//Tested Class
	private Pet pet;
	
	//Scene
	private void setUpScenePet1(){
		
		pet=new Pet("1", "Alberto", "12/12/2015", 'F', "Ardilla");
		
	}

	private void setUpScenePet2(){
		
		pet=new Pet("2", "Zara", "1/1/2019", 'M', "Zorro");
		
	}
	
	private void setUpScenePetError1(){
		
		pet=new Pet("3", "Bruno", "hola", 'L', "Perro");
		
	}
	
	private void setUpScenePetError2(){
		
		pet=new Pet("3", "Bruno", "a/a/a", 'L', "Perro");
		
	}
	
	//Test
	@Test
	void testCompareTo(){
		
		setUpScenePet1();
		Pet p1=pet;
		setUpScenePet2();
		assertTrue(p1.compareTo(pet)<0);
		setUpScenePet1();
		assertEquals(p1.compareTo(pet),0);
		
	}
	
	@Test
	void testCompare(){
		
		setUpScenePet1();
		Pet p1=pet;
		setUpScenePet2();
		assertTrue(pet.compare(p1,pet)<0);
		setUpScenePet1();
		assertEquals(pet.compare(p1,pet),0);
		
	}
	
	@Test
	void testCompareBirthdate(){
		
		setUpScenePet1();
		Pet p1=pet;
		setUpScenePet2();
		assertTrue(p1.compareBirthdate(pet)<0);
		setUpScenePet1();
		assertEquals(p1.compareBirthdate(pet),0);
		
	}
	
	@Test
	void testCompareGender(){
		
		setUpScenePet1();
		Pet p1=pet;
		setUpScenePet2();
		assertTrue(p1.compareGender(pet)<0);
		setUpScenePet1();
		assertEquals(p1.compareGender(pet),0);
		
	}
	
	@Test
	void testCompareType(){
		
		setUpScenePet1();
		Pet p1=pet;
		setUpScenePet2();
		assertTrue(p1.compareType(pet)<0);
		setUpScenePet1();
		assertEquals(p1.compareType(pet),0);
		
	}
	
	@Test
	void testGetBirthdate(){
		
		setUpScenePetError1();
		assertTrue(pet.getBirthdate() instanceof Date);
		setUpScenePetError2();
		assertTrue(pet.getBirthdate() instanceof Date);
		
	}
	
}
