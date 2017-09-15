package com.pl10.dermalif.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pl10.dermalif.constant.HrefConstant;
import com.pl10.dermalif.constant.ViewConstant;
import com.pl10.dermalif.enums.TypeIngresoStatus;
import com.pl10.dermalif.model.CityAjaxResponse;
import com.pl10.dermalif.model.FacturaModel;
import com.pl10.dermalif.model.IngresoJsonObject;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonJsonObject;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.model.UserRolePersonModel;
import com.pl10.dermalif.service.CityService;
import com.pl10.dermalif.service.FacturaService;
import com.pl10.dermalif.service.IngresoService;
import com.pl10.dermalif.service.PersonService;
import com.pl10.dermalif.service.StorageService;
import com.pl10.dermalif.service.UserService;

@Controller
@RequestMapping("user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
	
	private static final Log LOG = LogFactory.getLog(UserController.class);
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@Autowired
	@Qualifier("cityService")
	private CityService cityService;

	@Autowired
	ServletContext servletContext;
	
	@Autowired
	@Qualifier("ingresoService")
	private IngresoService ingresoService;
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("storageService")
	StorageService storageService;
	
	@Autowired
	@Qualifier("facturaService")
	FacturaService facturaService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping({"","/"})
	public String viewUsers(Model model){
		LOG.info("METHOD: viewUsers()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_USUARIOS);
		lvm.setDescripcion("Listado de todos los usuarios");
		model.addAttribute("lvm",lvm);
		model.addAttribute("personsecurity",personsecurity);
		LOG.info("Returning to user/index view");
		return ViewConstant.VIEW_USERS;
	}
	
	@GetMapping("/usuarioform")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ModelAndView requestUserForm(@RequestParam(name="id", required=false) String id){
		LOG.info("METHOD: requestUserForm() -- PARAMS: "+id);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		PersonModel personModel = new PersonModel();
		if(!"0".equals(id)){
			personModel = personService.findPersonModelById(id);
		}
		personModel = personService.repairCityModel(personModel);	
		//ROLE
		UserRolePersonModel userRolePersonModel = userService.findUserRole(personModel.getEmail());
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FORMUSER);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_USUARIOS);
		lvm.setDescripcion("Aqui puedes Agregar/editar usuarios");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		model.addObject("person", personModel);
		model.addObject("personrole", userRolePersonModel);
		LOG.info("Returning to "+ViewConstant.VIEW_FORMUSER+" view -- PARAMS: "+personModel+" "+userRolePersonModel);		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@PostMapping("/adduser")
	public ModelAndView addPerson(@Valid @ModelAttribute(name = "person") PersonModel personModel,
			BindingResult bindingResult) {
		LOG.info("METHOD: addPerson() -- PARAMS: " + personModel);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView();
		LocationViewModel lvm = new LocationViewModel();
		personModel = personService.repairCityModel(personModel);
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_USUARIOS);
		lvm.setDescripcion("Aqui puedes Agregar/editar usuarios");
		model.addObject("lvm", lvm);
		model.addObject("person", personModel);
		model.addObject("personsecurity",personsecurity);
		if (bindingResult.hasErrors()) {
			LOG.info("Returning to "+ViewConstant.VIEW_FORMUSER+" view -- PARAMS: "+personModel+" -- bindingResult"+bindingResult );
			model.setViewName(ViewConstant.VIEW_FORMUSER);
		} else {
			LOG.info("Returning to user/index view");
			model.setViewName("redirect:/user");
			if(!personModel.getId().equals("")){
				personModel.setImageprofile(personService.findPersonModelById(personModel.getId()).getImageprofile());
			}
			if (null != personService.addPersonModel(personModel)) {
				model.addObject("result", 1);
			} else {
				model.addObject("result", 0);
			}
		}
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/addrole")
	public @ResponseBody boolean addUserRole(@RequestParam boolean value, 
			@RequestParam String personrol, 
			@RequestParam String tprol,
			@RequestParam boolean rolesys) {
		LOG.info("METHOD: addUserRole() -- PARAMS: value="+value+" personrol="+personrol+" tprol="+tprol);
		PersonModel personModel = personService.findPersonModelById(personrol);		
		UserRolePersonModel userRolePersonModel = userService.findUserRole(personModel.getEmail());			
		userRolePersonModel.setUsersystem(rolesys);
		if(tprol.equals("rolecit")){
			userRolePersonModel.setCitrole(value);
		}else if(tprol.equals("roleing")){
			userRolePersonModel.setIngrole(value);
		}else if(tprol.equals("rolehcl")){
			userRolePersonModel.setHclrole(value);
		}else if(tprol.equals("roledoc")){
			userRolePersonModel.setDocrole(value);
		}else if(tprol.equals("rolefac")){
			userRolePersonModel.setFacrole(value);
		}else if(tprol.equals("rolepac")){
			userRolePersonModel.setPacrole(value);
		}
		userRolePersonModel = userService.addUserRole(userRolePersonModel);
		return true;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = {"/search/city"}, method = RequestMethod.GET)
	public @ResponseBody List<CityAjaxResponse> searchUsers(@RequestParam String searchString) {
		LOG.info("Requesting search users with term: {} " + searchString);
		List<CityAjaxResponse> list = cityService.listCityAjaxResponse(searchString);
		return list;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@RequestMapping(value = {"/alluser"}, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PersonJsonObject alluserDataTables(HttpServletRequest request){
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart")){
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
		}
		String searchParameter = request.getParameter("sSearch");		
		List<PersonModel> personModelList = personService.searchListPersonModel(searchParameter, pageNumber);		
		Long countRecords = personService.countSearchListPersonModel(searchParameter);		
		PersonJsonObject personJsonObject = new PersonJsonObject();		
		personJsonObject.setiTotalDisplayRecords(countRecords.intValue());		
		personJsonObject.setiTotalRecords(countRecords.intValue());
		personJsonObject.setAaData(personModelList);
		return personJsonObject;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	@RequestMapping(value = {"/allingresos"}, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody IngresoJsonObject allIngresosDataTables(HttpServletRequest request){
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart")){
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;
		}
		String searchParameter = request.getParameter("sSearch");
		List<IngresoModel> ingresoModels = ingresoService.searchListIngresoModel(searchParameter, pageNumber);
		Long countRecords = ingresoService.countSearchListIngresoModel(searchParameter);
		IngresoJsonObject ingresoJsonObject = new IngresoJsonObject();	
		ingresoJsonObject.setiTotalDisplayRecords(countRecords.intValue());		
		ingresoJsonObject.setiTotalRecords(countRecords.intValue());
		ingresoJsonObject.setAaData(ingresoModels);
		LOG.info("Returning ingresoJsonObject "+ingresoModels);
		return ingresoJsonObject;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@PostMapping("/upload")
	public String uploadImagePerfil(@RequestParam(name="id", required=false)String id,
			@RequestParam(name="file", required=false) MultipartFile file,
			Model model) throws Exception{
		LOG.info("METHOD: uploadImagePerfil() -- PARAMS: id:"+id+" file: "+file);
		//String webappRoot = servletContext.getRealPath("/");		
		if(id.equals("0") || id.isEmpty()){
			return "redirect:/user";
		}else{
			if (!file.isEmpty()) {
				storageService.store(file, id);
			}
			return "redirect:/user/usuarioform?id="+id;			
		}
	}	
	
	@GetMapping("/files")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")	
	public @ResponseBody ResponseEntity<Resource> getFile(@RequestParam(name="filename", required=false) String filename) {
		Resource file = storageService.loadFile(filename);
		return ResponseEntity.ok()
				.body(file);
	}
	
	@GetMapping("ingresosview")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	public String viewIngresosView(@RequestParam(name="result", required=false) Integer result,Model model){
		LOG.info("METHOD: viewIngresosView()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_INGRESO);
		lvm.setDescripcion("Aqui puedes gestionar todos los ingresos");
		model.addAttribute("lvm",lvm);
		model.addAttribute("personsecurity",personsecurity);
		model.addAttribute("result",result);
		LOG.info("Returning to "+ViewConstant.VIEW_INGRESOLIST+" view");
		return ViewConstant.VIEW_INGRESOLIST;
	}
	
	@GetMapping("ingresoform")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	public ModelAndView requestIngresoForm(@RequestParam(name="id", required=false) String id){
		LOG.info("METHOD: requestIngresoForm() -- PARAMS: "+id);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FORMINGRESO);
		PersonModel personModel = personService.findPersonModelById(id);
		//nuevo model ingreso
		IngresoModel ingreso = new IngresoModel();
		ingreso.setPersonModel(personModel);		
		//calcular edad
		DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fechaHora.format(personModel.getBirthdate()), fmt);
		LocalDate ahora = LocalDate.now();
		Period edad = Period.between(fechaNac, ahora);		
		//ultimos 10 ingresos
		List<IngresoModel> ingresoModels = ingresoService.listPageableIngresoModel(personModel);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_INGRESO);
		lvm.setDescripcion("Aquí puedes gestionar los ingresos");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		model.addObject("person", ingreso.getPersonModel());
		model.addObject("ingreso", ingreso);
		model.addObject("edad", edad.getYears() + " Años");
		model.addObject("ingresosprevios", ingresoModels);
		LOG.info("Returning to "+ViewConstant.VIEW_FORMINGRESO+" view -- PARAMS: "+ingreso);
		return model;
	}
	
	@PostMapping("/addingreso")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	public ModelAndView addIngreso(@Valid @ModelAttribute(name="ingreso") IngresoModel ingresoModel, BindingResult bindingResult){
		LOG.info("METHOD: addIngreso() -- PARAMS: " + ingresoModel);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView();
		LocationViewModel lvm = new LocationViewModel(HrefConstant.HREF_INGRESO,"ADMISIONES","Aquí puedes gestionar los ingresos");
		model.addObject("lvm", lvm);
		ingresoModel.setPersonModel(personService.findPersonModelById(ingresoModel.getPersonModel().getId()));
		ingresoModel.setFecha(new Date());
		if(ingresoService.countAllIngresos()==0){
			ingresoModel.setNorden(Long.parseLong("1"));
		}else{
			ingresoModel.setNorden(ingresoService.findMaxValueNorden()+1);
		}		
		ingresoModel.setTstatus(TypeIngresoStatus.EN_CURSO);
		if (bindingResult.hasErrors()) {
			//calcular edad
			DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate fechaNac = LocalDate.parse(fechaHora.format(ingresoModel.getPersonModel().getBirthdate()), fmt);
			LocalDate ahora = LocalDate.now();
			Period edad = Period.between(fechaNac, ahora);	
			//ultimos 10 ingresos
			List<IngresoModel> ingresoModels = ingresoService.listPageableIngresoModel(ingresoModel.getPersonModel());
			model.addObject("person", ingresoModel.getPersonModel());
			model.addObject("ingreso", ingresoModel);
			model.addObject("edad", edad.getYears() + " Años");
			model.addObject("ingresosprevios", ingresoModels);
			model.addObject("personsecurity",personsecurity);
			LOG.info("Returning to "+ViewConstant.VIEW_FORMINGRESO+" view -- PARAMS: "+ingresoModel+" -- bindingResult"+bindingResult );
			model.setViewName(ViewConstant.VIEW_FORMINGRESO);
		}else{						
			ingresoModel = ingresoService.addIngresoModel(ingresoModel);	
			LOG.info("Returning to ingresos view");
			if(null != ingresoModel){
				model.setViewName("redirect:/user/ingresosview?result=1");
			} else {
				model.setViewName("redirect:/user/ingresosview?result=0");
			}
		}
		return model;
	}//user/anulaingreso
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	@PostMapping("changedate")
	public String changeUpdate(@ModelAttribute(name="DNI") String id,
			@ModelAttribute(name="fecha") String fecha,
			@ModelAttribute(name="hora") String hora) throws ParseException{
		LOG.info("METHOD: changeUpdate() -- PARAMS: id=" + id+", fecha="+fecha+", hora="+hora);
		Date fechad;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
		fechad = df.parse(fecha+" "+hora);
		IngresoModel ingresoModel = ingresoService.findIngresoModelById(id);
		if(ingresoModel.getTstatus()==TypeIngresoStatus.ANULADO) {
			return "redirect:/user/ingresosview?result=4";
		}
		ingresoModel.setFecha(fechad);
		ingresoService.addIngresoModel(ingresoModel);
		return "redirect:/user/ingresosview?result=2";
	}	
	
	@PostMapping("anulaingreso")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	public String anulaIngreso(@ModelAttribute(name="idIngreso") String id) throws ParseException{
		LOG.info("METHOD: anulaIngreso() -- PARAMS: idIngreso=" + id);
		IngresoModel ingresoModel = ingresoService.findIngresoModelById(id);
		if(ingresoModel.getTstatus()==TypeIngresoStatus.ANULADO) {
			return "redirect:/user/ingresosview?result=4";
		}
		List<FacturaModel> facturaModels = facturaService.findFacturaModelByIngresoModel(ingresoModel);
		boolean facturaActiva=false;
		for(FacturaModel facturaModel:facturaModels) {
			if(!facturaModel.getEstado().equals("ANULADO")) {
				facturaActiva = true;
				break;
			}
		}
		if(facturaActiva==true) {
			return "redirect:/user/ingresosview?result=3";
		}
		ingresoModel.setTstatus(TypeIngresoStatus.ANULADO);
		ingresoService.addIngresoModel(ingresoModel);
		return "redirect:/user/ingresosview?result=1";
	}
	
	
	
	
	@GetMapping("citas")
	public ModelAndView requestCitasView(){
		LOG.info("METHOD: requestCitasView()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_CITAS);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_CITAS);
		lvm.setDescripcion("Aquí puedes gestionar citas");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_CITAS+" view");		
		return model;
	}
	
	@GetMapping("perfil")
	public ModelAndView requestUserPerfilForm(){
		LOG.info("METHOD: requestUserPerfilForm()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FORMUSER);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("PERFIL");
		lvm.setUbicacion(HrefConstant.HREF_PERFIL);
		lvm.setDescripcion("Aquí puedes gestionar tu perfil");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_FORMUSER+" view");		
		return model;
	}
	
	
}
