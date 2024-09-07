fun main() {
    val n = readln().toInt()
    val totalLine = 2*n-1
    val resultLines = MutableList(totalLine) {""}
    for(lineNumber in 1..n) {
        val blankSize = n - lineNumber
        val resultLine = (List(blankSize) { " " } + List(lineNumber) { "*" }).joinToString("")
        resultLines[lineNumber - 1] = resultLine
        resultLines[totalLine - lineNumber] = resultLine
    }
    resultLines.forEach { println(it) }
}

