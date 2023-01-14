### Производительность оптимального двоичного дерева поиска

| Заполнение дерева (N): | Weight sorting: creation | WS: search | Key sorting: creation | KS: search | 
|------------------------|--------------------------|------------|-----------------------|------------|
| 100000                 | 269 ms                   | 94 ms      | 293 ms                | 79 ms      |
| 500000                 | 964 ms                   | 504 ms     | 920 ms                | 292 ms     |
| 1000000                | 1947 ms                  | 818 ms     | 1574 ms               | 504 ms     |
| 2000000                | 3067 ms                  | 1690 ms    | 3789 ms               | 1144 ms    |
| 3000000                | 4750 ms                  | 2578 ms    | 5953 ms               | 2095 ms    |
| 4000000                | 6848 ms                  | 3530 ms    | 7517 ms               | 2400 ms    |

В целом, построение через нахождение центра тяжести весов (Key sorting, алгоритм 2) занимает всегда больше времени, 
чем при сортировке весов (Weight sorting, алгоритм 1). Проверка поиска сделана таким образом: ищется случайная нода, 
и она ищется повторно столько раз, скольким равен ее вес (своеобразная эмуляция нагрузки, ведь наибольшее преимущество 
оптимального дерева показывается, когда мы часто ищем "тяжелые" ноды). Поиск в среднем работает быстрее для алгоритма 
Key Sorting, и чем больше N, тем заметнее разница, ведь для Weight Sorting при больших N дерево вырождается в случайное.