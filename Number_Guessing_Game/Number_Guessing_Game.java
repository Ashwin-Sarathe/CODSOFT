import java.util.Random;
import java.util.Scanner;

public class Number_Guessing_Game {
    public static void main(String[] args) {
        Random rd = new Random();
        Scanner sc = new Scanner(System.in);
        int no_of_rounds =0;
        int final_score=0;
        int score=0;
        int min = 1;
        int max = 100;
        int guessNumber=0;
        int randomNumber = rd.nextInt(max-min+1)+min;
        char choice = 'Y';
        do {
            int numOfAttempts = 10;
            score=11;
            no_of_rounds++;
            do {
                score--;
                System.out.println("Please Enter your Guess:");
                guessNumber = sc.nextInt();
                int difference = randomNumber - guessNumber;
                numOfAttempts--;
                if (difference >= 30)
                    System.out.println("Your Guess is too Low!!!");
                else if (difference < 30 && difference > 0)
                    System.out.println("Your Guess is Slightly Low!!!");
                else if (difference <= -30)
                    System.out.println("Your Guess is too High!!!");
                else if (difference > -30 && difference < 0)
                    System.out.println("Your Guess is Slightly High!!!");
                else
                    System.out.println("You Guessed Right");
            } while (guessNumber != randomNumber && numOfAttempts > 0);
            if(numOfAttempts==0)
                System.out.println("Sorry!! You Ran Out of Attempts!!");
            if(score>=8)
                System.out.println("Bravo!! You Scored "+score);
            else
                System.out.println("You Scored "+score);
            final_score +=score;
            System.out.println("Ready for another round? (Y/N)");
            String input = sc.next();
            choice = input.charAt(0);
        }while(choice=='Y' || choice=='y');
        int total_score = no_of_rounds*10;
        System.out.println("Total number of rounds played: "+no_of_rounds);
        System.out.println("Final Score => "+final_score+ " out of "+total_score);
        sc.close();
    }
}
