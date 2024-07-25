fun main() {
    while(true) {
        readlnOrNull()?.let {
            if(it.isNotBlank()) println(it)
        } ?: break
    }
}