package com.education.classroom.utils;

import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;





import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @类功能描述：XML文件处理类
 * @创建者：ZhangLiYing
 * @创建时间：2012-3-13 13:59:59
 * 说明：目前该类仅用于数据字典初始化程序
 */
public class XMLHelper {
	private static Logger logger = LoggerFactory.getLogger(XMLHelper.class);
	/**
	 * ADD 20131224 
	 * @param xml
	 * @return
	 */
	public static Document loadByXml(String xml) 
	{ 
		Document document = null; 
		try 
		{ 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder(); 
			document=builder.parse(new InputSource(new StringReader(xml))); 
			document.normalize(); 
		}catch (Exception ex){ 
			ex.printStackTrace(); 
		} 
		return document; 
	} 

	public static boolean doc2XmlFile(Document document,String filename) 
	{ 
		boolean flag = true; 
		try 
		{ 
			/** 将document中的内容写入文件中 */ 
			TransformerFactory tFactory = TransformerFactory.newInstance(); 
			Transformer transformer = tFactory.newTransformer();
			
			/** 编码 */ 
			//transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312"); 
			DOMSource source = new DOMSource(document); 
			StreamResult result = new StreamResult(new File(filename)); 
			transformer.transform(source, result); 
		}catch(Exception ex) 
		{ 
			flag = false; 
			ex.printStackTrace(); 
		} 
		return flag; 
	} 

	public static Document load(String filename) 
	{ 
		Document document = null; 
		try 
		{ 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder();
			document=builder.parse(new File(filename)); 
			document.normalize(); 
		} 
		catch (Exception ex){ 
			ex.printStackTrace(); 
		} 
		return document; 
	} 
	
