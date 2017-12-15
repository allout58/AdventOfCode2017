import java.io.File
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    val file = File("input.txt")
    val network = HashMap<String, Array<String>>()

    file.readLines().forEach {
        val split = it.split("<->")
        network.put(split[0].trim(), split[1].split(",").map { it.trim() }.toTypedArray())
    }

    val groups = HashMap<String, HashSet<String>>()
    val potentialGroupStarts = LinkedList<String>()
    potentialGroupStarts.addAll(network.keys)

    while (potentialGroupStarts.isNotEmpty()) {
        val start = potentialGroupStarts.pop()!!
        val workQueue: Queue<String> = LinkedList()
        workQueue.offer(start)
        val found = hashSetOf(start)

        while (workQueue.isNotEmpty()) {
            val key = workQueue.remove()
            val here = network[key]
            if (here == null) {
                throw Exception("Key $key from work queue not in network")
            }
            else {
                workQueue.addAll(here.dropWhile { it == key || found.contains(it) })
                found.addAll(here)
            }
        }
        groups.put(start, found)
        potentialGroupStarts.removeAll(found)
    }

    println("Total # Groups: ${groups.size}")
    println("Groups: $groups")
}