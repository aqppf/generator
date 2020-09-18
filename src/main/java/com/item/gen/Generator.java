package com.item.gen;

import java.io.File;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;

public class Generator {
	
	public static void main(String[] args) throws Exception {
		
		File configFile = new File("D:\\workspace\\gen\\src\\main\\resources\\generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(null);
        Configuration config = cp.parseConfiguration(configFile);
		
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, null, null);
        myBatisGenerator.generate(null);
		
		System.out.println("ok");
	}

}
