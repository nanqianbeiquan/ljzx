package com.srd.ljzd.entityparser.base;

import com.srd.ljzd.entity.biz.BizMsg;


public abstract class BizParser {
	
	public abstract  BizMsg parse(Object data);
}
