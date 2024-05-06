package itech2306.assignment;

import java.util.Scanner;

public class Menu {
	 private Scanner scanner;

	 public Menu() {
	        scanner = new Scanner(System.in);
	 }

	 public void displayMainMenu() {
	        System.out.println("\u001B[32m");
	        System.out.println("Main Menu");
	        System.out.println("************************************************");
	        System.out.println("1. Enter Company Details");
	        System.out.println("2. See List of All Companies");
	        System.out.println("3. Add Investor for a Company");
	        System.out.println("4. See List of All Investors for a Company");
	        System.out.println("5. Declare Dividend for a Company");
	        System.out.println("6. Set Topics for a Company Meeting ");
	        System.out.println("7. Exit");
	        System.out.println("************************************************");
	        System.out.print("Enter your choice: ");
	 }

	 public int getUserChoice() {
	        return scanner.nextInt();
	 }

	 public void close() {
	        System.out.println("\u001B[37m");
	        scanner.close();
	 }
}
