import java.lang.Exception

enum class CharType {
    NONE, WHITESPACE, WORD, SEPARATOR, OPERATOR
}

enum class TokenType {
    IDENTIFIER, KEYWORD, SEPARATOR, OPERATOR, LITERAL
}

data class Token(val value: String, val tokenType: TokenType) {
    override fun toString(): String {
        return "$tokenType($value)"
    }
}

fun recognizeCharType(char: Char): CharType {
    return when {
        char.isWhitespace() -> CharType.WHITESPACE
        char.isLetterOrDigit() -> CharType.WORD
        char in ";{}()" -> CharType.SEPARATOR
        char in ">=<!+-*/" -> CharType.OPERATOR
        else -> CharType.NONE
    }
}

fun tokenize(sourceText: String): ArrayList<Token> {
    val tokens = arrayListOf<Token>()

    var charType = CharType.NONE
    var lexeme = ""
    for (char in sourceText) {
        val currentCharType = recognizeCharType(char)

        if (charType == CharType.SEPARATOR) {
            tokens.add(Token(lexeme, TokenType.SEPARATOR))
            lexeme = ""
        }

        if (currentCharType != charType) {
            when (charType) {
                CharType.WORD -> {
                    if (lexeme in arrayOf("let", "if", "else", "while")) {
                        tokens.add(Token(lexeme, TokenType.KEYWORD))
                    } else {
                        tokens.add(Token(lexeme, TokenType.IDENTIFIER))
                    }
                }
                CharType.OPERATOR -> {
                    tokens.add(Token(lexeme, TokenType.OPERATOR))
                }
            }

            charType = currentCharType
            lexeme = ""
        }

        if (charType == CharType.NONE) {
            throw Exception("I know nothing about \"${lexeme}\", John Snow!")
        }

        lexeme += char
    }

    return tokens
}