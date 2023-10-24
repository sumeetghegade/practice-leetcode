package practice;

import java.util.HashSet;

public class LowestCommonAncestorofaBinaryTreeIII {

    /*
    Approach 1:
    As each node also points to its parent,
    we can travers from one node to the root node and keep adding nodes to a set.
    We do the same on the other node and as soon as we find a node that is alos present in the set means we found the intersection.

    TC: O(h)
    SC: O(h)
     */
    public Node lowestCommonAncestor1(Node p, Node q) {
        HashSet<Node> set1 = new HashSet<>();

        Node temp = p;
        while(temp != null) {
            set1.add(temp);
            temp = temp.parent;
        }

        temp = q;
        while(temp != null) {
            if(set1.contains(temp))
                return temp;
            temp = temp.parent;
        }
        return null;
    }

    /*
    Approach 2:
    We can also consider this the same problem as finding the intersection of two linked lists.
    So keep going up the tree from both nodes. as soon as we see null we again start from the other node.
    When this traversal ends up at the same node, we have found the intersection i.e. lowest common ancestor.

    TC: O(h1 + h2)
    SC: O(1)
     */
    public Node lowestCommonAncestor2(Node p, Node q) {

        Node n1 = p, n2 = q;

        while(n1 != n2) {
            n1 = n1 == null ? q : n1.parent;
            n2 = n2 == null? p : n2.parent;
        }
        return n1;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    };
}
