package GiaiDeThi;

import java.io.*;

//useful methods
/*
* datetime https://www.w3schools.com/java/java_date.asp

static boolean ssxau(String s1,String s2)
{
if (s1.equalsIgnoreCase(s2)) return false;
else return true;
}

public static final String UTF8_BOM = "\uFEFF";

private static String removeUTF8BOM(String s) {
    if (s.startsWith(UTF8_BOM)) {
        s = s.substring(1);
    }
    return s;
}
*
compareTo() (detail down below)
* substring(start(inclusive), end(exclusive)
*/

class VanBan
{
    protected String str;
    VanBan(){}
    VanBan(String st)
    {
        str=st;
    }
    public static void main(String args[])throws IOException
    {
        String st;
        System.out.print("Nhap xau: ");
        BufferedReader stream = new BufferedReader(new InputStreamReader(System.in));
        //DataInputStream stream = new DataInputStream(System.in);
        st =stream.readLine();

        VanBan VB =new VanBan(st);

        System.out.println("So tu trong xau la: "+dem(VB));
        System.out.println("So kt H (hoac h) trong xau la: "+demkt(VB));

        VB.str=ChuanHoa(VB);
        System.out.println("Xau chuan hoa: "+VB.str);


    }

    static int dem(VanBan vb)
    {
        int d;
        vb.str=ChuanHoa(vb);
        if (vb.str.charAt(0)==' ') d=0;
        else d=1;

        for (int i=0;i<vb.str.length();i++)
            if (vb.str.charAt(i)==' '&&vb.str.charAt(i+1)!=' ')
                d++;
        return d;
    }
    static int demkt(VanBan vb)
    {
        int d=0;
        for (int i=0;i<vb.str.length();i++)
            if (vb.str.charAt(i)=='H'||vb.str.charAt(i)=='h')
        d++;
        return d;
    }

    static String ChuanHoa(VanBan vb)
    {
        StringBuffer vbn= new StringBuffer(vb.str);
        while(vbn.charAt(0)==' ')
            vbn=vbn.delete(0,1);    //start (inclusive), end (exclusive)

        while(vbn.charAt(vbn.length()-1)==' ')
            vbn=vbn.delete(vbn.length()-1,vbn.length());

        for (int i=1;i<vbn.length();i++)
            while (vbn.charAt(i)==' '&&vbn.charAt(i+1)==' ')
                vbn=vbn.delete(i,i+1);

        return(vbn.toString());
    }
}

/*
        * public int compareTo(String anotherString)
        Compares two strings lexicographically. The comparison is based on the Unicode value of each character in the strings. The character sequence represented by this String object is compared lexicographically to the character sequence represented by the argument string. The result is a negative integer if this String object lexicographically precedes the argument string. The result is a positive integer if this String object lexicographically follows the argument string. The result is zero if the strings are equal; compareTo returns 0 exactly when the equals(Object) method would return true.
        This is the definition of lexicographic ordering. If two strings are different, then either they have different characters at some index that is a valid index for both strings, or their lengths are different, or both. If they have different characters at one or more index positions, let k be the smallest such index; then the string whose character at position k has the smaller value, as determined by using the < operator, lexicographically precedes the other string. In this case, compareTo returns the difference of the two character values at position k in the two string -- that is, the value:

        this.charAt(k)-anotherString.charAt(k)

        If there is no index position at which they differ, then the shorter string lexicographically precedes the longer string. In this case, compareTo returns the difference of the lengths of the strings -- that is, the value:
        this.length()-anotherString.length()

        Specified by:
        compareTo in interface Comparable<String>
Parameters:
        anotherString - the String to be compared.
        Returns:
        the value 0 if the argument string is equal to this string; a value less than 0 if this string is lexicographically less than the string argument; and a value greater than 0 if this string is lexicographically greater than the string argument.*/

