package org.example;

import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.model.Author;
import org.example.model.Book;
import org.example.model.BookStore;
import org.example.model.HibernateContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Controller implements AutoCloseable {
    private HibernateContext model = new HibernateContext();

    public void addBook() {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();

        Scanner scanner = new Scanner(System.in);

        try {

            Author author = new Author();
            System.out.println("Author Name: ");
            author.setName(scanner.nextLine());

            Book book = new Book();

            System.out.println("Title: ");
            book.setTitle(scanner.nextLine());

            System.out.println("ISBN: ");
            book.setISBN(scanner.nextLine());

            System.out.println("Date (yyyy-MM-dd): ");
            book.setDateIssued(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));

            book.setAuthor(author);

            session.persist(author);
            session.persist(book);

            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            tx.rollback();
        }
    }

    public void updateBook(String currentBookTitle, String newTitle, String newISBN) {
        Session session = model.getSession();
        Transaction tx = session.beginTransaction();
        try {
            Query query = session.createQuery("from Book where title = :currentBookTitle", Book.class);
            query.setParameter("currentBookTitle", currentBookTitle);
            Book book = (Book) query.uniqueResult();
            if (book != null) {
                book.setTitle(newTitle);
                book.setISBN(newISBN);
                session.update(book);
                tx.commit();

            }
        } catch(Exception e) {
                System.out.println(e);
                tx.rollback();
        }

    }

    public void searchBook(Scanner sc) {
        System.out.println("Enter book title or ISBN: ");
        String searchQuery = sc.nextLine();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(searchQuery) || book.getISBN().equalsIgnoreCase(searchQuery)) {
                System.out.println(book.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No book found with the given title or ISBN.");
        }
    }

    @Override
    public void close() throws Exception {

    }
}

