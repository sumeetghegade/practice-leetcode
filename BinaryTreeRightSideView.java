package practice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
INTUITION:
The problem will be when some of the subtrees on the left will have depth greater than the right most subtree.

We can easily solve this porblem by perfomring a BFS and adding the last node on each level to our result

 */

public class BinaryTreeRightSideView {

    int max = 0;
    public List<Integer> rightSideView(TreeNodes root) {

        List<Integer> result = new ArrayList<>();
        if(root == null)
            return result;

        Queue<TreeNodes> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int qsize = queue.size();
            for(int i = 0; i < qsize; i++) {
                TreeNodes curNode = queue.poll();

                // add all children to queue
                if(curNode.left != null)
                    queue.add(curNode.left);
                if(curNode.right != null)
                    queue.add(curNode.right);
                // add last nodes value to result list
                if(i == qsize - 1)
                    result.add(curNode.val);
            }
        }

        return result;
    }

    public class TreeNodes {
        int val;
        TreeNodes left;
        TreeNodes right;
        TreeNodes() {}
        TreeNodes(int val) { this.val = val; }
        TreeNodes(int val, TreeNodes left, TreeNodes right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
