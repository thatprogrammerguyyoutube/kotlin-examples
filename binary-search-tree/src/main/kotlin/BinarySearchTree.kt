import java.util.LinkedList
import java.util.Queue

class BinarySearchTree<T: Comparable<T>> : Cloneable {
    data class Node<T>(var value: T, var left: Node<T>? = null, var right: Node<T>? = null)

    private var root: Node<T>? = null
    private var size: Long = 0

    fun isEmpty() : Boolean = root == null

    fun size() : Long = size

    fun contains(value: T) : Boolean {
        var cur = root
        while (cur != null) {
            val cmp = compareValues(value, cur.value)

            // Go left
            if (cmp < 0) {
                cur = cur.left
            }
            // Go right
            else if (cmp > 0) {
                cur = cur.right
            }
            // Found the value, so return true
            else {
                return true
            }
        }
        return false
    }

    fun add(value: T) : BinarySearchTree<T> {
        // Handle the empty tree case
        if (root == null) {
            root = Node(value)
            size += 1
            return this
        }

        // Find the insertion point
        var cur = root!!
        while (true) {
            val cmp = compareValues(value, cur.value)

            // Navigate down the left branch
            if (cmp < 0) {
                if (cur.left != null) {
                    cur = cur.left!!
                }
                else {
                    cur.left = Node<T>(value)
                    size += 1
                    return this
                }
            }
            // Navigate down the right branch
            else if (cmp > 0) {
                if (cur.right != null) {
                    cur = cur.right!!
                }
                else {
                    cur.right = Node<T>(value)
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

    fun remove(value: T) : BinarySearchTree<T> {
        var prev: Node<T>? = null
        var cur = root
        while (cur != null) {
            val cmp = compareValues(value, cur.value)

            if (cmp < 0) {
                prev = cur
                cur = cur.left
            }
            else if (cmp > 0) {
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
                    removeLeaf(prev, cur);
                }
                else if (numChildren == 1) {
                    removeSwapWithChild(cur);
                }
                else {
                    removeFindSuccessor(cur);
                }

                size -= 1
                return this
            }
        }
        return this
    }

    public override fun clone() : BinarySearchTree<T> {
        val newTree = BinarySearchTree<T>()
        val queue : Queue<Node<T>?> = LinkedList()
        queue.add(root)
        while (queue.isNotEmpty()) {
            val elem = queue.remove()
            if (elem != null) {
                newTree.add(elem.value)
                queue.add(elem.left)
                queue.add(elem.right)
            }
        }
        return newTree
    }

    private fun removeLeaf(parent: Node<T>?, child: Node<T>) {
        if (parent == null) {
            root = null;
        }
        else if (parent.left == child) {
            parent.left = null;
        }
        else {
            parent.right = null;
        }
    }

    private fun removeSwapWithChild(node: Node<T>) {
        if (node.left != null) {
            node.value = node.left!!.value
            node.right = node.left?.right
            node.left = node.left?.left
        } else {
            node.value = node.right!!.value
            node.left = node.right?.left
            node.right = node.right?.right
        }
    }

    private fun removeFindSuccessor(node: Node<T>) {
        var successorParent : Node<T> = node
        var successor = node.right!!
        while(successor.left != null) {
            successorParent = successor
            successor = successor.left!!
        }

        node.value = successor.value

        if (successor.right != null) {
            successorParent.left = successor.right
        }
        else if (successorParent != node) {
            successorParent.left = null
        }
        else {
            node.right = null
        }
    }
}