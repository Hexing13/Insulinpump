package Insulinpump;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by hexing on 15-4-8.
 */

public class Insulin_Pump extends JFrame{
    private JPanel contentPane; //总面板
    static JLabel slable1;//电量标签
    static JProgressBar progressBar1; //电量进度条
    static int dianliang = 100; //进度条的显示值
    static JLabel slable2; //时间
    static JLabel label1; //胰岛素剩余量
    static JLabel label2; //胰岛素注射量
    static JLabel label3; //血压量
    static JLabel label4; //血压划分
    static JProgressBar progressBar; //进度条
    static JButton button1;//开始
    static JButton button2;//进食
    static JButton button3;//设置
    static JButton button4;//关闭
    static JButton button5;//说明
    static double Insulin_value = 26.4; //每天所需的胰岛素总量
    static double zaobig_value = 5.28;//早餐前大剂量
    static double zhongbig_value = 3.96;//中餐前大剂量
    static double wanbig_value = 3.96;//晚餐前大剂量
    static double small_value = 1;//每小时基础量
    static double bu_value = 1;//补充计量
    static double realValue = 300.00; //胰岛素泵的实值
    static int value = 300;//进度条的显示值
    static boolean startFlag = false; //开始标志
    static boolean eatFlag = false; //进食标志
    static boolean threadFlag = true; //进程标志
    JPanel panel1; //上方面板
    JPanel panel2; //下方面板
    power1 p;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Insulin_Pump frame = new Insulin_Pump();
                    frame.setVisible(true);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    //未按开始按钮之前，胰岛素和血糖的标签不能显示
    public void init(){
        label1.setVisible(false);
        label2.setVisible(false);
        label3.setVisible(false);
        label4.setVisible(false);
        progressBar.setVisible(false);
    }

    //按下开始按钮，胰岛素和血糖的标签显示
    public void resume(){
        label1.setVisible(true);
        label2.setVisible(true);
        label3.setVisible(true);
        label4.setVisible(true);
        progressBar.setVisible(true);
    }

    //构造函数---初始化
    public Insulin_Pump(){
        //窗口的设置
        setTitle("胰岛素泵仿真程序");
        setBounds(200, 200, 500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); //不能更改窗口大小

        //总面板的设置
        contentPane = new JPanel();
        contentPane.setBackground(SystemColor.control);
        //contentPane.setBorder(new EmptyBorder(100,100,100,100));
        setContentPane(contentPane);
        contentPane.setLayout(null); //在总面板里用setBounds()来控制位置和大小

        //时间标签
        slable2 = new JLabel();
        slable2.setBounds(300,30,300,50);

        //电量标签
        slable1 = new JLabel("电量:");
        slable1.setBounds(30,38,50,30);

        //电量进度条
        progressBar1 = new JProgressBar();
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);
        progressBar1.setValue(100);
        progressBar1.setBounds(100,38,100,30);
        progressBar1.setForeground(Color.black);


        //上方面板
        panel1 = new JPanel();
        panel1.setBackground(Color.CYAN);
        panel1.setBounds(0, 0, 500, 500);
        panel1.setVisible(true);
        panel1.setLayout(null);
        panel1.add(slable1);
        panel1.add(slable2);
        panel1.add(progressBar1);


