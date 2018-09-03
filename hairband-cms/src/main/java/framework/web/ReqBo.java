package framework.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import framework.exception.ApplicationException;

public class ReqBo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private Map<Object, Object> param = new HashMap<Object, Object>();
	private Object reqModel;
	
	public ReqBo(){
		super();
	}
	
	public ReqBo(HttpServletRequest req){
		this.param.putAll(getRequestParams(req));
	}
	
	public ReqBo(Object reqModel){
		this.reqModel = reqModel;
	}
	
	public ReqBo(HttpServletRequest req,Object reqModel){
		this.param.putAll(getRequestParams(req));
		this.reqModel = reqModel;
	}
	
	private Map<String,Object> getRequestParams(HttpServletRequest req){
		Map<String,String[]> map = req.getParameterMap();
		Map<String,Object> result = new HashMap<String,Object>();
		String[] values = null;
		for(String key:map.keySet()){
			values = map.get(key);
			if(values != null&&values.length!=0){
				if(values.length == 1){
					result.put(key, values[0]);
				}else if(values.length > 1){
					result.put(key, StringUtils.join(values, ","));
				}
			}
		}
		return result;
	}
	
	public Object getParam(Object name){
		Object obj = this.param.get(name);
		if(obj == null || "".equals(obj))
			return null;
		else
			return obj;
	}
	
	public Object getOriginalParam(Object name){
		return this.param.get(name);
	}
	
	public Object getParam(Object name,Object defaultValue){
		Object obj = getParam(name);
		if(obj == null){
			return defaultValue;
		}
		return obj;
	}
	
	public ReqBo setParam(Object key,Object value){
		this.param.put(key, value);
		return this;
	}
	
	public Object getReqModel(){
		return this.reqModel;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getReqModel(Class<T> clazz){
		return (T) this.reqModel;
	}
	
	public ReqBo setReqModel(Object reqModel){
		this.reqModel = reqModel;
		return this;
	}
	
	public String getParamStr(Object name){
		return getParamStr(name,null);
	}
	
	public String getParamStr(Object name,String defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		return obj.toString().trim();
	}
	
	public Integer getParamInt(Object name){
		return getParamInt(name,null);
	}
	
	public Integer getParamInt(Object name,Integer defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		if(obj instanceof Integer){
			return (Integer) obj;
		}
		return Integer.parseInt(obj.toString().trim());
	}
	
	public Long getParamLong(Object name){
		return getParamLong(name,null);
	}
	
	public Long getParamLong(Object name,Long defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		if(obj instanceof Long){
			return (Long) obj;
		}
		return Long.parseLong(obj.toString().trim());
	}
	
	public Float getParamFloat(Object name){
		return getParamFloat(name,null);
	}
	
	public Float getParamFloat(Object name,Float defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		if(obj instanceof Float){
			return (Float) obj;
		}
		return Float.parseFloat(obj.toString().trim());
	}
	
	public Double getParamDouble(Object name){
		return getParamDouble(name,null);
	}
	
	public Double getParamDouble(Object name,Double defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		if(obj instanceof Double){
			return (Double) obj;
		}
		return Double.parseDouble(obj.toString().trim());
	}
	
	public BigDecimal getParamDecimal(Object name){
		return getParamDecimal(name,null);
	}
	
	public BigDecimal getParamDecimal(Object name,BigDecimal defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		if(obj instanceof BigDecimal){
			return (BigDecimal) obj;
		}
		return new BigDecimal(obj.toString().trim());
	}
	
	public Boolean getParamBoolean(Object name){
		return getParamBoolean(name,null);
	}
	
	public Boolean getParamBoolean(Object name,Boolean defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		if(obj instanceof Boolean){
			return (Boolean) obj;
		}
		return Boolean.valueOf(obj.toString());
	}
	
	public Date getParamDate(Object name,Date defaultValue,String dateFormat) {
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		if(obj instanceof Date){
			return (Date) obj;
		}
		try {
			return new SimpleDateFormat(dateFormat).parse(obj.toString());
		} catch (ParseException e) {
			throw new ApplicationException(e);
		}
	}
	
	public Date getParamDate(Object name){
		return getParamDate(name,null,dateFormat);
	}
	
	public <T> T getParamT(Object name,Class<T> clazz){
		return getParamT(name,clazz,null);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getParamT(Object name,Class<T> clazz, Object defaultValue){
		Object obj = getParam(name,defaultValue);
		if(obj == null){
			return null;
		}
		return (T) obj;
	}
	
	public Map<Object,Object> getParams(){
		return this.param;
	}
}
