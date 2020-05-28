import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Deven Ronquillo
 * @version 29/11/17
 *
 * Parses DIMACS .gr map data into edges.
 */
public class MapDataParser {
    /**
     * Main method, parses .gr file.
     *
     * @param arg File name of the file to be parsed.
     * @return An Edge array of the edges parsed from the file.
     */
    public static Edge[] parse(String arg){

        System.out.println("STARTING DATA STREAM!");

        ArrayList<Edge> edges = new ArrayList<>();

        File file;

        FileInputStream inputStream;

        try {

            file = new File(arg);
            inputStream = new FileInputStream(file.getAbsoluteFile());

            int data = inputStream.read();

            System.out.println(".gr file read in properly...");

            int counter = 1;

            while(data != -1){

                if(data == 97){

                    data = inputStream.read();

                    if(data == 32){

                        data = inputStream.read();

                        StringBuilder value = new StringBuilder();

                        int tail = 0;
                        int head = 0;
                        int weight = 0;

                        System.out.println("----------Making edge----------: "+counter);

                        counter++;

                        for(int i = 0; i<=2;){

                            value.append(data - '0');

                            data = inputStream.read();

                            if(data > 57 || data < 48){

                                if(i == 0){

                                    System.out.println("Found tail vertex.");

                                    tail = Integer.parseInt(value.toString());
                                    value = new StringBuilder();
                                    data = inputStream.read();
                                }

                                if(i == 1){

                                    System.out.println("Found head vertex.");

                                    head = Integer.parseInt(value.toString());
                                    value = new StringBuilder();
                                    data = inputStream.read();
                                }

                                if(i == 2){

                                    System.out.println("Found edge weight.");

                                    weight = Integer.parseInt(value.toString());
                                    value = new StringBuilder();
                                    data = inputStream.read();

                                    edges.add(new Edge(tail,head,weight));
                                }

                                i++;
                            }
                        }
                    }
                }

                data = inputStream.read();
            }

            System.out.println("Done parsing data!");
        }
        catch(FileNotFoundException e){

            System.err.println("FileNotFoundException:" + e.getMessage());
        }
        catch (IOException e){

            System.err.println("IOException:" + e.getMessage());
        }

        return edges.toArray(new Edge[edges.size()]);
    }
}
