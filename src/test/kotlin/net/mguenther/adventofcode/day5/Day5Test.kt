package net.mguenther.adventofcode.day5

import net.mguenther.adventofcode.day5.maze
import net.mguenther.adventofcode.day5.strangerMaze
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class Day5Test {

    @Test
    fun maze_should_yield_solution_for_minimal_example() {
        assertThat(maze(intArrayOf(0, 3, 0, 1, -3)), `is`(5))
    }

    @Test
    fun strangerMaze_should_yield_solution_for_minimal_example() {
        assertThat(strangerMaze(intArrayOf(0, 3, 0, 1, -3)), `is`(10))
    }
}