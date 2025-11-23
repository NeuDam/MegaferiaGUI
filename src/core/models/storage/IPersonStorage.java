/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.Author;
import core.models.Manager;
import core.models.Narrator;
import core.models.Person;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public interface IPersonStorage {
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
}
