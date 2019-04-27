package com.example.gabrieldiogo.routecalculations.nativetools

class Graph {

    val vertices : HashMap<String, Vertex> = HashMap()
    var vertexCount : Int = 0
    var edgeCount : Int = 0



    fun addVertex(pos : String, size: String, distance: Float) {

        if (vertices.containsKey(pos)) {

            print("If you want to add vertex " + { pos } + " you have to remove the current " + { size } + " first.");

        } else {

            vertices.put(pos, Vertex(pos, size, distance));
            vertexCount += 1;

        }

    }

    fun getVertex(pos: String): Vertex? {

        when(vertices.containsKey(pos)) {

            true -> return vertices[pos]
            false -> return null

        }

    }

//    fun

}