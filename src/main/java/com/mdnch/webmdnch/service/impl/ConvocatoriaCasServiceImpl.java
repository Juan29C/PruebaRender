package com.mdnch.webmdnch.service.impl;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.mdnch.webmdnch.dto.enums.DocumentoTipo;
import com.mdnch.webmdnch.dto.request.DocumentoConfigRequest;
import com.mdnch.webmdnch.dto.response.DocumentoItemResponse;
import com.mdnch.webmdnch.entity.ConvocatoriaDocumentoEntity;
import com.mdnch.webmdnch.repository.ConvocatoriaDocumentoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mdnch.webmdnch.dto.request.ConvocatoriaCasRequest;
import com.mdnch.webmdnch.dto.response.ConvocatoriaCasResponse;
import com.mdnch.webmdnch.entity.ConvocatoriaCasEntity;
import com.mdnch.webmdnch.exception.ResourceNotFoundException;
import com.mdnch.webmdnch.mapper.ConvocatoriaCasMapper;
import com.mdnch.webmdnch.repository.ConvocatoriaCasRepository;
import com.mdnch.webmdnch.service.ConvocatoriaCasService;
import com.mdnch.webmdnch.util.FileUploadUtil;

@Service
public class ConvocatoriaCasServiceImpl implements ConvocatoriaCasService {

    @Value("${documentos.urlBase}")
        private String docUrlBase;

    private final ConvocatoriaCasRepository repo;
    private final ConvocatoriaCasMapper mapper;
    private final ConvocatoriaDocumentoRepository docRepo;

    private final String carpetaDestino = "documentos/cas/";

    public ConvocatoriaCasServiceImpl(ConvocatoriaCasRepository repo,
                                      ConvocatoriaCasMapper mapper,
                                      ConvocatoriaDocumentoRepository docRepo) {
        this.repo = repo;
        this.mapper = mapper;
        this.docRepo = docRepo;
    }

    @Override
    @Transactional
    public ConvocatoriaCasResponse create(ConvocatoriaCasRequest request) {
        if (repo.existsByCodigo(request.getCodigo())) {
            throw new IllegalArgumentException("Ya existe una convocatoria con el código: " + request.getCodigo());
        }
        ConvocatoriaCasEntity entity = mapper.toEntity(request);
        entity.setEstado(true);
        entity.setResponsable("Admin");
        entity.setFechaCreacion(java.time.LocalDate.now());

        // Por cada campo archivo conocido, crea/actualiza doc
        Map<DocumentoTipo, MultipartFile> archivos = new LinkedHashMap<>();
        archivos.put(DocumentoTipo.BASES, request.getBases());
        archivos.put(DocumentoTipo.ANEXOS, request.getAnexos());
        archivos.put(DocumentoTipo.COMUNICADO1, request.getComunicado1());
        archivos.put(DocumentoTipo.COMUNICADO2, request.getComunicado2());
        archivos.put(DocumentoTipo.EVAL_CURRICULAR, request.getEvaluacionCurricular());
        archivos.put(DocumentoTipo.EVAL_ENTREVISTA, request.getEvaluacionEntrevista());
        archivos.put(DocumentoTipo.ABSOLUCION_RECLAMOS, request.getAbsolucionReclamos());
        archivos.put(DocumentoTipo.RESULTADOS_FINALES, request.getResultadosFinales());


        for (var entry : archivos.entrySet()) {
            MultipartFile mf = entry.getValue();
            if (mf != null && !mf.isEmpty()) {
                ConvocatoriaDocumentoEntity doc = new ConvocatoriaDocumentoEntity();
                doc.setConvocatoria(entity);
                doc.setTipo(entry.getKey());
                doc.setUrl(saveFile(mf));
                doc.setHabilitado(false);
                entity.getDocumentos().add(doc);
            }
        }

        if (request.getPostulacion() != null && !request.getPostulacion().isBlank()) {
            ConvocatoriaDocumentoEntity docPost = new ConvocatoriaDocumentoEntity();
            docPost.setConvocatoria(entity);
            docPost.setTipo(DocumentoTipo.POSTULACION);
            docPost.setUrl(normalizeUrl(request.getPostulacion()));
            docPost.setTitulo("Postulación");
            docPost.setHabilitado(false);
            // opcional: docPost.setOrden(10);
            entity.getDocumentos().add(docPost);
        }

        // Aplica configuración (habilitado/titulo/descripcion/orden)
        applyConfig(entity, request.getConfig());

        // Persistir
        ConvocatoriaCasEntity saved = repo.save(entity);

        // Construir response
        ConvocatoriaCasResponse res = mapper.toResponse(saved);
        // mantener compatibilidad si aún usas ...Url (opcional)
        res.setDocumentos(mapDocs(saved.getDocumentos()));
        //res = withPublicUrls(res);
        return res;
    }

