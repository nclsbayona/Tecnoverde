package co.edu.javeriana.ms.Calificacion.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import co.edu.javeriana.ms.Calificacion.entities.Contenido;
import co.edu.javeriana.ms.Calificacion.entities.InfoAdicional;
import co.edu.javeriana.ms.Calificacion.entities.MultimediaXInfo;
import co.edu.javeriana.ms.Calificacion.entities.Pregunta;
import co.edu.javeriana.ms.Calificacion.entities.Servicio;
import co.edu.javeriana.ms.Calificacion.entities.TipoInformacion;
import co.edu.javeriana.ms.Calificacion.entities.TipoServicio;
import co.edu.javeriana.ms.Calificacion.entities.aux.CrearServicio;
import co.edu.javeriana.ms.Calificacion.entities.dtos.ContenidoDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.InfoAdicionalDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.PreguntaDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.ProveedorDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.SearchResultsDTO;
import co.edu.javeriana.ms.Calificacion.entities.dtos.ServicioDTO;
import co.edu.javeriana.ms.Calificacion.repositories.ContenidoRepository;
import co.edu.javeriana.ms.Calificacion.repositories.InfoAdicionalRepository;
import co.edu.javeriana.ms.Calificacion.repositories.MultimediaXInfoRepository;
import co.edu.javeriana.ms.Calificacion.repositories.PreguntaRepository;
import co.edu.javeriana.ms.Calificacion.repositories.RespuestaRepository;
import co.edu.javeriana.ms.Calificacion.repositories.ServicioRepository;
import co.edu.javeriana.ms.Calificacion.repositories.TipoInformacionRepository;
import co.edu.javeriana.ms.Calificacion.repositories.TipoServicioRepository;

@Service
public class ProductosService {

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private InfoAdicionalRepository infoAdicionalRepository;

    @Autowired
    private PreguntaRepository preguntaRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private TipoServicioRepository tipoServicioRepository;

    @Autowired
    private TipoInformacionRepository tipoInformacionRepository;

    @Autowired
    private MultimediaXInfoRepository multimediaXInfoRepository;

    @Autowired
    @Qualifier("Normal")
    private RestTemplate restTemplate;
    /*
     * @Autowired
     * private TipoContenidoRepository tipoContenidoRepository;
     */

