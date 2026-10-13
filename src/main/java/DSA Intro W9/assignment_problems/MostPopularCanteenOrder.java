import java.util.*;

public class MostPopularCanteenOrder {
    static void mostPopular(String[] orders) {
        HashMap<String, Integer> frequency = new HashMap<>();

        for (String item : orders) {
            frequency.put(item, frequency.getOrDefault(item, 0) + 1);
        }

        String popular = orders[0];
        int maxCount = frequency.get(popular);

        for (String item : orders) {
            int count = frequency.get(item);

            if (count > maxCount) {
                popular = item;
                maxCount = count;
            }
        }

        System.out.println("(\"" + popular + "\", " + maxCount + ")");
    }

    public static void main(String[] args) {
        String[] orders1 = {
                "dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"
        };

        String[] orders2 = {
                "tea", "coffee", "coffee", "tea"
        };

        mostPopular(orders1);
        mostPopular(orders2);
    }
}