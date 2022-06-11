package com.chunk.navigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChunkNavigatorTest {

    @Test
    void getErrorScoreOfaLineTest1() {
        // given
        String line = "{([(<{}[<>[]}>{[]{[(<()>";
        assertEquals(1197,ChunkNavigator.getErrorScoreOfaLine(line));
    }

    @Test
    void getErrorScoreOfaLineTest2() {
        // given
        String line = "[<(<(<(<{}))><([]([]()";
        assertEquals(3,ChunkNavigator.getErrorScoreOfaLine(line));
    }

    @Test
    void isOpeningCharTestTrue() {
        assertTrue(ChunkNavigator.isOpeningChar('('));
    }

    @Test
    void isOpeningCharTestFalse() {
        assertFalse(ChunkNavigator.isOpeningChar(']'));
    }

     @Test
    void isThisClosingCharOfPrecedingCharTrue(){
         assertTrue(ChunkNavigator.isCurrentCharClosingOfPrevious(')','('));
     }

    @Test
    void isThisClosingCharOfPrecedingCharFalse(){
        assertFalse(ChunkNavigator.isCurrentCharClosingOfPrevious('(','}'));
    }
}
