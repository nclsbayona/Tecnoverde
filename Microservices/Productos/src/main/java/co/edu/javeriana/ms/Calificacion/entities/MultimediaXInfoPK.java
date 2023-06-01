package co.edu.javeriana.ms.Calificacion.entities;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultimediaXInfoPK implements Serializable {

    private Long producto;
    private Long infoAdicional;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Objects.equals(getClass(), obj.getClass())) {
            return false;
        }
        final MultimediaXInfoPK other = (MultimediaXInfoPK) obj;
        if (!java.util.Objects.equals(this.getProducto(), other.getProducto())) {
            return false;
        }
        if (!java.util.Objects.equals(this.getInfoAdicional(), other.getInfoAdicional())) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.getProducto());
        hash = 31 * hash + Objects.hashCode(this.getInfoAdicional());
        return hash;
    }

    @Override
    public String toString() {
        return "MultimediaXInfoPK{" + " producto=" + producto + ", infoAdicional=" + infoAdicional + '}';
    }

}