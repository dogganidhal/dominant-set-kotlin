package com.ndogga.io

import java.awt.Point
import java.io.*


/**
 *  @author Nidhal Dogga
 *  @since 01/10/2020 14:21
 *  @copyright All rights reserved. Nah just kidding
 */


object IOUtils {

    private fun saveToFile(fileName: String, result: ArrayList<Point>) {
        var index = 0
        try {
            while (true) {
                val input = BufferedReader(InputStreamReader(FileInputStream("$fileName$index.points")))
                try {
                    input.close()
                } catch (e: IOException) {
                    System.err.println("I/O exception: unable to close $fileName$index.points")
                }
                index++
            }
        } catch (e: FileNotFoundException) {
            printToFile("$fileName$index.points", result)
        }
    }

    private fun printToFile(fileName: String, points: ArrayList<Point>) {
        try {
            val output = PrintStream(FileOutputStream(fileName))
            for (p in points) output.println("${p.getX().toInt()} ${p.getY().toInt()}")
            output.close()
        } catch (e: FileNotFoundException) {
            System.err.println("I/O exception: unable to create $fileName")
        }
    }

    private fun readFromFile(fileName: String): ArrayList<Point> {
        var line: String
        var coordinates: Array<String>
        val points = ArrayList<Point>()
        try {
            val input = BufferedReader(
                    InputStreamReader(FileInputStream(fileName))
            )
            try {
                while (input.readLine().also { line = it } != null) {
                    coordinates = line.split("\\s+").toTypedArray()
                    points.add(Point(coordinates[0].toInt(), coordinates[1].toInt()))
                }
            } catch (e: IOException) {
                System.err.println("Exception: interrupted I/O.")
            } finally {
                try {
                    input.close()
                } catch (e: IOException) {
                    System.err.println("I/O exception: unable to close $fileName")
                }
            }
        } catch (e: FileNotFoundException) {
            System.err.println("Input file not found.")
        }
        return points
    }

}