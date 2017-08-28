package com.pl10.dermalif.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
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
import com.pl10.dermalif.model.HistoriaModel;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonJsonObject;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.service.HistoriaService;
import com.pl10.dermalif.service.IngresoService;
import com.pl10.dermalif.service.PersonSevice;
import com.pl10.dermalif.service.UserService;

@Controller
@RequestMapping("historia")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class HistoriaController {
	
	private static final Log LOG = LogFactory.getLog(HistoriaController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("ingresoService")
	private IngresoService ingresoService;
	
	@Autowired
	@Qualifier("historiaService")
	private HistoriaService historiaService;
	
	@Autowired
	@Qualifier("personService")
	private PersonSevice personService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HISTORIA')")
	@GetMapping("hcform")
	public ModelAndView requestHCForm(@RequestParam(name="id", required=false) String id){
		LOG.info("METHOD: requestHCForm() -- PARAMS: "+id);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FORMHC);
		IngresoModel ingresoModel = ingresoService.findIngresoModelById(id);		
		List<HistoriaModel> historiaModels = historiaService.findAllHistoriaModelByIngreso(ingresoModel);
		System.out.println(":v"+historiaModels);
		model.addObject("ingreso",ingresoModel);
		model.addObject("person",ingresoModel.getPersonModel());
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("HISTORIA");
		lvm.setUbicacion(HrefConstant.HREF_HC);
		lvm.setDescripcion("Aquí puedes gestionar la historia clínica");		
		model.addObject("lvm",lvm);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		model.addObject("personsecurity",personsecurity);		
		
		//calcular edad
		DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fechaHora.format(ingresoModel.getPersonModel().getBirthdate()), fmt);
		LocalDate ahora = LocalDate.now();
		Period edad = Period.between(fechaNac, ahora);
		model.addObject("edad", edad.getYears() + " Años");		
		//model.addObject("examenfisicodefault", HrefConstant.TEXT_DEFAULTEF);
		model.addObject("ejemplotratamiento", HrefConstant.TEXT_EJ_TRATAMIENTO);
		HistoriaModel historiaModel = new HistoriaModel();
		historiaModel.setIngresomodel(ingresoModel);
		historiaModel.setEfgeneral(HrefConstant.TEXT_DEFAULTEF);
		if(!historiaModels.isEmpty()){
			historiaModel = historiaModels.get(0);
		}				
		model.addObject("historia",historiaModel);
		LOG.info("Returning to "+ViewConstant.VIEW_FORMHC+" view");		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HISTORIA')")
	@PostMapping("/addhistoria")
	public ModelAndView addHistoria(@Valid @ModelAttribute(name = "historia") HistoriaModel historiaModel,
			BindingResult bindingResult) {
		LOG.info("METHOD: addHistoria() -- PARAMS: " + historiaModel);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FORMHC);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("HISTORIA");
		lvm.setUbicacion(HrefConstant.HREF_HC);
		lvm.setDescripcion("Aquí puedes gestionar la historia clínica");		
		model.addObject("lvm",lvm);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		model.addObject("personsecurity",personsecurity);		
		IngresoModel ingresoModel = ingresoService.findIngresoModelById(historiaModel.getIngresomodel().getId());
		model.addObject("ingreso",ingresoModel);
		model.addObject("person",ingresoModel.getPersonModel());
		//calcular edad
		DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fechaHora.format(ingresoModel.getPersonModel().getBirthdate()), fmt);
		LocalDate ahora = LocalDate.now();
		Period edad = Period.between(fechaNac, ahora);
		model.addObject("edad", edad.getYears() + " Años");		
		//model.addObject("examenfisicodefault", HrefConstant.TEXT_DEFAULTEF);
		model.addObject("ejemplotratamiento", HrefConstant.TEXT_EJ_TRATAMIENTO);			
		historiaModel.setIngresomodel(ingresoModel);
		model.addObject("historia",historiaModel);
		if (bindingResult.hasErrors()) {			
			LOG.info("Returning to "+ViewConstant.VIEW_FORMHC+" view -- PARAMS: "+historiaModel+" -- bindingResult"+bindingResult );
		}else{
			LOG.info("Returning to "+ViewConstant.VIEW_FORMHC+" view -- PARAMS: "+historiaModel);
			historiaModel.setIngresomodel(ingresoService.findIngresoModelById(historiaModel.getIngresomodel().getId()));
			historiaModel = historiaService.addHistoriaModel(historiaModel);
			model.addObject("historia",historiaModel);
		}		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCUMENTO')")
	@GetMapping("reporthistoria")
	public ModelAndView reporteHistoria(@RequestParam(name="id", required=false) String id){
		LOG.info("METHOD: reporteHistoria() -- PARAMS: id=" + id);
		ModelAndView model = new ModelAndView(ViewConstant.DOCS_HISTORIA);
		HistoriaModel historiaModel = historiaService.findHistoriaModelById(id);
		model.addObject("historia",historiaModel);
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HISTORIA')")
	@GetMapping("userhistory")
	public ModelAndView requestViewHistoryUser(){
		LOG.info("METHOD: requestViewHistoryUser()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_USERHISTORY);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("HISTORIA");
		lvm.setUbicacion(HrefConstant.HREF_USERHISTORY);
		lvm.setDescripcion("Aquí puedes ver los usuarios que tienen historias clínicas");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_USERHISTORY+" view");		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HISTORIA')")
	@RequestMapping(value = {"/alluser"}, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PersonJsonObject alluserDataTables(HttpServletRequest request){
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart")){
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
		}
		String searchParameter = request.getParameter("sSearch");		
		List<PersonModel> personModelList = historiaService.findAllPersonModelWithHistoria(searchParameter, pageNumber);		
		Long countRecords = historiaService.countFindAllPersonModelWithHistoria(searchParameter);
		PersonJsonObject personJsonObject = new PersonJsonObject();		
		personJsonObject.setiTotalDisplayRecords(countRecords.intValue());		
		personJsonObject.setiTotalRecords(countRecords.intValue());
		personJsonObject.setAaData(personModelList);
		return personJsonObject;
	}

}
