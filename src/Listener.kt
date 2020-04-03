class Listener(val context: CompilerContext): SwatBaseListener() {
    override fun enterPrimaryStatement(ctx: SwatParser.PrimaryStatementContext) {
        print("(module (memory 1) (start 0) (func")

        for (variable in context.variables) {
            print(" (local \$$variable i32)")
        }

        print("\n\n")
    }

    override fun exitPrimaryStatement(ctx: SwatParser.PrimaryStatementContext) {
        println("\n))")
    }

    override fun exitDefinition(ctx: SwatParser.DefinitionContext) {
        println("local.set \$${ctx.identifier().text} ")
    }

    override fun enterLiteral(ctx: SwatParser.LiteralContext) {
        println("local.const ${ctx.text}")
    }

    override fun enterVariable(ctx: SwatParser.VariableContext) {
        println("local.get ${ctx.text}")
    }

    override fun exitExpression(ctx: SwatParser.ExpressionContext) {
        when {
            ctx.MINUS() != null -> println("i32.sub")
            ctx.PLUS() != null -> println("i32.add")
            ctx.TIMES() != null -> println("i32.mul")
            ctx.DIV() != null -> println("i32.div_s")
        }
    }

    override fun enterIfBlock(ctx: SwatParser.IfBlockContext) {
        println("if i32")
    }

    override fun enterElseBlock(ctx: SwatParser.ElseBlockContext) {
        println("else")
    }

    override fun exitCondition(ctx: SwatParser.ConditionContext) {
        println("end")
    }

    override fun enterLoop(ctx: SwatParser.LoopContext) {
        context.loopCount++
        println("block \$b${context.loopCount}")
        println("loop \$l${context.loopCount}")
    }

    override fun exitLoopBlock(ctx: SwatParser.LoopBlockContext) {
        println("br_if \$b${context.loopCount}")
        println("br \$l${context.loopCount}")
        println("end")
        println("end")
    }

    override fun exitEquation(ctx: SwatParser.EquationContext) {
        val op = ctx.relop()

        when {
            op.EQ() != null -> println("i32.eq")
            op.NEQ() != null -> println("i32.ne")
            op.GT() != null -> println("i32.gt_s")
            op.LT() != null -> println("i32.lt_s")
        }
    }
}