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

type sCrearProveedor struct {
	Username    string `json:"username"`
	Password    string `json:"password"`
	Nombre      string `json:"nombre"`
	Edad        string `json:"edad"`
	Descripcion string `json:"descripcion"`
	Telefono    string `json:"telefono"`
	PaginaWeb   string `json:"pagina_web"`
	Foto        struct {
		URL string `json:"url"`
	} `json:"foto"`
	RedesSociales struct {
		Facebook  string `json:"facebook"`
		Instagram string `json:"instagram"`
		Twitter   string `json:"twitter"`
	} `json:"redesSociales"`
}

func CrearProveedor(user string, password string, nombre string, edad string, descripcion string, telefono string, pagina_web string, foto string, facebook string, instagram string, twitter string) (Interfaces.ModeloDeDatos, error) {
	req := sCrearProveedor{user, password, nombre, edad, descripcion, telefono, pagina_web, struct{URL string "json:\"url\""}{foto}, struct{Facebook string "json:\"facebook\""; Instagram string "json:\"instagram\""; Twitter string "json:\"twitter\""}{facebook, instagram, twitter}}
	jsonReq, err := json.Marshal(req)
	if err == nil {
		resp, err := http.Post("http://"+os.Getenv("GATEWAY_IP")+"/api/cuentas/proveedor", "application/json", bytes.NewBuffer(jsonReq))
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
	return nil, errors.New("error creating proveedor")
}
