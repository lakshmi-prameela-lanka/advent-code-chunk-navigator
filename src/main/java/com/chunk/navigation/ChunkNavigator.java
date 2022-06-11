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

    static List<Character> openingChars = List.of('(', '[', '{', '<');
    static List<Character> closingChars = List.of(')', ']', '}', '>');

    static Map<Character, Character> legalChunkCharMap = Map.of('(', ')', '[', ']', '{', '}', '<', '>');

    static Map<Character, Integer> errorScoreCharMap = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137);

    public static void main(String[] args) {
        for (String chunk : allLines) {
            System.out.println(chunk);
            getErrorScoreOfaLine(chunk);
        }
    }

    public static int getErrorScoreOfaLine(String line) {
        int errorScore = 0;
        Stack<Character> traversedChars = new Stack<>();
        for (Character currentChar : line.toCharArray()) {
            if (isOpeningChar(currentChar)) {
                traversedChars.push(currentChar);
            } else {
                Character lastOpenChar = traversedChars.pop();
                if (!isCurrentCharClosingOfPrevious(currentChar, lastOpenChar)) {
                    System.out.println("first illegal character found in the line '" + line + "' was ::  " + currentChar);
                    errorScore = errorScore + errorScoreCharMap.get(currentChar);
                }
            }
        }
        System.out.println("Error Score for each line :: " + errorScore);
        return errorScore;
    }

    public static boolean isOpeningChar(Character character) {
        return openingChars.contains(character);
    }

    public static boolean isCurrentCharClosingOfPrevious(Character currentChar, Character previousChar) {
        return null != legalChunkCharMap.get(previousChar) &&
                legalChunkCharMap.get(previousChar).equals(currentChar);
    }
}
