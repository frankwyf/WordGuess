package wordguess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Guess {
  // Use this to get player input in readFromPlayer()
  private static final Scanner INPUT = new Scanner(System.in);

  //UML implementations
  private int guessNumber;
  private String chosenWord;

  // TODO: Implement constructor with int parameter
  Guess(int num){
    //in the range of 1~6
    if (num>0 && num<7){
      this.guessNumber=num;
    }
    else {
      String err="GuessNumber out of range!";
      throw new GameException(err);
    }
  }

  // TODO: Implement constructor with int and String parameters
  Guess(int num,String word){
    //in the range of 1~6
    if (num>0 && num<7){
      this.guessNumber=num;
    }
    else {
      String err="GuessNumber out of range!";
      throw new GameException(err);
    }
    //check the length of word
    if (word.length()!=5){
      String wrongLength="Invalid chosenWord (Invalid length)";
      throw new GameException(wrongLength);
    }
    int i;
    for (i=0;i<word.length();i++){
      if (!Character.isAlphabetic(word.charAt(i)) || word.length()!=5){
        String wrongWord="Invalid chosenWord(Illegal character)";
        throw new GameException(wrongWord);
      }
    }
    //turn into uppercase
    this.chosenWord=word.toUpperCase();
  }

  // TODO: Implement getGuessNumber(), returning an int
  public int getGuessNumber() {
    return guessNumber;
  }

  // TODO: Implement getChosenWord(), returning a String
  public String getChosenWord() {
    return chosenWord.toUpperCase();
  }

  // TODO: Implement readFromPlayer()
  public void readFromPlayer(){
    System.out.print("Enter guess ("+ this.guessNumber +"/6): ");
    chosenWord=INPUT.next();
    //determine whether the input is valid word or not
    if (chosenWord.length()!=5){
      String wrongLength="Invalid chosenWord (Invalid length)";
      throw new GameException(wrongLength);
    }
     int check;
    for (check=0;check<chosenWord.length();check++){
      if (!Character.isAlphabetic(chosenWord.charAt(check))){
        String wrongInput="Invalid input!";
        throw new GameException(wrongInput);
      } 
    }
    chosenWord=chosenWord.toUpperCase();
  }

  // TODO: Implement compareWith(), giving it a String parameter and String return type
  public String compareWith(String target){
    String out= "";
    //loop through the whole string
    int i;
    for (i=0;i<target.length();i++){
      //both match in character and position,set background to green
      if (target.charAt(i)==chosenWord.charAt(i)){
        out+="\033[30;102m "+chosenWord.charAt(i)+" \033[0m";
      }
      //in target but not in the right position
      if (target.indexOf(chosenWord.charAt(i))!=-1 && target.charAt(i)!=chosenWord.charAt(i)){
        //the first same miss letter to show up,set to yellow
        if(out.indexOf(chosenWord.charAt(i))==-1){
          out+="\033[30;103m "+chosenWord.charAt(i)+ " \033[0m";
        }
        //repeated characters are set to white
        else {out+="\033[30;107m "+chosenWord.charAt(i)+" \033[0m";}
      }
      //the character is not in the string, set background to white
      if (target.indexOf(chosenWord.charAt(i))==-1){
        out+="\033[30;107m "+chosenWord.charAt(i)+" \033[0m";
      }
    }
    //deal with repeated words
    int j;
    for (j=0;j<target.length();j++){
      String sameGreen="\033[30;102m "+chosenWord.charAt(j)+ " \033[0m";
      String sameYellow="\033[30;103m "+chosenWord.charAt(j)+ " \033[0m";
      String sameWhite="\033[30;107m "+chosenWord.charAt(j)+ " \033[0m";
      //three color for one character is not allowed
      if (out.contains(sameYellow) && out.contains(sameWhite) && out.contains(sameGreen)){
        out=out.replace(sameYellow, sameWhite);
      }
    }
    return out;
  }

  // TODO:Implement compareWith() method for accessibility mode of the game
  public String compareWithA(String target){
    //integer array to store the positions of correct matches
    List<Integer> correct= new ArrayList<>();
    //integer array to store the positions of correct but in wording position matches
    List<Integer> miss= new ArrayList<>();
    //integer array to store the positions of wrong letters
    List<Integer> wrong= new ArrayList<>();
    //output string
    String out= "";
    //loop through the whole string
    int i;
    for (i=0;i<target.length();i++){
      //both match in character and position,set background to green
      if (target.charAt(i)==chosenWord.charAt(i)){
        int j=i+1;
        correct.add(j);
      }
      //in target but not in the right position
      if (target.indexOf(chosenWord.charAt(i))!=-1 && target.charAt(i)!=chosenWord.charAt(i)){
        int x=i+1;
        miss.add(x);
      }
      //the character is not in the string, set background to white
      if (target.indexOf(chosenWord.charAt(i))==-1){
        int y=i+1;
        wrong.add(y);
      }
    }
    //Form the output list
    int output;
    //deal with miss letters
    for (output=0;output<miss.size();output++){
      out+=String.valueOf(miss.get(output));
      if (miss.get(output)<4){
        if (miss.get(output)==1){
          out+="st, ";
        }
        if (miss.get(output)==2){
          out+="nd, ";
        }
        if (miss.get(output)==3){
          out+="rd, ";
        }
      }
      else{
        out+="th, ";
      }
    }
    //add the message
    if (miss.size()!=0){
      //the last position end with no ", "
      out=out.substring(0,out.length()-2);
      out+=" correct but in wrong place; ";
    }
    //deal with correct letters
    for (output=0;output<correct.size();output++){
      out+=String.valueOf(correct.get(output));
      if (correct.get(output)<4){
        if (correct.get(output)==1){
          out+="st, ";
        }
        if (correct.get(output)==2){
          out+="nd, ";
        }
        if (correct.get(output)==3){
          out+="rd, ";
        }
      }
      else{
        out+="th, ";
      }
    }
    //add the message
    if (correct.size()!=0){
      //the last position end with no ", "
      out=out.substring(0,out.length()-2);
      out+=" perfect; ";
    }
    //deal with wrong letters
    for (output=0;output<wrong.size();output++){
      out+=String.valueOf(wrong.get(output));
      if (wrong.get(output)<4){
        if (wrong.get(output)==1){
          out+="st, ";
        }
        if (wrong.get(output)==2){
          out+="nd, ";
        }
        if (wrong.get(output)==3){
          out+="rd, ";
        }
      }
      else{
        out+="th, ";
      }
    }
    //add the message
    if (wrong.size()!=0){
      //the last position end with no ", "
      out=out.substring(0,out.length()-2);
      out+=" wrong.";
    }
    //the last two element should be connected with an "and" instead of a comma
    if (out.contains(" perfect") && (out.indexOf("f")-out.indexOf(";"))>9){
      StringBuilder temp=new StringBuilder(out);
      temp.replace(out.indexOf("f")-9,out.indexOf("f")-7, " and ");
      out=temp.toString();
    }
    else if (out.contains(" correct") && out.indexOf("c")>6){
      StringBuilder temp=new StringBuilder(out);
      temp.replace(out.indexOf("c")-6,out.indexOf("c")-4, " and ");
      out=temp.toString();
    }
    else if (out.contains(" wrong.")){
      StringBuilder temp=new StringBuilder(out);
      temp.replace(out.indexOf(".")-11,out.indexOf(".")-10, " and");
      out=temp.toString();
    }
    //deal with all wrong situations
    if (out.equals("1st, 2nd, 3rd, 4th and 5th wrong.")){
      out="All wrong!";
    }
    //message end with a period
    if (out.charAt(out.length()-2)==';'){
      out=out.substring(0, out.length()-2);
      out+='.';
    }
    return out;
  }

  // TODO: Implement matches(), giving it a String parameter and boolean return type
  public boolean matches(String target){
    if (chosenWord.equals(target)){return true;}
    return false;
  }
}
