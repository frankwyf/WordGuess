// Main program entry point for WordGuess

package wordguess;

import java.io.IOException;


public class Wordle {
  public static void main(String[] args) throws IOException {
    Game game;

    if (args.length > 0) {
      if (args.length==1){
        //play today's game under accessibility mode
        if (args[0].equals("-a")){
          game = new Game("data/words.txt");
          game.playAccessibility();
        }
        // Player wants to play the fixed game
        else {
          game = new Game(Integer.parseInt(args[0]), "data/words.txt");
          game.play();
        }
        game.save("build/lastgame.txt");
        game.history("build/history.txt");
        game.display("build/history.txt");
      }
      // run in accessibility mode
      if (args.length==2){
        game = new Game(Integer.parseInt(args[1]), "data/words.txt");
        game.playAccessibility();
        game.save("build/lastgame.txt");
        game.history("build/history.txt");
        game.display("build/history.txt");
      }
    }
    else {
      // Play today's game
      game = new Game("data/words.txt");
      game.play();
      game.save("build/lastgame.txt");
      game.history("build/history.txt");
      game.display("build/history.txt");
    }
  }
}
