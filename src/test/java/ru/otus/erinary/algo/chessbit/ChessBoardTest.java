package ru.otus.erinary.algo.chessbit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.erinary.algo.utils.FileResourcesUtils;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChessBoardTest {

    private static final Logger logger = LoggerFactory.getLogger(ChessBoardTest.class);

    @BeforeAll
    static void warmup() {
        ChessBitBoard.fillBits();
    }

    @Test
    void testKing() {
        var testCases = FileResourcesUtils.createTestCases("src/test/resources/chessbit/king/");
        for (FileResourcesUtils.TestCase testCase : testCases) {
            logger.info(testCase.getTestNumber());
            var uLong = ChessBitBoard.getKingsMoves(Integer.parseInt(testCase.getInput()));
            var moves = ChessBitBoard.simplePopcnt(uLong);
            assertEquals(Integer.parseInt(testCase.getOutput().get(1)), moves);
            assertEquals(new BigInteger(testCase.getOutput().get(2)), uLong);
        }
    }

    @Test
    void testKnight() {
        var testCases = FileResourcesUtils.createTestCases("src/test/resources/chessbit/knight/");
        for (FileResourcesUtils.TestCase testCase : testCases) {
            logger.info(testCase.getTestNumber());
            var uLong = ChessBitBoard.getKnightsMoves(Integer.parseInt(testCase.getInput()));
            var moves = ChessBitBoard.popcnt(uLong);
            assertEquals(Integer.parseInt(testCase.getOutput().get(1)), moves);
            assertEquals(new BigInteger(testCase.getOutput().get(2)), uLong);
        }
    }

    @Test
    void testRook() {
        var testCases = FileResourcesUtils.createTestCases("src/test/resources/chessbit/rook/");
        for (FileResourcesUtils.TestCase testCase : testCases) {
            logger.info(testCase.getTestNumber());
            var uLong = ChessBitBoard.getRookMoves(Integer.parseInt(testCase.getInput()));
            var moves = ChessBitBoard.cachedPopcnt(uLong);
            assertEquals(Integer.parseInt(testCase.getOutput().get(1)), moves);
            assertEquals(new BigInteger(testCase.getOutput().get(2)), uLong);
        }
    }

    @Test
    void testBishop() {
        var testCases = FileResourcesUtils.createTestCases("src/test/resources/chessbit/bishop/");
        for (FileResourcesUtils.TestCase testCase : testCases) {
            logger.info(testCase.getTestNumber());
            var uLong = ChessBitBoard.getBishopMoves(Integer.parseInt(testCase.getInput()));
            var moves = ChessBitBoard.cachedPopcnt(uLong);
            assertEquals(Integer.parseInt(testCase.getOutput().get(1)), moves);
            assertEquals(new BigInteger(testCase.getOutput().get(2)), uLong);
        }
    }

    @Test
    void testQueen() {
        var testCases = FileResourcesUtils.createTestCases("src/test/resources/chessbit/queen/");
        for (FileResourcesUtils.TestCase testCase : testCases) {
            logger.info(testCase.getTestNumber());
            var uLong = ChessBitBoard.getQueenMoves(Integer.parseInt(testCase.getInput()));
            var moves = ChessBitBoard.cachedPopcnt(uLong);
            assertEquals(Integer.parseInt(testCase.getOutput().get(1)), moves);
            assertEquals(new BigInteger(testCase.getOutput().get(2)), uLong);
        }
    }

}