import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.TextAnchor;



public class Game {
  private final int gameNumber;
  private final String target;
  private String success="Fail";
  private int finalGuess;
  private final List<String> summary= new ArrayList<>();
  private final int [] count=new int [6];
  // TODO: Implement constructor with String parameter
  Game(String filename) throws IOException{
    //get the difference of days
    LocalDate today=LocalDate.now();
    LocalDate origin=LocalDate.of(2021,6,19);
    Long difference=today.toEpochDay() - origin.toEpochDay();
    //initializing the gameNumber
    this.gameNumber=difference.intValue();
    //read in the words
    WordList todaygame=new WordList(filename);
    //get the target word
    this.target=todaygame.getWord(this.gameNumber);
  }

  // TODO: Implement constructor with int and String parameters
  Game(int num,String filename) throws IOException {
    this.gameNumber=num;
    //read in the words
    WordList specialGame=new WordList(filename);
     //get the target word
     this.target=specialGame.getWord(this.gameNumber);
  }

  // TODO: Implement play() method
  public void play(){
    //title of the game
    System.out.println("WORDLE "+this.gameNumber);
    //start the game, maximum guess is 6 times
    int i=1;
    while (i<7){
      //create the guess object
      Guess temp = new Guess(i);
      //read user input with a title line above
      try {
        temp.readFromPlayer();
      } catch (GameException e) {
        //TODO: handle invalid user input
        System.out.println("Invalid input!(Only five letter words is allowed)");
        //fail at the last tempt
        if (i==6){
          break;
        }
        else {
          i=i+1;
          temp=new Guess(i);
        }
        boolean correction=true;
        while (correction){
          try {
            temp.readFromPlayer();
            correction=false;
          } catch (GameException invalidInputException) {
            //TODO: handle repeated invalid user input
            System.out.println("Invalid input!(Only five letter words is allowed)");
            //out of trial times
            if (i==6){
              System.out.println("Nope - out of trials!");
              correction=false;
            }
            else {
              i=i+1;
              temp=new Guess(i);
            }
          }
        }
      }
      //print the result
      System.out.println(temp.compareWith(target));
      //save the summary
      this.summary.add(temp.compareWith(target));
      //different winning messages
      if (temp.matches(target) && i==1){
        this.success="Win";
        this.finalGuess=i;
        System.out.println("Superb - Got it in one!");
        break;
      }
      if (temp.matches(target) && i>1 && i<6){
        this.success="Win";
        this.finalGuess=i;
        System.out.println("Well done!");
        break;
      }
      if (temp.matches(target) && i==6){
        this.success="Win";
        this.finalGuess=i;
        System.out.println("That was a close call!");
        break;
      }
      //if all temps are incorrect
      if (!temp.matches(target) && i==6){
        this.success="Fail";
        this.finalGuess=i;
        System.out.println("Nope - Better luck next time!");
        break;
      }
      //i is always the final guess number here
      this.finalGuess=i;
      //create the next guess object
      i+=1;
    }
  }

  //TODO:Implement play() method for accessibility mode
  public void playAccessibility(){
    //title of the game
    System.out.println("WORDLE "+this.gameNumber);
    //start the game, maximum guess is 6 times
    int i=1;
    while (i<7){
      //create the guess object
      Guess temp = new Guess(i);
      //read user input with a title line above
      try {
        temp.readFromPlayer();
      } catch (GameException e) {
        //TODO: handle invalid user input
        System.out.println("Invalid input!(Only five letter words is allowed)");
        //fail at the last tempt
        if (i==6){
          break;
        }
        else {
          i=i+1;
          temp=new Guess(i);
        }
        boolean correction=true;
        while (correction){
          try {
            temp.readFromPlayer();
            correction=false;
          } catch (GameException invalidInputException) {
            //TODO: handle repeated invalid user input
            System.out.println("Invalid input!(Only five letter words is allowed)");
            //out of trial times
            if (i==6){
              System.out.println("Nope - out of trials!");
              correction=false;
            }
            else {
              i=i+1;
              temp=new Guess(i);
            }
          }
        }
      }
      //print the result
      System.out.println(temp.compareWithA(target));
      //save the summary
      this.summary.add(temp.compareWith(target));
      //different winning messages
      if (temp.matches(target)){
        this.success="Win";
        this.finalGuess=i;
        System.out.println("You won!");
        break;
      }
      //if all temps are incorrect
      if (!temp.matches(target) && i==6){
        this.success="Fail";
        this.finalGuess=i;
        System.out.println("Nope - Better luck next time!");
        break;
      }
      //integer i is always the final guess number here
      this.finalGuess=i;
      //create the next guess object
      i+=1;
    }
  }

