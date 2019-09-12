package testModel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import model.Club;
import model.ClubManager;
import model.Owner;

class TestClubManager {

	//Tested Class
	private ClubManager manager;
	
	//Scene
	private void setUpSceneClubManager(){
		
		manager=new ClubManager();
		
	} 
	
	//Test
	@Test
	void testAddClub(){
		
		setUpSceneClubManager();
		assertEquals(manager.addClub("1","1","1/1/1","1"), "Se ha agregado el club \"1\".");
		assertEquals(manager.addClub("1","1","1/1/1","1"), "El club con id \"1\" ya existe.");
		assertEquals(manager.addClub("2","1","1/1/1","1"), "El club con nombre \"1\" ya existe.");
		
	}
	
	@Test
	void testDeleteClubId(){
		
		setUpSceneClubManager();
		manager.addClub("1","1","1/1/1","1");
		assertEquals(manager.deleteClubId("1"),"Eliminaste el club con id \"1\".");
		assertEquals(manager.deleteClubId("1"),"El club con id \"1\" no existe.");
		
	}

	@Test
	void testDeleteClubName(){
		
		setUpSceneClubManager();
		manager.addClub("1","1","1/1/1","1");
		assertEquals(manager.deleteClubName("1"),"Eliminaste el club con nombre \"1\".");
		assertEquals(manager.deleteClubName("1"),"El club con nombre \"1\" no existe.");
		
	}
	
	@Test
	void testIdSort(){
		
		setUpSceneClubManager();
		Club c1=new Club("3","3","1/1/2015","3");
		Club c2=new Club("2","2","1/5/2014","2");
		Club c3=new Club("1","1","2/1/2014","1");
		ArrayList<Club> clubs=new ArrayList<Club>();
		clubs.add(c3);
		clubs.add(c2);
		clubs.add(c1);
		
		manager.addClub(c1);
		manager.addClub(c2);
		manager.addClub(c3);
		manager.idSort();
		
		assertArrayEquals(clubs.toArray(), manager.getClubs().toArray());
		
	}
	
	@Test
	void testNameSort(){
		
		setUpSceneClubManager();
		Club c1=new Club("3","3","1/1/2015","3");
		Club c2=new Club("2","2","1/5/2014","2");
		Club c3=new Club("1","1","2/1/2014","1");
		ArrayList<Club> clubs=new ArrayList<Club>();
		clubs.add(c3);
		clubs.add(c2);
		clubs.add(c1);
		
		manager.addClub(c1);
		manager.addClub(c2);
		manager.addClub(c3);
		manager.nameSort();
		
		assertArrayEquals(clubs.toArray(), manager.getClubs().toArray());
		
	}
	
	@Test
	void testCreationDateSort(){
		
		setUpSceneClubManager();
		Club c1=new Club("3","3","1/1/2015","3");
		Club c2=new Club("2","2","1/5/2014","2");
		Club c3=new Club("1","1","2/1/2014","1");
		ArrayList<Club> clubs=new ArrayList<Club>();
		clubs.add(c3);
		clubs.add(c2);
		clubs.add(c1);
		
		manager.addClub(c1);
		manager.addClub(c2);
		manager.addClub(c3);
		manager.creationDateSort();
		
		assertArrayEquals(clubs.toArray(), manager.getClubs().toArray());
		
	}
	
	@Test
	void testPetTypeSort(){
		
		setUpSceneClubManager();
		Club c1=new Club("3","3","1/1/2015","3");
		Club c2=new Club("2","2","1/5/2014","2");
		Club c3=new Club("1","1","2/1/2014","1");
		ArrayList<Club> clubs=new ArrayList<Club>();
		clubs.add(c3);
		clubs.add(c2);
		clubs.add(c1);
		
		manager.addClub(c1);
		manager.addClub(c2);
		manager.addClub(c3);
		manager.petTypeSort();
		
		assertArrayEquals(clubs.toArray(), manager.getClubs().toArray());
		
	}
	
	@Test
	void testOwnerQuantitySort(){
		
		setUpSceneClubManager();
		Club c1=new Club("3","3","1/1/2015","3");
		c1.addOwner(new Owner("1","1","1","1/1/1","1"));
		Club c2=new Club("1","1","2/1/2014","1");
		ArrayList<Club> clubs=new ArrayList<Club>();
		clubs.add(c2);
		clubs.add(c1);
		
		manager.addClub(c1);
		manager.addClub(c2);
		manager.ownerQuantitySort();
		
		assertArrayEquals(clubs.toArray(), manager.getClubs().toArray());
		
	}
	
	@Test
	void testSearchId(){
		
		setUpSceneClubManager();
		Club c1=new Club("1","1","1/1/1","1");
		manager.addClub(c1);
		
		assertEquals(manager.searchId("1").charAt(0), 'E');
		assertEquals(manager.searchId("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchName(){
		
		setUpSceneClubManager();
		Club c1=new Club("1","1","1/1/1","1");
		manager.addClub(c1);
		
		assertEquals(manager.searchName("1").charAt(0), 'E');
		assertEquals(manager.searchName("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchCreationDate(){
		
		setUpSceneClubManager();
		Club c1=new Club("1","1","1/1/1","1");
		manager.addClub(c1);
		
		assertEquals(manager.searchCreationDate("1/1/1").charAt(0), 'E');
		assertEquals(manager.searchCreationDate("2/2/2").charAt(0), 'N');
		
	}
	
	@Test
	void testSearchPetType(){
		
		setUpSceneClubManager();
		Club c1=new Club("1","1","1/1/1","1");
		manager.addClub(c1);
		
		assertEquals(manager.searchPetType("1").charAt(0), 'E');
		assertEquals(manager.searchPetType("2").charAt(0), 'N');
		
	}
	
	@Test
	void testSetActualClub(){
		
		setUpSceneClubManager();
		Club c1=new Club("1","1","1/1/1","1");
		manager.addClub(c1);
		
		assertFalse(manager.inClub());
		manager.setActualClub("1");
		assertTrue(manager.inClub());
		
	}
	
	@Test
	void testSetActualOwner(){
		
		setUpSceneClubManager();
		Club c1=new Club("1","1","1/1/1","1");
		Owner o1=new Owner("1","1","1","1/1/1","1");
		c1.addOwner(o1);
		manager.addClub(c1);
		
		assertEquals(manager.setActualOwner("1"),"No estas posicionado en un club.");
		manager.setActualClub("1");
		assertFalse(manager.inOwner());
		manager.setActualOwner("1");
		assertTrue(manager.inOwner());
		
	}
	
}
