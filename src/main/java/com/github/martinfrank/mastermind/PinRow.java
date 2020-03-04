package com.github.martinfrank.mastermind;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class PinRow {

    private final int width;

    private final ColorPin[] colors;
    private List<CheckPin> checkers = new ArrayList<>();

    public PinRow(int[] indices, Set<ColorPin> givenColors) {
        List<ColorPin> sampleColors = new ArrayList<>(givenColors);
        width = indices.length;
        colors = new ColorPin[width];
        IntStream.range(0, width).forEach(c -> colors[c] = sampleColors.get(indices[c]));
    }

    public static PinRow randomPinRow(int codeWidth, Set<ColorPin> colors) {
        int[] indices = new int[codeWidth];
        int amountColors = colors.size();
        IntStream.range(0, codeWidth).forEach(c -> indices[c] = new Random().nextInt(amountColors));
        return new PinRow(indices, colors);
    }


    public List<CheckPin> check(PinRow code) {
        checkers.clear();
        List<ColorPin> myRemaining = new ArrayList<>();
        List<ColorPin> codeRemaining = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            ColorPin current = colors[i];
            ColorPin expected = code.colors[i];
            if (current == expected) {
                checkers.add(CheckPin.BLACK);
            } else {
                myRemaining.add(current);
                codeRemaining.add(expected);
            }
        }

        for (ColorPin mine : myRemaining) {
            for (ColorPin candidate : codeRemaining) {
                if (mine.equals(candidate)) {
                    codeRemaining.remove(candidate);
                    checkers.add(CheckPin.WHITE);
                    break;
                }
            }
        }
        return checkers;
    }


    @Override
    public String toString(){
        return Arrays.toString(colors);
    }

    public List<CheckPin> getCheck() {
        return checkers;
    }

    public List<ColorPin> getColorPins() {
        return Arrays.asList(colors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PinRow pinRow = (PinRow) o;
        return Arrays.equals(colors, pinRow.colors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(colors);
    }

    public int getWidth() {
        return width;
    }

    public static List<PinRow> getAllPossibleCombinations(Set<ColorPin> colors, int width) {
        int amountColors = colors.size();
        int size = (int)Math.pow( amountColors, width);

        List<PinRow> result = new ArrayList<>();
        for (int i = 0; i < size; i ++){
            BigInteger bi = BigInteger.valueOf(i);
            int[] indices = createIndice(leadingZeros(bi.toString(amountColors), width) );
            result.add(new PinRow(indices, colors));
        }
        return result;
    }

    private static String leadingZeros(String numberString, int size) {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0,size-numberString.length()).forEach(i -> sb.append("0"));
        sb.append(numberString);
        return sb.toString();
    }

    private static int[] createIndice(String numberString) {
        int length = numberString.length();
        int[] indice = new int[length];
        IntStream.range(0,length).forEach(i -> indice[i] = createIndex(numberString.charAt(i)));
        return indice;
    }

    private static int createIndex(char charAt) {
        if (charAt >= '0' && charAt <= '9'){
            return charAt-'0';
        }else{
            return charAt-'a'+10;
        }
    }
}
