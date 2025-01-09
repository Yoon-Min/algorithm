import kotlin.math.*

val s = mutableListOf<Int>()
var minBlindSpotCount = Int.MAX_VALUE
val cameras = mutableListOf<Camera>()
val isVisited = MutableList(8) { false }
val directionMap = hashMapOf(
    1 to listOf(0,1,2,3),
    2 to listOf(0,1),
    3 to listOf(0,1,2,3),
    4 to listOf(0,1,2,3),
    5 to listOf(0)
)

fun main() {
    val (n,m) = readln().split(" ").map { it.toInt() }
    val map = mutableListOf<MutableList<Char>>()
    repeat(n) { x ->
        val inputLine = readln().toMutableList()
        map.add(inputLine)
        for(y in inputLine.indices) {
            if(map[x][y].code - 48 in 1..5) {
                cameras.add(Camera(Pair(x,y),map[x][y].code - 48))
            }
        }
    }
    rec(0, map)
    println(minBlindSpotCount)
}

fun rec(i: Int, map: List<MutableList<Char>>) {
    if(cameras.lastIndex < i) {
        val tmpMap = map.map { it.toMutableList() }
        cameras.zip(s).forEach { p -> updateCameraFov(true, p.first, p.second, tmpMap) }
        val tmpTotalBlindSpot = tmpMap.sumOf { inner -> inner.count { e -> e == '0' } }
        minBlindSpotCount = min(minBlindSpotCount, tmpTotalBlindSpot)
        return
    }
    val curCamera = cameras[i]
    if(!isVisited[i]) {
        isVisited[i] = true
        directionMap[curCamera.code]?.forEach { di ->
            s.add(di)
            rec(i+1, map)
            s.removeLast()
        }
        isVisited[i] = false
    }
}

fun updateCameraFov(isRequestToFill: Boolean, camera: Camera, directionIndex: Int, map: List<MutableList<Char>>) {
    when(camera.code) {
        1 -> updateFovTypeOne(isRequestToFill, camera, directionIndex, map)
        else -> updateFovOtherType(isRequestToFill, camera, directionIndex, map)
    }
}

data class Camera(
    val position: Pair<Int,Int>,
    val code: Int
)

fun updateFovTypeOne(isRequestToFill: Boolean, camera: Camera, directionIndex: Int, map: List<MutableList<Char>>) {
    val directionVector = listOf(listOf(0,1), listOf(1,0), listOf(0,-1), listOf(-1,0))[directionIndex]
    var curPos = camera.position
    while(map[curPos.first][curPos.second] != '6') {
        curPos = curPos.copy(curPos.first + directionVector.first(), curPos.second + directionVector.last())
        if(curPos.first !in map.indices || curPos.second !in map[0].indices) break
        if(isRequestToFill && map[curPos.first][curPos.second] == '0') {
            map[curPos.first][curPos.second] = '#'
        }
        else if(!isRequestToFill && map[curPos.first][curPos.second] == '#') {
            map[curPos.first][curPos.second] = '0'
        }
    }
}

fun updateFovOtherType(isRequestToFill: Boolean, camera: Camera, directionIndex: Int, map: List<MutableList<Char>>) {
    val directionVector = when(camera.code) {
        2 -> listOf(listOf(0,2), listOf(1,3))
        3 -> listOf(listOf(0,3), listOf(0,1), listOf(1,2), listOf(2,3))
        4 -> listOf(listOf(0,2,3), listOf(3,0,1), listOf(0,1,2), listOf(1,2,3))
        5 -> listOf(listOf(0,1,2,3))
        else -> throw IllegalArgumentException()
    }
    directionVector[directionIndex].forEach { di ->
        updateFovTypeOne(isRequestToFill, camera, di, map)
    }
}





