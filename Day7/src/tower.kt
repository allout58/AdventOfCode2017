import java.io.File

fun main(args: Array<String>) {
    val filename = "input.txt"
    val file = File(filename)
    val items = ArrayList<Item>()
    val children = ArrayList<String>()
    for (line: String in file.readLines()) {
        val item = itemFromString(line)
        children.addAll(item.childrenNames)
        items.add(item)
    }
    items.forEach { it.children.addAll(it.childrenNames.map { name -> items.find { kid -> kid.name == name }!! }) }
    val bottomName = items.map { it.name }.minus(children)[0]
    println("Bottom is $bottomName")
    var parent = items.find { it.name == bottomName }!!
    var unbalancedChild = parent.unbalancedChild
    while (unbalancedChild != null) {
        val nextChild = unbalancedChild.unbalancedChild
        if (nextChild == null) {
            val siblingWeights = parent.children.map { it.weight }
            val diff = siblingWeights.max()!! - siblingWeights.min()!!
            println("Weight should be ${unbalancedChild._weight - diff}")
            break
        }
        parent = unbalancedChild
        unbalancedChild = nextChild
    }
}

data class Item(val name: String, var _weight: Int, val children: ArrayList<Item> = ArrayList(), val childrenNames: ArrayList<String> = ArrayList()) {
    private var childrenWeight = 0
    val weight: Int
        get() {
            if (children.size > 0 && childrenWeight == 0) {
                childrenWeight = children.map { it.weight }.reduce(Int::plus)
            }
            return _weight + childrenWeight
        }
    val unbalancedChild: Item?
        get() {
            val weights = children.map { it.weight }.distinct()
            return if (weights.size > 1) children.find { it.weight == weights.max()!! } else null
        }
}

fun itemFromString(str: String): Item {
    val match = Regex("([a-z]+) \\(([0-9]+)\\)( -> )?((?:[a-z]+(?:, )?)*)").matchEntire(str) ?: throw Exception("$str is not a valid item string")
    val groups = match.groupValues.subList(1, match.groupValues.size).filter { it != "" }
    val name = groups[0]
    val weight = groups[1]
    val it = Item(name, weight.toInt())
    if (groups.size == 4) {
        it.childrenNames.addAll(groups[3].split(", "))
    }
    return it
}