  // TODO: Implement save() method, with a String parameter
  public void save(String filename){
    File record=new File(filename);
    try (PrintWriter save = new PrintWriter(record)) {
      int j;
      for (j=0;j<this.summary.size();j++){
        //write in the summary
        save.println(this.summary.get(j));
      }
    } catch (IOException e) {
      System.out.println("Failed to save game summary.");
    }
  }

  //TODO:Implement history(), with a String parameter
  public void history(String history){
    //store the game history in one single string
    String record=this.gameNumber+","+this.success+","+this.finalGuess;
    File save=new File(history);
    if (!save.exists()){
      try {
        save.createNewFile();
      } catch (IOException e) {
        //TODO: create history.txt if not exists
        System.out.println("Failed to create history file.");
      }
    }
    try (FileWriter writer = new FileWriter(save, true);
         PrintWriter output = new PrintWriter(writer)) {
      output.println(record);
      output.flush();
      writer.flush();
    } catch (IOException e) {
      //fail to store the record
      System.out.println("Fail to write history record.");
    }
  }
  //TODO:display the history of games
  public void display(String history){
    List<String> show= new ArrayList<>();
    try {
      //read in all the history records and compute the results
      Path display=Paths.get(history);
      show=Files.readAllLines(display);
    } catch (IOException e) {
      //TODO: handle IOException
      System.out.println("History file error.");
    }
    //Display the number of games been played
    int numberOfgames=show.size();
    System.out.println("The numbers of game in history is: "+numberOfgames+".");
    //integer to store the number of winning games
    int winGames=0;
    //check for win games 
    int eachGame;
    for (eachGame=0;eachGame<show.size();eachGame++){
      if (show.get(eachGame).contains("Win")){
        winGames+=1;
      }
    }
    //convert winGames into percentage
    double percentage=(1.0*winGames/(show.size()))*100;
    System.out.println("Percentage of games that were won is: "
    +new DecimalFormat("0.00").format(percentage)+"%.");
    //check for current win streaks,trace back from the current game
    int currentStreak=0;
    // if it was a win in current game, keep counting from the back of the records
    if (show.get(show.size()-1).contains("Win")){
      int currentWin;
      for (currentWin=show.size()-1;currentWin>0;currentWin--){
        if (show.get(currentWin).contains("Win")){
          currentStreak+=1;
        }
        else{
          break;
        }
      }
      System.out.println("Length of current winning streak is: "+currentStreak+".");
    }
    //else, current win streak is 0
    else{
      System.out.println("You just lost!Length of current winning streak is: "+currentStreak+".");
    }
    //longest winning streak in history
    List<Integer> allWinstreaks= new ArrayList<>();
    //steak is used to store every winning steak in history
    int streak=0;
    //loop all the records to find every streaks
    int streaks;
    for (streaks=0;streaks<show.size();streaks++){
      if (show.get(streaks).contains("Win")) {
        streak += 1;
      }
      else {
        //end of one winning streak
        allWinstreaks.add(streak);
        streak=0;
      }
    }
    //if the records ends with a win, store the current streak
    if (streak!=0) {
      allWinstreaks.add(streak);
    }
    //if the game was never won
    if (allWinstreaks.isEmpty()){
      System.out.println("Never won! Try harder next time!");
    }
    else {
      //find the max value in the list and display
      int longest=allWinstreaks.get(0);
      //get the max value in allWinstreaks
      for (Integer temp:allWinstreaks){
        //get the maximum value in allWinstreaks
        if (temp != null) {
          longest = Math.max(longest, temp);
        }
      }
      System.out.println("Longest winning streak is: "+longest+".");
    }
    //draw the histogram by guess number
    //read in all the guess numbers
    List<Integer> allGuess=new ArrayList<>();
    int loop;
    for (loop=0;loop<show.size();loop++){
      //get the last char, which stores the number of guess in the game
      int guesses;
      guesses=show.get(loop).length()-1;
      //when converting char into integer, according to unicode a -48 is required
      allGuess.add((int) show.get(loop).charAt(guesses) -48);
    }
    //count the occurrence of each guess number,store in integer array count
    for (loop=0;loop<allGuess.size();loop++){
      switch (allGuess.get(loop)) {
        case 1 -> count[0]++;
        case 2 -> count[1]++;
        case 3 -> count[2]++;
        case 4 -> count[3]++;
        case 5 -> count[4]++;
        case 6 -> count[5]++;
        default -> {
        }
      }
    }
    // draw the histogram on terminal
    System.out.println("Histogram on the terminal:\n");
    //print all the guess number and their length
    System.out.print("1: ");
    int graph;
    for (graph=0;graph<count[0];graph++){
      System.out.print("\033[30;102m \b \033[0m");
    }
    //print their length and change the line
    System.out.println(" "+count[0]+"\n");
    System.out.print("2: ");
    for (graph=0;graph<count[1];graph++){
      System.out.print("\033[30;104m \b \033[0m");
    }
    System.out.println(" "+count[1]+"\n");
    System.out.print("3: ");
    for (graph=0;graph<count[2];graph++){
      System.out.print("\033[30;105m \b \033[0m");
    }
    System.out.println(" "+count[2]+"\n");
    System.out.print("4: ");
    for (graph=0;graph<count[3];graph++){
      System.out.print("\033[30;106m \b \033[0m");
    }
    System.out.println(" "+count[3]+"\n");
    System.out.print("5: ");
    for (graph=0;graph<count[4];graph++){
      System.out.print("\033[30;103m \b \033[0m");
    }
    System.out.println(" "+count[4]+"\n");
    System.out.print("6: ");
    for (graph=0;graph<count[5];graph++){
      System.out.print("\033[30;101m \b \033[0m");
    }
    System.out.println(" "+count[5]+"\n");
    Date exitTime=new Date();
    System.out.println("End at: "+ exitTime);
    //show histogram generated by jfreechart
    BarChartDemo chart = new BarChartDemo("Guess distribution");
    chart.pack();
    chart.setMinimumSize(new Dimension(800, 600));
    chart.setPreferredSize(new Dimension(800, 600));
    chart.setVisible(true);
  }


