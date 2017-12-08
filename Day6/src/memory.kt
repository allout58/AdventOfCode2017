fun main(args: Array<String>) {
//    val input = "0\t2\t7\t0"
    val input = "0\t5\t10\t0\t11\t14\t13\t4\t11\t8\t8\t7\t1\t4\t12\t11"
    val arr = input.split(Regex("\\s")).map { it.toInt() }.toIntArray()
    val states = ArrayList<String>()
    var currentState = arr.joinToString()
    while (!states.contains(currentState)) {
        states.add(currentState)
        var max: Int = arr.max()!!
        val ndx = arr.indexOf(max)
        arr[ndx] = 0
        var currentNdx = (ndx + 1) % arr.size
        while (max > 0) {
            arr[currentNdx] += 1
            currentNdx = (currentNdx + 1) % arr.size
            max--
        }
        currentState = arr.joinToString()

    }
    println("Loop length ${states.size - states.indexOf(currentState)}")
    println("Loop found after ${states.size} cycles")
}