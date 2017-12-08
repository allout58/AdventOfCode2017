fun main(args: Array<String>) {
    val input = 368078
//    val input =
    val mem = Array(600, { _ -> Array(600, { 0 }) })
    var lastVal = 1
    mem[300][300] = 1
    var x = 0
    var y = 0
    var deltaX = 1
    var deltaY = 1
    var dir = Direction.Right

    while (lastVal <= input) {
        when (dir) {
            Direction.Up -> {
                y++
                if (y == deltaY) {
                    dir = dir.next()
                }
            }
            Direction.Down -> {
                y--
                if (y == -deltaY) {
                    dir = dir.next()
                    deltaY++
                }
            }
            Direction.Right -> {
                x++
                if (x == deltaX) {
                    dir = dir.next()
                }
            }
            Direction.Left -> {
                x--
                if (x == -deltaX) {
                    dir = dir.next()
                    deltaX++
                }
            }
        }
        lastVal = getVal(x + 300, y + 300, mem)
        mem[y + 300][x + 300] = lastVal
        println("($x, $y) [$deltaX, $deltaY] = $lastVal")
    }
    print("$lastVal > $input is at ($x, $y)")
}

fun getVal(x: Int, y: Int, mem: Array<Array<Int>>): Int {
    var sum = 0
    for (i in x - 1..x + 1) {
        for (j in y - 1..y + 1) {
            sum += mem[j][i]
        }
    }
    return sum
}