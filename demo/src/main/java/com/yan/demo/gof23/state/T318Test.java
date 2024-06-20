package com.yan.demo.gof23.state;

public class T318Test {
    public static void main(String[] args) {
        String input = "This is a text with *values to remove* and more text.";
        String output = input.replaceAll("\\*.+?\\*", "");
        System.out.println(output); //
    }

}
