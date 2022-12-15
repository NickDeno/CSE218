CSE218 Final Project-Social Media Application

How To Run Application:
	1) Ensure JavaFX Libraries are installed in Eclipse: For example, create a User Library that contains all JavaFx jars called "MyJavaFXLib" and add
	   library to project by right-clicking on project -> Build Path -> Configure Build Path -> ClassPath -> Add Library -> User Library -> then add your JavaFX 
	   library.
	      
	2) Add External Jars: If a "Referenced Libraries" library containing RichTextFx already appears in the project folder, you can skip this step.
	   The external jars used for this program are located in the "ExternalLibs" folder. To add these jars to class path, go to ExternalLibs folder -> right-click on 
	   jar file -> Build Path -> Add to Build Path. The jar will then be added to a "Referenced Libraries" library in the project. This only has to be done for the 
	   first time, after that the jar will always be in the class path. Also if you prefer, you can add the external jars directly to your "MyJavaFXLib" library 
	   instead.	
	      
	3) Add runtime args to "AppDemo": --module-path "Path to JavaFX library" --add-modules=javafx.controls,javafx.fxml
	
	4) Run AppDemo.java located in src/app/AppDemo.java
	
	NOTE: All backup data is stored in backupData/AppState.dat file. If you attempt to launch the program before adding the External jar dependencies, there 
		  will be an error pop up. If you still continue and attempt to launch it before adding the external jars to class path, the backupData/AppStte.dat file 
		  could possibly get corrupted in the process. In that case, add the external jars, delete the backupData/AppState.dat file, copy the rawData/DemoAppState.dat 
		  file into the backupData folder, and rename it to AppState.dat. 
		  
		  The DemoAppState.dat file is just a "Demo" backup file that contains some pre added users and posts to show off the functionality of program. All demo 
		  users have a password of "Password21". If you wish to start with no pre added users or posts, delete the backupData/AppState.dat file. In this case, a 
		  brand new AppState.dat file will be created on launch of the program,

