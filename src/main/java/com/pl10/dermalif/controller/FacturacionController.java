package com.pl10.dermalif.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import com.pl10.dermalif.enums.TypeIngresoStatus;
import com.pl10.dermalif.model.ArticuloAjaxResponse;
import com.pl10.dermalif.model.ArticuloJsonObject;
import com.pl10.dermalif.model.ArticuloModel;
import com.pl10.dermalif.model.FacturaModel;
import com.pl10.dermalif.model.FacturadescModel;
import com.pl10.dermalif.model.IngresoModel;
import com.pl10.dermalif.model.LocationViewModel;
import com.pl10.dermalif.model.PersonModel;
import com.pl10.dermalif.service.ArticuloService;
import com.pl10.dermalif.service.FacturaService;
import com.pl10.dermalif.service.FacturadescService;
import com.pl10.dermalif.service.IngresoService;
import com.pl10.dermalif.service.PersonService;
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
	
	@Autowired
	@Qualifier("ingresoService")
	private IngresoService ingresoService;
	
	@Autowired
	@Qualifier("facturaService")
	private FacturaService facturaService;
	
	@Autowired
	@Qualifier("facturadescService")
	private FacturadescService facturadescService;
	
	@Autowired
	@Qualifier("personService")
	PersonService personService;

	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	@GetMapping("ingresos")
	public ModelAndView requestIngresosFact(@RequestParam(name="result", required=false) String result){
		LOG.info("METHOD: requestIngresosFact() --PARAMS: result="+result);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FACTINGRESOS);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_FACTINGR);
		lvm.setDescripcion("Aquí puedes ver los ingresos y facturas");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		model.addObject("result","0");
		//error de generacion de factura
		if(result.equals("1")) {
			model.addObject("result",1);
		}
		LOG.info("Returning to "+ViewConstant.VIEW_FORMHC+" view");		
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
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
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
	
	@GetMapping("getconsecutivo")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	public @ResponseBody String getConsecutivo() {
		LOG.info("METHOD: getConsecutivo()");
		return facturaService.generarConsecutivo().toString();
	}
	
	@PostMapping("createfactura")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	public ModelAndView createFactura(@ModelAttribute(name="idIngreso") String idIngreso,
			@ModelAttribute(name="idFact") Integer idFact) {
		LOG.info("METHOD: createFactura() -- PARAMS: idFact=" + idFact+", idIngreso="+idIngreso);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FACTNEW);
		IngresoModel ingresoModel = ingresoService.findIngresoModelById(idIngreso);
		if(ingresoModel.getTstatus()==TypeIngresoStatus.ANULADO) {
			return new ModelAndView("redirect:/user/ingresosview?result=4");
		}
		//factura				
		if(facturaService.existFactura(idFact)!=false) {
			return new ModelAndView("redirect:/factura/ingresos?result=1");
		}else {
			
			FacturaModel facturaModel = new FacturaModel();
			facturaModel.setIdfactura(idFact);
			facturaModel.setIngresomodel(ingresoModel);
			facturaModel.setUser(user.getUsername());
			facturaModel.setEstado("NUEVO");
			facturaModel.setFecha(new Date());
			facturaModel.setSubtotal(0.0);
			facturaModel.setValoriva(0.0);
			facturaModel.setValortotal(0.0);
			facturaModel.setIva(0.0);
			facturaModel = facturaService.addFactura(facturaModel);			
			model = new ModelAndView("redirect:/factura/new?factura="+facturaModel.getId());
			return model;
		}
	}
	
	@GetMapping("new")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")	
	public ModelAndView requestNewFact(@RequestParam(name="factura", required=false) String id){
		LOG.info("METHOD: requestNewFact() -- PARAMS: id="+id);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PersonModel personsecurity = userService.userConverter(user);
		FacturaModel facturaModel = facturaService.findFacturaModelById(id);
		List<FacturadescModel> facturadescModels = facturadescService.findFacturadescModelByFactura(facturaModel);
		ModelAndView model = new ModelAndView(ViewConstant.VIEW_FACTNEW);
		LocationViewModel lvm = new LocationViewModel();
		lvm.setModulo("FACTURACIÓN");
		lvm.setUbicacion(HrefConstant.HREF_FACTINGR);
		lvm.setDescripcion("Aquí puedes gestionar tu factura");
		model.addObject("lvm",lvm);
		model.addObject("personsecurity",personsecurity);
		//Person
		IngresoModel ingresoModel = facturaModel.getIngresomodel();		
		model.addObject("person", ingresoModel.getPersonModel());
		//calcular edad
		DateFormat fechaHora = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaNac = LocalDate.parse(fechaHora.format(ingresoModel.getPersonModel().getBirthdate()), fmt);
		LocalDate ahora = LocalDate.now();
		Period edad = Period.between(fechaNac, ahora);	
		model.addObject("edad", edad.getYears() + " Años");
		//crear factura
		model.addObject("factura",facturaModel);
		model.addObject("facturadesc",facturadescModels);
		LOG.info("Returning to "+ViewConstant.VIEW_FACTNEW+" view");		
		return model;
	}
	
	@GetMapping("/search/articulo")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	public @ResponseBody List<ArticuloAjaxResponse> searchArticulos(@RequestParam String searchString){
		LOG.info("Requesting search articulos with term: {} " + searchString);
		List<ArticuloAjaxResponse> list = articuloService.listArticuloAjaxResponse(searchString);
		return list;
	}
	
	@GetMapping("/search/articulo/id")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	public @ResponseBody ArticuloModel getArticulo(@RequestParam(name="id", required=false) Integer id){
		LOG.info("Requesting search articulos with term: {} " + id);
		return articuloService.findArticuloModelById(id);
	}
	
	@GetMapping("addarticulo")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	public @ResponseBody String agregarArticulo(@RequestParam(name="id", required=false) Integer id,
			@RequestParam(name="idf", required=false) String factura,
			@RequestParam(name="cantidad", required=false) Integer cantidad,
			@RequestParam(name="valorart", required=false) String valorart,
			@RequestParam(name="importe", required=false) String importe,
			@RequestParam(name="iva", required=false) String iva){
		LOG.info("METHOD: agregarArticulo() -- PARAMS: id="+id+" factura="+factura
				+" cantidad="+cantidad+" valorart="+valorart+" importe="+importe);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FacturadescModel facturadescModel = new FacturadescModel();
		try {
			FacturaModel facturaModel = facturaService.findFacturaModelById(factura);
			if(!facturaModel.getEstado().equals("NUEVO")) {
				LOG.info("METHOD: agregarArticulo() no se puede editar");
				return null;
			}
			facturaModel.setValortotal(facturaModel.getValortotal()+Double.parseDouble(importe));
			Double ivad = Double.parseDouble(iva);
			facturaModel.setIva(ivad);
			facturaModel.setValoriva((ivad/100)*facturaModel.getValortotal());
			facturaModel.setSubtotal(facturaModel.getValortotal() - facturaModel.getValoriva());
			facturaModel.setUser(user.getUsername());
			facturaModel = facturaService.addFactura(facturaModel);				
			facturadescModel.setFacturamodel(facturaModel);
			facturadescModel.setCantidad(cantidad);
			ArticuloModel articuloModel = articuloService.findArticuloModelById(id);
			facturadescModel.setCodarticulo(articuloModel.getCodigo());
			facturadescModel.setNamearticulo(articuloModel.getName());
			facturadescModel.setVarticulo(Double.parseDouble(valorart));
			facturadescModel.setVdescuento(articuloModel.getValor()-facturadescModel.getVarticulo());
			facturadescModel.setVtotal(Double.parseDouble(importe));
			facturadescModel = facturadescService.addFacturadescModel(facturadescModel);
		} catch (Exception e) {
			LOG.info("METHOD: agregarArticulo() request null Exception="+e);
			return null;
		}		
		return facturadescModel.getId();
	}
	
	@GetMapping("removearticulo")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FACTURA')")
	public @ResponseBody String eliminarArticulo(@RequestParam(name="id", required=false) String id){
		LOG.info("METHOD: agregarArticulo() -- PARAMS: id="+id);
		FacturadescModel facturadescModel = facturadescService.findFacturadescModelById(id);
		try {
			FacturaModel facturaModel = facturadescModel.getFacturamodel();
			if(!facturaModel.getEstado().equals("NUEVO")) {
				LOG.info("METHOD: eliminarArticulo() no se puede editar");
				return null;
			}
			facturaModel.setValortotal(facturaModel.getValortotal() - facturadescModel.getVtotal());
			facturaModel.setValoriva(facturaModel.getValortotal() - ((facturaModel.getIva()/100)*facturaModel.getValortotal()));
			facturaModel.setSubtotal(facturaModel.getValortotal()-facturaModel.getValoriva());
			facturadescService.removeFacturadescModel(facturadescModel);
			facturaModel= facturaService.addFactura(facturaModel);
		} catch (Exception e) {
			LOG.info("METHOD: eliminarArticulo() request null Exception="+e);
			return null;
		}		
		return "OK";
	}
	
	
	@GetMapping("reportfactura")
	@PreAuthorize("hasRole('ROLE_DOCUMENTO')")
	public ModelAndView reporteFactura(@RequestParam(name="id", required=false) String id){
		LOG.info("METHOD: reporteFactura() -- PARAMS: id=" + id);
		ModelAndView model = new ModelAndView(ViewConstant.DOCS_FACTURA);		
		FacturaModel facturaModel = facturaService.findFacturaModelById(id);
		PersonModel personModel = personService.findPersonModelByEmail(facturaModel.getUser());
		List<FacturadescModel> facturadescModels = facturadescService.findFacturadescModelByFactura(facturaModel);
		
		model.addObject("factura",facturaModel);
		model.addObject("person",personModel);
		model.addObject("facturadesc",facturadescModels);
		return model;
	}
	
}
