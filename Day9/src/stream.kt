import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val filename = "input.txt"
    val file = File(filename)
    val garbageRegex = Regex("<(.*?)>")
    val noBang = Regex("!.").replace(file.readText(), "")
    val noGarbageInput = garbageRegex.replace(noBang, "")
    val noEmptyGarbage = noGarbageInput.replace("<>", "")
    val justGroupsInput = Regex("[^{}]").replace(noEmptyGarbage, "")
    val stack = Stack<Group>()
    for (c in justGroupsInput) {
        if (c == '{') {
            stack.push(Group())
        }
        else if (c == '}') {
            if (stack.size > 1) {
                val bottomGroup = stack.pop()
                if (stack.peek() != null) {
                    stack.peek().children.add(bottomGroup)
                }
            }
        }
    }
    val topGroup = stack.pop()
    val workQueue: Queue<Group> = LinkedList<Group>()
    workQueue.add(topGroup)
    var totalScore = 0
    while (workQueue.isNotEmpty()) {
        val item = workQueue.remove()
        totalScore += item.score
        item.children.forEach { it ->
            it.score = item.score + 1
            workQueue.add(it)
        }
    }
    println("Total Score: $totalScore")
    println("Total Garbage: ${garbageRegex.findAll(noBang).sumBy { it.groupValues[1].length }}")
}

data class Group(var score: Int = 1, val children: ArrayList<Group> = ArrayList())