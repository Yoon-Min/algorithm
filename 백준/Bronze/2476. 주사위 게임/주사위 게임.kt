import kotlin.math.*

/*
같은 눈이 3개가 나오면 10,000원+(같은 눈)×1,000원의 상금을 받게 된다.
같은 눈이 2개만 나오는 경우에는 1,000원+(같은 눈)×100원의 상금을 받게 된다.
모두 다른 눈이 나오는 경우에는 (그 중 가장 큰 눈)×100원의 상금을 받게 된다.
 */


fun main() {
    val n = readln().toInt()
    var max = 0
    repeat(n) {
        val dice = readln().split(" ").map { it.toInt() }.groupBy { it }
        val result = when(dice.size) {
            1 -> 10000 + (dice.keys.first()) * 1000
            2 -> 1000 + (dice.filter { it.value.size > 1 }.keys.first()) * 100
            else -> dice.keys.max() * 100
        }
        max = max(result, max)
    }
    println(max)
}

