/**
@author Deven Ronquillo
@version fall 2017
*/
public class NumericalAnalysis {

    public NumericalAnalysis(){

        super();
    }//end constructor

    public double solveForZero(function f, double low, double high){

        double ans = -1;

        while(ans != 0.0) {

            double median = (high + low)/2;
            ans = f.f(median);

            if (ans > 0.0) {

                high = median;
            } else {

                low = median;
            }

            System.out.println("low: " + low + " high: " + high);
            System.out.println("ans: " + ans);
        }

        return low;
    }//end solve for zero
}//end numerical analysis
