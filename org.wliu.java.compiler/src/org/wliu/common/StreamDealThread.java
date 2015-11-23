package org.wliu.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

public class StreamDealThread extends Thread {
	private static Logger log = Logger.getLogger(StreamDealThread.class);
	private InputStream is = null;
	
	public StreamDealThread(InputStream is) {
		this.is = is;
	}
	
	public void run() {
		if (is != null) {
			byte[] buffer = new byte[128];
			int len = -1;
			try {
				while ((len=is.read(buffer)) > 0) {
					System.out.println(new String(buffer,0,len,"UTF-8"));
				}
				
			}catch (IOException ex) {
				log.error(ex);
				ex.printStackTrace();
			} finally {
				if (is != null) {
					try {
						is.close();
						log.info("success to finish read the inputsteam");
					} catch (IOException e) {
						log.error(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		}
	}

}
