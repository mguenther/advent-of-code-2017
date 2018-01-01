package net.mguenther.adventofcode.day25

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day25Solution {

    @Test
    fun should_print_solution_to_part_one() {

        val tm = TuringMachine()

        tm.addState("A", { value -> when (value) {
            0    -> Instruction(1, Right, "B")
            else -> Instruction(0, Right, "C")
        }})

        tm.addState("B", { value -> when (value) {
            0    -> Instruction(0, Left, "A")
            else -> Instruction(0, Right, "D")
        }})

        tm.addState("C", { value -> when (value) {
            0    -> Instruction(1, Right, "D")
            else -> Instruction(1, Right, "A")
        }})

        tm.addState("D", { value -> when (value) {
            0    -> Instruction(1, Left, "E")
            else -> Instruction(0, Left, "D")
        }})

        tm.addState("E", { value -> when (value) {
            0    -> Instruction(1, Right, "F")
            else -> Instruction(1, Left, "B")
        }})

        tm.addState("F", { value -> when (value) {
            0    -> Instruction(1, Right, "A")
            else -> Instruction(1, Right, "E")
        }})

        tm.tick(12368930)
        println(tm.checksum())
    }
}
