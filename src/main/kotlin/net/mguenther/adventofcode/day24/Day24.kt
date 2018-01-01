package net.mguenther.adventofcode.day24

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 */
class ElectromagneticHost {

    private fun initialComponents(components: List<Component>) =
            components.filter { c -> c.hasZeroPin() }

    private fun candidates(components: List<Component>, tail: Component) =
            components.filter { component -> tail.isCompatible(component) }

    fun solve(components: List<Component>): Int {

        val zeroPinComponents = initialComponents(components)

        return zeroPinComponents
                .flatMap { zeroPinComponent -> solve(components.minus(zeroPinComponent), listOf(zeroPinComponent.assign(0))) }
                .groupBy { solution -> solution.size }
                .maxBy { c -> c.key }!!
                .value
                .map { solution -> strengthOf(solution) }
                .max() ?: 0
    }

    fun solve(components: List<Component>, prototype: Bridge): List<Bridge> {

        val candidates = candidates(components, prototype.takeLast(1).get(0))

        if (candidates.isEmpty()) {
            return listOf(prototype)
        }

        return candidates.flatMap { candidate -> solve(components.minus(candidate), prototype + candidate.assign(connectOn(prototype, candidate))) }
    }

    fun connectOn(bridge: List<Component>, component: Component): Port {
        val tail = bridge.takeLast(1).get(0)
        if (tail.free.contains(component.left)) return component.left
        else return component.right
    }

    private fun strengthOf(solution: Bridge): Int = solution.map { c -> c.strength() }.sum()

}

class Component(val left: Port, val right: Port, val free: List<Port> = listOf(left, right)) {

    fun assign(port: Port): Component {
        return Component(left, right, free.minus(port))
    }

    fun isCompatible(other: Component): Boolean {
        return free.contains(other.left) || free.contains(other.right)
    }

    fun strength(): Int = left + right

    fun hasZeroPin(): Boolean = left == 0 || right == 0

    override fun toString(): String {
        return "$left/$right"
    }
}

typealias Port = Int
typealias Bridge = List<Component>
