import kotlin.math.*

fun main() {
    val (w,h) = readln().split(" ").map { it.toInt() }
    val horizontalCutPoints = mutableListOf<Int>()
    val verticalCutPoints = mutableListOf<Int>()
    val cut = readln().toInt()
    repeat(cut) {
        val (orientation, num) = readln().split(" ").map { it.toInt() }
        if(orientation == 0) {
            horizontalCutPoints.add(num)
        }
        else {
            verticalCutPoints.add(num)
        }
    }

    horizontalCutPoints.sort()
    verticalCutPoints.sort()

    var cur = 0
    var maxWidth = 0
    var maxHeight = 0

    for(next in horizontalCutPoints) {
        maxWidth = max(maxWidth, next-cur)
        cur = next
    }
    maxWidth = max(maxWidth, h-cur)

    cur = 0
    for(next in verticalCutPoints) {
        maxHeight = max(maxHeight, next-cur)
        cur = next
    }
    maxHeight = max(maxHeight, w-cur)

    println(maxWidth * maxHeight)
}