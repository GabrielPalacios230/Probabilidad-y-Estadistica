import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        DataSet dataSet = new DataSet();
        DiscreteMaths dm = new DiscreteMaths();

        BigDecimal[][] conjuntoDataSetX = new BigDecimal[5][];
        BigDecimal[][][] conjuntoDataSetY = new BigDecimal[5][][];

        for (int i = 0; i < conjuntoDataSetX.length; i++) {
            conjuntoDataSetX[i] = dataSet.getX(i + 1);
            conjuntoDataSetY[i] = dataSet.getY(i + 1);
        }
        System.out.println("**************************************************************************** HANDS ON 5 *******************************************************************************");
        for (int i = 0; i < conjuntoDataSetX.length; i++) {
            System.out.println("--------------------------------------------------------------------CONJUNTO DE DATOS RESULTADO " + (i + 1) + "---------------------------------------------------------------------");
            for (int j = 1; j <= 3; j++) {
                System.out.println();
                System.out.println("Grado del polinomio: " + j);

                PolynomialRegression pr = new PolynomialRegression(conjuntoDataSetX[i], conjuntoDataSetY[i], j);
                BigDecimal[][] matriz = dm.getMatrizX(conjuntoDataSetX[i], j);
                BigDecimal[][] matrizTranspuesta = dm.getMatrizTransp(matriz, j, true);
                BigDecimal[][] matrizXTranspX = pr.multiply(matrizTranspuesta, matriz);
                BigDecimal[][] matrizInversa = pr.invert(matrizXTranspX);
                BigDecimal[][] matrizXtranspY = pr.multiply(matrizTranspuesta, conjuntoDataSetY[i]);
                BigDecimal[][] bMayor = pr.multiply(matrizInversa, matrizXtranspY);

                BigDecimal rSquared = calculateRSquared(conjuntoDataSetY[i], conjuntoDataSetX[i], bMayor, j);
                BigDecimal r = calculateR(rSquared);
                System.out.println("Coeficiente de determinación (R^2): " + rSquared + "                      Coeficiente de correlación (r): " + r);

                printResults(bMayor, j);
            }
        }
    }

    private static BigDecimal calculateRSquared(BigDecimal[][] y, BigDecimal[] x, BigDecimal[][] coefficients, int degree) {
        BigDecimal ssTotal = BigDecimal.ZERO;
        BigDecimal ssResidual = BigDecimal.ZERO;
        BigDecimal yMean = Arrays.stream(y).map(val -> val[0]).reduce(BigDecimal.ZERO, BigDecimal::add).divide(BigDecimal.valueOf(y.length), MathContext.DECIMAL128);

        for (int i = 0; i < y.length; i++) {
            BigDecimal yi = y[i][0];
            BigDecimal yHat = coefficients[0][0];
            for (int k = 1; k <= degree; k++) {
                yHat = yHat.add(coefficients[k][0].multiply(x[i].pow(k)));
            }
            ssTotal = ssTotal.add(yi.subtract(yMean).pow(2));
            ssResidual = ssResidual.add(yi.subtract(yHat).pow(2));
        }

        return BigDecimal.ONE.subtract(ssResidual.divide(ssTotal, MathContext.DECIMAL128));
    }

    private static BigDecimal calculateR(BigDecimal rSquared) {
        return rSquared.sqrt(MathContext.DECIMAL128);
    }

    private static void printResults(BigDecimal[][] coefficients, int degree) {
        switch (degree) {
            case 1:
                System.out.println("Machine Efficiency %= " + coefficients[0][0] + " + " + coefficients[1][0] + " Batch Size");

                for (int p = 0; p < 5; p++) {
                    BigDecimal xIncognit = BigDecimal.valueOf(65).add(BigDecimal.valueOf(p));
                    System.out.println("Si X= " + xIncognit);
                    BigDecimal yB0 = coefficients[0][0];
                    BigDecimal yB1 = coefficients[1][0].multiply(xIncognit);
                    System.out.println("YHat= " + yB0.add(yB1));
                }
                System.out.println();
                break;

            case 2:
                System.out.println("Machine Efficiency %= " + coefficients[0][0] + " + " + coefficients[1][0] + " Batch Size + " + coefficients[2][0] + " Batch Size^2");

                for (int p = 0; p < 5; p++) {
                    BigDecimal xIncognit = BigDecimal.valueOf(65).add(BigDecimal.valueOf(p));
                    System.out.println("Si X= " + xIncognit);
                    BigDecimal yB0 = coefficients[0][0];
                    BigDecimal yB1 = coefficients[1][0].multiply(xIncognit);
                    BigDecimal yB2 = coefficients[2][0].multiply(xIncognit.pow(2));
                    System.out.println("YHat= " + yB0.add(yB1).add(yB2));
                }
                System.out.println();
                break;

            case 3:
                System.out.println("Machine Efficiency %= " + coefficients[0][0] + " + " + coefficients[1][0] + " Batch Size + " + coefficients[2][0] + " Batch Size^2 + " + coefficients[3][0] + " Batch Size^3");
                for (int p = 0; p < 5; p++) {
                    BigDecimal xIncognit = BigDecimal.valueOf(65).add(BigDecimal.valueOf(p));
                    System.out.println("Si X= " + xIncognit);
                    BigDecimal yB0 = coefficients[0][0];
                    BigDecimal yB1 = coefficients[1][0].multiply(xIncognit);
                    BigDecimal yB2 = coefficients[2][0].multiply(xIncognit.pow(2));
                    BigDecimal yB3 = coefficients[3][0].multiply(xIncognit.pow(3));
                    System.out.println("YHat= " + yB0.add(yB1).add(yB2).add(yB3));
                }
                System.out.println();
                break;
        }
    }
}
