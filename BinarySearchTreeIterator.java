package practice;

import java.util.Stack;

/*
INTUITION:
The first thought is to perform a normal inorder traversal using recursive DFS and store all the nodes in an array or list.
But if the tree is huge this would be a problem as we will be storing a huge number of nodes in memory.

So instead of recursive DFS we can do iterative DFS and instead of add all nodes in one go
we can do it partially.
So we can first add all the nodes in the leftmost subtree to a stack.
then we keep popping when next() is called.
when popping if we see that the node we popped had a right node, we go to that node and again push all leftmost nodes to the stack.
we keep doing this
so at any point we will only have at most 'h' nodes in our stack.
also we will mostly get the next() result in o(1) except for some times.

hasNext will just be decided based on if we have anything in our stack.

 */

public class BinarySearchTreeIterator {


    Stack<TreeNode> stack;
    public BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    public int next() {
        TreeNode temp = stack.pop();
        pushAllLeft(temp.right);
        return temp.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void pushAllLeft(TreeNode start) {
        while(start != null) {
            stack.push(start);
            start = start.left;
        }
    }



    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
