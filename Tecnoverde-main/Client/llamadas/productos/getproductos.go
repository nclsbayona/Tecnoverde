package productos

import (
	"encoding/json"
	"errors"
	"net/http"
	"os"

	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/tipos"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

type sSearchResults struct {
	Servicios  []Tipos.Servicio  `json:"servicios"`
	Contenidos []Tipos.Contenido `json:"contenidos"`
}

func (c sSearchResults) ObtenerColumnas() []string {
	return []string{
		"Servicio/Contenido",
		"Proveedor",
	}
}

func (c sSearchResults) ObtenerFilas() [][]string {
	ret := [][]string{}
	for _, servicio := range c.Servicios {
		ret = append(ret, []string{servicio.Nombre, servicio.Proveedor.Nombre})
	}
	for _, contenido := range c.Contenidos {
		ret = append(ret, []string{contenido.Nombre, contenido.Proveedor.Nombre})
	}
	return ret
}


func SearchAll() (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		req, err := http.NewRequest("GET", "http://"+os.Getenv("GATEWAY_IP")+"/api/productos/clientes", nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente sSearchResults
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					return cliente, nil
				}
			}
		}
		return sSearchResults{}, errors.New("error fetching productos")
	}
	return sSearchResults{}, errors.New("invalid token")
}

func SearchByTipos(tipo_contenido string, tipo_servicio string) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/productos/clientes"
		if tipo_contenido != "" {
			url += "?tipo_contenido=" + tipo_contenido
			if tipo_servicio != "" {
				url += "&tipo_servicio=" + tipo_servicio
			}
		} else if tipo_servicio != "" {
			url += "tipo_servicio=" + tipo_servicio
		}
		req, err := http.NewRequest("GET", url, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente sSearchResults
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					return cliente, nil
				}
			}
		}
		return sSearchResults{}, errors.New("error fetching productos")
	}
	return sSearchResults{}, errors.New("invalid token")
}

func SearchByNombre(name string) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/productos/clientes?nombre=" + name
		req, err := http.NewRequest("GET", url, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente sSearchResults
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					return cliente, nil
				}
			}
		}
		return sSearchResults{}, errors.New("error fetching productos")
	}
	return sSearchResults{}, errors.New("invalid token")
}

func SearchByDescripcion(desc string) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/productos/clientes?descripcion=" + desc
		req, err := http.NewRequest("GET", url, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente sSearchResults
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					return cliente, nil
				}
			}
		}
		return sSearchResults{}, errors.New("error fetching productos")
	}
	return sSearchResults{}, errors.New("invalid token")
}
