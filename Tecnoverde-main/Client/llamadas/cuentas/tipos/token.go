package tipos

type Token struct {
	Token string `json:"token"`
}

func (t Token) ObtenerColumnas() []string {
	return []string{
		"Token",
	}
}

func (t Token) ObtenerFilas() [][]string {
	return [][]string{
		{t.Token},
	}
}