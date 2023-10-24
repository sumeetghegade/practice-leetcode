package practice;

import java.util.*;
/*
INTUITION:
Lets imagine the tree is on a x-y plane where root is at the origin.
all nodes are placed at some x-y coordinate. left of root is -ve x axis and right is +ve.
as we go down we have a positive y axis so that will be our depth.

Now at the end what I want is a mpa with vertical level as key and value will be all nodes at that vertical level.
The values should in sorted order of depth. I think BFS will take care of it for us so I'll go with BFS for tree traversal.
It will also make maintaining vertical level easier.
So the way to maintain vertical level we have have another queue to keep track of a nodes vertical level.
we will perform poll offer operations on both queues. one will store node another will strore its vertical level.

Now we need the list of nodes from left to right in our result. so we can sort the map on values and then add the corespoiding lists to our final result.
We can possibly use a treemap too.
That will increase our time complexity.
Instead we can keep track of min vlevel and max vlevel as we perform bfs . then we can iterate from minVlevel to maxvlevel on map and add
values from our map into final list in that order.

O(n)

*/

public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root == null)
            return result;

        // {vlevel : [node1, node2, ...] }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> vLevelQueue = new ArrayDeque<>();

        int minVLevel = 0;
        int maxVLevel = 0;

        nodeQueue.offer(root);
        vLevelQueue.offer(0);

        while(!nodeQueue.isEmpty()) {
            TreeNode cur = nodeQueue.poll();
            int curlevel = vLevelQueue.poll();

            minVLevel = Math.min(minVLevel, curlevel);
            maxVLevel = Math.max(maxVLevel, curlevel);

            if(!map.containsKey(curlevel))
                map.put(curlevel, new ArrayList<Integer>());

            map.get(curlevel).add(cur.val);

            if(cur.left != null) {
                nodeQueue.add(cur.left);
                vLevelQueue.add(curlevel - 1);
            }

            if(cur.right != null) {
                nodeQueue.add(cur.right);
                vLevelQueue.add(curlevel + 1);
            }
        }

        for(int i = minVLevel; i <= maxVLevel; i++) {
            result.add(map.get(i));
        }

        return result;
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

    public static void main(String[] args) {
        BinaryTreeVerticalOrderTraversal obj = new BinaryTreeVerticalOrderTraversal();
        BinaryTreeVerticalOrderTraversal.TreeNode node8 = obj.new TreeNode(8, null, null);
        BinaryTreeVerticalOrderTraversal.TreeNode node5 = obj.new TreeNode(5, node8, null);
        BinaryTreeVerticalOrderTraversal.TreeNode node4 = obj.new TreeNode(4, null, null);
        BinaryTreeVerticalOrderTraversal.TreeNode node7 = obj.new TreeNode(3, node4, node5);
        BinaryTreeVerticalOrderTraversal.TreeNode node2 = obj.new TreeNode(2, null, node7);
        BinaryTreeVerticalOrderTraversal.TreeNode node3 = obj.new TreeNode(7, null, null);
        BinaryTreeVerticalOrderTraversal.TreeNode node6 = obj.new TreeNode(6, node3, null);
        BinaryTreeVerticalOrderTraversal.TreeNode root = obj.new TreeNode(1, node2, node6);

        System.out.println(obj.verticalOrder(root));

    }
}
