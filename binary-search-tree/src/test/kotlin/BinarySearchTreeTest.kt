import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BinarySearchTreeTest  {
    @Test
    fun testAdd() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(2)
        bst.add(5)
        bst.add(3)

        assertEquals(bst.size(), 4)
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(4) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(2) }
        assertFalse { bst.contains(1) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testAddDedup() {
        val bst = BinarySearchTree<Int>()

        bst.add(3)
        bst.add(3)
        bst.add(3)
        bst.add(3)

        assertTrue { bst.contains(3) }
        assertFalse { bst.isEmpty() }

        bst.remove(3)

        assertFalse(bst.contains(3))
        assertTrue { bst.isEmpty() }
    }

    @Test
    fun removeNothingPopulated() {
        val bst = BinarySearchTree<Int>()

        bst.add(4).add(6).add(2).add(5).add(3)

        assertTrue { bst.contains(2) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(4) }
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(6) }
        assertFalse { bst.isEmpty() }

        bst.remove(13) 

        assertTrue { bst.contains(2) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(4) }
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(6) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun removeNothingEmpty() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.remove(13) 
        assertTrue { bst.isEmpty() }
    }

    @Test
    fun testRemoveRoot() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4).add(2).add(5).add(3).add(1)

        assertEquals(bst.size(), 5)
        bst.remove(4) 
        assertEquals(bst.size(), 4)
        assertTrue { bst.contains(5) }
        assertFalse { bst.contains(4) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(2) }
        assertTrue { bst.contains(1) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveLeafRight() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(2)
        bst.add(5)
        bst.add(3)
        bst.add(1)

        assertEquals(bst.size(), 5)
        bst.remove(5) 
        assertEquals(bst.size(), 4)
        assertFalse { bst.contains(5) }
        assertTrue { bst.contains(4) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(2) }
        assertTrue { bst.contains(1) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveLeafLeft() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(2)
        bst.add(5)
        bst.add(3)
        bst.add(1)

        assertEquals(bst.size(), 5)
        bst.remove(1) 
        assertEquals(bst.size(), 4)
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(4) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(2) }
        assertFalse { bst.contains(1) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveLeafRoot() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)

        assertFalse { bst.isEmpty() }
        bst.remove(4) 
        assertTrue { bst.isEmpty() }
    }

    @Test
    fun testRemoveOneChildLeft() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(6)
        bst.add(2)
        bst.add(5)
        bst.add(3)

        bst.remove(6) 
        assertFalse { bst.contains(6) }
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(4) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(2) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveOneChildRight() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(6)
        bst.add(2)
        bst.add(7)
        bst.add(3)

        bst.remove(6) 
        assertFalse { bst.contains(6) }
        assertTrue { bst.contains(7) }
        assertTrue { bst.contains(4) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(2) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveOneChildChain() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(2)
        bst.add(3)
        bst.add(4)
        bst.add(5)
        bst.add(6)

        bst.remove(3)
        assertTrue { bst.contains(6) }
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(4) }
        assertFalse { bst.contains(3) }
        assertTrue { bst.contains(2) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveTwoChildrenDirectSuccessor() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(7)
        bst.add(2)
        bst.add(5)
        bst.add(6)
        bst.add(3)
        bst.add(8)

        bst.remove(7) 
        assertTrue { bst.contains(4) }
        assertFalse { bst.contains(7) }
        assertTrue { bst.contains(2) }
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(6) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(8) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveTwoChildrenIndirectSuccessor() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(7)
        bst.add(2)
        bst.add(5)
        bst.add(6)
        bst.add(9)
        bst.add(3)
        bst.add(8)

        bst.remove(7) 
        assertTrue { bst.contains(4) }
        assertFalse { bst.contains(7) }
        assertTrue { bst.contains(2) }
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(6) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(8) }
        assertTrue { bst.contains(9) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testRemoveTwoChildrenIndirectSuccessorWithChild() {
        val bst = BinarySearchTree<Int>()
        assertTrue { bst.isEmpty() }

        bst.add(4)
        bst.add(7)
        bst.add(2)
        bst.add(5)
        bst.add(6)
        bst.add(10)
        bst.add(3)
        bst.add(8)
        bst.add(9)

        bst.remove(7) 
        assertTrue { bst.contains(4) }
        assertFalse { bst.contains(7) }
        assertTrue { bst.contains(2) }
        assertTrue { bst.contains(5) }
        assertTrue { bst.contains(6) }
        assertTrue { bst.contains(3) }
        assertTrue { bst.contains(8) }
        assertTrue { bst.contains(9) }
        assertTrue { bst.contains(10) }
        assertFalse { bst.isEmpty() }
    }

    @Test
    fun testCopy() {
        val bst1 = BinarySearchTree<Int>()
        bst1.add(4)
        bst1.add(7)
        bst1.add(2)
        bst1.add(5)
        bst1.add(6)
        bst1.add(10)
        bst1.add(3)
        bst1.add(8)
        bst1.add(9)

        val bst2 = bst1.clone()

        bst1.add(24)
        bst1.remove(10)
        bst2.add(12)
        bst2.remove(5)

        assertTrue { bst1.contains(24) }
        assertTrue { bst1.contains(5) }
        assertFalse { bst1.contains(12) }
        assertFalse { bst1.contains(10) }

        assertTrue { bst2.contains(12) }
        assertTrue { bst2.contains(10) }
        assertFalse { bst2.contains(24) }
        assertFalse { bst2.contains(5) }
    }
}