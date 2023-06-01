package menu

import (
	"fmt"
	"io"
	"strings"

	"github.com/charmbracelet/bubbles/key"
	"github.com/charmbracelet/bubbles/list"
	tea "github.com/charmbracelet/bubbletea"
	Constants "github.com/nclsbayona/Tecnoverde/Client/tui/constants"
	Cuentas "github.com/nclsbayona/Tecnoverde/Client/tui/cuentas"
)

func optionsToItems(options []Item) []list.Item {
	items := make([]list.Item, len(options))
	for i, proj := range options {
		items[i] = list.Item(proj)
	}
	return items
}

type renameProjectMsg []list.Item

type Item struct {
	name string
	role string
}

func (i Item) FilterValue() string { return i.name }
func (i Item) ToString() string    { return "*" + i.role + "*" + " " + i.name }

type itemDelegate struct{}

func (d itemDelegate) Height() int                               { return 1 }
func (d itemDelegate) Spacing() int                              { return 0 }
func (d itemDelegate) Update(msg tea.Msg, m *list.Model) tea.Cmd { return nil }
func (d itemDelegate) Render(w io.Writer, m list.Model, index int, listItem list.Item) {
	i, ok := listItem.(Item)
	if !ok {
		return
	}

	var str string = i.ToString()

	fn := Constants.DocStyle.Render
	if index == m.Index() {
		fn = func(s ...string) string {
			return Constants.SelectedItemStyle.Render(" ✔️ " + strings.Join(s, " "))
		}
	} else {
		fn = func(s ...string) string {
			return Constants.ItemStyle.Render("" + strings.Join(s, " "))
		}
	}

	fmt.Fprint(w, fn(str))
}

type Menu struct {
	Cuenta *Cuentas.Cuenta
	List   list.Model
	choice Item
}

type errMsg struct{ error }

func (m Menu) Init() tea.Cmd {
	return nil
}

func (m Menu) View() string {
	return m.List.View()
}

func (m Menu) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	var cmd tea.Cmd
	var cmds []tea.Cmd
	switch msg := msg.(type) {
	case renameProjectMsg:
		m.List.SetItems(msg)
	case tea.WindowSizeMsg:
		Constants.WindowSize = msg
		top, right, bottom, left := Constants.DocStyle.GetMargin()
		m.List.SetSize(msg.Width-left-right, msg.Height-top-bottom-1)
	case tea.KeyMsg:
		switch {
		case key.Matches(msg, Constants.Keymap.Back):
			return NewMenu()
		case key.Matches(msg, Constants.Keymap.Quit):
			return m, tea.Quit
		case key.Matches(msg, Constants.Keymap.Enter):
			i, ok := m.List.SelectedItem().(Item)
			if ok {
				m.choice = i
			}
			switch i.name {
			case "Iniciar Sesion":
				entry := NewIniciarSesion()
				return entry.Update(Constants.WindowSize)

			case "Crear Cliente":
				entry := NewCrearCliente()
				return entry.Update(Constants.WindowSize)

			case "Crear Proveedor":
				entry := NewCrearProveedor()
				return entry.Update(Constants.WindowSize)

			case "Ver Clientes":
				entry := NewVerClientes()
				return entry.Update(Constants.WindowSize)

			case "Ver Proveedores":
				entry := NewVerProveedores()
				return entry.Update(Constants.WindowSize)

			case "Ver Productos":
				entry := NewVerProductos()
				return entry.Update(Constants.WindowSize)

			case "Ver Servicios":
				entry := NewVerServicios()
				return entry.Update(Constants.WindowSize)

			case "Crear Servicio":
				entry := NewCrearServicio()
				return entry.Update(Constants.WindowSize)

			case "Ver Contenidos":
				entry := NewVerContenidos()
				return entry.Update(Constants.WindowSize)

			case "Buscar Servicio (SOAP)":
				entry := NewBuscarServicio()
				return entry.Update(Constants.WindowSize)

			case "Ver Compras":
				entry := NewVerCompras()
				return entry.Update(Constants.WindowSize)

			case "Pagar Compra":
				entry := NewPagarCompra()
				return entry.Update(Constants.WindowSize)

			case "Crear Una Nueva Compra":
				entry := NewCrearUnaNuevaCompra()
				return entry.Update(Constants.WindowSize)

			case "Ver Calificaciones":
				entry := NewVerCalificaciones()
				return entry.Update(Constants.WindowSize)

			case "Crear Una Nueva Calificacion":
				entry := NewCrearCalificacion()
				return entry.Update(Constants.WindowSize)

			case "Salir":
				return m, tea.Quit
			}
		}
	}
	m.List, cmd = m.List.Update(msg)
	cmds = append(cmds, cmd)
	return m, tea.Batch(cmds...)
}

func NewMenu() (tea.Model, tea.Cmd) {
	var menu *Menu = &Menu{}
	menu.Cuenta = new(Cuentas.Cuenta)
	menu.Cuenta.Init()
	menu.List = list.New(optionsToItems([]Item{{"Iniciar Sesion", "No es necesario registro"}, {"Crear Cliente", "No es necesario registro"}, {"Crear Proveedor", "No es necesario registro"}, {"Ver Clientes", "No es necesario registro"}, {"Ver Proveedores", "No es necesario registro"}, {"Ver Productos", "Clientes"}, {"Ver Servicios", "Clientes"}, {"Crear Servicio", "Proveedores"}, {"Ver Contenidos", "Clientes"}, {"Buscar Servicio (SOAP)", "Clientes"}, {"Ver Compras", "Clientes"}, {"Pagar Compra", "Clientes"}, {"Crear Una Nueva Compra", "Clientes"}, {"Ver Calificaciones", "Clientes"}, {"Crear Una Nueva Calificacion", "Clientes"}, {"Salir", "¯\\_( ͡❛ ͜ʖ͡❛ )_/¯"}}), itemDelegate{}, Constants.WindowSize.Width, Constants.WindowSize.Height)

	if Constants.WindowSize.Height != 0 {
		top, right, bottom, left := Constants.DocStyle.GetMargin()
		menu.List.SetSize(Constants.WindowSize.Width-left-right, Constants.WindowSize.Height-top-bottom-1)
	}
	str := menu.Cuenta.ToString()
	menu.List.Title = str
	menu.List.AdditionalShortHelpKeys = func() []key.Binding {
		return []key.Binding{
			Constants.Keymap.Enter,
			Constants.Keymap.Back,
			Constants.Keymap.Quit,
		}
	}
	return *menu, func() tea.Msg { return errMsg{nil} }
}

func RunProgram() *tea.Program {
	var p *tea.Program
	// 1 is Inline, 2 is Fullscreen
	switch Constants.Mode {
	case 1:
		p = tea.NewProgram(Constants.Menu)
	case 2:
		p = tea.NewProgram(Constants.Menu, tea.WithAltScreen())
	}
	return p
}
