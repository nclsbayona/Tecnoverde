package menu

import (

	//
	tea "github.com/charmbracelet/bubbletea"
	//
	Calificaciones "github.com/nclsbayona/Tecnoverde/Client/llamadas/calificaciones"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

type VerCalificaciones struct {
	datos           []Interfaces.ModeloDeDatos
	funcion_general func() ([]Interfaces.ModeloDeDatos, error)
}

func (i VerCalificaciones) Init() tea.Cmd {
	return nil
}

func (i VerCalificaciones) View() string {
	return ""
}

func (i VerCalificaciones) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	res, _ := i.funcion_general()
	return NewTableMultipleData(res).Update(msg)
}

func NewVerCalificaciones() VerCalificaciones {
	return VerCalificaciones{
		nil, Calificaciones.SearchAll,
	}
}


type CrearCalificacion struct {
	datos PedirDatosCrearCalificacion
}

func (v CrearCalificacion) Init() tea.Cmd {
	return v.datos.Init()
}

func (v CrearCalificacion) View() string {
	return v.datos.View()
}

func (v CrearCalificacion) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewCrearCalificacion() CrearCalificacion {
	return CrearCalificacion{
		datos: NewPedirDatosCrearCalificacion(
			[]string{"ID del cliente", "ID del servicio", "puntaje", "comentario"}, "Crear calificacion", Calificaciones.Create, 
		),
	}
}

