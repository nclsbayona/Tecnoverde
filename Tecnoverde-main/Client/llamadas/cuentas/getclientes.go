package cuentas

import (
	"encoding/json"
	"errors"
	"net/http"
	"os"
	"strconv"

	Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/tipos"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

func GetClientes() ([]Interfaces.ModeloDeDatos, error) {
	resp, err := http.Get("http://" + os.Getenv("GATEWAY_IP") + "/api/cuentas/cliente")
	var retorno []Interfaces.ModeloDeDatos = nil
	if err == nil {
		defer resp.Body.Close()
		var clientes []Tipos.Cliente
		err = json.NewDecoder(resp.Body).Decode(&clientes)
		if err == nil {
			retorno=make([]Interfaces.ModeloDeDatos,len(clientes))
			for i,cliente:=range clientes{
				retorno[i]=cliente
			}
			return retorno, nil
		}
	}
	return retorno, errors.New("error reading clientes")
}

func GetCliente(id int) (Interfaces.ModeloDeDatos,error) {
	resp, err := http.Get("http://" + os.Getenv("GATEWAY_IP") + "/api/cuentas/cliente?id=" + strconv.Itoa(id))
	if err == nil {
		defer resp.Body.Close()
		var cliente []Tipos.Cliente
		err = json.NewDecoder(resp.Body).Decode(&cliente)
		if err == nil {
			return cliente[0], nil
		}
	}
	return nil, errors.New("error reading cliente")
}
