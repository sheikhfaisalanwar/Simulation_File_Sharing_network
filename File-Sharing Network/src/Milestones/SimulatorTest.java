package Milestones;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SimulatorTest{
	
	Consumer c1;
	Consumer c2;

	Producer p1;

	Document d1;
	Document d2;
	
	RankingStrategy rs;
	
	Simulator s;
	
	@Before
	public void setUp() throws Exception {
		
		rs = new RankingStrategy("",1,2,0,0,0);
		
		c1 = new Consumer("c1","pw","book", rs);
		
		c2 = new Consumer("c2","pw","book", rs);

		p1 = new Producer("p6","pw","book", rs);

		d1 = new Document("Book1", p1,"book");

		d2 = new Document("Book2", p1,"book");
		
		s = new Simulator("Sim");
		

	}
	
	
	@Test 
	public void testAddAndRemoveUser(){
		assertEquals("Test for init size", 0, s.getUsers().size());
		s.addUser(c1);
		assertEquals("Test for size +1", c1, s.getUsers().get(0));
		s.removeUser(0);
		assertEquals("Test for init size", 0, s.getUsers().size());

	}
	
	@Test 
	public void testAddAndRemoveDoc(){
		assertEquals("Test for empty list", 0, s.getDocs().size());
		s.addDoc(d1);
		assertEquals("Test for size 1", d1, s.getDocs().get(0));
		s.removeDoc(0);
		assertEquals("Test for empty list", 0, s.getDocs().size());

	}
	
	@Test 
	public void testAddTaste(){
		assertEquals("Test for empty list", 0, s.getTastesList().size());
		s.addTaste("Toy");
		assertEquals("Test for empty list", 1, s.getTastesList().size());
	}
	
	@Test
	public void testSetAndGetK(){
		s.setK(5);
		
		assertEquals("K should be 5", 5, s.getK());
	}

	@Test
	public void testGetDocs(){
		s.addDoc(d1);
		assertEquals(d1, s.getDocs().get(0));
	}

	@Test
	public void testGetUsers(){
		s.addUser(c1);
		assertEquals(c1, s.getUsers().get(0));
	}

	@Test
	public void testGetTastesList(){
		s.addTaste("book");
		assertEquals("book", s.getTastesList().get(0));
	}

	@Test
	public void testToString(){
		String sr = "User(s):\n";

		s.addUser(c1);

		for (int i = 0; i < 1; i++) {
			sr += s.getUsers().get(i).getType()+" "+s.getUsers().get(i).getName()+" "+s.getUsers().get(i).getTaste() + "\n";
		}

		assertEquals(sr, s.toString());
		


	}
	
	//Simulate is tested throught act which is tested through search


}