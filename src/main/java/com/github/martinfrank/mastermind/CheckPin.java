package com.github.martinfrank.mastermind;

public enum CheckPin {

    WHITE,  //proper color, wrong location
    BLACK; //proper color, proper location

    public String literal(){
        switch (this){
            case BLACK: return "b";
            case WHITE: return "w";
            default:return "?";
        }
    }

}
