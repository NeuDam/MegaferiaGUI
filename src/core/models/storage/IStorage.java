/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.*;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public interface IStorage {

  boolean addStand(Stand stand);

  Stand getStand(long id);

  ArrayList<Stand> getAllStands();

  boolean addAuthor(Author author);

  Author getAuthor(long id);

  ArrayList<Author> getAllAuthors();

  boolean addManager(Manager manager);

  Manager getManager(long id);

  ArrayList<Manager> getAllManagers();

  boolean addNarrator(Narrator narrator);

  Narrator getNarrator(long id);

  ArrayList<Narrator> getAllNarrators();

  ArrayList<Person> getAllPersons();

  boolean addPublisher(Publisher publisher);

  Publisher getPublisher(String nit);

  ArrayList<Publisher> getAllPublishers();

  boolean addBook(Book book);

  Book getBook(String isbn);

  ArrayList<Book> getAllBooks();

  void addObserver(StorageObserver observer);

  void removeObserver(StorageObserver observer);

}
