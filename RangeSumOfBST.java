package practice;

public class RangeSumOfBST {

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

     // TC: O(n) [DFS]
    public int rangeSumBST(TreeNode root, int low, int high) {

        // base condition
        // return if leaf node
        if(root == null) return 0;
        int sum = 0;

        // if current node value valid, add to result
        if(root.val <= high && root.val >= low)
            sum += root.val;

        // no need to check left subtree if current value is less than low.
        // no need for less than equal because all node values are unique
        if(root.val > low)
            sum += rangeSumBST(root.left, low, high);
        // no need to check right subtree if current value is greater than high
        // no need for less than equal because all node values are unique
        if(root.val < high)
            sum += rangeSumBST(root.right, low, high);

        return sum;
    }

}
