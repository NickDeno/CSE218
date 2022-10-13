Big O p1:
	Step 1: Getting lyrics from txt file into array using ParentList getLyrics() method
		The Big O complexity of this method is O(n). This method utilizes .split string method to split txt file into n amount of words and insert
		into an array. The time complexity of .split method is n => O(n)
	
	Step 2: Filling ParentList(LinkedList<ParentLink>) with lyrics array using fillWithLyrics() method
		The Big O complexity of this method is O(n^2). This method searches through n elements of lyrics list (time complexity of n). In this loop, each
		word (element) is searched through ParentList to check if that word already exists in a link (checks for uniqueness) which also has a time complexity
		of n. Then if word is unique, a sub loop is run to search the remaining ParentList for any other instances of that word. If there is one, the word
		after that instance is added into a baby list (time complexity of n). Then the current word and baby list of that word are inserted into ParentList.
		Overall, time complexity is n + n(n) = n^2 + n => O(n^2)	
	
Big O p2 (Step 1 and Step 2 is same as p1):
	Step 3: Generating random paragraph using generateParagraph() method
		The Big O complexity of this method is O(n^2). First, an outer loop is run for n number chosen by user (time complexity of n). Then for each 
		iteration, an inner loop is run to search through the ParentList. Inside the inner loop, for each iteration it searches the list and finds the
		baby list of the current key word (the word proceeding it). Then, it gets a random word inside that baby list and updates the keyword to that randomly
		found word (time complexity of n). In total, time complexity is n(n) = n^2 => O(n^2)
		
	
	