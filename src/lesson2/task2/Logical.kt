@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.sqr
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    return number / 1000 + number % 1000 / 100 == number % 100 / 10 + number % 10
}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    return (x1 == x2) || (y1 == y2) || (abs(x1 - x2) == abs(y1 - y2))
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    val visok: Boolean
    if (year % 400 == 0) {
        visok = true
    }
    else if (year % 100 == 0) {
        visok = false
    }
    else if (year % 4 == 0) {
        visok = true
    }
    else visok = false
    if (month == 2 && visok)
        return 29
    else if (month == 2 && !visok)
        return 28
    else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        return 31
    else return 30
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {
    if (pointInsideCircle(x1 + r1,y1,x2,y2,r2) && pointInsideCircle(x1 - r1,y1,x2,y2,r2) && pointInsideCircle(x1,y1 + r1,x2,y2,r2) && pointInsideCircle(x1,y1 - r1,x2,y2,r2))
        return true
    else return false
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    var dlina = a
    var shir = b
    var vys = c
    if (a <= b && b <= c) {
        dlina = a
        shir = b
        vys = c
    }
    else if (c <= a && a <= b) {
        dlina = c
        shir = a
        vys = b
    }
    else if (b <= c && c <= a) {
        dlina = b
        shir = c
        vys = a
    }
    else if (a <= c && c <= b) {
        dlina = a
        shir = c
        vys = b
    }
    else if (b <= a && a <= c) {
        dlina = b
        shir = a
        vys = c
    }
    else if (c <= b && b <= a) {
        dlina = c
        shir = b
        vys = a
    }

    if (min(r,s) >= dlina && max(r,s) >= shir)
        return true
    else return false
}
