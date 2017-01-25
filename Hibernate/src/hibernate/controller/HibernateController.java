package hibernate.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import hibernate.dao.Image;
import hibernate.dao.User;
import hibernate.dao.UserDAO;

@Controller
public class HibernateController {
	
	@Autowired
	UserDAO dao;
	
	@RequestMapping("/")
	public String homePage() {
		return "index";
	}
	@RequestMapping("/uploadImage")
	public @ResponseBody String uploadFileHandler(@RequestParam("username") String username,  @RequestParam("imageName") String imageName, @RequestParam("image") MultipartFile file) {
		
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles" +File.separator+ username );
				if (!dir.exists()) dir.mkdirs();
				
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + imageName);
				if (serverFile.exists()) {
					return imageName +" already exist!!";
				}
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				User u = new User();
				u.setUsername(username);
				List<Image> images = new ArrayList<>();
				Image i = new Image();
				i.setImageName(imageName);
				i.setImageURL("/images/"+username+"/"+imageName);
				images.add(i);
				u.setImage(images);
				dao.insertUser(u);
				return "You successfully uploaded file=" + imageName;
			} catch (Exception e) {
				return "You failed to upload " + imageName + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + imageName
					+ " because the file was empty.";
		}
		
		
	}
	
	@RequestMapping("/allUsers")
	public ModelAndView showAllUsers(ModelAndView mav) {
		List<User> users = dao.getAllUsers();
		mav.addObject("users", users);
		mav.setViewName("index");
		return mav;
	}
	@RequestMapping("/getImages")
	public ModelAndView getUserImages(ModelAndView mav, @RequestParam("name") String username) {
		List<Image> images = dao.getUserImages(username);
		mav.addObject("images", images);
		mav.setViewName("index");
		return mav;
	}
	
}
