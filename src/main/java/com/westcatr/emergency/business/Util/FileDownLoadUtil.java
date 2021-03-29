package com.westcatr.emergency.business.Util;

import cn.hutool.core.io.FileTypeUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 文件下载
 * 仅限PDF，WORD，EXCEL
 * <p>
 * liweijie
 */

public class FileDownLoadUtil {

    //下载某个文件夹最新的文件
    public static void download(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {

        File file = getNewFileForPath(path);
        if (file != null && file.exists())
            downloadSingleFile(file, request, response);

    }

    //下载指定文件
    public static void downloadSingleFile(File file, HttpServletRequest request, HttpServletResponse response) {
        String fileName = file.getName();

        //李伟杰
        String ext = FileTypeUtil.getType(file);
        String agent = (String) request.getHeader("USER-AGENT"); //判断浏览器类型
        try {
            if (agent != null && agent.indexOf("Fireforx") != -1) {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");   //UTF-8编码，防止输出文件名乱码
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }

            BufferedInputStream bis = null;
            OutputStream os = null;
            response.reset();
            response.setCharacterEncoding("utf-8");
            switch (ext) {
                case "pdf":
                    response.setContentType("application/pdf"); // pdf格式
                    break;
                case "docx":
                    response.setContentType("application/msword"); // word格式
                    break;
                case "xls":
                    response.setContentType("application/vnd.ms-excel"); // xls格式
                    break;
                default:
                    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");//xlsx
            }
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            try {
                bis = new BufferedInputStream(new FileInputStream(file));
                byte[] b = new byte[bis.available() + 1000];
                int i = 0;
                os = response.getOutputStream();   //直接下载导出
                while ((i = bis.read(b)) != -1) {
                    os.write(b, 0, i);
                }
                os.flush();
                os.close();
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static ArrayList<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        ArrayList<String> list = new ArrayList<String>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }


    public static List<File> getFiles(String realpath, List<File> files) {

        File realFile = new File(realpath);
        if (realFile.isDirectory()) {
            File[] subfiles = realFile.listFiles();
            for (File file : subfiles) {
                if (file.isDirectory()) {
                    getFiles(file.getAbsolutePath(), files);
                } else {
                    files.add(file);
                }
            }
        }
        return files;
    }


    public static File getNewFileForPath(String path) {
        List<File> list = getFiles(path, new ArrayList<File>());
        if (list.size() != 0) {
            if (list.size() > 0) {

                Collections.sort(list, new Comparator<File>() {
                    public int compare(File file, File newFile) {
                        if (file.lastModified() < newFile.lastModified()) {
                            return 1;
                        } else if (file.lastModified() == newFile.lastModified()) {
                            return 0;
                        } else {
                            return -1;
                        }

                    }
                });

            }
            if (list.get(0) != null)
                return list.get(0);
        }


        return null;
    }

}
