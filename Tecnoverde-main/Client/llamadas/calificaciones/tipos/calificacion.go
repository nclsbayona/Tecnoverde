package tipos

import (
	
	TiposCuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/tipos"
	TiposProductos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/tipos"
)

type Calificacion struct {
	Puntaje    string                  `json:"puntaje"`
	Comentario string                  `json:"comentario"`
	Cliente    TiposCuentas.Cliente    `json:"cliente"`
	Servicio   TiposProductos.Servicio `json:"servicio"`
}

func (c Calificacion) ObtenerColumnas() []string {
	return []string{
		"Puntaje",
		"Comentario",
		"Cliente",
		"Nombre del servicio",
	}
}

func (c Calificacion) ObtenerFilas() [][]string {
	return [][]string{
		{c.Puntaje, c.Comentario, c.Cliente.Nombre, c.Servicio.Nombre},
	}
}