/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;

import entity.Book;

/**
 *
 * @author NguyenQuocTuan
 */
public class MyList implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Node<Book> head, tail;
	Node<Book> sorted;
    
    //constructor
    public MyList() {
        head = tail = null;
    }
    //check if the list is empty or not
    public boolean isEmpty() {
        return head == null;
    }
    //add a new Book to the end of list
    public void addLast(Book b) {
        Node<Book> newNode = new Node<Book>(b);
        if (head == null) {
        	head = new Node<Book>(b);
        	return;
        }
        newNode.next = null;
        Node<Book> last = head;
        while (last.next != null) last = last.next;
        last.next = newNode;
        return;
    }
    //add a new Book to the beginning of list
    public void addFirst(Book b) {
        Node<Book> newNode = new Node<Book>(b);
        newNode.next = head;
        head = newNode;
    }
    //output information of all books in the list
    public void traverse() {
    	System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s%-10s", "Code","Title",
                "Quantity","Lended","Price","Value", "Position"));
    	int oNum = 1;
    	 Node<Book> tnode = head; 
         while (tnode != null) 
         { 
        	 tnode.info.setONum(oNum);
             System.out.println(tnode.info); 
             tnode = tnode.next;
             oNum++;
         } 
    }
    //return number of nodes/elements in the list
    public int size() {
    	Node<Book> current = head;
        int count = 0;
        while (current != null) {
        	count ++;
        	current = current.next;
        }
        return count;
    }
    //return a Node at position k, starting position is 0
    public Node<Book> getNode(int k) {
        Node<Book> current = head;
        int count = 0;
        while (current != null) {
        	if (count == k) return current;
        	count ++;
        	current = current.next;
        }
        assert(false); 
        return current;
    }
    //add a new book after a position k
    public void addAfter(Book b, int k) {
    	Node<Book> prevNode = getNode(k);
       if (prevNode == null) {
    	   System.out.println("There is no book at position selected");
    	   return;
       }
       Node<Book> newNode = new Node<Book>(b);
       newNode.next = prevNode.next;
       prevNode.next = newNode;
    }
    //delete a book at position k
    public void deleteAt(int k) {
    	Node<Book> temp = head, prev = null, key = getNode(k);   
    	 if (temp != null && temp == key) { 
             head = temp.next; // Changed head 
             return; 
         }
    	 while (temp != null && temp != key) { 
             prev = temp; 
             temp = temp.next; 
         } 
    	 if (temp == null) return; 
    	 prev.next = temp.next; 
    }
    //search a Node by a given book code
    public Node<Book> search(String bCode) {
    	Node<Book> current = head;
    	while (current != null) { 
            if (current.info.getbCode().equals(bCode)) 
                return current;    //data found 
            current = current.next; 
        } 
        return null;
    }
    
    //Sort by code
    public void sortCode() {
    	insertionSort(head, "code");
    }
    
    //Sort by price
    public void sortPrice() {
    	insertionSort(head, "price");
    }
    
    public void insertionSort(Node<Book> headref, String sortType) { 
        // Initialize sorted linked list 
        sorted = null; 
        Node<Book> current = headref; 
        // Traverse the given linked list and insert every 
        // node to sorted 
        while (current != null)  { 
            // Store next for next iteration 
        	Node<Book> next = current.next; 
            // insert current in sorted linked list 
            sortedInsert(current, sortType); 
            // Update current 
            current = next; 
        } 
        // Update head_ref to point to sorted linked list 
        head = sorted; 
    } 
  
    void sortedInsert(Node<Book> newnode, String sortType) {
    	
    	if (sortType.equalsIgnoreCase("price")) {
    		if (sorted == null || sorted.info.getPrice() >= newnode.info.getPrice()) {
    			newnode.next = sorted; 
                sorted = newnode;
    		} else {
    			Node<Book> current = sorted;
    			while (current.next != null && current.next.info.getPrice() < newnode.info.getPrice()) {
    				current = current.next; 
    			}
    			newnode.next = current.next; 
                current.next = newnode;}
    	}
    	
    	if (sortType.equalsIgnoreCase("code")) {
    		if (sorted == null || sorted.info.getbCode().compareToIgnoreCase(newnode.info.getbCode()) >= 0) {
    			newnode.next = sorted; 
                sorted = newnode;
    		} else {
    			Node<Book> current = sorted;
    			while (current.next != null && current.next.info.getbCode().compareToIgnoreCase(newnode.info.getbCode()) < 0) {
    				current = current.next; 
    			}
    			newnode.next = current.next; 
                current.next = newnode;}
    		}
    	}
    }
  
