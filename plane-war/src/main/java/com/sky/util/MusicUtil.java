package com.sky.util;

import javazoom.jl.player.Player;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.concurrent.ForkJoinPool;


/**
 * 音乐播放工具类
 *
 * @author zzk
 */
@Slf4j
public class MusicUtil extends Thread {

	private boolean loop;
	private String fileName;
	
	public MusicUtil(String fileName, boolean loop) {
		this.loop = loop;
		this.fileName = fileName;
	}

	public MusicUtil(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 根据是否循环播放音乐
	 */
	@Override
	public void run() {
		try{
			if(loop){
				while(true){
					play(this.fileName);
				}
			}else{
				play(this.fileName);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 播放 mp3 格式的音乐文件
	 *
	 * @param filePath
	 * @return
	 */
	public static void play(String filePath) {
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
