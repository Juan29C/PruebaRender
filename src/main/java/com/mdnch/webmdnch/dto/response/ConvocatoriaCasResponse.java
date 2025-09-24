package com.mdnch.webmdnch.dto.response;

import java.time.LocalDate;
import java.util.List;

public class ConvocatoriaCasResponse {
    private Integer id;
    private String codigo;
    private String convocatoria;
    private String area;
    private Integer vacantes;
    private String postulacion;

    private String basesUrl;
    private String anexosUrl;
    private String comunicado1Url;
    private String comunicado2Url;
    private String evaluacionCurricularUrl;
    private String evaluacionEntrevistaUrl;
    private String absolucionReclamosUrl;
    private String resultadosFinalesUrl;

    private Boolean estado;
    private String responsable;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    private List<DocumentoItemResponse> documentos;


    public List<DocumentoItemResponse> getDocumentos() { return documentos; }
    public void setDocumentos(List<DocumentoItemResponse> documentos) { this.documentos = documentos; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(String convocatoria) {
        this.convocatoria = convocatoria;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getVacantes() {
        return vacantes;
    }

    public void setVacantes(Integer vacantes) {
        this.vacantes = vacantes;
    }

    public String getPostulacion() {
        return postulacion;
    }

    public void setPostulacion(String postulacion) {
        this.postulacion = postulacion;
    }

    public String getBasesUrl() {
        return basesUrl;
    }

    public void setBasesUrl(String basesUrl) {
        this.basesUrl = basesUrl;
    }

    public String getAnexosUrl() {
        return anexosUrl;
    }

    public void setAnexosUrl(String anexosUrl) {
        this.anexosUrl = anexosUrl;
    }

    public String getComunicado1Url() {
        return comunicado1Url;
    }

    public void setComunicado1Url(String comunicado1Url) {
        this.comunicado1Url = comunicado1Url;
    }

    public String getComunicado2Url() {
        return comunicado2Url;
    }

    public void setComunicado2Url(String comunicado2Url) {
        this.comunicado2Url = comunicado2Url;
    }

    public String getEvaluacionCurricularUrl() {
        return evaluacionCurricularUrl;
    }

    public void setEvaluacionCurricularUrl(String evaluacionCurricularUrl) {
        this.evaluacionCurricularUrl = evaluacionCurricularUrl;
    }

    public String getEvaluacionEntrevistaUrl() {
        return evaluacionEntrevistaUrl;
    }

    public void setEvaluacionEntrevistaUrl(String evaluacionEntrevistaUrl) {
        this.evaluacionEntrevistaUrl = evaluacionEntrevistaUrl;
    }

    public String getAbsolucionReclamosUrl() {
        return absolucionReclamosUrl;
    }

    public void setAbsolucionReclamosUrl(String absolucionReclamosUrl) {
        this.absolucionReclamosUrl = absolucionReclamosUrl;
    }

    public String getResultadosFinalesUrl() {
        return resultadosFinalesUrl;
    }

    public void setResultadosFinalesUrl(String resultadosFinalesUrl) {
        this.resultadosFinalesUrl = resultadosFinalesUrl;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
}
