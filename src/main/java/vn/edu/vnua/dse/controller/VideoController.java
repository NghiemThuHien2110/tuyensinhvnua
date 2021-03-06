package vn.edu.vnua.dse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import vn.edu.vnua.dse.entity.Video;
import vn.edu.vnua.dse.service.VideoService;

@Controller
@RequestMapping(value = "/admin")
public class VideoController {
	@Autowired
	private VideoService videoService;

	/**
	 * Controller lấy ra danh sách tất cả video dang duoc kich hoat
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/medias", method = RequestMethod.GET)
	public String getAllCategorires(Model model) {

		List<Video> listAllVideo = new ArrayList<Video>();
		List<Video> listLimitVideo = new ArrayList<Video>();
		int pagesNumber = 0;
		int totalRecord = 0;
		try {
			listAllVideo = videoService.getAllVideo();
			listLimitVideo = videoService.getLimitedVideo(0);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pagesNumber = (int) Math.ceil(listAllVideo.size() / 10.0);
		totalRecord = listAllVideo.size();
		model.addAttribute("listVideo", listLimitVideo);
		model.addAttribute("pagesNumber", pagesNumber);
		model.addAttribute("totalRecord", totalRecord);

		return "admin/medias";
	}

	/**
	 * Controller thêm mới một video
	 * 
	 * @param Video
	 * @return
	 */
	@RequestMapping(value = "/AddVideo", method = RequestMethod.POST)
	public ModelAndView createVideo(Video video) {
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			video.setAuthor(username);
			video.setEditor(username);
		}

		try {
			videoService.addVideo(video);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return new ModelAndView("redirect:" + "/admin/medias");
	}

	/**
	 * Controller cập nhật một video
	 * 
	 * @return
	 */
	@RequestMapping(value = "/EditVideo", method = RequestMethod.POST)
	public ModelAndView updateVideo(Video video) {

		// Get username
		String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
			video.setUpdatedUser(username);
		}

		try {
			videoService.updateVideo(video);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ModelAndView("redirect:" + "/admin/medias");
	}

	/**
	 * Phương thức xóa bỏ 1 video
	 * 
	 * @param Video
	 * @return
	 */
	@RequestMapping(value = "/DeleteVideo", method = RequestMethod.POST)
	public ModelAndView deleteVideo(HttpServletRequest request) {
		int videoId;
		videoId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id").toString()) : 0;
		try {
			videoService.deleteVideo(videoId);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return new ModelAndView("redirect:" + "/admin/medias");
	}

	/**
	 * Phuong thuc thay doi trang thai cua video
	 * 
	 * @param request
	 */
	@RequestMapping(value = "/changeStatusVideo", method = RequestMethod.POST)
	public String changeStatusVideo(HttpServletRequest request) {
		int videoId;
		String username = null;

		// get VideoId
		videoId = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id").toString()) : 0;
		boolean status = request.getParameter("status") != null
				? "true".equals(request.getParameter("status")) ? false : true
				: false;

		// Get username
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}

		try {
			videoService.changeStatusVideo(videoId, status, username);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return "redirect:/admin/medias";
	}

	/**
	 * Controller lấy ra 10 video tiep theo
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getVideoLimit", method = RequestMethod.GET, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String getVideoLimit(@RequestParam int startIndex) {

		List<Video> listVideo = new ArrayList<Video>();
		String html = "";
		int i = startIndex + 1;
		try {
			listVideo = videoService.getLimitedVideo(startIndex);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		for (Video item : listVideo) {
//			html += "<tr role='row' class='odd'>";
//			html += "<td><input type=\"checkbox\" class=\"custom-control-input\" id=\"defaultUnchecked\"></td>";
//			html += "<td class=''>" + (i++) + "</td>";
//			html += "<td id='Video-name' class='sorting_1'>" + item.getName() + "</td>";
//			html += "<td id='Video-status'>" + (item.isStatus() == true ? "Kích hoạt" : "Khóa") + "</td>";
//			html += "<td id='Video-created-date'>" + item.getCreatedDate() + "</td>";
//			html += "<td id='Video-created-user'>" + (item.getCreatedUser() == null ? "" : item.getCreatedUser())
//					+ "</td>";
//			html += "<td id='Video-updated-date'>" + (item.getUpdatedDate() == null ? "" : item.getUpdatedDate())
//					+ "</td>";
//			html += "<td id='Video-updated-user'>" + (item.getUpdatedUser() == null ? "" : item.getUpdatedUser())
//					+ "</td>";
//			html += "<td><a id='changeStatusVideo' onclick='openModalChangeStatusVideo(" + item.getId() + ", "
//					+ item.isStatus() + ")' href='#' class = "
//					+ (item.isStatus() == true ? "\"fa fa-toggle-on\"" : "\"fa fa-toggle-off\"")
//					+ "></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick='openModalUpdateVideo("
//					+ item.getId() + ", \"" + item.getName() + "\", " + item.isStatus()
//					+ ")' href=\"#\" class = \"fa fa-pencil\"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a "
//					+ "onclick='openModalDeleteVideo(" + item.getId() + ", \"" + item.getName() + "\")' "
//					+ "href=\"#\" class = \"fa fa-trash-o\"></a></td>";
//			html += "</tr>";
		}
		return html + (i - 1);
	}
}
