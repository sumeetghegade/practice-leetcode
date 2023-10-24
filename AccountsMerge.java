package practice;

import java.util.*;

/*
Brute Force:
For brute force we can may be check each account with every other account and find if they have any common emails.
But it will be too expensive and won't be the optimal approach. We can look at it as a graph problem.
A disjoint set or union find problem to be specific.

We can do this using UnionFind because we want to find accounts with same owner based on the given condition and put them all in
a single group.
We will have a standard Union find data structure. So a parent array in which each index represents corresponding account in our input list.
0 -> 0, 1 -> 1, ...
Each will be connected to itself at first.

We can then map each mail to the person it belongs to. Instead of using names lets use the index here as well.
If at any point we see an email already in the map, means it belongs to multiple accounts of same person.
So in this case we will do union(map.get(email) [prev recorded index], currentindex);
So now in our parent array in UF, we will have same people with multiple accounts connected to each other.

Now we can iterate on all accounts in the input and create disjoint sets.
This can be a map with key as the parent of that index in parent array and value will be a list of emails.
We can use a treeset instead of list so that we have the emails in sorted order already.
MAP { INDEX: [EMAIL1, EMAIL2, ...], ...} INDEX will be parent of tree always as we'll run find on each account index.

Now we can just construct our final result list of list from this. We just need to add the person name at 0th index and then the entire set.
 */

public class AccountsMerge {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        if(accounts.size() == 0)
            return new ArrayList<>();

        int numAccounts = accounts.size();
        UnionFind uf = new UnionFind(numAccounts);

        Map<String, Integer> mailIndex = new HashMap<>();

        for(int i = 0; i < numAccounts; i++) {
            for(int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if(mailIndex.containsKey(mail)) {
                    int prev = mailIndex.get(mail);
                    uf.union(prev, i);
                } else {
                    mailIndex.put(mail, i);
                }
            }
        }

        Map<Integer, TreeSet<String>> disjointSet = new HashMap<>();
        for (int i = 0; i < numAccounts; i++) {
            // find parent index of current list index in parent array
            int parentIndex = uf.find(i);
            disjointSet.putIfAbsent(parentIndex, new TreeSet<String>());

            TreeSet<String> curSet = disjointSet.get(parentIndex);
            for (int j = 1; j < accounts.get(i).size(); j++) {
                curSet.add(accounts.get(i).get(j));
            }
            disjointSet.put(parentIndex, curSet);
        }

        List<List<String>> result = new ArrayList<>();
        for (int index : disjointSet.keySet()) {
            List<String> curList = new ArrayList<>();
            curList.add(accounts.get(index).get(0));
            curList.addAll(disjointSet.get(index));
            result.add(curList);
        }
        return result;

    }


    class UnionFind {
        int size;
        int[] parent;
        int[] rank;

        public UnionFind(int size) {
            this.size = size;
            this.parent = new int[size];
            this.rank = new int[size];
            for(int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public void union(int a, int b) {
            int aRoot = find(a);
            int bRoot = find(b);

            if(aRoot == bRoot)
                return;

            if(rank[aRoot] < rank[bRoot])
                parent[aRoot] = bRoot;

            else if(rank[aRoot] > rank[bRoot])
                parent[bRoot] = aRoot;
            else{
                parent[aRoot] = parent[bRoot];
                rank[bRoot] = rank[bRoot] + 1;
            }
        }

        public int find(int x) {
            if(x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }


    }


    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();

        List<String> account1 = new ArrayList<>();
        account1.add("A");
        account1.add("A1@fb.com");
        account1.add("A3@fb.com");
        accounts.add(account1);

        List<String> account2 = new ArrayList<>();
        account2.add("A");
        account2.add("A2@fb.com");
        account2.add("A3@fb.com");
        accounts.add(account2);

        List<String> account3 = new ArrayList<>();
        account3.add("B");
        account3.add("B1@mail.com");
        accounts.add(account3);

        List<String> account4 = new ArrayList<>();
        account4.add("A");
        account4.add("A4@mail.com");
        accounts.add(account4);

        AccountsMerge obj = new AccountsMerge();
        System.out.println(obj.accountsMerge(accounts));
    }

}


