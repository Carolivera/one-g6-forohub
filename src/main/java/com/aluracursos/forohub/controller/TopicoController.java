package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    //Crear un nuevo tópico: requiere tener un usuario y un curso para asignar
    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico,
                                                                UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.getReferenceById(datosRegistroTopico.idUsuario());
        Curso curso = cursoRepository.getReferenceById(datosRegistroTopico.idCurso());
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico, usuario, curso));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    //Traer los tópicos activos
    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> buscarListadoTopicos(@PageableDefault(size=10) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new));
    }
    //Traer un tópico por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> buscarDatosTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico);
        return ResponseEntity.ok(datosTopico);
    }
    //Actualizar datos de un tópico: requiere enviar usuario y curso
    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        System.out.println(datosActualizarTopico);
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarTopico.idUsuario());
        Curso curso = cursoRepository.getReferenceById(datosActualizarTopico.idCurso());
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarTopico(datosActualizarTopico, usuario, curso);
        return ResponseEntity.ok(new DatosRespuestaTopico(topico));
    }
    //Eliminar lógicamente un tópico (se cambia el status a false o inactivo)
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }

}
