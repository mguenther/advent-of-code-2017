package net.mguenther.adventofcode.day4

import java.util.stream.Collectors

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

val PRIME_NUMBERS: Map<String, Long> = mapOf(
        Pair("a", 2L),
        Pair("b", 3L),
        Pair("c", 5L),
        Pair("d", 7L),
        Pair("e", 11L),
        Pair("f", 13L),
        Pair("g", 17L),
        Pair("h", 19L),
        Pair("i", 23L),
        Pair("j", 29L),
        Pair("k", 31L),
        Pair("l", 37L),
        Pair("m", 41L),
        Pair("n", 43L),
        Pair("o", 47L),
        Pair("p", 53L),
        Pair("q", 59L),
        Pair("r", 61L),
        Pair("s", 67L),
        Pair("t", 71L),
        Pair("u", 73L),
        Pair("v", 79L),
        Pair("w", 83L),
        Pair("x", 89L),
        Pair("y", 97L),
        Pair("z", 101L))

fun isPassphrase(phrase: String, mapperFn: (String) -> String = ::wordIdentity): Boolean {

    val numberOfWords = phrase.split(" ").size
    val numberOfIndividualWords = phrase.split(" ")
            .stream()
            .map(mapperFn::invoke)
            .distinct()
            .collect(Collectors.toList())
            .size

    return numberOfWords == numberOfIndividualWords
}

fun wordIdentity(word: String): String = word

fun primeProductForWord(word: String): String {
    return word
            .split("")
            .map { character -> PRIME_NUMBERS.getOrDefault(character, 1) }
            .reduceRight { l: Long, r: Long -> l * r }
            .toString()
}
