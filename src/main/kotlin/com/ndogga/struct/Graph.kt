package com.ndogga.struct

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import java.awt.Point
import java.math.BigInteger


/**
 *  @author Nidhal Dogga
 *  @since 13/10/2020 15:36
 *  @copyright All rights reserved. Nah just kidding
 */


typealias Graph<T> = ArrayList<T>
typealias GeometricGraph = Graph<Point>
typealias AdjacencyMatrix = Array<ByteArray>


fun GeometricGraph.adjacencyMatrix(edgeThreshold: Int) : AdjacencyMatrix {
    val adjacencyMatrix = Array(size) { ByteArray(size) { 0 } }
    forEachIndexed { index, point ->
        this
                .forEachIndexed { i, p ->
                    val distance = p.distance(point)
                    val bit: Byte = when {
                        distance.compareTo(edgeThreshold) < 0 && distance.compareTo(0) > 0 -> 1
                        else -> 0
                    }
                    adjacencyMatrix[index][i] = bit
                }
    }
    return adjacencyMatrix
}


val BigInteger.countActiveBits: Int
    get() = toString(2).count { c -> c == '1' }


fun <A>Array<A>.forEachParallel(f: suspend (Int, A) -> Unit): Unit = runBlocking {
    mapIndexed { index, value ->
        async {
            f(index, value)
        }
    }
            .forEach {
                it.await()
            }
}



fun <T> BooleanArray.mapParallel(f: suspend (Int, Boolean) -> T): Collection<T> = runBlocking {
    mapIndexed { index, value ->
        async {
            f(index, value)
        }
    }.awaitAll()
}
