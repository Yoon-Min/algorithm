import kotlin.math.*

fun main() {
    val switchStatus = MutableList(101) { 0 }
    val totalSwitch = readln().toInt()
    val inputStatus = readln().split(" ").map { it.toInt() }
    for(i in 0..inputStatus.lastIndex) {
        switchStatus[i+1] = inputStatus[i]
    }
    repeat(readln().toInt()) {
        val (gender, switchCode) = readln().split(" ").map { it.toInt() }
        when(gender) {
            1 -> {
                for(next in switchCode..totalSwitch step switchCode) {
                    switchStatus[next] = switchStatus[next] xor 1
                }
            }
            else -> {
                switchStatus[switchCode] = switchStatus[switchCode] xor 1
                var left = switchCode-1
                var right = switchCode+1
                while(
                    1 <= left &&
                    right <= totalSwitch &&
                    switchStatus[left] == switchStatus[right]
                ) {
                    switchStatus[left] = switchStatus[left] xor 1
                    switchStatus[right] = switchStatus[right] xor 1
                    left -= 1
                    right += 1
                }
            }
        }
    }
    var counter = 0
    var limit = 0
    while(counter < totalSwitch) {
        if(limit < 20) {
            print(switchStatus[counter+1])
            print(' ')
            limit += 1
            counter += 1
        }
        else {
            println()
            limit = 0
        }
    }
}