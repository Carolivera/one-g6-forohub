package com.aluracursos.forohub.domain.usuario;

public record DatosRespuestaUsuario(
        Long id,
        String nombre,
        String correoElectronico,
        String contrasena,
        String perfiles
) {
}
