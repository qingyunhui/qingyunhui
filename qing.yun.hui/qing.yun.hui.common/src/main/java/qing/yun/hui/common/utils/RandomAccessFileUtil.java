
package qing.yun.hui.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;


/**
 * <p>不能对二进制的文件进行截取(如果对二进制流进行读取某一段后，打开文件时会出错...)</p>
 * <p>该类慎用。</p>
 * @Date Create on 2016年11月11日
 * @author <a href="mailto:qingyunhui@zuozh.com">qingyunhui</a>
 * @since version1.0 Copyright 2015 ZZJR All Rights Reserved.
 */
public class RandomAccessFileUtil {
	
	

	
	
	private long bytes=1024;//字节
	private long KB=1*bytes;//1kb=1024byte
	private long MB=1*KB*bytes;//1M=1024kb
	private long G=1*MB*KB*bytes;//1G=1024Mb
	
	public static void main(String[] args){
		RandomAccessFileUtil raf=new RandomAccessFileUtil();
		raf.writeFile();
	}
	
	public void writeFile(){
		File file=new File("F:/test/test.docx");
		RandomAccessFile raf=null;
		FileOutputStream os=null;
		try {
			raf=new RandomAccessFile(file, "rw");
			os=new FileOutputStream("F:/test/test_tmp.docx");
			long size=file.length();//byte
			long point=0L;
			
			int cursize=0;
			
			long maxSize=0L;
			
			if(size/G>0){
				//1个G为单位
				point=size/1000;//前十分之一
			}else if(size/MB>0){
				//M为单位
				if(size/MB>=500){
					//大于等于500M的文件处理
					point=size/50;
				}else if(size/MB>=200){
					//大于等于200M的文件处理
					point=size/20;
				}else{
					point=size/2;
				}
			}else if(size/KB>0){
				//1Kb为单位
				point=size/2;
			}else{
				//byte为单位
				point=size;
			}
			byte[] b=new byte[1024];
			System.out.println("size:"+size+",point:"+point);
			while((cursize=raf.read(b))!=-1){
				System.out.println("cursize:"+cursize+",b:"+b.length);
				os.write(b);
				if(maxSize>=point){
					break;
				}
				maxSize+=(long)cursize;
			}
			raf.close();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

//11479,2477 byte  114792477


