// Main program entry point for WordGuess

import java.io.IOException;


public class Wordle {
  public static void main(String[] args) throws IOException {
    Game game;
    String wordsFile = AppPaths.dataFile("words.txt").toString();
    String lastGameFile = AppPaths.stateFile("lastgame.txt").toString();
    String historyFile = AppPaths.stateFile("history.txt").toString();

    if (args.length > 0) {
      if (args.length==1){
        //play today's game under accessibility mode
        if (args[0].equals("-a")){
          game = new Game(wordsFile);
          game.playAccessibility();
        }
        // Player wants to play the fixed game
        else {
          game = new Game(Integer.parseInt(args[0]), wordsFile);
          game.play();
        }
        game.save(lastGameFile);
        game.history(historyFile);
        game.display(historyFile);
      }
      // run in accessibility mode
      if (args.length==2){
        game = new Game(Integer.parseInt(args[1]), wordsFile);
        game.playAccessibility();
        game.save(lastGameFile);
        game.history(historyFile);
        game.display(historyFile);
      }
    }
    else {
      // Play today's game
      game = new Game(wordsFile);
      game.play();
      game.save(lastGameFile);
      game.history(historyFile);
      game.display(historyFile);
    }
  }
}
