import java.io.File

fun main(args: Array<String>) {
    val firewalls = File("input.txt").readLines().map { it.split(": ").map { it.toInt() } }
            .associate { it.first() to it.last() }

    val severity = firewalls.map { if (it.key % (2 * (it.value - 1)) == 0) it.key * it.value else 0 }.sum()
    var delay = 0
    while (firewalls.filter { (it.key + delay) % (2 * (it.value - 1)) == 0 }.isNotEmpty())
        delay++

    println("Severity: $severity")
    println("Delay: $delay")
}

