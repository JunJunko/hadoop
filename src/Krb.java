import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;  
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.security.SecurityUtil;  
import org.apache.hadoop.security.UserGroupInformation;  
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map; 

public class Krb {  
    static Configuration conf = new Configuration();  
    static FileSystem fs = null;  
    
    public static void testUpload() throws Exception{  
    	FileSystem fs = FileSystem.get(KerberosConf());
        InputStream in = new FileInputStream("D://workspace//Uoo//shares_data//600000.csv");  
        OutputStream out = fs.create(new Path("/a/600000.csv"));  
        IOUtils.copyBytes(in, out, 1024, true);//in输入源, out输出源头, 1024缓冲区大小 ,true 是否关闭数据流。如果是false，就在finally里关掉  
    } 
    
    
    public static void ListFile() throws Exception{  
    	FileSystem fs = FileSystem.get(KerberosConf());
    	FileStatus[] fsStatus = fs.listStatus(new Path("/a"));
    	
    	for(int i = 0; i < fsStatus.length; i++){
     	   System.out.println(fsStatus[i].getPath().toString());
     	}
    } 
    
    
    public static void readFile(String filePath) throws Exception{
        FileSystem fs = FileSystem.get(KerberosConf());
        Path srcPath = new Path(filePath);
        InputStream in = null;
        try {
            in = fs.open(srcPath);
            IOUtils.copyBytes(in, System.out, 4096, false); //复制到标准输出流
        } finally {
            IOUtils.closeStream(in);
        }
    }
    
    

    
    public static  Configuration KerberosConf() throws Exception{  
    	conf.set("fs.defaultFS", "hdfs://cdh:9000");
    	conf.set("hadoop.security.authentication", "kerberos");
    	conf.set("dfs.namenode.kerberos.principal.pattern", "hadoop/*@HADOOP.COM"); 
    	
    	UserGroupInformation.setConfiguration(conf);
    	UserGroupInformation.loginUserFromKeytab("hadoop/cdh@HADOOP.COM", 
    	   "D:\\workspace\\Hadoop\\hdfs.keytab");
    	
    	return conf;
    } 

 
    public static void main(String[] args) throws IOException {  
//    	conf.set("fs.defaultFS", "hdfs://cdh:9000");
//    	conf.set("hadoop.security.authentication", "kerberos");
//    	conf.set("dfs.namenode.kerberos.principal.pattern", "hadoop/*@HADOOP.COM"); 
//    	
//    	UserGroupInformation.setConfiguration(conf);
//    	UserGroupInformation.loginUserFromKeytab("hadoop/cdh@HADOOP.COM", 
//    	   "D:\\workspace\\Hadoop\\hdfs.keytab");
//    	 
//    	FileSystem fs = FileSystem.get(conf);
////    	boolean  flag = fs.mkdirs(new Path("/a//test4")); 
//    	FileStatus[] fsStatus = fs.listStatus(new Path("/a"));
//    	
////    	boolean flag = fs.delete(new Path("/a/test4"),true);
//    	for(int i = 0; i < fsStatus.length; i++){
//    	   System.out.println(fsStatus[i].getPath().toString());
//    	}
//      
    	
    	try {
    		readFile("/a/600000.csv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
} 
