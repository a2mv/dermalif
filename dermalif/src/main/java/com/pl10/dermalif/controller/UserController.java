package com.pl10.dermalif.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
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
import com.pl10.dermalif.model.IngresoJsonObject;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonJsonObject;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.model.UserRolePersonModel;
import com.pl10.dermalif.service.CityService;
import com.pl10.dermalif.service.IngresoService;
import com.pl10.dermalif.service.PersonSevice;
import com.pl10.dermalif.service.UserService;

@Controller
@RequestMapping("user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
	
	private static final Log LOG = LogFactory.getLog(UserController.class);
	
	@Autowired
	@Qualifier("personService")
	private PersonSevice personService;
	
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping("/usuarioform")
	public ModelAndView requestUserForm(@RequestParam(name="id", required=false) String id){
		LOG.info("METHOD: requestUserForm() -- PARAMS: "+id);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		PersonModel personModel = new PersonModel();
		if(!"0".equals(id)){
			personModel = personService.findPersonModelById(id);
		}else{
			personModel.setImageprofile("/dist/img/avatar5.png");
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
			if(personModel.getId().equals("")){
				personModel.setImageprofile("/dist/img/avatar5.png");
			}else{
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
		String webappRoot = servletContext.getRealPath("/");
		if(id.equals("0") || id.isEmpty()){
			return "redirect:/user";
		}else{
			if (!file.isEmpty()) {
				BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
				File destination = new File(webappRoot + "/resources/profile-pictures/ProfileImage"+id+".png");
				ImageIO.write(src, "png", destination);
				PersonModel personModel = personService.findPersonModelById(id);
				personModel.setImageprofile("/resources/profile-pictures/ProfileImage"+id+".png");
				personService.addPersonModel(personModel);
			}
			return "redirect:/user/usuarioform?id="+id;			
		}
	}	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	@GetMapping("ingresosview")
	public String viewIngresosView(Model model){
		LOG.info("METHOD: viewIngresosView()");
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("ADMISIONES");
		lvm.setUbicacion(HrefConstant.HREF_INGRESO);
		lvm.setDescripcion("Aqui puedes gestionar todos los ingresos");
		model.addAttribute("lvm",lvm);
		model.addAttribute("personsecurity",personsecurity);
		LOG.info("Returning to "+ViewConstant.VIEW_INGRESOLIST+" view");
		return ViewConstant.VIEW_INGRESOLIST;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	@GetMapping("ingresoform")
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	@PostMapping("/addingreso")
	public ModelAndView addIngreso(@Valid @ModelAttribute(name="ingreso") IngresoModel ingresoModel, BindingResult bindingResult){
		LOG.info("METHOD: addPerson() -- PARAMS: " + ingresoModel);
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
			LOG.info("Returning to user/index view");
			model.setViewName("redirect:/user");
			if(null != ingresoService.addIngresoModel(ingresoModel)){
				model.addObject("result", 1);
			} else {
				model.addObject("result", 0);
			}
		}
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_INGRESO')")
	@PostMapping("changedate")
	public String changeUpdate(@ModelAttribute(name="DNI") String id,
			@ModelAttribute(name="fecha") Date fecha,
			@ModelAttribute(name="hora") String hora) throws ParseException{
		LOG.info("METHOD: changeUpdate() -- PARAMS: id=" + id+", fecha="+fecha+", hora="+hora);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy ");
		String datefinal = df.format(fecha);
		datefinal = datefinal+hora;
		df = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
		fecha = df.parse(datefinal);
		IngresoModel ingresoModel = ingresoService.findIngresoModelById(id);
		ingresoModel.setFecha(fecha);
		ingresoService.addIngresoModel(ingresoModel);
		return "redirect:/user/ingresosview";
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
