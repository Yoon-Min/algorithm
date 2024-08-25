import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

val fDp = MutableList(4000001) { 1L }
val mod = 1000000007L

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    storeFactorialN(n)
    val nFac = fDp[n]
    val kFac = fDp[k]
    val nkFac = fDp[n-k]
    val result = (nFac * getPower((kFac * nkFac) % mod, mod-2)) % mod
    println(result)
}

fun storeFactorialN(n: Int) {
    var result = 1L
    (1..n).forEach {
        result  = ((result%mod) * (it%mod)) % mod
        fDp[it] = result
    }
}

fun getPower(n: Long, pow: Long): Long {
    if(pow == 0L) return 1L
    else if(pow == 1L) return n

    val half = getPower(n, pow/2)
    var result =  ((half%mod) * (half%mod)) % mod
    if(pow%2 == 1L) result = ((result%mod) * (n%mod)) % mod

    return result
}