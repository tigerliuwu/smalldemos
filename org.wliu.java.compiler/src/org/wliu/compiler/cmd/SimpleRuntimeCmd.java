package org.wliu.compiler.cmd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.wliu.common.StreamDealThread;
import org.wliu.data.DataPrepare;

/**
 * 1. generate 2 java files:HelloWorld.java & HelloWorldWithJar.java 
 * in the folder:System.getProperty("java.io.tmpdir")+"/src"<p>
 * 2. Use commandline:javac to compile these 2 files to the folder:System.getProperty("java.io.tmpdir")+"/bin"<p>
 * 3. About the file:HelloWorldWithJar.java, it depends on log4j.jar
 * @author Think
 *
 */
public class SimpleRuntimeCmd {
	private static Logger log = Logger.getLogger(SimpleRuntimeCmd.class);
	
	
	public static void main(String[] args) throws IOException {
		log.setLevel(Level.ALL);
		// prepare the sample java file
		String dest = System.getProperty("java.io.tmpdir") + "bin";
		dest = dest.replace("\\", "/");
		File destDir = new File(dest);
		if(!destDir.exists() ) {
			destDir.mkdir();
		}
		log.info("here we come");
		String strSrc = System.getProperty("java.io.tmpdir")+"src";
		strSrc = strSrc.replace("\\", "/");
		DataPrepare.prepareData(strSrc,"HelloWorld");
		DataPrepare.prepareDataWithExtJar(strSrc,"HelloWorldWithJar");
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(".").getPath());
		//编译所有java文件为.class文件
		String[] classjars = System.getProperty("java.class.path").split(";");
		String log4jJar = null;
		for (String jar : classjars) {
			if (jar.endsWith("log4j-1.2.17.jar")) {
				log4jJar = jar;
				break;
			}
		}
		// both "-classpath" and "-cp" can work well
		// refer to http://blog.csdn.net/liangzai_cool/article/details/45083807
		String[] cmdarray = {"javac","-classpath",log4jJar,"-d",dest, strSrc+"/*.java"};
		Process prc = Runtime.getRuntime().exec(cmdarray);
		InputStream is = prc.getInputStream();
//		OutputStream out = prc.getOutputStream();
		InputStream errIn = prc.getErrorStream();
		
		StreamDealThread inThread = new StreamDealThread(is);
		inThread.start();
		
		StreamDealThread errThread = new StreamDealThread(errIn);
		errThread.start();
		
		try {
			prc.waitFor();
		} catch (InterruptedException e) {
			log.error(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("finish");
	}

}