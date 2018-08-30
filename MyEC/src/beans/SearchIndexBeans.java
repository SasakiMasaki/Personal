package beans;

import java.io.Serializable;

public class SearchIndexBeans implements Serializable{
	private String keyword = "";
	private final int displayNum = 10;
	private int resultNum = 0;
	private int pageNum = 1;
	private int index = 1;

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getDisplayNum() {
		return displayNum;
	}
	public int getResultNum() {
		return resultNum;
	}
	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}
	public int getPageNum() {
		if(this.resultNum > this.displayNum) {
			this.pageNum = this.resultNum / this.displayNum;
			if(this.resultNum % this.displayNum != 0) {
				this.pageNum += 1;
			}
		}
		return pageNum;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
}
