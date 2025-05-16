import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n, w, l) = readLine().split(" ").map { it.toInt() }
    val weight = readLine().split(" ").map { it.toInt() }

    val waiting = weight.reversed().toMutableList()
    val bridge = ArrayDeque<Int>()
    val passed = mutableListOf<Int>()
    var time = 0
    var weightSumOnBridge = 0
    var trucksOnBridge = 0

    while (true) {
        bridge.addFirst(0)
        if(bridge.size == w+1) {
            if(bridge.last() > 0) {
                passed.add(bridge.last())
                trucksOnBridge -= 1
                weightSumOnBridge -= bridge.last()
            }
            bridge.removeLast()
        }

        if (waiting.isNotEmpty() && trucksOnBridge < w && weightSumOnBridge + waiting.last() <= l) {
            bridge[0] = waiting.last()
            weightSumOnBridge += waiting.last()
            trucksOnBridge += 1
            waiting.removeLast()
        }

//        println("$waiting $bridge $passed")

        time += 1

        if (passed.size == n) {
            break
        }
    }

    println(time)
}