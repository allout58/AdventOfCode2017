import java.io.File

fun main(args: Array<String>) {
    val filename = "input.txt"
    val file = File(filename)
    println(file.readLines().map { it.split(" ").map { it.toCharArray().sorted() } }.map { it.size == it.distinct().size }.count { it })
}