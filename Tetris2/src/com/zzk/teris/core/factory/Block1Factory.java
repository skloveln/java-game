package com.zzk.teris.core.factory;

import com.zzk.teris.core.AbstractBlock;
import com.zzk.teris.core.AbstractBlockFactory;
import com.zzk.teris.core.blocks.Block1;

import java.awt.*;
/**
 * 块工厂
 * @author zzk
 */
public class Block1Factory implements AbstractBlockFactory{
	@Override
	public AbstractBlock getBlock(int x, int y, Color color) {
		return new Block1(x, y,color);
	}
}
