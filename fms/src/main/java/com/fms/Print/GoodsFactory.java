package com.fms.Print;

import java.util.Arrays;
import java.util.Collection;

public class GoodsFactory {
	private static TradePrint[] data = {new TradePrint()};
	public static Object[] getBeanArray() {
		return data;
	}

	public static Collection<?> getBeanCollection() {
		return Arrays.asList(data);
	}
}