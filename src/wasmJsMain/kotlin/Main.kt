import kotlin.Array

fun main() {
    println("Loaded Kotlin WASM module successfully.")
}

@OptIn(ExperimentalJsExport::class)
@JsExport
fun printTest() {
    println("Hello from Kotlin WASM!")
}

@OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)
@JsExport
fun firstFibonacciNumbers(count: Int): JsArray<JsNumber> {
    val fibs = generateSequence(Pair(1,1)) {
        Pair(it.second, it.first + it.second)
    }.map { it.first }

    val fibsList = fibs.take(count).toList()
    val jsListResult = fibsList.map { it.toJsNumber() }.toJsArray()

    return jsListResult
}

@OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)
@JsExport
fun firstFibonacciNumbersBigInt(count: Int): JsArray<JsBigInt> {
    val fibs: Sequence<Long> = generateSequence(Pair(1,1)) {
        Pair(it.second, it.first + it.second)
    }.map { it.first.toLong() }

    val fibsList = fibs.take(count).toList()
    val jsListResult = fibsList.map { it.toJsBigInt() }.toJsArray()

    return jsListResult
}
