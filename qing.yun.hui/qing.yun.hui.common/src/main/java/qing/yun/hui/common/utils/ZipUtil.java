package qing.yun.hui.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/***
 ** @Description: zip工具类
 ** @author: qing.yunhui
 ** @email: 280672161@.qq.com
 ** @dateTime: Nov 20, 2015 10:12:19 AM
 ** @version: V1.0
 ***/
public class ZipUtil {
	
	private static final String EXTENSION_TMP = ".tmp";
	private static final String EXTENSION_NAME = ".zip";
	private static final int BUFFER_SIZE = 2048;
	
	/**
	 * 递归对某文件或某目录下的所有文件进行压缩
	 * 
	 * @param firstAbsFilename
	 * @param inFile
	 * @param zos 输出的ZipOutputStream对象
	 * @throws IOException
	 */
	private static void recursionPath(String firstAbsFilename, File inFile, ZipOutputStream zos) throws IOException {
		BufferedInputStream bis = null;
		FileInputStream fis = null;
		try {
			File[] files = null;
			// 是目录, 递归处理其下的文件
			if (inFile.isDirectory()) {
				files = inFile.listFiles();
				for (int i = 0; i < files.length; i++) {
					recursionPath(firstAbsFilename, files[i], zos);
				}
			} else {
				// 是文件,写入输出流
				// 待压缩文件的第一层文件的文件名
				int idx = firstAbsFilename.lastIndexOf(File.separator);
				String firstFilename = firstAbsFilename.substring(idx + 1);
				// 当前待压缩绝对文件名
				String inAbsFilename = inFile.getCanonicalPath().trim();
				// 当前待压缩相对于第一层文件的文件名
				String contextFilename = "";
				if (inAbsFilename.length() > firstAbsFilename.length()) {
					contextFilename = inAbsFilename.substring(firstAbsFilename.trim().length() + 1);
				}
				String entryName = null;
				if ("".equals(contextFilename)) {
					entryName = firstFilename;
				} else {
					entryName = firstFilename + File.separator + contextFilename;
				}
				byte data[] = new byte[BUFFER_SIZE];
				fis = new FileInputStream(inFile);
				// 将文件转化压缩流
				bis = new BufferedInputStream(fis, BUFFER_SIZE);
				ZipEntry entry = new ZipEntry(entryName);
				zos.putNextEntry(entry);
				int count;
				while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
					zos.write(data, 0, count);
				}
				zos.flush();
				bis.close();
				fis.close();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (fis != null)
				fis.close();
		}
	}

	/**
	 * 解压zip格式的压缩文件到当前文件夹,返回文件列表
	 * 
	 * @param inputZipFile 读入的需要解压的文件
	 * @return
	 */
	public static List<File> unzip(File inputZipFile) throws IOException {
		List<File> fileList = new ArrayList<File>();
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		ZipFile zipFile = null;
		try {
			// 默认输出当前目录
			File outFile = inputZipFile.getParentFile();
			zipFile = new ZipFile(inputZipFile);
			Enumeration<?> emu = zipFile.entries();
			while (emu.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) emu.nextElement();
				// 在此, 只是把目录作为一个file读出来, 其下的文件将在后面被迭代到
				if (entry.isDirectory()) {
					new File(outFile.getCanonicalPath() + File.separator + entry.getName()).mkdirs();
					continue;
				}
				File file = new File(outFile.getCanonicalPath() + File.separator + entry.getName());
				File parent = file.getParentFile();
				if (parent != null && (!parent.exists())) {
					parent.mkdirs();
				}
				fos = new FileOutputStream(file);
				bos = new BufferedOutputStream(fos, BUFFER_SIZE);
				bis = new BufferedInputStream(zipFile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER_SIZE];
				while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
					bos.write(data, 0, count);
				}
				bos.flush();
				bos.close();
				fos.close();
				bis.close();
				fileList.add(file);
			}
			zipFile.close();
		} finally {
			if (bos != null)
				bos.close();
			if (fos != null)
				fos.close();
			if (bis != null)
				bis.close();
			if (zipFile != null)
				zipFile.close();
		}
		return fileList;
	}

	/**
	 * 对某文件进行压缩, 返回压缩后的文件;
	 * 
	 * @param filesPath
	 * @return
	 */
	public static File zip(File filesPath) throws IOException {
		String outFilename = null;
		if (filesPath.isDirectory()) {
			outFilename = filesPath.getCanonicalPath() + EXTENSION_NAME;
		} else {
			int idx = filesPath.getCanonicalPath().lastIndexOf('.');
			outFilename = filesPath.getCanonicalPath().substring(0, idx) + EXTENSION_NAME;
		}
		File outFile = new File(outFilename);
		if (zip(filesPath, outFile)) {
			return outFile;
		}
		return null;
	}

	/**
	 * 压缩文件zip
	 * 
	 * @param filesPath
	 * @param outZipFilePath
	 * @return
	 */
	public synchronized static boolean zip(File filesPath, File outZipFilePath) throws IOException {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		BufferedInputStream bis = null;
		FileInputStream fis = null;
		try {
			// 压缩先输出临时文件. 压缩完成, 将临时文件改名为zip文件名
			File tmpOutFile = new File(outZipFilePath.getCanonicalPath() + EXTENSION_TMP);
			fos = new FileOutputStream(tmpOutFile);
			zos = new ZipOutputStream(new BufferedOutputStream(fos));
			recursionPath(filesPath.getCanonicalPath(), filesPath, zos);
			zos.flush();
			zos.close();
			if (outZipFilePath.exists()) {
				outZipFilePath.delete();
			}
			// 将临时文件改名为zip文件名
			tmpOutFile.renameTo(outZipFilePath);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (zos != null)
				zos.close();
			if (fos != null)
				fos.close();
			if (bis != null)
				bis.close();
			if (fis != null)
				fis.close();
		}
		return true;
	}

	public static void zip(String zipName, File... files) {
		ZipOutputStream zos = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			File zipFile = new File(zipName);
			zipFile.createNewFile();//zipName:D:/test/test.txt 如果test目录下不存在test.txt则创建空文件,如果不存在test目录则创建失败，如果test目录下存在test.txt则创建失败。
			zos = new ZipOutputStream(new FileOutputStream(zipFile));
			byte data[] = new byte[BUFFER_SIZE];
			for (File file : files) {
				fis = new FileInputStream(file);
				// 将文件转化压缩流
				bis = new BufferedInputStream(fis, BUFFER_SIZE);
				zos.putNextEntry(new ZipEntry(file.getName()));
				int count;
				while ((count = bis.read(data, 0, BUFFER_SIZE)) != -1) {
					zos.write(data, 0, count);
				}
				zos.closeEntry();
				fis.close();
				bis.close();
			}
			zos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	public static void main(String[] args) throws IOException{
		//one:begin
		/*String zipFileNamePath="F:/pifu.zip";
		File file=new File("F:/test/");
		File file2=new File("E:/360Downloads/test.txt");
		zip(zipFileNamePath, file,file2);*/
		//one:end
		//two:begin
		File filesPath = new File("F:/test");
		zip(filesPath, new File("F:\\test.zip"));
		//two:end
	}

}
