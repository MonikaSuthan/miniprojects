import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean available;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class User {
    private String name;
    private int userID;
    private List<Book> borrowedBooks;

    public User(String name, int userID) {
        this.name = name;
        this.userID = Integer.parseInt(String.valueOf(userID));
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getUserID() {
        return userID;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.setAvailable(false);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.setAvailable(true);
    }
}

class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void registerUser(User user) {
        users.add(user);
    }

    public User getUser(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        // Example usage of the Library Management System
        Library library = new Library();

        // Adding books to the library
        Book book1 = new Book("Java Programming", "John Doe", "1234567890");
        Book book2 = new Book("Data Structures and Algorithms", "Jane Smith", "0987654321");
        library.addBook(book1);
        library.addBook(book2);

        // Registering users
        User user1 = new User("Alice", 1001);
        User user2 = new User("Bob", 1002);
        library.registerUser(user1);
        library.registerUser(user2);

        // User borrowing a book
        Book foundBook = library.searchBook("Java Programming");
        if (foundBook != null && foundBook.isAvailable()) {
            user1.borrowBook(foundBook);
            System.out.println("Book borrowed successfully by " + user1.getName());
        } else {
            System.out.println("Book not available or not found");
        }

        // User returning a book
        Book borrowedBook = user1.getBorrowedBooks().get(0);
        user1.returnBook(borrowedBook);
        System.out.println("Book returned successfully by " + user1.getName());
    }
}

