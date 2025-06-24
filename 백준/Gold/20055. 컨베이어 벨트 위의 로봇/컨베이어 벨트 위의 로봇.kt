import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k) = readLine().split(" ").map { it.toInt() }
    val a = readLine().split(" ").map { it.toInt() }.toMutableList()
    val belt = MutableList(n) {0}
    
    var lastIndex = -1
    var round = 0

    while(a.count { it == 0 } < k) {
        round += 1
        // step1 rotate the belt
        val nextBelt = List(n) { i ->
            when(i) {
                0 -> 0
                else -> belt[i-1]
            }
        }

        val nextA = List(2*n) { i ->
            when(i) {
                0 -> a[2*n-1]
                else -> a[i-1]
            }
        }

        for(i in belt.indices) {
            belt[i] = nextBelt[i]
        }
        
        for(i in a.indices) {
            a[i] = nextA[i]
        }

        if(belt[n-1] == 1) {
            belt[n-1] = 0
        }

        for(i in n-1 downTo 0) {
            if(belt[i] == 1 && a[i+1] > 0 && belt[i+1] == 0) {
                belt[i+1] = 1
                belt[i] = 0
                a[i+1] -= 1

                if(belt[n-1] == 1) {
                    belt[n-1] = 0
                }
            }
        }

        // step3
        if(a[0] > 0) {
            belt[0] = 1
            a[0] -= 1
        }
    }

    println(round)
}
