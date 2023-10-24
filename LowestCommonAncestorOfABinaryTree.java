package practice;

public class LowestCommonAncestorOfABinaryTree {

    /*
    INTUITION:
    We can perform DFS exploring left and right subtree at each level
    anytime we see p or q we return that node else if we at end of branch then null
    at any point if we get a value other than null for both left and right subtree calls means we found our LCA
    O(n) -> dfs
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(root == null)
            return null;
        if(root == p || root == q)
            return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // the nodes can either be on different sides of the tree --> we'll receive non null from both left and right
        // or one can be child of another in which case we'll either recieve left or right and other will be null
        if(left != null && right != null)
            return root;
        else
            return left == null ? right : left;

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
