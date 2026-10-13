public class ClassTopperFinder {
    static void findTopper(int[][] marks) {
        int maxTotal = -1;
        int topper = 0;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;

            for (int j = 0; j < marks[i].length; j++) {
                total += marks[i][j];
            }

            if (total > maxTotal) {
                maxTotal = total;
                topper = i;
            }
        }

        System.out.println("(" + topper + ", " + maxTotal + ")");
    }

    public static void main(String[] args) {
        int[][] marks = {
                {78, 85, 90},
                {88, 92, 79},
                {65, 70, 95}
        };

        findTopper(marks);
    }
}