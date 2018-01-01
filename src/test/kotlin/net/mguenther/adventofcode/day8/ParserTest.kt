package net.mguenther.adventofcode.day8

import org.junit.Test

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class ParserTest {

    @Test
    fun test() {
        val program =
                "b inc 5 if a > 1\n" +
                "a inc 1 if b < 5\n" +
                "c dec -10 if a >= 1\n" +
                "c inc -20 if c == 10"
        val instructions = Parser(program).parse()
        instructions.forEach { i -> println(i) }
    }
}