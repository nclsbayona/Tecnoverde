package tipos
type InfoAdicional struct {
	TipoInformacion string `json:"tipoInformacion"`
	Valor           string `json:"valor"`
}

func (i InfoAdicional) ObtenerColumnas() []string {
	return []string{
		"TipoInformacion",
		"Valor",
	}
}

func (i InfoAdicional) ObtenerFilas() [][]string {
	return [][]string{
		{i.TipoInformacion, i.Valor},
	}
}
