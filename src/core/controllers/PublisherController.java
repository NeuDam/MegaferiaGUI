/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Manager;
import core.models.Publisher;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author edangulo
 */
public class PublisherController {

  private static final Pattern NIT_PATTERN = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d$");

  public static Response createPublisher(String nit, String name, String address, String managerId) {
    try {
      if (!NIT_PATTERN.matcher(nit.trim()).matches()) {
        return new Response("NIT must follow format XXX.XXX.XXX-X", Status.BAD_REQUEST);
      }

      if (name.trim().isEmpty()) {
        return new Response("Name must not be empty", Status.BAD_REQUEST);
      }

      if (address.trim().isEmpty()) {
        return new Response("Address must not be empty", Status.BAD_REQUEST);
      }

      long managerIdLong;
      try {
        managerIdLong = Long.parseLong(managerId.trim());
      } catch (NumberFormatException ex) {
        return new Response("Manager id must be numeric", Status.BAD_REQUEST);
      }

      Storage storage = Storage.getInstance();
      Manager manager = storage.getManager(managerIdLong);
      if (manager == null) {
        return new Response("Manager not found", Status.NOT_FOUND);
      }

      if (!storage.addPublisher(new Publisher(nit, name, address, manager))) {
        return new Response("A publisher with that NIT already exists", Status.BAD_REQUEST);
      }
      return new Response("Publisher created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAllPublishers() {
    try {
      Storage storage = Storage.getInstance();
      ArrayList<Publisher> publishers = storage.getAllPublishers();

      ArrayList<Publisher> publishersCopy = new ArrayList<>();
      for (Publisher publisher : publishers) {
        try {
          publishersCopy.add(publisher.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning publishers", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Publishers retrieved successfully", Status.OK, publishersCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

}
