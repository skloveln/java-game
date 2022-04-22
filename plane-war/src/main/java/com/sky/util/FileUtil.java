package com.sky.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 获取当前项目中各种资源
 *
 * @author zzk
 */
public class FileUtil {

	/**
	 * 根据图片的相对路径获取图片
	 *
	 * @param imagePath 图片相对路径
	 * @return 图片
	 */
	public static Image getImage(String imagePath) {
		URL url = FileUtil.class.getClassLoader().getResource(imagePath);
		if (url == null) {
			throw new RuntimeException(String.format("获取工程资源路径异常, imagePath: %s", imagePath));
		}
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			throw new RuntimeException(String.format("读取工程资源路径异常, url: %s", url.getPath()));
		}
	}

	public static String getFilePath(String fileName) {
		URL url = FileUtil.class.getClassLoader().getResource(fileName);
		if (url == null) {
			throw new RuntimeException(String.format("获取工程资源路径异常, filePath: %s", fileName));
		}
		try {
			return URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(String.format("解析工程文件路径异常, url: %s", url.getPath()));
		}
	}
}
