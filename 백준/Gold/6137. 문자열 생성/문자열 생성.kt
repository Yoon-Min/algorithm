import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = ArrayDeque<String>()
    val result = mutableListOf<String>()

    repeat(n) { arr.add(readLine()) }

    while(arr.isNotEmpty()) {

        if(arr.first() != arr.last()) {
            if(arr.first() < arr.last()) result.add(arr.removeFirst())
            else result.add(arr.removeLast())
            continue
        }

        var tmpLeft = 0
        var tmpRight = arr.lastIndex
        while(tmpLeft < tmpRight && arr[tmpLeft] == arr[tmpRight]) {
            tmpLeft += 1
            tmpRight -= 1
        }

        if(tmpLeft >= tmpRight) {
            result.add(arr.removeFirst())
            continue
        }

        if(arr[tmpLeft] < arr[tmpRight]) {
            result.add(arr.removeFirst())
        }
        else {
            result.add(arr.removeLast())
        }
    }

    val strResult = result.joinToString("")

    if(strResult.length > 80) {
        var i = 0
        var cnt = 0
        while(i < strResult.length) {
            print(result[i])
            cnt += 1
            if(cnt == 80) {
                cnt = 0
                println()
            }
            i += 1
        }
    }
    else {
        println(strResult)
    }

}