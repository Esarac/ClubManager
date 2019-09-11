package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import model.Club;
import model.Owner;
import model.Pet;

class TestClub {

	//Tested Class
	private Club club;
	
	//Scene
	private void setUpSceneClub1(){
		
		club=new Club("1","Apple","12/12/2010","Ardilla");
		
	}
	
	private void setUpSceneClub2(){
		
		club=new Club("2","Zara","1/1/2015","Zorro");
		club.addOwner(new Owner("1", "Alberto", "Ariza", "12/12/2005", "Ardilla"));
		
	}
	
	private void setUpSceneClubError1(){
		
		club=new Club("1","Apple","hola","Ardilla");
		
	}
	
	private void setUpSceneClubError2(){
		
		club=new Club("1","Apple","a/a/a","Ardilla");
		
	}
	
	//Test
	@Test
	void testAddOwner(){
		
		setUpSceneClub1();
		assertEquals(club.addOwner("1","1","1","1/1/1","1"),"Se ha agregado el dueno \"1\".");
		assertEquals(club.addOwner("1","1","1","1/1/1","1"),"El dueno con id \"1\" ya existe.");
	
	}
	
	@Test
	void testDeleteOwnerId(){
		
		setUpSceneClub1();
		club.addOwner("1","1","1","1/1/1","1");
		assertEquals(club.deleteOwnerId("1"),"Eliminaste el dueno con id \"1\".");
		assertEquals(club.deleteOwnerId("1"),"El dueno con id \"1\" no existe.");
		
	}
	
	@Test
	void testDeleteOwnerName(){
		
		setUpSceneClub1();
		club.addOwner("1","1","1","1/1/1","1");
		assertEquals(club.deleteOwnerName("1"),"Eliminaste el dueno con nombre \"1\".");
		assertEquals(club.deleteOwnerName("1"),"El dueno con nombre \"1\" no existe.");
		
	}
	
	@Test
	void testIdSort(){
		
		setUpSceneClub1();
		Owner o1=new Owner("3","3","3","1/1/2015","3");
		Owner o2=new Owner("2","2","2","1/5/2014","2");
		Owner o3=new Owner("1","1","1","2/1/2014","1");
		ArrayList<Owner> owners=new ArrayList<Owner>();
		owners.add(o3);
		owners.add(o2);
		owners.add(o1);
		
		club.addOwner(o1);
		club.addOwner(o2);
		club.addOwner(o3);
		club.idSort();
		
		assertArrayEquals(owners.toArray(),club.getOwners().toArray());
		
	}
	
	@Test
	void testNameSort(){
		
		setUpSceneClub1();
		Owner o1=new Owner("3","3","3","1/1/2015","3");
		Owner o2=new Owner("2","2","2","1/5/2014","2");
		Owner o3=new Owner("1","1","1","2/1/2014","1");
		ArrayList<Owner> owners=new ArrayList<Owner>();
		owners.add(o3);
		owners.add(o2);
		owners.add(o1);
		
		club.addOwner(o1);
		club.addOwner(o2);
		club.addOwner(o3);
		club.nameSort();
		
		assertArrayEquals(owners.toArray(),club.getOwners().toArray());
		
	}
	
	@Test
	void testLastNameSort(){
		
		setUpSceneClub1();
		Owner o1=new Owner("3","3","3","1/1/2015","3");
		Owner o2=new Owner("2","2","2","1/5/2014","2");
		Owner o3=new Owner("1","1","1","2/1/2014","1");
		ArrayList<Owner> owners=new ArrayList<Owner>();
		owners.add(o3);
		owners.add(o2);
		owners.add(o1);
		
		club.addOwner(o1);
		club.addOwner(o2);
		club.addOwner(o3);
		club.lastNameSort();
		
		assertArrayEquals(owners.toArray(),club.getOwners().toArray());
		
	}
	
	@Test
	void testBirthdateSort(){
		
		setUpSceneClub1();
		Owner o1=new Owner("3","3","3","1/1/2015","3");
		Owner o2=new Owner("2","2","2","1/5/2014","2");
		Owner o3=new Owner("1","1","1","2/1/2014","1");
		ArrayList<Owner> owners=new ArrayList<Owner>();
		owners.add(o3);
		owners.add(o2);
		owners.add(o1);
		
		club.addOwner(o1);
		club.addOwner(o2);
		club.addOwner(o3);
		club.birthdateSort();
		
		assertArrayEquals(owners.toArray(),club.getOwners().toArray());
		
	}
	
