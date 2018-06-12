package cn.org.kkl.dynamicompile;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
	
	public static void closeIo(Closeable ...io) {
		for (Closeable stream : io) {
			if(null!=stream) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
