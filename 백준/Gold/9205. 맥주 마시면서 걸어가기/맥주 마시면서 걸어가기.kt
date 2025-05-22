import kotlin.math.*

val dVector = listOf(Pair(0,50), Pair(50,0), Pair(-50,0), Pair(0,-50))

fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        var isPossibleToVisit = false
        val n = readLine().toInt()
        val conv = mutableSetOf<Pair<Int,Int>>()

        val (sI, sJ) = readLine().split(" ").map { it.toInt() }

        repeat(n) {
            val pos = readLine().split(" ").map { it.toInt() }
            conv.add(Pair(pos.first(), pos.last()))
        }

        val (eI, eJ) = readLine().split(" ").map { it.toInt() }

        val q = ArrayDeque<Pair<Int,Int>>().also { it.add(Pair(sI,sJ)) }

        while(q.isNotEmpty()) {
            val cur = q.removeFirst()
            val dist = abs(cur.first - eI) + abs(cur.second - eJ)
            val requiredBeer = ceil(dist.toDouble() / 50).toInt()

            if(requiredBeer > 20) {
                val visible = mutableListOf<Pair<Int,Int>>()
                for(pos in conv) {
                    val distanceFromCurToConv = abs(pos.first - cur.first) + abs(pos.second - cur.second)
                    val requiredBeerForConv = ceil(distanceFromCurToConv.toDouble() / 50)

                    if(requiredBeerForConv <= 20) {
                        q.add(Pair(pos.first,pos.second))
                        visible.add(pos)
                    }
                }
                visible.forEach { p ->
                    conv.remove(p)
                }
            }
            else {
                isPossibleToVisit = true
                break
            }
        }

        if(isPossibleToVisit) println("happy")
        else println("sad")
    }
}