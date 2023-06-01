package busquedasoap

import (
	"bytes"
	"errors"
	"io"
	"net/http"
	"os"
	"strconv"
	"strings"
	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

type Response struct {
	Response string `json:"response"`
}

func (c Response) ObtenerColumnas() []string {
	return []string{
		"Response",
	}
}

func (c Response) ObtenerFilas() [][]string {
	return [][]string{
		{c.Response},
	}
}

func NewCreado(c string) Response {
	return Response{c}
}

func SearchByID(id int) (Interfaces.ModeloDeDatos, error) {
	if Cuentas.ValidateToken() {
		client := http.Client{}
		url := "http://" + os.Getenv("GATEWAY_IP") + "/api/busqueda-soap"
		xmlbody := `
		<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://ms.javeriana.edu.co/Calificacion/entities">
			<soapenv:Header/>
			<soapenv:Body>
				<gs:buscarProductosRequest>
					<gs:servicio_id>` + strconv.Itoa(id) + `</gs:servicio_id>
				</gs:buscarProductosRequest>
			</soapenv:Body>
		</soapenv:Envelope>`
		req, err := http.NewRequest("POST", url, strings.NewReader(xmlbody))
		if err == nil {
			req.Header = http.Header{
				"Authorization": {"Bearer " + Cuentas.Token},
				"Content-Type":  {"text/xml"},
			}
			resp, err := client.Do(req)
			if err == nil {
				defer resp.Body.Close()
				var cliente string
				buf := new(bytes.Buffer)
				_, err := io.Copy(buf, resp.Body)
				if err == nil {
					cliente = buf.String()
					return NewCreado(cliente), nil
				}
			}
		}
		return nil, errors.New("error fetching servicio")
	}
	return nil, errors.New("invalid token")
}
