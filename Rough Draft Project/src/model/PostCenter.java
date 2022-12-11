package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

public class PostCenter implements Serializable{
	private static final long serialVersionUID = 1L;
	private LinkedList<Post> postList;	

	public PostCenter() {
		postList = new LinkedList<Post>();
	}
	
	public void display() {
		postList.forEach(post -> System.out.println(post.toString()));
	}
	
	public void addPost(Post post) {
		postList.add(post);
	}
	
	public boolean removePost(Post post) {
		return postList.remove(post);
	}
	
	public boolean removePost(UUID uuid) {
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getUuid().equals(uuid)) {
				postList.remove(post);
				return true;
			}
		}
		return false;

	}

	public Post getPost(UUID uuid) {
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getUuid().equals(uuid)) {
				return post;
			}
		}
		return null;
	}
	
	public LinkedList<Post> getPosts(){
		return postList;
	}
	
	public LinkedList<Post> searchByTopic(String topic) {
		LinkedList<Post> temp = new LinkedList<Post>();
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getTopic().equals(topic)) {
				temp.add(post);
			}
		}
		return temp;
	}
	
	public LinkedList<Post> searchByTitle(String title) {
		LinkedList<Post> temp = new LinkedList<Post>();
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			Post post = itr.next();
			if(post.getTitle().equals(title)) {
				temp.add(post);
			}
		}
		return temp;
	}
	
	//Takes postList and sorts them based on likes using quickSort algorithm.
	public Post[] searchByLikes() {
		Post[] postsArr = new Post[postList.size()];
		postList.toArray(postsArr);
		quickSort(postsArr, 0, postsArr.length-1);
		return postsArr;
	}
	
	private void quickSort(Post[] arr, int left, int right) {
		if (left >= right) {
			return;
		} else {
			int pivot = arr[right].getLikes().intValue();
			int partition = partition(arr, left, right, pivot);
			quickSort(arr, left, partition - 1);
			quickSort(arr, partition + 1, right);
		}
	}

	private int partition(Post[] arr, int left, int right, long pivot) {
		int leftPtr = left - 1;
		int rightPtr = right + 1;

		while (true) {
			while (leftPtr < right && arr[++leftPtr].getLikes().intValue() < pivot);
			while (rightPtr > left && arr[--rightPtr].getLikes().intValue() >= pivot);
			
			if (leftPtr >= rightPtr) {
				break;
			} else {
				Post temp = arr[rightPtr];
				arr[rightPtr] = arr[leftPtr];
				arr[leftPtr] = temp;
			}

		}
		Post temp = arr[right];
		arr[right] = arr[leftPtr];
		arr[leftPtr] = temp;
		return leftPtr;
	}
	
	@Override
	public String toString() {
		String  s = "";	
		Iterator<Post> itr = postList.iterator();
		while(itr.hasNext()) {
			s += itr.next().toString();
		}
		return s;
	}
	
}
