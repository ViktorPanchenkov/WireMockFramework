import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Dima {

    public static void main(String[] args) throws IOException {
        List<String> list = new ArrayList<>();
        readFile(list);
    }


    List<String> list = new ArrayList<>();

    public static List<String> readFile(List<String> list) throws IOException {

        try (DataInputStream in =
                     new DataInputStream(new FileInputStream("./src/test/java/data.log"))) {

            while (true) {
                String  s = in.readUTF();
                list = List.of(s.split("\"\\\\r?\\\\n\""));
                System.out.println(list);


            }

        }
        catch (EOFException | FileNotFoundException e) {
            // e.printStackTrace();
            System.out.println("плохо дело - ексш");
        }
        System.err.println(list.get(0));
        return list;
    }
}
