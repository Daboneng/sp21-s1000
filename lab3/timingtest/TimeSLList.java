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
        SLList<Integer> Curr = new SLList<>();
        Stopwatch sw = new Stopwatch();

        while (n <= limit) {
            for(int i = 1; i < n; i ++) {
                Curr.addLast(i);
            }
            Curr.getLast();
            double TimeinSeconds = sw.elapsedTime();
            Ns.addLast(n);
            opCounts.addLast(m);
            times.addLast(TimeinSeconds);
            n *=2;
        }
        printTimingTable(Ns, times, opCounts);
    }

}
