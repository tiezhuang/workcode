package com.workcode.config;

import com.workcode.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ExcelFormatUtil {
    /**
     * 读取excel表
     * @param
     * @param
     * @return
     * @throws Exception
     */
    public static List<User> getBankListByExcel(MultipartFile file) throws Exception {
        SimpleDateFormat Time3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<User> list = new ArrayList<User>();             // 读取的数据放入该集合中
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);  //示意访问sheet
        //XSSFWorkbook book = new XSSFWorkbook(file.getInputStream());        // 文件所在位置
        //XSSFSheet sheet = book.getSheetAt(0);//第一张单表

        for (int i = 1; i < sheet.getLastRowNum() + 1; i++) { //表的行数
            User user = new User();
           // XSSFRow row = sheet.getRow(i);
            String user_id = null;
            String user_password = null;
            if (sheet.getRow(i) != null) {
                if(sheet.getRow(i).getCell(0)!=null){
                    sheet.getRow(i).getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                    user_id = sheet.getRow(i).getCell(0).getStringCellValue();// 员工账号
                }
               /* if(row.getCell(1)!=null){
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    user_password = row.getCell(1).getStringCellValue();  // 员工密码
                }*/
                user.setUserId(Integer.valueOf(user_id));      // 将读取的数据放入对象
                user.setUserPassword("123456");
                user.setCreateTime(new Date());
            }
            list.add(user);
        }
        return list;
    }

    /**
     * @param excelName 表名
     * @param colNames 表头部数据
     * @param mapKeys 查找的对应数据
     * @param dataList 集合数据
     */
    public static void createExcel(HttpServletResponse response,
                            String excelName,
                            String[] colNames,
                            String[] mapKeys,
                            List<Map<String,Double>> dataList) throws IOException {


        // 创建新的Excel 工作簿
        Workbook workbook = new HSSFWorkbook();

        // 在Excel工作簿中建一工作表，其名为缺省值 Sheet0
        //Sheet sheet = workbook.createSheet();

        // 如要新建一名为excelName的工作表，其语句为：
        Sheet sheet = workbook.createSheet("表格");

        // 创建行（row 1）
        Row row1 = sheet.createRow(0);
        for (int a = 0;a < colNames.length;a++){
            row1.createCell(0).setCellValue(colNames[a]);//向第一行添加表头数据
        }

        if (null != dataList) { // 输出数据
            for (int i = 0; i < dataList.size(); i++) {
                Row rows = sheet.createRow(i + 1);
                for (int j = 0; j < mapKeys.length; j++) {
                    rows.createCell(j).setCellValue(dataList.get(i).get(mapKeys[j]));
                }
            }
        }

        //创建单元格（第三列）
      /*  Cell cell22 = row2.createCell(1);
        String dateTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(dateTime);
*/       String dateTime = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");
        String filename = new String(excelName.getBytes("gbk"), "iso8859-1") + dateTime + ".xlsx";
        //设置导出Excel的名称
        response.setHeader("Content-disposition", "attachment;filename=" + filename);

        //刷新缓冲
        response.flushBuffer();
        // 新建一输出文件流（注意：要先创建文件夹）
       // FileOutputStream out = new FileOutputStream("C:\\Users\\w\\Desktop");
        // 把相应的Excel 工作簿存盘
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        // 操作结束，关闭文件
        outputStream.close();
        //关闭文件
        workbook.close();
    }

}
