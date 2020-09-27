package com.item.gen.plugins;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

public class CustomJavaMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {

	@Override
	public void addInterfaceElements(Interface interfaze) {
		
		addInterfaceSelectByCondition(interfaze);
		addInterfaceSelectByPrimaryIds(interfaze);
	}
	
	private void addInterfaceSelectByCondition(Interface interfaze) {
		
		// 先创建import对象
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		// 添加Lsit的包
		importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
		
		// 创建方法对象
		Method method = new Method();
		
		method.setVisibility(JavaVisibility.PUBLIC);
		
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		method.setReturnType(returnType);
		
		method.setName("selectByCondition");
		
		// 设置参数类型是对象
		FullyQualifiedJavaType parameterType;
		parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		
		// 为方法添加参数，变量名称record
		method.addParameter(new Parameter(parameterType, "condition"));
		
		addMapperAnnotations(interfaze, method);
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
		if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
			interfaze.addImportedTypes(importedTypes);
			interfaze.addMethod(method);
		}
	}
	
	
	private void addInterfaceSelectByPrimaryIds(Interface interfaze) {
		
		List<IntrospectedColumn> id = introspectedTable.getPrimaryKeyColumns();
		
		if (id.size() != 1) {
			return;
		}
		
		// 先创建import对象
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
		// 添加Lsit的包
		importedTypes.add(FullyQualifiedJavaType.getNewListInstance());
		
		// 创建方法对象
		Method method = new Method();
		
		method.setVisibility(JavaVisibility.PUBLIC);
		
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		returnType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		method.setReturnType(returnType);
		
		String idName = id.get(0).getJavaProperty();
		idName = idName.substring(0, 1).toUpperCase() + idName.substring(1);
		method.setName("selectBy"+idName+"List");
		
		// 设置参数类型是对象
		FullyQualifiedJavaType parameterType=FullyQualifiedJavaType.getNewListInstance();
		parameterType.addTypeArgument(id.get(0).getFullyQualifiedJavaType());
		
		// 为方法添加参数，变量名称record
		method.addParameter(new Parameter(parameterType, "list"));
		
		addMapperAnnotations(interfaze, method);
		context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);
		if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable)) {
			interfaze.addImportedTypes(importedTypes);
			interfaze.addMethod(method);
		}
		
	}
	
	

	public void addMapperAnnotations(Interface interfaze, Method method) {
	}
}
