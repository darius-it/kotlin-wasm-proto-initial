import kotlin.Array

fun main() {
    println("Loaded Kotlin WASM module successfully.")
}

@OptIn(ExperimentalJsExport::class, ExperimentalWasmJsInterop::class)
@JsExport
fun matrixMultiplication(
    mat1: JsArray<JsNumber>, mat1Dimension: JsNumber,
    mat2: JsArray<JsNumber>, mat2Dimension: JsNumber,
): JsArray<JsNumber> {
    require(mat1Dimension == mat2Dimension) {
        "Matrix dimension mismatch! Please only use square N x N matrices."
    }

    val result: MutableList<Int> = mutableListOf();

    for (i in 0 until mat1Dimension.toInt()) {
        for (j in 0 until mat1Dimension.toInt()) {
            result.add(
                mat1.toList()[i + j] * mat2.toList()[j + i]
            )
        }
    }


    val finalList = result.map { el -> el.toJsNumber() }

//    val list: List<JsNumber> =
//        listOf(1, 2).map { it.toJsNumber() }

    // Uses .toJsArray() to convert List or Array to JsArray
    val jsArray: JsArray<JsNumber> = finalList.toJsArray()

    return jsArray
}