package com.pl10.dermalif.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pl10.dermalif.constant.HrefConstant;
import com.pl10.dermalif.constant.ViewConstant;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.service.UserService;

@Controller
@RequestMapping("factura")
public class FacturacionController {
	
	private static final Log LOG = LogFactory.getLog(FacturacionController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	@GetMapping("ingresos")
	public ModelAndView requestIngresosFact(){
		LOG.info("METHOD: requestIngresosFact()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FACTINGRESOS);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_FACTINGR);
		lvm.setDescripcion("Aquí puedes ver los ingresos y facturas");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_FORMHC+" view");		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	@GetMapping("new")
	public ModelAndView requestNewFact(){
		LOG.info("METHOD: requestNewFact()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FACTNEW);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_FACTINGR);
		lvm.setDescripcion("Aquí puedes gestionar tu factura");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_FACTNEW+" view");		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	@GetMapping("newarticulo")
	public ModelAndView requestNewArticulo(){
		LOG.info("METHOD: requestNewArticulo()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_ARTICNEW);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_NEWARTICULO);
		lvm.setDescripcion("Aquí puedes gestionar tus articulos");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_ARTICNEW+" view");		
		return model;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	@GetMapping("newformarticulo")
	public ModelAndView requestNewFormArticulo(){
		LOG.info("METHOD: requestNewFormArticulo()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FORMARTICNEW);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_NEWARTICULO);
		lvm.setDescripcion("Aquí puedes crear/editar tu articulo");
		model.addObject("lvm", lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_FORMARTICNEW+" view");		
		return model;
	}
}
