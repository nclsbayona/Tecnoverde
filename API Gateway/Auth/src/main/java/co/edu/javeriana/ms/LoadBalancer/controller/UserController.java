package co.edu.javeriana.ms.LoadBalancer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import co.edu.javeriana.ms.LoadBalancer.entities.Cliente;
import co.edu.javeriana.ms.LoadBalancer.entities.Proveedor;
import co.edu.javeriana.ms.LoadBalancer.entities.aux.IniciarSesion;
import co.edu.javeriana.ms.LoadBalancer.entities.dtos.ClienteDTO;
import co.edu.javeriana.ms.LoadBalancer.entities.dtos.ProveedorDTO;
import co.edu.javeriana.ms.LoadBalancer.services.JWTService;
import co.edu.javeriana.ms.LoadBalancer.services.UserService;

@RestController
@RequestMapping("/api/cuentas")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/cliente")
    public List<ClienteDTO> getClientes(@RequestParam(required = false) Long id) {
        List<ClienteDTO> arr = null;
        if (id != null)
            arr = this.service.getClienteByID(id);
        else
            arr = this.service.getAllClientes();
        return arr;
    }

    @GetMapping("/proveedor")
    public List<ProveedorDTO> getProveedores(@RequestParam(required = false) Long id) {
        List<ProveedorDTO> arr = null;
        if (id != null)
            arr = this.service.getProveedorByID(id);
        else
            arr = this.service.getAllProveedores();
        return arr;
    }

    @PostMapping("/cliente")
    public String welcomeCliente(@RequestBody Cliente c) {
        return service.createCliente(c);
    }

    @PostMapping("/proveedor")
    public String welcomeProveedor(@RequestBody Proveedor p) {
        return service.createProveedor(p);
    }

    @PostMapping("/login")
    public String login(@RequestBody IniciarSesion logIn) {
        Authentication auth = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(logIn.getUsername(), logIn.getPassword()));
        return (auth.isAuthenticated()) ? jwtService.generateToken(logIn.getUsername(), auth.getAuthorities()) : "Invalid credentials";
    }

    /*
     * @Autowired
     * private ContenidoService contenidoService;
     * 
     * @Autowired
     * private ServicioService servicioService;
     * 
     * 
     * @GetMapping("")
     * 
     * @PreAuthorize("hasRole('ROLE_CLIENTE')")
     * public SearchResultsDTO search(@RequestParam(required = false) String
     * tipo_servicio,
     * 
     * @RequestParam(required = false) String tipo_contenido, @RequestParam(required
     * = false) String nombre,
     * 
     * @RequestParam(required = false) String descripcion) {
     * SearchResultsDTO dto = null;
     * if (tipo_servicio != null) {
     * ArrayList<ServicioDTO> arrS = new ArrayList<ServicioDTO>();
     * ArrayList<ContenidoDTO> arrC = new ArrayList<ContenidoDTO>();
     * for (Servicio p : this.servicioService.getAllByTipo(tipo_servicio)) {
     * arrS.add(servicioService.getDTO(p, true, true));
     * }
     * if (tipo_contenido != null) {
     * for (Contenido p : this.contenidoService.getAllByTipo(tipo_contenido))
     * arrC.add(contenidoService.getDTO(p, true));
     * }else{
     * for (Contenido p : this.contenidoService.getAll()) {
     * arrC.add(contenidoService.getDTO(p, true));
     * }
     * }
     * dto = new SearchResultsDTO(arrS, arrC);
     * } else if (tipo_contenido != null) {
     * ArrayList<ServicioDTO> arrS = new ArrayList<ServicioDTO>();
     * ArrayList<ContenidoDTO> arrC = new ArrayList<ContenidoDTO>();
     * for (Servicio p : this.servicioService.getAll()) {
     * arrS.add(servicioService.getDTO(p, true, true));
     * }
     * for (Contenido p : this.contenidoService.getAllByTipo(tipo_contenido)) {
     * arrC.add(contenidoService.getDTO(p, true));
     * }
     * dto = new SearchResultsDTO(arrS, arrC);
     * } else if (nombre != null) {
     * ArrayList<ServicioDTO> arrS = new ArrayList<ServicioDTO>();
     * for (Servicio p : this.servicioService.getAllByNombre(nombre)) {
     * arrS.add(servicioService.getDTO(p, true, true));
     * }
     * ArrayList<ContenidoDTO> arrC = new ArrayList<ContenidoDTO>();
     * for (Contenido p : this.contenidoService.getAllByNombre(nombre)) {
     * arrC.add(contenidoService.getDTO(p, true));
     * }
     * dto = new SearchResultsDTO(arrS, arrC);
     * } else if (descripcion != null) {
     * ArrayList<ServicioDTO> arrS = new ArrayList<ServicioDTO>();
     * for (Servicio p : this.servicioService.getAllByDescripcion(descripcion)) {
     * arrS.add(servicioService.getDTO(p, true, true));
     * }
     * ArrayList<ContenidoDTO> arrC = new ArrayList<ContenidoDTO>();
     * for (Contenido p : this.contenidoService.getAllByDescripcion(descripcion)) {
     * arrC.add(contenidoService.getDTO(p, true));
     * }
     * dto = new SearchResultsDTO(arrS, arrC);
     * } else {
     * ArrayList<ServicioDTO> arrS = new ArrayList<ServicioDTO>();
     * for (Servicio p : this.servicioService.getAll()) {
     * arrS.add(servicioService.getDTO(p, true, true));
     * }
     * ArrayList<ContenidoDTO> arrC = new ArrayList<ContenidoDTO>();
     * for (Contenido p : this.contenidoService.getAll()) {
     * arrC.add(contenidoService.getDTO(p, true));
     * }
     * dto = new SearchResultsDTO(arrS, arrC);
     * }
     * return dto;
     * }
     */
}
