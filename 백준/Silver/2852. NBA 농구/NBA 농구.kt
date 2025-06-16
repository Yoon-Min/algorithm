import kotlin.math.*

fun main() {
    val n = readln().toInt()
    var leadingTeam = -1
    var leadingTeamGoalStartingTime = -1
    val leadingTime = MutableList(3) {0}
    val score = MutableList(3) {0}

    repeat(n) {
        val (inp1, inp2) = readln().split(" ")
        val teamNum = inp1.toInt()
        val goalTime = inp2.toSeconds()
        score[teamNum] += 1

        if(leadingTeam == -1) {
            leadingTeam = teamNum
            leadingTeamGoalStartingTime = goalTime
        }
        else if(score[1] == score[2]) {
            leadingTime[leadingTeam] += goalTime - leadingTeamGoalStartingTime
            leadingTeam = 0
            leadingTeamGoalStartingTime = 0
        }
        else if(score[1] > score[2]) {
            if(leadingTeam != 1) leadingTeamGoalStartingTime = goalTime
            leadingTeam = 1
        }
        else {
            if(leadingTeam != 2) leadingTeamGoalStartingTime = goalTime
            leadingTeam = 2
        }
    }
    if(leadingTeam != 0 && score.max() == score[leadingTeam]) {
        leadingTime[leadingTeam] += 48*60 - leadingTeamGoalStartingTime
    }

    println(leadingTime[1].toMinuets())
    println(leadingTime[2].toMinuets())
}

fun String.toSeconds(): Int {
    val splitTime = this.split(":").map { it.toInt() }
    return splitTime.first() * 60 + splitTime.last()
}

fun Int.toMinuets(): String {
    var minuets = (this / 60).toString()
    var seconds = (this % 60).toString()

    if(minuets.length == 1) {
        minuets = "0$minuets"
    }

    if(seconds.length == 1) {
        seconds = "0$seconds"
    }

    return "$minuets:$seconds"
}