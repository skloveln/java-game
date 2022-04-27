package com.sky.util;


import com.sky.constant.Constant;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public class ImageUtil {
	public static Map<String,Image> images = new HashMap<String, Image>();
	
	static{
		images.put("fail", GameUtil.getImage(Constant.IMG_PRE + "fail.png"));
	}
}
