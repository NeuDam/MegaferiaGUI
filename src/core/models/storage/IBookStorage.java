/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.Book;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public interface IBookStorage {
  boolean addBook(Book book);

  Book getBook(String isbn);

  ArrayList<Book> getAllBooks();
}
