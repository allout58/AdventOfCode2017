import kotlin.math.abs

fun main(ags: Array<String>) {
    val input = 368078
    var x = 0
    var y = 0
    var deltaX = 1
    var deltaY = 1
    var dir = Direction.Right

    // Optimize based on the fact that the bottom right is always a perfect square
//    val nearestSquareRoot = Math.floor(Math.sqrt(input.toDouble())).toInt()
//    deltaX = nearestSquareRoot + 1
//    deltaY = nearestSquareRoot
//    val nearestSquare = Math.pow(nearestSquareRoot.toDouble(), 2.0)
    for (i in 2..input) {
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
//        println("$i ($x, $y) [$deltaX, $deltaY]")
    }
    print("$input is at ($x, $y), which is ${abs(x) + abs(y)} step(s) away")
}

enum class Direction {
    Up {
        override fun next() = Left
    },
    Down {
        override fun next() = Right
    },
    Left {
        override fun next() = Down
    },
    Right {
        override fun next() = Up
    };

    abstract fun next(): Direction
}