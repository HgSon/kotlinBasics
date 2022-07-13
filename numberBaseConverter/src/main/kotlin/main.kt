package converter

fun main() {
    print("Enter number in decimal system: ")
    val decimal = readLine()!!.toInt()
    println()
    print("Enter target base: ")
    val base = readLine()!!.toInt()
    println()
    print("Conversion result: ${convertDecimal(decimal, base)}")
}

fun convertDecimal(decimal: Int, base: Int):String {
    val numberMap = mapOf(10 to "a", 11 to "b", 12 to "c", 13 to "d", 14 to "e", 15 to "f")
    var result = ""
    var quotient = decimal
    while(true) {
        val num = quotient % base
        result += if (num < 10) (num).toString() else numberMap.get(num)
        quotient /= base
        if (quotient < base) {
            result += if (quotient < 10) quotient.toString() else numberMap.get(quotient)
            break
        }
    }
    return result.reversed()
}