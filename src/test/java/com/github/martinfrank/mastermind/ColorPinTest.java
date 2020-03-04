package com.github.martinfrank.mastermind;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.IntStream;

public class ColorPinTest {

    @Test
    public void testPermutation() {
        int amountColors = 6;
        int codeWidth = 4;
        final Set<ColorPin> colors = ColorPin.randomSet(amountColors);
        List<PinRow> combinations = PinRow.getAllPossibleCombinations(colors, codeWidth);
        Assert.assertEquals(Math.pow(amountColors, codeWidth), combinations.size(), 0.1);
    }

    @Test
    public void testCheck4_6() {
        final Set<ColorPin> colors = ColorPin.randomSet(6);
        int[] indicesCode = new int[]{0, 1, 0, 1};
        PinRow code = new PinRow(indicesCode, colors);
        int[] indicesTrial = new int[]{0, 1, 0, 1};
        PinRow trial = new PinRow(indicesTrial, colors);
        checkResult(createCheck(0,4), trial.check(code));
    }

    @Test
    public void testCheck5_6() {
        final Set<ColorPin> colors = ColorPin.randomSet(6);
        int[] indicesCode = new int[]{0, 1, 0, 1, 0};
        PinRow code = new PinRow(indicesCode, colors);
        int[] indicesTrial = new int[]{0, 1, 0, 1, 0};
        PinRow trial = new PinRow(indicesTrial, colors);
        checkResult(createCheck(0, 5), trial.check(code));
    }

    @Test
    public void testCheck6_6() {
        final Set<ColorPin> colors = ColorPin.randomSet(6);
        int[] indicesCode = new int[]{0, 1, 0, 1, 0, 1};
        PinRow code = new PinRow(indicesCode, colors);
        int[] indicesTrial = new int[]{0, 1, 0, 1, 0, 1};
        PinRow trial = new PinRow(indicesTrial, colors);
        checkResult(createCheck(0,6), trial.check(code));
    }


    private List<CheckPin> createCheck(int amountWhite, int amountBlack) {
        List<CheckPin> check = new ArrayList<>();
        IntStream.range(0,amountWhite).forEach(i -> check.add(CheckPin.WHITE));
        IntStream.range(0,amountBlack).forEach(i -> check.add(CheckPin.BLACK));
        return check;
    }


    private void checkResult(List<CheckPin> valid, List<CheckPin> candidate) {
        Assert.assertEquals(valid.size() ,candidate.size());
        Assert.assertEquals(valid.stream().filter(CheckPin.BLACK::equals).count(), candidate.stream().filter(CheckPin.BLACK::equals).count());
        Assert.assertEquals(valid.stream().filter(CheckPin.WHITE::equals).count(), candidate.stream().filter(CheckPin.WHITE::equals).count());
    }
}

