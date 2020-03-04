package com.github.martinfrank.mastermind;

import java.util.*;
import java.util.stream.Collectors;

public class BoardSolver {

    private final Board board;
    private final Set<ColorPin> colors;
    private final Random random;

    public BoardSolver(Board board, Set<ColorPin> colors) {
        this.board = board;
        this.colors = colors;
        random = new Random();
    }

    public void solve() {
        int codeWidth = board.getCodeWidth();
        List<PinRow> combinations = PinRow.getAllPossibleCombinations(colors, codeWidth);
        do {
            PinRow attempt = removeRandom(combinations);
            PinChecker pinChecker = new PinChecker(board.addAttempt(attempt));
            combinations = combinations.stream().filter(pr -> pinChecker.isSuperior(pr.check(attempt))).collect(Collectors.toList());
        } while (!board.isSolved() && board.isAttemptLimitReached());

    }

    private PinRow removeRandom(List<PinRow> combinations) {
        return combinations.remove(random.nextInt(combinations.size()));
    }

}
