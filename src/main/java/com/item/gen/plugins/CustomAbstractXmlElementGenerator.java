package com.item.gen.plugins;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

public class CustomAbstractXmlElementGenerator extends AbstractXmlElementGenerator {

	public void addElements(XmlElement parentElement) {
		
		addSelectByCondition(parentElement);
		addSelectByIdList(parentElement);
		
		// 格式化默认的xml文件
		List<Element> list = new ArrayList<>();
		
		for (Element ele : parentElement.getElements() ) {
			Element curE = new TextElement("\r\n" + ele.getFormattedContent(2) );
			list.add(curE);
			//System.out.println(curE.getFormattedContent(0));
		}
		parentElement.getElements().clear();
		parentElement.getElements().addAll(list);
		
	}
	
	/***
	 * <foreach  item="item" collection="listTag"  open="(" separator="," close=")">
	 * #{item}
	 * </foreach>
	 * @param parentElement
	 */
	private void addSelectByIdList(XmlElement parentElement) {
		
		List<IntrospectedColumn> id = introspectedTable.getPrimaryKeyColumns();
		
		if (id.size() != 1) {
			return;
		}
		
		XmlElement foreach = new XmlElement("foreach");
		foreach.addAttribute(new Attribute("item", "item"));
		foreach.addAttribute(new Attribute("collection", "list"));
		foreach.addAttribute(new Attribute("open", "("));
		foreach.addAttribute(new Attribute("separator", ","));
		foreach.addAttribute(new Attribute("close", ")"));
		foreach.addElement(new TextElement("#{item}"));
		
		TextElement id_in = new TextElement("where "+ id.get(0).getActualColumnName() +" in");
		
		// 公用include 所有的列Base_Column
		XmlElement baseColumn = new XmlElement("include");
		baseColumn.addAttribute(new Attribute("refid", "Base_Column_List"));
		
		String idName = id.get(0).getJavaProperty();
		idName = idName.substring(0, 1).toUpperCase() + idName.substring(1);
		
		// 增加selectByCondition
		XmlElement find = new XmlElement("select");
		find.addAttribute(new Attribute("id", "selectBy"+idName+"List"));
		find.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		find.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
		find.addElement(new TextElement("select "));
		find.addElement(baseColumn);
		find.addElement(id_in);
		find.addElement(foreach);
		
		parentElement.addElement(find);
		
	}
	
	private void addSelectByCondition(XmlElement parentElement) {
		
		// 增加base_query
		XmlElement where = new XmlElement("sql");
		where.addAttribute(new Attribute("id", "base_query"));
		
		//在这里添加where条件
        XmlElement selectTrimElement = new XmlElement("trim"); //设置trim标签
        selectTrimElement.addAttribute(new Attribute("prefix", "WHERE"));  
        selectTrimElement.addAttribute(new Attribute("prefixOverrides", "AND | OR")); //添加where和and
		StringBuilder sb = new StringBuilder();
		for(IntrospectedColumn introspectedColumn : introspectedTable.getAllColumns()) {
			XmlElement selectNotNullElement = new XmlElement("if"); //$NON-NLS-1$
			sb.setLength(0);
			sb.append(introspectedColumn.getJavaProperty());
			sb.append(" != null ");
			if (introspectedColumn.isStringColumn()) {
				sb.append("and ");
				sb.append(introspectedColumn.getJavaProperty());
				sb.append(" != ''");
			}
			selectNotNullElement.addAttribute(new Attribute("test", sb.toString()));
			sb.setLength(0);
			// 添加and
			sb.append(" and ");
			// 添加
			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
			// 添加等号
			sb.append(" = ");
			sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            selectNotNullElement.addElement(new TextElement(sb.toString()));
            selectTrimElement.addElement(selectNotNullElement);
		}
		where.addElement(selectTrimElement);
		
		//parentElement.addElement(where);
		
		// 公用include
		XmlElement include = new XmlElement("include");
		include.addAttribute(new Attribute("refid", "base_query"));
		
		// 公用include 所有的列Base_Column
		XmlElement baseColumn = new XmlElement("include");
		baseColumn.addAttribute(new Attribute("refid", "Base_Column_List"));
		
		// 公用select
		sb.setLength(0);		
		sb.append("from ");
		sb.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
		TextElement selectText = new TextElement(sb.toString());
		
		// 增加selectByCondition
		XmlElement find = new XmlElement("select");
		find.addAttribute(new Attribute("id", "selectByCondition"));
		find.addAttribute(new Attribute("resultMap", "BaseResultMap"));
		find.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));
		find.addElement(new TextElement("select "));
		find.addElement(baseColumn);
		find.addElement(selectText);
		find.addElement(selectTrimElement);
		
		parentElement.addElement(find);
	}

}
