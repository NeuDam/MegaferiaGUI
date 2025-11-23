/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import core.models.storage.IPersonStorage;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class PersonController {

  public static Response createAuthor(String id, String firstname, String lastname) {
    try {
      long idLong;

      try {
        idLong = Long.parseLong(id.trim());
        if (idLong < 0) {
          return new Response("Id must be positive", Status.BAD_REQUEST);
        }
        if (String.valueOf(idLong).length() > 15) {
          return new Response("Id must have at most 15 digits", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Id must be numeric", Status.BAD_REQUEST);
      }

      if (firstname.trim().isEmpty()) {
        return new Response("Firstname must not be empty", Status.BAD_REQUEST);
      }

      if (lastname.trim().isEmpty()) {
        return new Response("Lastname must not be empty", Status.BAD_REQUEST);
      }

      IPersonStorage storage = Storage.getInstance();
      if (!storage.addAuthor(new Author(idLong, firstname, lastname))) {
        return new Response("A person with that id already exists", Status.BAD_REQUEST);
      }
      return new Response("Author created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response createManager(String id, String firstname, String lastname) {
    try {
      long idLong;

      try {
        idLong = Long.parseLong(id.trim());
        if (idLong < 0) {
          return new Response("Id must be positive", Status.BAD_REQUEST);
        }
        if (String.valueOf(idLong).length() > 15) {
          return new Response("Id must have at most 15 digits", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Id must be numeric", Status.BAD_REQUEST);
      }

      if (firstname.trim().isEmpty()) {
        return new Response("Firstname must not be empty", Status.BAD_REQUEST);
      }

      if (lastname.trim().isEmpty()) {
        return new Response("Lastname must not be empty", Status.BAD_REQUEST);
      }

      IPersonStorage storage = Storage.getInstance();
      if (!storage.addManager(new Manager(idLong, firstname, lastname))) {
        return new Response("A person with that id already exists", Status.BAD_REQUEST);
      }
      return new Response("Manager created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response createNarrator(String id, String firstname, String lastname) {
    try {
      long idLong;

      try {
        idLong = Long.parseLong(id.trim());
        if (idLong < 0) {
          return new Response("Id must be positive", Status.BAD_REQUEST);
        }
        if (String.valueOf(idLong).length() > 15) {
          return new Response("Id must have at most 15 digits", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Id must be numeric", Status.BAD_REQUEST);
      }

      if (firstname.trim().isEmpty()) {
        return new Response("Firstname must not be empty", Status.BAD_REQUEST);
      }

      if (lastname.trim().isEmpty()) {
        return new Response("Lastname must not be empty", Status.BAD_REQUEST);
      }

      IPersonStorage storage = Storage.getInstance();
      if (!storage.addNarrator(new Narrator(idLong, firstname, lastname))) {
        return new Response("A person with that id already exists", Status.BAD_REQUEST);
      }
      return new Response("Narrator created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAllPersons() {
    try {
      IPersonStorage storage = Storage.getInstance();
      ArrayList<Person> persons = storage.getAllPersons();

      ArrayList<Person> personsCopy = new ArrayList<>();
      for (Person person : persons) {
        try {
          personsCopy.add(person.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning persons", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Persons retrieved successfully", Status.OK, personsCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAllAuthors() {
    try {
      IPersonStorage storage = Storage.getInstance();
      ArrayList<Author> authors = storage.getAllAuthors();

      ArrayList<Author> authorsCopy = new ArrayList<>();
      for (Author author : authors) {
        try {
          authorsCopy.add(author.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning authors", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Authors retrieved successfully", Status.OK, authorsCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAllManagers() {
    try {
      IPersonStorage storage = Storage.getInstance();
      ArrayList<Manager> managers = storage.getAllManagers();

      ArrayList<Manager> managersCopy = new ArrayList<>();
      for (Manager manager : managers) {
        try {
          managersCopy.add(manager.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning managers", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Managers retrieved successfully", Status.OK, managersCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAllNarrators() {
    try {
      IPersonStorage storage = Storage.getInstance();
      ArrayList<Narrator> narrators = storage.getAllNarrators();

      ArrayList<Narrator> narratorsCopy = new ArrayList<>();
      for (Narrator narrator : narrators) {
        try {
          narratorsCopy.add(narrator.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning narrators", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Narrators retrieved successfully", Status.OK, narratorsCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAuthorsWithMostPublishers() {
    try {
      IPersonStorage storage = Storage.getInstance();
      ArrayList<Author> authors = storage.getAllAuthors();

      ArrayList<Author> authorsMax = new ArrayList<>();
      int maxPublishers = -1;
      for (Author author : authors) {
        if (author.getPublisherQuantity() > maxPublishers) {
          maxPublishers = author.getPublisherQuantity();
          authorsMax.clear();
          authorsMax.add(author);
        } else if (author.getPublisherQuantity() == maxPublishers) {
          authorsMax.add(author);
        }
      }

      ArrayList<Author> authorsMaxCopy = new ArrayList<>();
      for (Author author : authorsMax) {
        try {
          authorsMaxCopy.add(author.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning authors", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Authors with most publishers retrieved successfully", Status.OK, authorsMaxCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

}
