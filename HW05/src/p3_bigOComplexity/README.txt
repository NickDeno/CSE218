BigO p1:

1. Getting lyrics from txt file:
	Big O Complexity - O(n)
	Using the Utilities method getLyrics(), it returns a a String array[] containing an array of all the words in in the txt file.
	This is achieved by using the .split method of String class. The .split method has a time complexity of n, as it has to traverse the 
	string char by char and check if the current char is a space character.
	
2. Creating the "Lyrics" LinkedList:
	Big O Complexity - O(n^2)
		Using the Utilities method lyricsToList(), this method first calls the getLyrics method to get a String array of all the words in the 
	txt file which has a time complexity of n. Then, a new LinkedList<ParentLink> lyricsList is created, and a LinkedList<String> usedWordsList 
	are created. The purpose of the usedWordsList is to help ensure only unique words are added to the lyricsList. To convert the txt words into the
	list like how the instructions specified, a for loop is first run. This loop has a time complexity of n since it runs for the length of the array
	that hold the words of text file. In the loop, it loops through the array of words and checks if the current word has already been used. This
	is accomplished by calling usedWordsList.contains which checks if the current word is in that list. If it is not, then it continues on. The 
	.contains method also has a time complexity of n.
		Next, if the word is unique, a new LinkedList<String> babyList is created and  a second for loop is called to iterate through the rest of this 
	lyricsList. In this loop, all words in the string array that follow the current unique word are added into the babyList. Then, a new ParentLink
	is added into the lyricsList with the current unique word, and the babyList of that word containing all the words that follow that unique word.
	In total, this method uses a nested for loop, which has a time complexity of n^2.
	
	