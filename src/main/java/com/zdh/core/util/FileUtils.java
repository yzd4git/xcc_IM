package com.zdh.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 将文件转为 String 和 byte
 * @author yzd
 *
 */
public class FileUtils {

	private FileUtils(){
		throw new AssertionError();
	}
	 public static byte[] toByteArray(String filename) throws IOException {  
		  
	        File f = new File(filename);  
	        if (!f.exists()) {  
	            throw new FileNotFoundException(filename);  
	        }  
	  
	        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
	        BufferedInputStream in = null;  
	        try {  
	            in = new BufferedInputStream(new FileInputStream(f));  
	            int buf_size = 1024;  
	            byte[] buffer = new byte[buf_size];  
	            int len = 0;  
	            while (-1 != (len = in.read(buffer, 0, buf_size))) {  
	                bos.write(buffer, 0, len);  
	            }  
	            return bos.toByteArray();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            throw e;  
	        } finally {  
	            try {  
	                in.close();  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	            bos.close();  
	        }  
	    }  
	
	   public static String readFile(String fileName) {
	    String output = ""; 
	    File file = new File(fileName);
	    if(file.exists()){
	      if(file.isFile()){
	        try{
	          BufferedReader input = new BufferedReader (new FileReader(file));
	          StringBuffer buffer = new StringBuffer();
	          String text;
	          while((text = input.readLine()) != null)
	            buffer.append(text +"/n");
	          output = buffer.toString();          
	        }
	        catch(IOException ioException){
	          System.err.println("File Error!");
	        }
	      }
	      else if(file.isDirectory()){
	        String[] dir = file.list();
	        output += "Directory contents:/n";
	         
	        for(int i=0; i<dir.length; i++){
	          output += dir[i] +"/n";
	        }
	      }
	    }
	    else{
	      System.err.println("Does not exist!");
	    }
	    return output;
	   }
	   public static void main (String args[]){
	     String str = readFile("C:\\Users\\yzd\\Desktop\\vkd\\iwall09.vkd");
	     System.out.print(str);
	   }
	}