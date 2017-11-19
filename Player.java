package edu.macalester.comp124.hw3;

import acm.program.ConsoleProgram;

/**
 */
public class Player {

    int score;
    String name;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addToScore(int val){
        this.score = this.score + val;
    }
}
