package main

/*
import (
	"fmt"
	Busqueda "github.com/nclsbayona/Tecnoverde/Client/llamadas/busquedasoap"
	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
	CrearCuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas/creacion"
	Productos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos"
	Contenidos "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/contenidos"
	Servicios "github.com/nclsbayona/Tecnoverde/Client/llamadas/productos/servicios"
)

func main() {
	fmt.Println("Crear Cliente")
	fmt.Println(CrearCuentas.CrearCliente("y34x4t3", "Nicol4$", "L34k", "21", "Donde nos perdimos?", ""))

	fmt.Println("Crear Proveedor")
	fmt.Println(CrearCuentas.CrearProveedor("JPxD", "GG", "Pablo", "17", "Soy un guía experto!", "6052426172", "Mi pagina web", "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fthumbs.dreamstime.com%2Fb%2Fcara-seria-del-hombre-98568244.jpg&f=1&nofb=1&ipt=4c893e50e3b78758ead2295899039d64bcb302f318199ec9fce1c9808fca0f22&ipo=images", "", "", ""))

	fmt.Println("Ver Clientes")
	fmt.Println(Cuentas.GetClientes())

	fmt.Println("Ver Cliente")
	fmt.Println(Cuentas.GetCliente(1))

	fmt.Println("Ver Proveedores")
	fmt.Println(Cuentas.GetProveedores())

	fmt.Println("Ver Proveedor")
	fmt.Println(Cuentas.GetProveedor(1))

	fmt.Println("Login X")
	fmt.Println(Cuentas.Login("bayona.n", "ajbha"))

	fmt.Println("Login")
	fmt.Println(Cuentas.Login("bayona.n", "Nicol4$"))

	fmt.Println("Token Claims")
	fmt.Println(Cuentas.GetTokenClaims())

	fmt.Println("Ver Productos")
	fmt.Println(Productos.SearchAll())

	fmt.Println("Ver Productos por tipo")
	fmt.Println(Productos.SearchByTipos("Infografia", "Acompañamiento"))

	fmt.Println("Ver Productos por nombre")
	fmt.Println(Productos.SearchByNombre("Con"))

	fmt.Println("Ver Productos por descripcion")
	fmt.Println(Productos.SearchByDescripcion("Con"))

	fmt.Println("Ver Contenidos")
	fmt.Println(Contenidos.SearchAll())

	fmt.Println("Ver Contenido")
	fmt.Println(Contenidos.SearchByID(3))

	fmt.Println("Ver Contenidos por tipo")
	fmt.Println(Contenidos.SearchByTipo("Infografia"))

	fmt.Println("Ver Servicios")
	fmt.Println(Servicios.SearchAll())

	fmt.Println("Ver Servicio")
	fmt.Println(Servicios.SearchByID(1))

	fmt.Println("Ver Servicios por tipo")
	fmt.Println(Servicios.SearchByTipo("Acompañamiento"))

	fmt.Println("Login")
	fmt.Println(Cuentas.Login("juan", "jujujuan"))

	fmt.Println("Crear Servicio")
	fmt.Println(Servicios.Create("Este es un servicio de prueba", "Probando", "1", "$25.000", "Alojamiento", map[string]string{"Ubicacion": "Castillo Marroquin"}))

	fmt.Println("Buscar servicio (SOAP)")
	fmt.Println(Busqueda.SearchByID(1))
} */

import (
	Menu "github.com/nclsbayona/Tecnoverde/Client/tui/menu"
	Constants "github.com/nclsbayona/Tecnoverde/Client/tui/constants"
	"github.com/urfave/cli/v2"
	"log"
	"os"
)

func start(app_type int) error {
	Constants.Menu, _ = Menu.NewMenu()
	Constants.Mode = app_type
	Constants.P = Menu.RunProgram()
	_, err := Constants.P.Run()
	return err
}

func inline(c *cli.Context) error {
	return start(1)
}

func fullscreen(c *cli.Context) error {
	return start(2)
}

func main() {
	app := &cli.App{
		Name:    "Tecnoverde API Client",
		Usage:   "Client for Tecnoverde API",
		Action:  fullscreen,
		Version: "1.0.0",
		Commands: []*cli.Command{
			{
				Name:   "inline",
				Usage:  "View the App in Inline mode",
				Action: inline,
			},
			{
				Name:   "fullscreen",
				Usage:  "View the App in Fullscreen mode",
				Action: fullscreen,
			},
		},
	}

	err := app.Run(os.Args)
	if err != nil {
		log.Fatal(err)
	}
}
