import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess

class CompilerContext {
    val variables = mutableListOf<String>()
    var loopCount = 0
}

fun main(args: Array<String>) {
    val filename = args.firstOrNull() ?: "source.swat"

    if (!Files.exists(Path.of(filename))) {
        println("wat: error: $filename: No such file or directory" +
                "compilation terminated.")
        exitProcess(1)
    }

    val charStream = CharStreams.fromFileName(filename)
    val swatLexer = SwatLexer(charStream)
    val commonTokenStream = CommonTokenStream(swatLexer)
    val swatParser = SwatParser(commonTokenStream)

    val parseTree = swatParser.primaryStatement()

    val context = CompilerContext()
    val declarationListerner = DeclarationListener(context)
    val listener = Listener(context)

    val declarationWalker = ParseTreeWalker.DEFAULT.walk(declarationListerner, parseTree)
    val parseTreeWalker = ParseTreeWalker.DEFAULT.walk(listener, parseTree)
}
