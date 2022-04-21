package com.sky.test;


import com.sky.constant.Constant;
import com.sky.core.MyFrame;
import com.sky.util.GameUtil;

import java.awt.*;

/**
 * 摆线
 * @author zzk
 *
 */
public class TestFrame7 extends MyFrame {

	Image background = GameUtil.getImage("com/neusoft/planewar/img/ball.png");
	int x = 0;
	int y = 0;
	int width = background.getWidth(null);// 得到当前图片的宽度
	int height = background.getHeight(null);// 得到当前图片的宽度
	/**
	 * 任意角度theta
	 */
	Point center = new Point((Constant.GAME_WIDTH-width)/2, (Constant.GAME_HEIGHT-height)/2);
	double theta;
	double speed = 0.1;
	int r=100;
	// double theta = Math.PI/6;
	@Override
	public void paint(Graphics g) {
		g.drawImage(background, x, y, null);
		x=(int) (r*(theta-Math.sin(theta)));
		y=(int) (center.y+r*(1-Math.cos(theta)));

		theta += speed;

	}

	public static void main(String[] args) {
		TestFrame7 tf = new TestFrame7();
		tf.launchFrame();
	}
}