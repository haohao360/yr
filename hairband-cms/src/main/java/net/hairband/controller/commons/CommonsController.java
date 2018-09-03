package net.hairband.controller.commons;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("commons")
public class CommonsController {

	/**
	 * 
	 * 
	 * 公共初始化路径方法
	 * 
	 * */
	@RequestMapping("initpage.htm")
	public String initPages(@RequestParam("path") String path){
		return path.replace("|", "/");
	}
	
	@RequestMapping("imgmanager.htm")
	public String initImgManager(){
		return "img/img";
	}
}
