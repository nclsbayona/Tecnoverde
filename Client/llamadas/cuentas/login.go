package cuentas

import (
	"bytes"
	"encoding/json"
	"errors"
	"io"
	"net/http"
	"os"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/tipos"
)

type sLogin struct {
	Username string `json:"username"`
	Password string `json:"password"`
}

var Token string

func init(){
	Token=""
}

func Login(user string, password string) (Interfaces.ModeloDeDatos, error) {
	req := sLogin{user, password}
	jsonReq, err := json.Marshal(req)
	if err == nil {
		resp, err := http.Post("http://"+os.Getenv("GATEWAY_IP")+"/api/cuentas/login", "application/json", bytes.NewBuffer(jsonReq))
		if err == nil {
			defer resp.Body.Close()
			var token string
			buf := new(bytes.Buffer)
			_, err := io.Copy(buf, resp.Body)
			if err == nil {
				token = buf.String()
				Token=token
				return Tipos.Token{Token: token}, nil
			}
		}
	}
	return nil, errors.New("error loging in")
}