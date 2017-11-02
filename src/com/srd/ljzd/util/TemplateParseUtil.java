package com.srd.ljzd.util;
/**
 * 模板解析实体类
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateParseUtil {
	/**
	 * 解析模板生成Excel
	 * @param templateDir  模板目录
	 * @param templateName 模板名称
	 * @param excelPath 生成的Excel文件路径
	 * @param data 数据参数
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void parse(String templateDir,String templateName,String excelPath,Map<String,Object> data) throws IOException, TemplateException {
		//初始化工作
		Configuration cfg = new Configuration();
		//设置默认编码格式为UTF-8
		cfg.setDefaultEncoding("UTF-8"); 
		//全局数字格式
		cfg.setNumberFormat("0.00");
		//设置模板文件位置
		cfg.setDirectoryForTemplateLoading(new File(templateDir));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		//加载模板
		Template template = cfg.getTemplate(templateName,"utf-8");
		OutputStreamWriter writer = null;
		try{
			//填充数据至Excel
			writer = new OutputStreamWriter(new FileOutputStream(excelPath),"UTF-8");
			template.process(data, writer);
			writer.flush();
		}finally{
			writer.close();
		}	
	}


	
}
