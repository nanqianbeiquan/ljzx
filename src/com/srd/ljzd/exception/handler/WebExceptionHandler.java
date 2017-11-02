package com.srd.ljzd.exception.handler;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.exception.AuthException;
import com.srd.ljzd.exception.BusinessException;
import com.srd.ljzd.exception.SessionOverdueException;
import com.srd.ljzd.util.ErrorCode;
import com.srd.ljzd.util.IpAddressGetter;
import com.srd.ljzd.util.LoggerUtils;


@ControllerAdvice
public class WebExceptionHandler {
	
	@ExceptionHandler( AuthException.class )
    //@ResponseStatus(HttpSta)
    public ModelAndView authException(HttpServletRequest request, AuthException ae) {
		StringBuffer errorMsg = new StringBuffer("");
			
		if(ae.getErrorCode()!=null&&StringUtils.isEmpty(ae.getDescription())){
			ae.setDescription(ErrorCode.getErrorMesssage(ae.getErrorCode()));
		}
	    
	    errorMsg.append("throws business exception");
	    errorMsg.append("ip="+IpAddressGetter.getIpAddr(request)+",errorCode="+ae.getErrorCode()+",des="+ae.getDescription());
	    errorMsg.append("source="+ae.getSource());
	    ClientAccount acc = (ClientAccount)request.getSession().getAttribute("account");
		if(acc!=null){
			errorMsg.append(",account="+acc.toString());
		}
	    LoggerUtils.error(errorMsg.toString(), ae);
		
	    ModelAndView mv = new ModelAndView();
		mv.addObject("exception", ae);
		mv.setViewName("/system/auth/exception");
		
	    return mv;
    }
	
	@ExceptionHandler( SessionOverdueException.class )
    //@ResponseStatus(HttpSta)
    public ModelAndView sessionOverdueException(HttpServletRequest request,
    		BusinessException be) {
		StringBuffer errorMsg = new StringBuffer("");
		
		if(be.getErrorCode()!=null&&StringUtils.isEmpty(be.getDescription())){
			be.setDescription(ErrorCode.getErrorMesssage(be.getErrorCode()));
		}
	    
		errorMsg.append("throws sessionOverdueException");
		errorMsg.append("ip="+IpAddressGetter.getIpAddr(request)+",errorCode="+be.getErrorCode()+",des="+be.getDescription());
		errorMsg.append("source="+be.getSource());
		ClientAccount acc = (ClientAccount)request.getSession().getAttribute("account");
		if(acc!=null){
			errorMsg.append(",account="+acc.toString());
		}
		LoggerUtils.error(errorMsg.toString(), be);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/modules/login/login4Web");
		
	    return mv;
    }
	
	@ExceptionHandler( BusinessException.class )
    //@ResponseStatus(HttpSta)
    public ModelAndView businessException(HttpServletRequest request,
    		BusinessException be) {
		StringBuffer errorMsg = new StringBuffer("");
		
		if(be.getErrorCode()!=null&&StringUtils.isEmpty(be.getDescription())){
			be.setDescription(ErrorCode.getErrorMesssage(be.getErrorCode()));
		}
		
		errorMsg.append("throws business exception");
		errorMsg.append("ip="+IpAddressGetter.getIpAddr(request)+",errorCode="+be.getErrorCode()+",des="+be.getDescription());
		errorMsg.append("source="+be.getSource());
		ClientAccount acc = (ClientAccount)request.getSession().getAttribute("account");
		if(acc!=null){
			errorMsg.append(",account="+acc.toString());
		}
		LoggerUtils.error(errorMsg.toString(), be);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", be);
		mv.setViewName("/system/auth/exception");
	    return mv;
    }
	
	@ExceptionHandler( DataAccessException.class )
    public ModelAndView dataAccessException(HttpServletRequest request,
    		DataAccessException e) {
		StringBuffer errorMsg = new StringBuffer("");
	    
		errorMsg.append("throws dataAccess exception");
		errorMsg.append("ip="+IpAddressGetter.getIpAddr(request)+",errorCode=9999,");
		ClientAccount acc = (ClientAccount)request.getSession().getAttribute("account");
		if(acc!=null){
			errorMsg.append(",account="+acc.toString());
		}
		LoggerUtils.error(errorMsg.toString(), e);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", new BusinessException(ErrorCode.ER_SQL_EXCEPTION, 
				ErrorCode.getErrorMesssage(ErrorCode.ER_SQL_EXCEPTION), null, null));
		mv.setViewName("/system/auth/exception");
		
        return mv;
    }
	
	@ExceptionHandler( Exception.class )
    public void exception(HttpServletRequest request, Exception e) throws Exception{
		StringBuffer errorMsg = new StringBuffer("");
		
		errorMsg.append("throws runtime exception,");
		errorMsg.append("ip="+IpAddressGetter.getIpAddr(request));
		ClientAccount acc = (ClientAccount)request.getSession().getAttribute("account");
		if(acc!=null){
			errorMsg.append(",account="+acc.toString());
		}
		
		LoggerUtils.error(errorMsg.toString(), e);
		
		throw e;
    }
	
	public String getPrintStackTrace(Exception e){
		StringWriter sw = new StringWriter();  
        e.printStackTrace(new PrintWriter(sw, true));  
        return sw.toString(); 
	}
}
