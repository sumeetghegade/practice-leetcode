package practice;

/*
          1
      2       3
    4   5

INTUITION:

Brute force:
We can potentially go to each node. consider it the root and calculate the left and right subtree height for that node.
Keep track of max and return result. This will take o(n^2).
Here we are doing a lot of repetitive work.

Instead what we can also do is not start at the root but start from the leafs.
So we make a recursive call, each call will return the height of the tree passing through it
so leaf will return 0 and itsparent will return 1 and so on. we will also make sure to return the max of left and right subtree height.
And also while we are returning the height we can keep track of the max diameter by adding up left and right subtree height.
O(n)
 */


import com.sun.source.tree.Tree;

public class DiameterofBinaryTree {


    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        solve(root);
        return maxDiameter;
    }


    public int solve(TreeNode root) {
        if(root == null) {
            return 0;
        }

        int left = solve(root.left);
        int right = solve(root.right);

        maxDiameter = Math.max(maxDiameter, left + right);
        return Math.max(left, right) + 1;
    }

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


    public static void main(String[] args) {
        DiameterofBinaryTree sol = new DiameterofBinaryTree();
        TreeNode node6 = sol.new TreeNode(6, null, null);
        TreeNode node7 = sol.new TreeNode(7, null, null);
        TreeNode node4 = sol.new TreeNode(4, node6, null);
        TreeNode node5 = sol.new TreeNode(5, null, node7);
        TreeNode node2 = sol.new TreeNode(2, node4, node5);
        TreeNode node3 = sol.new TreeNode(3, null, null);
        TreeNode node1 = sol.new TreeNode(3, node2, node3);

        System.out.println(sol.diameterOfBinaryTree(node1));

    }
}


