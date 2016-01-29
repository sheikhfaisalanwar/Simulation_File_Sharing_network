package Milestones;
//Check interface for documentation on methods


import java.io.Serializable;

import javax.swing.DefaultListModel;

/**
 * Type of user with the ability to do all actions of the consumer along with the creation of documents
 * @author 
 */
public class Producer extends Consumer implements User, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<Document> documentsUploaded = new DefaultListModel<Document>();
	

	/**
	 * Constructor which initializes user with name,password,tag and automatically initiates type of user to producer
	 * @param name
	 * @param password
	 * @param tag
	 */
	public Producer(String name, String password, String tag, RankingStrategy rankingStrategy){
		super(name, password, tag, "producer", rankingStrategy);
	}
	
	@Override
	public void act(Simulator n, SimulatorFrame gui){//need to shift this to network and make multiple acts
		n.addDoc(createDocument(name+taste+(documentsUploaded.size()+1), taste));
		gui.appendTextArea("Document '"+name+taste+(documentsUploaded.size()+1)+"' created!\n");
		super.act(n, gui);
	}

	
	/**
	 * Method to create a Document with a name and a tag which might correspond to users tastes
	 * Also likes the created document to increase search relevance
	 * @param name
	 * @param tag
	 * @return this document 
	 */
	public Document createDocument(String name, String tag){
		Document d = new Document(name, this, tag);
		documentsUploaded.addElement(d);
		documentsViewed.add(d);
		//System.out.println("Document "+d.getName()+" created.");
		like(d);
		return d;
	}
	
	
	/**
	 * Method to get the list of documents uploaded by current user
	 * @return DefaultListModel of Documents
	 */
	public DefaultListModel<Document> getDocumentsUploaded(){
		return documentsUploaded;
	}

}
