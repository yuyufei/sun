package cn.com.dssp.model;

import java.sql.Timestamp;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 请求信息保存
 * <p>Title: TbsHttpReuqestModel</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017年2月20日上午11:03:08
 * @version 1.0
 */
public class TbsHttpReuqestModel extends BaseModel{
	private String id;//序列号
	private String name;//名称
	private String header;//请求头信息
	private String firstPage;//所有分类地址
	private String encoding;//编码
	private String listName;//列表过滤
	private String regexs;//jquery表达式
	private String arrtSplit;//属性
	private String inserts;//入库语句
	private String listUrlRegex;//请求属性
	private Integer listUrlTest;//测试行数
	private Integer listPageId;//开始页
	private String listSplitUrlChar;//横中线分隔符
	private String listSplitUrl;//分页表达式
	private String listRegexs;//jquery表达式
	private String listAttrSplit;//属性
	private String listInserts;//入库语句
	private String contUrlRegex;//url表达式
	private Integer contUrlTest;//测试url
	private String contRegexs;//选择器
	private String contAttrSplit;//属性分割
	private String contInserts;//入库表达式
	private Timestamp createTime;//时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(String firstPage) {
		this.firstPage = firstPage;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getRegexs() {
		return regexs;
	}
	public void setRegexs(String regexs) {
		this.regexs = regexs;
	}
	public String getArrtSplit() {
		return arrtSplit;
	}
	public void setArrtSplit(String arrtSplit) {
		this.arrtSplit = arrtSplit;
	}
	public String getInserts() {
		return inserts;
	}
	public void setInserts(String inserts) {
		this.inserts = inserts;
	}
	public String getListUrlRegex() {
		return listUrlRegex;
	}
	public void setListUrlRegex(String listUrlRegex) {
		this.listUrlRegex = listUrlRegex;
	}
	public Integer getListUrlTest() {
		return listUrlTest;
	}
	public void setListUrlTest(Integer listUrlTest) {
		this.listUrlTest = listUrlTest;
	}
	public Integer getListPageId() {
		return listPageId;
	}
	public void setListPageId(Integer listPageId) {
		this.listPageId = listPageId;
	}
	public String getListSplitUrlChar() {
		return listSplitUrlChar;
	}
	public void setListSplitUrlChar(String listSplitUrlChar) {
		this.listSplitUrlChar = listSplitUrlChar;
	}
	public String getListSplitUrl() {
		return listSplitUrl;
	}
	public void setListSplitUrl(String listSplitUrl) {
		this.listSplitUrl = listSplitUrl;
	}
	public String getListRegexs() {
		return listRegexs;
	}
	public void setListRegexs(String listRegexs) {
		this.listRegexs = listRegexs;
	}
	public String getListAttrSplit() {
		return listAttrSplit;
	}
	public void setListAttrSplit(String listAttrSplit) {
		this.listAttrSplit = listAttrSplit;
	}
	public String getListInserts() {
		return listInserts;
	}
	public void setListInserts(String listInserts) {
		this.listInserts = listInserts;
	}
	public String getContUrlRegex() {
		return contUrlRegex;
	}
	public void setContUrlRegex(String contUrlRegex) {
		this.contUrlRegex = contUrlRegex;
	}
	public Integer getContUrlTest() {
		return contUrlTest;
	}
	public void setContUrlTest(Integer contUrlTest) {
		this.contUrlTest = contUrlTest;
	}
	public String getContRegexs() {
		return contRegexs;
	}
	public void setContRegexs(String contRegexs) {
		this.contRegexs = contRegexs;
	}
	public String getContAttrSplit() {
		return contAttrSplit;
	}
	public void setContAttrSplit(String contAttrSplit) {
		this.contAttrSplit = contAttrSplit;
	}
	public String getContInserts() {
		return contInserts;
	}
	public void setContInserts(String contInserts) {
		this.contInserts = contInserts;
	}
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "TbsHttpReuqestModel [id=" + id + ", name=" + name + ", header=" + header + ", firstPage=" + firstPage
				+ ", encoding=" + encoding + ", listName=" + listName + ", regexs=" + regexs + ", arrtSplit="
				+ arrtSplit + ", inserts=" + inserts + ", listUrlRegex=" + listUrlRegex + ", listUrlTest=" + listUrlTest
				+ ", listPageId=" + listPageId + ", listSplitUrlChar=" + listSplitUrlChar + ", listSplitUrl="
				+ listSplitUrl + ", listRegexs=" + listRegexs + ", listAttrSplit=" + listAttrSplit + ", listInserts="
				+ listInserts + ", contUrlRegex=" + contUrlRegex + ", contUrlTest=" + contUrlTest + ", contRegexs="
				+ contRegexs + ", contAttrSplit=" + contAttrSplit + ", contInserts=" + contInserts + ", createTime="
				+ createTime + "]";
	}
	
	
	

}
