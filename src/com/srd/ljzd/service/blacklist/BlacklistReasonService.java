package com.srd.ljzd.service.blacklist;

import java.util.List;

import com.srd.ljzd.entity.blacklist.BlacklistReason;
import com.srd.ljzd.util.BlacklistReasonTypeEnum;

public interface BlacklistReasonService {
/**
 * 获取原因列表
 * @param type 1是加入原因，  2是删除原因
 * @return
 */
	List<BlacklistReason> getReasonList(BlacklistReasonTypeEnum type);
}
