
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("************************************************ HANDS ON 6 ***********************************************");
        List<Interval> intervals = DataSet.getIntervals();

        DescriptiveStatistics ds = new DescriptiveStatistics(intervals);
        ds.calculateStatistics();
        ds.printDescriptiveTable();
    }
}
