package com.example.project;

public class Sprite {
    private int x; //instance variables
    private int y;

    public Sprite(int x, int y) { //initalize instance variables
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x; //return x coordinate
    }
    public int getY(){
        return y; //return y coordinate
    }

    public void setX(int newX){
        x = newX; //set new x coordinate based on parameter
    }
    public void setY(int newY){
        y = newY; //set new y coordinate based on parameter
    }

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
    int row = size - y - 1;
    int col = x;
        return "[" + row + "][" + col + "]";
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
