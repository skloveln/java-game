package com.sky.util;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.concurrent.ForkJoinPool;

/**
 * 音乐播放工具类
 *
 * @author zzk
 */
public class MusicUtil {

	/**
	 * 播放 mp3 格式的音乐文件
	 *
	 * @param filePath
	 * @return
	 */
	public static void play(String filePath) {
		try {
			BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filePath));
			new Player(buffer).play();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 播放 mp3 格式的音乐文件
	 *
	 * @param filePath
	 * @return
	 */
	public static void asyncPlay(String filePath) {
		ForkJoinPool.commonPool().execute(() -> {
			try {
				BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filePath));
				new Player(buffer).play();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 播放 mp3 格式的音乐文件
	 *
	 * @param filePath 音乐文件路径
	 * @param loop 是否循环播放
	 */
	public static void play(String filePath, boolean loop) {
		ForkJoinPool.commonPool().execute(() -> {
			do {
				try {
					BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(filePath));
					new Player(buffer).play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} while (loop);
		});
	}
}
