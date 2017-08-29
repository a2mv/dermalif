package com.pl10.dermalif.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pl10.dermalif.entity.City;
import com.pl10.dermalif.entity.Country;
import com.pl10.dermalif.repository.CityRepository;
import com.pl10.dermalif.repository.CountryRepository;
import com.pl10.dermalif.service.CitydemoService;

@Service("citydemoService")
public class CitydemoServiceImpl implements CitydemoService {

	@Autowired
	@Qualifier("cityRepository")
	private CityRepository cityRepository;
	
	@Autowired
	@Qualifier("countryRepository")
	private CountryRepository countryRepository;
	
	@Override
	public void addCities() {
		cityRepository.save(new City(countryRepository.save(new Country("Colombia")),"Bogotá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Medellín"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Cali"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Barranquilla"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Cartagena"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Cúcuta"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Soledad"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Ibagué"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Soacha"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Bucaramanga"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Villavicencio"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Santa Marta"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Pereira"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Bello"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Valledupar"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Montería"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Pasto"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Buenaventura"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Manizales"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Neiva"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Palmira"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Armenia"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Sincelejo"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Popayán"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Riohacha"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Itaguí"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Floridablanca"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Envigado"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Tuluá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Tumaco"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Dosquebradas"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Tunja"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Barrancabermeja"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Girón"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Apartadó"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Uribia"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Florencia"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Turbo"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Maicao"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Piedecuesta"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Yopal"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Ipiales"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Fusagasugá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Facatativá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Cartago"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Chía"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Pitalito"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Zipaquirá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Malambo"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Jamundí"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Rionegro"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Magangué"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Yumbo"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Lorica"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Caucasia"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Quibdó"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Buga"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Duitama"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Sogamoso"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Manaure"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Girardot"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Ciénaga"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Tierralta"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Sabanalarga"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Ocaña"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Santander de Quilichao"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Aguachica"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Cereté"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Villa del Rosario"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Garzón"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Arauca"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Sahagún"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Mosquera"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Montelíbano"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Candelaria"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Madrid"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Chigorodó"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Caldas"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Los Patios"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Calarcá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Funza"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"La Dorada"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"El Carmen de Bolívar"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Espinal"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Arjona"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Turbaco"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Santa Rosa de Cabal"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"San Andrés"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Acacías"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Copacabana"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"San Vicente del Caguán"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Planeta Rica"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Chiquinquirá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Ciénaga de Oro"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"San José del Guaviare"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Necoclí"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"La Plata"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Granada"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"La Estrella"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Corozal"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Riosucio"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Puerto Asís"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Zona Bananera"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Plato"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Cajicá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Baranoa"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Carepa"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Florida"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"San Marcos"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Villamaría"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Pamplona"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"El Cerrito"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Fundación"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Girardota"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Pradera"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Puerto Boyacá"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"El Banco"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Orito"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Marinilla"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"La Ceja"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Tame"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Sabaneta"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Ayapel"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Valle del Guamuez"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Barbosa"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"San Onofre"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Chinchiná"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Puerto Libertador"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"El Bagre"));
		cityRepository.save(new City(countryRepository.findByName("Colombia"),"Agustín Codazzi"));
		cityRepository.save(new City(countryRepository.save(new Country("Afganistán")),"Kabul"));
		cityRepository.save(new City(countryRepository.save(new Country("Albania")),"Tirana"));
		cityRepository.save(new City(countryRepository.save(new Country("Alemania")),"Berlín"));
		cityRepository.save(new City(countryRepository.save(new Country("Andorra")),"Andorra la Vieja"));
		cityRepository.save(new City(countryRepository.save(new Country("Angola")),"Luanda"));
		cityRepository.save(new City(countryRepository.save(new Country("Antigua y Barbuda")),"Saint John’s"));
		cityRepository.save(new City(countryRepository.save(new Country("Arabia Saudita")),"Riad"));
		cityRepository.save(new City(countryRepository.save(new Country("Argelia")),"Argel"));
		cityRepository.save(new City(countryRepository.save(new Country("Argentina")),"Buenos Aires"));
		cityRepository.save(new City(countryRepository.save(new Country("Armenia")),"Ereván"));
		cityRepository.save(new City(countryRepository.save(new Country("Australia")),"Camberra"));
		cityRepository.save(new City(countryRepository.save(new Country("Austria")),"Viena"));
		cityRepository.save(new City(countryRepository.save(new Country("Azerbaiyán")),"Bakú"));
		cityRepository.save(new City(countryRepository.save(new Country("Bahamas")),"Nasáu"));
		cityRepository.save(new City(countryRepository.save(new Country("Bangladés")),"Daca"));
		cityRepository.save(new City(countryRepository.save(new Country("Barbados")),"Bridgetown"));
		cityRepository.save(new City(countryRepository.save(new Country("Baréin")),"Manama"));
		cityRepository.save(new City(countryRepository.save(new Country("Bélgica")),"Bruselas"));
		cityRepository.save(new City(countryRepository.save(new Country("Belice")),"Belmopán"));
		cityRepository.save(new City(countryRepository.save(new Country("Benín")),"Porto Novo y Cotonú"));
		cityRepository.save(new City(countryRepository.save(new Country("Bielorrusia")),"Minsk"));
		cityRepository.save(new City(countryRepository.save(new Country("Birmania")),"Naipyidó"));
		cityRepository.save(new City(countryRepository.save(new Country("Bolivia")),"Sucre"));
		cityRepository.save(new City(countryRepository.save(new Country("Bosnia y Herzegovina")),"Sarajevo"));
		cityRepository.save(new City(countryRepository.save(new Country("Botsuana")),"Gaborone"));
		cityRepository.save(new City(countryRepository.save(new Country("Brasil")),"Brasilia"));
		cityRepository.save(new City(countryRepository.save(new Country("Brunéi")),"Bandar Seri Begawan"));
		cityRepository.save(new City(countryRepository.save(new Country("Bulgaria")),"Sofía"));
		cityRepository.save(new City(countryRepository.save(new Country("Burkina Faso")),"Uagadugú"));
		cityRepository.save(new City(countryRepository.save(new Country("Burundi")),"Buyumbura"));
		cityRepository.save(new City(countryRepository.save(new Country("Bután")),"Timbu"));
		cityRepository.save(new City(countryRepository.save(new Country("Cabo Verde")),"Praia"));
		cityRepository.save(new City(countryRepository.save(new Country("Camboya")),"Nom Pen"));
		cityRepository.save(new City(countryRepository.save(new Country("Camerún")),"Yaundé"));
		cityRepository.save(new City(countryRepository.save(new Country("Canadá")),"Ottawa"));
		cityRepository.save(new City(countryRepository.save(new Country("Catar")),"Doha"));
		cityRepository.save(new City(countryRepository.save(new Country("Chad")),"Yamena"));
		cityRepository.save(new City(countryRepository.save(new Country("Chile")),"Santiago de Chile"));
		cityRepository.save(new City(countryRepository.save(new Country("China")),"Pekín"));
		cityRepository.save(new City(countryRepository.save(new Country("Chipre")),"Nicosia"));
		cityRepository.save(new City(countryRepository.save(new Country("Ciudad del Vaticano")),"Ciudad del Vaticano"));
		cityRepository.save(new City(countryRepository.save(new Country("Comoras")),"Moroni"));
		cityRepository.save(new City(countryRepository.save(new Country("Corea del Norte")),"Pionyang"));
		cityRepository.save(new City(countryRepository.save(new Country("Corea del Sur")),"Seúl"));
		cityRepository.save(new City(countryRepository.save(new Country("Costa de Marfil")),"Yamusukro"));
		cityRepository.save(new City(countryRepository.save(new Country("Costa Rica")),"San José"));
		cityRepository.save(new City(countryRepository.save(new Country("Croacia")),"Zagreb"));
		cityRepository.save(new City(countryRepository.save(new Country("Cuba")),"La Habana"));
		cityRepository.save(new City(countryRepository.save(new Country("Dinamarca")),"Copenhague"));
		cityRepository.save(new City(countryRepository.save(new Country("Dominica")),"Roseau"));
		cityRepository.save(new City(countryRepository.save(new Country("Ecuador")),"Quito"));
		cityRepository.save(new City(countryRepository.save(new Country("Egipto")),"El Cairo"));
		cityRepository.save(new City(countryRepository.save(new Country("El Salvador")),"San Salvador"));
		cityRepository.save(new City(countryRepository.save(new Country("Emiratos Árabes Unidos")),"Abu Dabi"));
		cityRepository.save(new City(countryRepository.save(new Country("Eritrea")),"Asmara"));
		cityRepository.save(new City(countryRepository.save(new Country("Eslovaquia")),"Bratislava"));
		cityRepository.save(new City(countryRepository.save(new Country("Eslovenia")),"Liubliana"));
		cityRepository.save(new City(countryRepository.save(new Country("España")),"Madrid"));
		cityRepository.save(new City(countryRepository.save(new Country("Estados Unidos")),"Washington DC"));
		cityRepository.save(new City(countryRepository.save(new Country("Estonia")),"Tallin"));
		cityRepository.save(new City(countryRepository.save(new Country("Etiopía")),"Adís Abeba"));
		cityRepository.save(new City(countryRepository.save(new Country("Filipinas")),"Manila"));
		cityRepository.save(new City(countryRepository.save(new Country("Finlandia")),"Helsinki"));
		cityRepository.save(new City(countryRepository.save(new Country("Fiyi")),"Suva"));
		cityRepository.save(new City(countryRepository.save(new Country("Francia")),"París"));
		cityRepository.save(new City(countryRepository.save(new Country("Gabón")),"Libreville"));
		cityRepository.save(new City(countryRepository.save(new Country("Gambia")),"Banjul"));
		cityRepository.save(new City(countryRepository.save(new Country("Georgia")),"Tiflis"));
		cityRepository.save(new City(countryRepository.save(new Country("Ghana")),"Acra"));
		cityRepository.save(new City(countryRepository.save(new Country("Granada")),"Saint George"));
		cityRepository.save(new City(countryRepository.save(new Country("Grecia")),"Atenas"));
		cityRepository.save(new City(countryRepository.save(new Country("Guatemala")),"Ciudad de Guatemala"));
		cityRepository.save(new City(countryRepository.save(new Country("Guyana")),"Georgetown"));
		cityRepository.save(new City(countryRepository.save(new Country("Guinea")),"Conakri"));
		cityRepository.save(new City(countryRepository.save(new Country("Guinea-Bisáu")),"Bisáu"));
		cityRepository.save(new City(countryRepository.save(new Country("Guinea Ecuatorial")),"Malabo"));
		cityRepository.save(new City(countryRepository.save(new Country("Haití")),"Puerto Príncipe"));
		cityRepository.save(new City(countryRepository.save(new Country("Honduras")),"Tegucigalpa"));
		cityRepository.save(new City(countryRepository.save(new Country("Hungría")),"Budapest"));
		cityRepository.save(new City(countryRepository.save(new Country("India")),"Nueva Delhi"));
		cityRepository.save(new City(countryRepository.save(new Country("Indonesia")),"Yakarta"));
		cityRepository.save(new City(countryRepository.save(new Country("Irak")),"Bagdad"));
		cityRepository.save(new City(countryRepository.save(new Country("Irán")),"Teherán"));
		cityRepository.save(new City(countryRepository.save(new Country("Irlanda")),"Dublín"));
		cityRepository.save(new City(countryRepository.save(new Country("Islandia")),"Reikiavik"));
		cityRepository.save(new City(countryRepository.save(new Country("Islas Marshall")),"Majuro"));
		cityRepository.save(new City(countryRepository.save(new Country("Islas Salomón")),"Honiara"));
		cityRepository.save(new City(countryRepository.save(new Country("Israel")),"Jerusalén"));
		cityRepository.save(new City(countryRepository.save(new Country("Italia")),"Roma"));
		cityRepository.save(new City(countryRepository.save(new Country("Jamaica")),"Kingston"));
		cityRepository.save(new City(countryRepository.save(new Country("Japón")),"Tokio"));
		cityRepository.save(new City(countryRepository.save(new Country("Jordania")),"Amán"));
		cityRepository.save(new City(countryRepository.save(new Country("Kazajistán")),"Astaná"));
		cityRepository.save(new City(countryRepository.save(new Country("Kenia")),"Nairobi"));
		cityRepository.save(new City(countryRepository.save(new Country("Kirguistán")),"Biskek"));
		cityRepository.save(new City(countryRepository.save(new Country("Kiribati")),"Tarawa"));
		cityRepository.save(new City(countryRepository.save(new Country("Kuwait")),"Kuwait"));
		cityRepository.save(new City(countryRepository.save(new Country("Laos")),"Vientián"));
		cityRepository.save(new City(countryRepository.save(new Country("Lesoto")),"Maseru"));
		cityRepository.save(new City(countryRepository.save(new Country("Letonia")),"Riga"));
		cityRepository.save(new City(countryRepository.save(new Country("Líbano")),"Beirut"));
		cityRepository.save(new City(countryRepository.save(new Country("Liberia")),"Monrovia"));
		cityRepository.save(new City(countryRepository.save(new Country("Libia")),"Trípoli"));
		cityRepository.save(new City(countryRepository.save(new Country("Liechtenstein")),"Vaduz"));
		cityRepository.save(new City(countryRepository.save(new Country("Lituania")),"Vilna"));
		cityRepository.save(new City(countryRepository.save(new Country("Luxemburgo")),"Luxemburgo"));
		cityRepository.save(new City(countryRepository.save(new Country("Madagascar")),"Antananarivo"));
		cityRepository.save(new City(countryRepository.save(new Country("Malasia")),"Kuala Lumpur"));
		cityRepository.save(new City(countryRepository.save(new Country("Malaui")),"Lilongüe"));
		cityRepository.save(new City(countryRepository.save(new Country("Maldivas")),"Malé"));
		cityRepository.save(new City(countryRepository.save(new Country("Malí")),"Bamako"));
		cityRepository.save(new City(countryRepository.save(new Country("Malta")),"La Valeta"));
		cityRepository.save(new City(countryRepository.save(new Country("Marruecos")),"Rabat"));
		cityRepository.save(new City(countryRepository.save(new Country("Mauricio")),"Port-Louis"));
		cityRepository.save(new City(countryRepository.save(new Country("Mauritania")),"Nuakchot"));
		cityRepository.save(new City(countryRepository.save(new Country("México")),"Ciudad de México"));
		cityRepository.save(new City(countryRepository.save(new Country("Micronesia")),"Palikir"));
		cityRepository.save(new City(countryRepository.save(new Country("Moldavia")),"Chisináu"));
		cityRepository.save(new City(countryRepository.save(new Country("Mónaco")),"Mónaco"));
		cityRepository.save(new City(countryRepository.save(new Country("Mongolia")),"Ulán Bator"));
		cityRepository.save(new City(countryRepository.save(new Country("Montenegro")),"Podgorica"));
		cityRepository.save(new City(countryRepository.save(new Country("Mozambique")),"Maputo"));
		cityRepository.save(new City(countryRepository.save(new Country("Namibia")),"Windhoek"));
		cityRepository.save(new City(countryRepository.save(new Country("Nauru")),"Yaren"));
		cityRepository.save(new City(countryRepository.save(new Country("Nepal")),"Katmandú"));
		cityRepository.save(new City(countryRepository.save(new Country("Nicaragua")),"Managua"));
		cityRepository.save(new City(countryRepository.save(new Country("Níger")),"Niamey"));
		cityRepository.save(new City(countryRepository.save(new Country("Nigeria")),"Abuya"));
		cityRepository.save(new City(countryRepository.save(new Country("Noruega")),"Oslo"));
		cityRepository.save(new City(countryRepository.save(new Country("Nueva Zelanda")),"Wellington"));
		cityRepository.save(new City(countryRepository.save(new Country("Omán")),"Mascate"));
		cityRepository.save(new City(countryRepository.save(new Country("Países Bajos")),"Ámsterdam"));
		cityRepository.save(new City(countryRepository.save(new Country("Pakistán")),"Islamabad"));
		cityRepository.save(new City(countryRepository.save(new Country("Palaos")),"Melekeok"));
		cityRepository.save(new City(countryRepository.save(new Country("Panamá")),"Panamá"));
		cityRepository.save(new City(countryRepository.save(new Country("Papúa Nueva Guinea")),"Port Moresby"));
		cityRepository.save(new City(countryRepository.save(new Country("Paraguay")),"Asunción"));
		cityRepository.save(new City(countryRepository.save(new Country("Perú")),"Lima"));
		cityRepository.save(new City(countryRepository.save(new Country("Polonia")),"Varsovia"));
		cityRepository.save(new City(countryRepository.save(new Country("Portugal")),"Lisboa"));
		cityRepository.save(new City(countryRepository.save(new Country("Reino Unido de Gran Bretaña e Irlanda del Norte")),"Londres"));
		cityRepository.save(new City(countryRepository.save(new Country("República Centroafricana")),"Bangui"));
		cityRepository.save(new City(countryRepository.save(new Country("República Checa")),"Praga"));
		cityRepository.save(new City(countryRepository.save(new Country("República de Macedonia")),"Skopie"));
		cityRepository.save(new City(countryRepository.save(new Country("República del Congo")),"Brazzaville"));
		cityRepository.save(new City(countryRepository.save(new Country("República Democrática del Congo")),"Kinsasa"));
		cityRepository.save(new City(countryRepository.save(new Country("República Dominicana")),"Santo Domingo"));
		cityRepository.save(new City(countryRepository.save(new Country("República Sudafricana")),"Bloemfontein, Ciudad Del Cabo y Pretoria"));
		cityRepository.save(new City(countryRepository.save(new Country("Ruanda")),"Kigali"));
		cityRepository.save(new City(countryRepository.save(new Country("Rumanía")),"Bucarest"));
		cityRepository.save(new City(countryRepository.save(new Country("Rusia")),"Moscú"));
		cityRepository.save(new City(countryRepository.save(new Country("Samoa")),"Apia"));
		cityRepository.save(new City(countryRepository.save(new Country("San Cristóbal y Nieves")),"Basseterre"));
		cityRepository.save(new City(countryRepository.save(new Country("San Marino")),"San Marino"));
		cityRepository.save(new City(countryRepository.save(new Country("San Vicente y las Granadinas")),"Kingstown"));
		cityRepository.save(new City(countryRepository.save(new Country("Santa Lucía")),"Castries"));
		cityRepository.save(new City(countryRepository.save(new Country("Santo Tomé y Príncipe")),"Santo Tomé"));
		cityRepository.save(new City(countryRepository.save(new Country("Senegal")),"Dakar"));
		cityRepository.save(new City(countryRepository.save(new Country("Serbia")),"Belgrado"));
		cityRepository.save(new City(countryRepository.save(new Country("Seychelles")),"Victoria"));
		cityRepository.save(new City(countryRepository.save(new Country("Sierra Leona")),"Freetown"));
		cityRepository.save(new City(countryRepository.save(new Country("Singapur")),"Singapur"));
		cityRepository.save(new City(countryRepository.save(new Country("Siria")),"Damasco"));
		cityRepository.save(new City(countryRepository.save(new Country("Somalia")),"Mogadiscio"));
		cityRepository.save(new City(countryRepository.save(new Country("Sri Lanka")),"Sri Jayewardenepura"));
		cityRepository.save(new City(countryRepository.save(new Country("Suazilandia")),"Babane y Lobamba"));
		cityRepository.save(new City(countryRepository.save(new Country("Sudán")),"Jartum"));
		cityRepository.save(new City(countryRepository.save(new Country("Sudán del Sur")),"Yuba"));
		cityRepository.save(new City(countryRepository.save(new Country("Suecia")),"Estocolmo"));
		cityRepository.save(new City(countryRepository.save(new Country("Suiza")),"Berna"));
		cityRepository.save(new City(countryRepository.save(new Country("Surinam")),"Paramaribo"));
		cityRepository.save(new City(countryRepository.save(new Country("Tailandia")),"Bangkok"));
		cityRepository.save(new City(countryRepository.save(new Country("Tanzania")),"Dodoma"));
		cityRepository.save(new City(countryRepository.save(new Country("Tayikistán")),"Dusambé"));
		cityRepository.save(new City(countryRepository.save(new Country("Timor Oriental")),"Dili"));
		cityRepository.save(new City(countryRepository.save(new Country("Togo")),"Lomé"));
		cityRepository.save(new City(countryRepository.save(new Country("Tonga")),"Nukualofa"));
		cityRepository.save(new City(countryRepository.save(new Country("Trinidad y Tobago")),"Puerto España"));
		cityRepository.save(new City(countryRepository.save(new Country("Túnez")),"Túnez"));
		cityRepository.save(new City(countryRepository.save(new Country("Turkmenistán")),"Asjabad"));
		cityRepository.save(new City(countryRepository.save(new Country("Turquía")),"Ankara"));
		cityRepository.save(new City(countryRepository.save(new Country("Tuvalu")),"Fongafale"));
		cityRepository.save(new City(countryRepository.save(new Country("Ucrania")),"Kiev"));
		cityRepository.save(new City(countryRepository.save(new Country("Uganda")),"Kampala"));
		cityRepository.save(new City(countryRepository.save(new Country("Uruguay")),"Montevideo"));
		cityRepository.save(new City(countryRepository.save(new Country("Uzbekistán")),"Taskent"));
		cityRepository.save(new City(countryRepository.save(new Country("Vanuatu")),"Port Vila"));
		cityRepository.save(new City(countryRepository.save(new Country("Venezuela")),"Caracas"));
		cityRepository.save(new City(countryRepository.save(new Country("Vietnam")),"Hanói"));
		cityRepository.save(new City(countryRepository.save(new Country("Yemen")),"Saná"));
		cityRepository.save(new City(countryRepository.save(new Country("Yibuti")),"Yibuti"));
		cityRepository.save(new City(countryRepository.save(new Country("Zambia")),"Lusaka"));
		cityRepository.save(new City(countryRepository.save(new Country("Zimbabue")),"Harare"));	
		
	}

}