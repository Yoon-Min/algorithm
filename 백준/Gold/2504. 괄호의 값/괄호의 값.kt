import kotlin.math.*

var input = ""
val pair = hashMapOf(')' to '(', ']' to '[')

fun main() {
    input = readln()
    println(getValueFrom(input))
}

fun getValueFrom(str: String): Int {
    val groupCheckerStack = mutableListOf<Char>()
    val groupStack = mutableListOf<Char>()
    val groupList = mutableListOf<String>()

    for(k in 0..str.lastIndex) {
        val cur = str[k]
        if(cur == '(' || cur == '[') {
            groupCheckerStack.add(cur)
            groupStack.add(cur)
        }
        else if(groupCheckerStack.isNotEmpty() && pair[cur] == groupCheckerStack.last()) {
            groupCheckerStack.removeLast()
            groupStack.add(cur)
            if(groupCheckerStack.isEmpty()) {
                groupList.add(groupStack.joinToString(""))
                groupStack.clear()
            }
        }
        else {
            return 0
        }
    }
    if(groupCheckerStack.isNotEmpty()) return 0

    return groupList.sumOf { g ->
        val mulNum = when(g.first()) {
            '(' -> 2
            else -> 3
        }
        if(g.length == 2) mulNum
        else mulNum * getValueFrom(g.slice(1..<g.lastIndex))
    }
}