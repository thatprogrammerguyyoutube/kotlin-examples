package com.thatprogrammerguy

import java.util.*

class BinarySearchTree<T: Comparable<T>> : Cloneable {
    data class Node<T>(var value: T, var left: Node<T>? = null, var right: Node<T>? = null)

    private var root: Node<T>? = null
    private var size: Long = 0L

    // Running total implementation
    fun size() : Long = size

    // Calculation implementation
//    fun size() : Long {
//        var count = 0L
//        val queue : Queue<Node<T>?> = LinkedList()
//        queue.add(root)
//        while(!queue.isEmpty()) {
//            val elem = queue.remove()
//            if (elem != null) {
//                count += 1
//                queue.add(elem.left)
//                queue.add(elem.right)
//            }
//        }
//        return count
//    }

    fun isEmpty() : Boolean = root == null

    // No duplicate values allowed
    fun add(value: T) : BinarySearchTree<T> {
        if (isEmpty()) {
            root = Node(value)
            size = 1
            return this
        }
        var curNode = root!!
        while (true) {
            val comparison = value.compareTo(curNode.value)

            // Navigate left if we're less than
            if (comparison < 0) {
                if (curNode.left != null) {
                    curNode = curNode.left!!
                }
                else {
                    curNode.left = Node(value)
                    size += 1
                    return this
                }
            }
            // Navigate right if we're greater than
            else if (comparison > 0) {
                if (curNode.right != null) {
                    curNode = curNode.right!!
                }
                else {
                    curNode.right = Node(value)
                    size += 1
                    return this
                }
            }
            // Value already exists, so just return
            else {
                return this
            }
        }
    }

    // Duplicate values allowed
    // The test "testAddDedup" should fail when this version of add is enabled
//    fun add(value: T) : BinarySearchTree<T> {
//        if (isEmpty()) {
//            root = Node(value)
//            size = 1
//            return this
//        }
//        var curNode = root!!
//        while (true) {
//            // Navigate left if we're less than
//            if (value < curNode.value) {
//                if (curNode.left != null) {
//                    curNode = curNode.left!!
//                }
//                else {
//                    curNode.left = Node(value)
//                    size += 1
//                    return this
//                }
//            }
//            // Navigate right if we're greater than
//            else {
//                if (curNode.right != null) {
//                    curNode = curNode.right!!
//                }
//                else {
//                    curNode.right = Node(value)
//                    size += 1
//                    return this
//                }
//            }
//        }
//    }

    fun contains(value: T) : Boolean {
        var cur = root
        while (cur != null) {
            val comparison = value.compareTo(cur.value)

            // Go left if less than
            if (comparison < 0) {
                cur = cur.left
            }
            // Go right if greater than
            else if (comparison > 0) {
                cur = cur.right
            }
            // Found the value, so return true
            else {
                return true
            }
        }
        return false
    }

    fun remove(value: T) : BinarySearchTree<T> {
        var prev: Node<T>? = null
        var cur = root

        while (cur != null) {
            val comparison = value.compareTo(cur.value)

            // Go left
            if (comparison < 0) {
                prev = cur
                cur = cur.left
            }
            // Go right
            else if (comparison > 0) {
                prev = cur
                cur = cur.right
            }
            else {
                var numChildren = 0

                if (cur.left != null) {
                    numChildren += 1
                }
                if (cur.right != null) {
                    numChildren += 1
                }

                if (numChildren == 0) {
                    removeLeaf(prev, cur)
                }
                else if (numChildren == 1) {
                    removeRedirect(prev, cur)
                }
                else {
                    removeSuccessor(cur)
                }

                size -= 1
                return this
            }
        }
        return this
    }

    private fun removeSuccessor(removalNode: Node<T>) {
        var successorParent : Node<T> = removalNode
        var successor = removalNode.right!!

        while (successor.left != null) {
            successorParent = successor
            successor = successor.left!!
        }

        removalNode.value = successor.value

        // Update the left child of the parent
        if (successorParent != removalNode) {
            // Both the Successor and Successor Redirect strategies
            successorParent.left = successor.right
        }
        else {
            successorParent.right = successor.right
        }
    }

    private fun removeRedirect(parent: Node<T>?, removalNode: Node<T>) {
        val nextNode = if (removalNode.left != null) {
            removalNode.left
        }
        else {
            removalNode.right
        }

        if (parent == null) {
            root = nextNode
        }
        else if (parent.left == removalNode) {
            parent.left = nextNode
        }
        else {
            parent.right = nextNode
        }
    }

    private fun removeLeaf(parent: Node<T>?, removalNode: Node<T>) {
        if (parent == null) {
            root = null
        }
        else if (parent.left == removalNode) {
            parent.left = null
        }
        else {
            parent.right = null
        }
    }

    public override fun clone() : BinarySearchTree<T> {
        val newTree = BinarySearchTree<T>()
        val queue : Queue<Node<T>?> = LinkedList()
        queue.add(root)
        while(!queue.isEmpty()) {
            val elem = queue.remove()
            if (elem != null) {
                newTree.add(elem.value)
                queue.add(elem.left)
                queue.add(elem.right)
            }
        }
        return newTree
    }
}