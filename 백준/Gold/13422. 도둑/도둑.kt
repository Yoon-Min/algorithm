fun main() = with(System.`in`.bufferedReader()) {
    repeat(readLine().toInt()) {
        val (n, m, k) = readLine().split(" ").map { it.toInt() }
        val arr = readLine().split(" ").map { it.toInt() }.toMutableList().also { it.addAll(it.toList()) }
        var result = 0

        var sum = (0 until m).sumOf { arr[it] }
        var left = 0
        var right = m - 1

        while (true) {
            if (sum < k) {
                result += 1
                if(n == m) break
            }
            sum -= arr[left++]
            sum += arr[++right]
            if (left == n) {
                break
            }
        }
        println(result)
    }
}