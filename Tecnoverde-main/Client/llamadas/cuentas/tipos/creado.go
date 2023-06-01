package tipos;

type Creado struct {
	Ok    string `json:"ok"`
}

func (c Creado) ObtenerColumnas() []string {
	return []string{
		"Ok",
	}
}

func (c Creado) ObtenerFilas() [][]string {
	return [][]string{
		{c.Ok},
	}
}

func NewCreado(c string) Creado {
	return Creado{c}
}