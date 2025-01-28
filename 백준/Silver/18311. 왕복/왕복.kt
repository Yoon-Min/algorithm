fun main() {
    val (n,k) = readln().split(" ").map { it.toLong() }
    val input = readln().split(" ").map { it.toLong() }
    val dist = mutableListOf(Pair(0L,1))
    for(i in input.indices) {
        val nextDist = dist.last().first + input[i]
        val nextNum = if(i == input.lastIndex) dist.last().second else dist.last().second+1
        dist.add(Pair(nextDist,nextNum))
    }
    for(i in input.lastIndex downTo 0) {
        val nextDist = dist.last().first + input[i]
        val nextNum = dist.last().second - 1
        dist.add(Pair(nextDist,nextNum))
    }

    var i = 0
    while(i < dist.lastIndex) {
        if(k in dist[i].first..< dist[i+1].first) {
            println(dist[i].second)
            break
        }
        i += 1
    }
}