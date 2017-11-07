package com.zyc.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * @author Jerry Zhang Create date 2011-3-30
 * @version 1.0.0
 * @email jerry.zhang@jamboree.com.cn
 */
public class FileUtil {

	private static Logger logger = Logger.getLogger("FileUtil");

	public static byte[] readFileToByteArray(File f) {
		try {

			BufferedInputStream bin = new BufferedInputStream(
					new FileInputStream(f));
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			while (true) {
				int readCount = bin.read(buffer);
				if (readCount == -1)
					break;
				bout.write(buffer, 0, readCount);
			}
			bout.flush();
			bout.close();
			bin.close();
			return bout.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 创建指定目录
	 * 
	 * @param dir
	 */
	public static void mkDir(String dir) {
		File file = new File(dir);
		if (!file.isDirectory())
			;
		file.mkdir();

	}

	/**
	 * 创建多级目录
	 * 
	 * @param dir
	 */
	public static void mkdirs(String dir) {
		File file = new File(dir);
		if (!file.isDirectory())
			;
		file.mkdirs();
	}

	/**
	 * 是否存在该文件
	 * 
	 * @param strFile
	 * @return
	 */
	public static boolean isExistFile(String strFile) {
		File file = new File(strFile);
		if (file.exists() && file.isFile())
			return true;
		return false;

	}

	/**
	 * 是否存在该目录
	 * 
	 * @param strDir
	 * @return
	 */
	public static boolean isExistDir(String strDir) {
		File file = new File(strDir);
		if (file.exists() && file.isDirectory())
			return true;
		return false;

	}

	/**
	 * 重命名文件
	 * 
	 * @return
	 */
	public static void renameFile(String dir, String filename) {
		File file = new File(dir);
		String fileDir = dir.substring(0, dir.lastIndexOf("/"));
		File dest = new File(fileDir + File.separator + filename);
		file.renameTo(dest);
	}

	/**
	 * 删除目录下的所有文件及子目录
	 * 
	 * @param dir
	 */
	public static void delFiles(String dir) {
		File files = new File(dir);
		if (!files.exists()) {
			return;
		}
		File[] file = files.listFiles();
		for (int i = 0; i < file.length; i++) {
			String delDir = dir + File.separator + file[i].getName();
			File delFileDir = new File(delDir);
			if (delFileDir.exists() && delFileDir.isDirectory()) {
				delFiles(delDir);
			}
			file[i].delete();
		}
	}

	/**
	 * 删除文件夹及文件夹下面的所有文件及文件夹
	 * 
	 * @param dir
	 */
	public static void delDirFiles(String dir) {
		File files = new File(dir);
		if (!files.exists()) {
			return;
		}
		if (files.isFile()) {
			files.delete();
		} else if (files.isDirectory()) {
			File[] file = files.listFiles();
			for (int i = 0; i < file.length; i++) {
				String delDir = dir + File.separator + file[i].getName();
				File delFileDir = new File(delDir);
				if (delFileDir.exists() && delFileDir.isDirectory()) {
					delFiles(delDir);
				}
				file[i].delete();
			}
		}
		files.delete();
	}

	/**
	 * 删除文件
	 * 
	 * @param dir
	 * @param fileName
	 */
	public static void delFile(String dir, String fileName) {
		String fileNamePath = dir + File.separator + fileName;
		delFile(fileNamePath);
	}

	/**
	 * 
	 * @param path
	 */
	public static void delFile(String path) {
		File delFile = new File(path);
		if (delFile.exists() && delFile.isFile()) {
			delFile.delete();
		}
	}

	public static String readContent(String dir, String fileName) {
		String pathName = dir + File.separator + fileName;
		File file = new File(pathName);
		String content = "";
		if (!file.exists()) {
			if (logger.isInfoEnabled())
				logger.error("文件不存在 路径：" + pathName);
			return content;
		}

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(pathName), "UTF-8"));
			String s = new String();
			while ((s = in.readLine()) != null)
				content = content + s + "\n";
			in.close();
			return content;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件未找到 路径：" + pathName + " 信息：" + e.getMessage());
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件读写发生错误 路径：" + pathName + " 信息："
						+ e.getMessage());
			return "";
		}
	}

	public static String readContent(String filepath) {
		File file = new File(filepath);
		String content = "";
		if (!file.exists()) {
			if (logger.isInfoEnabled())
				logger.error("文件不存在 路径：" + filepath);
			return content;
		}

		try {

			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(filepath), "UTF-8"));
			String s = new String();
			while ((s = in.readLine()) != null)
				content = content + s + "\n";
			in.close();
			// writeFile("d:/123.html", content);
			return content;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件未找到 路径：" + filepath + " 信息：" + e.getMessage());
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件读写发生错误 路径：" + filepath + " 信息："
						+ e.getMessage());
			return "";
		}
	}

	/**
	 * copy 源目录下的到所有文件到目标目录下
	 * 
	 * @param sourceDir
	 *            源目录
	 * @param targetDir
	 *            目标目录
	 */
	public static void copyFiles(String sourceDir, String targetDir) {
		File sourceFiles = new File(sourceDir);
		String[] fileNames = sourceFiles.list();
		if (fileNames == null || fileNames.length == 0) {
			if (logger.isInfoEnabled())
				logger.error("源目录 不存在：" + sourceDir);
			return;
		}
		for (int i = 0; i < fileNames.length; i++) {

			String srcName = sourceDir + File.separator + fileNames[i];
			File srcFileDir = new File(srcName);
			if (srcFileDir.isDirectory()) {
				String targetName = targetDir + File.separator + fileNames[i];
				File targetFileDir = new File(targetName);
				if (!targetFileDir.exists() || !targetFileDir.isDirectory()) {
					targetFileDir.mkdir();
				}
				copyFiles(srcName, targetName);
				continue;
			}
			String targetName = targetDir + File.separator + fileNames[i];
			readWriteFile(srcName, targetName);
		}
	}

	/**
	 * copy 源目录下的到指定文件到指定目录下
	 *
	 * 目标目录
	 */
	public static void copyFile(String dir, String filename, String fileNewname) {
		File sourceFiles = new File(dir);
		String[] fileNames = sourceFiles.list();
		if (fileNames == null || fileNames.length == 0) {
			if (logger.isInfoEnabled())
				logger.error("源目录 不存在：" + dir);
			return;
		}
		String srcName = dir + filename;
		String targetFileName = fileNewname;
		String targetName = dir + targetFileName;
		readWriteFile(srcName, targetName);
	}

	/**
	 * 
	 * @param sourceName
	 *            源文件全路径
	 * @param targetName
	 *            目标文件全路径
	 */
	public static void readWriteFile(String sourceName, String targetName) {
		try {
			File srcFile = new File(sourceName);
			File targetFile = new File(targetName);
			if (!srcFile.exists() || !srcFile.isFile()) {
				srcFile.createNewFile();
			}
			if (!targetFile.exists() || !targetFile.isFile()) {
				targetFile.createNewFile();
			}
			InputStream in = new FileInputStream(srcFile);
			OutputStream os = new FileOutputStream(targetFile);
			readWriteFile(in, os);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件未找到 源文件路径：" + sourceName + " 目标文件路径:"
						+ targetName + " 信息：" + e.getMessage());
			// TODO Auto-generated catch block
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件未找到 源文件路径：" + sourceName + " 目标文件路径:"
						+ targetName + " 信息：" + e.getMessage());
		}
	}

	/**
	 * 
	 * @param in
	 * @param targetName
	 *            目标文件全路径
	 */
	public static void readWriteFile(InputStream in, String targetName) {
		try {
			File targetFile = new File(targetName);
			if (!targetFile.exists() || !targetFile.isFile()) {
				targetFile.createNewFile();
			}
			OutputStream os = new FileOutputStream(targetFile);
			readWriteFile(in, os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件未找到 目标文件路径:" + targetName + " 信息："
						+ e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件发生读写错误 目标文件路径:" + targetName + " 信息："
						+ e.getMessage());
		}
	}

	/**
	 * 
	 * @param in
	 * @param targetName
	 */
	public static void readWriteFile(byte[] in, String targetName) {
		try {
			File targetFile = new File(targetName);
			if (!targetFile.exists() || !targetFile.isFile()) {
				targetFile.createNewFile();
			}
			OutputStream os = new FileOutputStream(targetFile);
			os.write(in);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件未找到 目标文件路径:" + targetName + " 信息："
						+ e.getMessage());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件发生读写错误 目标文件路径:" + targetName + " 信息："
						+ e.getMessage());

		}
	}

	/**
	 * 
	 * @param sourceName
	 * @param os
	 */
	public static void readWriteFile(String sourceName, OutputStream os) {
		try {
			File srcFile = new File(sourceName);
			if (!srcFile.exists() || !srcFile.isFile()) {
				srcFile.createNewFile();
			}
			InputStream in = new FileInputStream(srcFile);
			readWriteFile(in, os);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件未找到 源文件路径:" + sourceName + " 信息："
						+ e.getMessage());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.error("文件发生读写错误 源文件路径:" + sourceName + " 信息："
						+ e.getMessage());

		}
	}

	/**
	 * 
	 * @param in
	 * @param os
	 */
	public static void readWriteFile(InputStream in, OutputStream os) {
		try {
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);// 将文件写入服务器
			}
			os.flush();
			os.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 文件上传
	 * 
	 * @param is
	 * @param filepath
	 * @param filename
	 * @throws IOException
	 */
	public static void uploadFile(InputStream is, String filepath,
			String filename) throws IOException {
		OutputStream os;
		try {
			String file = filepath + "/" + filename;
			os = new FileOutputStream(file);
			// 8k缓存数据
			byte[] buffer = new byte[1024 * 8];
			// 设置读进缓存的字节数
			int len;
			while ((len = is.read(buffer)) != -1) {
				// 将缓存数据写入磁盘
				os.write(buffer, 0, len);
			}
			// 关闭输出流
			os.close();
			// 关闭输入流
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @param fileNames
	 * @param separateFlag
	 * @return
	 */
	public static String[] separateFileNames(String fileNames,
			String separateFlag) {
		return fileNames.split(separateFlag);
	}

	/**
	 * 用操作系统的目录分离符
	 * 
	 * @param str
	 * @return
	 */
	public static String repace(String str) {
		String realPath = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			String tmp = "";
			if (ch == '/') {
				tmp = File.separator;
			} else if (ch == '\\') {
				tmp = File.separator;
			} else {
				tmp = tmp + ch;
			}
			realPath = realPath + tmp;
		}
		return realPath;
	}

	/**
	 * 把反斜杆替换成斜杆
	 * 
	 * @param str
	 * @return
	 */
	public static String repaceBySlash(String str) {
		String realPath = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			String tmp = "";
			if (ch == '\\') {
				tmp = "/";
			} else {
				tmp = tmp + ch;
			}
			realPath = realPath + tmp;
		}
		return realPath;
	}

	/**
	 * 把斜杆替换成反斜杆
	 * 
	 * @param str
	 * @return
	 */
	public static String repaceByBackslash(String str) {
		String realPath = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			String tmp = "";
			if (ch == '/') {
				tmp = "\\";
			} else {
				tmp = tmp + ch;
			}
			realPath = realPath + tmp;
		}
		return realPath;
	}

	/**
	 * 指定字符编码的写文件方式
	 * 
	 * @param path
	 * @param str
	 * @param codec
	 *            alter by wzb
	 */
	public static void writeFile(String path, String str, String codec) {
		try {
			if (codec == null || codec.trim().equals(""))
				codec = "UTF-8";
			FileOutputStream fos = new FileOutputStream(path);
			Writer out = new OutputStreamWriter(fos, codec);
			out.write(str);
			out.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.info(e.getMessage());
		}
	}

	public static void writeFile(String path, String str) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			if (logger.isInfoEnabled())
				logger.info(e.getMessage());
		}
	}

	public static String getHttpPath(HttpServletRequest request) {

		String root = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + root;
		return basePath;

	}

	/**
	 * 将文件名改名为filename_date.txt
	 * 
	 * @param filename
	 * @return
	 */
	public static String parseFileName(String filename) {
		if (!StringUtils.isEmpty(filename) && filename.lastIndexOf(".") != -1) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
			Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
			String dateString = format.format(now);
			filename = filename
					.substring(0, filename.lastIndexOf("."))
					.concat("_")
					.concat(dateString)
					.concat(filename.substring(filename.lastIndexOf("."),
							filename.length()));
			return filename;
		}
		return null;
	}

	/**
	 * 将文件名改名为date.*
	 * 
	 * @param filename
	 * @return
	 */
	public static String getDateStringFileName(String filename) {
		if (!StringUtils.isEmpty(filename) && filename.lastIndexOf(".") != -1) {
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
			Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
			String dateString = format.format(now);
			String returnFileName = "";
			returnFileName = returnFileName.concat(dateString).concat(
					filename.substring(filename.lastIndexOf("."),
							filename.length()));
			return returnFileName;
		}
		return null;
	}

	/**
	 * 得到系统时间为yyyyMMddHHmmssSS格式
	 * 
	 * @return
	 */
	public static String getDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSS");
		Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
		String dateString = format.format(now);
		return dateString;
	}

	/**
	 * 保存文件
	 * 
	 * @param is
	 *            输入流
	 * @param filepath
	 *            文件保存路径
	 * @param filename
	 *            文件名
	 * @throws IOException
	 */
	public static void saveFile(InputStream is, String filepath, String filename)
			throws IOException {
		// String fileEx = this.getFileEx(fileName);
		// if("jpg|png|gif".contains(fileEx)) throw new
		// IOException("非法文件后缀名:"+fileEx);
		File targetDir = new File(filepath);
		if (!targetDir.exists() || !targetDir.isDirectory()) {
			targetDir.mkdirs();
		}
		File outFile = new File(targetDir, filename);
		OutputStream os = new FileOutputStream(outFile);
		readWriteFile(is, os);
	}

	/**
	 * 
	 * @date 2011-10-18
	 * @param fileName
	 *            文件名
	 * @return 文件后缀如 a.txt返回小写txt
	 * @desc
	 */
	public static String getFileEx(String fileName) {
		String ret = "";
		if (fileName != null) {
			int pos = fileName.lastIndexOf(".");
			if (pos > -1) {
				ret = fileName.substring(pos + 1);
			}
		}
		return ret.trim().toLowerCase();
	}

	public static void main(String[] args) {
		String filePath = "d:/abc/123.html";
		if (!FileUtil.isExistDir(filePath.substring(0,
				filePath.lastIndexOf("/")))) {
			FileUtil.mkdirs(filePath);
		}
		FileUtil.writeFile(filePath, "testhtml");
	}



	public static String zipfile(String uploadFilePath) throws Exception {
		// TODO Auto-generated method stub
		return ZipUtils.compress(uploadFilePath);
	}

	public static void download(String urlString, String filename,
			String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		// 输入流
		InputStream is = con.getInputStream();
		System.out.println(con.getContent());
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() +  File.separator + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
	}

}