    public SearchResultsDTO getAllProducts() {
        SearchResultsDTO searchResults = null;
        List<ContenidoDTO> contenidos = new ArrayList<>();
        List<ServicioDTO> servicios = new ArrayList<>();
        ;
        for (Contenido contenido : contenidoRepository.findAll()) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(contenido);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(contenido);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(contenido.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            contenidos.add(new ContenidoDTO(contenido, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        for (Servicio servicio : servicioRepository.findAll()) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            servicios.add(new ServicioDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return searchResults;
    }

    public ServicioDTO getServicioById(Long id) {
        Servicio servicio = servicioRepository.findById(id).get();
        List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
        List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
        ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
        List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                .collect(Collectors.toList());
        List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
        for (Pregunta p : preguntas) {
            List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
            PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
            preguntasDTOs.add(preguntaDTO);
        }
        return new ServicioDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs);
    }

    public List<ServicioDTO> getServiciosByTipo(String tipo) {
        List<ServicioDTO> serviciosDTOs = new ArrayList<>();
        for (Servicio servicio : servicioRepository.findByTipoServicioTipo(tipo)) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p,
                        usuariosService.getClienteById(Long.parseLong(p.getCliente_ID())), null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            serviciosDTOs.add(new ServicioDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return serviciosDTOs;
    }

    public List<ContenidoDTO> getContenidosByTipo(String tipo) {
        List<ContenidoDTO> serviciosDTOs = new ArrayList<>();
        for (Contenido servicio : contenidoRepository.findByTipoContenidoTipo(tipo)) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p,
                        usuariosService.getClienteById(Long.parseLong(p.getCliente_ID())), null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            serviciosDTOs.add(new ContenidoDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return serviciosDTOs;
    }

    public ContenidoDTO getContenidoById(Long id) {
        Contenido contenido = contenidoRepository.findById(id).get();
        List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(contenido);
        List<Pregunta> preguntas = preguntaRepository.findAllByProducto(contenido);
        ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(contenido.getProveedor_ID()));
        List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                .collect(Collectors.toList());
        List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
        for (Pregunta p : preguntas) {
            List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
            PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
            preguntasDTOs.add(preguntaDTO);
        }
        return new ContenidoDTO(contenido, proveedorDTO, infoAdicionalDTOs, preguntasDTOs);
    }

    public List<ContenidoDTO> getContenidosByProveedor(Long id) {
        List<ContenidoDTO> contenidosDTOs = new ArrayList<>();
        for (Contenido contenido : contenidoRepository.findByProveedor_ID(id.toString())) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(contenido);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(contenido);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(contenido.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            contenidosDTOs.add(new ContenidoDTO(contenido, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return contenidosDTOs;
    }

    public List<ServicioDTO> getServiciosByProveedor(Long id) {
        List<ServicioDTO> serviciosDTOs = new ArrayList<>();
        for (Servicio servicio : servicioRepository.findByProveedorID(id.toString())) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            serviciosDTOs.add(new ServicioDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return serviciosDTOs;
    }

    public List<ContenidoDTO> getAllContenidos() {
        List<ContenidoDTO> contenidosDTOs = new ArrayList<>();
        for (Contenido contenido : contenidoRepository.findAll()) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(contenido);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(contenido);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(contenido.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            contenidosDTOs.add(new ContenidoDTO(contenido, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return contenidosDTOs;
    }

    public List<ServicioDTO> getAllServicios() {
        List<ServicioDTO> serviciosDTOs = new ArrayList<>();
        for (Servicio servicio : servicioRepository.findAll()) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            serviciosDTOs.add(new ServicioDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return serviciosDTOs;
    }

    public List<ServicioDTO> getServiciosByNombre(String nombre) {
        List<ServicioDTO> serviciosDTOs = new ArrayList<>();
        for (Servicio servicio : servicioRepository.findByNombre(nombre)) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            serviciosDTOs.add(new ServicioDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return serviciosDTOs;
    }

    public List<ContenidoDTO> getContenidosByNombre(String nombre) {
        List<ContenidoDTO> contenidosDTOs = new ArrayList<>();
        for (Contenido contenido : contenidoRepository.findByNombre(nombre)) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(contenido);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(contenido);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(contenido.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            contenidosDTOs.add(new ContenidoDTO(contenido, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return contenidosDTOs;
    }

    public List<ServicioDTO> getServiciosByDescripcion(String descripcion) {
        List<ServicioDTO> serviciosDTOs = new ArrayList<>();
        for (Servicio servicio : servicioRepository.findByDescripcion(descripcion)) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(servicio);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(servicio);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(servicio.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            serviciosDTOs.add(new ServicioDTO(servicio, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return serviciosDTOs;
    }

    public List<ContenidoDTO> getContenidosByDescripcion(String descripcion) {
        List<ContenidoDTO> contenidosDTOs = new ArrayList<>();
        for (Contenido contenido : contenidoRepository.findByNombre(descripcion)) {
            List<InfoAdicional> infoadicional = infoAdicionalRepository.findAllByProducto(contenido);
            List<Pregunta> preguntas = preguntaRepository.findAllByProducto(contenido);
            ProveedorDTO proveedorDTO = usuariosService.getProveedorById(Long.parseLong(contenido.getProveedor_ID()));
            List<InfoAdicionalDTO> infoAdicionalDTOs = infoadicional.stream().map(InfoAdicionalDTO::new)
                    .collect(Collectors.toList());
            List<PreguntaDTO> preguntasDTOs = new ArrayList<>();
            for (Pregunta p : preguntas) {
                List<String> respuestas = respuestaRepository.findAllTextosByPregunta(p);
                PreguntaDTO preguntaDTO = new PreguntaDTO(p, null, null, null, respuestas);
                preguntasDTOs.add(preguntaDTO);
            }
            contenidosDTOs.add(new ContenidoDTO(contenido, proveedorDTO, infoAdicionalDTOs, preguntasDTOs));
        }
        return contenidosDTOs;
    }

    public JsonNode readJsonResource(String name) {
        String url = "https://restcountries.com/v3.1/name/" + name;
        String json = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(json).get(0);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public ServicioDTO createOrUpdateService(CrearServicio crear) {

        Servicio servicio = new Servicio();
        servicio.setNombre(crear.getNombre());
        TipoServicio tipo = tipoServicioRepository.findByTipo(crear.getTipo_servicio());

        if (tipo == null) {
            tipo = new TipoServicio();
            tipo.setTipo(crear.getTipo_servicio());
            tipoServicioRepository.save(tipo);
        }

        servicio.setTipoServicio(tipo);
        servicio.setDescripcion(crear.getDescripcion());
        servicio.setPrecio(crear.getPrecio());
        servicio.setProveedor_ID(crear.getProveedor_ID());
        servicioRepository.save(servicio);

        if (tipo.getTipo().equals("Alojamiento") || tipo.getTipo().equals("Transporte")) {
            String maps_api_key = System.getenv("MAPS_API_KEY");
            GeoApiContext context = new GeoApiContext.Builder().apiKey(maps_api_key).build();
            try {
                String ubicacion = crear.getInformacionAdicional().get("Ubicacion");
                ubicacion = (ubicacion.equals("")) ? "Universidad Javeriana" : ubicacion;
                GeocodingResult[] results = GeocodingApi
                        .geocode(context, ubicacion).await();

                InfoAdicional infoAdicional = new InfoAdicional();
                TipoInformacion tipoInformacion = tipoInformacionRepository.findById("Mapa").orElse(null);
                if (tipoInformacion == null) {
                    tipoInformacion = new TipoInformacion();
                    tipoInformacion.setTipo("Mapa");
                    tipoInformacionRepository.save(tipoInformacion);
                }
                infoAdicional.setTipoInformacion(tipoInformacion);
                infoAdicional.setValor("https://www.google.com/maps?q=" + results[0].geometry.location.lat + ","
                        + results[0].geometry.location.lng);
                infoAdicionalRepository.save(infoAdicional);
                MultimediaXInfo cross_reference = new MultimediaXInfo();
                cross_reference.setInfoAdicional(infoAdicional);
                cross_reference.setProducto(servicio);
                multimediaXInfoRepository.save(cross_reference);

                System.out.println(results[0].addressComponents[results[0].addressComponents.length - 2].longName);
                JsonNode obj = readJsonResource(
                        results[0].addressComponents[results[0].addressComponents.length - 2].longName);

                if (obj != null) {
                    // Obtener la información del país del nodo JSON
                    String nombrePais = obj.get("name").get("common").asText();
                    String codigoPais = obj.get("cca2").asText();
                    String capitalPais = obj.get("capital").get(0).asText();
                    int poblacionPais = obj.get("population").asInt();

                    infoAdicional = new InfoAdicional();
                    tipoInformacion = tipoInformacionRepository.findById("Nombre del pais").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Nombre del pais");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor(nombrePais);
                    infoAdicionalRepository.save(infoAdicional);
                    cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);

                    infoAdicional = new InfoAdicional();
                    tipoInformacion = tipoInformacionRepository.findById("Codigo del pais").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Codigo del pais");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor(codigoPais);
                    infoAdicionalRepository.save(infoAdicional);
                    cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);

                    infoAdicional = new InfoAdicional();
                    tipoInformacion = tipoInformacionRepository.findById("Poblacion del pais").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Poblacion del pais");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor(String.valueOf(poblacionPais));
                    infoAdicionalRepository.save(infoAdicional);
                    cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);

                    infoAdicional = new InfoAdicional();
                    tipoInformacion = tipoInformacionRepository.findById("Capital del pais").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Capital del pais");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor(capitalPais);
                    infoAdicionalRepository.save(infoAdicional);
                    cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            context.shutdown();

            if (tipo.getTipo().equals("Transporte")) {
                String fecha_salida = crear.getInformacionAdicional().get("Fecha Salida");
                if (fecha_salida.equals("")) {
                    InfoAdicional infoAdicional = new InfoAdicional();
                    TipoInformacion tipoInformacion = tipoInformacionRepository.findById("Fecha Salida").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Fecha Salida");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor(new Date(System.currentTimeMillis()).toString());
                    infoAdicionalRepository.save(infoAdicional);
                    MultimediaXInfo cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);
                }

                String fecha_llegada = crear.getInformacionAdicional().get("Fecha Llegada");
                if (fecha_llegada.equals("")) {
                    InfoAdicional infoAdicional = new InfoAdicional();
                    TipoInformacion tipoInformacion = tipoInformacionRepository.findById("Fecha Llegada").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Fecha Llegada");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24).toString());
                    infoAdicionalRepository.save(infoAdicional);
                    MultimediaXInfo cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);
                }

                String hora_salida = crear.getInformacionAdicional().get("Hora Salida");
                if (hora_salida.equals("")) {
                    InfoAdicional infoAdicional = new InfoAdicional();
                    TipoInformacion tipoInformacion = tipoInformacionRepository.findById("Hora Salida").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Hora Salida");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor("07:00");
                    infoAdicionalRepository.save(infoAdicional);
                    MultimediaXInfo cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);
                }

                String hora_llegada = crear.getInformacionAdicional().get("Hora Llegada");
                if (hora_llegada.equals("")) {
                    InfoAdicional infoAdicional = new InfoAdicional();
                    TipoInformacion tipoInformacion = tipoInformacionRepository.findById("Hora Llegada").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Hora Llegada");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor("20:00");
                    infoAdicionalRepository.save(infoAdicional);
                    MultimediaXInfo cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);
                }

                String trayecto = crear.getInformacionAdicional().get("Trayecto");
                if (trayecto.equals("")) {
                    InfoAdicional infoAdicional = new InfoAdicional();
                    TipoInformacion tipoInformacion = tipoInformacionRepository.findById("Trayecto").orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Trayecto");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor("No entiendo que es trayecto pero deberia estar aquí");
                    infoAdicionalRepository.save(infoAdicional);
                    MultimediaXInfo cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);
                }

                String tipo_transporte = crear.getInformacionAdicional().get("Tipo Transporte");
                if (tipo_transporte.equals("")) {
                    InfoAdicional infoAdicional = new InfoAdicional();
                    TipoInformacion tipoInformacion = tipoInformacionRepository.findById("Tipo Transporte")
                            .orElse(null);
                    if (tipoInformacion == null) {
                        tipoInformacion = new TipoInformacion();
                        tipoInformacion.setTipo("Tipo Transporte");
                        tipoInformacionRepository.save(tipoInformacion);
                    }
                    infoAdicional.setTipoInformacion(tipoInformacion);
                    infoAdicional.setValor("Transporte Terrestre");
                    infoAdicionalRepository.save(infoAdicional);
                    MultimediaXInfo cross_reference = new MultimediaXInfo();
                    cross_reference.setInfoAdicional(infoAdicional);
                    cross_reference.setProducto(servicio);
                    multimediaXInfoRepository.save(cross_reference);
                }
            }
        }

        for (Map.Entry<String, String> entry : crear.getInformacionAdicional().entrySet()) {
            InfoAdicional infoAdicional = new InfoAdicional();

            TipoInformacion tipoInformacion = tipoInformacionRepository.findById(entry.getKey()).orElse(null);

            if (tipoInformacion == null) {
                tipoInformacion = new TipoInformacion();
                tipoInformacion.setTipo(entry.getKey());
                tipoInformacionRepository.save(tipoInformacion);
            }

            infoAdicional.setTipoInformacion(tipoInformacion);
            infoAdicional.setValor(entry.getValue());
            infoAdicionalRepository.save(infoAdicional);
            MultimediaXInfo cross_reference = new MultimediaXInfo();
            cross_reference.setInfoAdicional(infoAdicional);
            cross_reference.setProducto(servicio);
            multimediaXInfoRepository.save(cross_reference);
        }

        return this.getServicioById(servicio.getId());
    }
}