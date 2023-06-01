package cuentas

import (
	"encoding/json"
	"errors"
	"fmt"
	"github.com/dgrijalva/jwt-go"
	"time"
)

func extractUnverifiedClaims(tokenString string) ([]string, error) {
	token, _, err := new(jwt.Parser).ParseUnverified(tokenString, jwt.MapClaims{})
	var name []string = nil
	if err != nil {
		return []string{}, err
	}

	if claims, ok := token.Claims.(jwt.MapClaims); ok {
		var tm time.Time
		switch iat := claims["iat"].(type) {
		case float64:
			tm = time.Unix(int64(iat), 0)
		case json.Number:
			v, _ := iat.Int64()
			tm = time.Unix(v, 0)
		}

        var tm2 time.Time
		switch iat := claims["exp"].(type) {
		case float64:
			tm2 = time.Unix(int64(iat), 0)
		case json.Number:
			v, _ := iat.Int64()
			tm2 = time.Unix(v, 0)
		}

		name = []string{"Token expires: "+tm2.String(), "Token created: "+tm.String(), "User: "+fmt.Sprint(claims["sub"]), fmt.Sprint(claims["roles"])}
	}

	if name == nil {
		return name, errors.New("invalid token payload")
	}
	return name, nil
}

func ValidateToken() (bool){
	return Token!=""
	/*token, _, err := new(jwt.Parser).ParseUnverified(Token, jwt.MapClaims{})
	if err != nil {
		return false
	}
	return token.Valid*/
}

func GetTokenClaims() ([]string, error) {
	if Token != "" {
		return extractUnverifiedClaims(Token)
	}
	return []string{}, errors.New("no token")
}
