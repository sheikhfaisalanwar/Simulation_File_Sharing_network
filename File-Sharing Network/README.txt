Run SimulationFrame.jar




This is what you will first see when running this program:
 
In the menu bar, click “Simulator”, then “Simulate n simulations”. This will allow you to enter the number of results you want, as well as the number of simulation cycles to simulate. We recommend 10 results, and 100 simulation cycles.


The GUI will then update with the last simulation cycles results, like so:
 
There are five components: the top menu bar, the User(s) list, the Document(s) list, and the Top Document(s) list.
Menu Bar
The three menus in the top menu bar have the following options:
Simulator
 
•	Simulate n cycles
•	Clear text area: clears the message area at the bottom of the window.
•	Add tastes: add new tastes to the list from which tastes can be chosen from when creating a new user.
•	Add producers, add consumers: allows you to add new users of the specified types.
•	Save/Load allows you to save a file with a specific file name and restore your work from the file.
•	Go back one simulation: allows you to iteration from all the no. of simulation iteration that were performed 

User
 
Each of these options only works if a user has been selected from the User(s) list. If not an error message will indicate to you that you must choose a user first.
•	Change ranking strategy: allows you to pick from a list of ranking strategies for the selected user.
•	View documents liked: prints the documents this user has liked to the text area.
•	View followers: prints a list of this user’s followers to the text area.
•	Number of followers: prints the number of followers this user has to the text area.
•	View following: prints the users this user follows to the text area.
•	View documents Viewed: prints a list of documents this user has viewed to the text area.
•	View payoff graph: displays a graph of this user’s payoff in a new window.
Document
 
This only works if a document has been selected from the Document(s) list of Top Document(s) list. If not an error message will indicate that you must choose a document first.
•	View likers: prints a list of users who have liked this document to the text area.



Contributers:
Sheikh Faisal Anwar:Initial Design, Payoff Graphs, Save/Load
Vanja Veselinovic:Overall design, Ranking Strategies, Undo,
Nicholas Robidoux: All unit tests, overall design
Muhammad Kashif Siddiqui:GUI, Garbage input handling







---------------------------------------------------------------------------------------------------------
