import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val totalStudents = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }

    val frames = mutableListOf<Node>()
    val studentsInFrames = mutableSetOf<Int>()
    val recommendedCount = MutableList(101) { 0 }
    var order = 1

    arr.forEach { recommendedStudent ->
        if(studentsInFrames.contains(recommendedStudent)) {
            recommendedCount[recommendedStudent] += 1
        }
        else if (frames.size < n) {
            frames.add(Node(recommendedStudent, order++))
            recommendedCount[recommendedStudent] = 1
            studentsInFrames.add(recommendedStudent)
        }
        else {
            frames.sortWith(compareBy({ -recommendedCount[it.num] }, { -it.order }))
            val minRecommendedNode = frames.removeLast()
            recommendedCount[minRecommendedNode.num] = 0
            studentsInFrames.remove(minRecommendedNode.num)
            frames.add(Node(recommendedStudent, order++))
            studentsInFrames.add(recommendedStudent)
            recommendedCount[recommendedStudent] = 1
        }
    }

    frames.sortBy { it.num }
    println(frames.map { it.num }.joinToString(" "))
}

data class Node(
    val num: Int,
    val order: Int,
)