Data Structures Used:
	1) UserCenter - Data Structure Used - TreeMap (Red-Black Binary Tree): 
	   Search, Insertion and Deletion Time Complexity: O(log(n))
	   
	   The UserCenter contains all the user accounts that have been created. It is backed by a TreeMap Data Structure. The 2 main reasons for using a TreeMap for the 
	   UserCenter were fast search time and the ability to grow without knowing the number of User objects that will be added. Also, since we do not need to preserve 
	   the insertion order of Users, the TreeMap satisfies the needs of the UserCenter. When using a TreeMap, a "key field" needs to be implemented in the object that 
	   is being added to the TreeMap. In this case, users are being added to the TreeMap and the key field is their username. The key field must be unique so to ensure 
	   this, when a new user created account the UserCenter TreeMap is checked to see if there is already an object(user) with that username. If there is, the new user
	   is forced to chose a different username. In regards to the search time, I chose this data structure since the search time is O(log(n)) which allows for fast 
	   lookup time when a user attempts to sign in. In regards to how the TreeMap functions (how it grows when new objects are added), I chose it since we do not know 
	   how many objects(Users) will be added to the UserCenter. In that case, a TreeMap or TreeSet are the most ideal data structures to chose. 
	   
	   Justifications for not using other data structures: If we knew the number of Users that would be added to the UserCenter,a HashMap could be used and we could 
	   benefit from its O(1) search and deletion time compared to the TreeMap's O(log(n) search and deletion time.
	   
	2) PostCenter - Data Structure Used - LinkedList: 
	   Insertion Time Complexity: O(1)
	   Search and Deletion Time Complexity: O(n)
	   
	   The PostCenter contains all the posts that have been created by the users. It is backed by a LinkedList of Post
	   objects. The 3 main reasons for using a LinkedList were to preserve insertion order, the ability to grow without knowing the number of Post objects that will
	   be added to it, and an insertion time of O(1). In regards to the insertion order, contrasting with the UserCenter, we need to preserve the insertion order since 
	   we want the ability to display the posts in chronological order. In regards to how the the LinkedList functions(how it grows when new objects are added), I chose 
	   it since we do not know how many Posts will be created by the users, so we need a data structure that can grow without knowing the max number of objects(Posts). 
	   Also, the insertion time of a LinkedList is O(1), meaning when a user creates a new post, it will be added to the PostCenter almost instantaneously. However, the 
	   main down side of using a LinkedList is the search time of O(n), since with a LinkedList you only know the next element so you must traverse each object one by
	   one to search for a specific object.
	   
	   Justifications for not using different data structures: If we did not care about the insertion order of the posts, we could have also used a TreeMap. If we cared 
	   about the insertion order, but knew the max number of Posts that would be created, we could have used a LinkedHashMap which benefits from a O(1) search time like 
	   a HashMap, and also preserves insertion order like a Linked List. But since a HashMap and LinkedHashMap are array based, it is not ideal when you
	   do not know the max number of objects being added to it, which is the case.
	   
	3) User:
		Main Data Structures:
			userPosts (Posts Created by User) - Data Structure Used - LinkedList<Post>()
			userPostReplies (Reply Posts Created by User) - Data Structure Used - LinkedList<ReplyPost>()
			following (Users that this user is following) - Data Structure Used - TreeMap<String,User>() <-String = key = username
			followers (Users that follow this user) - Data Structure Used - LinkedList<User>()
			blockedUsers(Users that this user has blocked) - Data Structure Used - TreeMap<String,User>() <-String = key = username
		
		userPosts: 
			The purpose of the userPosts LinkedList is to store only the Posts that were created by that user. In regards to the reasoning of choosing a 
			LinkedListin this case, its the same reasons as the PostCenter like preserving insertion order, the ability to grow without knowing how many posts this 
			user will create, and the fast insertion time of O(1). When a user creates a post, the post is added to the PostCenter and a shallow copy of the post is 
			also added to that users userPosts. The main reason for having a shallow copy LinkedList of posts in the user is to offset the slow search time of a 
			LinkedList(O(n). When we want to display only a specific users posts, instead of having to traverse the whole PostCenter LinkedList, we can just iterate 
			through this LinkedList and display only those posts. And since the posts are shallow copies, if we make changes to one of the users posts, such as another
			user liking the post, the copy in the  PostCenter will automatically be updated without having to traverse the PostCenteer to find that post, and update 
			it there.
		
		userPostReplies: 
			Same reasoning as userPosts for choosing a LinkedList. Same functionality as the userPosts in regards to having a LinkedList of shallow copies to offset 
			the slow search time of the PostCenter(LinkedList), but in this case it contains all the ReplyPosts that the user created(Posts that they created that 
			were replying to other posts).
		
		following: 
			The reasoning for choosing a TreeMap to hold the users that this user is following is very similar to the reasoning for the UserCenter. A data structure 
			with fast lookup time and the ability to grow without knowing the number of objects(users) that would be added to it. The main functionality of storing
			the users that this user is following is when it comes to displaying posts. A feature of this application is to filter all the posts(PostCenter) to just 
			display the posts of the people that this user is following. To accomplish this, when the PostCenter posts are iterated through to be displayed, it checks 
			if each posts poster(user who posted that post) is in the current users following. If they are, it will display post, if they aren't it will not display 
			post. A TreeMap of following users is super beneficial in this case since its search time is O(log(n)), meaning that the time to check if each posts poster 
			is in the current users following will be very quick.
		
		followers: 
			The reasoning for choosing a LinkedList to hold the users that follow this user is mainly just the fact that a LinkedList can grow without knowing the 
			number of objects(followers) that will be added to it. In the case of the followers of this user, there is never a need to search for a specific follower 
			since there is no feature to filter the PostCenter posts by followers, only following. The only functionality of the followers LinkedList is when a user 
			views someone's UserProfilePage. In the page, the followers of the user will be displayed in a ListView. So because of the fact that the followers will 
			never be searched through,just displayed, a TreeMap is not needed and a LinkedList is satisfactory.
		
		blockedUsers: 
			The reasoning for choosing a TreeMap to hold the users that this user has blocked is very similar to the reasoning for the UserCenter and for the
			following, being a fast lookup time and the ability to grow without knowing the number of blockedUsers being added. In the case of the following TreeMap, it is 
			only searched through when a user wants to filter the posts by just their following. However, in this case, there are 2 main functions that this TreeMap of 
			blocked users serve. The main use case is when displaying the posts in any instance(AllPosts, Posts by a certain user, Posts by a certain topic) etc. Anytime the 
			PostCenter is iterated through to display the posts, no matter what filter is applied, each posts poster(user who created that post) is checked to see if they 
			are in the current users blocked users, and if they are it will skip over displaying that post. It is super beneficial in this use case to have a search time
			of O(log(n) since the blockedUsers will be searched through for every iteration in the PostCenter. The second use case of the TreeMaps functionality is when a 
			user wants to add a new blocked user to their blockedUsers TreeMap. When a user attempts to add a new blocked user, the TreeMap will first be searched to check
			if that user is already in the TreeMap. Since the key field used in the TreeMap is the username of each user, and the username is unique, this will prevent the
			user from adding multiple instances of the same user into the TreeMap.
	
	4) Posts:
		Main Data Structures:
		postReplies - Data Structure Used - LinkedList<ReplyPost>()
		
		postReplies: 
			The reasoning for using a LinkedList to hold all the post replies to this specific post is very similar to the reasoning for the PostCenter, userPosts, and 
			userPostReplies, that being preserving insertion order, and the ability to grow without knowing how many post replies will be added to this post. The 
			purpose of storing the postReplies of a post is pretty self explanatory, when a user clicks on a specific post, the post clicked on and the post clicked 
			ons' postReplies will be displayed in chronological order(oldest to newest). If a user wants to add a reply to some post, the ReplyPost will be added to 
			that posts' postReplies.

Features/Guideline:
	1) Login
	2) Create New Accounts
	3) User Information: Username, Password, Email, Profile Pic, Banner Pic, Bio, All the Posts Created by Users, All the Users User is Following, All the Users 
	   Following User, All the Users User has Blocked
	4) Posts Information: User who Created Post, User who Created Posts' Profile Pic, TimeStamp, Title, Description, Topic, PostImages(Optional)
	5) View Posts in Chronological Order
	6) Filter Posts Functionality: Filter Posts by Following, Specific User, Topic, Title, etc
	7) Respond To an Existing Post.
	8) Ability to See All Responses to a Post, with Newest One Being at Bottom
	9) Ability to Follow Users
   10) Ability to Block Specific Users
   11) Data Persistence
   12) Owner Editing and Deleting Posts
   13) Record Likes of Each Post
   14) Ability to Delete Account
   15) Ability to Change Information of Account: Email, Password, Profile Pic, etc.
  
	
		