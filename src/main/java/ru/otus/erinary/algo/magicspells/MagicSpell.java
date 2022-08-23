package ru.otus.erinary.algo.magicspells;

import java.util.List;
import java.util.function.BiFunction;

/**
 * ДЗ-01 - Квадратные заклинания.
 */
public class MagicSpell {
    private static final List<BiFunction<Integer, Integer, Boolean>> SPELLS = List.of(
            ((x, y) -> x < y),
            ((x, y) -> x.equals(y)),
            ((x, y) -> x == 24 - y),
            ((x, y) -> x <= 24 - y + 5),
            ((x, y) -> (2 * x == y) || (2 * x == y - 1)),
            ((x, y) -> x < 10 || y < 10),
            ((x, y) -> x >= 16 && y >= 16),
            ((x, y) -> x == 0 || y == 0),
            ((x, y) -> (x >= y + 11) || (y >= x + 11)),
            ((x, y) -> (x < y) && (2 * x + 2 > y)),
            ((x, y) -> x == 1 || x == 23 || y == 1 || y == 23),
            ((x, y) -> Math.pow(x, 2) + Math.pow(y, 2) <= 400),
            ((x, y) -> (x - 5 < 24 - y) && (x + 5 > 24 - y)),
            ((x, y) -> Math.pow((x - 27), 2) + Math.pow((y - 27), 2) >= 529),
            ((x, y) -> ((x - 10 >= y) && (x - 20 <= y)) || ((x + 10 <= y) && (x + 20 >= y))),
            ((x, y) -> ((x + 9 >= y) && (x - 9 <= y)) && ((x >= 15 - y) && (x <= 33 - y))),
            ((x, y) -> x - 15 > (8 * Math.sin(((double) y / 10 * Math.PI)))),
            ((x, y) -> ((x == 0) || (y == 0)) && (!x.equals(y)) || (x == 1) || (y == 1)),
            ((x, y) -> x == 0 || x == 24 || y == 0 || y == 24),
            ((x, y) -> x % 2 == y % 2),
            ((x, y) -> {
                var condition = false;
                for (int n = 0; n < 25; n++) {
                    condition = condition || (n * x + n == y);

                }
                return condition;
            }),
            ((x, y) -> {
                var condition = false;
                for (int n = 0; n < 25; n++) {
                    condition = condition || (x == 3 * n - y);
                }
                return condition;
            }),
            ((x, y) -> x % 3 == 0 && y % 2 == 0),
            ((x, y) -> x.equals(y) || x == 24 - y),
            ((x, y) -> {
                var condition = false;
                for (int n = 0; n < 5; n++) {
                    condition = condition || (x == 6 * n || y == 6 * n);
                }
                return condition;
            })

    );

    private static void draw(final BiFunction<Integer, Integer, Boolean> function) {
        for (int x = 0; x < 25; x++) {
            var line = new StringBuilder();
            for (int y = 0; y < 25; y++) {
                var condition = function.apply(x, y);
                var symbol = "# ";
                if (!condition) {
                    symbol = ". ";
                }
                line.append(symbol);
            }
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        var spellNumber = 0;
        for (var spell : SPELLS) {
            spellNumber++;
            System.out.printf("%n%d.%n%n", spellNumber);
            draw(spell);
        }
    }
}
