package edu.macalester.comp124.hw3;

import acm.program.ConsoleProgram;

/**
 * Created by bjackson on 9/24/2015
 * The main function for the program.
 */
public class FarkleProgram extends ConsoleProgram {

    /**
     * The main function for the entire program.
     *
     * You should create other helper methods inside FarkleProgram.
     * Some of them should create an instances of the Player and Die classes.
     * Make sure to test your new methods in the TestFarkleProgram junit class.
     *
     */

    public static final int WINNING_SCORE = 10000;
    private Player p0;
    private Player p1;
    private Player p2;
    private Player currentPlayer;
    private Die d0;
    private Die d1;
    private Die d2;
    private Die d3;
    private Die d4;
    private Die d5;

    public void init(){
        p0 = new Player("p0",0);
        p1 = new Player("p1",0);
        p2 = new Player("p2",0);
        d0 = new Die(1,false);
        d1 = new Die(1,false);
        d2 = new Die(1,false);
        d3 = new Die(1,false);
        d4 = new Die(1,false);
        d5 = new Die(1,false);
    }

    public void run() {
        p0.setName(readLine("The name of player 1:"));
        p1.setName(readLine("The name of player 2:"));
        p2.setName(readLine("The name of player 3:"));
        currentPlayer = p2;

        while(true) {
            nextPlayer();
            println(currentPlayer.getName()+"'s turn:");

            takeTurn();

            if(testForWin(currentPlayer)){
                Player winner;
                winner = currentPlayer;
                println(winner.getName()+" is winning. The rest two people have one more turn.");
                resetDice();
                nextPlayer();
                println(currentPlayer.getName()+"'s turn:");
                takeTurn();
                resetDice();
                nextPlayer();
                println(currentPlayer.getName()+"'s turn:");
                takeTurn();
                winner = findHighestScorer();
                println(winner.getName()+" has won the game and the score is "+winner.getScore()+".");
                break;
            }

            resetDice();

        }


    }

    public Player findHighestScorer(){
        if(p0.getScore()>p1.getScore()){
            if(p0.getScore()>p2.getScore()){
                return p0;
            } else {
                return p2;
            }
        } else {
            if (p1.getScore()>p2.getScore()){
                return p1;
            } else {
                return p2;
            }
        }
    }





    private void takeTurn(){
        boolean keepRolling = true;
        boolean scoreAllSix = false;
        boolean s0;
        boolean s1;
        boolean s2;
        boolean s3;
        boolean s4;
        boolean s5;
        int addScore = 0;
        while (keepRolling||scoreAllSix){
            scoreAllSix = false;
            rollDice();
            if (testBust()){
                printDice();
                println("Sorry, you busted!");
                addScore = 0;
                break;}
            printDice();
            s0 = d0.isSetAside();
            s1 = d1.isSetAside();
            s2 = d2.isSetAside();
            s3 = d3.isSetAside();
            s4 = d4.isSetAside();
            s5 = d5.isSetAside();
            String asideValue = setDiceAside();
            while(!allScore(asideValue)){
                println("One or some of the dices you put aside can not score.");
                d0.isSetAside = s0;
                d1.isSetAside = s1;
                d2.isSetAside = s2;
                d3.isSetAside = s3;
                d4.isSetAside = s4;
                d5.isSetAside = s5;
                printDice();
                asideValue = setDiceAside();
            }
            while(asideValue.length()==0){
                println("You must set aside at least one die.");
                printDice();
                asideValue = setDiceAside();
            }
            addScore = addScore + calculateScore(asideValue);
            print("Current score is "+addScore+".");
            if (d0.isSetAside&&d1.isSetAside&&d2.isSetAside&&d3.isSetAside&&d4.isSetAside&&d5.isSetAside){
                println("You have a hot dice. Keep rolling.");
                scoreAllSix = true;
                keepRolling = false;
                resetDice();
            } else {
                keepRolling = readBoolean("Continue rolling (true or false)?");
            }
        }
        currentPlayer.addToScore(addScore);
        println(currentPlayer.getName()+" has "+currentPlayer.getScore()+" points. Next player's turn!"+"\n");
    }

    public boolean allScore(String diceValue){
        int num2 = 0;
        int num3 = 0;
        int num4 = 0;
        int num6 = 0;
        for(int i = 0 ; i < diceValue.length();i++){
            if ((""+diceValue.charAt(i)).equals("2")){
                num2++;
            } else if ((""+diceValue.charAt(i)).equals("3")){
                num3++;
            } else if ((""+diceValue.charAt(i)).equals("4")){
                num4++;
            } else if ((""+diceValue.charAt(i)).equals("6")){
                num6++;
            }
        }
        return ((num2==3||num2==6||num2==0)&&(num3==3||num3==6||num3==0)&&(num4==3||num4==6||num4==0)&&(num6==3||num6==6||num6==0));

    }



    private void resetDice(){
        d0.isSetAside = false;
        d1.isSetAside = false;
        d2.isSetAside = false;
        d3.isSetAside = false;
        d4.isSetAside = false;
        d5.isSetAside = false;
    }

    private void rollDice(){
        d0.roll();
        d1.roll();
        d2.roll();
        d3.roll();
        d4.roll();
        d5.roll();
    }

