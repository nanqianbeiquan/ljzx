package com.srd.ljzd.controller.report;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.srd.ljzd.entity.biz.BizMsg;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.law.LawAdaptor;
import com.srd.ljzd.entity.news.NewsOpinion;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.service.biz.BizService;
import com.srd.ljzd.service.law.LawService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.news.NewsService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.FileUtils;
import com.srd.ljzd.util.StringTool;
import com.srd.ljzd.util.ThirdPartChannelEnum;

import freemarker.template.Template;

@Controller
@RequestMapping("/reportController")
public class CreditInvestigationController {
	
	protected static Logger log = LogManager.getLogger(CreditInvestigationController.class.getName());

	
	@Autowired
	BizService bizService;
	// 模板生成HTML存放地址；
	@Value("${htmlDirPath}")
	private String htmlDirPath;
	// HTML样式文件存放地址，主要是样式图片；
	@Value("${targetDir}")
	private String targetDir;
	@Autowired
	FreeMarkerConfig freemarkerConfig;

	@Autowired
	LawService lawService;
	
	@Autowired
	NewsService newsService;
	
	 @Autowired
	 private OperationLogService operationLogService;
	
	@RequestMapping(value="/createPdfInPage")
	@ResponseBody
    public void createPdfInPage(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		
		ClientAccount account = (ClientAccount)request.getSession().getAttribute("account");
		if(account==null){
			throw new SessionOverdueException(ErrorCode.ER_SESSION_OVERDUE, "登录会话过期", null, this.getClass());
		}
		String companyName =request.getParameter("companyName");
		//保存用户使用记录
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"下载报告","企业详情报告 企业名称 "+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
	 	// 创建数据模型 
    	Map root = new HashMap();
		
		BizMsg company = new BizMsg();
		if(StringTool.isNotNullAndBlack(companyName)){
			companyName = URLDecoder.decode(companyName, "UTF-8");
			company = bizService.getCachedBizMsg(companyName,ThirdPartChannelEnum.ZHONG_SHU);
			if(company!=null){
				root.put("company", company);
			}
			
		LawAdaptor lawInfo = lawService.getCachedLawMsg(companyName);
//		LawAdaptor lawInfo = lawService.queryLawInfo(companyName);
			if(lawInfo!=null){
				root.put("lawInfo",lawInfo);
			}else{
				root.put("lawInfo","");
			}
			
			Map<String, List<NewsOpinion>> newsMap = new HashMap<String, List<NewsOpinion>>();
			newsMap = newsService.queryAllOpinionList(companyName);
			if(newsMap!=null){
				List<NewsOpinion> newsOpinionList = newsMap.get("newsOpinionList");
				List<NewsOpinion> negativeOpinionList = newsMap.get("negativeOpinionList");
				root.put("newsOpinionList",newsOpinionList);
				root.put("negativeOpinionList",negativeOpinionList);
				root.put("newsMap",newsMap);
			}else{
				root.put("newsMap","");
				root.put("newsOpinionList","");
				root.put("negativeOpinionList","");
			}
		}
		

        
       	String basePath = request.getSession().getServletContext().getRealPath("/");
    	Template temp =	freemarkerConfig.getConfiguration().getTemplate("lengjingReportDetail.ftl");

   
    	 //将生成的内容写入html中 
        companyName=StringTool.formartForPdfName(companyName);
    	String imagePath="../images/pdf";
    	String cellPhone = account!=null?account.getMobilePhone():"";
    	String personTemp=cellPhone+"_"+companyName;
     	// String htmlDirPath = basePath + "html/";
     	//htmlDirPath = "E:/htmlFileForPdf/html/";
    	FileUtils.createDir(htmlDirPath);
    	String file1 = htmlDirPath+personTemp+"_lengjingReportDetail.html";
    	//复制文件
    	String sourceDir = basePath+"/images/pdf/report";
    	File fileImage = new File(targetDir);
    	if(!fileImage.exists()){
    		FileUtils.copyDirectiory(sourceDir, targetDir);
    	}
    	
    	File file = new File(file1);
    	//if (!file.exists())
    		file.createNewFile();
    		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
  
	    	root.put("imagePath", imagePath);
	    	root.put("company", company);
	    	root.put("reportDate",DateUtils.getCurrentDateStr());
	    	temp.process(root, out);
	    	out.flush();
	    	out.close();

	    	String url = new File(file1).toURI().toURL().toString();
	    	String personSign="";
	    	if(account!=null){
	    		personSign = personTemp+System.currentTimeMillis();
	    	}
	    	String pdfDirPath = basePath + "pdf/";
	    	FileUtils.createDir(pdfDirPath);
	    	String outputFile = pdfDirPath+personSign+"_风声征信报告.pdf";
	    	log.info("companyName ===="+companyName+";outputFile==="+outputFile);
	    	OutputStream os = new FileOutputStream(outputFile);
    	
	    	ITextRenderer renderer = new ITextRenderer();
	    
	    	
	        ITextFontResolver fontResolver = renderer.getFontResolver();
	    	renderer.setDocument(url);
	    	
	    	try {
	        	  fontResolver.addFont("/resources/fonts/MicrosoftYaHei.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED); 
					log.info("fontResolver.addFont2 from src resouce fonts");
			} catch (Exception e) {
				log.error("fontResolver.addFont from src resouce fonts e=="+e);
			}
	    	
	    	renderer.layout();
	    	try {
	    		renderer.createPDF(os);
	    	} catch (DocumentException e) {
	    		e.printStackTrace();
		    	log.error("companyName ===="+companyName+"；createPDF  e ==="+e);
	    	}
	    	
		    	log.info("companyName ===="+companyName+"；createPDF 转换成功！");
		   	if(os!=null){
		    		os.close();
		   	}
		    	
    		try {
    			// 读取PDF文件输入到页面
    			ByteArrayOutputStream baos=new ByteArrayOutputStream();
        		FileInputStream fis=new FileInputStream(outputFile);
        		BufferedInputStream bis=new BufferedInputStream(fis);
        		int c=bis.read();//读取bis流中的下一个字节
        		while(c!=-1){
        		     baos.write(c);
        		     c=bis.read();
        		}
        		bis.close();
        		fis.close();
        		
        		response.setContentType("application/pdf");  
    		    response.setContentLength(baos.size()); 
    		    // 设置文件名称；
    		
    		    companyName = new String(companyName.getBytes("gb2312"), "ISO8859-1" ); 
    		    String lengjingReport =new String("风声征信报告".getBytes("gb2312"), "ISO8859-1" );
    		    String pdfName = companyName+"_"+lengjingReport+".pdf";
    		    log.info("pdfName ==="+pdfName);
    		    response.setHeader("Content-disposition","attachment; filename="+pdfName); 
    		    ServletOutputStream   servletout = response.getOutputStream(); 
    		    //将pdf数据流写入响应数据流中 
    		    baos.writeTo(servletout); 
    		    servletout.flush(); 
    		    servletout.close();
    		    baos.flush();
    		    baos.close();
			} catch (Exception e) {
				log.error("在页面导出生成PDF异常  e==="+e);
			}finally{
				try {
					new File(outputFile).delete();
				} catch (Exception e2) {
					log.error("删除文件异常 e ====="+e2);
				}
				
			}
    		    
    }

}
