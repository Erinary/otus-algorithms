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

    public static final BigInteger A_COLUMN = new BigInteger("fefefefefefefefe", 16);
    public static final BigInteger AB_COLUMN = new BigInteger("fcfcfcfcfcfcfcfc", 16);
    public static final BigInteger H_COLUMN = new BigInteger("7f7f7f7f7f7f7f7f", 16);
    public static final BigInteger GH_COLUMN = new BigInteger("3f3f3f3f3f3f3f3f", 16);

    public static void main(String[] args) {
        System.out.println("King: " + getKingsMoves(59));
        System.out.println("Knight: " + getKnightsMoves(24));
    }

    /**
     * Вычисление возможных ходов короля.
     *
     * @param position начальная позиция фигуры
     * @return число, представляющее возможные ходы
     */
    public static BigInteger getKingsMoves(final int position) {
        var start = BigInteger.ONE.shiftLeft(position);
        var aPos = start.and(A_COLUMN);
        var hPos = start.and(H_COLUMN);

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

        var mask = GH_COLUMN.and(start.shiftLeft(6).or(start.shiftRight(10)))
                .or(H_COLUMN.and(start.shiftLeft(15).or(start.shiftRight(17))))
                .or(A_COLUMN.and(start.shiftLeft(17).or(start.shiftRight(15))))
                .or(AB_COLUMN.and(start.shiftLeft(10).or(start.shiftRight(6))));

        return trimToUInt64(mask);
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