    @Override
    public List<ConvocatoriaCasResponse> getAll() {
        return repo.findAll().stream()
                .map(mapper::toResponse)
                .peek(r -> {
// cargar documentos vía repo hijo (opcional) o por fetch si LAZY/EAGER
                })
                .map(r -> {
                    var e = repo.findById(r.getId()).orElseThrow();
                    r.setDocumentos(mapDocs(e.getDocumentos()));
// si mantienes ...Url, puedes seguir decorándolas, pero ideal migrar al arreglo
                    return r;
                })
                .toList();
    }

    @Override
    public ConvocatoriaCasResponse findById(Integer id) {
        ConvocatoriaCasEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada con ID: " + id));
        ConvocatoriaCasResponse r = mapper.toResponse(entity);
        r.setDocumentos(mapDocs(entity.getDocumentos()));
        return r;
    }

    @Override
    @Transactional
    public ConvocatoriaCasResponse update(Integer id, ConvocatoriaCasRequest request) {
        ConvocatoriaCasEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada con ID: " + id));


        mapper.updateFromRequest(request, entity); // actualiza campos simples


    // indexar documentos existentes por tipo
        var byTipo = entity.getDocumentos().stream()
                .collect(java.util.stream.Collectors.toMap(d -> d.getTipo(), d -> d, (a,b)->a));


        Map<DocumentoTipo, MultipartFile> archivos = new java.util.LinkedHashMap<>();
        archivos.put(DocumentoTipo.BASES, request.getBases());
        archivos.put(DocumentoTipo.ANEXOS, request.getAnexos());
        archivos.put(DocumentoTipo.COMUNICADO1, request.getComunicado1());
        archivos.put(DocumentoTipo.COMUNICADO2, request.getComunicado2());
        archivos.put(DocumentoTipo.EVAL_CURRICULAR, request.getEvaluacionCurricular());
        archivos.put(DocumentoTipo.EVAL_ENTREVISTA, request.getEvaluacionEntrevista());
        archivos.put(DocumentoTipo.ABSOLUCION_RECLAMOS, request.getAbsolucionReclamos());
        archivos.put(DocumentoTipo.RESULTADOS_FINALES, request.getResultadosFinales());


        for (var entry : archivos.entrySet()) {
            DocumentoTipo tipo = entry.getKey();
            MultipartFile mf = entry.getValue();
            if (mf != null && !mf.isEmpty()) {
                ConvocatoriaDocumentoEntity doc = byTipo.get(tipo);
                if (doc == null) {
                    doc = new ConvocatoriaDocumentoEntity();
                    doc.setConvocatoria(entity);
                    doc.setHabilitado(false);
                    doc.setTipo(tipo);
                    entity.getDocumentos().add(doc);
                    byTipo.put(tipo, doc);
                }
                replaceFile(doc, mf); // elimina anterior y guarda nuevo
                // no cambiamos habilitado/título/orden aquí (lo hará config si llega)
            }
        }

        if (request.getPostulacion() != null) {
            String nuevaUrl = normalizeUrl(request.getPostulacion());

            // Si no existe el documento POSTULACION, lo creamos (manteniendo coherencia con create: habilitado=false por defecto)
            ConvocatoriaDocumentoEntity docPost = byTipo.get(DocumentoTipo.POSTULACION);
            if (docPost == null) {
                docPost = new ConvocatoriaDocumentoEntity();
                docPost.setConvocatoria(entity);
                docPost.setTipo(DocumentoTipo.POSTULACION);
                //docPost.setTitulo("Postulación");
                docPost.setHabilitado(false);
                entity.getDocumentos().add(docPost);
                byTipo.put(DocumentoTipo.POSTULACION, docPost);
            }

            // Solo cambiamos la URL. No tocamos habilitado/título/orden.
            if (!nuevaUrl.isBlank()) {
                docPost.setUrl(nuevaUrl);           // externa: se devolverá tal cual por toPublicUrl()
            }
            // Si viene blank ("") NO hacemos nada para no alterar el estado actual.
            // Si quisieras limpiar la URL cuando venga blank, reemplaza por:
            // else { docPost.setUrl(null); }
        }

        // Aplica configuración
        applyConfig(entity, request.getConfig());

        entity.setResponsable("Admin Update");
        entity.setFechaModificacion(LocalDate.now());

        ConvocatoriaCasEntity updated = repo.save(entity);

        ConvocatoriaCasResponse res = mapper.toResponse(updated);
        res.setDocumentos(mapDocs(updated.getDocumentos()));
        //res = withPublicUrls(res);
        return res;
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        ConvocatoriaCasEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada con ID: " + id));


        for (var d : entity.getDocumentos()) {
            FileUploadUtil.eliminarArchivo(carpetaDestino, d.getUrl());
        }
// Por orphanRemoval=true, al hacer repo.deleteById se eliminan hijos
        repo.deleteById(id);
    }

