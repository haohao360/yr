package util.model;

import java.io.Serializable;

/**
 * 
 * 为combox数据提供数据支持
 * 
 * */
public class ComboxDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String k;
	private String v;
	public String getK() {
		return k;
	}
	public void setK(String k) {
		this.k = k;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
}
