package tipos
type Pregunta struct {
	Texto      string   `json:"texto"`
	Respuestas []string `json:"respuestas"`
}

func (p Pregunta) ObtenerColumnas() []string {
	return []string{
		"Texto",
		"Respuestas",
	}
}

func (p Pregunta) ObtenerFilas() [][]string {
	return [][]string{
		{p.Texto, p.Respuestas[0]},
	}
}