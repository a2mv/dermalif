package com.pl10.dermalif.controller;

import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pl10.dermalif.constant.HrefConstant;
import com.pl10.dermalif.constant.ViewConstant;
import com.pl10.dermalif.model.CitaJsonObject;
import com.pl10.dermalif.model.CitaModel;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonAjaxResponse;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.service.CitaService;
import com.pl10.dermalif.service.PersonService;
import com.pl10.dermalif.service.UserService;

@Controller
@RequestMapping("cita")
public class CitasController {

	private static final Log LOG = LogFactory.getLog(CitasController.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("citaService")
	private CitaService citaService;

	@GetMapping({ "", "/" })
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CITAS')")
	public ModelAndView viewCitas() {
		LOG.info("METHOD: requestCitasView()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_CITAS);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_CITAS);
		lvm.setDescripcion("Aquí puedes gestionar citas");
		model.addObject("lvm", lvm);
		model.addObject("personsecurity", personsecurity);
		LOG.info("Returning to " + ViewConstant.VIEW_CITAS + " view");
		return model;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CITAS')")
	@GetMapping("/search/person")
	@ResponseBody
	public List<PersonAjaxResponse> searchPerson(@RequestParam String searchString) {
		LOG.info("Requesting searchPerson with term: {} " + searchString);
		List<PersonAjaxResponse> list = personService.listCityAjaxResponse(searchString);
		return list;
	}

	@GetMapping("/search/citas")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CITAS')")
	@ResponseBody
	public List<CitaJsonObject> listCitas(@RequestParam(name = "start", required = false) Long start,
			@RequestParam(name = "end", required = false) Long end) {
		LOG.info("Requesting listCitas --PARAM start= " + start + " end= " + end);
		Date startDate = new Date(start * 1000L);
		Date endDate = new Date(end * 1000L);
		List<CitaJsonObject> citas = citaService.findCitaJsonStartBetween(startDate, endDate);
		return citas;
	}
	
	@GetMapping("addcita")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CITAS')")
	public @ResponseBody String agregarCita(@RequestParam(name="id_pa", required=false) String id_pa,
			@RequestParam(name="title", required=false) String title,
			@RequestParam(name="backgroundColor", required=false) String backgroundColor,
			@RequestParam(name="duration", required=false) String duration,
			@RequestParam(name="start", required=false) Long start,
			@RequestParam(name="end", required=false) Long end) {
		LOG.info("METHOD: agregarCita() -- PARAMS: id_pa="+id_pa+" start="+start
				+" backgroundColor="+backgroundColor+" duration="+duration+" end="+end);
		CitaModel citaModel = new CitaModel();
		citaModel.setBackgroundColor(backgroundColor);
		citaModel.setBorderColor(backgroundColor);
		citaModel.setDuration(Short.parseShort(duration));
		citaModel.setEnd(new Date(end));
		citaModel.setStart(new Date(start));
		citaModel.setTitle(title);
		citaModel.setPersonModel(personService.findPersonModelById(id_pa));
		citaService.addCitaModel(citaModel);		
		return "OK";
	}
	
	@GetMapping("/search/person/id")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CITAS')")
	public @ResponseBody PersonModel getPersons(@RequestParam(name="id", required=false) String id){
		LOG.info("Requesting search getPersons with term: {} " + id);
		return personService.findPersonModelById(id);
	}
	
	@GetMapping("deletecita")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CITAS')")
	public @ResponseBody String deleteCita(@RequestParam(name="id", required=false) String id) {
		LOG.info("METHOD: deleteCita() -- PARAMS: id="+id);
		CitaModel citaModel = citaService.findCitaModelById(id);
		citaService.deleteCita(citaModel);
		return "OK";
	}
	
	
}
