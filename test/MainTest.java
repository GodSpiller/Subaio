import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainTest {

    @Test
    public void test01() {
        //arrange
        List<String> input = new ArrayList<>();
        List<List<String>> expectedResults = new ArrayList<>();
        input.add("mobilepay*benjamin"); input.add("mobilepay*kåre"); input.add("paypal"); input.add("paypal"); input.add("paypal"); input.add("Spotify Premium"); input.add("Spotify Premium"); input.add("Spotify Premium"); input.add("løn-01"); input.add("løn-02"); input.add("løn-03");
        List<String> grp1 = new ArrayList<>(); grp1.add("mobilepay*benjamin");
        List<String> grp2 = new ArrayList<>(); grp2.add("mobilepay*kåre");
        List<String> grp3 = new ArrayList<>(); grp3.add("paypal"); grp3.add("paypal"); grp3.add("paypal");
        List<String> grp4 = new ArrayList<>(); grp4.add("Spotify Premium"); grp4.add("Spotify Premium"); grp4.add("Spotify Premium");
        List<String> grp5 = new ArrayList<>(); grp5.add("løn-01"); grp5.add("løn-02"); grp5.add("løn-03");
        expectedResults.add(grp1); expectedResults.add(grp2); expectedResults.add(grp3); expectedResults.add(grp4); expectedResults.add(grp5);

        //act
        List<List<String>> results = Main.groupStrings(input, 4);

        //assert
        assert results.equals(expectedResults);
    }

    @Test
    public void test02(){
        //arrange
        List<String> input = new ArrayList<>();
        List<List<String>> expectedResults = new ArrayList<>();
        input.add("mobilepay*benjamin"); input.add("mobilepay*kåre"); input.add("paypal"); input.add("paypal"); input.add("paypal"); input.add("Spotify Premium"); input.add("Spotify Premium"); input.add("Spotify Premium"); input.add("løn-01"); input.add("løn-02"); input.add("løn-03");
        Collections.shuffle(input);
        List<String> grp1 = new ArrayList<>(); grp1.add("mobilepay*benjamin");
        List<String> grp2 = new ArrayList<>(); grp2.add("mobilepay*kåre");
        List<String> grp3 = new ArrayList<>(); grp3.add("paypal"); grp3.add("paypal"); grp3.add("paypal");
        List<String> grp4 = new ArrayList<>(); grp4.add("Spotify Premium"); grp4.add("Spotify Premium"); grp4.add("Spotify Premium");
        List<String> grp5 = new ArrayList<>(); grp5.add("løn-01"); grp5.add("løn-02"); grp5.add("løn-03");
        expectedResults.add(grp1); expectedResults.add(grp2); expectedResults.add(grp3); expectedResults.add(grp4); expectedResults.add(grp5);

        //act
        List<List<String>> results = Main.groupStrings(input, 4);

        //assert
        assert results.equals(expectedResults);
    }
}
