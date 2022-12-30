package com.thatprogrammerguy

fun main(args: Array<String>) {
    val bst = BinarySearchTree<Int>()

    bst.add(4)
    bst.add(2)
    bst.add(5)
    bst.add(3)

    System.out.println("5: " + bst.contains(5))
    System.out.println("4: " + bst.contains(4))
    System.out.println("3: " + bst.contains(3))
    System.out.println("2: " + bst.contains(2))
    System.out.println("1: " + bst.contains(1))
}