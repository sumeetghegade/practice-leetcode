package practice;

/*
INTUITION:
The straightforward way to do this is to perform an inorder traversal and get a list of sorted nodes.
Then use this list to create a doubly linked list. --> )(n) space and time

If we want to do inplace we will definitely have to do an inorder traversal but figure out a way to adjust the pointers.
We first keep going left:
When we reach the leaf node we need to save it somewhere and once we go back up the tree we need to adjust
the left and right pointers such that the
cur node will point to prev
prev will point to cur
prev will now be cur
then we traverse right

to return the head we will use a dummy node.
prev will first point to dummy node so when we get to the smallest value node, changing prevs right will dummys right to our smallest value node.

after we are done traversing, dummy will be pointing to first and prev will at end of linked list.
now we can make dummy.right and prev point to each other giving use the doubly liked list
 */

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {

    Node prev;
    public Node treeToDoublyList(Node root) {
        if(root == null)
            return null;
        Node dummy = new Node(0, null, null);
        prev = dummy;
        inorder(root);

        prev.right = dummy.right;
        dummy.right.left = prev;
        return dummy.right;


    }

    public void inorder(Node curNode) {

        if(curNode == null)
            return;

        inorder(curNode.left);
        curNode.left = prev;
        prev.right = curNode;
        prev = curNode;
        inorder(curNode.right);

    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static void main(String[] args) {
        ConvertBinarySearchTreeToSortedDoublyLinkedList obj = new ConvertBinarySearchTreeToSortedDoublyLinkedList();

    }
}
