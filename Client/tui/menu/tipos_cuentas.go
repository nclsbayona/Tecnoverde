package menu

import (

	//
	tea "github.com/charmbracelet/bubbletea"
	//
	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	Creacion "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/creacion"
)

type IniciarSesion struct {
	datos PedirDatosLogin
}

func (i IniciarSesion) Init() tea.Cmd {
	return i.datos.Init()
}

func (i IniciarSesion) View() string {
	return i.datos.View()
}

func (i IniciarSesion) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return i.datos.Update(msg)
}

func NewIniciarSesion() IniciarSesion {
	return IniciarSesion{
		datos: NewPedirDatosLogin(
			[]string{"Usuario", "Contraseña"}, "🔐 Iniciar Sesion 🔐", Cuentas.Login,
		),
	}
}

type VerClientes struct {
	datos PedirDatosVerClientes
}

func (v VerClientes) Init() tea.Cmd {
	return v.datos.Init()
}

func (v VerClientes) View() string {
	return v.datos.View()
}

func (v VerClientes) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewVerClientes() VerClientes {
	return VerClientes{
		datos: NewPedirDatosVerClientes(
			[]string{"ID"}, "👀 Ver Clientes 👀", Cuentas.GetClientes, Cuentas.GetCliente,
		),
	}
}

type VerProveedores struct {
	datos PedirDatosVerProveedores
}

func (v VerProveedores) Init() tea.Cmd {
	return v.datos.Init()
}

func (v VerProveedores) View() string {
	return v.datos.View()
}

func (v VerProveedores) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewVerProveedores() VerProveedores {
	return VerProveedores{
		datos: NewPedirDatosVerProveedores(
			[]string{"ID"}, "👀 Ver Proveedores 👀", Cuentas.GetProveedores, Cuentas.GetProveedor,
		),
	}
}

type CrearCliente struct {
	datos PedirDatosCrearCliente
}

func (v CrearCliente) Init() tea.Cmd {
	return v.datos.Init()
}

func (v CrearCliente) View() string {
	return v.datos.View()
}

func (v CrearCliente) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewCrearCliente() CrearCliente {
	return CrearCliente{
		datos: NewPedirDatosCrearCliente(
			[]string{"user", "password", "nombre", "edad", "descripcion", "foto"}, "Crear Cliente", Creacion.CrearCliente,
		),
	}
}

type CrearProveedor struct {
	datos PedirDatosCrearProveedor
}

func (v CrearProveedor) Init() tea.Cmd {
	return v.datos.Init()
}

func (v CrearProveedor) View() string {
	return v.datos.View()
}

func (v CrearProveedor) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	return v.datos.Update(msg)
}

func NewCrearProveedor() CrearProveedor {
	return CrearProveedor{
		datos: NewPedirDatosCrearProveedor(
			[]string{"user", "password", "nombre", "edad", "descripcion", "telefono", "pagina_web", "foto", "facebook", "instagram", "twitter"}, "Crear Proveedor", Creacion.CrearProveedor,
		),
	}
}
