/**
@author Deven Ronquillo
@version fall 2017
*/

public class BigOh {

    public static void main(String[] args){

        for(int n = 1; n <= 10; n++){

            long start = System.currentTimeMillis();
            int sum = 0;

            for(int i = 0; i < n; i++){

                for(int j = 0; j < i; j++) {

                    sum++;
                }
            }

            long end = System.currentTimeMillis();


            System.out.println("TEST ONE - current n: " + n + " Total: " + (sum));
            System.out.println("Total runtime for n: " + n + " is: " + ( end - start) + " ms.\n");
        }

        for(int n = 1; n <= 10; n++){

            long start = System.currentTimeMillis();
            int sum = 0;

            for(int i = 0; i < n; i++){

                for(int j = 0; j < i * i; j++) {

                  //  for( int h = 0; h < j; h++){

                        sum++;
                  //  }
                }
            }

            long end = System.currentTimeMillis();

            System.out.println("TEST TWO - current n: " + n + " Total: " + (sum));
            System.out.println("Total runtime for n: " + n + " is: " + ( end - start) + " ms.\n");
        }

        for(int n = 1; n <= 10; n++){

            long start = System.currentTimeMillis();
            int sum = 0;

            for(int i = 0; i < n; i++){

                for(int j = 0; j < n * n; j++) {

                    sum++;
                }
            }

            long end = System.currentTimeMillis();


            System.out.println("TEST THREE - current n: " + n + " Total: " + (sum));
            System.out.println("Total runtime for n: " + n + " is: " + ( end - start) + " ms.\n");
        }
    }
}
