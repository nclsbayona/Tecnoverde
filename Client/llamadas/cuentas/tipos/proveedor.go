package tipos;

type Proveedor struct {
	Nombre      string `json:"nombre"`
	Edad        string `json:"edad"`
	Descripcion string `json:"descripcion"`
	Foto        string `json:"foto"`
	Telefono    string `json:"telefono"`
	PaginaWeb   string `json:"pagina_web"`
	Facebook    string `json:"facebook"`
	Instagram   string `json:"instagram"`
	Twitter     string `json:"twitter"`
}

func (p Proveedor) ObtenerColumnas() []string {
	return []string{
		"Nombre",
		"Edad",
		"Descripcion",
		"Foto",
		"Telefono",
		"PaginaWeb",
		"Facebook",
		"Instagram",
		"Twitter",
	}
}

func (p Proveedor) ObtenerFilas() [][]string {
	return [][]string{
		{p.Nombre, p.Edad, p.Descripcion, p.Foto, p.Telefono, p.PaginaWeb, p.Facebook, p.Instagram, p.Twitter},
	}
}