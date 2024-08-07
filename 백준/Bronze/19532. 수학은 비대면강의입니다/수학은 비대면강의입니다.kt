import java.util.*
import kotlin.math.*

fun main() {
     val const = readln().split(" ").map { it.toInt() }
     for(x in -999..999) {
         for(y in -999..999) {
             val firstCheck = const[0]*x + const[1]*y == const[2]
             val secondCheck = const[3]*x + const[4]*y == const[5]
             if(firstCheck && secondCheck) {
                 println("$x $y")
             }
         }
     }
}