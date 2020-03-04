package com.github.martinfrank.mastermind;

import java.util.*;

public enum ColorPin {

    RED, GREEN, BLUE, YELLOW, PURPLE, WHITE, ORANGE, GRAY, BLACK, CYAN;

    public static Set<ColorPin> randomSet(int amountColors) {
        List<ColorPin> all = Arrays.asList(ColorPin.values());
        Collections.shuffle(all);
        return new HashSet<>(all.subList(0, amountColors));
    }

    public static int maxLength(){
        return Arrays.stream(values()).mapToInt(c -> c.toString().length()).max().orElse(0);
    }
}
