package Insulinpump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hexing on 15-4-12.
 */
public class Explain extends JFrame{
    static JLabel explain1;//说明一
    static JLabel explain2;//说明二
    static JPanel contentPane;//总面板
    static JButton button;//确定按钮

    public Explain(){

        //窗口的设置
        setTitle("说明");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300,300,300,300);
        setResizable(true);
        setLayout(null);

        //总面板的设置
        contentPane = new JPanel();
        contentPane.setBackground(Color.cyan);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        //说明标签
        explain1 = new JLabel("1.当点量不足时，发出警报");
        explain1.setBounds(40,70,200,50);
        explain2 = new JLabel("2.当胰岛素量不足时，发出警报");
        explain2.setBounds(40,120,200,50);

        //确定按钮
        button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        button.setBackground(Color.pink);
        button.setBounds(100,180,70,40);
        contentPane.add(explain1);
        contentPane.add(explain2);
        contentPane.add(button);
    }
}
