package Milestones;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.DefaultListModel;

/*<h1> Consumer </h1>
 * Type of user with ability like and search documents and follow users
 */ 



/**
 * Consumer
 * Type of user with the ability to like, search documents
 * follow users, act in the case of being randomly selected
 * @author 
 *
 */
public class Consumer implements User, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String password;
	protected String taste;
	protected String type;
	protected String s;
	protected ArrayList<Document> documentsLiked = new ArrayList<Document>();
	protected ArrayList<Document> documentsViewed = new ArrayList<Document>();
	protected ArrayList<User> following = new ArrayList<User>();
	protected ArrayList<User> followers = new ArrayList<User>();
	private ArrayList<Integer> payoffs;
	private RankingStrategy rankingStrategy;
	private Random randGen;
	
	public DefaultListModel<Document> results = new DefaultListModel<Document>();
	
	/**
	 * Constructor which initializes user with name,password,tag and automatically initiates type of user to consumer
	 * @param name
	 * @param password
	 * @param tag
	 * @param rankingStrategy
	 */
	public Consumer(String name, String password, String tag, RankingStrategy rankingStrategy){
		this(name, password, tag, "consumer", rankingStrategy);
	}
	
	/**
	 * Initializes all variables like above constructor with the addition of an Array list of payoffs 
	 * @param name
	 * @param password
	 * @param tag
	 * @param type
	 * @param rankingStrategy
	 */
	public Consumer(String name, String password, String tag, String type, RankingStrategy rankingStrategy){
		this.name = name;
		this.password = password;
		this.taste = tag;
		this.type = type;
		this.rankingStrategy = rankingStrategy;
		payoffs = new ArrayList<Integer>();
		randGen = new Random();
	}
	
	@Override
	public void setRankingStrategy(RankingStrategy rankingStrategy){
		this.rankingStrategy = rankingStrategy;
	}

	/* 
	 * 
	 */
	@Override
	public void act(Simulator s, SimulatorFrame gui){
		if (s.getDocs().size() == 0) {
			//Append everyone's payoff as zero
			for(int i = 0; i < s.getUsers().size();i++){
				s.getUsers().get(i).appendPayoff(0);
			}
			gui.appendTextArea("There are no documents yet.\n\n");
		}
		else{
			gui.topKResults = search(s.getDocs(), s.getK(), s, gui);
			gui.topkdocs.setModel(gui.topKResults);
			gui.revalidate();
			gui.repaint();
		}
	}

	@Override
	public boolean like(Document document) {
		if (!(documentsLiked.contains(document))){
			documentsLiked.add(document);
			document.like(this);
			return true;
		}
		return false;
	}

	@Override
	public boolean follow(User user) {
		if ((!(following.contains(user))) && (!(user == this))){
			following.add(user);
			user.addFollower(this);
			return true;
		}
		return false;
	}

	@Override
	public void addFollower(User user){
		followers.add(user);
	}

	public DefaultListModel<Document> getResults(){
		return results;
	}

	@Override
	public ArrayList<Document> getViewed() {
		return documentsViewed;
	}

	@Override
	public ArrayList<User> getFollowing() {
		return following;
	}

	@Override
	public ArrayList<Document> getLiked() {
		return documentsLiked;
	}

	@Override
	public ArrayList<User> getFollowers() {
		return followers;
	}
	
	@Override
	public DefaultListModel<Document> search(DefaultListModel<Document> docs, int k, Simulator sim, SimulatorFrame gui) {
		results.clear(); //Clear Results DLM so as to not have duplicates by the end
		
		for (int i = 0; i < docs.size(); i++){
			results.addElement(docs.get(i));
		}
		
		//for each document, calculate the relevance with a few steps
		// v, w, x, y, z are determined by the RankingStrategy of the user
		for (int i = 0; i < results.size(); i++){
			Document d = results.get(i);
			
			//STEP 0: reset search relevance
			d.setSearchRelevance(0);
			
			//STEP 1: LIKES - give v points for each like the document has
			d.increaseSearchRelevanceBy(rankingStrategy.getLikesWeight() * d.getNumLikes());
			
			//STEP 2: FOLLOWERS - give w points for each follower the uploader of the document has
			d.increaseSearchRelevanceBy(rankingStrategy.getFollowersWeight() * d.getUploader().getNumFollowers());
			
			//STEP 3: LIKE SIMILARITY - give x points for each document the uploader has liked that you've also liked
			int countLikeSimilarity = 0;
			for (Document dl : d.getUploader().getLiked()){
				if (documentsLiked.contains(dl)) countLikeSimilarity++;		
			}
			d.increaseSearchRelevanceBy(rankingStrategy.getLikeSimilarityWeight() * countLikeSimilarity);
			
			//STEP 4: FOLLOW SIMILARITY - give y points for each user the uploader follows that you also follow
			int countFollowSimilarity = 0;
			for (User uf : d.getUploader().getFollowing()){
				if (documentsLiked.contains(uf)) countFollowSimilarity++;		
			}
			d.increaseSearchRelevanceBy(rankingStrategy.getFollowSimilarityWeight() * countFollowSimilarity);
			
			//STEP 5: DISTANCE - if you already follow the uploader of the document, multiply the points by z
			d.multiplySearchRelevanceBy(rankingStrategy.getDistanceWeight());
			
			//STEP 6: if the document tag matches the taste of the user, multiply the points by 2
			if (d.getTag().equals(taste)){
				d.increaseSearchRelevanceBy(d.getSearchRelevance());
				//STEP 6.1: if matching tag and the document hasn't been viewed yet, multiply the points by 100 to guarantee it shows up in the search
				if (!(documentsViewed.contains(d))){
					d.multiplySearchRelevanceBy(100);
				}
			}
			//STEP 6.2: if not matching tag and the document hasn't been viewed yet, multiply the points by 2
			if (!(documentsViewed.contains(d))){
				d.increaseSearchRelevanceBy(d.getSearchRelevance());
			}
		}
						
		//sort results by search relevance which we just calculated
		ArrayList<Document> arrayList = Collections.list(results.elements()); //Put elements of DLM to ArrayList
		Collections.sort(arrayList); // sort Array
		results.clear(); // remove all elements

		//Add the Top K (from parameter of this method) Documents from Array back into Results DLM
		int x = 0;
		for(Document o:arrayList){
			if (x<k) {
				results.addElement(o); 
				x++;
			}
		} // Documents Added
				
		if (results.size() == 0) gui.appendTextArea("No results found.\n");
		else{
			int resultsSize = k;
			if (k > results.size()) resultsSize = results.size();

			//payoff: 1 point for each document in the top k that matches the user's taste and hasn't been viewed yet
			int payoff = 0;
			
			/*
			 * Strategy B) pick a taste other than your own, and 'likeï' documents that match them and users
			 * that 'like' them. Here the idea is to influence the similarity rankings of the
			 * consumers.
			 * 
			 * Here we randomly select another taste that will be liked and followed in the a) section
			 */
			
			//pick a random other taste other than our own (only for producers)
			String randomOtherTaste = "";
			if (type.equals("producer")){
				do{
					randomOtherTaste = sim.getTastesList().get(randGen.nextInt(sim.getTastesList().size()));
				}while(randomOtherTaste.equals(taste)); //This while loop ensures we pick a taste other than our own
			}
			
			//Strategy A)
			
			Document r;
			for (int i = 0; i < resultsSize; i++){
				r = results.get(i);
				gui.appendTextArea((i+1)+". "+r.toString()+"\n");
				if (documentsLiked.contains(r)) {
					gui.appendTextArea("Already liked the document: '"+r.getName()+"'.\n");
				}
				if (!documentsViewed.contains(r)){
					documentsViewed.add(r);
					/* Here if randomOtherTaste is "" we're a consumer and it will be ignored and only like/follow documents of our taste
					 * if randomOtherTaste is something else we're a consumer and both our taste and randomOtherTaste will be liked/followed, as b) explains
					 */
					if ((r.getTag().equals(taste)) || (r.getTag().equals(randomOtherTaste))){
						if (like(r)){
							gui.appendTextArea("Liked the document: '"+r.getName()+"'.\n");
							
						}
						for (User u : r.getLikers()){
							if (follow(u)){
								gui.appendTextArea("Followed the User: "+u.getName()+"[ "+u.getType()+"] of taste '"+u.getTaste()+"'.\n");
							}

						}

					}
				}
			}
			
			/*
			 *Payoff Calculation 
			 *Strategy: add one point for each document in the top k that hasn't been seen by the user yet and that matches the userâ€™s taste
			 */
			for (Document v : documentsViewed){
				for(int i = 0 ; i < resultsSize; i ++){
					if( results.get(i) != v && results.get(i).getTag().equals(taste)) {
						payoff ++;
					}
				}
			}
			
			gui.appendTextArea(" \n");
			/* Append the payoff of users not selected this simulation with previous iteration's payoff
			 * Append the payoff the user selected this simulation with payoff of previous iteration + this iteration's payoff
			 */
			for(int i = 0; i < sim.getUsers().size();i++){
				
				//Temporary array to hold current user in  the for loops' payoff
				ArrayList<Integer> temp = new ArrayList<Integer>();
				temp = sim.getUsers().get(i).getPayoff();
				
				//For all users not selected this iteration, append previous iteration's payoff
				if ( !(sim.getUsers().get(i).getName().equals(this.name)) ){
					
					if (temp.size() == 0) sim.getUsers().get(i).appendPayoff(0);
					if (temp.size() == 1) sim.getUsers().get(i).appendPayoff(temp.get(0));
					else{
						sim.getUsers().get(i).appendPayoff(temp.get( (temp.size()-1) ));
					}
					
				}
				
				//For User selected this iteration, append previous iteration + current iteration's payoff
				else {
					if (temp.size() == 0) sim.getUsers().get(i).appendPayoff(payoff);
					if (temp.size() == 1) sim.getUsers().get(i).appendPayoff(temp.get(0)+payoff);
					else{
						sim.getUsers().get(i).appendPayoff( (temp.get(temp.size()-1)) + payoff);
					}
				}
			}
						
		}
				
		return results;
	}
	
	public void appendPayoff(int payoff){
		payoffs.add(payoff);
	}
	
	public void setPayoff(int index, int element){
		payoffs.set(index, element);
	}
	
	@Override
	public String getName(){
		return name;
	}

	public String getTaste(){
		return taste;
	}
	
	public String getType(){
		return type;
	}
	
	public RankingStrategy getRankingStrategy(){
		return rankingStrategy;
	}
	
	public int getNumFollowers(){
		return followers.size();
	}
	public ArrayList<Integer> getPayoff(){
		return payoffs;
	}
	
	public String toString() {
		return " "+name +" [ "+type+" ], Taste: "+taste+", Strategy: "+rankingStrategy.getName()+"   ";
	}

}
