import java.util.Scanner;
import java.util.Random;

public class BattleShip {

    public static int[][] map = new int[10][10];
    public static Scanner input = new Scanner(System.in);
    public static int userShips = 0;
    public static int compShips = 0;


    public static void main(String[] args){
        intro();
        promptUser();
        computerShips();
        battle();
    }

    public static void printOceanMap(){
        System.out.println();
        System.out.println("   0123456789");
        for(int i = 0; i < 10; i++){
            System.out.print(i + " |");
            for (int j = 0; j < 10; j++){
                if(map[i][j] == 1){
                    System.out.print("@"); // User's ship
                } else if(map[i][j] == 3 || map[i][j] == 7) {
                    System.out.print("-"); // Guessed coordinates
                } else if(map[i][j] == 4) {
                    System.out.print("x"); // User or computer sinks user's ship
                } else if(map[i][j] == 5) {
                    System.out.print("!"); // User or computer sinks computer's ship
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println("| " + i);
        }
        System.out.println("   0123456789");
        System.out.println();
        System.out.println("Your ships: " + userShips + " | Computer ships: " + compShips);
        System.out.println();
    }

    public static void intro(){
        System.out.println("*** Welcome to the Batte Ships Game ***");
        System.out.println("Right now, the sea is empty.");
        printOceanMap();
    }

    public static void promptUser(){
        for(int i = 0; i < 5; i++){
            System.out.print("Enter X coordinate for your " + i + ". ship: ");
            int x = input.nextInt();
            System.out.print("Enter Y coordinate for your " + i + ". ship: ");
            int y = input.nextInt();
            if(x > 9 || x < 0 || y > 9 || y < 0) {
                i--;
            } else if(map[x][y] == 1) {
                i--;
            } else {
                map[x][y] = 1;
                userShips++;
            }
        }
        printOceanMap();
    }

    public static void computerShips(){
        System.out.println("Computer is deploying ships.");
        Random random = new Random();
        for(int i = 0; i < 5; i++){
            int x = random.nextInt(11);
            int y = random.nextInt(11);
            if(x > 9 || x < 0 || y > 9 || y < 0){
                i--;
            } else if(map[x][y] == 1) {
                i--;
            } else {
                map[x][y] = 2;
                System.out.println(i + ". ship DEPLOYED.");
                compShips++;
            }
        }
        System.out.println();
    }

    public static void battle(){
        while(compShips != 0 && userShips != 0){
            usersTurn();
            compsTurn();
        }

        if(compShips == 0) {
            System.out.print("Hooray! You won the battle :)");
        } else {
            System.out.print("Sorry! You lost :(");
        }

    }

    public static void usersTurn(){
        System.out.println("YOUR TURN");
        int x = 10;
        int y = 10;
        while(x > 9 || x < 0 || y > 9 || y < 0 || map[x][y] == 3 || map[x][y] == 7) {
            System.out.println("Enter X coordinate: ");
            x = input.nextInt();
            System.out.println("Enter Y coordinate: ");
            y = input.nextInt();
        }

        System.out.println();

        if(map[x][y] == 1) {
            System.out.print("Oh no, you sunk your own ship :(");
            map[x][y] = 4;
            userShips--;
        } else if(map[x][y] == 2) {
            System.out.print("Boom! You sunk the ship!");
            map[x][y] = 5;
            compShips--;
        } else if(map[x][y] == 6) {
            System.out.print("Sorry, you missed");
            map[x][y] = 7;
        } else {
            System.out.print("Sorry, you missed");
            map[x][y] = 3;
        }
        System.out.println();
        printOceanMap();
    }

    public static void compsTurn(){
        System.out.println("COMPUTER'S TURN");
        Random random = new Random();
        int x = random.nextInt(11);
        int y = random.nextInt(11);
        while(x > 9 || x < 0 || y > 9 || y < 0 || map[x][y] == 6 || map[x][y] == 7) {
            x = random.nextInt(11);
            y = random.nextInt(11);
        }

        if(map[x][y] == 1) {
            System.out.print("The Computer sunk one of your ships!");
            map[x][y] = 4;
            userShips--;
        } else if(map[x][y] == 2) {
            System.out.print("The Computer sunk one of its own ships");
            map[x][y] = 5;
            compShips--;
        } else if(map[x][y] == 3) {
            System.out.print("Computer missed");
            map[x][y] = 7;
        } else {
            System.out.print("Computer missed");
            map[x][y] = 6;
        }
        System.out.println();
        printOceanMap();
    }

}
