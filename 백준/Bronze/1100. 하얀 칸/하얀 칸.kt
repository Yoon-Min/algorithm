import kotlin.math.*

fun main() {
    val map = mutableListOf<String>()
    repeat(8) {
        map.add(readln())
    }
    var count = 0
    for(i in 0..7) {
        for(j in 0..7) {
            if(i % 2 == 0) {
                if(j % 2 == 0 && map[i][j] == 'F') count++
            }
            else if(j % 2 != 0 && map[i][j] == 'F') count++
        }
    }
    println(count)
}
