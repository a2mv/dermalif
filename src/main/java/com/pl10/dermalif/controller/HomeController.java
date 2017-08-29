package com.pl10.dermalif.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pl10.dermalif.constant.ViewConstant;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.service.CitydemoService;
import com.pl10.dermalif.service.UserService;

@Controller
@RequestMapping("home")
public class HomeController {

	private static final Log LOG = LogFactory.getLog(HomeController.class);
	
	@Autowired
	@Qualifier("citydemoService")
	CitydemoService citydemoService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@GetMapping({"","/"})
	public String requestStarter(Model model){
		LOG.info("METHOD: requestStarter()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("DERMALIF");
		lvm.setUbicacion(" - pl10");
		lvm.setDescripcion("Bienvenidos!!");
		model.addAttribute("lvm",lvm);		
		model.addAttribute("personsecurity",personsecurity);
		LOG.info("Returning to starter view");
		return ViewConstant.VIEW_STARTER;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("generarpaises")
	public String generarPaises(){
		LOG.info("METHOD: generarPaises()");
		citydemoService.addCities();
		return "redirect:/home";
	}
	
}
