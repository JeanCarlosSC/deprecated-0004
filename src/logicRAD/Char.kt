package logicRAD

fun Char.isOperator(): Boolean {
    return this == '+' || this == '-' || this == '*' || this == '^' || this == '/'
}
