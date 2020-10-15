@file:JvmName("DefaultTeam")

package algorithms

import com.ndogga.algorithm.GraphAlgorithms
import java.awt.Point
import kotlin.collections.ArrayList


/**
 *  @author Nidhal Dogga
 *  @since 01/10/2020 12:12
 *  @copyright All rights reserved. Nah just kidding
 */


class DefaultTeam {

    fun calculDominatingSet(graph: ArrayList<Point>, edgeThreshold: Int): ArrayList<Point> {
        return GraphAlgorithms
                .computeDominantSet(graph, edgeThreshold)
    }

}
