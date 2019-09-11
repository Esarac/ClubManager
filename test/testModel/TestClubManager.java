package testModel;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import model.ClubManager;

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
		
		
		
	}

	@Test
	void testDeleteClubName(){
		
		
		
	}
	
}
