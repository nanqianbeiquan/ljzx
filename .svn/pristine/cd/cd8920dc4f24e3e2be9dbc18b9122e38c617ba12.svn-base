package com.srd.ljzd.util;

import java.util.HashSet;
import java.util.Set;

public class VirtualMacFilter {

	private static Set<String> virtualMac;
	static{
		virtualMac = new HashSet<String>();
		virtualMac.add("VIRTUAL");
		virtualMac.add("00:50:56");//Vmware的虚拟机
		virtualMac.add("00:15:5d");//Hyper-v的虚拟机
		
	}
	
	//
	/**
	 * 是否是虚拟机mac地址
	 * @param mac
	 * @return 是虚拟机mac返回true,否则返回false
	 */
	public static boolean isVirtualMac(String mac){
		for(String record:virtualMac){
			if(mac.indexOf(record)!=-1){
				return true;
			}
		}
		
		return false;
	}
}
