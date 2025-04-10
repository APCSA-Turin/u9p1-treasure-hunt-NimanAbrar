package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size; //initialize size and grid
        Sprite[][] grid = new Sprite[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Dot qdot = new Dot(i, j);
                grid[i][j] = qdot; //stores dot in every element of grid initially
            }
        }
        this.grid = grid;
    }

 
    public Sprite[][] getGrid(){ //return grid
        return grid;
    }

    public int getSize() { //return size
        return size;
    }

    public void placeSprite(Sprite s){ //place sprite in new spot
        int row = size - s.getY() - 1;
        int col = s.getX();
        if (col < 0 || row < 0 || row >= size || col >= size) {
            return;
        }
        grid[row][col] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        placeSprite(s);
        if (direction.equals("w")) {
            Dot qdot = new Dot(s.getX(), s.getY() - 1); // if sprite moves up then row is subtracted by 1
            placeSprite(qdot);
        } else if (direction.equals("a")) {
            Dot qdot = new Dot(s.getX() + 1,s.getY()); //if sprite moves to the left then column is added by 1
            placeSprite(qdot);
        } else if (direction.equals("s")) {
            Dot qdot = new Dot(s.getX(), s.getY() +1); //if sprite moves to down then row is added by 1
            placeSprite(qdot);
        } else if (direction.equals("d")) {
            Dot qdot = new Dot(s.getX() - 1, s.getY()); //if sprite moves to the right then column is subtracted by 1
            placeSprite(qdot);
        } else {
            return;
        }
    }


    public void display() { //print out the current grid to the screen 
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) { //iterate through every element of grid
                if (grid[row][col] instanceof Treasure && !(grid[row][col] instanceof Trophy)) { //checks if element = treasure
                    System.out.print("💎");
                } else if (grid[row][col] instanceof Enemy) { //checks if element = enemy
                    System.out.print("☄️");
                } else if (grid[row][col] instanceof Trophy) { //checks if element = trophy
                    System.out.print("🌍");
                } else if (grid[row][col] instanceof Player) { //checks if element = player
                    System.out.print("🛸");
                } else {
                    System.out.print("🪐"); //prints a planet if it's a dot
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("🌵"); //prints a cactus for all elements
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("GAME OVER. NO NEW PLANET FOUND. HUMANITY WILL FADE INTO EXTINCTION. GOODBYE");
    }

    public void win(){ //use this method to display a win 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("🌎"); //prints a habitable planet for all elements
            }
            System.out.println();
        }
        System.out.println("NEW PLANET FOUND. HUMANITY PERSISTS. HOORAY.");
    }

}