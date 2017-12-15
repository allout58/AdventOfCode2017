import java.io.File

fun main(args: Array<String>) {
    val list = (0..255).toMutableList()
    var currentPosition = 0
    var skipSize = 0
    val input = File("input.txt").readText().trim().toCharArray().map { it.toInt() }
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
    val denseHash = (0..15).map { list.subList(it * 16, (it + 1) * 16).reduce { acc, next -> acc xor next } }
    println("Dense Hash: ${denseHash.joinToString("") { it.toString(16).padStart(2, '0') }}")
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