package contenidos

import (
	"bytes"
	"encoding/json"
	"errors"
	"net/http"
	"os"
	"strconv"

	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/tipos"
)

type sCreateServicio struct {
	Nombre               string            `json:"nombre"`
	Descripcion          string            `json:"descripcion"`
	ProveedorID          string            `json:"proveedor_ID"`
	Precio               string            `json:"precio"`
	TipoServicio         string            `json:"tipo_servicio"`
	InformacionAdicional map[string]string `json:"informacionAdicional"`
}

func SearchAll() ([]Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		var retorno []Interfaces.ModeloDeDatos = nil
		client := http.Client{}
		req, err := http.NewRequest("GET", "http://"+os.Getenv("GATEWAY_IP")+"/api/servicios/clientes", nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente []Tipos.Servicio
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					retorno = make([]Interfaces.ModeloDeDatos, len(cliente))
					for i,client:=range cliente{
						retorno[i]=client
					}
					return retorno, nil
				}
			}
		}
		return retorno, errors.New("error fetching servicios")
	}
	return nil, errors.New("invalid token")
}

func SearchByTipo(tipo_contenido string) ([]Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		var retorno []Interfaces.ModeloDeDatos = nil
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/servicios/clientes?tipo=" + tipo_contenido
		req, err := http.NewRequest("GET", url, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente []Tipos.Servicio
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					retorno = make([]Interfaces.ModeloDeDatos, len(cliente))
					for i,client:=range cliente{
						retorno[i]=client
					}
					return retorno, nil
				}
			}
		}
		return retorno, errors.New("error fetching servicios")
	}
	return nil, errors.New("invalid token")
}

func SearchByID(id int) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/servicios/clientes/servicio?id=" + strconv.Itoa(id)
		req, err := http.NewRequest("GET", url, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente Tipos.Servicio
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					return cliente, nil
				}
			}
		}
		return Tipos.Servicio{}, errors.New("error fetching servicios")
	}
	return Tipos.Servicio{}, errors.New("invalid token")
}

func Create(Nombre string,
	Descripcion string,
	ProveedorID string,
	Precio string,
	TipoServicio string,
	InformacionAdicional map[string]string) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/servicios/proveedores/servicio"
		body := sCreateServicio{Nombre,
			Descripcion,
			ProveedorID,
			Precio,
			TipoServicio,
			InformacionAdicional}
		out, err := json.Marshal(body)
		if err == nil {
			req, err := http.NewRequest("POST", url, bytes.NewBuffer(out))
			if err == nil {
				req.Header = http.Header{
					"Authorization": {"Bearer " + Cuentas.Token},
					"Content-Type":  {"application/json"},
				}
				resp, err := client.Do(req)
				if err == nil && resp.StatusCode == 200 {
					defer resp.Body.Close()
					var cliente Tipos.Servicio
					err = json.NewDecoder(resp.Body).Decode(&cliente)
					if err == nil {
						return cliente, nil
					}
				}
			}
		}
		return Tipos.Servicio{}, errors.New("error creating servicio")
	}
	return Tipos.Servicio{}, errors.New("invalid token")
}
