/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author edangulo
 */
public class Storage implements IStorage {

  private static Storage instance;

  private ArrayList<Stand> stands;
  private ArrayList<Author> authors;
  private ArrayList<Manager> managers;
  private ArrayList<Narrator> narrators;
  private ArrayList<Publisher> publishers;
  private ArrayList<Book> books;
  private ArrayList<StorageObserver> observers;

  private Storage() {
    this.stands = new ArrayList<>();
    this.authors = new ArrayList<>();
    this.managers = new ArrayList<>();
    this.narrators = new ArrayList<>();
    this.publishers = new ArrayList<>();
    this.books = new ArrayList<>();
    this.observers = new ArrayList<>();
  }

  public static Storage getInstance() {
    if (instance == null) {
      instance = new Storage();
    }
    return instance;
  }

  public boolean addStand(Stand stand) {
    for (Stand s : this.stands) {
      if (s.getId() == stand.getId()) {
        return false;
      }
    }
    this.stands.add(stand);
    notifyStandChanged();
    return true;
  }

  public Stand getStand(long id) {
    for (Stand stand : this.stands) {
      if (stand.getId() == id) {
        return stand;
      }
    }
    return null;
  }

  public ArrayList<Stand> getAllStands() {
    ArrayList<Stand> sortedStands = new ArrayList<>(this.stands);
    Collections.sort(sortedStands, new Comparator<Stand>() {
      @Override
      public int compare(Stand s1, Stand s2) {
        return Long.compare(s1.getId(), s2.getId());
      }
    });
    return sortedStands;
  }

  public boolean addAuthor(Author author) {
    for (Author a : this.authors) {
      if (a.getId() == author.getId()) {
        return false;
      }
    }
    this.authors.add(author);
    notifyPersonChanged();
    return true;
  }

  public Author getAuthor(long id) {
    for (Author author : this.authors) {
      if (author.getId() == id) {
        return author;
      }
    }
    return null;
  }

  public ArrayList<Author> getAllAuthors() {
    ArrayList<Author> sortedAuthors = new ArrayList<>(this.authors);
    Collections.sort(sortedAuthors, new Comparator<Author>() {
      @Override
      public int compare(Author a1, Author a2) {
        return Long.compare(a1.getId(), a2.getId());
      }
    });
    return sortedAuthors;
  }

  public boolean addManager(Manager manager) {
    for (Manager m : this.managers) {
      if (m.getId() == manager.getId()) {
        return false;
      }
    }
    this.managers.add(manager);
    notifyPersonChanged();
    return true;
  }

  public Manager getManager(long id) {
    for (Manager manager : this.managers) {
      if (manager.getId() == id) {
        return manager;
      }
    }
    return null;
  }

  public ArrayList<Manager> getAllManagers() {
    ArrayList<Manager> sortedManagers = new ArrayList<>(this.managers);
    Collections.sort(sortedManagers, new Comparator<Manager>() {
      @Override
      public int compare(Manager m1, Manager m2) {
        return Long.compare(m1.getId(), m2.getId());
      }
    });
    return sortedManagers;
  }

  public boolean addNarrator(Narrator narrator) {
    for (Narrator n : this.narrators) {
      if (n.getId() == narrator.getId()) {
        return false;
      }
    }
    this.narrators.add(narrator);
    notifyPersonChanged();
    return true;
  }

  public Narrator getNarrator(long id) {
    for (Narrator narrator : this.narrators) {
      if (narrator.getId() == id) {
        return narrator;
      }
    }
    return null;
  }

  public ArrayList<Narrator> getAllNarrators() {
    ArrayList<Narrator> sortedNarrators = new ArrayList<>(this.narrators);
    Collections.sort(sortedNarrators, new Comparator<Narrator>() {
      @Override
      public int compare(Narrator n1, Narrator n2) {
        return Long.compare(n1.getId(), n2.getId());
      }
    });
    return sortedNarrators;
  }

  public boolean addPublisher(Publisher publisher) {
    for (Publisher p : this.publishers) {
      if (p.getNit().equals(publisher.getNit())) {
        return false;
      }
    }
    this.publishers.add(publisher);
    notifyPublisherChanged();
    return true;
  }

  public Publisher getPublisher(String nit) {
    for (Publisher publisher : this.publishers) {
      if (publisher.getNit().equals(nit)) {
        return publisher;
      }
    }
    return null;
  }

  public ArrayList<Publisher> getAllPublishers() {
    ArrayList<Publisher> sortedPublishers = new ArrayList<>(this.publishers);
    Collections.sort(sortedPublishers, new Comparator<Publisher>() {
      @Override
      public int compare(Publisher p1, Publisher p2) {
        return p1.getNit().compareTo(p2.getNit());
      }
    });
    return sortedPublishers;
  }

  public boolean addBook(Book book) {
    for (Book b : this.books) {
      if (b.getIsbn().equals(book.getIsbn())) {
        return false;
      }
    }
    this.books.add(book);
    notifyBookChanged();
    return true;
  }

  public Book getBook(String isbn) {
    for (Book book : this.books) {
      if (book.getIsbn().equals(isbn)) {
        return book;
      }
    }
    return null;
  }

  public ArrayList<Book> getAllBooks() {
    ArrayList<Book> sortedBooks = new ArrayList<>(this.books);
    Collections.sort(sortedBooks, new Comparator<Book>() {
      @Override
      public int compare(Book b1, Book b2) {
        return b1.getIsbn().compareTo(b2.getIsbn());
      }
    });
    return sortedBooks;
  }

  public ArrayList<Person> getAllPersons() {
    ArrayList<Person> allPersons = new ArrayList<>();
    allPersons.addAll(this.authors);
    allPersons.addAll(this.managers);
    allPersons.addAll(this.narrators);

    Collections.sort(allPersons, new Comparator<Person>() {
      @Override
      public int compare(Person p1, Person p2) {
        return Long.compare(p1.getId(), p2.getId());
      }
    });

    return allPersons;
  }

  @Override
  public void addObserver(StorageObserver observer) {
    if (!this.observers.contains(observer)) {
      this.observers.add(observer);
    }
  }

  @Override
  public void removeObserver(StorageObserver observer) {
    this.observers.remove(observer);
  }

  private void notifyStandChanged() {
    for (StorageObserver observer : this.observers) {
      observer.onStandChanged();
    }
  }

  private void notifyPersonChanged() {
    for (StorageObserver observer : this.observers) {
      observer.onPersonChanged();
    }
  }

  private void notifyPublisherChanged() {
    for (StorageObserver observer : this.observers) {
      observer.onPublisherChanged();
    }
  }

  private void notifyBookChanged() {
    for (StorageObserver observer : this.observers) {
      observer.onBookChanged();
    }
  }

}
