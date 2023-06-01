package interfaces
type ModeloDeDatos interface {
	ObtenerColumnas() []string
	ObtenerFilas() [][]string
}