# First the login
podman login docker.io

cd ./"API Gateway"
# Build and push the Auth
cd ./"Auth"
podman build -t docker.io/micr0nclsbayona/api-gateway-auth .
podman push docker.io/micr0nclsbayona/api-gateway-auth
cd ..
# Build and push the LoadBalancer
cd ./"LoadBalancer"
podman build -t docker.io/micr0nclsbayona/api-gateway-loadbalancer .
podman push docker.io/micr0nclsbayona/api-gateway-loadbalancer
cd ..
# Build and push the Service discovery
cd ./"Service Discovery"
podman build -t docker.io/micr0nclsbayona/api-gateway-serviced .
podman push docker.io/micr0nclsbayona/api-gateway-serviced
cd ..
cd ..
cd ./"Microservices"
# Build and push the SOAP Microservice
cd ./"Busquedas-SOAP"
podman build -t docker.io/micr0nclsbayona/microservices-soaps .
podman push docker.io/micr0nclsbayona/microservices-soaps
cd ..
# Build and push the Grades Microservice
cd ./"Calificaciones"
podman build -t docker.io/micr0nclsbayona/microservices-grades .
podman push docker.io/micr0nclsbayona/microservices-grades
cd ..
# Build and push the Purchases Microservice
cd ./"Compras"
podman build -t docker.io/micr0nclsbayona/microservices-purchases .
podman push docker.io/micr0nclsbayona/microservices-purchases
cd ..
# Build and push the Products Microservice
cd ./"Productos"
podman build -t docker.io/micr0nclsbayona/microservices-products .
podman push docker.io/micr0nclsbayona/microservices-products
cd ..
# Build and push the Client 
cd ../"Client"
podman build -t docker.io/micr0nclsbayona/client .
podman push docker.io/micr0nclsbayona/client
cd ..
