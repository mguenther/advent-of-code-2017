package net.mguenther.adventofcode.day6

import java.util.*

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
fun indexOfHighest(memoryBanks: IntArray): Int {
    var index = 0
    for (i in 1 until memoryBanks.size) {
        if (memoryBanks[i] > memoryBanks[index]) index = i
    }
    return index
}

fun redistribute(memoryBanks: IntArray) {
    val fromIndex = indexOfHighest(memoryBanks)
    var blocks = memoryBanks[fromIndex]
    var nextIndex = (fromIndex + 1).rem(memoryBanks.size)
    memoryBanks[fromIndex] = 0
    while (blocks > 0) {
        memoryBanks[nextIndex]++
        blocks--
        nextIndex = (nextIndex + 1).rem(memoryBanks.size)
    }
}

fun redistributions(memoryBanks: IntArray): Int {
    val knownDistributions = hashSetOf<String>()
    var redistributionCycles = 0
    var lastDistribution: String = distributionLiteral(memoryBanks)
    while (true) {
        redistributionCycles++
        knownDistributions.add(lastDistribution)
        redistribute(memoryBanks)
        lastDistribution = distributionLiteral(memoryBanks)
        if (knownDistributions.contains(lastDistribution)) break
    }
    return redistributionCycles
}

fun redistributionsInLoop(memoryBanks: IntArray): Int {
    val knownDistributions = hashSetOf<String>()
    var lastDistribution: String = distributionLiteral(memoryBanks)
    while (true) {
        knownDistributions.add(lastDistribution)
        redistribute(memoryBanks)
        lastDistribution = distributionLiteral(memoryBanks)
        if (knownDistributions.contains(lastDistribution)) break
    }
    var redistributionsInLoop = 0
    // lastDistribution contains the distribution literal that caused the cycle
    val cycleLiteral = lastDistribution
    while (true) {
        redistributionsInLoop++
        redistribute(memoryBanks)
        if (distributionLiteral(memoryBanks).equals(cycleLiteral)) break
    }
    return redistributionsInLoop
}

fun distributionLiteral(memoryBanks: IntArray): String = Arrays.toString(memoryBanks)
