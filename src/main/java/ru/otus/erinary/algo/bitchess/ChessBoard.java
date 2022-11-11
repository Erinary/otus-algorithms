package ru.otus.erinary.algo.bitchess;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * ДЗ-05 - Шахматные биты.
 * <p>
 * Представление шахматного поля (возможные позиции):
 * 56	57	58	59	60	61	62	63
 * 48	49	50	51	52	53	54	55
 * 40	41	42	43	44	45	46	47
 * 32	33	34	35	36	37	38	39
 * 24	25	26	27	28	29	30	31
 * 16	17	18	19	20	21	22	23
 * 08	09	10	11	12	13	14	15
 * 00	01	02	03	04	05	06	07
 */
public class ChessBoard {

    public static final BigInteger EXCLUDED_A_COLUMN = new BigInteger("fefefefefefefefe", 16);
    public static final BigInteger EXCLUDED_AB_COLUMN = new BigInteger("fcfcfcfcfcfcfcfc", 16);
    public static final BigInteger EXCLUDED_H_COLUMN = new BigInteger("7f7f7f7f7f7f7f7f", 16);
    public static final BigInteger EXCLUDED_GH_COLUMN = new BigInteger("3f3f3f3f3f3f3f3f", 16);
    public static final BigInteger DIAGONAL_A8H1 = new BigInteger("102040810204080", 16);
    public static final BigInteger DIAGONAL_A1H8 = new BigInteger("8040201008040201", 16);

    public static void main(String[] args) {
        System.out.println("King: " + getKingsMoves(59));
        System.out.println("Knight: " + getKnightsMoves(24));
        System.out.println("Rook: " + getRookMoves(37));
        System.out.println("Bishop: " + getBishopMoves(58));
    }

    /**
     * Вычисление возможных ходов короля.
     *
     * @param position начальная позиция фигуры
     * @return число, представляющее возможные ходы
     */
    public static BigInteger getKingsMoves(final int position) {
        var start = BigInteger.ONE.shiftLeft(position);
        var aPos = start.and(EXCLUDED_A_COLUMN);
        var hPos = start.and(EXCLUDED_H_COLUMN);

        var mask = aPos.shiftLeft(7).or(start.shiftLeft(8)).or(hPos.shiftLeft(9))
                .or(aPos.shiftRight(1)).or(hPos.shiftLeft(1))
                .or(aPos.shiftRight(9)).or(start.shiftRight(8)).or(hPos.shiftRight(7));
        return trimToUInt64(mask);
    }

    /**
     * Вычисление возможных ходов коня.
     *
     * @param position начальная позиция фигуры
     * @return число, представляющее возможные ходы
     */
    public static BigInteger getKnightsMoves(final int position) {
        var start = BigInteger.ONE.shiftLeft(position);

        var mask = EXCLUDED_GH_COLUMN.and(start.shiftLeft(6).or(start.shiftRight(10)))
                .or(EXCLUDED_H_COLUMN.and(start.shiftLeft(15).or(start.shiftRight(17))))
                .or(EXCLUDED_A_COLUMN.and(start.shiftLeft(17).or(start.shiftRight(15))))
                .or(EXCLUDED_AB_COLUMN.and(start.shiftLeft(10).or(start.shiftRight(6))));

        return trimToUInt64(mask);
    }

    public static BigInteger getRookMoves(final int position) {
        var start = BigInteger.ONE.shiftLeft(position);

        var verticalMoves = new BigInteger("101010101010101", 16);
        while (verticalMoves.and(start).equals(BigInteger.ZERO)) {
            verticalMoves = verticalMoves.shiftLeft(1);
        }

        var horizontalMoves = new BigInteger("ff", 16);
        while (horizontalMoves.and(start).equals(BigInteger.ZERO)) {
            horizontalMoves = horizontalMoves.shiftLeft(8);
        }

        return trimToUInt64(verticalMoves.or(horizontalMoves));
    }

    public static BigInteger getBishopMoves(final int position) {
        var start = BigInteger.ONE.shiftLeft(position);

        var diagonalLeft = BigInteger.ZERO;
        var startingDiagonalLeft = DIAGONAL_A8H1;
        var leftLowerDiagonalField = new BigInteger("103070f1f3f7f", 16);
        for (int i = 0; i < 7; i++) {
            if (!startingDiagonalLeft.and(start).equals(BigInteger.ZERO)) {
                diagonalLeft = startingDiagonalLeft;
                break;
            }
            startingDiagonalLeft = startingDiagonalLeft.shiftRight(1).and(leftLowerDiagonalField);
        }
        if (diagonalLeft.equals(BigInteger.ZERO)) {
            startingDiagonalLeft = DIAGONAL_A8H1;
            var leftUpperDiagonalField = new BigInteger("fefcf8f0e0c08000", 16);
            for (int i = 0; i < 7; i++) {
                if (!startingDiagonalLeft.and(start).equals(BigInteger.ZERO)) {
                    diagonalLeft = startingDiagonalLeft;
                    break;
                }
                startingDiagonalLeft = startingDiagonalLeft.shiftLeft(1).and(leftUpperDiagonalField);
            }
        }

        var diagonalRight = BigInteger.ZERO;
        var startingDiagonalRight = DIAGONAL_A1H8;
        var rightLowerDiagonalField = new BigInteger("80c0e0f0f8fcfe", 16);
        for (int i = 0; i < 7; i++) {
            if (!startingDiagonalRight.and(start).equals(BigInteger.ZERO)) {
                diagonalRight = startingDiagonalRight;
                break;
            }
            startingDiagonalRight = startingDiagonalRight.shiftLeft(1).and(rightLowerDiagonalField);
        }
        if (diagonalRight.equals(BigInteger.ZERO)) {
            startingDiagonalRight = DIAGONAL_A1H8;
            var rightUpperDiagonalField = new BigInteger("7f3f1f0f07030100", 16);
            for (int i = 0; i < 7; i++) {
                if (!startingDiagonalRight.and(start).equals(BigInteger.ZERO)) {
                    diagonalRight = startingDiagonalRight;
                    break;
                }
                startingDiagonalRight = startingDiagonalRight.shiftRight(1).and(rightUpperDiagonalField);
            }
        }

        return trimToUInt64(diagonalLeft.or(diagonalRight));
    }

    /**
     * Убирает "лишние" (больше 64-го) разряды в числе.
     */
    private static BigInteger trimToUInt64(final BigInteger uint64) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(uint64.longValue());
        byte[] uint64Bytes = buffer.array();
        return new BigInteger(1, uint64Bytes);
    }
}
