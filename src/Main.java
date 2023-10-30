import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("mobilepay*benjamin"); strings.add("mobilepay*kåre"); strings.add("paypal"); strings.add("paypal"); strings.add("paypal"); strings.add("Spotify Premium"); strings.add("Spotify Premium"); strings.add("Spotify Premium"); strings.add("løn-01"); strings.add("løn-02"); strings.add("løn-03");
        //Collections.shuffle(strings);

        System.out.println(groupStrings(strings,4));

    }

    public static List<List<String>> groupStrings(List<String> stringsToBeGrouped, int editDistanceThreshold){
        int iterations = 0;
        List<List<String>> groupedStrings = new ArrayList<>();
        while (!stringsToBeGrouped.isEmpty()){
            List<String> grouping = new ArrayList<>();
            grouping.add(stringsToBeGrouped.get(0));
            stringsToBeGrouped.remove(0);

            for (int i = 1; i < stringsToBeGrouped.size(); i++){
                String next = stringsToBeGrouped.get(i);
                if (editDistance(grouping.get(0), next, grouping.get(0).length(), next.length()) <= editDistanceThreshold){
                    grouping.add(next);
                    stringsToBeGrouped.remove(i);
                    i--;
                }

            }
            System.out.println(++iterations);
            groupedStrings.add(grouping);
        }

        return groupedStrings;
    }

    static int editDistance(String str1, String str2, int m, int n)
    {
        // Create a table to store results of subproblems
        int dp[][] = new int[m + 1][n + 1];

        // Fill d[][] in bottom up manner
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // If first string is empty, only option is
                // to insert all characters of second string
                if (i == 0)
                    dp[i][j] = j; // Min. operations = j

                    // If second string is empty, only option is
                    // to remove all characters of second string
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i

                    // If last characters are same, ignore last
                    // char and recur for remaining string
                else if (str1.charAt(i - 1)
                        == str2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];

                    // If the last character is different,
                    // consider all possibilities and find the
                    // minimum
                else
                    dp[i][j]
                            = 1
                            + min(
                            dp[i][j - 1], // Insert
                            dp[i - 1][j], // Remove
                            dp[i - 1][j - 1]); // Replace
            }
        }

        return dp[m][n];
    }


    public static int min(int x, int y, int z){

        if (x <= y && x <= z)
            return x;
        if (y <= x && y <= z)
            return y;
        else
            return z;
    }
}