  //draw the histogram by jfreechart
  public class BarChartDemo extends ApplicationFrame {
    public BarChartDemo(String title) {
        super(title);
        JFreeChart chart = createChart();
        //build a blank panel
        this.setContentPane(new ChartPanel(chart)); 
    }

    private JFreeChart createChart() {
        JFreeChart chart = ChartFactory.createBarChart3D(
                // title
                "Histogram of guess distribution", 
                // x-axis title
                "Guess number", 
                // y-axis title
                "Number", 
                // dataset
                getDataSet(), 
                // orientation: vertical
                PlotOrientation.VERTICAL, 
                // show legend
                false,   
                // generate tool      
                false,     
                // generate URL link
                false        
        );
        //set the details of barchart
        CategoryPlot plot = chart.getCategoryPlot();
        // line's background color
        plot.setBackgroundPaint(Color.lightGray);
        // horizontal line color
        plot.setRangeGridlinePaint(Color.RED);
        // show the numbers of each guess number
        CustomRender renderer=new CustomRender();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, 
        TextAnchor.BASELINE_LEFT));
        renderer.setItemLabelAnchorOffset(10D);
        // distance between bars
        renderer.setItemMargin(0.4);
        //set color of the bars
        
        renderer.getItemPaint(0, 1);
        plot.setRenderer(renderer);
        return chart;
    }
    // build the dataset
    private CategoryDataset getDataSet() {
      //create the bar chart
      DefaultCategoryDataset dataset = new DefaultCategoryDataset();
      dataset.addValue(count[0], "", "1");
      dataset.addValue(count[1], "", "2");
      dataset.addValue(count[2], "", "3");
      dataset.addValue(count[3], "", "4");
      dataset.addValue(count[4], "", "5");
      dataset.addValue(count[5], "", "6");
      return dataset;
    }
    public class CustomRender extends org.jfree.chart.renderer.category.IntervalBarRenderer{
      //set the color for bars
      private final Paint[] colors;

      public CustomRender(){
        String[] colorValues = {"#008000", "#5F9EA0", "#FFA500", "#FF69B4", "#DC143C", "#800000"};
        colors=new Paint[colorValues.length];
        int i;
        for (i=0; i< colorValues.length; i++){
          colors[i]=Color.decode(colorValues[i]);
        }
      }
      @Override
      public Paint getItemPaint(int i,int j){
        //get the colors set previously
        return colors[j % colors.length];
      }
    }

  }
}