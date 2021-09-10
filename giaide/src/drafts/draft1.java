package drafts;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

//Cách đơn giản nhất là bufferedReader rồi parse
//Còn làm kiểu file nhị phân thì dataInputStream(new FileInputStream) rồi ghi...

public class draft1 {
    public static void main(String[] args) throws IOException {

        //System.out.println(new File(".").getAbsolutePath());

//        FileInputStream dis = new FileInputStream("b.txt");
//        FileReader fr = new FileReader("b.txt");
//        int res;
//        do {
//            res = dis.read();
//                char ch =(char) res;
//            System.out.println("Ket qua la:" + ch);
//        } while (res != -1);


//        while (true)
//        {
//            int i = fr.read();	//Mỗi lần đọc, là đọc 1 kí tự
//            if (i==-1)
//                break;
//            char ch =(char) i;
//            System.out.print(ch);
//            System.out.println("Ket qua la:" + i);
//        }
//        fr.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("b.txt"), StandardCharsets.UTF_8));


        String res = br.readLine();
        Float a = Float.parseFloat(res);
        System.out.println("ket qua ne: " + res);
         res = br.readLine();
        Float b = Float.parseFloat(res);

        System.out.println("ket qua ne: " + res);
         res = br.readLine();
        System.out.println("ket qua ne: " + res + " " + (a + b));

    }
}
