package constants

import (
	"github.com/charmbracelet/bubbles/key"
	tea "github.com/charmbracelet/bubbletea"
	"github.com/charmbracelet/lipgloss"
)

/* CONSTANTS */

var (
	// P the current tea program
	P *tea.Program
	// WindowSize store the size of the terminal window
	WindowSize tea.WindowSizeMsg
	// The App Mode
	Mode int
	// The App Menu
	Menu tea.Model
)

/* STYLING */

// DocStyle styling for viewports
var DocStyle = lipgloss.NewStyle().Margin(0, 1)

// HelpStyle styling for help context menu
var HelpStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("241"))

// ErrStyle provides styling for error messages
var ErrStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("#bd534b"))

// AlertStyle provides styling for alert messages
var AlertStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("62"))

var SelectedItemStyle = lipgloss.NewStyle().PaddingLeft(2).Foreground(lipgloss.Color("#395BBF"))

var ItemStyle = lipgloss.NewStyle().PaddingLeft(1).Foreground(lipgloss.Color("#9D8EF2"))

var Quit = lipgloss.NewStyle().Background(lipgloss.Color("#ffffff")).Foreground(lipgloss.Color("0")).Render("Ok, Bye !")

var FocusedStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("205"))
var BlurredStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("240"))
var CursorStyle = lipgloss.NewStyle().Foreground(lipgloss.Color("197"))

var TableStyle = lipgloss.NewStyle().BorderStyle(lipgloss.NormalBorder()).BorderForeground(lipgloss.Color("#AFDE70"))

type keymap struct {
	Enter key.Binding
	Back  key.Binding
	Quit  key.Binding
}

// Keymap reusable key mappings shared across models
var Keymap = keymap{
	Enter: key.NewBinding(
		key.WithKeys("enter"),
		key.WithHelp("enter", "select"),
	),
	Back: key.NewBinding(
		key.WithKeys("esc"),
		key.WithHelp("esc", "back"),
	),
	Quit: key.NewBinding(
		key.WithKeys("ctrl+c", "q"),
		key.WithHelp("ctrl+c", "quit"),
	),
}
