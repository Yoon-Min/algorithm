
val dVector = listOf(Pair(0,1), Pair(0,-1), Pair(1,0), Pair(-1,0))
// -> : 0,  <- : 1

fun main() = with(System.`in`.bufferedReader()) {
    val (m,n) = readLine().split(" ").map { it.toInt() }
    val map = List(m) { readLine().split(" ").map { it.toInt() } }
    val initPos = readLine().split(" ").map { it.toInt()-1 }.run { Node(this[0],this[1],this[2]) }
    val targetPos = readLine().split(" ").map { it.toInt()-1 }.run { Node(this[0],this[1],this[2]) }
    val q = ArrayDeque<Node>().also { it.add(initPos) }
    val requiredCommand = List(m) { List(n) { MutableList(4) {Int.MAX_VALUE} } }

    requiredCommand[initPos.i][initPos.j][initPos.dir] = 0
    while(q.isNotEmpty()) {
        val cur = q.removeFirst()

        cur.getNextDirection().forEach { nextDir ->
            if(cur.processedCommand+1 < requiredCommand[cur.i][cur.j][nextDir]) {
                requiredCommand[cur.i][cur.j][nextDir] = cur.processedCommand+1
                q.add(cur.copy(dir = nextDir, processedCommand = cur.processedCommand+1))
            }
        }

        for(k in 1..3) {
            val nextPos = cur.getNextPosIfGoK(k)
            val nx =  nextPos.second
            val ny = nextPos.first

            if(nx !in 0 until n || ny !in 0 until m) continue
            if(map[ny][nx] == 1) break
            if(cur.processedCommand+1 < requiredCommand[ny][nx][cur.dir]) {
                requiredCommand[ny][nx][cur.dir] = cur.processedCommand+1
                q.add(cur.copy(i = ny, j = nx, processedCommand = cur.processedCommand+1))
            }
        }
    }
//    requiredCommand.forEach { println(it) }

    println(requiredCommand[targetPos.i][targetPos.j][targetPos.dir])
}

data class Node(
    val i: Int,
    val j: Int,
    val dir: Int,
    val processedCommand: Int = 0
) {
    fun getNextDirection(): List<Int> {
        return when(dir) {
            0,1 -> listOf(2,3)
            else -> listOf(0,1)
        }
    }

    fun getNextPosIfGoK(k: Int): Pair<Int,Int> {
        return when(dir) {
            0 -> Pair(i, j+k)
            1 -> Pair(i, j-k)
            2 -> Pair(i+k, j)
            else -> Pair(i-k, j)
        }
    }
}

