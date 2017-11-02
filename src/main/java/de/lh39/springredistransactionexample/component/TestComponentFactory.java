package de.lh39.springredistransactionexample.component;


import javax.inject.Provider;

import de.lh39.springredistransactionexample.domain.Pet;
import de.lh39.springredistransactionexample.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory for creating {@link TestComponent} with partially autowired fields.
 *
 * @author lh39
 */
@Component
public class TestComponentFactory {
  @Autowired
  PetRepository petRepository;
  @Autowired
  Provider<TestComponent> testComponentProvider;

  public TestComponent create(Pet pet) {
    TestComponent testComponent = testComponentProvider.get();
    testComponent.setPet(pet);
    return testComponent;
  }
}
