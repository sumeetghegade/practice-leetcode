package practice;



/*
We need to traverse each branch to each leaf node.
So dfs will be the best traversal choice.

We can traverse to each node while increasing the factor to multiply each node value with by 10 as we go deeper
into the tree.

When we reach the leaf we can return sum calculated till that point.
Also while going back up the tree, at each point we need to sum the result we got from left and right subtree and return that sum.

We need to come up with a base condition to return appropriate values.
return null if node == null. this will take care of cases when only one of the left or right child does not exist

 */
public class SumRootToLeafNumbers {

    public int sumNumbers(TreeNode root) {
        return sumNodes(root, 0);
    }

    private int sumNodes(TreeNode root, int currentSum) {
        // to return zero when only one of left or right child does not exist
        if (root == null)
            return 0;
        // calculate sum at current level
        currentSum = currentSum * 10 + root.val;
        // if leaf node then return current sum value
        if (root.left == null && root.right == null)
            return currentSum;
        // recursive call on left subtree
        int leftSum = sumNodes(root.left, currentSum);
        //recursive call on right subtree
        int rightSum = sumNodes(root.right, currentSum);
        // return sum of left and right subtree as we go back up the call stack
        return leftSum + rightSum;
    }


    //Definition for a binary tree node.
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
