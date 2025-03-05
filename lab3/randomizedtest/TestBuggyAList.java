package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    public TestBuggyAList(){
    }
    public static void main(String[] args){
       randomizedTest();
    }

   public static void testThreeAddThreeRemove(){
       AListNoResizing <Integer> tempa = new AListNoResizing<>();
       BuggyAList <Integer> tempb = new BuggyAList<>();
       tempa.addLast(1000);
       tempb.addLast(1000);
       tempa.addLast(2000);
       tempb.addLast(2000);
       tempa.addLast(3000);
       tempb.addLast(3000);
       for(int i = 0;i<3;i++) {
           int aLast = tempa.removeLast();
           int bLast = tempb.removeLast();
           if (aLast!=bLast) {
               System.out.print("Wrong");
               return;
           }
       }
       System.out.print("Correct");
       return;
   }
   public static void randomizedTest(){
       AListNoResizing<Integer> L = new AListNoResizing<>();
       BuggyAList<Integer> M = new BuggyAList<>();
       int N = 5000;
       for (int i = 0; i < N; i += 1) {
           int operationNumber = StdRandom.uniform(0, 4);
           if (operationNumber == 0) {
               // addLast
               int randVal = StdRandom.uniform(0, 100);
               L.addLast(randVal);
               M.addLast(randVal);
               System.out.println("addLast(" + randVal + ")");
           } else if (operationNumber == 1) {
               // size
               int size = L.size();
               int size_m = M.size();
               if(size !=size_m){
                   System.out.println("Wrong return of size");
                   return;
               }
               else {
                   System.out.println("size: " + size);
               }
           }
           else if(L.size() == 0){
               continue;
           }
           else if(operationNumber == 2){
               int removeval = L.removeLast();
               int removeval_m = M.removeLast();
               if(removeval_m!=removeval){
                   System.out.println("Wrong of remove:L:"+removeval+"M:"+removeval_m);
                   return;
               }
               else {
                   System.out.println("remove:" + removeval);
               }
           }
           else if(operationNumber == 3){
               int lastval = L.getLast();
               int lastval_m = M.getLast();
               if(lastval!=lastval_m) {
                   System.out.println("Wrong of lastval:L:" + lastval + "M:" + lastval_m);
                   return;
               }
               else {
                   System.out.println("Last val:" + lastval);
               }
           }
       }
   }
}
