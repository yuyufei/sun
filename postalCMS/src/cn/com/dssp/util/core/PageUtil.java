package cn.com.dssp.util.core;


/**
 * ��ҳʵ��
 * <p>Title: PageUtil</p>
 * <p>Description: </p>
 * @author	fly
 * @date	2017��1��18������10:29:28
 * @version 1.0
 */
public class PageUtil {

	private Integer pageId = 1; // ��ǰҳ
	private Integer rowCount = 0; // ������
	private Integer pageSize = 10; // ҳ��С
	private Integer pageCount = 0; // ��ҳ��
	private Integer pageOffset = 0;// ��ǰҳ��ʼ��¼
	private Integer pageTail = 0; // ��ǰҳ����ļ�¼
	private String queryCondition; //�Զ�������
	private String andCondition; // ����
	private String orderByCondition; // ����
	private boolean paging=false; //Ĭ�Ϸ�ҳ
	private boolean like=false;   //Ĭ��ģ����ѯ

	public boolean getLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}
	
	public boolean getPaging() {
		return paging;
	}

	public void setPaging(boolean paging) {
		this.paging = paging;
	}

	public void splitPageInstance() {
		if (pageSize < 1 || null == pageSize) {
			pageSize = 10;
		}
		// ��ҳ��=(�ܼ�¼��+ÿҳ����-1)/ÿҳ����
		pageCount = (rowCount + pageSize - 1) / pageSize;
		// ��ǰҳ������ҳ��
		if (pageId > pageCount) {
			pageId = pageCount;
		}
		// ��ֹ pageOffset С�� 0
		pageOffset = ((pageId - 1) * pageSize);
		if (pageOffset < 0)
			pageOffset = 0;
	}

	public String getLimit() {
		return " limit " + pageOffset + "," + pageSize;
	}

	public String getAndCondition() {
		return andCondition == null ? "" : " AND " + andCondition;
	}

	public String getOrderByCondition() {
		return orderByCondition == null ? "" : " order by " + orderByCondition;
	}

	public String getAllConditionAndLimit() {
		return getQueryCondition()+getAndCondition() + getOrderByCondition() + getLimit();
	}

	// GET AND SET
	
	public Integer getPageId() {
		return pageId;
	}

	public String getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(String queryCondition) {
		this.queryCondition = queryCondition;
	}


	public void setAndCondition(String andCondition) {
		this.andCondition = andCondition;
	}

	public void setOrderByCondition(String orderByCondition) {
		this.orderByCondition = orderByCondition;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
		splitPageInstance();
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(Integer pageOffset) {
		this.pageOffset = pageOffset;
	}

	public Integer getPageTail() {
		return pageTail;
	}

	public void setPageTail(Integer pageTail) {
		this.pageTail = pageTail;
	}

}