    public boolean testBust(){
        String diceValue ="";
        if (!d0.isSetAside()){
            diceValue = diceValue + d0.getFaceValue();
        }
        if (!d1.isSetAside()){
            diceValue = diceValue + d1.getFaceValue();
        }
        if (!d2.isSetAside()){
            diceValue = diceValue + d2.getFaceValue();
        }
        if (!d3.isSetAside()){
            diceValue = diceValue + d3.getFaceValue();
        }
        if (!d4.isSetAside()){
            diceValue = diceValue + d4.getFaceValue();
        }
        if (!d5.isSetAside()){
            diceValue = diceValue + d5.getFaceValue();
        }
        return calculateScore(diceValue)==0;

    }

    public int calculateScore(String diceValues){
        int score = 0;

        score = score + checkThreeOfAKind(diceValues, 1)*1000;

        score = score + checkThreeOfAKind(diceValues, 2)*200;

        score = score + checkThreeOfAKind(diceValues, 3)*300;

        score = score + checkThreeOfAKind(diceValues, 4)*400;

        score = score + checkThreeOfAKind(diceValues, 5)*500;

        score = score + checkThreeOfAKind(diceValues, 6)*600;

        if (countOccurrences(diceValues, 1)>3&&countOccurrences(diceValues, 1)<6){
            score = score + (countOccurrences(diceValues, 1)-3)*100;
        } else if (countOccurrences(diceValues, 1)<3){
            score = score + countOccurrences(diceValues, 1)*100;
        }

        if ((countOccurrences(diceValues, 5)>3&&countOccurrences(diceValues, 5)<6)){
            score = score + (countOccurrences(diceValues, 5)-3)*50;
        } else if (countOccurrences(diceValues, 5)<3){
            score = score + countOccurrences(diceValues, 5)*50;
        }

        return score;
    }

    public int checkThreeOfAKind(String diceValues, int num){
        if (countOccurrences(diceValues, num)>=3&&countOccurrences(diceValues, num)<6){
            return 1;
        } else if (countOccurrences(diceValues, num)==6){
            return 2;
        } else {
            return 0;
        }
    }

    public int countOccurrences(String combinedRolls, int testVal){
        return combinedRolls.length() - combinedRolls.replace(""+testVal, "").length();
    }

    private void printDice(){
        if (d0.isSetAside()){
            print("D0: "+d0.getFaceValue()+" Set Aside,     ");
        } else {
            print("D0: "+d0.getFaceValue()+",     ");
        }
        if (d1.isSetAside()){
            print("D1: "+d1.getFaceValue()+" Set Aside,     ");
        } else {
            print("D1: "+d1.getFaceValue()+",     ");
        }
        if (d2.isSetAside()){
            print("D2: "+d2.getFaceValue()+" Set Aside,     ");
        } else {
            print("D2: "+d2.getFaceValue()+",     ");
        }
        if (d3.isSetAside()){
            print("D3: "+d3.getFaceValue()+" Set Aside,     ");
        } else {
            print("D3: "+d3.getFaceValue()+",     ");
        }
        if (d4.isSetAside()){
            print("D4: "+d4.getFaceValue()+" Set Aside,     ");
        } else {
            print("D4: "+d4.getFaceValue()+",     ");
        }
        if (d5.isSetAside()){
            print("D5: "+d5.getFaceValue()+" Set Aside,     "+"\n");
        } else {
            print("D5: "+d5.getFaceValue()+",     "+"\n");
        }

    }



    private String setDiceAside(){
        String valueAside = "";
        if (!d0.isSetAside){
            if (readBoolean("Would you like to set D0 aside? (true or false)")){
                d0.setIsSetAside();
                valueAside=valueAside + d0.getFaceValue();
            }
        }
        if (!d1.isSetAside){
            if (readBoolean("Would you like to set D1 aside? (true or false)")){
                d1.setIsSetAside();
                valueAside=valueAside + d1.getFaceValue();
            }
        }
        if (!d2.isSetAside){
            if (readBoolean("Would you like to set D2 aside? (true or false)")){
                d2.setIsSetAside();
                valueAside=valueAside + d2.getFaceValue();
            }
        }
        if (!d3.isSetAside){
            if (readBoolean("Would you like to set D3 aside? (true or false)")){
                d3.setIsSetAside();
                valueAside=valueAside + d3.getFaceValue();
            }
        }
        if (!d4.isSetAside){
            if (readBoolean("Would you like to set D4 aside? (true or false)")){
                d4.setIsSetAside();
                valueAside=valueAside + d4.getFaceValue();
            }
        }
        if (!d5.isSetAside){
            if (readBoolean("Would you like to set D5 aside? (true or false)")){
                d5.setIsSetAside();
                valueAside=valueAside + d5.getFaceValue();
            }
        }
        return valueAside;
    }

    public boolean testForWin(Player p){
        return (p.getScore() >= WINNING_SCORE);
    }

    private void nextPlayer(){
        if (currentPlayer.getName().equals(p0.getName())){
           currentPlayer = p1;
        } else if (currentPlayer.getName().equals(p1.getName())){
            currentPlayer = p2;
        } else {
            currentPlayer = p0;
        }
    }


}
