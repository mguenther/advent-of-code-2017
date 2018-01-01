package net.mguenther.adventofcode.day8

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

class Interpreter {

    private val registers: MutableMap<String, Int> = mutableMapOf()
    private var highestValueEver: Int = 0

    fun run(program: String): Pair<Map<String, Int>, Int> {
        reset()
        val parser = Parser(program)
        for (instruction in parser.parse()) {
            when (instruction) {
                is IncrementStatement -> if (evaluate(instruction.onCondition)) evaluate(instruction)
                is DecrementStatement -> if (evaluate(instruction.onCondition)) evaluate(instruction)
                else -> throw InterpreterException("Invalid instruction.")
            }
            val highestValueCurrently = registers.values.max() ?: 0
            if (highestValueCurrently > highestValueEver) highestValueEver = highestValueCurrently
        }
        return Pair(registers, highestValueEver)
    }

    private fun evaluate(instruction: ConditionalStatement): Boolean {

        print("Evaluating conditional statement " + instruction + ".")

        val currentRegisterValue = registers.getOrDefault(instruction.register, 0)

        when (instruction.operator) {
            ">=" -> return currentRegisterValue!! >= instruction.expectedValue
            ">"  -> return currentRegisterValue!! > instruction.expectedValue
            "<=" -> return currentRegisterValue!! <= instruction.expectedValue
            "<"  -> return currentRegisterValue!! < instruction.expectedValue
            "==" -> return currentRegisterValue!! == instruction.expectedValue
            "!=" -> return currentRegisterValue!! != instruction.expectedValue
            else -> throw InterpreterException("Invalid comparison operator.")
        }
    }

    private fun evaluate(instruction: IncrementStatement) {
        println("Evaluating increment statement " + instruction + ".")
        val currentRegisterValue = registers.getOrDefault(instruction.register, 0)
        registers.put(instruction.register, currentRegisterValue + instruction.by)
    }

    private fun evaluate(instruction: DecrementStatement) {
        println("Evaluating decrement statement " + instruction + ".")
        val currentRegisterValue = registers.getOrDefault(instruction.register, 0)
        registers.put(instruction.register, currentRegisterValue - instruction.by)
    }

    private fun reset() {
        registers.clear()
        highestValueEver = 0
    }
}

class InterpreterException(message: String) : RuntimeException(message)
