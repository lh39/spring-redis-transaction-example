package de.lh39.springredistransactionexample;

import de.lh39.springredistransactionexample.component.TestComponent;
import de.lh39.springredistransactionexample.component.TestComponentFactory;
import de.lh39.springredistransactionexample.domain.Pet;
import de.lh39.springredistransactionexample.domain.PetType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Entry point with tests.
 *
 * @author lh39
 */
@SpringBootApplication
public class SpringRedisTransactionExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringRedisTransactionExampleApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      printSpaces();
      System.out.println("###################################################################");
      System.out.println("TESTING TRANSACTIONAL BLOCK WITHIN FACTORY CREATED DOMAIN COMPONENT");
      System.out.println("###################################################################");
      Pet pet = new Pet("Croco", PetType.ALIGATOR);
      TestComponentFactory testComponentFactory = ctx.getBean(TestComponentFactory.class);
      TestComponent testComponent = testComponentFactory.create(pet);
      testComponent.printAllPets();
      try {
        testComponent.testTransactional();
      } catch (Exception exception) {
        System.out.println("Exception caught: " + exception.getMessage());
      }
      testComponent.printAllPets();
      testComponent.cleanUp();
      System.out.println("###################################################################");
      System.out.println("------------------------ TESTING FINISHED -------------------------");
      System.out.println("###################################################################");
      printSpaces();
    };
  }

  @Bean
  public CommandLineRunner commandLineRunne2r(ApplicationContext ctx) {
    return args -> {
      printSpaces();
      System.out.println("#######################################################################");
      System.out.println("TESTING NON TRANSACTIONAL BLOCK WITHIN FACTORY CREATED DOMAIN COMPONENT");
      System.out.println("#######################################################################");
      Pet pet = new Pet("Vitta", PetType.DINOSAUR);
      TestComponentFactory testComponentFactory = ctx.getBean(TestComponentFactory.class);
      TestComponent testComponent = testComponentFactory.create(pet);
      testComponent.printAllPets();
      try {
        testComponent.testNonTransactional();
      } catch (Exception exception) {
        System.out.println("Exception caught: " + exception.getMessage());
      }
      testComponent.printAllPets();
      testComponent.cleanUp();
      System.out.println("#######################################################################");
      System.out.println("-------------------------- TESTING FINISHED ---------------------------");
      System.out.println("#######################################################################");
      printSpaces();
    };
  }

  private void printSpaces() {
    System.out.print(System.lineSeparator() + System.lineSeparator() + System.lineSeparator());
  }
}
