package JavaIO;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class BufferedReader1
{
    public static void main(String args[]) throws IOException {
        //char c;

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
        BufferedReader br = new BufferedReader(new FileReader("bxh.txt"));
        System.out.print("Enter a number:");
        String str = br.readLine();

        float iNum = Float.parseFloat(str);

        System.out.println(iNum);


    }
}
