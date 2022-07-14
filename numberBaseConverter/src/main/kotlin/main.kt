package converter

fun main() {
    print("Enter number in decimal system: ")
    val decimal = readLine()!!.toInt()
    println()
    print("Enter target base: ")
    val base = readLine()!!.toInt()
    println()
    print("Conversion result: ${converter(decimal, base)}")
}

fun converter(decimal: Int, base: Int):String {
    return when(base) {
        2 -> Integer.toBinaryString(decimal)
        8 -> Integer.toOctalString(decimal)
        16 -> Integer.toHexString(decimal)
        else -> convertDecimal(decimal, base)
    }
}

