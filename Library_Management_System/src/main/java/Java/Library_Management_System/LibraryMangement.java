package Java.Library_Management_System;
import java.io.*;
import java.util.*;

public class LibraryMangement {
	private static final String FILE_NAME = "books.txt";
    private static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        loadBooks();

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n --Welcome to Libray Book Management System");
            System.out.println("1. Add Book Name : ");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book");
            System.out.println("4. View All Books");
            System.out.println("5.----- Exit-------");
            System.out.print("Enter your choice: ");

            // Input validation
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next(); 
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1: addBook(sc); break;
                case 2: removeBook(sc); break;
                case 3: searchBook(sc); break;
                case 4: viewBooks(); break;
                case 5: 
                    saveBooks();
                    System.out.println("Exiting...bye!");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    //  Add Book
    private static void addBook(Scanner sc) {
        System.out.print("Enter title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter author: ");
        String author = sc.nextLine().trim();
        System.out.print("Enter BookId: ");
        String isbn = sc.nextLine().trim();

        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            System.out.println("All fields are required.");
            return;
        }

        books.add(new Book(title, author, isbn));
        saveBooks();
        System.out.println("Book added successfully!");
    }

    // Remove Book
    private static void removeBook(Scanner sc) {
        System.out.print("Enter bookId of book to remove: ");
        String bookId = sc.nextLine().trim();

        boolean removed = books.removeIf(book -> book.getBookId().equals(bookId));

        if (removed) {
            saveBooks();
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found!");
        }
    }

    //Search Book
    private static void searchBook(Scanner sc) {
        System.out.print("Enter title or author keyword: ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword) ||
                book.getAuthor().toLowerCase().contains(keyword)) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching book found!");
        }
    }

    //View All Books
    private static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n Books in Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    //File Handling
    private static void saveBooks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Book book : books)
                writer.write(book.toString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    private static void loadBooks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] data = fileScanner.nextLine().split(" \\| ");
                if (data.length == 3) {
                    books.add(new Book(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
    }
}