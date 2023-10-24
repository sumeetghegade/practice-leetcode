package practice.OA_Evan;

public class Q1 {
    public static int countLexicographicallySmallerReversals(String inputStr, int k) {
        int count = 0;
        int n = inputStr.length();

        for (int i = 0; i <= n - k; i++) {
            String substring = inputStr.substring(i, i + k);
            String reversedSubstring = new StringBuilder(substring).reverse().toString();

            if (reversedSubstring.compareTo(substring) < 0) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String inputStr = "aaaaa";
        int k = 4;
        int result = countLexicographicallySmallerReversals(inputStr, k);
        System.out.println(result); // This will print the count of valid reversals
    }
}