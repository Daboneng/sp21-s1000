package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int n = 1000;
        int m = 10000;
        double limit = Math.pow(2,7)*1000;
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        while (n <= limit) {
            SLList<Integer> Curr = new SLList<>();
            for(int i = 1; i < n; i ++) {
                Curr.addLast(i);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 1; j < m; j ++){
                Curr.getLast();
            }
            double TimeinSeconds = sw.elapsedTime();
            Ns.addLast(n);
            times.addLast(TimeinSeconds);
            opCounts.addLast(m);
            n *=2;
        }
        printTimingTable(Ns, times, opCounts);
    }

}
