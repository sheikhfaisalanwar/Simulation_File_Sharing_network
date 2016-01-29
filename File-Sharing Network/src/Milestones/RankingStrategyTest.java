package Milestones;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RankingStrategyTest{
 
    private RankingStrategy rs;
    
    @Before
    public void setUp() throws Exception {
        rs = new RankingStrategy("test",1,2,3,4,5);
    }
    
    @Test
    public void testGetName(){
    	assertEquals("test", rs.getName());
    }
    
    @Test
    public void testGetLikesWeight(){
    	assertEquals(1, rs.getLikesWeight());
    }
    
    @Test
    public void testGetFollowersWeight(){
    	assertEquals(2, rs.getFollowersWeight());
    }
    
    @Test
    public void testGetLikeSimilarityWeight(){
    	assertEquals(3, rs.getLikeSimilarityWeight());
    }
    
    @Test
    public void testGetFollowSimilarityWeight(){
    	assertEquals(4, rs.getFollowSimilarityWeight());
    }
    
    @Test
    public void testGetDistanceWeight(){
    	assertEquals(5, rs.getDistanceWeight());
    }
    
    @Test
    public void testToString(){
    	assertEquals("test", rs.toString());
    }
}
   