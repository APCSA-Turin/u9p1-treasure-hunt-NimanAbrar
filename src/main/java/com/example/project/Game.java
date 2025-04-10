package com.example.project;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game{ //instance variables
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 
    private String difficulty;

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size; //initialize size
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { //write your game logic here
        Scanner scanner = new Scanner(System.in); //initialize scanner
        boolean toPlay = true; //determines if game is still in session
        while(toPlay) { //condition
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display(); //output the screen to user
            System.out.println("Number of Lives: " + player.getLives()); //game stats
            System.out.println("Player Coordinate: " + player.getCoords());
            System.out.println("Player Coordinate: " + player.getRowCol(size));
            System.out.println("Number of treasures needed: " + treasures.length);
            System.out.println("Number of treasures acquired: " + player.getTreasureCount());
            System.out.print("Enter direction (w/a/s/d): "); //asks for user input
            String direction = scanner.nextLine();
                if (direction.equals("w") || direction.equals("a") || //checks if input is valid
                direction.equals("s") || direction.equals("d")) { 
                 if(player.isValid(size, direction)) { //checks if input is within boundaries
                     int newX = player.getX(); //stores initial coordinate before movement
                     int newY = player.getY();
                     if(direction.equals("w")) { //moves player according to input
                         newY++;
                     }
                     if(direction.equals("a")) {
                         newX--;
                     }
                     if(direction.equals("s")) { 
                         newY--;
                     }
                     if(direction.equals("d")) {
                         newX++;
                     } 
                     Sprite sprite = grid.getGrid()[size-1-newY][newX]; //stores sprite located at coordinate
                     player.interact(size, direction, player.getTreasureCount(), sprite); //interact with sprite
                         player.move(direction); //moves player to new coordinate
                     grid.placeSprite(player, direction); //places sprite on grid
                     if (player.getWin() || player.getLives() == 0) { //terminates loop based on if player wins or loses
                         toPlay = false;
                     }
                     }
                 } 
            }   
            clearScreen(); //clear screen for cleanliness
        if (player.getWin()) { //output win sequence
            grid.win();
        }
        if(player.getLives() == 0) { //output lose sequence
            grid.gameover();
        }
    }

    public void setDifficulty(String newDifficulty) {
        difficulty = newDifficulty;
    }
    public String getDifficulty() {
        return difficulty;
    }

    public void initialize(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which difficulty would you like to embark in? (easy/medium/hard)");
        String difficulty = scanner.nextLine();
        if (difficulty.equals("easy")) {
            size = 10;
            Grid grid = new Grid(size); //initialize
        this.grid = grid;
        player = new Player(0, 0); //initial locations of all sprites
        enemies = new Enemy[2];
        trophy = new Trophy(0, 9);
        enemies[0] = new Enemy(5, 5);
        enemies[1] = new Enemy(7, 8);
        treasures = new Treasure[2];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(6, 7);
        grid.placeSprite(player);
        for (int i = 0; i < enemies.length; i++) {
            grid.placeSprite(enemies[i]);
        }
        for (int i = 0; i < treasures.length; i++) {
            grid.placeSprite(treasures[i]);
        }
        grid.placeSprite(trophy);
        } else if (difficulty.equals("medium")) {
            size = 12;
            Grid grid = new Grid(size);
            this.grid = grid;
            player = new Player(0, 0); //initial locations of all sprites
        enemies = new Enemy[3];
        trophy = new Trophy(0, 11);
        enemies[0] = new Enemy(5, 8);
        enemies[1] = new Enemy(10, 5);
        enemies[2] = new Enemy(4, 3);
        treasures = new Treasure[3];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(1, 7);
        treasures[2] = new Treasure(11,11);
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        for (int i = 0; i < enemies.length; i++) {
            grid.placeSprite(enemies[i]);
        }
        for (int i = 0; i < treasures.length; i++) {
            grid.placeSprite(treasures[i]);
        }
        } else if (difficulty.equals("hard")) {
            size = 15;
            Grid grid = new Grid(size);
            this.grid = grid;
            player = new Player(7, 7); //initial locations of all sprites
            player.setLives(1);
        enemies = new Enemy[8];
        trophy = new Trophy(0, 14);
        enemies[0] = new Enemy(13, 8);
        enemies[1] = new Enemy(10, 5);
        enemies[2] = new Enemy(4, 3);
        enemies[3] = new Enemy(2, 7);
        enemies[4] = new Enemy(9, 14);
        enemies[5] = new Enemy(13,13);
        enemies[6] = new Enemy(5, 2);
        enemies[7] = new Enemy(8, 1);
        treasures = new Treasure[5];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(1, 7);
        treasures[2] = new Treasure(11,11);
        treasures[3] = new Treasure(0, 0);
        treasures[4] = new Treasure(9, 13);
        grid.placeSprite(trophy);
        grid.placeSprite(player);
        for (int i = 0; i < enemies.length; i++) {
            grid.placeSprite(enemies[i]);
        }
        for (int i = 0; i < treasures.length; i++) {
            grid.placeSprite(treasures[i]);
        }
        }
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
    }
    public int getSize() {
        return size;
    }
   


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean keepPlaying = true;
        while (keepPlaying) {
            Game pistons = new Game(10);
        System.out.println();
        System.out.println("Do you wish to keep playing? (yes/no)");
        String yesOrNo = scanner.nextLine();
        if (yesOrNo.equals("no")) {
        keepPlaying = false;
        }
        }
}
}

