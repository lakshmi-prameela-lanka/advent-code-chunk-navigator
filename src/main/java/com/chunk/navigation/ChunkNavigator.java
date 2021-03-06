package com.chunk.navigation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    static Map<Character, Integer> autoCompleteScoreMap = Map.of(')', 1, ']', 2, '}', 3, '>', 4);

    public static void main(String[] args) {
        List<String> allLines = null;
        try (Stream<String> lines = Files.lines(Paths.get("src/main/resources/input_lines.txt"))) {
            allLines = lines.collect(Collectors.toList());
            System.out.println(allLines);
        } catch (IOException e) {
            System.out.println("Input file not found , Please try again");
        }
        var totNavSysErrorScore = calTotSyntaxErrorScore(allLines);
        System.out.println("Total Syntax Score for All Errors  :: " + totNavSysErrorScore);
    }

    private static void calAutoCompleteScore(List<String> lines) {
        List<Long> incompleteLinesScore = new ArrayList<>();
        for (String incompleteLine : lines) {
            Long autoCompleteScore = 0L;
            Stack<Character> traversedChars = new Stack<>();
            for (Character currentChar : incompleteLine.toCharArray()) {
                if (isOpeningChar(currentChar)) {
                    traversedChars.push(currentChar);
                } else {
                    traversedChars.pop();
                }
            }
            System.out.println("Traversed Chars :: " + traversedChars);
            // read the list from stack
            while (!traversedChars.empty()) {
                char closingChar = legalChunkCharMap.get(traversedChars.pop());
                autoCompleteScore = (autoCompleteScore * 5) + autoCompleteScoreMap.get(closingChar);
                System.out.println(autoCompleteScore);
            }
            System.out.println("Auto complete score :: for Line :: " + incompleteLine + ":: " + autoCompleteScore);
            incompleteLinesScore.add(autoCompleteScore);
        }
        System.out.println(incompleteLinesScore.size());
        // sort the list
        Collections.sort(incompleteLinesScore);
        System.out.println(incompleteLinesScore);
        System.out.println("Middles score is " + incompleteLinesScore.get(25));
    }

    public static int calTotSyntaxErrorScore(List<String> lines) {
        int totNavSysErrorScore = 0;
        List<String> incompleteLines = new ArrayList<>();
        List<String> corruptedLines = new ArrayList<>();
        System.out.println("Total Lines :: " + lines.size());
        for (String chunk : lines) {
            int errorScore = 0;
            Stack<Character> traversedChars = new Stack<>();
            boolean isCorruptedLine = isCorruptedLine(chunk, errorScore, traversedChars);
            if (!isCorruptedLine) {
                incompleteLines.add(chunk);
            } else {
                corruptedLines.add(chunk);
            }
            // System.out.println("Error Score for each line :: " + errorScore);
            totNavSysErrorScore = totNavSysErrorScore + errorScore;
        }
        System.out.println("Incomplete Lines :: " + incompleteLines.size());

        System.out.println("Corrupted Lines :: " + corruptedLines.size());
        System.out.println(incompleteLines);
        System.out.println(corruptedLines);
        calAutoCompleteScore(incompleteLines);
        return totNavSysErrorScore;
    }

    private static boolean isCorruptedLine(String line, int errorScore, Stack<Character> traversedChars) {
        boolean isCorruptedLine = false;
        for (Character currentChar : line.toCharArray()) {
            if (isOpeningChar(currentChar)) {
                traversedChars.push(currentChar);
            } else {
                Character lastOpenChar = traversedChars.pop();
                if (!isCurrentCharClosingOfPrevious(currentChar, lastOpenChar)) {
                    System.out.println("first illegal character found in the line '" + line + "' was ::  " + currentChar);
                    errorScore = errorScore + errorScoreCharMap.get(currentChar);
                    isCorruptedLine = true;
                    break;
                }
            }
        }
        return isCorruptedLine;
    }

    public static boolean isOpeningChar(Character character) {
        return openingChars.contains(character);
    }

    public static boolean isCurrentCharClosingOfPrevious(Character currentChar, Character previousChar) {
        return null != legalChunkCharMap.get(previousChar) &&
                legalChunkCharMap.get(previousChar).equals(currentChar);
    }
}
