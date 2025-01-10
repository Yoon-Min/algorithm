import kotlin.math.*

fun main() {
    repeat(readln().toInt()) {
        val inputLine = readln().split(" ")
        val num = inputLine.first().toDouble()
        var sum = num
        for(i in 1..inputLine.lastIndex) {
            val o = inputLine[i]
            when(o) {
                "%" -> sum += 5
                "#" -> sum -= 7
                "@" -> sum *= 3
            }
        }
        println(String.format("%.2f", sum))
    }
}




