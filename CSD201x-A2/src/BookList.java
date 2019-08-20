/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.io.File;
import java.util.Scanner;

import entity.Book;
import util.FileIO;
import util.MyList;

/**
 *
 * @author NguyenQuocTuan
 */
public class BookList {

    //a list of book
    private MyList books;

    public MyList getBooks() {
        return books;
    }

    
    
    public BookList() {
        books = new MyList();
    }

    //1.0 accept information of a Book
    private Book getBook() {
    	String bCode, title;
        int quantity, lended;
        double price;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter infomation of a book");
        bCode = inputBookCode(sc);
        System.out.println("Title: ");
        title = sc.nextLine();
        quantity = inputQuantity(sc);
        lended = inputLended(sc);
        price = inputPrice(sc);
        return new Book(bCode, title, quantity, lended, price, 1);
    }
    
    //1.1 accept and add a new Book to the end of book list
    public void addLast() {
    	books.addLast(getBook());
    	saveList();
    	list();
    }

    //1.2 output information of book list
    public void list() {
       books.traverse();
    }

    //1.3 search book by book code
    public void search(Scanner sc) {
      System.out.println("Enter book code:");
      String bCode = sc.nextLine();
      if (books.search(bCode)==null) System.out.println("No book found");
      else {
    	  System.out.println(String.format("%-10s%-20s%-10s%-10s%-10s%-10s%-10s", "Code","Title",
                  "Quantity","Lended","Price","Value", "Position"));
    	  System.out.println(books.search(bCode).info);
      }
    }

    //1.4 accept and add a new Book to the beginning of book list
    public void addFirst() {
      books.addFirst(getBook()); 
      saveList();
      list();
    }

    //1.5 Add a new Book after a position k
    public void addAfter() {
      Scanner sc = new Scanner(System.in);
      books.addAfter(getBook(), inputPos(sc, "add")-1);
      saveList();
      list();
    }

    //1.6 Delete a Book at position k
    public void deleteAt() {
      Scanner sc = new Scanner(System.in);
      books.deleteAt(inputPos(sc, "delete")-1);
      saveList();
      list();
    }
    
    //Sort by price
    public void sortPrice() {
    	books.sortPrice();
    	saveList();
    	list();
    }
    
  //Sort by code
    public void sortCode() {
    	books.sortCode();
    	saveList();
    	list();
    }
    
    //validate book code
    String inputBookCode(Scanner sc) {
	  System.out.println("Book Code:");
	  String bCode = sc.nextLine();
	  if (!bCode.matches("[A-Z][0-9]{2}")) {
		  System.out.println("Book code must be at least 1 upper case character and 2 number. Ex: A01, B02,...");
		  return inputBookCode(sc);
	  }
	  if (books != null) {
		  for (int i = 0; i < books.size(); i++) {
			    if (books.getNode(i).info.getbCode().equals(bCode)) {
			    	System.out.println("Book code must be unique!");
			    	return inputBookCode(sc);
			    }
			}
	  }
	  return bCode;
   }
    
    //validate quantity
    int inputQuantity(Scanner sc) {
    	try {
    		System.out.println("Book Quantity:");
    		int quantity = Integer.parseInt(sc.nextLine());
    		if (quantity < 0) {
    			System.out.println("Quantity must be Integer and greater than or equal to zero!");
    			return inputQuantity(sc);
    		}
        	return quantity;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Quantity must be Integer and greater than or equal to zero!");
			return inputQuantity(sc);
		}
    	
    }
    
    //validate lended
    int inputLended(Scanner sc) {
    	try {
    		System.out.println("Book Lended:");
    		int lended = Integer.parseInt(sc.nextLine());
    		if (lended < 0) {
    			System.out.println("Lended must be Integer and greater than or equal to zero!");
    			return inputLended(sc);
    		}
        	return lended;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Lended must be Integer and greater than or equal to zero!");
			return inputLended(sc);
		}
    	
    } 
    
    //validate price
    double inputPrice(Scanner sc) {
    	try {
    		System.out.println("Book Price:");
    		double price = Double.parseDouble(sc.nextLine());
    		if (price < 0) {
    			System.out.println("Price must be number and greater than or equal to zero!");
    			return inputPrice(sc);
    		}
        	return price;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Price must be number and greater than or equal to zero!");
			return inputPrice(sc);
		}
    	
    }
    
    //validate position
    int inputPos(Scanner sc, String action) {
    	try {
    		System.out.println("Enter Position to " + action + ":");
    		int pos = Integer.parseInt(sc.nextLine());
    		if (pos < 0) {
    			System.out.println("Position must be Integer and greater than or equal to zero!");
    			return inputPos(sc, action);
    		}
        	return pos;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Position must be Integer and greater than or equal to zero!");
			return inputPos(sc, action);
		}
    }
    
    //save book list
    public void saveList() {
    	FileIO fileIO = new FileIO();
    	fileIO.saveData(books, "BookList.txt");
    }
    
    //load book list
    public void loadList() {
    	FileIO fileIO = new FileIO();
    	File file = new File("BookList.txt");
    	if (!file.exists()) {
    		fileIO.saveData(books, "BookList.txt");
    		System.out.println("Data not exist! Data has been create!");
    	}
    	books = fileIO.loadData("BookList.txt");
    	list();	
    }
    
}
