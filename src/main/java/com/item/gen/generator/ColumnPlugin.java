package com.item.gen.generator;

import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.IntrospectedTable.TargetRuntime;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class ColumnPlugin extends PluginAdapter{

	@Override
	public boolean validate(List<String> warnings) {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
		addColumn(topLevelClass, introspectedTable);
        return true;
    }

	private void addColumn(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		Field field = new Field();
        field.setInitializationString(""); //$NON-NLS-1$
        field.setName("queryColumn"); //$NON-NLS-1$
        field.setStatic(false);
        field.setType(new FullyQualifiedJavaType("String")); //$NON-NLS-1$
        field.setVisibility(JavaVisibility.PRIVATE);
        
        if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3_DSQL) {
            context.getCommentGenerator().addFieldAnnotation(field, introspectedTable,
                    topLevelClass.getImportedTypes());
        } else {
            context.getCommentGenerator().addFieldComment(field, introspectedTable);
        }

        topLevelClass.addField(field);
        
        Method method = new Method();
        
        method.setName("getQueryColumn");
        method.setReturnType(FullyQualifiedJavaType.getStringInstance());
        method.addBodyLine("return this.queryColumn;");
        method.setVisibility(JavaVisibility.PUBLIC);
        
        topLevelClass.addMethod(method);
        
        method = new Method();
        Parameter parameter = new Parameter(FullyQualifiedJavaType.getStringInstance(), "queryColumn");
        
        method.setName("setQueryColumn");
        method.addParameter(parameter);
        method.addBodyLine("this.queryColumn = queryColumn;");
        method.setVisibility(JavaVisibility.PUBLIC);
        
        topLevelClass.addMethod(method);
		
	}
	

}
