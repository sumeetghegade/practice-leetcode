package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



class CloneGraph {
    public Node cloneGraph(Node node) {
        HashMap<Node, Node> adj = new HashMap<>();
        return dfs(node, adj);
    }

    public Node dfs(Node node, HashMap<Node, Node> adj) {
        if(node == null)
            return null;

        if(adj.containsKey(node))
            return adj.get(node);
        Node copy = new Node(node.val);
        adj.put(node, copy);

        for(Node neighbor: node.neighbors) {
            copy.neighbors.add(dfs(neighbor, adj));
        }
        return copy;
    }

    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}
