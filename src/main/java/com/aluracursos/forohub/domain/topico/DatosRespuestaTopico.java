package com.aluracursos.forohub.domain.topico;

import java.util.Date;

public record DatosRespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        Date fechaCreacion,
        Boolean status,
        String usuario,
        String curso
) {
    public DatosRespuestaTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(),
                topico.getUsuario().getNombre(), topico.getCurso().getNombre());
    }
}