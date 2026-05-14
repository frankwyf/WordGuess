// Domain exception for WordGuess runtime validation

package comp1721.cwk1;

public class GameException extends RuntimeException {
  public GameException(String message) {
    super(message);
  }
}
