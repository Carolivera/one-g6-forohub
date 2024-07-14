package com.aluracursos.forohub.domain.topico;

import java.util.Date;

public record DatosListadoTopico(
        String titulo,
        String mensaje,
        Date fechaCreacion,
        Boolean status,
        String usuario,
        String curso
) {
    public DatosListadoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                topico.getUsuario().getNombre(), topico.getCurso().getNombre());
    }
}
