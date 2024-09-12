import kotlin.math.*

val sawtooth = mutableListOf<MutableList<Int>>(mutableListOf())

fun main() {
    repeat(4) {
        sawtooth.add(readln().map { it.code - 48 }.toMutableList())
    }
    repeat(readln().toInt()) {
        val (sawtoothNumber, direction) = readln().split(" ").map { it.toInt() }
        val rotationStatus = MutableList(5) { if(it == sawtoothNumber) direction else 0 }
        var prevLeftPole = sawtooth[sawtoothNumber][6]
        var prevRightPole = sawtooth[sawtoothNumber][2]

        for(number in sawtoothNumber+1..sawtooth.lastIndex) {
            val curLeftPole = sawtooth[number][6]
            if(prevRightPole != curLeftPole) {
                rotationStatus[number] = if(rotationStatus[number-1] == 1) -1 else 1
                prevRightPole = sawtooth[number][2]
            }
            else {
                break
            }
        }
        for(number in sawtoothNumber-1 downTo 1) {
            val curRightPole = sawtooth[number][2]
            if(prevLeftPole != curRightPole) {
                rotationStatus[number] = if(rotationStatus[number+1] == 1) -1 else 1
                prevLeftPole = sawtooth[number][6]
            }
            else {
                break
            }
        }
        for(number in 1..4) {
            rotate(number, rotationStatus[number])
        }
    }
    val result = sawtooth
        .filter { it.isNotEmpty() }
        .mapIndexed { i, l -> if(l.first() == 1) 2.0.pow(i).toInt() else 0 }
        .sum()

    println(result)
}

fun rotate(sawtoothNumber: Int, direction: Int) {
    var prev: Int
    val range: IntProgression
    val firstStorageIndex: Int

    when(direction) {
        1 -> {
            prev = sawtooth[sawtoothNumber][0]
            firstStorageIndex = 0
            range = 1..7
        }
        -1 -> {
            prev = sawtooth[sawtoothNumber][7]
            firstStorageIndex = 7
            range = 6 downTo 0
        }
        else -> return
    }

    for(i in range) {
        val tmp = sawtooth[sawtoothNumber][i]
        sawtooth[sawtoothNumber][i] = prev
        prev = tmp
    }
    sawtooth[sawtoothNumber][firstStorageIndex] = prev
}