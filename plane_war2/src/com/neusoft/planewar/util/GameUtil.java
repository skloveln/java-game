package com.neusoft.planewar.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * 获取当前项目中各种资源
 * @author zzk
 *
 */
public class GameUtil {
	/**
	 * 根据图片的相对路径获取图片
	 * @param imagePath
	 * @return 图片
	 */
	public static Image getImage(String imagePath){
		URL url = GameUtil.class.getClassLoader().getResource(imagePath);
		BufferedImage img = null;
		try {
			img=ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(url);
		}
		return img;
	}
}
