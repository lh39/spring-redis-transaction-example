package de.lh39.springredistransactionexample.repository;

import java.util.Map;

import de.lh39.springredistransactionexample.domain.Pet;

/**
 * Repository for pet persistance.
 *
 * @author lh39
 */
public interface PetRepository {
  void savePet(Pet pet);

  void updatePet(Pet pet);

  Pet findPet(String nameAndType);

  Map<Object, Object> findAllPets();

  void deletePet(String nameAndType);
}
