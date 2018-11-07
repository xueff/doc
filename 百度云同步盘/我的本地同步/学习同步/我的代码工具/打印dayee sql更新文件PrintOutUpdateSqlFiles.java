package sql.toString;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * dayee数据库文件更新文件夹输出控制台
 * ① 设置起始时间
 * ②打印类型    console company
 * 
 * ③！！！注意输出 文件名  console 拼写错误 情况
 * 控制台打印对应路径下sql文件
 * @author cooltain
 */
public class PrintOutUpdateSqlFiles {
    //////////////////////////////////////////////////手动设置起始//////////////////////////////////////////////////////
    // 本地SQL项目文件存放的路径    1  2 3，
    private static String LOCALE_PROJECT_PATH = "D:\\ProgStudio\\workspacerelease1116\\release1116_wintalent_other";
    private static String LOCALE_PROJECT_PATH2 = "\\sql\\patch\\8x";
    private static String LOCALE_PROJECT_PATH3 = "\\8.v1.08";
    
    //文件过滤起始日期
    private static int dateStart = 20171201;
    
    private static boolean printType = true;  //打印类型 false console    true:company
    //////////////////////////////////////////////////手动设置结束//////////////////////////////////////////////////////
    
    
    
    
    private static StringBuffer fileString = new StringBuffer(); ///带输出文本
    private static Map<Integer,List<String>> fileDateAndNameMap = new HashMap<Integer,List<String>>(); ///（已过滤）日期，文件名
    private static List<String> fileNameConsoleOrdered = new LinkedList<String>(); //console  按日期排序文名h
    private static List<String> fileNameCompanyOrdered = new LinkedList<String>(); //company  按日期排序文件名
    
    private static String getCompletePath(){
        return LOCALE_PROJECT_PATH +LOCALE_PROJECT_PATH2 +LOCALE_PROJECT_PATH3;
    }
    
    public static void main(String[] args) throws Exception {
        String completePath = getCompletePath();
        
        //获取pathName的File对象  
        File dirFile = new File(completePath);  
        //判断该文件或目录是否存在，不存在时在控制台输出提醒  
        if (!dirFile.exists()) {  
            System.out.println("do not exit");  
            return ;  
        }  
        //判断如果不是一个目录，就判断是不是一个文件，时文件则输出文件路径  
        if (!dirFile.isDirectory()) {  
            if (dirFile.isFile()) {  
                System.out.println(dirFile.getCanonicalFile());  
            }  
            return ;  
        }  
          
        //获取此目录下的所有文件名与目录名  
        String[] fileList = dirFile.list(); 
        
        chooseNeedFiles(fileList);
        
        if (printType) {
            for (String filename : fileNameCompanyOrdered) {  
                //文件名
                File file = new File(dirFile.getPath(),filename);  
                printOutFiles(file);
            } 
        }else{
            for (String filename : fileNameConsoleOrdered) {  
                //文件名
                File file = new File(dirFile.getPath(),filename);  
                printOutFiles(file);
            }  
        }
        
        outPutInFile();
        
       
    }
    private static void outPutInFile() {
        byte[] buff=new byte[]{};  
        FileOutputStream out;
        try   
        {  
            buff=fileString.toString().getBytes();  
            if (printType) {
                    out = new FileOutputStream("D://conpany-sql.txt");  
            }else{
                    out = new FileOutputStream("D://console-sql.txt");  
                    //文件名
            }
            out.write(buff,0,buff.length);  
        }   
        catch (FileNotFoundException e)   
        {  
            e.printStackTrace();  
        }  
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
 
    }

    private static void printOutFiles(File file) {
        fileString.append("## "+file.getPath());
        System.out.println("## "+file.getPath());
        if(!file.isFile())
            return;
        InputStream in = null;  
        try {  
            // 根据文件创建文件的输入流  
            in = new FileInputStream(file);  
            // 创建字节数组  
            byte[] data = new byte[1024];  
            // 读取内容，放到字节数组里面  
            in.read(data);  
            System.out.println(new String(data));  
            fileString.append(new String(data));
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                // 关闭输入流  
                in.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        
    }
    
    

    /**
     * 获取要更新的文件(已排序)
     * @param dirFile
     * @param fileList
     */
	private static void chooseNeedFiles(String[] fileList) {
	    for (String fileName : fileList) {
	        outofDateFileRemove(fileName); 
        }
	    
	    Date now = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	    Integer nowDateInt = Integer.valueOf(formatter.format(now));
	    
	    
	    for (int i = dateStart; i < nowDateInt; i++) {
	        if(Integer.valueOf(String.valueOf(i).substring(6,8)) > 30){
	            i += 68;
//	            System.out.println("****"+i);
	            continue;
	        }
	        if(Integer.valueOf(String.valueOf(i).substring(4, 6)) >12){
	            i = Integer.valueOf((String.valueOf(i).substring(0, 4) + "0100"))+10000;
//	            System.out.println("/////////////////"+i);
	            continue;
	        }
	        List<String> oneDayFiles = fileDateAndNameMap.get(i);
	        if(oneDayFiles != null){
	            for (String fileName : oneDayFiles) {
	                if(fileName.contains("console")){
	                    fileNameConsoleOrdered.add(fileName);
	                }else{
	                    fileNameCompanyOrdered.add(fileName);
//	                    System.out.println(fileName);
	                }
	            }
	            
	        }
	     }
	       
	    
//	   
    }
	
	private static void outofDateFileRemove(String fileName) {
	    //1.过滤日期
	    int datafile = 0;
        try {
            datafile = Integer.valueOf(fileName.substring(0, 8));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        if(datafile <dateStart)
            return ;

        if(fileDateAndNameMap.containsKey(datafile)){
            fileDateAndNameMap.get(datafile).add(fileName);
        }else{
            List<String> filesNameList = new ArrayList<String>();
            filesNameList.add(fileName);
            fileDateAndNameMap.put(datafile, filesNameList);
        }
        
//        System.out.println("筛选的file=="+fileName);
	}

}
