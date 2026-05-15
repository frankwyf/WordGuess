// Domain exception for WordGuess runtime validation

public class GameException extends RuntimeException {
  public GameException(String message) {
    super(message);
  }
}
