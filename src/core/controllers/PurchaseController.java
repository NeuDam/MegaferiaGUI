/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Publisher;
import core.models.Stand;
import core.models.storage.Storage;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author edangulo
 */
public class PurchaseController {

  public static Response purchaseStands(ArrayList<Long> standIds, ArrayList<String> publisherNits) {
    try {
      if (standIds.isEmpty()) {
        return new Response("At least one stand is required", Status.BAD_REQUEST);
      }

      if (publisherNits.isEmpty()) {
        return new Response("At least one publisher is required", Status.BAD_REQUEST);
      }

      HashSet<Long> uniqueStands = new HashSet<>(standIds);
      if (uniqueStands.size() != standIds.size()) {
        return new Response("Stands must not be repeated", Status.BAD_REQUEST);
      }

      HashSet<String> uniquePublishers = new HashSet<>(publisherNits);
      if (uniquePublishers.size() != publisherNits.size()) {
        return new Response("Publishers must not be repeated", Status.BAD_REQUEST);
      }

      Storage storage = Storage.getInstance();
      ArrayList<Stand> stands = new ArrayList<>();
      for (Long standId : standIds) {
        Stand stand = storage.getStand(standId);
        if (stand == null) {
          return new Response("Stand with id " + standId + " not found", Status.NOT_FOUND);
        }
        stands.add(stand);
      }

      ArrayList<Publisher> publishers = new ArrayList<>();
      for (String nit : publisherNits) {
        Publisher publisher = storage.getPublisher(nit.trim());
        if (publisher == null) {
          return new Response("Publisher with NIT " + nit + " not found", Status.NOT_FOUND);
        }
        publishers.add(publisher);
      }

      for (Stand stand : stands) {
        for (Publisher publisher : publishers) {
          stand.addPublisher(publisher);
          publisher.addStand(stand);
        }
      }

      return new Response("Purchase completed successfully", Status.OK);
    } catch (Exception ex) {
      return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
  }

}
