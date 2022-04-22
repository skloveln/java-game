package com.sky.core;

import com.sky.constant.Constant;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 游戏中自定义窗口的父类 设置一次，终身使用
 *
 * @author zzk
 */
public class MyFrame extends Frame {

    private Image backImg; // 背景图

    /**
     * 自定义生成窗口的方法
     *
     */
    public void launchFrame() {
        // 设置窗口大小
        setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        // 设置窗口位置
        // setLocation(0, 0);
        // 相对居中，传入null，相对于显示器屏幕
        setLocationRelativeTo(null);
        // 设置窗口标题
        setTitle("飞机大战");
        // 设置可见
        setVisible(true);
        // 设置不能改变大小
        setResizable(false);
        // 设置关闭窗口
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);// 设置关闭
            }
        });
        // 屏蔽输入法
        enableInputMethods(false);
        setBackground(Color.BLACK);

        // 启动一个线程刷新
		new Thread(() -> {
            while (true){
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            }
		}).start();

    }

    /**
     * 防止图片闪烁，刷新时，填充背景图
     *
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (backImg == null) {
            backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        }
        Graphics backGraphics = backImg.getGraphics();
        Color c = backGraphics.getColor();
		backGraphics.setColor(Color.BLACK);
		backGraphics.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		backGraphics.setColor(c);
        paint(backGraphics);
        g.drawImage(backImg, 0, 0, null);
    }

}
