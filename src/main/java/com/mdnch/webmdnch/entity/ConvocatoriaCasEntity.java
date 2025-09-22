package com.mdnch.webmdnch.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "convocatoria_cas")
public class ConvocatoriaCasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_convocatoria")
    private Integer id;

    @Column(name = "codigo", length = 60, nullable = false, unique = true)
    private String codigo;

    @Column(name = "convocatoria", length = 200, nullable = false)
    private String convocatoria;

    @Column(name = "area", length = 120, nullable = false)
    private String area;

    @Column(name = "vacantes")
    private Integer vacantes;

    @Column(name = "postulacion", length = 500)
    private String postulacion; // enlace externo

    @Column(name = "bases_url", length = 500)                 private String basesUrl;
    @Column(name = "anexos_url", length = 500)                private String anexosUrl;
    @Column(name = "comunicado1_url", length = 500)           private String comunicado1Url;
    @Column(name = "comunicado2_url", length = 500)           private String comunicado2Url;
    @Column(name = "eval_curricular_url", length = 500)       private String evaluacionCurricularUrl;
    @Column(name = "eval_entrevista_url", length = 500)       private String evaluacionEntrevistaUrl;
    @Column(name = "absolucion_reclamos_url", length = 500)   private String absolucionReclamosUrl;
    @Column(name = "resultados_finales_url", length = 500)    private String resultadosFinalesUrl;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "responsable")
    private String responsable;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

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
