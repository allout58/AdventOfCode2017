import java.io.File

fun main(args: Array<String>) {
    val firewalls = HashMap<Int, Int>()
    File("input.txt").readLines().forEach {
        val splits = it.split(": ")
        firewalls.put(splits[0].toInt(), splits[1].toInt())
    }

    val maxDepth = firewalls.keys.max()!!

    var severity = 0
    val positions = Array(maxDepth + 1) { Scanner(-1) }
    firewalls.keys.forEach { positions[it].position = 0 }


    for (myDepth in 0..maxDepth) {
        if (positions[myDepth].position == 0) {
            severity += myDepth * (firewalls[myDepth] ?: 0)
        }
        updateScanners(firewalls, positions)
    }

    println("Final Severity: $severity")
}

fun updateScanners(firewalls: HashMap<Int, Int>, positions: Array<Scanner>) {
    for ((ndx, scan) in positions.withIndex()) {
        val range = firewalls[ndx]
        if (range != null) {
            if (scan.goingDown) {
                if (scan.position == range - 1) {
                    scan.goingDown = false
                    scan.position--
                }
                else {
                    scan.position++
                }
            }
            else {
                if (scan.position == 0) {
                    scan.goingDown = true
                    scan.position++
                }
                else {
                    scan.position--
                }
            }
        }
    }
}

data class Scanner(var position: Int, var goingDown: Boolean = true)