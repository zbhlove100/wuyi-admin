package wuyi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class AdminController {
	@RequestMapping("/admin")
	public String admin(Model model){
		model.addAttribute("ssss");
		return "index.html";
	}
}
