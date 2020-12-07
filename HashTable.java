/**
* This class creates a hash table and assigns keys 
* to the nodes that are inserted into the linked list
* array that acts as the hash table. There are accessor
* methods for deletion, insertion, searching, and printing
* the hash table. It includes two nested classes. The first
* is the node class. The second is the link list class.
*
* @author Camille Copeland
* @version 4/24/2020
*/
public class HashTable {
	/**
	* This creates the Node. It includes a constructor
	* and stores the data for the countries. 
	*
	* @author Camille Copeland
	* @version 4/24/2020
	*/
	private class Node {
		 String name;
		double gdpPerCapita;
		 Node nextNode;
		 public Node(String name, double gdpPerCapita) {
		 this.name = name;
		this.gdpPerCapita = gdpPerCapita;
		 }
		 public void printNode() {
		 System.out.printf("	%-25s%,-20.2f\n", name, gdpPerCapita);
		 }
		}
	/**
	* This linklist class includes accessor methods
	* that traverse, delete, insert, and display nodes
	* within the linkedlists. 
	*
	* @author Camille Copeland
	* @version 4/24/2020
	*/
	public class LinkList {
		private Node first;
		private Node last;
		public LinkList() {
			first=null;
			last=null;
		}
		
		public boolean isEmpty() {
			return(first==null);
		}
		
		public void insertFirst(Node a) {
			
			if(isEmpty()) {
				last = a;
			}
			
			a.nextNode = first; 
			first = a;
		}
		
		public void insertLast(Node a) {
		
			
			if(isEmpty()) {
				first = a;
			}
			else
				last.nextNode = a;
			
			last = a;
		}
		
		public Node deleteFirst() {
			if(first.nextNode == null)
				last = null;
			first = first.nextNode;
			return first;
		}
		
		public void displayList() {
			Node current = first;
			while(current != null) {
				current.printNode();
				current = current.nextNode;
			}
			System.out.println("");
		}
		
	}
	
	LinkList[] arr = new LinkList[311];

	
	
	public void HashTable() {
	}
	
	/**
	* This method inserts a node into a linkedlist at its given key.
	* It calls the hashfunction to determine the key and assign the 
	* node to the correct linkedlist. 
	*
	* @param country is the name of the country that will be stored in the node 
	* @param gdpPerCapita is the gdp that will be stored in the node
	* @return none
	*/
	public void insert(String country, double gdpPerCapita) {
	//	LinkList newList = new LinkList();
		Node newNode = new Node(country, gdpPerCapita);
		int key = hashFunction(country);
		if(arr[key]== null) {
			LinkList newList = new LinkList();
			arr[key]=newList;
			arr[key].insertFirst(newNode);
		}
		else {
			arr[key].insertLast(newNode);
		}
	}
	
	/**
	*Yo this finds the country given.
	*
	* @param country is used to find the key that the country belongs to
	* @return it returns the gdp per capita when the country is found
	*/
	public double find(String country) {
		int key = hashFunction(country);
		Node current = arr[key].first;
		if(arr[key].isEmpty()) {
			return -1;
		}
		else if(arr[key] != null) {
			while(current != arr[key].last){
				current = current.nextNode;
			}
			
			if(current.name.equals(country)) {
				System.out.println(country + " is found with a GDP per Capita of " +current.gdpPerCapita);
				return current.gdpPerCapita;
			}
			else if(!current.name.equals(country)) {
				System.out.println(country+" is not found");
			}
		}
		
		return -1;
	}
	
	public void delete(String country) {
		int key = hashFunction(country);
		
		if(arr[key].isEmpty()) {
			System.out.println("Country not found.");
		}//end if
		else if(arr[key] != null) {
			String del = arr[key].first.name;
			if(del.equals(country)) {
				arr[key].deleteFirst();
				if(arr[key].last == null)
					arr[key]=null;
			}//end if
			else {
				Node trail = arr[key].first;
				Node current = trail.nextNode;
				while(!current.name.equals(country) && current != null) {
					trail = trail.nextNode;
				}//end while
			
				if(current.name.equals(country)) {
					if(current == arr[key].last) {
						trail.nextNode = null;
						arr[key].last = trail;
					}//end if
					else if(current.nextNode != null) {
						trail.nextNode = current.nextNode;
					}//end else if	
				}// end if
			}//end else
		}//end else if
		
		System.out.println(country+" has been deleted from hash table.");
	}
	
	public void display() {
		Node current; 
		for(int i = 0; i < 311; i++) {
			if(arr[i] ==null) {
				System.out.println(i+ ".	Empty");
			}
			else {
				current = arr[i].first;
				System.out.print(i + ".");
				while(current != null) {
					current.printNode();
					current = current.nextNode;
				}//end while
			}//end else
		}//end for
		
	}//end method
	
	public void printFreeAndCollisions() {
		int collCount = 0;
		int freeCount = 0;
		Node current;
		for(int i=0; i<311; i++) {
			if(arr[i] == null) {
				freeCount++;
			}
			if(arr[i] != null && arr[i].first != null) {
				current = arr[i].first; 
				while(current.nextNode != null) {
					collCount++;
					current=current.nextNode;
				}
			}
		}//end for
		
		System.out.println("There are "+freeCount+" spaces available and "+collCount+" collisions in the hash table.");
	}
	
	

	public static int hashFunction(String S) {
		int hash=0;
		char[] charArr = S.toCharArray();
		for(int i = 0; i < S.length(); i++) {
			hash += charArr[i];
		}
		hash = hash%311;
		return hash;
	}
}
