import java.sql.Timestamp;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("mobilepay*benjamin"); strings.add("mobilepay*kåre"); strings.add("paypal"); strings.add("paypal"); strings.add("paypal"); strings.add("Spotify Premium"); strings.add("Spotify Premium"); strings.add("Spotify Premium"); strings.add("løn-01"); strings.add("løn-02"); strings.add("løn-03");
        //Collections.shuffle(strings);

        /*
        long startTime = System.nanoTime();
        groupStrings(strings, 4);
        System.out.println("Time: " + (System.nanoTime() - startTime));

        long startTime2 = System.nanoTime();
        groupStrings2(strings, 4);
        System.out.println("Time: " + (System.nanoTime() - startTime2));
        */

        System.out.println(groupStrings2(strings, 4));

    }

    public static List<List<String>> groupStrings(List<String> stringsToBeGrouped, int editDistanceThreshold){
        List<List<String>> groupedStrings = new ArrayList<>();

        while (!stringsToBeGrouped.isEmpty()){
            List<String> currentGroup = new ArrayList<>();
            String current = stringsToBeGrouped.get(0);
            currentGroup.add(current);
            stringsToBeGrouped.remove(0);

            for (int i = 0; i < stringsToBeGrouped.size(); i++){
                String next = stringsToBeGrouped.get(i);

                if (editDistance(current, next, currentGroup.get(0).length(), next.length()) <= editDistanceThreshold){
                    currentGroup.add(next);
                    stringsToBeGrouped.remove(i);
                    i--;
                }
                else {
                    break;
                }
            }

            groupedStrings.add(currentGroup);
        }

        return groupedStrings;
    }

    public static List<List<String>> groupStrings2(List<String> stringsToBeGrouped, int editDistanceThreshold) {
        HashMap<String, List<String>> groupedStrings = new HashMap<>();

        for (String current : stringsToBeGrouped) {
            boolean grouped = false;
            for (HashMap.Entry<String, List<String>> entry : groupedStrings.entrySet()) {
                String key = entry.getKey();
                if (editDistance(current, key, current.length(), key.length()) <= editDistanceThreshold) {
                    entry.getValue().add(current);
                    grouped = true;
                    break;
                }
            }

            if (!grouped) {
                List<String> newGroup = new ArrayList<>();
                newGroup.add(current);
                groupedStrings.put(current, newGroup);
            }
        }

        return new ArrayList<>(groupedStrings.values());
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