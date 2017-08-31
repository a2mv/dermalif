package com.pl10.dermalif.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pl10.dermalif.constant.HrefConstant;
import com.pl10.dermalif.constant.ViewConstant;
import com.pl10.dermalif.model.ArticuloJsonObject;
import com.pl10.dermalif.model.ArticuloModel;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.service.ArticuloService;
import com.pl10.dermalif.service.UserService;

@Controller
@RequestMapping("factura")
public class FacturacionController {
	
	private static final Log LOG = LogFactory.getLog(FacturacionController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("articuloService")
	private ArticuloService articuloService;
	
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
	
	@GetMapping("articulo")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	public ModelAndView requestArticulo(){
		LOG.info("METHOD: requestArticulo()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_ARTICULO);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_NEWARTICULO);
		lvm.setDescripcion("Aquí puedes gestionar tus articulos");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_ARTICULO+" view");		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = {"/allarticulo"}, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody ArticuloJsonObject allArticuloDataTables(HttpServletRequest request){
		LOG.info("METHOD: ArticuloJsonObject()");
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart")){
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
		}
		String searchParameter = request.getParameter("sSearch");		
		List<ArticuloModel> articuloModelList = articuloService.searchArticuloModel(searchParameter, pageNumber);		
		Long countRecords = articuloService.countSearchArticuloModel(searchParameter);		
		ArticuloJsonObject articuloJsonObject = new ArticuloJsonObject();		
		articuloJsonObject.setiTotalDisplayRecords(countRecords.intValue());		
		articuloJsonObject.setiTotalRecords(countRecords.intValue());
		articuloJsonObject.setAaData(articuloModelList);
		return articuloJsonObject;
	}

	@GetMapping("newformarticulo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView requestNewFormArticulo(@RequestParam(name="id", required=false) int id){
		LOG.info("METHOD: requestNewFormArticulo() -- PARAMS: "+id);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FORMARTICNEW);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_NEWARTICULO);
		lvm.setDescripcion("Aquí puedes crear/editar tu articulo");
		model.addObject("lvm", lvm);
		model.addObject("personsecurity",personsecurity);
		//articulo
		ArticuloModel articuloModel = new ArticuloModel();
		if(id!=0){
			articuloModel = articuloService.findArticuloModelById(id);
		}
		model.addObject("articulo", articuloModel);
		
		LOG.info("Returning to "+ViewConstant.VIEW_FORMARTICNEW+" view");		
		return model;
	}
	
	@PostMapping("/addarticulo")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView addArticulo(@Valid @ModelAttribute(name = "articulo") ArticuloModel articuloModel,
			BindingResult bindingResult) {
		LOG.info("METHOD: addArticulo() -- PARAMS: " + articuloModel);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView();
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_NEWARTICULO);
		lvm.setDescripcion("Aquí puedes crear/editar tu articulo");
		model.addObject("lvm", lvm);
		model.addObject("articulo", articuloModel);
		model.addObject("personsecurity",personsecurity);
		if (bindingResult.hasErrors()) {
			LOG.info("Returning to "+ViewConstant.VIEW_FORMARTICNEW+" view -- PARAMS: "+articuloModel+" -- bindingResult"+bindingResult );
			model.setViewName(ViewConstant.VIEW_FORMARTICNEW);
		} else {
			LOG.info("Returning to factura/articulo view");
			model.setViewName("redirect:/factura/articulo");
			if (null != articuloService.addArticuloModel(articuloModel)) {
				model.addObject("result", 1);
			} else {
				model.addObject("result", 0);
			}
		}
		return model;
	}
}
