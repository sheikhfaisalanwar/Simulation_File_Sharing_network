package Milestones;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SimulatorFrameTest{
	

	Consumer c1;
	Consumer c2;

	Producer p1;

	Document d1;
	Document d2;
	
	RankingStrategy rs = new RankingStrategy("",1,2,0,0,0);

	Simulator s;
	SimulatorFrame sf;

	@Before
	public void setUp() throws Exception{
		c1 = new Consumer("c1","pw","book",rs);
		
		c2 = new Consumer("c2","pw","book",rs);

		p1 = new Producer("p6","pw","book",rs);

		d1 = new Document("Book1", p1,"book");

		d2 = new Document("Book2", p1,"book");


		sf = new SimulatorFrame();
		s = new Simulator("Sim");
	}

	//test setTextArea getInformation, append text area
	@Test 
	public void testTextArea(){
		sf.setTextArea("test");
		assertEquals("test", sf.getInformation());
		sf.appendTextArea(" test2");
		assertEquals("test test2", sf.getInformation());
	}

	@Test
	public void testIterations(){
		sf.setIterations(5);
		assertEquals(5, sf.getIterations());

	}

	@Test
	public void testGetRankingStrategyStringArray(){
		assertEquals(7, sf.getRankingStrategyStringArray().size());
	}

	@Test 
	public void testMultipleUsers(){
		int users = sf.getSim().getUsers().getSize();
		sf.multipleUsers("test", s);
		
		
		assertEquals(users, sf.getSim().getUsers().getSize());

	}
	
	@Test
	public void testCountCycles(){
		int i = sf.getCountCycles();
		sf.iterateCountCycles();
		assertEquals(i+1, sf.getCountCycles());
	
	}

	

}