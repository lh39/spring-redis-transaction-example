package de.lh39.springredistransactionexample.component;

/**
 * Exception for simulating issues when persisting pets.
 *
 * @author lh39
 */
public class PetIllException extends Exception {

  public PetIllException() {
  }

  public PetIllException(String message) {
    super(message);
  }

  public PetIllException(Throwable cause) {
    super(cause);
  }

  public PetIllException(String message, Throwable cause) {
    super(message, cause);
  }
}
