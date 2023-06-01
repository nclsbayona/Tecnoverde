package calificaciones

import (
	"bytes"
	"encoding/json"
	"errors"
	"net/http"
	"os"
	"strconv"

	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/calificaciones/tipos"
	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

type sCreateCalificacion struct {
	Cliente      int     `json:"cliente"`
	Servicio     int     `json:"servicio"`
	Calificacion float64 `json:"calificacion"`
	Comentario   string  `json:"comentario"`
}

func Create(cliente_id string, servicio_id string, calificacion string, comentario string) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/calificaciones"
		cliente, _ := strconv.Atoi(cliente_id)
		servicio, _ := strconv.Atoi(servicio_id)
		calificacion_float, _ := strconv.ParseFloat(calificacion, 64)
		body := sCreateCalificacion{Cliente: cliente, Servicio: servicio, Comentario: comentario, Calificacion: calificacion_float}
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
					var cliente Tipos.Calificacion
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

func SearchAll() ([]Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		req, err := http.NewRequest("GET", "http://"+os.Getenv("GATEWAY_IP")+"/api/calificaciones", nil)
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente []Tipos.Calificacion
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