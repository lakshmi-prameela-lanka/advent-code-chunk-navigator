package com.chunk.navigation;

import java.util.List;

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

    public static void main(String[] args) {
       for(String chunk: allLines){
           System.out.println(chunk);
       }
    }
}
