package com.srd.ljzd.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.SQLFunctionTemplate ;   
import org.hibernate.type.StringType; 
  
public class MySQL5LocalDialect extends MySQL5InnoDBDialect {   
public MySQL5LocalDialect(){  
super();    
//registerFunction("convert", new SQLFunctionTemplate(Hibernate.STRING, "convert(?1 using ?2)") ); 
//Hibernate.STRING在Hibernate3.6.5.Final版本以下可以使用，因为在Hibernate4.0及以上的版本中，Hibernate.STRING已经不再使用了 
//可以使用代替
registerFunction("convert", new SQLFunctionTemplate(new StringType(), "convert(?1 USING ?2)") );     
 }  
}
