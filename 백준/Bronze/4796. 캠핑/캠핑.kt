fun main() {
    var round = 1
    while(true) {
        val (l,p,v) = readln().split(" ").map { it.toInt() }
        if(l == 0 && p == 0 && v == 0) break

        val rangeCnt = (v / p) * l.toLong()
        println("Case $round: ${rangeCnt + if(v % p <= l) v %p else l}")
        round += 1
    }
}