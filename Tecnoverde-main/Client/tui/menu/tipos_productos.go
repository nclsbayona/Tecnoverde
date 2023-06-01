package menu

import (

	//
	tea "github.com/charmbracelet/bubbletea"
	//
	Productos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos"
	Contenidos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/contenidos"
	Servicios "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/servicios"
	Busqueda "github.com/nclsbayona/Tecnoverde/Client/llamadas/busquedasoap"
)

type VerServicios struct {
	datos PedirDatosVerServicios
}

func (v VerServicios) Init() tea.Cmd {
	return v.datos.Init()
}

func (v VerServicios) View() string {
	return v.datos.View()
}

func (v VerServicios) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewVerServicios() VerServicios {
	return VerServicios{
		datos: NewPedirDatosVerServicios(
			[]string{"id", "tipo"}, "Ver Servicios", Servicios.SearchAll, Servicios.SearchByTipo, Servicios.SearchByID,
		),
	}
}

type CrearServicio struct {
	datos PedirDatosCrearServicio
}

func (v CrearServicio) Init() tea.Cmd {
	return v.datos.Init()
}

func (v CrearServicio) View() string {
	return v.datos.View()
}

func (v CrearServicio) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewCrearServicio() CrearServicio {
	return CrearServicio{
		datos: NewPedirDatosCrearServicio(
			[]string{"Nombre", "Descripcion", "ProveedorID", "Precio", "TipoServicio", "Ubicacion"}, "Ver Servicios", Servicios.Create,
		),
	}
}

type VerContenidos struct {
	datos PedirDatosVerContenidos
}

func (v VerContenidos) Init() tea.Cmd {
	return v.datos.Init()
}

func (v VerContenidos) View() string {
	return v.datos.View()
}

func (v VerContenidos) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewVerContenidos() VerContenidos {
	return VerContenidos{
		datos: NewPedirDatosVerContenidos(
			[]string{"id", "tipo"}, "Ver Contenidos", Contenidos.SearchAll, Contenidos.SearchByTipo, Contenidos.SearchByID,
		),
	}
}

type VerProductos struct {
	datos PedirDatosVerProductos
}

func (v VerProductos) Init() tea.Cmd {
	return v.datos.Init()
}

func (v VerProductos) View() string {
	return v.datos.View()
}

func (v VerProductos) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewVerProductos() VerProductos {
	return VerProductos{
		datos: NewPedirDatosVerProductos(
			[]string{"tipo_contenido", "tipo_servicio", "nombre", "descripcion"}, "Ver Productos", Productos.SearchAll, Productos.SearchByTipos, Productos.SearchByNombre, Productos.SearchByDescripcion,
		),
	}
}


type BuscarServicio struct {
	datos PedirDatosBuscarServicio
}

func (v BuscarServicio) Init() tea.Cmd {
	return v.datos.Init()
}

func (v BuscarServicio) View() string {
	return v.datos.View()
}

func (v BuscarServicio) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewBuscarServicio() BuscarServicio {
	return BuscarServicio{
		datos: NewPedirDatosBuscarServicio(
			[]string{"id"}, "Buscar Servicio (SOAP)", Busqueda.SearchByID,
		),
	}
}