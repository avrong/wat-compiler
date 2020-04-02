import java.io.File

fun main() {
    val sourceFile = File("source.txt")
    val sourceText = sourceFile.readText()

    val tokens = tokenize(sourceText)
    println(tokens)
}