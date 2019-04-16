package vn.edu.vnua.dse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class RoleController {

	@RequestMapping(value = "/RoleManagement", method = RequestMethod.GET)
	public String channel() {
		return "admin/role";
	}
}
