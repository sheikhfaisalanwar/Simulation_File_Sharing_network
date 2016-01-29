package Milestones;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ProducerTest{
    
    Consumer c1;
	Consumer c2;

	Producer p1;

	Document d1;
	Document d2;
	
	RankingStrategy rs;
	
	Simulator s;
	SimulatorFrame sf;
    
    @Before
	public void setUp() throws Exception {
    	rs = new RankingStrategy("",1,2,0,0,0);
    	
		c1 = new Consumer("c1","pw","book", rs);
		
		c2 = new Consumer("c2","pw","book", rs);

		p1 = new Producer("John","pw","book", rs);

		d1 = new Document("Book1", p1,"book");

		d2 = new Document("Book2", p1,"book");
		
		s = new Simulator("Sim");

		sf = new SimulatorFrame();
		

	}
	
    @Test 
    public void createDocument(){
        Document d; 
        
        d = p1.createDocument("Doll", "toy");

        assertEquals("Doc should have 1 like", 1, d.getNumLikes());
    }
    
    @Test
    public void testGetDocumentsUploaded(){
        assertEquals(0, p1.getDocumentsUploaded().size());
    }
    
    @Test 
    public void testAct(){
    	p1.act(s, sf);

    	assertEquals(1, p1.getDocumentsUploaded().size());
        
    }

}