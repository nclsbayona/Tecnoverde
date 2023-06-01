package tipos;

type Cliente struct {
	Username    string `json:"username"`
	Nombre      string `json:"nombre"`
	Edad        string `json:"edad"`
	Descripcion string `json:"descripcion"`
	Foto        string `json:"foto"`
}

func (c Cliente) ObtenerColumnas() []string {
	return []string{
		"Username",
		"Nombre",
		"Edad",
		"Descripcion",
		"Foto",
	}
}

func (c Cliente) ObtenerFilas() [][]string {
	return [][]string{
		{c.Username, c.Nombre, c.Edad, c.Descripcion, c.Foto},
	}
}