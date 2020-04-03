class DeclarationListener(val context: CompilerContext): SwatBaseListener() {
    override fun enterDefinition(ctx: SwatParser.DefinitionContext?) {
        context.variables.add(ctx!!.identifier().text)
    }
}