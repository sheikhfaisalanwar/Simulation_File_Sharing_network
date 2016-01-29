package Milestones;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest{

    private Consumer c1;
    private Consumer c2;
    
    private Producer p1;
    private Document d1;
    
    private RankingStrategy rs;

    private Simulator s;
    private SimulatorFrame sf;
    
    @Before
    public void setUp() throws Exception {
        rs = new RankingStrategy("",1,2,0,0,0);
    	
        c1 = new Consumer("c1","pw","book", rs);
        c2 =  new Consumer("c2","pw","book", rs);

        p1 = new Producer("p6","pw","book", rs);

        d1 = new Document("Book1", p1,"book");
        
        s = new Simulator("Sim");

        sf = new SimulatorFrame();

    }
    @Test
    public void testActIncludingSearch(){
        s.addUser(new Consumer("c1","pw","toy", rs));
        s.addUser(new Consumer("c2","pw","car", rs));
        s.addUser(new Consumer("c3","pw","book", rs));
        s.addUser(new Consumer("c4","pw","toy", rs));
        s.addUser(new Consumer("c5","pw","car", rs));
        s.addUser(new Consumer("c6","pw","book", rs));
                
        s.addUser(new Producer("p1","pw","toy", rs));
        s.addUser(new Producer("p2","pw","car", rs));
        s.addUser(new Producer("p3","pw","book", rs));
        s.addUser(new Producer("p4","pw","toy", rs));
        s.addUser(new Producer("p5","pw","car", rs));
        s.addUser(new Producer("p6","pw","book", rs));

        s.setK(10);
        c1.act(s,sf);
        
        assertEquals(0, c1.getLiked().size());

    }

    @Test
    public void testLike(){
                
        c1.like(d1);
        
        assertEquals("Test for 1 like", 1 , c1.getLiked().size());

        c1.like(d1);

        assertEquals("Test for still 1 like", 1 , c1.getLiked().size());

    }
    
    @Test
    public void testFollow(){
        c1.follow(p1);

        assertEquals("Test for 1 follower", 1 , p1.getFollowers().size());

        c1.follow(p1);

        assertEquals("Test still 1 follower", 1 , p1.getFollowers().size());

    }
    
    
    @Test
    public void testAddFollower(){
        p1.addFollower(c1);
        
        assertEquals("Test for size 1", 1 , p1.getFollowers().size());
        
        p1.addFollower(c2);
        
        assertEquals("Test for size 2", 2 , p1.getFollowers().size());
    }

    public void testGetViewed(){

        assertEquals(0, c1.getViewed().size());

    }

    @Test
    public void testGetFollowingAndFollower(){
        c2.follow(c1);
        
        assertEquals("Test for size 1", 1 , c2.getFollowing().size());

        assertEquals("Test for size 1", 1 , c1.getFollowers().size());

    }

    @Test
    public void testGetLiked(){
        assertEquals("Test for 0 like",  0, c1.getLiked().size());

    }

    @Test
    public void testGetName(){
        assertEquals("Test for p6",  "c1", c1.getName());

    }

    @Test
    public void testGetTaste(){
        assertEquals("Test for book",  "book", c1.getTaste());

    }

    @Test
    public void testGetType(){
        assertEquals("consumer", c1.getType());

    }
    
    @Test
    public void testGetRankingStrategy(){
    	assertEquals(rs, c1.getRankingStrategy());
    }
    
    @Test
    public void testGetNumFollowers(){
    	assertEquals(0, c1.getNumFollowers());
    }
    
    @Test
    public void testGetPayoffs(){
    	assertEquals(0, c1.getPayoff().size());
    }
    
    @Test
    public void testCreateDocument(){
    	Document doc = p1.createDocument("a","b");
    	assertEquals("a", doc.getName());
    }
    
    @Test
    public void testGetDocumentsUploaded(){
    	assertEquals(0, p1.getDocumentsUploaded().size());
    }
    
    @Test
    public void testToString(){
        assertEquals(" "+c1.getName() +" [ "+c1.getType()+" ], Taste: "+c1.getTaste()+", Strategy: "+c1.getRankingStrategy().getName()+"   ", c1.toString());
    }
   
}
   