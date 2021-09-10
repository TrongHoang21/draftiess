package Swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardLayoutDemo implements ItemListener
{
    JPanel cards;
    final static String BUTTONPANEL = "JPanel with JButtons";
    final static String TEXTPANEL = "JPanel with JTextField";

    public void addComponentToPane(Container pane)
    {
        JPanel comboBoxPane = new JPanel();
        String comboBoxItems[] = { BUTTONPANEL, TEXTPANEL };
        JComboBox cb = new JComboBox(comboBoxItems);    //Khởi tạo CB với 2 ITEM
        cb.setEditable(false);
        cb.addItemListener(this);               //This là cái class mà chứa itemStateChange
        comboBoxPane.add(cb);                           //Bỏ cb vào trong 1 cái pane nhỏ

        JPanel card1 = new JPanel();                    //Tạo card 1, card 2
        card1.add(new JButton("Button 1"));
        card1.add(new JButton("Button 2"));
        card1.add(new JButton("Button 3"));

        JPanel card2 = new JPanel();
        card2.add(new JTextField("TextField", 20));

        cards = new JPanel(new CardLayout());
        cards.add(card1, BUTTONPANEL);                  //Set name cho tí nữa show trong StateChange
        cards.add(card2, TEXTPANEL);

        pane.add(comboBoxPane, BorderLayout.WEST);
        pane.add(cards, BorderLayout.CENTER);
    }

    public void itemStateChanged(ItemEvent evt)
    {
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, (String)evt.getItem());              //Show card, với name tương ứng nè
    }

    private static void createAndShowGUI()
    {
        JFrame frame = new JFrame("CardLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CardLayoutDemo demo = new CardLayoutDemo();
        demo.addComponentToPane(frame.getContentPane());  //hàm addC.. không phải static nên phải tạo

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        //javax.swing.SwingUtilities.invokeLater(new Runnable()
        //{
        //    public void run()
        //    {
        createAndShowGUI();
        //    }
        //});


    }
}

