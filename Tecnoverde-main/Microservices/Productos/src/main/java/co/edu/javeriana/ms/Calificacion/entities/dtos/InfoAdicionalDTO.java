package co.edu.javeriana.ms.Calificacion.entities.dtos;

import co.edu.javeriana.ms.Calificacion.entities.InfoAdicional;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InfoAdicionalDTO {
    private String tipoInformacion;
    private String valor;

    public InfoAdicionalDTO(InfoAdicional info){
        this.tipoInformacion = info.getTipoInformacion().getTipo();
        this.valor = info.getValor();
    }
}
