package creacion;

import (
	"bytes"
	"encoding/json"
	"errors"
	"io"
	"net/http"
	"os"
	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/tipos"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

type sCrearCliente struct {
	Username    string `json:"username"`
	Password    string `json:"password"`
	Nombre      string `json:"nombre"`
	Edad        string `json:"edad"`
	Descripcion string `json:"descripcion"`
	Foto        struct {
		URL string `json:"url"`
	} `json:"foto"`
}

func CrearCliente(user string, password string, nombre string, edad string, descripcion string, foto string) (Interfaces.ModeloDeDatos, error) {
	req := sCrearCliente{user, password, nombre, edad, descripcion, struct{URL string "json:\"url\""}{foto}}
	jsonReq, err := json.Marshal(req)
	if err == nil {
		resp, err := http.Post("http://"+os.Getenv("GATEWAY_IP")+"/api/cuentas/cliente", "application/json", bytes.NewBuffer(jsonReq))
		if err == nil {
			defer resp.Body.Close()
			var token string
			buf := new(bytes.Buffer)
			_, err := io.Copy(buf, resp.Body)
			if err == nil {
				token = buf.String()
				return Tipos.NewCreado(token), nil
			}
		}
	}
	return nil, errors.New("error creating cliente")
}
