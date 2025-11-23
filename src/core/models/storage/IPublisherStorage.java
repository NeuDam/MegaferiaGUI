/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.Publisher;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public interface IPublisherStorage {
  boolean addPublisher(Publisher publisher);

  Publisher getPublisher(String nit);

  ArrayList<Publisher> getAllPublishers();
}
