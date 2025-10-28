
public class BruteForceEx {
    static int[] array = {1,2,3};

    public static void bruteForce(int[] path, boolean[] used, int level) {
        if (level == 2) { // base case: scelti 2 numeri
            int sum = path[0] + path[1];
            System.out.println(path[0] + " + " + path[1] + " = " + sum);
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (!used[i]) {
                path[level] = array[i];  // scegli il numero
                used[i] = true;          // marca come usato
                bruteForce(path, used, level + 1); // ricorsione
                used[i] = false;         // backtrack
            }
        }
    }

    public static void main(String[] args) {
        int[] path = new int[2];          // array per memorizzare la combinazione
        boolean[] used = new boolean[array.length];
        System.out.println("Tutte le somme dei numeri sono:");
        bruteForce(path, used, 0);        // partire da level 0
    }
}

