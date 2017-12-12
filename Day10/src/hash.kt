fun main(args: Array<String>) {
//    val list = (0..4).toMutableList()
    val list = (0..255).toMutableList()
    var currentPosition = 0
    var skipSize = 0
//    val lengths = arrayOf(3, 4, 1, 5)
    val lengths = arrayOf(14, 58, 0, 116, 179, 16, 1, 104, 2, 254, 167, 86, 255, 55, 122, 244)

    for (len in lengths) {
        list.reverseSublistCircular(currentPosition, (currentPosition + len - 1) % list.size)
        currentPosition = (currentPosition + len + skipSize) % list.size
        skipSize++
    }

    println(list)
    println("Numbers ${list[0] * list[1]}")
}

fun <E> MutableList<E>.reverseSublistCircular(from: Int, to: Int) {
    var front = from
    var back = to

    while (front != back) {
        val temp = this[front]
        this[front] = this[back]
        this[back] = temp

        front = (front + 1) % this.size
        if (front != back) {
            back--
            if (back < 0) {
                back += this.size
            }
        }

    }
}