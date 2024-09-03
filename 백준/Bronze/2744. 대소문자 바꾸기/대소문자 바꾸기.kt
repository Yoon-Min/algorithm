import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.*

fun main() {
    readln().forEach {
        if(it.code in (65..90)) print(it.lowercase())
        else if(it.code in (97..122)) print(it.uppercase())
    }
}