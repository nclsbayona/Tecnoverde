package menu

import (

	//
	tea "github.com/charmbracelet/bubbletea"
	//
	Compras "github.com/nclsbayona/Tecnoverde/Client/llamadas/compras"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
)

type VerCompras struct {
	datos           []Interfaces.ModeloDeDatos
	funcion_general func() ([]Interfaces.ModeloDeDatos, error)
}

func (i VerCompras) Init() tea.Cmd {
	return nil
}

func (i VerCompras) View() string {
	return ""
}

func (i VerCompras) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	res, _ := i.funcion_general()
	return NewTableMultipleData(res).Update(msg)
}

func NewVerCompras() VerCompras {
	return VerCompras{
		nil, Compras.SearchAll,
	}
}


type PagarCompra struct {
	datos PedirDatosPagarCompra
}

func (v PagarCompra) Init() tea.Cmd {
	return v.datos.Init()
}

func (v PagarCompra) View() string {
	return v.datos.View()
}

func (v PagarCompra) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewPagarCompra() PagarCompra {
	return PagarCompra{
		datos: NewPedirDatosPagarCompra(
			[]string{"ID", "card_number"}, "Pagar compra", Compras.PagarCompra, 
		),
	}
}


type CrearCompra struct {
	datos PedirDatosCrearCompra
}

func (v CrearCompra) Init() tea.Cmd {
	return v.datos.Init()
}

func (v CrearCompra) View() string {
	return v.datos.View()
}

func (v CrearCompra) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewCrearUnaNuevaCompra() CrearCompra {
	return CrearCompra{
		datos: NewPedirDatosCrearCompra(
			[]string{"ID del cliente", "ID del servicio"}, "Crear compra", Compras.Create, 
		),
	}
}

