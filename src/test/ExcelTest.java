/**
 * 2018年8月3日
 * Administrator
 */
package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import com.axp.model.AdminuserWithdrawals;
import com.axp.util.CalcUtil;
import com.axp.util.ExcelUtil;

/**
 * @author 
 * @data 2018年8月3日下午3:13:02
 */
public class ExcelTest {
	
	@Test
	public void test2(){
		
		System.out.println(CalcUtil.div(100000, 800000, 4));
	}
	
	
	

	@Test
	public void test1() throws Exception {
		//String filepath = "C:\\Users\\Administrator\\Downloads\\提现数据1533281676385.xlsx";
		List<String> title = new ArrayList<String>();
		title.add("序号");
		title.add("账号");
		title.add("提现金额");
		title.add("手续费");
		title.add("提现时间");
		title.add("银行户名");
		title.add("手机号");
		title.add("银行账号");
		title.add("银行地址");
		title.add("批次号");
		title.add("审核时间");
		title.add("支付时间");
		title.add("商家名");
		title.add("审核说明");
		title.add("审核时间");
      for(String a :title){
    	  System.out.println(a+"--");
      }
      Collections.shuffle(title);
      for(String a :title){
    	  System.out.println(a);
      }
	}
	

	
	
	

}
