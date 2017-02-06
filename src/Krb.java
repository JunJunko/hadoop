import org.apache.hadoop.conf.Configuration;  
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;  
import org.apache.hadoop.fs.Path;  
import org.apache.hadoop.security.SecurityUtil;  
import org.apache.hadoop.security.UserGroupInformation;  
import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;  
import java.util.List;  
import java.util.Map; 

public class Krb {  
    static Configuration conf = new Configuration();  
    static FileSystem fs = null;  
    
    

 
    public static void main(String[] args) throws IOException {  
    	conf.set("fs.defaultFS", "hdfs://cdh:9000");
    	conf.set("hadoop.security.authentication", "kerberos");
    	conf.set("dfs.namenode.kerberos.principal.pattern", "hadoop/*@HADOOP.COM"); 
    	
    	UserGroupInformation.setConfiguration(conf);
    	UserGroupInformation.loginUserFromKeytab("hadoop/cdh@HADOOP.COM", 
    	   "D:\\workspace\\Hadoop\\hdfs.keytab");
    	 
    	FileSystem fs = FileSystem.get(conf);
//    	boolean  flag = fs.mkdirs(new Path("/a//test4")); 
    	FileStatus[] fsStatus = fs.listStatus(new Path("/a"));
    	
//    	boolean flag = fs.delete(new Path("/a/test4"),true);
    	for(int i = 0; i < fsStatus.length; i++){
    	   System.out.println(fsStatus[i].getPath().toString());
    	}
    }  
   
} 
