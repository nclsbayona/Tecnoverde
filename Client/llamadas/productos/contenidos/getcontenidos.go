package contenidos

import (
	"encoding/json"
	"errors"
	"net/http"
	"os"
	"strconv"

	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/tipos"
)

func SearchAll() ([]Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		var retorno []Interfaces.ModeloDeDatos = nil
		req, err := http.NewRequest("GET", "http://"+os.Getenv("GATEWAY_IP")+"/api/contenidos/clientes", nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente []Tipos.Contenido
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
		return retorno, errors.New("error fetching productos")
	}
	return nil, errors.New("invalid token")
}

func SearchByTipo(tipo_contenido string) ([]Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		var retorno []Interfaces.ModeloDeDatos = nil
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/contenidos/clientes?tipo=" + tipo_contenido
		req, err := http.NewRequest("GET", url, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente []Tipos.Contenido
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
		return retorno, errors.New("error fetching productos")
	}
	return nil, errors.New("invalid token")
}

func SearchByID(id int) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/contenidos/clientes/contenido?id=" + strconv.Itoa(id)
		req, err := http.NewRequest("GET", url, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente Tipos.Contenido
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					return cliente, nil
				}
			}
		}
		return Tipos.Contenido{}, errors.New("error fetching contenidos")
	}
	return Tipos.Contenido{}, errors.New("invalid token")
}
