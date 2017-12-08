import java.io.File

fun main(args: Array<String>) {
    val filename = "input.txt"
    val file = File(filename)
    val instructions = file.readLines().map { it.toInt() }.toIntArray()
    var nextNdx = 0
    var count = 0
    while (nextNdx < instructions.size) {
        count++
        val ins = instructions[nextNdx]
        if (ins >= 3) {
            instructions[nextNdx] -= 1
        }
        else {
            instructions[nextNdx] += 1
        }
        nextNdx += ins
    }

    println("Maze escaped in $count steps")
}