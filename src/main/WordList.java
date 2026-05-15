import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WordList {
  private final List<String> words;
  // TODO: Implement constructor with a String parameter
  WordList(String filename) throws IOException {
    Path file= Paths.get(filename);
    this.words= Files.readAllLines(file);
  }

  // TODO: Implement size() method, returning an int
  public int size(){
    return this.words.size();
  }

  // TODO: Implement getWord() with an int parameter, returning a String
  public String getWord(int n) throws GameException{
    //get the game number
    if (n<0 || n>words.size()-1){
      String gameNumerr="Invalid Game number!";
      throw new GameException(gameNumerr);
    }
    //return the word specified by the game number
    else {return this.words.get(n);}
  }
}
