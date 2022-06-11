package com.chunk.navigation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChunkNavigatorTest {
    @Test
    void getErrorScoreOfaLine() {
        // given
        String line = "{([(<{}[<>[]}>{[]{[(<()>";
        ChunkNavigator.getErrorScoreOfaLine(line);
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
