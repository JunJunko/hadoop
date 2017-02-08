import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class test {
	
	private final static IntWritable one = new IntWritable(1);  
    private final Text word = new Text(); 
	
    public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
//        	System.out.println(new String(filecontent, encoding));
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }
	  
	  
	  
	  public static void map(String value)  
              throws IOException {  
          String line = value.toString();  
          StringTokenizer tokenizer = new StringTokenizer(line);  
          
          System.out.println(tokenizer.hasMoreTokens());

      }  
  
	  
	    public static void main(String argv[]) throws UnsupportedEncodingException{
	        String filePath = "D:\\workspace\\Uoo\\shares_data\\600000.csv";
	        try {
				map(readToString(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//readToString(filePath);
	    }


}
