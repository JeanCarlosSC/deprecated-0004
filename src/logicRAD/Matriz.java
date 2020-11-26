package logicRAD;

public class Matriz {

    // forma escalonada
    private static int PerformOperation(double[][] a) {
        int i, j, k, c, flag = 0;
        int n = a.length;

        // Performing elementary operations
        for (i = 0; i < n; i++) {
            if (a[i][i] == 0) {
                c = 1;
                while ((i + c) < n && a[i + c][i] == 0)
                    c++;
                if ((i + c) == n) {
                    flag = 1;
                    break;
                }
                for (j = i, k = 0; k <= n; k++) {
                    double temp =a[j][k];
                    a[j][k] = a[j+c][k];
                    a[j+c][k] = temp;
                }
            }

            for (j = 0; j < n; j++) {
                // Excluding all i == j
                if (i != j) {
                    // Converting Matrix to reduced row
                    // echelon form(diagonal matrix)
                    double p = a[j][i] / a[i][i];

                    for (k = 0; k <= n; k++)
                        a[j][k] = a[j][k] - (a[i][k]) * p;
                }
            }
        }
        return flag;
    }

    // To check whether infinite solutions
    // exists or no solution exists
    private static int CheckConsistency(double[][] a) {
        int i, j;
        int n = a.length;
        double sum;

        // flag == 2 for infinite solution
        // flag == 3 for No solution
        int flag = 3;
        for (i = 0; i < n; i++) {
            sum = 0;
            for (j = 0; j < n; j++)
                sum = sum + a[i][j];
            if (sum == a[i][j])
                flag = 2;
        }
        return flag;
    }

    public static double[] gaussJordan(double[][] a){
        int flag;

        // Performing Matrix transformation
        flag = PerformOperation(a);

        if (flag == 1)
            flag = CheckConsistency(a);

        // Printing Solutions(if exist)
        int n = a.length;
        System.out.print("Result is : ");

        if (flag == 2)
            return new double[]{-1};// Infinite Solutions Exists
        else if (flag == 3)
            return new double[]{ 0};// No Solution Exists

            // Printing the solution by dividing constants by
            // their respective diagonal elements
        else {
            double[] resultado = new double[n];
            for (int i = 0; i < n; i++)
                resultado[i] = a[i][n] / a[i][i];
            return resultado;
        }
    }
}