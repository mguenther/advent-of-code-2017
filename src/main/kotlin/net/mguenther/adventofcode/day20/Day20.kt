package net.mguenther.adventofcode.day20

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */

data class Vector3(val x: Long, val y: Long, val z: Long) {

    fun distance(): Long = Math.abs(x) + Math.abs(y) + Math.abs(z)

    operator fun plus(that: Vector3): Vector3 = Vector3(this.x + that.x, this.y + that.y, this.z + that.z)
}

data class Particle(val id: Int, val position: Vector3, val velocity: Vector3, val acceleration: Vector3) {

    fun tick(): Particle = copy(velocity = velocity + acceleration, position = position + velocity + acceleration)
}

fun solvePartOne(particles: List<Particle>): Int {

    var ps = particles.map { it.tick() }

    for (i in 0 .. 1000) {
        ps = ps.map { it.tick() }
    }

    return ps.minBy { it.position.distance() }?.id!!
}

fun solvePartTwo(particles: List<Particle>): Int {

    var ps = next(particles)

    for (i in 0 .. 1000) {
        ps = next(ps)
    }

    return ps.size
}

private fun next(particles: List<Particle>): List<Particle> = particles
        .map { it.tick() }
        .groupBy { it.position }
        .filterValues { it.size == 1 }
        .flatMap { it.value }