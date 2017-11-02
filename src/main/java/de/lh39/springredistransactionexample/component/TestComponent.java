package de.lh39.springredistransactionexample.component;

import java.util.Map;

import de.lh39.springredistransactionexample.domain.Pet;
import de.lh39.springredistransactionexample.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Component to perform persistance operations with pets.
 *
 * @author lh39
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestComponent {
  PetRepository petRepository;
  Pet pet;

  @Autowired
  protected TestComponent(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  protected void setPet(Pet pet) {
    this.pet = pet;
  }

  @Transactional(rollbackFor = PetIllException.class)
  public void testTransactional() throws PetIllException {
    System.out.println("Saving pet with name: " + pet.getName() + " and type: " + pet.getPetType
        ().name());
    petRepository.savePet(pet);
    throw new PetIllException("pet is ill");
  }

  public void testNonTransactional() throws PetIllException {
    System.out.println("Saving pet with name: " + pet.getName() + " and type: " + pet.getPetType
        ().name());
    petRepository.savePet(pet);
    throw new PetIllException("pet is ill");
  }

  public void printAllPets() {
    Map<Object, Object> retrievedPets =
        petRepository.findAllPets();
    System.out.println("Found: " + retrievedPets.size() + " pets");
    retrievedPets.entrySet().forEach(entry -> {
      Pet pet = (Pet) entry.getValue();
      System.out.println("Pet: " + pet.getName() + " PetType: " + pet.getPetType().name());
    });
  }

  public void cleanUp() {
    System.out.println("Deleting all pets...");
    petRepository.findAllPets().entrySet().forEach(entry -> {
      Pet pet = (Pet) entry.getValue();
      petRepository.deletePet(pet.getName() + pet.getPetType());
    });
  }

}
