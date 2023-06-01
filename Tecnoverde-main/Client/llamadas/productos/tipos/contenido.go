package tipos
import Tipos "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/tipos"
type Contenido struct {
	Nombre      string `json:"nombre"`
	Descripcion string `json:"descripcion"`
	Tipo        string `json:"tipo"`
	Proveedor   Tipos.Proveedor `json:"proveedor"`
	InfoAdicional []InfoAdicional `json:"infoAdicional"`
	Preguntas     []Pregunta `json:"preguntas"`
}

func (c Contenido) ObtenerColumnas() []string {
	return []string{
		"Nombre",
		"Descripcion",
		"Tipo",
		"Proveedor",
	}
}

func (c Contenido) ObtenerFilas() [][]string {
	return [][]string{
		{c.Nombre, c.Descripcion, c.Tipo, c.Proveedor.Nombre},
	}
}
