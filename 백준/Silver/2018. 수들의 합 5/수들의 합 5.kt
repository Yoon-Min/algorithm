fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var left = 1
    var right = 1
    var count = 0
    var sum = 1

    while(true) {
        if(sum >= n) {
            if(sum == n) {
                count += 1
            }
            sum -= left
            left += 1
        }
        else if(right == n) {
            break
        }
        else {
            right += 1
            sum += right
        }
    }
    println(count)
}