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

func GetProveedores() ([]Interfaces.ModeloDeDatos, error) {
	resp, err := http.Get("http://" + os.Getenv("GATEWAY_IP") + "/api/cuentas/proveedor")
	var retorno []Interfaces.ModeloDeDatos = nil
	if err == nil {
		defer resp.Body.Close()
		var clientes []Tipos.Proveedor
		err = json.NewDecoder(resp.Body).Decode(&clientes)
		if err == nil {
			retorno=make([]Interfaces.ModeloDeDatos,len(clientes))
			for i,cliente:=range clientes{
				retorno[i]=cliente
			}
			return retorno, nil
		}
	}
	return retorno, errors.New("error reading proveedores")
}

func GetProveedor(id int) (Interfaces.ModeloDeDatos, error) {
	resp, err := http.Get("http://" + os.Getenv("GATEWAY_IP") + "/api/cuentas/proveedor?id=" + strconv.Itoa(id))
	if err == nil {
		defer resp.Body.Close()
		var cliente []Tipos.Proveedor
		err = json.NewDecoder(resp.Body).Decode(&cliente)
		if err == nil {
			return cliente[0], nil
		}
	}
	return nil, errors.New("error reading proveedor")
}
