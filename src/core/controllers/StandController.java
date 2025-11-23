/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Stand;
import core.models.storage.IStandStorage;
import core.models.storage.Storage;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public class StandController {

  public static Response createStand(String id, String price) {
    try {
      long idLong;
      double priceDouble;

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

      try {
        priceDouble = Double.parseDouble(price.trim());
        if (priceDouble <= 0) {
          return new Response("Price must be greater than 0", Status.BAD_REQUEST);
        }
      } catch (NumberFormatException ex) {
        return new Response("Price must be numeric", Status.BAD_REQUEST);
      }

      IStandStorage storage = Storage.getInstance();
      if (!storage.addStand(new Stand(idLong, priceDouble))) {
        return new Response("A stand with that id already exists", Status.BAD_REQUEST);
      }
      return new Response("Stand created successfully", Status.CREATED);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

  public static Response getAllStands() {
    try {
      IStandStorage storage = Storage.getInstance();
      ArrayList<Stand> stands = storage.getAllStands();

      ArrayList<Stand> standsCopy = new ArrayList<>();
      for (Stand stand : stands) {
        try {
          standsCopy.add(stand.clone());
        } catch (CloneNotSupportedException ex) {
          return new Response("Error cloning stands", Status.INTERNAL_SERVER_ERROR);
        }
      }

      return new Response("Stands retrieved successfully", Status.OK, standsCopy);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

}
