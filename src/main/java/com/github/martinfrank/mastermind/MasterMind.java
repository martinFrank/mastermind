package com.github.martinfrank.mastermind;

import java.util.Set;

public class MasterMind {


    public static void main(String[] args){
        final int codeWidth = 4;
        final Set<ColorPin> colors = ColorPin.randomSet(6);
        final int maxAmountTrials = 20;
        final PinRow code = PinRow.randomPinRow(codeWidth, colors);
        System.out.println("code: "+code);
        Board board = new Board(code, maxAmountTrials);
        BoardSolver solver = new BoardSolver(board, colors);
        solver.solve();
        BoardPrinter.printBoard(board);
    }
}
