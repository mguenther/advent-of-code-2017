package net.mguenther.adventofcode.day25

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class TuringMachine {

    private val states: MutableMap<String, (Int) -> Instruction> = mutableMapOf()
    private val slots: MutableMap<Int, Int> = mutableMapOf()
    private var cursor = 0
    private var currentState: String? = null

    fun addState(state: String, tickFn: (currentValue: Int) -> Instruction, isDefault: Boolean = false) {
        if (states.isEmpty() || isDefault) {
            currentState = state
        }
        states.put(state, tickFn)
    }

    fun checksum(): Int = slots.values.sum()

    fun tick(times: Int = 1) = repeat(times, { i -> tick() })

    private fun tick() {

        val currentValue = slots.getOrDefault(cursor, 0)
        val instruction = states.get(currentState)!!.invoke(currentValue)

        slots.put(cursor, instruction.value)

        when (instruction.move) {
            is Left -> cursor--
            is Right -> cursor ++
        }

        if (!states.containsKey(instruction.successor)) {
            throw IllegalStateException("State $instruction.successor does not exist.")
        }

        currentState = instruction.successor
    }
}

data class Instruction(val value: Int, val move: Direction, val successor: String)

sealed class Direction

object Left : Direction()
object Right : Direction()