	public static Document loadString(String xmlString) 
	{ 
		Document document = null; 
		try 
		{ 
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder();
			document=builder.parse(xmlString); 
			document.normalize(); 
		} 
		catch (Exception ex){ 
			ex.printStackTrace(); 
		} 
		return document; 
	} 
	
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Object createObjByXML(String xmlString, Class keyObj, Class valObj,Object bean)
			throws InstantiationException, IllegalAccessException {
		HashMap map=new HashMap();
		Document xmlDoc=loadString(xmlString);
		NodeList ndList=xmlDoc.getElementsByTagName("item");
		NamedNodeMap nodeMaps=null;
		for (int ii = 0; ii < ndList.getLength(); ii++) {
			Object oKeyObj = keyObj.newInstance();
			ArrayList subList = new ArrayList();
			Node itemNode = ndList.item(ii);
			
			try {
				nodeMaps = itemNode.getAttributes();
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
			
			for (int jj=0;jj<nodeMaps.getLength();jj++){
				Node node=nodeMaps.item(jj);	
				String nodeName=node.getNodeName();
				String nodeVal="";
				try {
					nodeVal=node.getNodeValue();
					BeanUtils.setProperty(oKeyObj,nodeName,nodeVal);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					logger.info("failed set "+nodeName+" = "+nodeVal+" \n"+e.getMessage());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					logger.info("failed set "+nodeName+" = "+nodeVal);
				}
			}
			
			if (itemNode.hasChildNodes()){				
				Object oValObj=null;
				NodeList subNodes=itemNode.getChildNodes();
				for (int mm=0;mm<subNodes.getLength();mm++){					
					oValObj=valObj.newInstance();
					Node dataNode=subNodes.item(mm);
					NamedNodeMap subNodeMaps=dataNode.getAttributes();
					if (subNodeMaps!=null){
						for (int nn=0;nn<subNodeMaps.getLength();nn++){
							Node node=subNodeMaps.item(nn);				
							String nodeName=node.getNodeName();
							String nodeVal="";
							try {
								nodeVal=node.getNodeValue();	
								BeanUtils.setProperty(oValObj,nodeName,nodeVal);								
							} catch (InvocationTargetException e) {
								e.printStackTrace();
								logger.info("failed set "+nodeName+" = "+nodeVal+" \n"+e.getMessage());
							} catch (IllegalAccessException e) {
								e.printStackTrace();
								logger.info("failed set "+nodeName+" = "+nodeVal);
							}							
						}						
					}
					subList.add(oValObj);					
				}				
				map.put(oKeyObj, subList);
			}
		}
		return map;
	}
	
	
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map createObjByXML(String fileName, Class keyObj, Class valObj)
			throws InstantiationException, IllegalAccessException {
		HashMap map=new HashMap();
		Document xmlDoc=load(fileName);
		NodeList ndList=xmlDoc.getElementsByTagName("item");
		NamedNodeMap nodeMaps=null;
		for (int ii = 0; ii < ndList.getLength(); ii++) {
			Object oKeyObj = keyObj.newInstance();
			ArrayList subList = new ArrayList();
			Node itemNode = ndList.item(ii);
			
			try {
				nodeMaps = itemNode.getAttributes();
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
			
			for (int jj=0;jj<nodeMaps.getLength();jj++){
				Node node=nodeMaps.item(jj);	
				String nodeName=node.getNodeName();
				String nodeVal="";
				try {
					nodeVal=node.getNodeValue();
					BeanUtils.setProperty(oKeyObj,nodeName,nodeVal);
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					logger.info("failed set "+nodeName+" = "+nodeVal+" \n"+e.getMessage());
				} catch (IllegalAccessException e) {
					e.printStackTrace();
					logger.info("failed set "+nodeName+" = "+nodeVal);
				}
			}
			
			if (itemNode.hasChildNodes()){				
				Object oValObj=null;
				NodeList subNodes=itemNode.getChildNodes();
				for (int mm=0;mm<subNodes.getLength();mm++){					
					oValObj=valObj.newInstance();
					Node dataNode=subNodes.item(mm);
					NamedNodeMap subNodeMaps=dataNode.getAttributes();
					if (subNodeMaps!=null){
						for (int nn=0;nn<subNodeMaps.getLength();nn++){
							Node node=subNodeMaps.item(nn);				
							String nodeName=node.getNodeName();
							String nodeVal="";
							try {
								nodeVal=node.getNodeValue();	
								BeanUtils.setProperty(oValObj,nodeName,nodeVal);								
							} catch (InvocationTargetException e) {
								e.printStackTrace();
								logger.info("failed set "+nodeName+" = "+nodeVal+" \n"+e.getMessage());
							} catch (IllegalAccessException e) {
								e.printStackTrace();
								logger.info("failed set "+nodeName+" = "+nodeVal);
							}							
						}						
					}
					subList.add(oValObj);					
				}				
				map.put(oKeyObj, subList);
			}
		}
		return map;
	}

	public static void createXMLFile(String fileName,Object oo) throws ParserConfigurationException { 
		Document xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		Element itemRoot=xmlDoc.createElement("root");
		xmlDoc.appendChild(itemRoot);		
		Element items=xmlDoc.createElement("items");
		parseObject2Element(xmlDoc,items,oo,"item");
		itemRoot.appendChild(items);
		doc2XmlFile(xmlDoc,fileName); 
	} 
	
	@SuppressWarnings("rawtypes")
	private static Element parseObject2Element(Document xmlDoc,Element element,Object oo,String tagName){
		Element currElement=null;
		if (oo!=null){			
			if (oo instanceof Map ||oo instanceof HashMap){
				Map map = (Map) oo;
				Set set = map.keySet();
				Iterator its = set.iterator();
				while (its.hasNext()){
					Object keyObj=its.next();
					Object valObj=map.get(keyObj);
					currElement=parseObject2Element(xmlDoc,element,keyObj,tagName);					
					Element subElement=parseObject2Element(xmlDoc,currElement,valObj,"data");
					if (currElement!=null && subElement!=null)//nullpoint exception
						currElement.appendChild(subElement);
					else{
						logger.info("null key::"+keyObj);
						logger.info("null val::"+valObj);
						
					}
				}
			}else if (oo instanceof List){
				List list=(List)oo;
				for (int ii=0;ii<list.size();ii++){
					Object obj=list.get(ii);
					currElement=parseObject2Element(xmlDoc,element,obj,tagName);
				}
			}else{				
				 Class cls = oo.getClass();
				 Element item=xmlDoc.createElement(tagName);
				 Field[] fieldlist = cls.getDeclaredFields();
				 for (int jj=0;jj<fieldlist.length;jj++){
					 try {		        				
	        				fieldlist[jj].setAccessible(true);
	        				String attrName=fieldlist[jj].getName();
	        				String fieldVal="";
	        				if (fieldlist[jj].get(oo)!=null){
	        					fieldVal=fieldlist[jj].get(oo).toString();	
	        				}
							item.setAttribute(attrName, fieldVal);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}	    
					 
				 }
				 currElement=item;
				 element.appendChild(item);
			}
		}		
		return currElement;
	}
}
