import java.util.*;

public class Main {

    public static void main(String[] args) {
        DataSetPermutation a = new DataSetPermutation();
        String [] chars = a.getX2();

        Permutation permutation = new Permutation(chars);
        List<String> permutations = permutation.getPermutations();
        System.out.println("********************************************************************* HANDS ON 8 *********************************************************************");
        System.out.println("Permutaciones:");
        for (String perm : permutations) {
            System.out.println(perm);
        }

        DataSetPermutation datasetPermutation = new DataSetPermutation();
        DataSetProbability datasetProbability = new DataSetProbability();

        Probability probability = new Probability(datasetPermutation, datasetProbability);
        System.out.println("\nProbabilidades:");

        System.out.println("P(A): " + probability.getMarginalProbability("a"));
        System.out.println("P(B): " + probability.getMarginalProbability("b"));
        System.out.println("P(C): " + probability.getMarginalProbability("c"));

        System.out.println("P(A, B): " + probability.getJointProbability("a", "b"));
        System.out.println("P(B, C): " + probability.getJointProbability("b", "c"));

        System.out.println("P(A|B): " + probability.getConditionalProbability("a", "b"));
        System.out.println("P(B|C): " + probability.getConditionalProbability("b", "c"));

        int[][] datasetProb = datasetProbability.getDataset();
        System.out.println("\nDataSet de las Probabilidades:");
        for (int[] row : datasetProb) {
            System.out.println(Arrays.toString(row));
        }

        String[] datasetPerm = datasetPermutation.getDataset();
        System.out.println("\nDataSet de las Permutaciones:");
        System.out.println(Arrays.toString(datasetPerm));
    }
}
