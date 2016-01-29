package Milestones;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DocumentTest{
	

	Consumer c1;
	Consumer c2;

	Producer p1;

	Document d1;
	Document d2;
	
	RankingStrategy rs = new RankingStrategy("",1,2,0,0,0);

	@Before
	public void setUp() throws Exception{
		c1 = new Consumer("c1","pw","book",rs);
		
		c2 = new Consumer("c2","pw","book",rs);

		p1 = new Producer("p6","pw","book",rs);

		d1 = new Document("Book1", p1,"book");

		d2 = new Document("Book2", p1,"book");
	}
	
	@Test 
	public void testAddUser(){
		assertEquals("Test for empty list", 0, d1.getLikers().size());
		d1.addUser(c1);
		assertEquals("Test for empty list", 1, d1.getLikers().size());
	}

	@Test
	public void testLike(){

		assertEquals("Test for empty list", 0, d1.getNumLikes());

		d1.like(c1);

		assertEquals("Test for list size 1", 1, d1.getNumLikes());

		d1.like(c1);	

		assertEquals("Test for list size to still be 1", 1, d1.getNumLikes());


	}

	@Test
	public void testGetLikers(){
		d1.like(c1);
		assertEquals(1, d1.getNumLikes());
	}

	@Test
	public void testCompareTo(){
		d2.like(c2);

		assertEquals("Should return 0", 0, d2.compareTo(d1));

	}
	
	public void testSearchRelevances(){
		assertEquals("Should return 0", 0, d2.getSearchRelevance());
		
		d2.increaseSearchRelevanceBy(1);
		
		assertEquals("Should return 1", 1, d2.getSearchRelevance());
		
		d2.setSearchRelevance(0);
		
		assertEquals("Should return 0", 0, d2.getSearchRelevance());
		
	}

	@Test
	public void testGetLikersString(){
		String sr = "'"+d1.getName()+"'"+" is Liked by:\n";
		for(User u: d1.getLikers()){
			sr += u.getName() + ", ";
		}
		sr = sr.substring(0, sr.length() - 2);
		sr = sr+"\n";

		assertEquals(sr,d1.getLikersString());

	}

	@Test
	public void testGetName(){
		assertEquals("Test for d1",  "Book1", d1.getName());

	}

	@Test
	public void testGetTag(){
		assertEquals("Test for d1",  "book", d1.getTag());

	}

	@Test
	public void testGetUploader(){
		Document d = p1.createDocument("doc1", "book");

		assertEquals(p1, d.getUploader());

	}

	@Test
	public void testToString(){
		assertEquals(" " +d1.getName() + " [ Uploader: "+ d1.getUploader().getName()+ " ], Likes: " + d1.getNumLikes()+"   ", d1.toString() );
	}

	@Test
	public void testGetSearchRelevance(){
		assertEquals(0, d1.getSearchRelevance());
	}
	
	@Test
	public void testSetSearchRelevance(){
		d1.setSearchRelevance(1);
		assertEquals(1, d1.getSearchRelevance());
	}
	
	@Test
	public void testIncreaseSearchRelevanceBy(){
		d1.increaseSearchRelevanceBy(1);
		assertEquals(1, d1.getSearchRelevance());
	}
	
	@Test
	public void testMultiplySearchRelevanceBy(){
		d1.multiplySearchRelevanceBy(1);
		assertEquals(0, d1.getSearchRelevance());
	}
}
