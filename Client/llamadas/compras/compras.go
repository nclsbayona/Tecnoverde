package compras

import (
	"bytes"
	"encoding/json"
	"errors"
	"net/http"
	"os"
	"strconv"

	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/compras/tipos"
	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

type sCreateCompra struct {
	ClienteID  int `json:"cliente"`
	ServicioID int `json:"servicio"`
}

func SearchAll() ([]Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		req, err := http.NewRequest("GET", "http://"+os.Getenv("GATEWAY_IP")+"/api/compras", nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente []Tipos.Compra
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					retorno := make([]Interfaces.ModeloDeDatos, len(cliente))
					for i, client := range cliente {
						retorno[i] = client
					}
					return retorno, nil
				}
			}
		}
		return nil, errors.New("error fetching productos")
	}
	return nil, errors.New("invalid token")
}

func PagarCompra(id string, card string) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		req, err := http.NewRequest("PUT", "http://"+os.Getenv("GATEWAY_IP")+"/api/compras/compra?id="+id+"&card="+card, nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente Tipos.Compra
				err = json.NewDecoder(resp.Body).Decode(&cliente)
				if err == nil {
					return cliente, nil
				}
			}
		}
		return nil, errors.New("error fetching productos")
	}
	return nil, errors.New("invalid token")
}

func Create(cliente_id string, servicio_id string) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/compras/compra"
		cliente, _ := strconv.Atoi(cliente_id)
		servicio, _ := strconv.Atoi(servicio_id)
		body := sCreateCompra{cliente, servicio}
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
					var cliente Tipos.Compra
					err = json.NewDecoder(resp.Body).Decode(&cliente)
					if err == nil {
						return cliente, nil
					}
				}
			}
		}
		return nil, errors.New("error creating servicio")
	}
	return nil, errors.New("invalid token")
}
