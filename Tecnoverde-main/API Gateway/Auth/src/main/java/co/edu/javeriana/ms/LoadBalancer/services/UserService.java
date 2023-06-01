package co.edu.javeriana.ms.LoadBalancer.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.edu.javeriana.ms.LoadBalancer.entities.AppUser;
import co.edu.javeriana.ms.LoadBalancer.entities.Cliente;
import co.edu.javeriana.ms.LoadBalancer.entities.Proveedor;
import co.edu.javeriana.ms.LoadBalancer.entities.dtos.ClienteDTO;
import co.edu.javeriana.ms.LoadBalancer.entities.dtos.ProveedorDTO;
import co.edu.javeriana.ms.LoadBalancer.repository.ClienteRepository;
import co.edu.javeriana.ms.LoadBalancer.repository.FotosRepository;
import co.edu.javeriana.ms.LoadBalancer.repository.ProveedorRepository;
import co.edu.javeriana.ms.LoadBalancer.repository.Redes_SocialesRepository;

@Component
public class UserService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private FotosRepository fotosRepository;

    @Autowired
    private Redes_SocialesRepository redesRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder encoder) {
        this.passwordEncoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails ud = null;
        try {
            Cliente cliente = clienteRepository.findByUsername(username).get(0);
            ud = new AppUser(cliente);
        } catch (Exception e) {
            try {
                Proveedor p = proveedorRepository.findByUsername(username).get(0);
                ud = new AppUser(p);
            } catch (Exception e2) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        }
        return ud;
    }

    private boolean checkUsernameTaken(String username) {
        return !clienteRepository.findByUsername(username).isEmpty()
                || !proveedorRepository.findByUsername(username).isEmpty();
    }

    public String createCliente(Cliente c) {
        String a = "OK";
        if (checkUsernameTaken(c.getUsername()))
            a = "NO " + a;
        else {
            try {
                c.setPassword(passwordEncoder.encode(c.getPassword()));
                fotosRepository.save(c.getFoto());
                clienteRepository.save(c);
                a+=" - ID "+String.valueOf(c.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                a = "NO " + a;
            }
        }
        return a;
    }

    public String createProveedor(Proveedor p) {
        String a = "OK";
        if (checkUsernameTaken(p.getUsername()))
            a = "NO " + a;
        else {
            try {
                p.setPassword(passwordEncoder.encode(p.getPassword()));
                redesRepository.save(p.getRedesSociales());
                fotosRepository.save(p.getFoto());
                proveedorRepository.save(p);
                a+=" - ID "+String.valueOf(p.getId());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                a = "NO " + a;
            }
        }
        return a;
    }

    public List<ClienteDTO> getAllClientes(){
        List<ClienteDTO> clientes = new ArrayList<>();
        for(Cliente c : clienteRepository.findAll()){
            clientes.add(new ClienteDTO(c));
        }
        return clientes;
    }
    
    public List<ClienteDTO> getClienteByID(Long id){
        List<ClienteDTO> clientes = new ArrayList<>();
        try{
        clientes.add(new ClienteDTO(clienteRepository.findById(id).get()));
        } catch (Exception e){

        }
        return clientes;

    }

    public List<ProveedorDTO> getProveedorByID(Long id) {
        List<ProveedorDTO> proveedores=new ArrayList<>();
        try{
            proveedores.add(new ProveedorDTO(proveedorRepository.findById(id).get()));
        } catch (Exception e){

        }
        return proveedores;
    }

    public List<ProveedorDTO> getAllProveedores() {
        List<ProveedorDTO> proveedores=new ArrayList<>();
        for(Proveedor p : proveedorRepository.findAll()){
            proveedores.add(new ProveedorDTO(p));
        }
        return proveedores;
    }
}
