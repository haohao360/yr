package framework.web;

import java.io.Serializable;

/**
 * 
 * 简单分页组件
 * 
 * */
public class Pager<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private T entry;
	private Long total;
	public Pager(){
		
	}
	public Pager(T entry,Long total){
		this.entry = entry;
		this.total = total;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public T getEntry() {
		return entry;
	}
	public void setEntry(T entry) {
		this.entry = entry;
	}
}
