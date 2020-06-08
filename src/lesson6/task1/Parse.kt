@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import java.lang.Exception
import javax.sound.midi.MetaMessage

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val slovar = mutableMapOf<String, Pair<String, Int>>()
    slovar["января"] = Pair("01", 31)
    slovar["февраля"] = Pair("02", 28)
    slovar["марта"] = Pair("03", 31)
    slovar["апреля"] = Pair("04", 30)
    slovar["мая"] = Pair("05", 31)
    slovar["июня"] = Pair("06", 30)
    slovar["июля"] = Pair("07", 31)
    slovar["августа"] = Pair("08", 31)
    slovar["сентября"] = Pair("09", 30)
    slovar["октября"] = Pair("10", 31)
    slovar["ноября"] = Pair("11", 30)
    slovar["декабря"] = Pair("12", 31)
    val date = str.split(" ")
    if (date.size != 3) return ""
    else {
        if (date[1] in slovar) {
            val day = date[0].toInt()
            val month = slovar[date[1]]?.first?.toInt()
            val year = date[2]
            if (day <= slovar[date[1]]!!.second)
                return (String.format("%02d.%02d", day, month) + "." + year)
        }
    }
    return ""
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val slovar = mutableMapOf<String, Pair<String, Int>>()
    slovar["01"] = Pair("января", 31)
    slovar["02"] = Pair("февраля", 28)
    slovar["03"] = Pair("марта", 31)
    slovar["04"] = Pair("апреля", 30)
    slovar["05"] = Pair("мая", 31)
    slovar["06"] = Pair("июня", 30)
    slovar["07"] = Pair("июля", 31)
    slovar["08"] = Pair("августа", 31)
    slovar["09"] = Pair("сентября", 30)
    slovar["10"] = Pair("октября", 31)
    slovar["11"] = Pair("ноября", 30)
    slovar["12"] = Pair("декабря", 31)
    val date = digital.split(".")
    if (date.size != 3) return ""
    if (date[2].length > 4) return ""
    else {
        if (date[1] in slovar) {
            val day = date[0].toInt()
            val month = slovar[date[1]]?.first
            val year = date[2].toInt()
            if (day <= slovar[date[1]]!!.second)
                return "$day $month $year"
        }
    }
    return ""
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    val slovar = setOf<Char>('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '+', '(', ')')
    val resslov = setOf<Char>('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+')
    var resh: String = ""
    val number = phone.split(" ")
    for (i in 0..number.size - 1) {
        var len = number[i].length - 1
        for (j in 0..len) {
            if (number[i][j] in slovar) {
                if (number[i][j] == '(' && number[i][j + 1] == ')') return ""
                if (number[i][j] in resslov) {
                    resh += number[i][j]
                }
            } else return ""
        }
    }
    return resh
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val slovar = setOf<Char>('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '%')
    val cifr = setOf<Char>('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
    val str = jumps.split(" ")
    var max = 0
    for (i in 0..str.size - 1) {
        var znac = str[i]
        var jump = ""
        for (j in 0..znac.length - 1) {
            if (!(znac[j] in slovar)) return -1
            else {
                if (znac[j] in cifr)
                    jump += znac[j]
            }
        }
        if (jump.isNotEmpty()) {
            var res = jump.toInt()
            if (res > max)
                max = res
        }
    }
    if (max == 0) return -1
    else return max
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var res = jumps.split(" ")
    val slovar = setOf<Char>('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '%')
    var max = 0
    var i = 0
    while (i < res.size) {
        var zn1 = ""
        for (j in 0..res[i].length - 1) {
            if (!(res[i][j] in slovar)) return -1
            zn1 += res[i][j]
        }
        var zn2 = ""
        for (j in 0..res[i + 1].length - 1) {
            if (!(res[i + 1][j] in slovar)) return -1
            zn2 += res[i + 1][j]
        }
        if (zn2.startsWith("+") && zn1.toInt() > max)
            max = zn1.toInt()
        i += 2
    }
    return max
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun throwExample() {
    throw IllegalArgumentException("")
}

fun plusMinus(expression: String): Int {
    if (expression.isEmpty()) throwExample()
    val exp = expression.split(" ")
    if (exp[0].startsWith('+') || exp[0].startsWith('-')) throwExample()
    var result = exp[0].toInt()
    var i = 1
    while (i < exp.size) {
        val zn = exp[i]
        if (exp[i + 1].startsWith('+') || exp[i + 1].startsWith('-')) throwExample()
        val ch = exp[i + 1].toInt()
        if (zn.startsWith('-'))
            result -= ch
        if (zn.startsWith('+'))
            result += ch
        i += 2
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val stroka = str.toLowerCase().split(" ")
    var ind = 0
    for (i in 0..stroka.size - 2) {
        if (stroka[i].length == stroka[i + 1].length) {
            var j = 0
            while (stroka[i][j] == stroka[i + 1][j] && j < stroka[i].length) {
                j++
                if (j == stroka[i].length)
                    return ind
            }
            ind += stroka[i].length + 1
        } else
            ind += stroka[i].length + 1
    }
    return -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    if (description.isEmpty()) return ""
    else {
        val sp = description.split(";")
        val spisok = mutableMapOf<String, Double>()
        var maxprice = 0.0
        var maxname = ""
        for (i in 0..sp.size - 1) {
            var dl = sp[i].removePrefix(" ").split(" ")
            spisok[dl[0]] = dl[1].toDouble()
        }
        for ((key, value) in spisok) {
            if (maxprice < value) {
                maxprice = value
                maxname = key
            }
        }
        return maxname
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    val slovar = mutableMapOf<String, Int>(
        "I" to 1,
        "IV" to 4,
        "V" to 5,
        "IX" to 9,
        "X" to 10,
        "XL" to 40,
        "L" to 50,
        "XC" to 90,
        "C" to 100,
        "CD" to 400,
        "D" to 500,
        "CM" to 900,
        "M" to 1000
    )
    var rc = roman
    println(rc)
    var result = 0
    while (rc.isNotEmpty()) {
        if ((rc.startsWith('I')) || (rc.startsWith('X')) || (rc.startsWith('C'))) {
            if (rc.length > 1) {
                var two = rc[0].toString()
                two += rc[1].toString()
                println(two)
                if (two in slovar) {
                    result += slovar[two]!!
                    rc = rc.substring(2)
                } else {
                    result += slovar[rc[0].toString()]!!
                    println(result)
                    rc = rc.substring(1)
                    println(rc)
                }
            } else {
                result += slovar[rc[0].toString()]!!
                println(result)
                rc = rc.substring(1)
                println(rc)
            }
        } else
            if (rc[0].toString() in slovar) {
                result += slovar[rc[0].toString()]!!
                println(result)
                rc = rc.substring(1)
                println(rc)
            } else return -1
    }
    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun throwExcep() {
    throw IllegalStateException("")
}

fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    var result = mutableListOf<Int>()
    var k = 0
    while (k < cells) {
        result.add(0)
        k++
    }
    val slovar = listOf<Char>('>', '<', '+', '-', '[', ']', ' ')
    var first = 0
    var second = 0
    val posotkr = mutableListOf<Int>()
    val poszakr = mutableListOf<Int>()
    for (i in 0..commands.length - 1) {
        if (!(commands[i] in slovar))
            throwExample()
        if (commands[i] == '[') {
            posotkr.add(i)
            first++
        }
        if (commands[i] == ']') {
            poszakr.add(i)
            second++
        }
    }
    if (first != second)
        throwExample()
    val oz = mutableMapOf<Int, Int>()
    val zo = mutableMapOf<Int, Int>()
    var i = 0
    if (first > 1) {
        while (i < first) {
            if (posotkr[i + 1] > poszakr[i]) {
                oz[posotkr[i]] = poszakr[i]
                i++
            } else {
                var j = i + 2
                if (j < posotkr.size) {
                    if (posotkr[j] < poszakr[j - 1]) {
                        oz[posotkr[j - 1]] = poszakr[j - 2]
                    }
                    j++
                }
                if (j == first) {
                    oz[posotkr[i]] = poszakr[j - 1]
                    oz[posotkr[j - 1]] = poszakr[j - 2]
                } else {
                    oz[posotkr[i]] = poszakr[j - 1]
                    oz[posotkr[j - 1]] = poszakr[j - 2]
                }
                i = j
            }
        }
    }
    if (first == 1)
        oz[posotkr[0]] = poszakr[0]
    if (!(oz.isEmpty()))
        for ((key, value) in oz)
            zo[value] = key
    println(oz)
    println(zo)
    var pos = cells / 2
    var poscom = 0
    var kolvo = 0
    while (pos < cells && kolvo < limit && poscom < commands.length) {
        if (commands[poscom] == '>') {
            pos++
            kolvo++
            poscom++
        } else if (commands[poscom] == '<') {
            pos--
            kolvo++
            poscom++
        } else if (commands[poscom] == '+') {
            result[pos]++
            kolvo++
            poscom++
        } else if (commands[poscom] == '-') {
            result[pos]--
            kolvo++
            poscom++
        } else if (poscom < commands.length) {
            if (commands[poscom] == '[') {
                if (result[pos] != 0) {
                    kolvo++
                    poscom++
                } else {
                    kolvo++
                    poscom = oz[poscom]!! + 1
                }
            } else if (commands[poscom] == ']') {
                if (result[pos] != 0) {
                    kolvo++
                    poscom = zo[poscom]!! + 1
                } else {
                    kolvo++
                    poscom++
                }
            }
        }
    }
    if (pos >= cells)
        throwExcep()
    return result.toList()
}
