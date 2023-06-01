package menu

import (
	"fmt"
	"strconv"
	"strings"

	"github.com/charmbracelet/bubbles/cursor"
	"github.com/charmbracelet/bubbles/key"
	"github.com/charmbracelet/bubbles/textinput"
	tea "github.com/charmbracelet/bubbletea"
	Interfaces "github.com/nclsbayona/Tecnoverde/Client/llamadas/interfaces"
	Constants "github.com/nclsbayona/Tecnoverde/Client/tui/constants"
)

type PedirDatosBuscarServicio struct {
	focusIndex       int
	title            string
	inputs           []textinput.Model
	cursorMode       cursor.Mode
	funcion_unica_id func(int) (Interfaces.ModeloDeDatos, error)
}

func startModelPedirDatosBuscarServicio(fields []string, title string, funcion_especifica func(int) (Interfaces.ModeloDeDatos, error)) PedirDatosBuscarServicio {
	m := PedirDatosBuscarServicio{
		inputs:           make([]textinput.Model, len(fields)),
		title:            title,
		funcion_unica_id: funcion_especifica,
	}
	var t textinput.Model
	for i := range m.inputs {
		t = textinput.New()
		t.CursorStyle = Constants.CursorStyle
		t.CharLimit = 32

		switch i {
		case 0:
			t.Placeholder = fields[i]
			t.EchoMode = textinput.EchoNormal
			t.Focus()
			t.PromptStyle = Constants.FocusedStyle
			t.TextStyle = Constants.FocusedStyle
		default:
			t.Placeholder = fields[i]
			if t.Placeholder == "Contraseña" {
				t.EchoMode = textinput.EchoNone
			} else {
				t.EchoMode = textinput.EchoNormal
			}
		}

		m.inputs[i] = t
	}

	return m
}

func (m PedirDatosBuscarServicio) Init() tea.Cmd {
	return textinput.Blink
}

func (m PedirDatosBuscarServicio) Update(msg tea.Msg) (tea.Model, tea.Cmd) {
	switch msg := msg.(type) {
	case tea.KeyMsg:
		switch msg.String() {
		default:
			if key.Matches(msg, Constants.Keymap.Back) {
				return NewMenu()
			}
		// Change cursor mode
		case "ctrl+r":
			m.cursorMode++
			if m.cursorMode > cursor.CursorHide {
				m.cursorMode = cursor.CursorBlink
			}
			cmds := make([]tea.Cmd, len(m.inputs))
			for i := range m.inputs {
				cmds[i] = m.inputs[i].SetCursorMode(textinput.CursorMode(m.cursorMode))
			}
			return m, tea.Batch(cmds...)
		// Set focus to next input
		case "tab", "shift+tab", "enter", "up", "down":
			s := msg.String()

			if s == "enter" && m.focusIndex == len(m.inputs) {
				if m.inputs[0].Value() != "" {
					val, _ := strconv.Atoi(m.inputs[0].Value())
					result, err := m.funcion_unica_id(val)
					if err == nil && result != nil {
						entry := NewTableSingleData(result)
						return entry.Update(Constants.WindowSize)
					}
				}
			}

			// Cycle indexes
			if s == "up" || s == "shift+tab" {
				m.focusIndex--
			} else {
				m.focusIndex++
			}

			if m.focusIndex > len(m.inputs) {
				m.focusIndex = 0
			} else if m.focusIndex < 0 {
				m.focusIndex = len(m.inputs)
			}

			cmds := make([]tea.Cmd, len(m.inputs))
			for i := 0; i <= len(m.inputs)-1; i++ {
				if i == m.focusIndex {
					// Set focused state
					cmds[i] = m.inputs[i].Focus()
					m.inputs[i].PromptStyle = Constants.FocusedStyle
					m.inputs[i].TextStyle = Constants.FocusedStyle
				} else {
					// Remove focused state
					m.inputs[i].Blur()
					m.inputs[i].PromptStyle = Constants.DocStyle
					m.inputs[i].TextStyle = Constants.DocStyle
				}
			}
			return m, tea.Batch(cmds...)
		}
	}

	// Handle character input and blinking
	cmd := m.updateInputs(msg)

	return m, cmd
}

func (m *PedirDatosBuscarServicio) updateInputs(msg tea.Msg) tea.Cmd {
	cmds := make([]tea.Cmd, len(m.inputs))

	// Only text inputs with Focus() set will respond, so it's safe to simply
	// update all of them here without any further logic.
	for i := range m.inputs {
		m.inputs[i], cmds[i] = m.inputs[i].Update(msg)
	}

	return tea.Batch(cmds...)
}

func (m PedirDatosBuscarServicio) View() string {
	var b strings.Builder

	b.WriteString(m.title)
	b.WriteRune('\n')
	b.WriteString(strings.Repeat("*", len(m.title)))
	b.WriteRune('\n')
	b.WriteRune('\n')
	b.WriteRune('\n')

	for i := range m.inputs {
		b.WriteString(m.inputs[i].View())
		if i < len(m.inputs)-1 {
			b.WriteRune('\n')
		}
	}

	focusedButton := Constants.BlurredStyle.Render(" - Submit - ")
	button := &focusedButton
	if m.focusIndex == len(m.inputs) {
		focusedButton = Constants.FocusedStyle.Render(" - Submit - ")
		button = &focusedButton
	}
	fmt.Fprintf(&b, "\n\n%s\n\n", *button)

	b.WriteString(Constants.HelpStyle.Render("cursor mode is "))
	b.WriteString(Constants.HelpStyle.Render(m.cursorMode.String()))
	b.WriteString(Constants.HelpStyle.Render(" (ctrl+r to change style)"))

	return b.String()
}

func NewPedirDatosBuscarServicio(fields []string, title string, funcion_unica func(int) (Interfaces.ModeloDeDatos, error)) PedirDatosBuscarServicio {
	return startModelPedirDatosBuscarServicio(fields, title, funcion_unica)
}
