import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    val filename = "input.txt"
    val file = File(filename)
    val input = file.readText().split(",")

    var x = 0
    var y = 0
    var z = 0

    var list = arrayListOf<Int>()

    for (dir in input) {
        when (dir) {
            "n" -> {
                y++
                z--
            }
            "s" -> {
                y--
                z++
            }
            "ne" -> {
                x++
                z--
            }
            "se" -> {
                x++
                y--
            }
            "nw" -> {
                x--
                y++
            }
            "sw" -> {
                x--
                z++
            }
        }
        list.add((abs(x) + abs(y) + abs(z)) / 2)
    }
    println("Distance: ${(abs(x) + abs(y) + abs(z)) / 2}")
    println("Farthest: ${list.max()}")
}

