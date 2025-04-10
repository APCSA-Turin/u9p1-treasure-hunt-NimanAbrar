package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite  { //instance variables
    private int treasureCount;
    private int numLives;
    private boolean win = false;

    public Player (int x, int y) { //set treasureCount = 0 and numLives = 2
        super(x, y);
        treasureCount = 0;
        numLives = 2;
    }
    public void setLives(int newLives) {
        numLives = newLives;
    }
    public int getTreasureCount(){ //returj treasure count
        return treasureCount;
    }
    public int getLives(){ //return lives
        return numLives;
    }
    public boolean getWin(){ //return win
        return win;
    }

    public void decrementLives() { //decrements lives
        numLives--;
    }
    public void incrementTreasures() { //increments treasure count
        treasureCount++;
    }

  
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) {
            setY(getY()+1); //y coordinate is incremented given input of "w"
        } else if (direction.equals("a")) { //x coordinate is decremented given input of "a"
            setX(getX()-1);;
        } else if (direction.equals("s")) { //y coordinate is decremented given input of "s"
            setY(getY()-1);;
        } else if (direction.equals("d")) { //x coordinate is incremented given input of "d"
            setX(getX()+1);;
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Enemy) { //if player converges with enemy, lives is decremented.
            numLives--;
        } else if (obj instanceof Treasure && !(obj instanceof Trophy)) { //if player converges with treasure, treasure count is incremented
            treasureCount++;
        } else if (obj instanceof Trophy) { //checks if player can win given convergence
            if (treasureCount == numTreasures) { //if sufficient amount of treasure, player wins
                win = true;
            }
        } else { //do nothing if no special sprite
            return;
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w") && getY() + 1 < size) { //checks if sprite can move beyond the given input
            return true; 
        } else if (direction.equals("a") && getX() - 1 >= 0) {
            return true;
        } else if (direction.equals("s") && getY()- 1 >= 0) {
            return true;
        } else if (direction.equals("d") && getX() + 1 < size) {
            return true;
        }
        return false;
    }

    public String getCoords(){ //returns "Player:"+coordinates
        return "Player:" + super.getCoords();
    }

    public boolean matchCoords(int x, int y) {
        if (getX() == x && getY() == y) { //if x = x2, and y = y2 return true
            return true;
        }
        return false;
    }

    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Player:" + super.getRowCol(size); 
    }
}



