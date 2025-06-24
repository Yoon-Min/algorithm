import kotlin.math.*

fun main() = with(System.`in`.bufferedReader()) {
    val (n,k) = readLine().split(" ").map { it.toInt() }
    val arr = readLine().toList()
    val checked = MutableList(n) { false }
    var cnt = 0

    for(i in arr.indices) {
        if (arr[i] == 'H') continue

        var leftPointer = i-1
        var leftTargetIndex = -1

        while(leftPointer > -1 && i-leftPointer <= k) {
            if (arr[leftPointer] == 'H' && !checked[leftPointer]) {
                leftTargetIndex = leftPointer
            }
            leftPointer -= 1
        }

        if (leftTargetIndex != -1) {
            checked[leftTargetIndex] = true
            cnt += 1
            continue
        }

        var rightPointer = i+1

        while(rightPointer < arr.size && rightPointer-i <= k) {
            if (arr[rightPointer] == 'H' && !checked[rightPointer]) {
                checked[rightPointer] = true
                cnt += 1
                break
            }
            rightPointer += 1
        }
    }

    println(cnt)
}
