import java.util.Random;
import java.util.Scanner;
class NumberGenerator{
    Random rd = new Random();
    int lowerBound=1;
    int upperBound=100;
    int randomNumber=0;
    int generateNum(){
        randomNumber = rd.nextInt(upperBound-lowerBound+1)+lowerBound;
        return randomNumber;
    }
}
class Game{
    int attempts=0;
    int maxAttempts=10;
    int targetNumber=0;
    Game(NumberGenerator obj){
        targetNumber=obj.generateNum();
    }
    boolean isCorrectGuess(int guess){
        if(guess==targetNumber)
            return true;
        else
            return false;
    }
    boolean isOutOfAttempts(){
        if(attempts==10)
            return true;
        else
            return false;
    }
    void incrementAttempts(){
        attempts++;
    }
    int getRemainingAttempts(){
        return maxAttempts-attempts;
    }
    int getTargetNumber(){
        return targetNumber;
    }
}
class GameRunner{
    Scanner sc = new Scanner(System.in);
    int no_of_rounds=0;
    int guessNumber=0;
    int difference=0;
    char choice;
    int round_score=11,final_score=0;
    void play(){
        no_of_rounds++;
        NumberGenerator ng = new NumberGenerator();
        Game game = new Game(ng);
        while(!game.isOutOfAttempts()){
            System.out.println("Please Enter your Guess:");
            guessNumber = sc.nextInt();
            round_score--;
            game.incrementAttempts();
            if(game.isCorrectGuess(guessNumber)){
                System.out.println("Your Guess is Correct!!!!");
                System.out.println("Round Score: "+round_score);
                final_score+=round_score;
                break;
            }
            else{
                System.out.println("Attempts Left: "+game.getRemainingAttempts());
                difference = game.targetNumber - guessNumber;
                if (difference >= 30)
                    System.out.println("Your Guess is too Low!!!");
                else if (difference > 0)
                    System.out.println("Your Guess is Slightly Low!!!");
                else if (difference <= -30)
                    System.out.println("Your Guess is too High!!!");
                else
                    System.out.println("Your Guess is Slightly High!!!");

            }
        }
        if(game.isOutOfAttempts()) {
            System.out.println("Sorry!! You Ran Out Of Attempts!");
            System.out.println("The Answer was: "+game.getTargetNumber());
        }
        System.out.print("Play again? (y/n): ");
        choice = sc.next().charAt(0);
        if(choice=='Y' || choice=='y'){
            round_score=11;
            play();
        }
        System.out.println("Number of Rounds Played: "+no_of_rounds);
        System.out.println("You Scored "+final_score+" out of "+no_of_rounds*10);
    }
}
public class Number_Guessing_Game {
    public static void main(String[] args) {
        GameRunner gr = new GameRunner();
        gr.play();
    }
}
