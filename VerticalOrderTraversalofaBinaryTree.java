package practice;

import java.util.*;

/*
INTUITION:
We can simply perform a DFS on the tree while keeping track of our vertical level.
Considering the root at 0 vLevel: on left it will be -1, -2, ... and right will be 1, 2, ...
anytime we move left new vLevel will be prev vLevel-1 and right will be +1;
We can have a map with vLevel as key and corresponding nodes at values.

This will work but won't cover certain edge cases:
1: nodes at same coordinate need to be sorted by value. this won't happen in a normal dfs.
2. nodes on same vLevel but from different subtrees will mess up the order

Example:
      1
    /  \
   2    6
    \  /
     7 3
    / \
   4   5
       /
      8

 To handle this we need two things:
 1. Nodes on same vLevel are added to the list in sorted order of their y cordinate.
 2. Nodes on same vLevel and same hLevel will need to be added in sorted order of their value.

 To do all this instead of a simple HashMap we can use a DS like:
 TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>>

this represents: vLevel : {hLevel: [val1, val2,...], ...}

We can avoid the first TreeMap and have a simple HashMap as we know the order will go from min vLevel to max vLevel in
increments of 1. so no need to sort that. We just need to keep track of min and max vLevels.

Later we can iterate on this data struc to add all the resultant levels in a list in correct order.

O(n log(n))  ???
 */

public class VerticalOrderTraversalofaBinaryTree {

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

    HashMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new HashMap<>();
    int xMin = 0, xMax = 0;
    public List<List<Integer>> verticalTraversal(TreeNode root) {

        // perform DFS
        dfs(root, 0, 0);

        List<List<Integer>> result = new ArrayList<>();

        for(int i = xMin; i <= xMax; i++){
            List<Integer> vLevel = new ArrayList<>();
            for(int key: map.get(i).keySet()) {
                PriorityQueue<Integer> integers = map.get(i).get(key);
                while (!integers.isEmpty()) {
                    vLevel.add(integers.poll());
                }
            }
            result.add(vLevel);
        }
        return result;
    }

    public void dfs(TreeNode root, int x, int y) {
        // base condition
        if(root == null)
            return;
        // keep track of min and max vLevel
        xMin = Math.min(xMin, x);
        xMax = Math.max(xMax, x);

        // add key and value if not exist for vLevel
        if(map.get(x) == null)
            map.put(x, new TreeMap<Integer, PriorityQueue<Integer>>());
        // add key and value if not exist for hLevel
        if(map.get(x).get(y) == null)
            map.get(x).put(y, new PriorityQueue<Integer>());
        // add val to priority queue
        map.get(x).get(y).add(root.val);

        // dfs call
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }

    public static void main(String[] args) {
        VerticalOrderTraversalofaBinaryTree obj = new VerticalOrderTraversalofaBinaryTree();
        TreeNode node8 = obj.new TreeNode(8, null, null);
        TreeNode node5 = obj.new TreeNode(5, node8, null);
        TreeNode node4 = obj.new TreeNode(4, null, null);
        TreeNode node7 = obj.new TreeNode(7, node4, node5);
        TreeNode node2 = obj.new TreeNode(2, null, node7);
        TreeNode node3 = obj.new TreeNode(3, null, null);
        TreeNode node6 = obj.new TreeNode(6, node3, null);
        TreeNode root = obj.new TreeNode(1, node2, node6);

        System.out.println(obj.verticalTraversal(root));

    }
}
