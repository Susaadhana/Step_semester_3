import java.util.*;

public class MergingTwoTokenQueues {
    static ArrayList<Integer> mergeTokens(int[] counterA, int[] counterB) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = 0;
        int j = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                result.add(counterA[i]);
                i++;
            } else {
                result.add(counterB[j]);
                j++;
            }
        }

        while (i < counterA.length) {
            result.add(counterA[i]);
            i++;
        }

        while (j < counterB.length) {
            result.add(counterB[j]);
            j++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] counterA = {3, 8, 15, 20};
        int[] counterB = {5, 8, 12};

        System.out.println(mergeTokens(counterA, counterB));
        System.out.println(mergeTokens(new int[]{}, new int[]{4, 9}));
    }
}