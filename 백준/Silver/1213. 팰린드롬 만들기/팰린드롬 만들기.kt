import kotlin.math.*

fun main() {
    val name = readln()
    val alGroup = name.toSet().associateWith { name.count { c -> c == it } }
    val als = alGroup.keys.sorted()
    val palindrome = mutableListOf<Char>()

    if (name.length % 2 == 0 && alGroup.values.all { it % 2 == 0 }) {
        for (i in als.indices) {
            val al = als[i]
            val halfOfTotalAl = alGroup[al]!! / 2
            palindrome.addAll(List(halfOfTotalAl) { al })
        }
        palindrome.addAll(palindrome.reversed())
        println(palindrome.joinToString(""))
    } else if (
        name.length % 2 == 1 &&
        alGroup.values.count { it % 2 == 1 } == 1 &&
        alGroup.values.count { it % 2 == 0 } == alGroup.size - 1
    ) {
        val centerAl = alGroup.filter { it.value%2 == 1 }.keys.first()
        for (i in als.indices) {
            val al = als[i]
            val halfOfTotalAl = alGroup[al]!! / 2
            palindrome.addAll(List(halfOfTotalAl) { al })
        }
        palindrome.addAll(listOf(centerAl) + palindrome.reversed())
        println(palindrome.joinToString(""))
    } else {
        println("I'm Sorry Hansoo")
    }
}
