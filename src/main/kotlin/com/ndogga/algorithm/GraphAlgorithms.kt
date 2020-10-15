package com.ndogga.algorithm

import com.ndogga.struct.*
import java.math.BigInteger


/**
 *  @author Nidhal Dogga
 *  @since 01/10/2020 14:20
 *  @copyright All rights reserved. Nah just kidding
 */


object GraphAlgorithms {

    fun computeDominantSet(graph: GeometricGraph, edgeThreshold: Int): GeometricGraph {
        val bigIntegerAdjacencyMatrix = graph
                .adjacencyMatrix(edgeThreshold)
                .map { row -> BigInteger(row.joinToString(String()), 2) }
        val dominantSet = mutableListOf<Int>()
        var mask = BigInteger.ZERO
        val target = bigIntegerAdjacencyMatrix
                .filter { bi -> bi > BigInteger.ZERO }
                .size
        while (mask.countActiveBits != target) {
            val kimK = bigIntegerAdjacencyMatrix
                    .indices
                    .maxByOrNull { index -> mask.or(bigIntegerAdjacencyMatrix[index]).countActiveBits }
                    ?: throw IllegalStateException()
            dominantSet.add(kimK)
            mask = mask.or(bigIntegerAdjacencyMatrix[kimK])
        }
        return dominantSet
                .apply {
                    addAll(
                            bigIntegerAdjacencyMatrix
                                    .indices
                                    .filter { index -> bigIntegerAdjacencyMatrix[index] == BigInteger.ZERO }
                    )
                }
                .map { index -> graph[index] }
                as GeometricGraph
    }

}
