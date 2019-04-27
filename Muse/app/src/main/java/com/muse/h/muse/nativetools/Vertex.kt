package com.example.gabrieldiogo.routecalculations.nativetools

data class Vertex constructor(var pos: String, var size: String, var distance: Float) {

    var edges: ArrayList<Edge> = ArrayList()

}