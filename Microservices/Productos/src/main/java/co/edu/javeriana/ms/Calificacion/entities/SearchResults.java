package co.edu.javeriana.ms.Calificacion.entities;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
public class SearchResults {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Servicio> servicios;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Contenido> contenidos;

    public SearchResults(ArrayList<Servicio> servicios, ArrayList<Contenido> contenidos) {
        this.servicios = servicios;
        this.contenidos = contenidos;
    }

    public SearchResults(ArrayList<Producto> servicios, boolean servicio) {
        if (servicio){
            this.servicios = new ArrayList<Servicio>();
            for (Producto m : servicios){
                this.servicios.add((Servicio) m);
            }
        }
        else{
            this.contenidos = new ArrayList<Contenido>();
            for (Producto m : servicios){
                this.contenidos.add((Contenido) m);
            }
        }
    }
}
