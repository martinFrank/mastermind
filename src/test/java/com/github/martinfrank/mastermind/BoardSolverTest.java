package com.github.martinfrank.mastermind;

import org.junit.Test;

import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class BoardSolverTest {

    @Test
    public void testSolving(){

        final int codeWidth = 6;
        final Set<ColorPin> colors = ColorPin.randomSet(6);
        final int maxAmountTrials = 7;

        int[] indices = new int[codeWidth];
        int amountColors = colors.size();

        IntStream.range(0, codeWidth).forEach(c -> indices[c] = new Random().nextInt(amountColors));
        PinRow code = new PinRow(indices, colors);

        Board board = new Board(code, maxAmountTrials);
        BoardSolver solver = new BoardSolver(board, colors);
        long start = System.currentTimeMillis();
        solver.solve();
        long diff = System.currentTimeMillis()-start;
        System.out.println("attempt to solve took "+diff+"ms...");

        BoardPrinter.printBoard(board);

    }
}
