package Milestones;

import java.io.Serializable;

/**Class that describes the behavior of all ranking strategies
 * @author Sheikh Faisal Anwar, Vanja Veselinovic, Nicholas Robidoux, Muhammad Kashif Siddiqui 
 *
 */
public class RankingStrategy implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int likesWeight, followersWeight, likeSimilarityWeight, followSimilarityWeight, distanceWeight;
	
	/**
	 * Constructor for class
	 * @param name
	 * @param likesWeight integer representing the weight of likes
	 * @param followersWeight integer representing the weight of likes
	 * @param likeSimilarityWeight integer representing the weight of like similarity
	 * @param followSimilarityWeight integer representing the weight of follow similarity
	 * @param distanceWeight integer representing the weight of the total distance
	 */
	public RankingStrategy(String name, int likesWeight, int followersWeight, int likeSimilarityWeight, int followSimilarityWeight, int distanceWeight){
		this.name = name;
		this.likesWeight = likesWeight;
		this.followersWeight = followersWeight;
		this.likeSimilarityWeight = likeSimilarityWeight;
		this.followSimilarityWeight = followSimilarityWeight;
		this.distanceWeight = distanceWeight;
		
		if (likesWeight < 0) likesWeight = 0;
		else if (likesWeight > 10) likesWeight = 10;
		
		if (followersWeight < 0) followersWeight = 0;
		else if (followersWeight > 10) followersWeight = 10;
			
		if (likeSimilarityWeight < 0) likeSimilarityWeight = 0;
		else if (likeSimilarityWeight > 10) likeSimilarityWeight = 10;
		
		if (followSimilarityWeight < 0) followSimilarityWeight = 0;
		else if (followSimilarityWeight > 10) followSimilarityWeight = 10;
		
		if (distanceWeight < 0) distanceWeight = 0;
		else if (distanceWeight > 10) distanceWeight = 10;
	}
	
	/**
	 * @return the name of the ranking strategy
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return weight for the likes
	 */
	public int getLikesWeight(){
		return likesWeight;
	}
	
	/** 
	 * @return weight of followers
	 */
	public int getFollowersWeight(){
		return followersWeight;
	}
	
	/**
	 * @return weight of likes similarity
	 */
	public int getLikeSimilarityWeight(){
		return likeSimilarityWeight;
	}
	
	/**
	 * @return weight of follow similarity
	 */ 
	public int getFollowSimilarityWeight(){
		return followSimilarityWeight;
	}
	
	/**
	 * @return weight of distance
	 */
	public int getDistanceWeight(){
		return distanceWeight;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return name;
	}
}