    @Override
    @Transactional
    public ConvocatoriaCasResponse updateDocsConfig(Integer id, List<DocumentoConfigRequest> config){
        ConvocatoriaCasEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convocatoria no encontrada con ID: " + id));
        applyConfig(entity, config); // la misma del servicio
        entity.setFechaModificacion(java.time.LocalDate.now());
        ConvocatoriaCasEntity saved = repo.save(entity);


        ConvocatoriaCasResponse r = mapper.toResponse(saved);
        r.setDocumentos(mapDocs(saved.getDocumentos()));
        //r = withPublicUrls(r);
        return r;
    }

    private String guardarYUrl(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        String nombre = FileUploadUtil.guardarArchivo(file, carpetaDestino);
        return "cas/" + nombre;
    }

    private String reemplazarSiHayNuevo(String urlAnterior, MultipartFile nuevo) {
        if (nuevo == null || nuevo.isEmpty()) return urlAnterior;
        if (urlAnterior != null) {
            FileUploadUtil.eliminarArchivo(carpetaDestino, urlAnterior);
        }
        String nombre = FileUploadUtil.guardarArchivo(nuevo, carpetaDestino);
        return "cas/" + nombre;
    }

    private String toPublicUrl(String path) {
        if (path == null || path.isBlank()) return null;
        if (path.startsWith("http://") || path.startsWith("https://")) return path;
        String base = docUrlBase.endsWith("/") ? docUrlBase : docUrlBase + "/";
        return base + path;
    }

    /*
    private ConvocatoriaCasResponse withPublicUrls(ConvocatoriaCasResponse r) {
        if (r == null) return null;
        r.setBasesUrl(                toPublicUrl(r.getBasesUrl()));
        r.setAnexosUrl(               toPublicUrl(r.getAnexosUrl()));
        r.setComunicado1Url(          toPublicUrl(r.getComunicado1Url()));
        r.setComunicado2Url(          toPublicUrl(r.getComunicado2Url()));
        r.setEvaluacionCurricularUrl( toPublicUrl(r.getEvaluacionCurricularUrl()));
        r.setEvaluacionEntrevistaUrl( toPublicUrl(r.getEvaluacionEntrevistaUrl()));
        r.setAbsolucionReclamosUrl(   toPublicUrl(r.getAbsolucionReclamosUrl()));
        r.setResultadosFinalesUrl(    toPublicUrl(r.getResultadosFinalesUrl()));
        return r;
    }

     */

    private DocumentoItemResponse toDocResponse(ConvocatoriaDocumentoEntity e) {
        DocumentoItemResponse r = new DocumentoItemResponse();
        r.setTipo(e.getTipo().name());
        r.setTitulo(e.getTitulo());
        r.setDescripcion(e.getDescripcion());
        r.setUrl(toPublicUrl(e.getUrl()));
        r.setHabilitado(Boolean.TRUE.equals(e.getHabilitado()));
        r.setOrden(e.getOrden());
        return r;
    }


    private List<DocumentoItemResponse> mapDocs(List<ConvocatoriaDocumentoEntity> docs) {
        return docs == null ? java.util.Collections.emptyList() :
                docs.stream()
                        .sorted(java.util.Comparator.comparing(d -> d.getOrden() == null ? 0 : d.getOrden()))
                        .map(this::toDocResponse)
                        .toList();
    }

    private void applyConfig(ConvocatoriaCasEntity entity, List<DocumentoConfigRequest> cfgList) {
        if (cfgList == null || cfgList.isEmpty()) return;

        var byTipo = entity.getDocumentos().stream()
                .collect(java.util.stream.Collectors.toMap(d -> d.getTipo().name(), d -> d, (a,b)->a));

        for (DocumentoConfigRequest c : cfgList) {
            if (c.getTipo() == null) continue;
            DocumentoTipo tipo;
            try {
                tipo = DocumentoTipo.valueOf(c.getTipo());
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Tipo de documento inválido: " + c.getTipo());
            }
            var doc = byTipo.get(tipo.name());
            if (doc == null) {
                doc = new ConvocatoriaDocumentoEntity();
                doc.setConvocatoria(entity);
                doc.setTipo(tipo);
                entity.getDocumentos().add(doc);
                byTipo.put(tipo.name(), doc);
            }
            if (c.getHabilitado() != null) doc.setHabilitado(c.getHabilitado());
            if (c.getTitulo() != null) doc.setTitulo(c.getTitulo());
            if (c.getDescripcion() != null) doc.setDescripcion(c.getDescripcion());
            if (c.getOrden() != null) doc.setOrden(c.getOrden());
        }
    }


    private String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;
        String nombre = FileUploadUtil.guardarArchivo(file, carpetaDestino);
        return "cas/" + nombre;
    }


    private void replaceFile(ConvocatoriaDocumentoEntity doc, MultipartFile nuevo) {
        if (nuevo == null || nuevo.isEmpty()) return; // no cambia
        if (doc.getUrl() != null) {
            FileUploadUtil.eliminarArchivo(carpetaDestino, doc.getUrl());
        }
        doc.setUrl(saveFile(nuevo));
    }

    private String normalizeUrl(String url) {
        return url == null ? null : url.trim();
    }

}
