package util.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import framework.exception.BusinessException;

public enum AreaEnum {
	PROVINCE(3),CITY(6),DISTRICT(9),COMMUNITY(12),PAVILION(15);
	
	private static Map<Integer,String> map = new ConcurrentHashMap<Integer, String>(100);
	
	private static Map<Integer,Area> mapArea = new ConcurrentHashMap<Integer, Area>(100);
	
	private static Map<String,Integer> mapKey = new ConcurrentHashMap<String, Integer>(100);
	
	private int length;
	
	private AreaEnum(int length){
		this.length = length;
	}
	
	public int length(){
		return this.length;
	}
	
	public static AreaEnum getAreaEnum(int length){
		switch(length){
		case 3: return PROVINCE;
		case 6: return CITY;
		case 9: return DISTRICT;
		case 12: return COMMUNITY;
		case 15: return PAVILION;
		default: throw new BusinessException("areaEnum is not support value="+length);
		}
	}
	
	public static String getName(int id){
		return map.get(id);
	}
	
	public static void setName(int id,String name){
		map.put(id, name);
	}
	
	public static Integer getKey(String code){
		return mapKey.get(code);
	}
	
	public static void setKey(String code,Integer key){
		mapKey.put(code, key);
	}
	
	public static Area getArea(int id){
		return mapArea.get(id);
	}
	
	public static void setArea(int id,Area area){
		mapArea.put(id, area);
	}
}
