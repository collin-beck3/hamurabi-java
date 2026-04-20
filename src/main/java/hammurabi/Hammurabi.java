 //package hammurabi;  //uncomment this when Maven is fully working

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
            
            int acresToBuy = askHowManyAcresToBuy(landPrice, grain);
            if (acresToBuy > 0) {
                acres += acresToBuy;
                grain -= acresToBuy * landPrice;
            } else { 
                int acresToSell = askHowManyAcresToSell(acres);
                if (acresToSell > 0) {
                    acres -= acresToSell;
                    grain += acresToSell * landPrice;
                }
            }

            int grainForFood = askHowMuchGrainToFeedPeople(grain);
            grain -= grainForFood; 

            int acresToPlant = askHowManyAcresToPlant(acres, population, grain); 
            grain -= acresToPlant * 2; 

            int harvested = harvest(acresToPlant, acresToPlant * 2); 
            grain += harvested; 
            System.out.println("We harvested " + harvested + " bushels this year!"); 

            int ratsAte = grainEatenByRats(grain); 
            if (ratsAte > 0) { 
                grain -= ratsAte; 
                System.out.println("Rats destroyed " + ratsAte + " bushels of grain!"); 
            } else { 
                System.out.println(" No rats this year!! :) "); 
            }

            int plagueDead = plagueDeaths(population); 
            if (plagueDead > 0) { 
                population -= plagueDead; 
                System.out.println( plagueDead + " people died from plague this year!");
            } else { 
                System.out.println( " No plague this year!"); 
            }

            starved = starvationDeaths(population, grainForFood); 
            if (starved > 0) { 
                population -= starved; 
                System.out.println(starved + " people starved this year!");
            }

            if (uprising(population, starved)) { 
                System.out.println( " O great Hammurabi, the people have revolted against you!"); 
                System.out.println( " The castle has been stormed and you have been removed from office!! Off with your head!!");
                break;
            }

            if (starved == 0) { 
                newPeople = Immigrants(population, acres, grain);
                population += newPeople; 
                System.out.println( newPeople + " people entered the kingdom!");
            } else { 
                newPeople = 0; 
                System.out.println( " Nobody wanted to join your starving kingdom! :(( ");
            }

            landPrice = newCostOfLand(); 

        }

        finalSummary(population, acres, starved); 
    }

    int askHowManyAcresToSell(int acresOwned) {
        while (true) { 
            int acresToSell = getNumber("O great Hammurabi, how many acres do you want to sell?"); 

            if (acresToSell < 0) {
                System.out.println("O great Hammurabi, surely you jest!");
                continue; 
            }

            if (acresToSell > acresOwned) { 
                System.out.println("O great Hammurabi, we only own " + acresOwned + "acres!");
                continue; 
            }
            return acresToSell; 
        }
    }

    int askHowManyAcresToBuy(int price, int bushels) { 
        while (true) { 
            int acres = getNumber("Ooo great Hammurabi, how many acres do you want to buy? "); 
            if (acres < 0) {
                System.out.println("O great Hammurabi, surely you jest!");
                continue; 
            }
            if (acres * price > bushels) { 
                System.out.println("O great Hammurabi, we only have " + bushels + " bushels of grain!");
                continue;
            }
            return acres; 
        }
    }

    int askHowMuchGrainToFeedPeople(int bushels) { 
        while (true) { 
            int grain = getNumber("O great Hammurabi, how many bushels shall we feed the people?"); 

            if (grain < 0) { 
                System.out.println("O great Hammurabi, surely you jest!"); 
                continue; 
            }
            if (grain > bushels) { 
                System.out.println("O great Hammurabi, we only have " + bushels + " bushels of grain!" ); 
                continue; 
            }
            return grain; 
        }
    }

    int askHowManyAcresToPlant(int acresOwned, int population, int bushels) { 
        while (true) { 
            int acres = getNumber(" O great Hammurabi, how many acres shall we plant? ");

            if (acres < 0) { 
                System.out.println(" O great Hammurabi, surely you jest!");
                continue; 
            }

            if (acres > acresOwned) { 
                System.out.println(" O great Hammurabi, we only own " + acresOwned + " acres!"); 
                continue; 
            }

            if (acres * 2 > bushels) {
                System.out.println(" O great Hammurabi, we only have " + bushels + " bushels for seed! "); 
                continue; 
            }

            if (acres > population * 10) { 
                System.out.println(" O great Hammurabi, we only have " + population + " people to farm!"); 
                continue; 
            }
            return acres; 
        }
    }

        int harvest(int acres, int bushelsUsedAsSeed) { 
            int yield = rand.nextInt(6)+ 1; 
            int harvested = bushelsUsedAsSeed > 0 ? acres * yield : 0; 
            return harvested; 
        }

        int grainEatenByRats(int bushels) {
            if (rand.nextInt(100) < 40) {
                int percentage = rand.nextInt(21) + 10; 
                int eaten = bushels * percentage / 100; 
                return eaten;
            }

            return 0; 
        }

        int plagueDeaths(int population) { 
            if (rand.nextInt(100) < 15) { 
                int deaths = population / 2; 
                System.out.println( " A horrible plague struck the kingdom!");
                return deaths; 
            }
            return 0; 
        }

        int Immigrants( int population, int acresOwned, int grainInStorage) {
            int newPeople = (20 * acresOwned + grainInStorage) / (100 * population) + 1; 
            return newPeople; 
        }

        int starvationDeaths(int population, int bushelsFedToPeople) { 
            int peopleFed = bushelsFedToPeople / 20; 
            if (peopleFed < population) { 
                return population - peopleFed; 
            }
            return 0; 
        }

        boolean uprising(int population, int howManyPeopleStarved) { 
            return howManyPeopleStarved * 100 / population > 45; 
        }

        int newCostOfLand() { 
            return rand.nextInt(7) + 17; 
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