// Domain exception for WordGuess runtime validation

package wordguess;

public class GameException extends RuntimeException {
  public GameException(String message) {
    super(message);
  }
}
