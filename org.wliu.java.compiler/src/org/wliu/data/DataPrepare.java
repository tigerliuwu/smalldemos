package org.wliu.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class DataPrepare {
	/**
	 * generate a java file with suffix .java which is located in System.getProperty("java.io.tmpdir")+"/src" 
	 * @param destFileName eg HelloWorld
	 * @throws FileNotFoundException 
	 */
	public static void prepareData(String destFileName) throws FileNotFoundException {
//		String className = "RunTime";  
        String classDir = System.getProperty("java.io.tmpdir")+System.getProperty("file.separator")+"src";  
        classDir = classDir.replace("\\", "/");
        
        prepareData(classDir,destFileName);
	}
	
	public static void prepareData(String destDir1,String destFileName) throws FileNotFoundException {
		String destDir = destDir1.replace("\\", "/");
        File dir = new File(destDir);
        if (!dir.exists()) {
        	dir.mkdirs();
        }
        File file = new File(destDir, destFileName+".java");  
        PrintWriter out = new PrintWriter(new FileOutputStream(file));  
        // 代码  
        StringBuffer sbf = new StringBuffer(128);  
        sbf.append("public class ");  
        sbf.append(destFileName);  
        sbf.append("{");  
        sbf.append("public void hello () {");  
        sbf.append("System.out.println(\"DynamicCompile Success.\");");  
        sbf.append("}");  
        sbf.append("}");  
        String code = sbf.toString();  
        out.println(code);  
        out.flush();  
        out.close();  
	
	}
	
	public static void prepareDataWithExtJar(String destDir1,String destFileName) throws FileNotFoundException {
		String destDir = destDir1.replace("\\", "/");
        File dir = new File(destDir);
        if (!dir.exists()) {
        	dir.mkdirs();
        }
        File file = new File(destDir, destFileName+".java");  
        PrintWriter out = new PrintWriter(new FileOutputStream(file));  
        // 代码  
        StringBuffer sbf = new StringBuffer(128); 
        sbf.append("import org.apache.log4j.Logger;");
        sbf.append("public class ");  
        sbf.append(destFileName);  
        sbf.append("{");  
        sbf.append("private static Logger log = Logger.getLogger("+destFileName+".class);");
        sbf.append("public void hello () {");  
        sbf.append("log.info(\"success to come to here\");");  
        sbf.append("System.out.println(\"DynamicCompile Success.\");");  
        sbf.append("}");  
        sbf.append("}");  
        String code = sbf.toString();  
        out.println(code);  
        out.flush();  
        out.close();  
	
	}
}
