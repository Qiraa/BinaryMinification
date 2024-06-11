package com.example.binaryminification.calcfunction

import androidx.compose.runtime.mutableStateListOf

typealias VariableResolver = (String) -> Boolean

open class LogicalExpression {
    open fun evaluate(resolver: VariableResolver): Boolean = false
}

class VariableExpression(private val variable: String) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = resolver(variable)
}

class NotExpression(private val expression: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = !expression.evaluate(resolver)
}

class AndExpression(private val left: LogicalExpression, private val right: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = left.evaluate(resolver) && right.evaluate(resolver)
}

class OrExpression(private val left: LogicalExpression, private val right: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = left.evaluate(resolver) || right.evaluate(resolver)
}

class ImpliesExpression(private val left: LogicalExpression, private val right: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = !left.evaluate(resolver) || right.evaluate(resolver)
}

class XORExpression(private val left: LogicalExpression, private val right: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = left.evaluate(resolver) xor right.evaluate(resolver)
}

class ShaeferExpression(private val left: LogicalExpression, private val right: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = !(left.evaluate(resolver) && right.evaluate(resolver))
}

class PierArrowExpression(private val left: LogicalExpression, private val right: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = !(left.evaluate(resolver) || right.evaluate(resolver))
}

class EqualExpression(private val left: LogicalExpression, private val right: LogicalExpression) : LogicalExpression() {
    override fun evaluate(resolver: VariableResolver): Boolean = left.evaluate(resolver) == right.evaluate(resolver)
}

fun parseExpression(input: String): LogicalExpression {
    val trimmedInput = input.replace(" ", "")
    return parseImpliesExpression(trimmedInput)
}

private fun parseImpliesExpression(input: String): LogicalExpression {
    val parts = splitByTopLevelOperator(input, "→")
    return if (parts.size > 1) {
        ImpliesExpression(parseXORExpression(parts[0]), parseXORExpression(parts[1]))
    } else {
        parseXORExpression(input)
    }
}

private fun parseXORExpression(input: String): LogicalExpression {
    val parts = splitByTopLevelOperator(input, "⊕")
    return if (parts.size > 1) {
        XORExpression(parseShaeferExpression(parts[0]), parseShaeferExpression(parts[1]))
    } else {
        parseShaeferExpression(input)
    }
}

private fun parseShaeferExpression(input: String): LogicalExpression {
    val parts = splitByTopLevelOperator(input, "|")
    return if (parts.size > 1) {
        parts.drop(1).fold(parsePierArrowExpression(parts[0])) { left, part -> ShaeferExpression(left, parsePierArrowExpression(part)) }
    } else {
        parsePierArrowExpression(input)
    }
}

private fun parsePierArrowExpression(input: String): LogicalExpression {
    val parts = splitByTopLevelOperator(input, "↓")
    return if (parts.size > 1) {
        parts.drop(1).fold(parseOrExpression(parts[0])) { left, part -> PierArrowExpression(left, parseOrExpression(part)) }
    } else {
        parseOrExpression(input)
    }
}

private fun parseOrExpression(input: String): LogicalExpression {
    val parts = splitByTopLevelOperator(input, "∨")
    return if (parts.size > 1) {
        parts.drop(1).fold(parseAndExpression(parts[0])) { left, part -> OrExpression(left, parseAndExpression(part)) }
    } else {
        parseAndExpression(input)
    }
}

private fun parseAndExpression(input: String): LogicalExpression {
    val parts = splitByTopLevelOperator(input, "∧")
    return if (parts.size > 1) {
        parts.drop(1).fold(parseNotExpression(parts[0])) { left, part -> AndExpression(left, parseNotExpression(part)) }
    } else {
        parseNotExpression(input)
    }
}

private fun parseNotExpression(input: String): LogicalExpression {
    return if (input.startsWith("¬")) {
        NotExpression(parsePrimaryExpression(input.substring(1)))
    } else {
        parsePrimaryExpression(input)
    }
}

private fun parsePrimaryExpression(input: String): LogicalExpression {
    return if (input.startsWith('(') && input.endsWith(')')) {
        parseImpliesExpression(input.substring(1, input.length - 1))
    } else {
        VariableExpression(input)
    }
}

private fun splitByTopLevelOperator(input: String, operator: String): List<String> {
    val parts = mutableListOf<String>()
    val currentPart = StringBuilder()
    var level = 0
    var i = 0

    while (i < input.length) {
        val char = input[i]
        when (char) {
            '(' -> level++
            ')' -> level--
        }

        if (level == 0 && input.startsWith(operator, i)) {
            parts.add(currentPart.toString())
            currentPart.clear()
            i += operator.length - 1
        } else {
            currentPart.append(char)
        }
        i++
    }

    parts.add(currentPart.toString())
    return parts
}

