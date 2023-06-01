package cuentas

import (
	"fmt"
	"strings"
	"time"
	//
	Cuentas "github.com/nclsbayona/Tecnoverde/Client/llamadas/cuentas"
)

type Cuenta struct {
	Token           string
	Caracteristicas []string
	CuentaValida    string
}

func (c *Cuenta) Init() {
	c.Token = Cuentas.Token
	if c.Token != "" {
		carac, err := Cuentas.GetTokenClaims()
		if err == nil {
			c.Caracteristicas = carac
			c.validarCuenta()
		} else {
			c.Caracteristicas = []string{"Error"}
			c.CuentaValida = "❌ Invalida"
		}
	} else {
		c.Caracteristicas = []string{"Error"}
		c.CuentaValida = "❌ No hay token"
	}
}

func (c *Cuenta) validarCuenta() {
	if len(c.Caracteristicas) > 0 {
		exp_date := c.Caracteristicas[0]
		exp_date = strings.Split(exp_date, " ")[2]
		layout := "2006-01-02T15:04:05.000Z"
		t, err := time.Parse(layout, exp_date)
		if err == nil && t.After(time.Now()) {
			c.CuentaValida = "✅ Valida"
		} else {
			if err != nil {
				c.CuentaValida = "❌ Error" + err.Error()
			} else {
				c.CuentaValida = "❌ Vencida"
			}
		}
	}
}

func (c *Cuenta) ToString() string {
	var str string
	if c.Token == "" {
		str = "🐒 No se ha iniciado sesion 🐒"
	} else {
		if len(c.Caracteristicas) > 1 {
			str = strings.Replace(c.Caracteristicas[3], "\n", "", -1) + "\n" + c.CuentaValida
		} else {
			str = c.Caracteristicas[0]
		}
	}
	return fmt.Sprintf(
		"\n%s\n",
		str,
	)
}