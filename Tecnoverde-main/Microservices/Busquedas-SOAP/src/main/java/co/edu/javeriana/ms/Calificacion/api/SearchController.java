package co.edu.javeriana.ms.Calificacion.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import co.edu.javeriana.ms.Calificacion.entities.dtos.ServicioDTO;
import co.edu.javeriana.ms.Calificacion.services.ProductosService;
import co.edu.javeriana.ms.calificacion.entities.BuscarProductosRequest;
import co.edu.javeriana.ms.calificacion.entities.BuscarProductosResponse;
import co.edu.javeriana.ms.calificacion.entities.InfoAdicional;
import co.edu.javeriana.ms.calificacion.entities.Proveedor;
import co.edu.javeriana.ms.calificacion.entities.SearchResultsDTO;
import co.edu.javeriana.ms.calificacion.entities.Servicio;

@Endpoint
public class SearchController {

    @Autowired
    private ProductosService productosService;

    @PayloadRoot(namespace = "http://ms.javeriana.edu.co/Calificacion/entities", localPart = "buscarProductosRequest")
    @ResponsePayload
    public BuscarProductosResponse search(@RequestPayload BuscarProductosRequest request) {
        BuscarProductosResponse response = new BuscarProductosResponse();
        SearchResultsDTO searchResultsDTO = new SearchResultsDTO();
        SearchResultsDTO.Servicios servicios = new SearchResultsDTO.Servicios();
        ServicioDTO s=productosService.getServicioById(request.getServicioId());
        Servicio servicio=new Servicio();
        servicio.setDescripcion(s.getDescripcion());
        servicio.setTipo(s.getTipo());
        Proveedor proveedor= new Proveedor();
        proveedor.setNombre(s.getProveedor().getNombre());
        proveedor.setTelefono(s.getProveedor().getTelefono());
        proveedor.setDescripcion(s.getProveedor().getDescripcion());
        proveedor.setTwitter(s.getProveedor().getTwitter());
        proveedor.setInstagram(s.getProveedor().getInstagram());
        proveedor.setFacebook(s.getProveedor().getFacebook());
        proveedor.setFoto(s.getProveedor().getFoto());
        proveedor.setPaginaWeb(s.getProveedor().getPagina_web());
        proveedor.setEdad(Integer.valueOf(s.getProveedor().getEdad()));
        servicio.setProveedor(proveedor);
        InfoAdicional infoAdicional=new InfoAdicional();
        servicio.setInfoAdicional(infoAdicional);
        servicios.setServicio(servicio);
        searchResultsDTO.setServicios(servicios);
        response.setSearchResultsDTO(searchResultsDTO);
        return response;
    }
}
