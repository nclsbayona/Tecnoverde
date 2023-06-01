package menu

import (
	"github.com/charmbracelet/bubbles/table"
	tea "github.com/charmbracelet/bubbletea"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
	Constants "github.com/nclsbayona/Tecnoverde/Client/tui/constants"
)

type Table struct {
	table table.Model
}

func (m Table) Init() tea.Cmd { return nil }

func (m Table) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	var cmd tea.Cmd
	switch msg := msg.(type) {
	case tea.KeyMsg:
		switch msg.String() {
		case "q", "ctrl+c", "esc":
			return NewMenu()

		case "enter":
			if m.table.Focused() {
				m.table.Blur()
			} else {
				m.table.Focus()
			}
		}
	}
	m.table, cmd = m.table.Update(msg)
	return m, cmd
}

func (m Table) View() string {
	return Constants.TableStyle.Render(m.table.View()) + "\n"
}

func NewTableSingleData(struc Interfaces.ModeloDeDatos) Table {
	columns := struc.ObtenerColumnas()
	rows := struc.ObtenerFilas()
	var cols []table.Column
	var row []table.Row
	for _, c := range columns {
		cols = append(cols, table.Column{Title: c, Width: Constants.WindowSize.Width / len(columns)})
	}
	for _, r := range rows {
		row = append(row, table.Row(r))
	}
	tab := table.New(
		table.WithColumns(cols),
		table.WithRows(row),
		table.WithHeight(15),
	)
	return Table{table: tab}
}

func NewTableMultipleData(struc []Interfaces.ModeloDeDatos) Table {
	if len(struc) > 0 {
		columns := struc[0].ObtenerColumnas()
		rows := make([]table.Row, len(struc))
		for i, s := range struc {
			rows[i] = s.ObtenerFilas()[0]
		}
		var cols []table.Column
		var row []table.Row
		for _, c := range columns {
			cols = append(cols, table.Column{Title: c, Width: Constants.WindowSize.Width / len(columns)})
		}
		for _, r := range rows {
			row = append(row, table.Row(r))
		}
		tab := table.New(
			table.WithColumns(cols),
			table.WithRows(row),
			table.WithHeight(15),
		)
		return Table{table: tab}
	}
	return Table{table: table.New()}
}

func NewTableMultipleData2(struc Interfaces.ModeloDeDatos) Table {
	columns := struc.ObtenerColumnas()
	pseudo_rows := struc.ObtenerFilas()
	rows := make([]table.Row, len(pseudo_rows))
	for i, s := range pseudo_rows {
		rows[i] = s
	}
	var cols []table.Column
	var row []table.Row
	for _, c := range columns {
		cols = append(cols, table.Column{Title: c, Width: Constants.WindowSize.Width / len(columns)})
	}
	for _, r := range rows {
		row = append(row, table.Row(r))
	}
	tab := table.New(
		table.WithColumns(cols),
		table.WithRows(row),
		table.WithHeight(len(pseudo_rows)),
	)
	return Table{table: tab}
}
