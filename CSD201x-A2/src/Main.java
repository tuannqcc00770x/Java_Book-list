import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NguyenQuocTuan
 */
public class Main {
  
    public static void main(String[] args) {
    	createMainMenu();
    }
    
    public static void createMainMenu() {
    	Scanner sc = new Scanner(System.in);
    	BookList bookList = new BookList();
    	boolean keepRunning = true;
    	while (keepRunning) {
    		System.out.println("Book List");
    		System.out.println("1. Input book and add to the end");
    		System.out.println("2. Display books");
    		System.out.println("3. Search by code");
    		System.out.println("4. Input book and add to beginning");
    		System.out.println("5. Add Book after position k");
    		System.out.println("6. Delete book at position k");
    		System.out.println("7. Sort by price");
    		System.out.println("8. Sort by code");
    		System.out.println("9. Load booklist from system");
    		System.out.println("0. Exit");
    		System.out.println("Enter your choice: ");
    		int choice = inputInt(sc, 0, 9);
    		switch (choice) {
			case 1:
				bookList.addLast();
				break;
			case 2:
				bookList.list();
				break;
			case 3:
				bookList.search(sc);
				break;
			case 4:
				bookList.addFirst();
				break;
			case 5:
				bookList.addAfter();
				break;
			case 6:
				bookList.deleteAt();
				break;
			case 7:
				bookList.sortPrice();;
				break;
			case 8:
				bookList.sortCode();;
				break;
			case 9:
				bookList.loadList();
				break;
			case 0:
				keepRunning = false;
				break;
			default:
				break;
			}
    	}
    }
  //Validate input Integer
    public static int inputInt(Scanner scan, int a, int b) {//validate: input integer (menu option)
		try {
			int min, max;
			if (a<b) {
				min = a;
				max = b;
			} else {
				max = a;
				min = b;
			}
			int result = Integer.parseInt(scan.nextLine());
			if (result < min || result > max) {
				System.out.print("You have to input integer from " + a + " to " + b +". Retry: ");
				return inputInt(scan, a, b);
			} else return result;
		} catch (Exception e) {
			System.out.print("You have to input integer from " + a + " to " + b +". Retry: ");
			return inputInt(scan, a, b);
		}
	} 
}
