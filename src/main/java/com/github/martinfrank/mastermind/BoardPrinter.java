package com.github.martinfrank.mastermind;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class BoardPrinter {

    public static void printBoard(Board board){
        final int columnSize = ColorPin.maxLength();
        final int codeWidth = board.getCodeWidth();
        final PinRow code = board.getCode();

        String separator = createSeparator(columnSize+2, codeWidth);
        String bigSeparator = createSeparator(columnSize+2, codeWidth, '=');
        System.out.println((board.isSolved()?"":"not ")+"solved in "+board.getAttempts().size()+" turns...");
        System.out.println(separator);
        System.out.println(printRow(code, columnSize)+emptyPins(codeWidth, columnSize));
        System.out.println(bigSeparator);
        for(int i = board.getMaxAttempts()-1; i >= 0; i --){
            if(i < board.getAttempts().size() ){
                PinRow trial = board.getAttempts().get(i);
                System.out.println(printRow(trial, columnSize)+printCheck(trial.getCheck(), codeWidth, columnSize));
            }else{
                System.out.println(emptyPins(codeWidth, columnSize)+emptyPins(codeWidth, columnSize));
            }
            System.out.println(separator);
        }

    }

    private static String emptyPins(int codeWidth, int columnSize) {
        StringBuilder sb = new StringBuilder("|");
        IntStream.range(0,codeWidth).forEach(i -> append(sb, " ", centerString("", columnSize), " |"));
        return sb.toString();
    }

    private static String createSeparator(int columsSize, int codeWidth) {
        return createSeparator(columsSize, codeWidth, '-');
    }

    private static String createSeparator(int columsSize, int codeWidth, char c) {
        StringBuilder sb = new StringBuilder("+");
        String stroke = createStroke(columsSize, c);
        IntStream.range(0,codeWidth).forEach(i -> append(sb,stroke,"+"));
        sb.append("+");
        IntStream.range(0,codeWidth).forEach(i -> append(sb,stroke,"+"));
        return sb.toString();
    }

    private static String createStroke(int strokeLength, char c) {
        StringBuilder sb = new StringBuilder("");
        IntStream.range(0,strokeLength).forEach(i -> sb.append(c));
        return sb.toString();
    }

    private static void append(StringBuilder sb, String... values){
        Arrays.stream(values).forEach(sb::append);
    }

    private static String printCheck(List<CheckPin> checkers, int codeWidth, int columnLength) {
        StringBuilder sb = new StringBuilder("|");
        for(int i = 0; i < codeWidth; i++){
            String value = i < checkers.size()?checkers.get(i).toString():" ";
            append(sb, " ",centerString(value, columnLength)," |");
        }
        return sb.toString();
    }

    private static String printRow(PinRow row, int columnLength) {
        StringBuilder sb = new StringBuilder("|");
        row.getColorPins().forEach(cp -> append(sb, " ",centerString(cp.toString(), columnLength)," |"));
        return sb.toString();
    }

    public static String centerString (String string, int width) {
        return String.format("%-" + width  + "s", String.format("%" + (string.length() + (width - string.length()) / 2) + "s", string));
    }
}
