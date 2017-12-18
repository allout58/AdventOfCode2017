fun main(args: Array<String>) {
    val grid = (0..127).map { hash("amgozmfv-$it").joinToString("") { it.toString(2).padStart(4, '0') }.toCharArray().map { it == '1' } }
    println("Used squares: ${grid.map { it.filter { it }.count() }.sum()}")
}

fun hash(value: String): Array<Int> {
    val list = (0..255).toMutableList()
    var currentPosition = 0
    var skipSize = 0
    val input = value.trim().toCharArray().map { it.toInt() }
    val lengths = arrayListOf<Int>()
    lengths.addAll(input)
    lengths.addAll(arrayOf(17, 31, 73, 47, 23))

    repeat(64) {
        for (len in lengths) {
            if (len != 0) {
                list.reverseSublistCircular(currentPosition, (currentPosition + len - 1) % list.size)
            }
            currentPosition = (currentPosition + len + skipSize) % list.size
            skipSize++
        }
    }
    return ((0..15).map { list.subList(it * 16, (it + 1) * 16).reduce { acc, next -> acc xor next } }).toTypedArray()
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