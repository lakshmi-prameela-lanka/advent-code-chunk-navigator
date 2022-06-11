package com.chunk.navigation;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ChunkNavigator {

    // initialize the input with given input lines
    static List<String> allLines = List.of("[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]");

    static List<Character> openingChars = List.of('(','[','{','<');
    static List<Character> closingChars = List.of(')',']','}','>');

    static Map<Character, Character> legalChunkCharMap = Map.of('(',')','[',']','{','}','<','>');

    public static void main(String[] args) {
       for(String chunk: allLines){
           System.out.println(chunk);
       }
    }

    public static int getErrorScoreOfaLine(String line){
        int errorScore = 0;
       Stack<Character> traversedChars  = new Stack<>();
        for(Character currentChar: line.toCharArray()){
            System.out.print(currentChar);
            if(isOpeningChar(currentChar)){
                traversedChars.push(currentChar);
            }   
        }
        System.out.println(traversedChars);
        return errorScore;
    }

    public static boolean isOpeningChar(Character character){
        return openingChars.contains(character);
    }
}
