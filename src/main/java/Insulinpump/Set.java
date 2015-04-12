package Insulinpump;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by hexing on 15-4-10.
 */
public class Set extends JFrame {

    static JButton button;//确定按钮
    static JLabel label1; //体重标签
    static JLabel label2; //糖尿病患者分类标签
    static JTextField weight;//体重单行文本框
    static JComboBox classify;//分类下拉菜单
    private JPanel contentPane;//总面板
    static JPanel panel1;
    static JPanel panel2;

    public Set(){

        //窗口设置
        setTitle("设置");
        setBounds(300, 300, 300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        addWindowListener(new MyWindowevent());

        //总面板设置
        contentPane = new JPanel();
        contentPane.setBackground(Color.CYAN);
        contentPane.setLayout(null);
        setContentPane(contentPane);


        //体重
        label1 = new JLabel("请输入你的体重:");
        weight = new JTextField(100);
        weight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //根据体重计算胰岛素总量
             /*   Insulin_Pump.Insulin_value = Integer.valueOf(weight.getText())*0.44;
                Insulin_Pump.zaobig_value = Insulin_Pump.Insulin_value*0.5*0.2;
                Insulin_Pump.zhongbig_value = Insulin_Pump.Insulin_value*0.5*0.15;
                Insulin_Pump.wanbig_value = Insulin_Pump.zhongbig_value;
                Insulin_Pump.small_value = Insulin_Pump.Insulin_value*0.5/24;
                System.out.println("1:"+Insulin_Pump.zhongbig_value);*/
            }
        });

        //分类
        label2 = new JLabel("请选择你的分类:");
        classify = new JComboBox();
        classify.addItem("1型糖尿病");
        classify.addItem("2型糖尿病");
        classify.addItem("妊娠糖尿病");

        panel1 = new JPanel();
        panel1.setVisible(true);
        panel1.setBackground(Color.CYAN);
        panel1.setLayout(new GridLayout(1, 2));
        panel1.setBounds(50,50,220,30);
        panel1.add(label1);
        panel1.add(weight);

        panel2 = new JPanel();
        panel2.setVisible(true);
        panel2.setBackground(Color.CYAN);
        panel2.setLayout(new GridLayout(1, 2));
        panel2.setBounds(50, 151, 220, 30);
        panel2.add(label2);
        panel2.add(classify);

        //确定按钮
        button = new JButton("确定");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //判断输入的体重是否正确,如若错入弹出提示对话框
                    int num = Integer.valueOf(weight.getText());

                   // dispose();
                  //  System.out.println("2:"+Insulin_Pump.Insulin_value);
                  setVisible(false);
                }catch (Exception a){
                    JOptionPane.showMessageDialog(null, "输入有误！", "提示", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button.setBounds(120,251,80,30);
        button.setBackground(Color.pink);
        contentPane.add(button);
        contentPane.add(panel1);
        contentPane.add(panel2);
    }

    //有问题
    class MyWindowevent extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            dispose();
        }
    }
   /* public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Set frame = new Set();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }*/
}
