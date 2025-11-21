/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

/**
 *
 * @author edangulo
 */
public interface StorageObserver {

  void onStandChanged();

  void onPersonChanged();

  void onPublisherChanged();

  void onBookChanged();

}
