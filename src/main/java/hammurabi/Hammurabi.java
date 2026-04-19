 //package hammurabi; 

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner; 

public class Hammurabi {
    Random rand = new Random(); 
    Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args) {
        new Hammurabi().playGame();
    }

    void playGame() { 
        int population = 100;
        int grain = 2800; 
        int acres = 1000; 
        int landPrice = 19; 

        int starved = 0; 
        int newPeople = 5; 

        for (int year = 1; year <= 10; year++) { 
            printSummary(year, starved, newPeople, population, grain, acres, landPrice);
        }

        finalSummary(population, acres, starved); 
    }

    void printSummary(int year, int starved, int newPeople, int population, int grain, int acres, int landPrice) {
        System.out.println("\nOHHH great Hammurabi!"); 
        System.out.println("You are in year " + year + " of your ten year reign."); 
        System.out.println("In the previous year " + starved + " people starved to death! :(("); 
        System.out.println("In the previous year " + newPeople + " people entered the kingdom :))"); 
        System.out.println("The population in now " + population + "!"); 
        System.out.println("We have " + grain + " bushels of grain in the storage."); 
        System.out.println("The kingdom is " + acres + " acres large."); 
        System.out.println("Land is currently worth " + landPrice + " bushels per acre."); 

    }

    void finalSummary(int population, int acres, int starved) { 
        System.out.println("\nYour ten year rule has ended!"); 
        System.out.println("Final population is: " + population); 
        System.out.println("Final acres: " + acres); 
        System.out.println("Acres per person: " + (acres / population)); 
        if (starved == 0) { 
            System.out.println("Oooo Great Hammurabi, THE PEOPLE LOVE YOU!"); 
        } else {
            System.out.println("The people consensus say they are UNHAPPY with your rule!"); 
        }
    }

    int getNumber(String message) { 
        while (true) {
            System.out.print(message); 
            try { 
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isnt a number!");
            }
        }
    }
}