package com.github.martinfrank.mastermind;

import java.util.List;

public class PinChecker {

    private final List<CheckPin> reference;

    public PinChecker(List<CheckPin> reference) {
        this.reference = reference;
    }

    public boolean isSuperior(List<CheckPin> candidate){
        if( candidate.size() < reference.size()){
            return false;
        }
        if( candidate.size() == reference.size()){
            long baseBlack = reference.stream().filter(cp -> cp==CheckPin.BLACK).count();
            long candidateBlack = candidate.stream().filter(cp -> cp==CheckPin.BLACK).count();
            return candidateBlack <= baseBlack;
        }
        return false;
    }

}
