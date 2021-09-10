package JavaIO;


import java.io.*;

public class FileReader_FileWriter
{
    public static void main(String args[])throws IOException
    {
        FileReader fr = new FileReader("b.txt");    //CharacterStream

        FileWriter fw= new FileWriter("d.txt");

        while (true)
        {
            int i = fr.read();	//Mỗi lần đọc, là đọc 1 kí tự
            if (i==-1)
                break;
            char ch =(char) i;
            System.out.print(ch);
            fw.write(i);		//Ghi ra kí tự đó
        }
        fr.close();
        fw.close();
    }
}