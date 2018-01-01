package net.mguenther.adventofcode.day18

import java.util.concurrent.BlockingQueue
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class PartTwo(private val programId: Int,
              private val send: BlockingQueue<Long>,
              private val receive: BlockingQueue<Long>) {

    private val registers: MutableMap<Register, Long> = mutableMapOf()
    private var pc = 0
    private var sent = 0

    init {
        registers.put("p", programId.toLong())
    }

    fun execute(`is`: List<Instruction>): CompletableFuture<Int> =
            CompletableFuture.supplyAsync {
                while (pc < `is`.size && pc >= 0) {
                    execute(`is`.get(pc))
                }
                sent
            }

    private fun execute(i: Instruction) {
        var jumpBy = 1
        when (i.command) {
            "snd" -> {
                send.put(resolve(i.target))
                sent++
            }
            "set" -> resolve(i.param).let { registers.put(i.target, it) }
            "add" -> resolve(i.target).plus(resolve(i.param)).let { write(i.target, it) }
            "mul" -> resolve(i.target).times(resolve(i.param)).let { write(i.target, it) }
            "mod" -> resolve(i.target).rem(resolve(i.param)).let { write(i.target, it) }
            "rcv" -> {
                try {
                    write(i.target, receive.poll(1, TimeUnit.SECONDS))
                } catch (e: Exception) {
                    pc = POISON_POLL
                }
            }
            else -> if (resolve(i.target) > 0L) jumpBy = resolve(i.param).toInt()
        }
        pc += jumpBy
    }

    private fun write(r: Register, value: Long) = registers.put(r, value)
    private fun resolve(value: String): Long = if (isDigit(value)) value.toLong() else registers.getOrDefault(value, 0)
    private fun isDigit(p: String): Boolean = p.isNotEmpty() && p.toCharArray()[0].isDigit() || p.toCharArray()[0] == '-'
}

private val POISON_POLL = -2