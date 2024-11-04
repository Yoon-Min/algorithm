import kotlin.math.*

fun main() {
    val l = mutableListOf<Info>()
    repeat(readln().toInt()) {
        val info = readln().split(" ")
        l.add(Info(info[0],info[1].toInt(),info[2].toInt(),info[3].toInt()))
    }
    l.sortWith(compareBy(
        {it.year},
        {it.month},
        {it.day}
    ))
    println(l.last().name)
    println(l.first().name)
}

data class Info(
    val name:String,
    val day:Int,
    val month:Int,
    val year:Int
)


