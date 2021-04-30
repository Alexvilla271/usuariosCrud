package com.example.usuarios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import me.tell.usuarios.AnadirUsuarioRequest;
import me.tell.usuarios.AnadirUsuarioResponse;
import me.tell.usuarios.BusquedaUsuarioRequest;
import me.tell.usuarios.BusquedaUsuarioResponse;
import me.tell.usuarios.EliminarUsuarioRequest;
import me.tell.usuarios.EliminarUsuarioResponse;
import me.tell.usuarios.ModificarUsuarioRequest;
import me.tell.usuarios.ModificarUsuarioResponse;

@Endpoint
public class UsuariosEndPoint {
    @Autowired
    private Iusuarios iusuarios;
    @PayloadRoot(namespace="http://tell.me/usuarios",localPart="AnadirUsuarioRequest" )
    @ResponsePayload
    public AnadirUsuarioResponse dameAlimento(@RequestPayload AnadirUsuarioRequest peticion){
        AnadirUsuarioResponse respuesta = new AnadirUsuarioResponse();
        respuesta.setRespuesta("Se agregó correctamente el usuario " + peticion.getNombre());

        Usuarios usuarios = new Usuarios();
        usuarios.setNombre(peticion.getNombre());
        usuarios.setTipo(peticion.getTipo());

        iusuarios.save(usuarios);
        
        return respuesta;
    }

    @PayloadRoot(namespace="http://tell.me/usuarios",localPart="BusquedaUsuarioRequest" )
    @ResponsePayload
    public BusquedaUsuarioResponse dameUsuarios( @RequestPayload BusquedaUsuarioRequest peticion){
        BusquedaUsuarioResponse respuesta = new BusquedaUsuarioResponse();
        Iterable<Usuarios> listaUsuarios = iusuarios.findAll();
        
        for(Usuarios ls : listaUsuarios){
            BusquedaUsuarioResponse.Notificacion notificacion = new BusquedaUsuarioResponse.Notificacion();
            notificacion.setId(ls.getId());
            notificacion.setNombre(ls.getNombre());
            notificacion.setTipo(ls.getTipo());
        }
        return respuesta;
    }

    @PayloadRoot(namespace="http://tell.me/usuarios",localPart="ModificarUsuarioRequest" )
    @ResponsePayload
    public ModificarUsuarioResponse modificarUsuario(@RequestPayload ModificarUsuarioRequest peticion){
        ModificarUsuarioResponse respuesta = new ModificarUsuarioResponse();
        Optional<Usuarios> s = iusuarios.findById(peticion.getId());

        if(s.isPresent()){
            Usuarios usuarios = new Usuarios();
            usuarios = s.get();
            usuarios.setNombre(peticion.getNombre());
            usuarios.setTipo(peticion.getTipo());
            iusuarios.save(usuarios);
            respuesta.setRespuesta("Se modificó el usuario: " + peticion.getNombre());
        }else{
            respuesta.setRespuesta("Id no existe: " + peticion.getId());
        }
        return respuesta;
    }

    @PayloadRoot(namespace="http://tell.me/usuarios",localPart="EliminarUsuarioRequest")
    @ResponsePayload
    public EliminarUsuarioResponse eliminaUsuario(@RequestPayload EliminarUsuarioRequest peticion){
        EliminarUsuarioResponse respuesta= new EliminarUsuarioResponse();
        respuesta.setRespuesta("Se eliminó el usuario " + peticion.getId());
        iusuarios.deleteById(peticion.getId());
    
        return respuesta;
    }
}
