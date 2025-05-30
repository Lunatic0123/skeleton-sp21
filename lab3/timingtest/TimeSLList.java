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
        AList<Integer> Ns = new AList<>();
        AList<Integer> op = new AList<>();
        AList<Double> times = new AList<>();
        for(int i = 0,number = 1000;i<8;i++){
            Ns.addLast(number);
            op.addLast(10000);
            number*=2;
        }
        for(int i = 0;i<8;i++){
            SLList <Integer> temp = new SLList<>();
            int count = 0;
            while(count!=Ns.get(i)){
                temp.addLast(1);
                    count++;
            }
            Stopwatch sw = new Stopwatch();
            count = 0;
            while(count!=10000){
                temp.getLast();
                count++;
            }
            times.addLast((sw.elapsedTime()));
        }
        printTimingTable(Ns,times,op);
    }

}