        //开始按钮
        button1 = new JButton("开始");
        button1.addActionListener(new ActionListener() { //匿名内部类，注册监听器，实现监听器接口
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startFlag == false){
                    resume();
                    startFlag = true;
                    label1.setText("胰岛素剩余量："+realValue+"R");
                    threadFlag = true;
                    p = new power1();
                    p.start();
                }
            }
        });
        button1.setBackground(Color.pink);
        button1.setBounds(11, 500, 88, 100);
        button1.setFont(new Font("幼圆",Font.BOLD,15));

        //胰岛素泵状态标签
        label1 = new JLabel();
        label1.setBounds(30,130,270,50);
        panel1.add(label1);

        //注射方式
        label2 = new JLabel();
        label2.setBounds(300,130,100,50);
        panel1.add(label2);

        //胰岛素泵进度条
        progressBar = new JProgressBar();
        progressBar.setMaximum(300);
        progressBar.setMinimum(0);
        progressBar.setValue(300);
        progressBar.setForeground(Color.BLUE);
        progressBar.setBounds(60,230,380,50);
        panel1.add(progressBar);

        //血糖含量标签
        label3 = new JLabel();
        label3.setBounds(30,330,270,50);
        panel1.add(label3);

        //血糖状况
        label4 = new JLabel();
        label4.setBounds(300,330,100,50);
        panel1.add(label4);

        //进食按钮
        button2 = new JButton("进食");
        button2.addActionListener(new ActionListener() { //匿名内部类
            @Override
            public void actionPerformed(ActionEvent e) {
                if(startFlag ) {
                    eatFlag = true;
                }
            }
        });
        button2.setBackground(Color.pink);
        button2.setFont(new Font("幼圆",Font.BOLD,15));
        button2.setBounds(109,500,88,100);

        //设置按钮
        button3 = new JButton("设置");
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Set();
            }
        });
        button3.setBackground(Color.pink);
        button3.setBounds(208,500,88,100);
        button3.setFont(new Font("幼圆",Font.BOLD,15));

        //关闭按钮
        button4 = new JButton("关闭");
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init();
            }
        });
        button4.setBackground(Color.pink);
        button4.setBounds(307,500,88,100);
        button4.setFont(new Font("幼圆",Font.BOLD,15));

        //说明按钮
        button5 = new JButton("说明");
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Explain();
            }
        });
        button5.setBackground(Color.pink);
        button5.setBounds(406,500,88,100);
        button5.setFont(new Font("幼圆",Font.BOLD,15));

        //下方面板
        panel2 = new JPanel();
        panel2.setVisible(true);
        panel2.setBackground(Color.pink);
        panel2.setBounds(0, 500, 500, 100);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        contentPane.add(panel1);
        contentPane.add(panel2);
        init();
        CountTime ct = new CountTime();
        ct.start();
    }


}
class CountTime extends Thread{
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat df1 = new SimpleDateFormat("mmss");
    public void run(){
        while(true){
            Insulin_Pump.slable2.setText(df.format(new Date()));
             try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class power1 extends Thread{
    Random r = new Random();
    DecimalFormat df = new DecimalFormat("0.00");
    String input_state;
    String body_state;

    public void run(){
        while (Insulin_Pump.threadFlag){
            work();
        }
    }

    public void work(){
        while (true){
            int count;
            CountTime ct = new CountTime();
            ct.start();
            String mmss = ct.df1.format(new Date());

            //时刻检查血糖高低，注射补充计量
            count = r.nextInt(120) + 50;
            Insulin_Pump.label3.setText("血糖含量:" + count + "Mg/dl");

            //若进餐，大剂量注射
            if(Insulin_Pump.eatFlag){
                input_state = "[进食中]";
                body_state = "[大剂量注射]";
                Insulin_Pump.realValue = Insulin_Pump.realValue - 1;
                Insulin_Pump.value = (int)Insulin_Pump.realValue;
            }else if("0000".equals(mmss)){
                input_state = "[整点注射]";
                body_state = "";
                Insulin_Pump.realValue = Insulin_Pump.realValue - Insulin_Pump.small_value;
                Insulin_Pump.value = (int)Insulin_Pump.realValue;
            }else {
                if (count < 60) {
                    //低血糖
                    body_state = "[过低]";
                    input_state = "[停止注射]";
                } else if (count > 160) {
                    //高血糖
                    body_state = "[过高]";
                    input_state = "[补充剂量]";
                    Insulin_Pump.realValue = Insulin_Pump.realValue - Insulin_Pump.bu_value;
                    Insulin_Pump.value = (int)(Insulin_Pump.realValue - Insulin_Pump.bu_value);
                } else {
                    //正常
                    body_state = "[正常]";
                    input_state = "[微量注射]";
                }
            }

            Insulin_Pump.dianliang = Insulin_Pump.dianliang-1;
            Insulin_Pump.label2.setText(input_state);
            Insulin_Pump.label4.setText(body_state);
            Insulin_Pump.progressBar.setValue(Insulin_Pump.value);
            Insulin_Pump.label1.setText("胰岛素剩余量:"+Insulin_Pump.realValue+"R");
            Insulin_Pump.progressBar1.setValue(Insulin_Pump.dianliang);

            if(Insulin_Pump.value < 100){
                JOptionPane.showMessageDialog(null, "胰岛素不足！", "提示", JOptionPane.ERROR_MESSAGE);
            }
            if(Insulin_Pump.eatFlag){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Insulin_Pump.eatFlag=false;
            }else{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
}