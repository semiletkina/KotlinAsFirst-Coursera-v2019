@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson9.task2.fifteenGameMoves
import kotlin.math.*

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var modul = 0.0
    for (element in v) modul += element * element
    return sqrt(modul)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    return if (list.size == 0) 0.0
    else list.sum() / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val srar = mean(list)
    if (list.size == 0)
        return list
    else {
        for (i in 0..list.size - 1) list[i] = list[i] - srar
        return list
    }
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    if (a.size == 0) return 0
    else {
        var scalyar = 0
        for (i in 0 until a.size) scalyar += a[i] * b[i]
        return scalyar
    }
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var chlenX = 1
    var poly = 0
    if (p.size == 0) return 0
    else {
        for (i in 0 until p.size) {
            poly += p[i] * chlenX
            chlenX *= x
        }
        return poly
    }
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.size == 0) return list
    else {
        var summa = list[0]
        for (i in 1 until list.size) {
            summa += list[i]
            list[i] = summa
        }
        return list
    }
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var del = 2
    var chislo = n
    while (!chislo.equals(1)) {
        while (chislo % del == 0) {
            chislo = chislo / del
            list.add(del)
        }
        del += 1
    }
    return list
}


/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = mutableListOf<Int>()
    var del = 2
    var chislo = n
    while (!chislo.equals(1)) {
        while (chislo % del == 0) {
            chislo = chislo / del
            list.add(del)
        }
        del += 1
    }
    return list.joinToString(separator = "*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var conv = listOf<Int>()
    var chislo = n
    if (n == 0) {
        conv = listOf<Int>(0)
        return conv
    }
    while (chislo > 0) {
        var element = chislo % base
        conv = listOf(element) + conv
        chislo = chislo / base
    }
    return conv
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var conv = ""
    var chislo = n
    var el = ""
    var element: Int
    val numtostr = "abcdefghijklmnopqrstuvwxyz"
    if (n == 0) {
        conv = "0"
        return conv
    }
    while (chislo > 0) {
        element = chislo % base
        if (element > 9) el = numtostr[element - 10].toString()
        else el = "$element"
        conv = el + conv
        chislo /= base
    }
    return conv
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var dig = digits
    val dl = digits.size
    var bas = 1
    var dec = 0
    var j = dl - 1
    for (i in 0 until dl) {
        dec += dig[j] * bas
        bas = bas * base
        j -= 1
    }
    return dec
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    var stroka = str
    val dl = str.length
    var dec = 0
    var bas = 1
    var j = dl - 1
    val numtostr = "abcdefghijklmnopqrstuvwxyz"
    val strtonum = "0123456789"
    for (i in 0 until dl) {
        if (stroka[j] in numtostr) {
            dec += (numtostr.indexOf(stroka[j], 0) + 10) * bas
        }
        if (stroka[j] in strtonum) {
            dec += (strtonum.indexOf(stroka[j], 0)) * bas
        }
        bas = bas * base
        j -= 1
    }
    return dec
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var ch = n
    var ost = 0
    var poz = 1
    var rom = ""
    val ed = arrayOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val des = arrayOf("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val sot = arrayOf("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val tys = arrayOf("M", "MM", "MMM")
    while (ch > 0) {
        ost = ch % 10
        if (ost != 0) {
            if (poz == 1) {
                rom = ed[ost - 1] + rom
            }
            if (poz == 2) {
                rom = des[ost - 1] + rom
            }
            if (poz == 3) {
                rom = sot[ost - 1] + rom
            }
            if (poz == 4) {
                rom = tys[ost - 1] + rom
            }
        }
        poz += 1
        ch = ch / 10
    }
    return rom
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    var rus = ""
    if (n == 0) return "нуль"
    var osttys = n % 1000
    var celtys = n / 1000
    val sto =
        arrayOf("сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот")
    val desyat =
        arrayOf("двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто")
    val edintys =
        arrayOf(
            "одна",
            "две",
            "три",
            "четыре",
            "пять",
            "шесть",
            "семь",
            "восемь",
            "девять",
            "десять",
            "одиннадцать",
            "двенадцать",
            "тринадцать",
            "четырнадцать",
            "пятнадцать",
            "шестнадцать",
            "семнадцать",
            "восемнадцать",
            "девятнадцать"
        )
    val edin =
        arrayOf(
            "один",
            "два",
            "три",
            "четыре",
            "пять",
            "шесть",
            "семь",
            "восемь",
            "девять",
            "десять",
            "одиннадцать",
            "двенадцать",
            "тринадцать",
            "четырнадцать",
            "пятнадцать",
            "шестнадцать",
            "семнадцать",
            "восемнадцать",
            "девятнадцать"
        )
    if (celtys > 100) {
        rus = sto[celtys / 100 - 1] + " "
        if (celtys % 100 == 0) rus += "тысяч "
        celtys = celtys % 100
    }
    if (celtys > 19) {
        rus += desyat[celtys / 10 - 2] + " "
        if (celtys % 10 == 0) rus += "тысяч "
        else {
            rus += edintys[celtys % 10 - 1] + " "
            if (celtys % 10 == 1) rus += "тысяча "
            if ((celtys % 10 == 2) || (celtys % 10 == 3) || (celtys % 10 == 4)) rus += "тысячи "
            if (celtys % 10 > 4) rus += "тысяч "
        }
    }
    if ((celtys < 20) and (celtys > 0)) {
        rus += edintys[celtys - 1] + " "
        if (celtys == 1) rus += "тысяча "
        if ((celtys == 2) || (celtys == 3) || (celtys == 4)) rus += "тысячи "
        if (celtys > 4) rus += "тысяч "
    }
    if (osttys == 0) return rus.trim()
    if (osttys > 100) {
        rus += sto[osttys / 100 - 1] + " "
        osttys %= 100
    }
    if (osttys > 19) {
        rus += desyat[osttys / 10 - 2] + " "
        if (osttys % 10 == 0) return rus.trim()
        else
            rus += edin[osttys % 10 - 1]
    }
    if ((osttys < 20) and (osttys > 0)) rus += edin[osttys - 1]
    return rus.trim()
}