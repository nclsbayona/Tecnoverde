package tipos

import (
	TiposCuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/tipos"
	TiposProductos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/tipos"
	"time"
)

type Compra struct {
	Pagado   bool                    `json:"pagado"`
	Fecha    time.Time               `json:"fecha"`
	Cliente  TiposCuentas.Cliente    `json:"cliente"`
	Servicio TiposProductos.Servicio `json:"servicio"`
}

func (c Compra) ObtenerColumnas() []string {
	return []string{
		"Pagado",
		"Fecha",
		"Cliente",
		"Servicio",
	}
}

func (c Compra) ObtenerFilas() [][]string {
	payed := "No"
	if c.Pagado {
		payed = "Si"
	}
	return [][]string{
		{payed, c.Fecha.String(), c.Cliente.Nombre, c.Servicio.Nombre},
	}
}
