package com.zzk.teris.core.factory;

import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockFactory;
import com.zzk.teris.core.blocks.Block3;

import java.awt.*;
/**
 * 块工厂
 * @author zzk
 */
public class Block3Factory implements AbstractBlockFactory{
	@Override
	public AbstractBlock getBlock(int x, int y, Color color) {
		return new Block3(x,y,color);
	}
}
