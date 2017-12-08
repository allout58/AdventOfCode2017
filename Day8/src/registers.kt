import java.io.File

fun main(args: Array<String>) {
    val filename = "input.txt"
    val file = File(filename)
    val regex = Regex("([a-z]+) (inc|dec) (-?\\d+) if ([a-z]+) ([<>!=]{1,2}) (-?\\d+)")
    val registers = HashMap<String, Int>()

    for (line in file.readLines()) {
        val match = regex.matchEntire(line)
        if (match != null) {
            val reg = match.groupValues[1]
            val op = match.groupValues[2]
            val delta = match.groupValues[3].toInt()
            val ifReg = match.groupValues[4]
            val comparator = match.groupValues[5]
            val ifVal = match.groupValues[6].toInt()

            val ifRegVal = registers[ifReg] ?: 0
            val doOp = when (comparator) {
                ">" -> ifRegVal > ifVal
                "<" -> ifRegVal < ifVal
                "<=" -> ifRegVal <= ifVal
                ">=" -> ifRegVal >= ifVal
                "==" -> ifRegVal == ifVal
                "!=" -> ifRegVal != ifVal
                else -> throw Exception("Invalid comparator `$comparator` on line `$line`")
            }
            if (doOp) {
                registers[reg] = (registers[reg] ?: 0) + if (op == "inc") delta else if (op == "dec") -1 * delta else throw Exception("Invalid op `$op` on line `$line`")
            }
        }
    }
    println("Largest value in any register is ${registers.map { it.value }.max()}")
}