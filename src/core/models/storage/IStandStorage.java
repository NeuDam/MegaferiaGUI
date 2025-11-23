/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.Stand;
import java.util.ArrayList;

/**
 *
 * @author edangulo
 */
public interface IStandStorage {
  boolean addStand(Stand stand);

  Stand getStand(long id);

  ArrayList<Stand> getAllStands();
}
