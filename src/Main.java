import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> input1 = new ArrayList<>();
        input1.add("mobilepay*benjamin"); input1.add("mobilepay*kåre"); input1.add("paypal"); input1.add("paypal"); input1.add("paypal"); input1.add("Spotify Premium"); input1.add("Spotify Premium"); input1.add("Spotify Premium"); input1.add("løn-01"); input1.add("løn-02"); input1.add("løn-03");
        List<String> input2 = new ArrayList<>();
        input2.add("mobilepay*benjamin"); input2.add("mobilepay*kåre"); input2.add("paypal"); input2.add("paypal"); input2.add("paypal"); input2.add("Spotify Premium"); input2.add("Spotify Premium"); input2.add("Spotify Premium"); input2.add("løn-01"); input2.add("løn-02"); input2.add("løn-03"); input2.add("løn-010000");

        for (int i = 0; i <1000; i++){
            input2.add("paypal");
        }

        long startTime2 = System.nanoTime();
        groupStrings(input2, 4);
        System.out.println("Time: " + (System.nanoTime() - startTime2));

        //System.out.println(editDistance("løn-01", "løn-010000", 6, 10));
        //System.out.println(groupStrings2(input2, 4));

    }

    public static List<List<String>> groupStrings(List<String> stringsToBeGrouped, int editDistanceThreshold) {
        HashMap<String, List<String>> groupedStrings = new HashMap<>();

        for (String current : stringsToBeGrouped) {
            boolean grouped = false;
            for (HashMap.Entry<String, List<String>> entry : groupedStrings.entrySet()) {
                String key = entry.getKey();
                 if (editDistance(current, key, current.length(), key.length()) <= editDistanceThreshold) {
                     if (key.equals(current)) {
                         entry.getValue().add(current);
                         grouped = true;
                         break;
                     }
                    else if (belongsInGroup(current, entry.getValue(), editDistanceThreshold)) {
                        entry.getValue().add(current);
                        grouped = true;
                        break;
                    }
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

    static boolean belongsInGroup(String current, List<String> group, int editDistanceThreshold){
        for (String s : group){
            if (editDistance(current, s, current.length(), s.length()) > editDistanceThreshold){
                return false;
            }
        }
        return true;
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