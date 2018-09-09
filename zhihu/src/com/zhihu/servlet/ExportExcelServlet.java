package com.zhihu.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zhihu.dao.pojo.User;
import com.zhihu.service.UserService;
import com.zhihu.service.impl.UserServiceImpl;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Servlet implementation class ExportExcelServlet
 */
@WebServlet("/ExportExcelServlet")
public class ExportExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private WritableWorkbook wwb = null;

	private WritableSheet sheetk = null;
	
	private UserService user = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list = user.getUserList();
		try {
			exportCheckOrgMember(list,response);
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		exportExcel(response,wwb);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	private void exportCheckOrgMember(List<User> list,HttpServletResponse response)
			throws IOException, RowsExceededException, WriteException {
		
		response.setContentType("application/ms-excel");
		String sheetName = "用户信息列表";//文件名==》用户信息列表
		sheetName = new String(sheetName.getBytes(),"iso8859-1");
		  
		response.addHeader("Content-Disposition", "attachment;filename="+ sheetName + ".xls");

		OutputStream os = response.getOutputStream();
		wwb = Workbook.createWorkbook(os);
		wwb.setProtected(false);

		//EXECL中的一个sheet
		sheetk = wwb.createSheet("用户信息", 0);

		//============设置execl表的一些属性===========
		WritableFont wf = new WritableFont(WritableFont.ARIAL, 13,WritableFont.BOLD, false);
		WritableCellFormat wcf = new WritableCellFormat(wf);
		WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 13,WritableFont.NO_BOLD, false);
		WritableCellFormat wcf1 = new WritableCellFormat(wf1);
		wcf.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
		wcf1.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);
		wcf1.setVerticalAlignment(VerticalAlignment.CENTRE);
		
		String[] title = {"", "账号", "姓名", "住址", "出生年月", "教育经历", "简介"};
		Label label;
		for (int i = 0; i < title.length; i++) {
			// Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
			// 在Label对象的子对象中指明单元格的位置和内容
			//label = new Label(i, 0, title[i]);
			label = new Label(i, 0, title[i], getHeader());
			// 将定义好的单元格添加到工作表中
			sheetk.addCell(label);
		}
		// 设置字体
					
		WritableFont wfont = new WritableFont(WritableFont.createFont("隶书"), 20);
		WritableCellFormat font = new WritableCellFormat(wfont);
		//循环数据，将数据填充到单元格内
		int t = 1;
		for (User user:list) {
			sheetk.addCell(new Label(0, t, t+"", wcf1));
			sheetk.addCell(new Label(1, t, user.getUserAccount(), wcf1));
			sheetk.addCell(new Label(2, t, user.getUserName(), wcf1));
			sheetk.addCell(new Label(3, t, user.getUserAddress(), wcf1));
			sheetk.addCell(new Label(4, t, user.getUserBirthday(), wcf1));
			sheetk.addCell(new Label(5, t, user.getUserEducation(), wcf1));
			sheetk.addCell(new Label(6, t, user.getUserContent(), wcf1));
			t++;
		}
		
	}
	
	/*
	 * 执行导出操作
	 */
	private void exportExcel(HttpServletResponse response,
			WritableWorkbook retValue) {
		response.setContentType("application/ms-excel");
		try {
			retValue.write();
			retValue.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	
	//获取并设置excel表头
	private WritableCellFormat getHeader() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);// 定义字体
			font.setColour(Colour.BLUE);// 蓝色字体
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
			format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框
			format.setBackground(Colour.YELLOW);// 黄色背景
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return format;
	}
}