class BinaryCalculator {
    var textTagList = mutableStateListOf<Pair<String, String>>()

    private fun extractVariables(expression: String): Set<String> {
        val variables = mutableSetOf<String>()
        for (char in expression) {
            if (char.isLetter()) {
                variables.add(char.toString())
            }
        }
        return variables
    }

    private fun generateTruthTable(expression: LogicalExpression, variables: Set<String>): List<Map<String, Boolean>> {
        val variableList = variables.toList()
        val numRows = 1 shl variableList.size
        val trueRows = mutableListOf<Map<String, Boolean>>()

        for (i in 0 until numRows) {
            val assignment = mutableMapOf<String, Boolean>()
            for (j in variableList.indices) {
                assignment[variableList[j]] = (i and (1 shl j)) != 0
            }

            val result = expression.evaluate { assignment[it]!! }
            var tempStr = "";
            for (variable in variableList) {
                tempStr += "${if (assignment[variable] == true) 1 else 0} ";
            }
            tempStr += "| ${if (result) 1 else 0}"
            textTagList.add(Pair(tempStr, "table"))

            if (result) {
                trueRows.add(assignment)
            }
        }
        return trueRows
    }

    private fun combineAndSimplify(expressions: List<String>): List<String> {
        val mutableExpressions = expressions.toMutableList()
        while (true) {
            var found = false
            for (i in mutableExpressions.indices) {
                for (j in i + 1 until mutableExpressions.size) {
                    val combined = tryCombine(mutableExpressions[i], mutableExpressions[j])
                    if (combined != null) {
                        textTagList.add(Pair("Имеющиеся логические выражения:", "subtitle"))
                        mutableExpressions.forEach {
                            textTagList.add(Pair(it, "t"))
                        }
                        textTagList.add(Pair("Совмещаемые логические выражения:", "t"))
                        textTagList.add(Pair(" ${mutableExpressions[i]}", "t"))
                        textTagList.add(Pair(" ${mutableExpressions[j]}", "t"))
                        textTagList.add(Pair("Результат совмещения:", "subtitle"))
                        textTagList.add(Pair(" $combined", "subtitle"))


                        mutableExpressions[i] = combined
                        mutableExpressions.removeAt(j)
                        found = true
                        break
                    }
                }
                if (found) break
            }
            if (!found) break
        }
        return mutableExpressions
    }

    private fun tryCombine(expr1: String, expr2: String): String? {
        val parts1 = expr1.split(" ∧ ").toSet()
        val parts2 = expr2.split(" ∧ ").toSet()

        if (parts1.size != parts2.size) return null

        val difference = parts1.minus(parts2).union(parts2.minus(parts1))
        if (difference.size == 2 && difference.any { it.startsWith('¬') }) {
            val commonParts = parts1.intersect(parts2)
            return commonParts.joinToString(" ∧ ")
        }
        return null
    }

    fun calcFunction(logicalExpression: String) {
        textTagList.clear()
        textTagList.add(Pair("Изначальное логическое выражение: $logicalExpression", "title"))
        val expression = parseExpression(logicalExpression)

        val variables = extractVariables(logicalExpression)
        textTagList.add(Pair("\nШаг 1: Составляем таблицу истинности\n", "title"))
        var tempText = ""
        for(v in variables){
            tempText+= "$v "
        }
        textTagList.add(Pair(tempText, "table"))
        val trueRows = generateTruthTable(expression, variables)

        // Step 2: Convert trueRows to logical expressions
        val logicalExpressions = trueRows.map { row ->
            row.entries.joinToString(" ∧ ") { (variable, value) ->
                if (value) variable else "¬$variable"
            }
        }

        // Print logical expressions
        textTagList.add(Pair("\nШаг 2: Составляем по таблице истинности список логических выражений:\n", "title"))
        logicalExpressions.forEach {
            textTagList.add(Pair(it, "t"))
        }

        // Step 3: Combine and simplify expressions
        textTagList.add(Pair("\nШаг 3: Совмещаем друг с другом логические выражения через ∨ (ИЛИ), что бы убрать повторяющиеся переменные где это возможно:\n", "title"))
        val simplifiedExpressions = combineAndSimplify(logicalExpressions)

        // Print simplified expressions
        textTagList.add(Pair("\nСписок совмещенных логических выражений:\n", "title"))

        simplifiedExpressions.forEach {
            textTagList.add(Pair(it, "t"))
        }

        // Step 4: Combine simplified expressions into final expression
        val finalExpression = simplifiedExpressions.joinToString(" || ")

        textTagList.add(Pair("\nИтоговое логическое выражение:\n", "title"))
        textTagList.add(Pair(finalExpression, "t"))
    }
}