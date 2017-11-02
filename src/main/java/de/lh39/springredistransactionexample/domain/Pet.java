package de.lh39.springredistransactionexample.domain;

import java.io.Serializable;

/**
 * Immutable pet pojo.
 *
 * @author lh39
 */
public class Pet implements Serializable {
  private final PetType type;
  private final String name;

  public Pet(String name, PetType type) {
    this.type = type;
    this.name = name;
  }

  public PetType getPetType() {
    return this.type;
  }

  public String getName() {
    return this.name;
  }

}
