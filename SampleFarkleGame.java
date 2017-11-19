package Libby_Section03.farkleHW3;

import acm.program.ConsoleProgram;

/**
 * Created by shoop on 2/17/16.
 */
public class SampleFarkleGame extends ConsoleProgram {
    // instance variable omitted

    //initialize instance variables in the init() method

    // a possible way to set up the run method
    //
    // commented so that errors won't cause other code in shared to fail
    //
//    public void run() {
//        setFont("SanSarif-24");
//        setSize(1200, 800);
//
//        Player winner = null;
//
//        while(true){
//            println(currentPlayer.getName()+"'s turn:");
//            takeTurn(currentPlayer);
//            if (winner == null && testForWin(currentPlayer)) {
//                winner = currentPlayer;
//                println("\t"+currentPlayer.toString()+" "+winner.getName()+" could be the winner. You have one last chance turn.");
//            }
//            advanceTurn();
//            println();
//
//            if (winner != null && currentPlayer.equals(winner)) {
//                // We have a winner and have now given everyone else a turn
//                winner = findHighestScorer();
//                println("\t"+winner.toString()+ " " +winner.getName() +" wins the game!");
//                break;
//            }
//        }
//    }

}
