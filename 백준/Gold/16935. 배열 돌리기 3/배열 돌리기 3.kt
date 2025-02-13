
fun main() {
    var (n,m, r) = readln().split(" ").map { it.toInt() }
    var map = List(n) { readln().split(" ").map { it.toInt() }.toMutableList() }
    val calcSequence = readln().split(" ").map { it.toInt() }

    for(seq in calcSequence) {
        when(seq) {
            1 -> {
                for(i in 0 until m) {
                    for(j in 0 until n/2) {
                        val tmp = map[j][i]
                        map[j][i] = map[n-1-j][i]
                        map[n-1-j][i] = tmp
                    }
                }
            }
            2 -> {
                for(i in 0 until n) {
                    for(j in 0 until m/2) {
                        val tmp = map[i][j]
                        map[i][j] = map[i][m-1-j]
                        map[i][m-1-j] = tmp
                    }
                }
            }
            3 -> {
                map = (0 until m).map { j -> (n-1 downTo 0).map { i -> map[i][j] }.toMutableList() }
            }
            4 -> {
                map = (m-1 downTo 0).map { j -> (0 until n).map { i -> map[i][j] }.toMutableList() }
            }
            5 -> {
                val origin = map.map { it.toList() }
                for(section in 1..4) {
                    val sectionScope = getSectionScope(section, n, m)
                    for(i in sectionScope.first) {
                        for(j in sectionScope.second) {
                            val cur = origin[i][j]
                            val nextSectionPos = when(section) {
                                1 -> Pair(i, j + m/2)
                                2 -> Pair(i + n/2, j)
                                3 -> Pair(i, j - m/2)
                                4 -> Pair(i - n/2, j)
                                else -> throw IllegalArgumentException()
                            }
                            map[nextSectionPos.first][nextSectionPos.second] = cur
                        }
                    }
                }
            }
            6 -> {
                val origin = map.map { it.toList() }
                for(section in 1..4) {
                    val sectionScope = getSectionScope(section, n, m)
                    for(i in sectionScope.first) {
                        for(j in sectionScope.second) {
                            val cur = origin[i][j]
                            val nextSectionPos = when(section) {
                                1 -> Pair(i + n/2, j)
                                2 -> Pair(i, j - m/2)
                                3 -> Pair(i - n/2, j)
                                4 -> Pair(i , j + m/2)
                                else -> throw IllegalArgumentException()
                            }
                            map[nextSectionPos.first][nextSectionPos.second] = cur
                        }
                    }
                }
            }
        }
        n = map.size
        m = map[0].size
    }
    map.forEach { println(it.joinToString(" ")) }
}

fun getSectionScope(section: Int, n: Int, m: Int): Pair<IntProgression,IntProgression> {
    return when(section) {
        1 -> Pair(0 until n/2, 0 until m/2)
        2 -> Pair(0 until n/2, m/2 until m)
        3 -> Pair(n/2 until n, m/2 until m)
        4 -> Pair(n/2 until n, 0 until m/2)
        else -> throw IllegalArgumentException()
    }
}


