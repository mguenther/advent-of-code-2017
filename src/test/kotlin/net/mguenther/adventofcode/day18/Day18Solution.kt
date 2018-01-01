package net.mguenther.adventofcode.day18

import org.junit.Test
import java.util.concurrent.LinkedBlockingDeque

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day18Solution {

    @Test
    fun should_print_solution_for_part_one() {
        val instructions = instructionsFromResource("/day_18.input")
        val duet = PartOne()
        println(duet.execute(instructions))
    }

    @Test
    fun should_print_solution_for_part_two() {
        val instructions = instructionsFromResource("/day_18.input")

        val program0Receive = LinkedBlockingDeque<Long>()
        val program1Receive = LinkedBlockingDeque<Long>()

        PartTwo(0, program1Receive, program0Receive).execute(instructions)
        println(PartTwo(1, program0Receive, program1Receive).execute(instructions).get())
    }
}