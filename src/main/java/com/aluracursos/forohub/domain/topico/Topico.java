package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.respuesta.Respuesta;
import com.aluracursos.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Column(unique = true)

    private String mensaje;
    private Date fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cursos_id")
    private Curso curso;
    @OneToMany
    @JoinColumn(name = "respuesta_id")
    private List<Respuesta> respuesta;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario usuario, Curso curso){
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fechaCreacion = datosRegistroTopico.fechaCreacion();
        this.status = true;
        this.usuario = usuario;
        this.curso = curso;
    }
    public void actualizarTopico(DatosActualizarTopico datosActualizarTopico, Usuario usuario,
                                 Curso curso){
        if(datosActualizarTopico.titulo() != null){
            this.titulo = datosActualizarTopico.titulo();
        }
        if(datosActualizarTopico.mensaje() != null){
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if(datosActualizarTopico.fechaCreacion() != null){
            this.fechaCreacion = datosActualizarTopico.fechaCreacion();
        }
        if(datosActualizarTopico.idUsuario() != null){
            this.usuario = usuario;
        }
        if(datosActualizarTopico.idCurso() != null){
            this.curso = curso;
        }
    }
    public void desactivarTopico(){
        this.status = false;
    }


}