	@Test
	void testFavoritePetTypeSort(){
		
		setUpSceneClub1();
		Owner o1=new Owner("3","3","3","1/1/2015","3");
		Owner o2=new Owner("2","2","2","1/5/2014","2");
		Owner o3=new Owner("1","1","1","2/1/2014","1");
		ArrayList<Owner> owners=new ArrayList<Owner>();
		owners.add(o3);
		owners.add(o2);
		owners.add(o1);
		
		club.addOwner(o1);
		club.addOwner(o2);
		club.addOwner(o3);
		club.favoritePetTypeSort();
		
		assertArrayEquals(owners.toArray(),club.getOwners().toArray());
		
	}
	
	@Test
	void testPetQuantitySort(){
		
		setUpSceneClub1();
		Owner o1=new Owner("3","3","3","1/1/2015","3");
		o1.addPet(new Pet("1","1","1/1/1",'F',"1"));
		Owner o2=new Owner("1","1","1","2/1/2014","1");
		ArrayList<Owner> owners=new ArrayList<Owner>();
		owners.add(o2);
		owners.add(o1);
		
		club.addOwner(o1);
		club.addOwner(o2);
		club.petQuantitySort();
		
		assertArrayEquals(owners.toArray(),club.getOwners().toArray());
		
	}
	
	@Test
	void testSearchId(){
		
		setUpSceneClub1();
		Owner o1=new Owner("1","1","1","1/1/1","1");
		club.addOwner(o1);
		
		assertEquals(club.searchId("1").charAt(0), 'E');
		assertEquals(club.searchId("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchName(){
		
		setUpSceneClub1();
		Owner o1=new Owner("1","1","1","1/1/1","1");
		club.addOwner(o1);
		
		assertEquals(club.searchName("1").charAt(0), 'E');
		assertEquals(club.searchName("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchLastName(){
		
		setUpSceneClub1();
		Owner o1=new Owner("1","1","1","1/1/1","1");
		club.addOwner(o1);
		
		assertEquals(club.searchLastName("1").charAt(0), 'E');
		assertEquals(club.searchLastName("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchBirthdate(){
		
		setUpSceneClub1();
		Owner o1=new Owner("1","1","1","1/1/1","1");
		club.addOwner(o1);
		
		assertEquals(club.searchBirthdate("1/1/1").charAt(0), 'E');
		assertEquals(club.searchBirthdate("2/2/2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchFavoritePetType(){
		
		setUpSceneClub1();
		Owner o1=new Owner("1","1","1","1/1/1","1");
		club.addOwner(o1);
		
		assertEquals(club.searchFavoritePetType("1").charAt(0), 'E');
		assertEquals(club.searchFavoritePetType("2").charAt(0), 'N');
		
	}
	
	@Test
	void testCompareTo(){
		
		setUpSceneClub1();
		Club c1=club;
		setUpSceneClub2();
		assertTrue(c1.compareTo(club)<0);
		setUpSceneClub1();
		assertEquals(c1.compareTo(club),0);
		
	}
	
	@Test
	void testCompare(){
		
		setUpSceneClub1();
		Club c1=club;
		setUpSceneClub2();
		assertTrue(club.compare(c1,club)<0);
		setUpSceneClub1();
		assertEquals(club.compare(c1,club),0);
		
	}
	
	@Test
	void testCompareCreationDate(){
		
		setUpSceneClub1();
		Club c1=club;
		setUpSceneClub2();
		assertTrue(c1.compareCreationDate(club)<0);
		setUpSceneClub1();
		assertEquals(c1.compareCreationDate(club),0);
		
	}
	
	@Test
	void testComparePetType(){
		
		setUpSceneClub1();
		Club c1=club;
		setUpSceneClub2();
		assertTrue(c1.comparePetType(club)<0);
		setUpSceneClub1();
		assertEquals(c1.comparePetType(club),0);
		
	}
	
	@Test
	void testCompareOwnerQuantity(){
		
		setUpSceneClub1();
		Club c1=club;
		setUpSceneClub2();
		assertTrue(c1.compareOwnerQuantity(club)<0);
		setUpSceneClub1();
		assertEquals(c1.compareOwnerQuantity(club),0);
		
	}
	
	@Test
	void testGetBirthdate(){
		
		setUpSceneClubError1();
		assertTrue(club.getCreationDate() instanceof Date);
		setUpSceneClubError2();
		assertTrue(club.getCreationDate() instanceof Date);
		
	}

}
