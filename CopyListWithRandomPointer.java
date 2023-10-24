package practice;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {

    /*
    INTUITION:
    First we just create a copy of the nodes without processing next and random.
    Add the original node and copy node as key value in a map.
    then iterate on the map and for every entry
    set next and random of current entry value to the value of keys next and random .
     */
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();

        Node dummyNode = head;

        while(dummyNode != null) {
            map.put(dummyNode, new Node(dummyNode.val));
            dummyNode = dummyNode.next;
        }

        dummyNode = head;
        for(Map.Entry<Node, Node> entry: map.entrySet()) {
            entry.getValue().next = map.get(entry.getKey().next);
            entry.getValue().random = map.get(entry.getKey().random);
        }

        return map.get(head);
    }


    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


}
