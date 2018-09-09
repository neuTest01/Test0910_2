package com.zhihu.servlet;

import com.alibaba.fastjson.JSONObject;
import com.zhihu.dao.pojo.*;
import com.zhihu.service.FileService;
import com.zhihu.service.LoginService;
import com.zhihu.service.impl.FileServiceImpl;
import com.zhihu.service.impl.LoginServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017-04-19.
 */
@WebServlet("/fileUploadServlet")
public class FileUploadServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private FileService fileService = new FileServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
        String savePath = this.getServletContext().getRealPath("/upload");
        File file = new File(savePath);
        String filePath = "";
        //判断上传文件的保存目录是否存在
        if (!file.exists() && !file.isDirectory()) {
            //创建目录
            file.mkdir();
        }
        //消息提示
        String message = "";
        try{
            //使用Apache文件上传组件处理文件上传步骤：
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(request)){
            //按照传统方式获取数据
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(request);
            for(FileItem item : list){
            //如果fileitem中封装的是普通输入项的数据
                if(item.isFormField()){
                    String name = item.getFieldName();
                    //解决普通输入项的数据的中文乱码问题
                    String value = item.getString("UTF-8");
                    //value = new String(value.getBytes("iso8859-1"),"UTF-8");
                }else{//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    if(filename==null || filename.trim().equals("")){
                        continue;
                    }
                    String originalFileName = filename.substring(filename.lastIndexOf("\\")+1);
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = new Date().getTime() + filename.substring(filename.lastIndexOf("."));
                    //获取item中的上传文件的输入流
                    InputStream in = item.getInputStream();
                    //创建一个文件输出流
                    FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while((len=in.read(buffer))>0){
                    //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //关闭输入流
                    in.close();
                    //关闭输出流
                    out.close();
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    filePath = request.getContextPath()+"/upload/"+filename;

                    //插入数据到文件表
                    com.zhihu.dao.pojo.File uploadFile = new com.zhihu.dao.pojo.File();
                    uploadFile.setFileName(filename);
                    uploadFile.setFileRealName(originalFileName);
                    uploadFile.setFileUrl(filePath);
                    uploadFile.setFileType(item.getContentType());
                    Integer imageId = fileService.insert(uploadFile);
                    //Integer imageId = null;
                    //修改用户表头像信息
                    User loginUser = (User)request.getSession().getAttribute("userInfo");
                    User user = new User();
                    user.setUserId(loginUser.getUserId());
                    user.setUserImageId(imageId);
                    loginService.modifyUserInfo(user);

                    User u = new User();
                    u.setUserId(loginUser.getUserId());
                    request.getSession().setAttribute("userInfo",loginService.Login(u));
                    message = "文件上传成功！";
                }
            }
        }catch (Exception e) {
            message= "文件上传失败！";
            e.printStackTrace();

        }
        Map<String,String> map = new HashMap<String,String>();
        map.put("msg",message);
        map.put("data",filePath);
        PrintWriter out = response.getWriter();
        out.print(JSONObject.toJSONString(map));
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
