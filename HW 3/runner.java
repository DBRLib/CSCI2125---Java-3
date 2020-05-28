/**
@author Deven Ronquillo
@version 1/11/2017
*/

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;


public class runner {

    public static void main(String[] args){

        final int[] primes = {13, 50069, 100003, 994501,5000299};

        final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        long avlInsert = 0;
        long avlRemove = 0;
        long avlFind = 0;
        long runtimeAVL = 0;
        long thisRuntimeAVL = 0;

        long hashInsert = 0;
        long hashRemove = 0;
        long hashFind = 0;
        long runtimeHash = 0;
        long thisRuntimeHash = 0;

        for(int x = 0; x <= 4; x++) {

            int nodes = primes[x % 5 ];

            AVLTree<Integer> tree = new AVLTree<>();
            HashTable<String, Integer> table = new HashTable<>(nodes);

            ArrayList<String> keys = new ArrayList<>();
            ArrayList<Integer> treeVals = new ArrayList<>();


            for (int i = 1; i <= nodes; i++) {//insert n number of nodes

                StringBuilder newString = new StringBuilder();
                Random rnd = new Random();

                while (newString.length() < 6) {

                    int index = rnd.nextInt(CHARS.length());
                    newString.append(CHARS.charAt(index));
                }

                keys.add(newString.toString());

                Integer number = new Integer(new Random().nextInt(nodes * 4) + 1);

                treeVals.add(number);

                runtimeAVL = Instant.now().toEpochMilli();

                tree.Add(number);
                runtimeAVL = Instant.now().toEpochMilli() - runtimeAVL;

                avlInsert += runtimeAVL;
                thisRuntimeAVL += runtimeAVL;


                runtimeHash = Instant.now().toEpochMilli();

                table.Insert(newString.toString(), number);
                runtimeHash = Instant.now().toEpochMilli() - runtimeHash;

                hashInsert += runtimeHash;
                thisRuntimeHash += runtimeHash;
            }

            System.out.println("run: " + x + " with n of: " + nodes + "\n");

            System.out.println("AVL Insert: " + thisRuntimeAVL + " ms");
            System.out.println("Hash Insert: " + thisRuntimeHash + " ms");

            thisRuntimeAVL = 0;
            thisRuntimeHash = 0;

            for (int i = 0; i <= keys.size() - 1; i++) {//insert n number of nodes

                runtimeHash = Instant.now().toEpochMilli();

                table.Get(keys.get(i));
                runtimeHash = Instant.now().toEpochMilli() - runtimeHash;

                hashFind += runtimeHash;
                thisRuntimeHash += runtimeHash;
            }

            for (int i = 0; i <= treeVals.size() - 1; i++) {//insert n number of nodes

                runtimeAVL = Instant.now().toEpochMilli();

                tree.Contains(treeVals.get(i));
                runtimeAVL = Instant.now().toEpochMilli() - runtimeAVL;

                avlFind += runtimeAVL;
                thisRuntimeAVL += runtimeAVL;
            }

            System.out.println("AVL Find: " + thisRuntimeAVL + " ms");
            System.out.println("Hash Find: " + thisRuntimeHash + " ms");

            thisRuntimeAVL = 0;
            thisRuntimeHash = 0;

            for (int i = 0; i <= keys.size() - 1; i++) {//insert n number of nodes

                runtimeHash = Instant.now().toEpochMilli();

                table.Remove(keys.get(i));
                runtimeHash = Instant.now().toEpochMilli() - runtimeHash;

                hashRemove += runtimeHash;
                thisRuntimeHash += runtimeHash;
            }

            for (int i = 0; i <= treeVals.size() - 1; i++) {//insert n number of nodes

                runtimeAVL = Instant.now().toEpochMilli();

                tree.Remove(treeVals.get(i));
                runtimeAVL = Instant.now().toEpochMilli() - runtimeAVL;

                avlRemove += runtimeAVL;
                thisRuntimeAVL += runtimeAVL;
            }

            System.out.println("AVL Remove: " + thisRuntimeAVL + " ms");
            System.out.println("Hash Remove: " + thisRuntimeHash + " ms\n");

            thisRuntimeAVL = 0;
            thisRuntimeHash = 0;
        }

        System.out.println("\nAVL Insert Total: " + avlInsert + " ms");
        System.out.println("Hash Insert Total: " + hashInsert + " ms");

        System.out.println("\nAVL Find Total: " + avlFind + " ms");
        System.out.println("Hash Find Total: " + hashFind + " ms");

        System.out.println("\nAVL Remove Total: " + avlRemove + " ms");
        System.out.println("Hash Remove Total: " + hashRemove + " ms");


    }
}
