package com.sinosoft.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

public class FileTool {
    public static File createfile(String path) {
        File file = new File(path);
        return file;
    }

    public static boolean isExist(String path) {
        File file = new File(path);
        return file.exists();
    }

    public static InputStream getFileInputStream(String path) {
        try {
            if (isExist(path)) {
                InputStream is = new FileInputStream(path);
                return is;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine, String newname) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        if (isOnLine) { // 在线打开方式
            URL u = new URL("file:///" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + new String(newname.getBytes("gb2312"), "iso8859-1"));
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + new String(newname.getBytes("gb2312"), "iso8859-1"));
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }

    public static void downLoad(InputStream f, HttpServletResponse response, String newname) throws Exception {

        if (f == null) {
            response.sendError(404, "File not found!");
            return;
        }

        BufferedInputStream br = new BufferedInputStream(f);
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        // 纯下载方式
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename=" + newname);

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }

    /**
     * 在线看文件
     * 
     * @param filePath
     * @param fileType
     * @param response
     * @param newname
     * @throws Exception
     */
    public static void viewfile(String filePath, String ContentType, HttpServletResponse response, String newname) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        // 在线打开方式
        URL u = new URL("file:///" + filePath);
        // response.setContentType(u.openConnection().getContentType());

        response.setContentType(ContentType);

        // response.setContentType("application/vnd.ms-excel; charset=gb2312");

        response.setHeader("Content-Disposition", "inline; filename=" + new String(newname.getBytes("gb2312"), "iso8859-1"));

        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }

    /**
     * 把文本编码为Html代码
     * 
     * @param target
     * @return 编码后的字符串
     */
    public static String htmEncode(String target) {
        StringBuffer stringbuffer = new StringBuffer();
        int j = target.length();
        for (int i = 0; i < j; i++) {
            char c = target.charAt(i);
            switch (c) {
            case 60:
                stringbuffer.append("&lt;");
                break;
            case 62:
                stringbuffer.append("&gt;");
                break;
            case 38:
                stringbuffer.append("&amp;");
                break;
            case 34:
                stringbuffer.append("&quot;");
                break;
            case 169:
                stringbuffer.append("&copy;");
                break;
            case 174:
                stringbuffer.append("&reg;");
                break;
            case 165:
                stringbuffer.append("&yen;");
                break;
            case 8364:
                stringbuffer.append("&euro;");
                break;
            case 8482:
                stringbuffer.append("&#153;");
                break;
            case 13:
                if (i < j - 1 && target.charAt(i + 1) == 10) {
                    stringbuffer.append("<br>");
                    i++;
                }
                break;
            case 32:
                if (i < j - 1 && target.charAt(i + 1) == ' ') {
                    stringbuffer.append(" &nbsp;");
                    i++;
                    break;
                }
            default:
                stringbuffer.append(c);
                break;
            }
        }
        return new String(stringbuffer.toString());
    }

    public static void main(String[] arg) {
        File file = new File("F:\\js\\json_parse.js");
        BakFile(file);

    }

    /**
     * 备份一个文件
     * 
     * @param file
     */
    public static void BakFile(File file) {
        if (file.isFile()) {
            String filename = file.getName();
            String p = file.getParent();
            String newfilename = p + "\\bak." + filename;
            
            File bakfile = createfile(newfilename);
            CopyFile(file, bakfile);
        }
    }

    public static void CopyFile(File oldFile, File newFile) {
        FileInputStream input = null;
        FileOutputStream output = null;
        try {
            input = new FileInputStream(oldFile);
            output = new FileOutputStream(newFile);

            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (output != null) {
                    output.close();
                }
            } catch (IOException ioe) {
                // ignore
            }
        }

    }
    
    public static void CompressJS(File file,String newname){
        
//        Runtime.getRuntime().exec(commandStr)
    }

}