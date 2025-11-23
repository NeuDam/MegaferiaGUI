/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import core.models.storage.IBookStorage;
import core.models.storage.IPersonStorage;
import core.models.storage.IPublisherStorage;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

/**
 *
 * @author edangulo
 */
public class BookController {

  private static final Pattern ISBN_PATTERN = Pattern.compile("^\\d{3}-\\d-\\d{2}-\\d{6}-\\d$");

  public static Response createPrintedBook(String title, ArrayList<Long> authorIds, String isbn, String genre,
      String format, String value, String publisherNit, String pages, String copies) {
    try {
      if (!ISBN_PATTERN.matcher(isbn.trim()).matches()) {
        return new Response("ISBN must follow format XXX-X-XX-XXXXXX-X", Status.BAD_REQUEST);
      }

      if (title.trim().isEmpty()) {
        return new Response("Title must not be empty", Status.BAD_REQUEST);
      }

      if (genre.trim().isEmpty()) {
        return new Response("Genre must not be empty", Status.BAD_REQUEST);
      }

      if (format.trim().isEmpty()) {
        return new Response("Format must not be empty", Status.BAD_REQUEST);
      }

      double valueDouble;
      try {
        valueDouble = Double.parseDouble(value.trim());
        if (valueDouble <= 0) {
          return new Response("Value must be greater than 0", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Value must be numeric", Status.BAD_REQUEST);
      }

      int pagesInt;
      try {
        pagesInt = Integer.parseInt(pages.trim());
        if (pagesInt <= 0) {
          return new Response("Pages must be greater than 0", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Pages must be numeric", Status.BAD_REQUEST);
      }

      int copiesInt;
      try {
        copiesInt = Integer.parseInt(copies.trim());
        if (copiesInt <= 0) {
          return new Response("Copies must be greater than 0", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Copies must be numeric", Status.BAD_REQUEST);
      }

      if (authorIds.isEmpty()) {
        return new Response("At least one author is required", Status.BAD_REQUEST);
      }

      HashSet<Long> uniqueAuthors = new HashSet<>(authorIds);
      if (uniqueAuthors.size() != authorIds.size()) {
        return new Response("Authors must not be repeated", Status.BAD_REQUEST);
      }

      IPersonStorage personStorage = Storage.getInstance();
      ArrayList<Author> authors = new ArrayList<>();
      for (Long authorId : authorIds) {
        Author author = personStorage.getAuthor(authorId);
        if (author == null) {
          return new Response("Author with id " + authorId + " not found", Status.NOT_FOUND);
        }
        authors.add(author);
      }

      IPublisherStorage publisherStorage = Storage.getInstance();
      Publisher publisher = publisherStorage.getPublisher(publisherNit.trim());
      if (publisher == null) {
        return new Response("Publisher not found", Status.NOT_FOUND);
      }

      IBookStorage bookStorage = Storage.getInstance();
      if (!bookStorage
          .addBook(new PrintedBook(title, authors, isbn, genre, format, valueDouble, publisher, pagesInt, copiesInt))) {
        return new Response("A book with that ISBN already exists", Status.BAD_REQUEST);
      }
      return new Response("Printed book created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response createDigitalBook(String title, ArrayList<Long> authorIds, String isbn, String genre,
      String format, String value, String publisherNit, String hyperlink) {
    try {
      if (!ISBN_PATTERN.matcher(isbn.trim()).matches()) {
        return new Response("ISBN must follow format XXX-X-XX-XXXXXX-X", Status.BAD_REQUEST);
      }

      if (title.trim().isEmpty()) {
        return new Response("Title must not be empty", Status.BAD_REQUEST);
      }

      if (genre.trim().isEmpty()) {
        return new Response("Genre must not be empty", Status.BAD_REQUEST);
      }

      if (format.trim().isEmpty()) {
        return new Response("Format must not be empty", Status.BAD_REQUEST);
      }

      double valueDouble;
      try {
        valueDouble = Double.parseDouble(value.trim());
        if (valueDouble <= 0) {
          return new Response("Value must be greater than 0", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Value must be numeric", Status.BAD_REQUEST);
      }

      if (authorIds.isEmpty()) {
        return new Response("At least one author is required", Status.BAD_REQUEST);
      }

      HashSet<Long> uniqueAuthors = new HashSet<>(authorIds);
      if (uniqueAuthors.size() != authorIds.size()) {
        return new Response("Authors must not be repeated", Status.BAD_REQUEST);
      }

      IPersonStorage personStorage = Storage.getInstance();
      ArrayList<Author> authors = new ArrayList<>();
      for (Long authorId : authorIds) {
        Author author = personStorage.getAuthor(authorId);
        if (author == null) {
          return new Response("Author with id " + authorId + " not found", Status.NOT_FOUND);
        }
        authors.add(author);
      }

      IPublisherStorage publisherStorage = Storage.getInstance();
      Publisher publisher = publisherStorage.getPublisher(publisherNit.trim());
      if (publisher == null) {
        return new Response("Publisher not found", Status.NOT_FOUND);
      }

      DigitalBook book;
      if (hyperlink == null || hyperlink.trim().isEmpty()) {
        book = new DigitalBook(title, authors, isbn, genre, format, valueDouble, publisher);
      } else {
        book = new DigitalBook(title, authors, isbn, genre, format, valueDouble, publisher, hyperlink);
      }

      IBookStorage bookStorage = Storage.getInstance();
      if (!bookStorage.addBook(book)) {
        return new Response("A book with that ISBN already exists", Status.BAD_REQUEST);
      }
      return new Response("Digital book created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response createAudiobook(String title, ArrayList<Long> authorIds, String isbn, String genre,
      String format, String value, String publisherNit, String duration, String narratorId) {
    try {
      if (!ISBN_PATTERN.matcher(isbn.trim()).matches()) {
        return new Response("ISBN must follow format XXX-X-XX-XXXXXX-X", Status.BAD_REQUEST);
      }

      if (title.trim().isEmpty()) {
        return new Response("Title must not be empty", Status.BAD_REQUEST);
      }

      if (genre.trim().isEmpty()) {
        return new Response("Genre must not be empty", Status.BAD_REQUEST);
      }

      if (format.trim().isEmpty()) {
        return new Response("Format must not be empty", Status.BAD_REQUEST);
      }

      double valueDouble;
      try {
        valueDouble = Double.parseDouble(value.trim());
        if (valueDouble <= 0) {
          return new Response("Value must be greater than 0", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Value must be numeric", Status.BAD_REQUEST);
      }

      int durationInt;
      try {
        durationInt = Integer.parseInt(duration.trim());
        if (durationInt <= 0) {
          return new Response("Duration must be greater than 0", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Duration must be numeric", Status.BAD_REQUEST);
      }

      long narratorIdLong;
      try {
        narratorIdLong = Long.parseLong(narratorId.trim());
      } catch (NumberFormatException ex) {
        return new Response("Narrator id must be numeric", Status.BAD_REQUEST);
      }

      if (authorIds.isEmpty()) {
        return new Response("At least one author is required", Status.BAD_REQUEST);
      }

      HashSet<Long> uniqueAuthors = new HashSet<>(authorIds);
      if (uniqueAuthors.size() != authorIds.size()) {
        return new Response("Authors must not be repeated", Status.BAD_REQUEST);
      }

      IPersonStorage personStorage = Storage.getInstance();
      ArrayList<Author> authors = new ArrayList<>();
      for (Long authorId : authorIds) {
        Author author = personStorage.getAuthor(authorId);
        if (author == null) {
          return new Response("Author with id " + authorId + " not found", Status.NOT_FOUND);
        }
        authors.add(author);
      }

      IPublisherStorage publisherStorage = Storage.getInstance();
      Publisher publisher = publisherStorage.getPublisher(publisherNit.trim());
      if (publisher == null) {
        return new Response("Publisher not found", Status.NOT_FOUND);
      }

      Narrator narrator = personStorage.getNarrator(narratorIdLong);
      if (narrator == null) {
        return new Response("Narrator not found", Status.NOT_FOUND);
      }

      IBookStorage bookStorage = Storage.getInstance();
      if (!bookStorage
          .addBook(new Audiobook(title, authors, isbn, genre, format, valueDouble, publisher, durationInt, narrator))) {
        return new Response("A book with that ISBN already exists", Status.BAD_REQUEST);
      }
      return new Response("Audiobook created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAllBooks() {
    try {
      IBookStorage storage = Storage.getInstance();
      ArrayList<Book> books = storage.getAllBooks();

      ArrayList<Book> booksCopy = new ArrayList<>();
      for (Book book : books) {
        try {
          booksCopy.add(book.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning books", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Books retrieved successfully", Status.OK, booksCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getBooksByAuthor(String authorId) {
    try {
      long authorIdLong;
      try {
        authorIdLong = Long.parseLong(authorId.trim());
      } catch (NumberFormatException ex) {
        return new Response("Author id must be numeric", Status.BAD_REQUEST);
      }

      IPersonStorage personStorage = Storage.getInstance();
      Author author = personStorage.getAuthor(authorIdLong);
      if (author == null) {
        return new Response("Author not found", Status.NOT_FOUND);
      }

      IBookStorage bookStorage = Storage.getInstance();
      ArrayList<Book> allBooks = bookStorage.getAllBooks();
      ArrayList<Book> authorBooks = new ArrayList<>();

      for (Book book : allBooks) {
        for (Author bookAuthor : book.getAuthors()) {
          if (bookAuthor.getId() == authorIdLong) {
            try {
              authorBooks.add(book.clone());
            } catch (CloneNotSupportedException ex) {
              return new Response("Error cloning books", Status.INTERNAL_SERVER_ERROR);
            }
            break;
          }
        }
      }

      return new Response("Books by author retrieved successfully", Status.OK, authorBooks);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getBooksByFormat(String format) {
    try {
      if (format.trim().isEmpty()) {
        return new Response("Format must not be empty", Status.BAD_REQUEST);
      }

      IBookStorage storage = Storage.getInstance();
      ArrayList<Book> allBooks = storage.getAllBooks();
      ArrayList<Book> formatBooks = new ArrayList<>();

      for (Book book : allBooks) {
        if (book.getFormat().equals(format.trim())) {
          try {
            formatBooks.add(book.clone());
          } catch (CloneNotSupportedException ex) {
            return new Response("Error cloning books", Status.INTERNAL_SERVER_ERROR);
          }
        }
      }

      return new Response("Books by format retrieved successfully", Status.OK, formatBooks);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getBooksByType(String type) {
    try {
      if (type.trim().isEmpty()) {
        return new Response("Type must not be empty", Status.BAD_REQUEST);
      }

      IBookStorage storage = Storage.getInstance();
      ArrayList<Book> allBooks = storage.getAllBooks();
      ArrayList<Book> typeBooks = new ArrayList<>();

      for (Book book : allBooks) {
        boolean match = false;
        if (type.equalsIgnoreCase("PrintedBook") && book instanceof PrintedBook) {
          match = true;
        } else if (type.equalsIgnoreCase("DigitalBook") && book instanceof DigitalBook) {
          match = true;
        } else if (type.equalsIgnoreCase("Audiobook") && book instanceof Audiobook) {
          match = true;
        }

        if (match) {
          try {
            typeBooks.add(book.clone());
          } catch (CloneNotSupportedException ex) {
            return new Response("Error cloning books", Status.INTERNAL_SERVER_ERROR);
          }
        }
      }

      return new Response("Books by type retrieved successfully", Status.OK, typeBooks);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

}
