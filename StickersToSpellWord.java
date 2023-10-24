package practice;

import java.util.HashMap;
import java.util.Map;

public class StickersToSpellWord {

    public static int minStickers(String[] stickers, String target) {
        int numOfStickers = stickers.length;
        // i -> sticker, j -> char present:1 else 0
        int[][] stickerCharCountArr = new int[numOfStickers][26];

        // fill sticker character count
        for (int i = 0; i < numOfStickers; i++)
            for (char c:stickers[i].toCharArray()) stickerCharCountArr[i][c-'a']++;

        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return helper(dp, stickerCharCountArr, target);
    }
    private static int helper(Map<String, Integer> dp, int[][] stickerCharCountArr, String target) {
        if (dp.containsKey(target)) return dp.get(target);
        int ans = Integer.MAX_VALUE;
        int numOfStickers = stickerCharCountArr.length;
        int[] targetCharCount = new int[26];
        // fill target char count
        for (char c:target.toCharArray())
            targetCharCount[c-'a']++;

        // try every sticker
        for (int i = 0; i < numOfStickers; i++) {
            // optimization
            // The idea is to avoid duplicate paths
            // Consider these two paths:
            // with --> with --> example
            // with --> example --> with
            // both will give use the same answer. So how do we avoid traversing all such duplicates?
            // we can check if the sticker contains the first character in our current sorted target. At least one of them should contain it.
            // this way we avoid paths where stickers do not contain it. gives us some optimization.
            if (stickerCharCountArr[i][target.charAt(0)-'a'] == 0)
                continue;

            StringBuilder sb = new StringBuilder();

            // apply a sticker on every character a-z
            // sb after this will contain remaining characters from target after applying current sticker
            // this will also sort the reduced target sb.
            // sorting will help us to have a unique subproblem so we avoid duplication here
            // we do not sort the first string becuase it is already a unique problem. duplicates will happen in subproblems
            for (int j = 0; j < 26; j++) {
                if (targetCharCount[j] > 0 )
                    for (int k = 0; k < Math.max(0, targetCharCount[j]-stickerCharCountArr[i][j]); k++)
                        sb.append((char)('a'+j));
            }
            String s = sb.toString();
            int tmp = helper(dp, stickerCharCountArr, s);
            // if we found a valid path which gives us the target then tmp will not be -1
            // choose the min from this or previous calculated paths
            // we are adding one for current levels sticker that we used
            if (tmp != -1)
                ans = Math.min(ans, 1+tmp);
        }
        // after trying all stickers at that level if we get an answer we add that to the dp table
        dp.put(target, ans == Integer.MAX_VALUE? -1:ans);
        return dp.get(target);
    }

    public static void main(String[] args) {
        String [] sarr = new String[]{"with","example","science"};
        System.out.println(minStickers(sarr, "thez"));
    }
}
