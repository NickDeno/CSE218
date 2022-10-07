package p2;

import java.util.Arrays;

import p1.Book;

public class BookBag {
	private Book[] arr;
	private int nElems;
	
	public BookBag(int maxSize) {
		arr = new Book[maxSize];
		nElems = 0;
	}
	
	public void insert(Book book) {
		arr[nElems++] = book;
	}
	
	public void display() {
		for(int i = 0; i < nElems; i++) {
			System.out.println(arr[i]);
		}
		System.out.println();
	}
	
	public Book sequentialSearchByISBN(String isbn) {
		for(int i = 0; i <nElems; i++) {
			if(arr[i].getIsbn().equals(isbn)) {
				return arr[i];
			}
		}
		return null;
	}
	
	public Book[] sequentialSearchByLastName(String lastName) {
		Book[] booksFound = new Book[nElems];
		int count = 0;
		
		for(int i = 0; i < nElems; i++) {
			if(arr[i].getAuthor().getLastName().equalsIgnoreCase(lastName)) {
				booksFound[count++] = arr[i];
			}
		}
		return Arrays.copyOf(booksFound, count);
	}
	
	public Book binarySearchByISBN(String isbn) {
		int left = 0;
		int right = nElems;
		
		while(left <= right) {
			int mid = (left + right)/2;

			if(arr[mid].getIsbn().compareTo(isbn) > 0) {
				right = mid - 1;
			} 
			else if(arr[mid].getIsbn().compareTo(isbn) < 0) {
				left = mid + 1;
			} 
			else {
				return arr[mid];
			}
		}
		return null;	
	}
	
	public void bubbleSortByISBN() {
		for(int i = 0; i < nElems; i++) {
			for(int j = i + 1; j < nElems; j++) {
				if(arr[j].getIsbn().compareTo(arr[i].getIsbn()) < 0) {
					Book temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
	}
	
//	public void insertionSortByLastName() {
//		
//	}
	
	public void selectionSortByPrice() {
		for(int i = 0; i < nElems; i++) {
			int min = i;
			for(int j = i + 1; j < nElems; j++) {
				if(arr[j].getPrice() < arr[min].getPrice()) {
					min = j;
				}
			}
			Book temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
	}
	
	public Book deleteByISBN(String isbn) {
		int i = 0;
		for(; i < nElems; i++) {
			if(arr[i].getIsbn().equals(isbn)) {
				break;
			}
		}
		
		if(i == nElems) {
			return null;
		} else {
			Book temp = arr[i];
			for (int j = i; j < nElems - 1; j++) {
				arr[j] = arr[j + 1];
			}
			nElems--;
			return temp;
		}
	}
	
	public Book[] deleteByLastName(String lastName) {
		Book[] booksRemoved = new Book[nElems];
		int count = 0;
		for(int i = 0; i < nElems; i++) {
			if(arr[i].getAuthor().getLastName().equalsIgnoreCase(lastName)) {
				booksRemoved[count++] = arr[i];
				for(int j = i; j< nElems-1; j++) {
					arr[j] = arr[j+1];
				}
				i--;
				nElems--;
			}
		}
		return Arrays.copyOf(booksRemoved, count);
	}
}
