import java.util.Scanner;
import java.util.Random;

class Esercizio {

    public static Scanner in = new Scanner(System.in);
    public static Random random = new Random();

    // Record per il massimo
    public static record Max(int massimo, int rIdx, int cIdx) {}

    // Trova il massimo nella matrice
    public static Max calcolaMassimo(int[][] M, int nR, int nC) {

        int maxVal = M[0][0];
        int posR = 0;
        int posC = 0;

        for (int r = 0; r < nR; r++) {
            for (int c = 0; c < nC; c++) {
                int corrente = M[r][c];
                if (corrente > maxVal) {
                    maxVal = corrente;
                    posR = r;
                    posC = c;
                }
            }
        }
        return new Max(maxVal, posR, posC);
    }

    // Media di una colonna
    public static float calcolaMedia(int[][] M, int nR, int colonna) {
        float accumulo = 0f;

        for (int r = 0; r < nR; r++) {
            accumulo += M[r][colonna];
        }

        return accumulo / nR;
    }

    // Riempi la matrice di valori casuali
    public static void riempiCasuale(int[][] M, int RIGHE, int COLONNE, int valMin, int valMax) {
        Random generatore = new Random();

        for (int r = 0; r < RIGHE; r++) {
            for (int c = 0; c < COLONNE; c++) {
                int range = (valMax - valMin) + 1;
                M[r][c] = generatore.nextInt(range) + valMin;
            }
        }
    }

    public static void main(String[] args) {

        int n = 7;
        int m = 5;

        int[][] M = new int[n][m];
        riempiCasuale(M, n, m, 298, 314);

        System.out.println("Temperature registrate:\n");
        UtilsMatrice.visualizza(M);

        Max risultato = calcolaMassimo(M, n, m);

        System.out.println("\nTemperatura massima: " + risultato.massimo());
        System.out.println("Verificatasi il giorno: " + (risultato.rIdx() + 1));
        System.out.println("Alle ore: " + (risultato.cIdx() + 11));

        float[] medieColonne = new float[m];

        for (int col = 0; col < m; col++) {
            medieColonne[col] = calcolaMedia(M, n, col);
        }

        System.out.println();
        for (int ora = 0; ora < m; ora++) {
            int inizio = ora + 11;
            int fine = ora + 12;
            System.out.println("Media fascia oraria: " + inizio + "-" + fine + ": " + medieColonne[ora]);
        }
    }
}