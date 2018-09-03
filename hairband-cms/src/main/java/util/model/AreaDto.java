package util.model;

public class AreaDto {

	/**
	 * 省编码
	 * */
	private String provinceCode;
	/**
	 * 市编码
	 * */
	private String cityCode;
	/**
	 * 区县编码
	 * */
	private String districtCode;
	/**
	 * 小区编码
	 * */
	private String communityCode;
	/**
	 * 亭子编码
	 * */
	private String pavilionCode;
	/**
	 * areaDto 实例类型
	 * */
	private AreaEnum areaEnum;
	
	public AreaDto(String code){
		this.areaEnum = AreaEnum.getAreaEnum(code.length());
		switch(this.areaEnum){
		case PAVILION: this.pavilionCode = code;
		case COMMUNITY: this.communityCode = code.substring(0, AreaEnum.COMMUNITY.length());
		case DISTRICT: this.districtCode = code.substring(0, AreaEnum.DISTRICT.length());
		case CITY: this.cityCode = code.substring(0, AreaEnum.CITY.length());
		case PROVINCE: this.provinceCode = code.substring(0, AreaEnum.PROVINCE.length());
		}
	}

	public AreaEnum getAreaEnum() {
		return areaEnum;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public String getCommunityCode() {
		return communityCode;
	}

	public String getPavilionCode() {
		return pavilionCode;
	}

	public void setAreaEnum(AreaEnum areaEnum) {
		this.areaEnum = areaEnum;
	}
}
