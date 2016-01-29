package Milestones;
import java.io.Serializable;
import java.util.*;
/**
 * Documents users can create 
 * @author 
 *
 */
/**
 * @author Sheikh Faisal Anwar, Vanja Veselinovic, Nicholas Robidoux, Muhammad Kashif Siddiqui 
 *
 */
public class Document implements Comparable<Document>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tag;
	private Producer uploader;
	private String name;
	private int searchRelevance;//in each search this is calculated for the current user, and the documents are ranked by it
	private int likes;//no of likes
	protected ArrayList<User> likers = new ArrayList<User>();

	/**
	 * Constructor which initializes name of documents user who uploaded it and a tag corresponding to users tastes
	 * @param name
	 * @param user
	 * @param tag
	 */
	public Document(String name, Producer user, String tag){
		this.name = name;
		this.uploader = user;
		this.tag = tag;
		likes = 0;
	}
	
	
	/**
	 * Method to add user who liked the document to the array list
	 * @param u user who liked the document
	 */
	public void like(User u){
		if(!(likers.contains(u))){
			likers.add(u);
			likes++;
		}
	}
	/**
	 * Method to get the users who the liked the document
	 * @return array list of users who liked the document
	 */
	public ArrayList<User> getLikers(){
		return likers;
	}
	
	
	/**
	 * 
	 * @return a string representation of user liked to be used in console output
	 */
	public String getLikersString(){
		String s = "'"+name+"'"+" is Liked by:\n";
		for(User u: likers){
			s += u.getName() + ", ";
		}
		s = s.substring(0, s.length() - 2);
		s = s+"\n";
		return s;
	}
	
	
	/**Getter method
	 * @return name of document
	 */
	public String getName(){
		return name;
	}
	
	
	/**Getter method
	 * @return string of the taste 
	 */
	public String getTag(){
		return tag;
	}
	
	/**
	 * @return integer value of search relevance
	 */
	public int getSearchRelevance(){
		return searchRelevance;
	}
	/**
	 * @param x update the value of search relevance
	 */
	public void setSearchRelevance(int x){
		searchRelevance = x;
	}
	/**
	 * @param x update the value of search relevance
	 */
	public void increaseSearchRelevanceBy(int x){
		searchRelevance += x;
	}
	public void multiplySearchRelevanceBy(int x){
		searchRelevance = searchRelevance * x;
	}
	/**
	 * @return user who uploaded the document
	 */
	public User getUploader(){
		return uploader;
	}
	/**
	 * add the user that has liked the document to the array list
	 * @param user 
	 */
	public void addUser(User user){
		likers.add(user);
	}
	public int getNumLikes(){//count the number of users that have liked the document
		return likes;
	}
	
	public String toString() {
		return " "+name+" [ Uploader: "+uploader.getName()+" ], Likes: "+getNumLikes()+"   ";
	}
	@Override
	public int compareTo(Document d){
	     return(d.searchRelevance - searchRelevance);
	}

}
