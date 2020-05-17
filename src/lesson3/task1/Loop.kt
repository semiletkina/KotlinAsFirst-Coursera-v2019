@file:Suppress("UNUSED_PARAMETER", "UNREACHABLE_CODE")

package lesson3.task1

import lesson2.task1.timeForHalfWay
import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 1
    var chislo = n
    while (chislo / 10 != 0) {
        chislo = chislo / 10
        count++
    }
    return count
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if ((n == 1) || (n == 2)) return 1
    else {
        var fib1 = 1
        var fib2 = 1
        var sum = 0
        for (i in 3..n) {
            sum = fib1 + fib2
            fib2 = fib1
            fib1 = sum
        }
        return sum
    }
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val k = m * n
    var i = 3
    if (k <= 2) return k
    if (m == n) return m
    if (isPrime(m) && isPrime(n)) return k
    while (i % m != 0 || i % n != 0)
        i++
    return i
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var minDel = 2
    while (n % minDel != 0)
        minDel++
    return minDel
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxDel = n / 2
    while (n % maxDel != 0)
        maxDel--
    return maxDel
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m == 1 && n == 1) return true
    if (isPrime(m) && isPrime(n)) return true
    else {
        for (i in 2..(max(m, n) / 2))
            if (m % i == 0 && n % i == 0) return false
        return true
    }
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    if (m == n) return true
    else if (sqrt(m.toDouble()).toInt() * sqrt(m.toDouble()).toInt() == m) return true
    else if (sqrt(n.toDouble()).toInt() * sqrt(n.toDouble()).toInt() == n) return true
    if (sqrt(m.toDouble()).toInt() == sqrt(n.toDouble()).toInt()) return false
    else return true
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var step = 0
    var number = x
    while (number != 1) {
        if (number % 2 == 0) number = number / 2
        else number = 3 * number + 1
        step++
    }
    return step
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var step = 1
    var l = x
    var sinus = 0.0
    var chlen: Double
    var i: Int
    if ((x * 180 / PI).toInt() % 180 == 0)
        return 0.0
    while (abs(l) >= (2 * PI))
        if (l < 0)
            l += 2 * PI
        else l -= 2 * PI
    do {
        chlen = 1.0
        for (i in 1..step)
            chlen *= (l / i.toDouble())
        if (step % 4 == 1) sinus += chlen
        else if (step % 4 == 3) sinus -= chlen
        step += 2
    } while (abs(chlen) >= eps)
    return sinus
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var step = 2
    var chlen = 1.0
    var cosinus = 1.0
    var l = x
    if ((x * 180 / PI).toInt() % 360 == 0)
        return 1.0
    if ((x * 180 / PI).toInt() % 180 % 2 == 1)
        return -1.0
    if ((x * 180 / PI).toInt() % 90 % 2 == 1)
        return 0.0
    while (abs(l) >= (2 * PI))
        if (l < 0)
            l += 2 * PI
        else l -= 2 * PI
    do {
        chlen = 1.0
        for (i in 1..step)
            chlen *= (l / i.toDouble())
        if (step % 4 == 0) cosinus += chlen
        else if (step % 4 == 2) cosinus -= chlen
        step += 2
    } while (abs(chlen) >= eps)
    return cosinus
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var new: Int = 0
    var old: Int = n
    var k: Int = 1
    while (old / 10 != 0) {
        old = old / 10
        k++
    }
    old = n
    var st10new = 1
    var st10old = 1
    for (i in 1..k - 1) st10old *= 10
    for (i in 1..k) {
        new += old / st10old * st10new
        old %= st10old
        st10new *= 10
        st10old /= 10
    }
    return new
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var new: Int
    new = revert(n)
    if (new == n) return true
    else return false
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    if (n / 10 == 0) return false
    else {
        var symbol = n % 10
        var ost = n / 10
        var symb = ost % 10
        while (symb == symbol && ost / 10 != 0) {
            ost = ost / 10
            symb = ost % 10
        }
        if (symb != symbol) return true
        else return false
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var k = 0
    var i = 1
    var m: Int
    var l: Int
    while (k < n) {
        m = i * i
        l = 1
        while (m / 10 != 0) {
            l++
            m = m / 10
        }
        k += l
        i++
    }
    m = (i - 1) * (i - 1)
    if (k == n) return m % 10
    else {
        for (j in 1..(k - n))
            m = m / 10
        return m % 10
    }
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = 2
    var i = 3
    var m: Int
    var l: Int
    if (n <= 2) return 1
    while (k < n) {
        m = fib(i)
        l = 1
        while (m / 10 != 0) {
            l++
            m = m / 10
        }
        k += l
        i++
    }
    m = fib(i - 1)
    if (k == n) return m % 10
    else {
        for (j in 1..(k - n))
            m /= 10
        return m % 10
    }
}
