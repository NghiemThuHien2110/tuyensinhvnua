package vn.edu.vnua.dse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.edu.vnua.dse.entity.User;
import vn.edu.vnua.dse.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Phuong thuc them moi 1 user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
	public ModelAndView createUser(User user, HttpServletRequest request, Model model) {

		// kiem tra username co ton tai ko
		if (userService.checkExistUserByName(user.getUsername())) {
			model.addAttribute("message", "Tên tài khoản đã tồn tại! Mời bạn nhập tên khác.");
			return new ModelAndView("redirect:" + "admin/user");
		}

		// Get username
		String createdByUser;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			createdByUser = ((UserDetails) principal).getUsername();
			user.setCreatedUser(createdByUser);
		}

		// get type role
		boolean role;
		role = request.getParameter("role") != null ? Integer.parseInt(request.getParameter("role")) == 1 ? true : false
				: false;
		user.setRole(role);

		// random salt
		String salt = "randomString";
		// ma hoa mk
		user.setPasswordSalt(salt);
		user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12)));

		try {
			userService.createUser(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new ModelAndView("redirect:" + "/admin/user/UserManagement");
	}

	/**
	 * Controller lấy ra danh sách tất cả user
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/user/UserManagement", method = RequestMethod.GET)
	public String getFirstResultPage(Model model) {

		List<User> listUser = new ArrayList<User>();
		List<User> listAllUser = new ArrayList<User>();
		int pagesNumber = 0;
		int totalRecord = 0;
		try {
			listUser = userService.getListUserLimit(0);
			listAllUser = userService.getListUser();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pagesNumber = (int) Math.ceil(listAllUser.size() / 10.0);
		totalRecord = listAllUser.size();
		model.addAttribute("listUser", listUser);
		model.addAttribute("pagesNumber", pagesNumber);
		model.addAttribute("totalRecord", totalRecord);

		return "admin/user";
	}

	/**
	 * Controller cập nhật một user
	 * 
	 * @return
	 */
	@RequestMapping(value = "/admin/user/EditUser", method = RequestMethod.POST)
	public ModelAndView updateUser(User user) {

		// Get username
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			user.setUpdatedUser(username);
		}

		try {
			userService.updateUser(user);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ModelAndView("redirect:" + "/admin/user/UserManagement");
	}

	/**
	 * Phương thức xóa bỏ 1 user
	 * 
	 * @param User
	 * @return
	 */
	@RequestMapping(value = "/admin/user/DeleteUser", method = RequestMethod.POST)
	public ModelAndView deleteUser(HttpServletRequest request) {
		int UserId;
		UserId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id").toString()) : 0;
		try {
			userService.deleteUser(UserId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ModelAndView("redirect:" + "/admin/user/UserManagement");
	}

	/**
	 * Phuong thuc thay doi trang thai cua User
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/admin/user/changeStatusUser", method = RequestMethod.POST)
	public ModelAndView changeStatusUser(HttpServletRequest request) {
		int UserId;
		String username = null;

		// get UserId
		UserId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id").toString()) : 0;
		boolean status = request.getParameter("status") != null
				? "true".equals(request.getParameter("status")) ? false : true
				: false;

		// Get username
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		try {
			userService.changeStatusUser(UserId, status, username);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ModelAndView("redirect:/admin/user/UserManagement");
	}

	/**
	 * Controller lấy ra 10 User tiep theo
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin/user/getUserLimit", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getCategoriresLimit(@RequestParam int startIndex) {

		List<User> listUser = new ArrayList<User>();
		String html = "";
		int i = startIndex + 1;
		try {
			listUser = userService.getListUserLimit(startIndex);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		for (User item : listUser) {
			html += "<tr role='row' class='odd'>";
			html += "<td><input type=\"checkbox\" class=\"custom-control-input\" id=\"defaultUnchecked\"></td>";
			html += "<td class=''>" + (i++) + "</td>";
			html += "<td id=\"user-username\" class=\"sorting_1\">" + item.getUsername() + "</td>";
			html += "<td id=\"user-email\">" + item.getEmail() + "</td>";
			html += "<td id=\"user-status\">" + (item.isStatus() == true ? "Kích hoạt" : "Khóa") + "</td>";
			html += "<td id=\"user-role\">" + (item.isRole() == true ? "Quản trị viên" : "Biên tập viên") + "</td>";
			html += "<td id=\"user-created-date\">" + (item.getCreatedDate() == null ? "" : item.getCreatedDate())
					+ "</td>";
			html += "<td id=\"user-created-user\">" + (item.getCreatedUser()) + "</td>";
			html += "<td id=\"user-udapted-date\">" + (item.getUpdatedDate() == null ? "" : item.getUpdatedDate())
					+ "</td>";
			html += "<td id=\"user-udapted-user\">" + (item.getUpdatedUser() == null ? "" : item.getUpdatedUser())
					+ "</td>";
			html += "<td><a id='changeStatusUser' onclick='changeStatusUserById(" + item.getId() + ", "
					+ item.isStatus() + ")' href='#' "
					+ (item.isStatus() == true ? "class=\"fa fa-toggle-on\"" : "class=\"fa fa-toggle-off\"")
					+ "></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick='openModalUpdateUser("
					+ item.getId() + ", \"" + item.getUsername() + "\", " + item.isRole() + ", " + item.isStatus()
					+ ")' href=\"#\" class = \"fa fa-pencil\"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a "
					+ "onclick='openModalDeleteUser(" + item.getId() + ", \"" + item.getUsername() + "\")' "
					+ "href=\"#\" class = \"fa fa fa-trash-o\"></a></td>";
			html += "</tr>";
		}
		return html + (i - 1);
	}

	/**
	 * Controller tra ve trang thong tin ca nhan
	 * 
	 * @return
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(ModelMap model) {

		User user = new User();
		int totalPostByAuthor = 0;
		int totalVideoByAuthor = 0;

		String author = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			author = ((UserDetails) principal).getUsername();
		}

		// get user
		user = userService.getUserByUsername(author).get(0);

		try {
			totalPostByAuthor = userService.getTotalPostByUser(author);
			totalVideoByAuthor = userService.getTotalVideoByUser(author);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		model.addAttribute("user", user);
		model.addAttribute("totalPostByAuthor", totalPostByAuthor);
		model.addAttribute("totalVideoByAuthor", totalVideoByAuthor);

		return "profile";
	}
}
