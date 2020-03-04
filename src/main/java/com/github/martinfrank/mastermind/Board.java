package com.github.martinfrank.mastermind;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int maxAttempts;
    private final PinRow code;
    private final List<PinRow> attempts = new ArrayList<>();

    public Board(PinRow code, int maxAmountTrials) {
        this.code = code;
        this.maxAttempts = maxAmountTrials;
    }

    public List<PinRow> getAttempts() {
        return attempts;
    }


    public List<CheckPin> addAttempt(PinRow trial) {
        attempts.add(trial);
        trial.check(code);
        return trial.getCheck();
    }

    private List<CheckPin> getLatestResult() {
        PinRow pinRow = attempts.get(attempts.size() - 1);
        pinRow.check(code);
        return pinRow.getCheck();
    }

    public boolean isSolved() {
        return code.getWidth() == getLatestResult().stream().filter(CheckPin.BLACK::equals).count();
    }

    public int getCodeWidth() {
        return code.getWidth();
    }

    public PinRow getCode() {
        return code;
    }


    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean isAttemptLimitReached() {
        return attempts.size() < maxAttempts;
    }
}
