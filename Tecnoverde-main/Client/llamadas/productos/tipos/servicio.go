package tipos
import Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/tipos"
type Servicio struct {
	Nombre      string `json:"nombre"`
	Descripcion string `json:"descripcion"`
	Tipo        string `json:"tipo"`
	Precio string `json:"precio"`
	Proveedor   Tipos.Proveedor `json:"proveedor"`
	InfoAdicional []InfoAdicional `json:"infoAdicional"`
	Preguntas     []Pregunta `json:"preguntas"`
}

func (s Servicio) ObtenerColumnas() []string {
	return []string{
		"Nombre",
		"Descripcion",
		"Tipo",
		"Precio",
		"Proveedor",
	}
}

func (s Servicio) ObtenerFilas() [][]string {
	return [][]string{
		{s.Nombre, s.Descripcion, s.Tipo, s.Precio, s.Proveedor.Nombre},
	}
}