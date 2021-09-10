package GiaiDeThi;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


//de chay muot cho swing thi can extend lop thread + using threadsafe invokeLater
//bai nay kiem tra jfilechooser, mo file, swing, thread, Seperator \\\\

//helpful code
//static boolean ssxau(String s1,String s2)
//        {
//        if (s1.equalsIgnoreCase(s2)) return false;
//        else return true;
//        }
//
//public static final String UTF8_BOM = "\uFEFF";
//
//private static String removeUTF8BOM(String s) {
//        if (s.startsWith(UTF8_BOM)) {
//        s = s.substring(1);
//        }
//        return s;
//        }

public class cau2_2018 {
    public static void main(String[] args){

        PrepareContent.createAndShowGUI();
    }
}
class CopyClass extends Thread{
    private static JLabel proccessBar;
    private static final String SEPARATOR = "\\\\";
    private String srcDir;
    private String dstDir;


    CopyClass(String src, String dst, JLabel pBar){
        srcDir = src;
        dstDir = dst;
        proccessBar = pBar;
    }

    public void run(){

        try {
            copyFile(srcDir, dstDir);
        } catch (IOException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(String srcDir, String dstDir) throws IOException, InvocationTargetException, InterruptedException    //Dir = directory
    {
        FileInputStream fin = new FileInputStream(srcDir);


        String[] items = srcDir.split(SEPARATOR);
        String fileName = items[items.length - 1];

        FileOutputStream fout = new FileOutputStream(dstDir + "\\" + "(Copy) " + fileName );

        //In Unicode (UTF16LE/BE, ngoai ra con co UTF8 1 byte nua), character holds 2 byte, so java also uses 2 byte for characters.
        //Mac du vay, ham avail khong dem EOF (2byte), nhung no se dem BOM char (du khong hien trong file)
        //Neu file trong thi cung khong co BOM char luon, avail se return 0
        //Ki tu dau tien se doc duoc thanh 2 lan read, 255 254, la BOM character, du la 1 Unicode char, weigh 2byte

        //nhung moi thu da thay doi, lan khac 04.09.2021 minh doc thi no KHONG doc BOM character nua. Van de la luu file encoding khac nhau (BOM se co khi luu dang UTF8 with BOM)
        int totalByteNum = fin.available();
        System.out.println("Con " + totalByteNum + " bytes co the doc duoc");

        if(totalByteNum != 0) { //If file is not empty
            int i;
            int count = 0;
            do {
                i = fin.read();

                if(i != -1) //dont write the weird char into the file
                    fout.write(i);

                count++;

                int processingPoint = count * 100 / totalByteNum;
                if (processingPoint <= 100) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            proccessBar.setText("Processing: " + processingPoint + "%");
                        }
                    });
                }

                System.out.println(count + " " + i);
            } while (i != -1);
        }

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                proccessBar.setText("Copy thanh cong!");
            }
        });

        fin.close();
        fout.close();

    }
}
class PrepareContent implements ActionListener {

    private static JLabel proccessBar;
    private static TextField txtSrc;
    private static TextField txtDst;


    public static void addComponentToPane(Container pane){

        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JButton btnBrowser1 = new JButton("Src");
        c.gridx = 1;
        c.gridy = 0;
        pane.add(btnBrowser1, c);

        JButton btnBrowser2 = new JButton("Dst");
        c.gridx = 1;
        c.gridy = 2;
        pane.add(btnBrowser2, c);

        JButton btnCopy = new JButton("Copy");
        c.gridx = 2;
        c.gridy = 3;
        pane.add(btnCopy, c);

        PrepareContent f1 = new PrepareContent();

        // add action listener to the button to capture user
        // response on buttons
        btnBrowser1.addActionListener(f1);
        btnBrowser2.addActionListener(f1);
        btnCopy.addActionListener(f1);

        proccessBar = new JLabel("Process 0%");           //Start here. you have to make it work
        c.gridx = 0;
        c.gridy = 3;
        pane.add(proccessBar, c);


        c.ipadx = 200;  //For 2 text box

        txtSrc = new TextField();
        c.gridx = 0;
        c.gridy = 0;
        pane.add(txtSrc, c);

        txtDst = new TextField();
        c.gridx = 0;
        c.gridy = 2;
        pane.add(txtDst, c);
    }

    public void actionPerformed(ActionEvent evt)
    {
        // if the user presses the save button show the save dialog
        String com = evt.getActionCommand();

        if (com.equals("Copy")) {
            String filePathSrc = txtSrc.getText();
            String filePathDst = txtDst.getText();

            if(!filePathDst.equals("") && !filePathSrc.equals(""))
            {
                CopyClass c = new CopyClass(filePathSrc, filePathDst, proccessBar);
                c.start();
            }
        }
        // if the user presses the open dialog show the open dialog
        else if(com.equals("Src")){
            // create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsOpenDialog function to show the open dialog
            int r = j.showOpenDialog(null);

            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION)

            {
                // set the label to the path of the selected file
                txtSrc.setText(j.getSelectedFile().getAbsolutePath());
            }
            // if the user cancelled the operation
            else
                txtSrc.setText("");
        }
        else {
            // create an object of JFileChooser class
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // set the selection mode to directories only
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            // invoke the showsOpenDialog function to show the save dialog
            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected directory
                txtDst.setText(j.getSelectedFile().getAbsolutePath());
            }
            // if the user cancelled the operation
            else
                txtDst.setText("");
        }
    }

    public static void createAndShowGUI()
    {
        JFrame fr = new JFrame();
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //prepare content
        addComponentToPane(fr.getContentPane());

        fr.pack();
        fr.setVisible(true);
    }
}


//static method need static variable (static context)
//static method need to be called by its class name
//In Unicode, character holds 2 byte, so java also uses 2 byte for characters. https://www.quora.com/Why-is-the-size-of-char-in-Java-2-bytes#:~:text=so%208%20digits%20in%20C,And%202%20Bytes%20In%20Java.&text=char%20in%20Java%20represents%20a,UTF%2D16%20code%20units).
//Why 255 254? (255 254 UTF16LE, 254 255 UTF16BE) You ran into a Byte Order Mark, being U+FEFF, which is, when read as separate bytes, equivalent to 254 and 255. The byte order mark (BOM) (U+FEFF BYTE ORDER MARK) is a particular usage of the special Unicode character, whose appearance as a magic number at the start of a text stream can signal several things to a program reading the text:[1]
//ham avai dem ca BOM, nhung khong dem EOF
//dung ghi EOF ra file, -> if(i!=-1) write