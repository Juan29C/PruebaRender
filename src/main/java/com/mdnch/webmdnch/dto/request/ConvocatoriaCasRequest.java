package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class ConvocatoriaCasRequest {
    private String codigo;
    private String convocatoria;
    private String area;
    private Integer vacantes;
    private String postulacion;

    private MultipartFile bases;
    private MultipartFile anexos;
    private MultipartFile comunicado1;
    private MultipartFile comunicado2;
    private MultipartFile evaluacionCurricular;
    private MultipartFile evaluacionEntrevista;
    private MultipartFile absolucionReclamos;
    private MultipartFile resultadosFinales;

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

    public MultipartFile getBases() {
        return bases;
    }

    public void setBases(MultipartFile bases) {
        this.bases = bases;
    }

    public MultipartFile getAnexos() {
        return anexos;
    }

    public void setAnexos(MultipartFile anexos) {
        this.anexos = anexos;
    }

    public MultipartFile getComunicado1() {
        return comunicado1;
    }

    public void setComunicado1(MultipartFile comunicado1) {
        this.comunicado1 = comunicado1;
    }

    public MultipartFile getComunicado2() {
        return comunicado2;
    }

    public void setComunicado2(MultipartFile comunicado2) {
        this.comunicado2 = comunicado2;
    }

    public MultipartFile getEvaluacionCurricular() {
        return evaluacionCurricular;
    }

    public void setEvaluacionCurricular(MultipartFile evaluacionCurricular) {
        this.evaluacionCurricular = evaluacionCurricular;
    }

    public MultipartFile getEvaluacionEntrevista() {
        return evaluacionEntrevista;
    }

    public void setEvaluacionEntrevista(MultipartFile evaluacionEntrevista) {
        this.evaluacionEntrevista = evaluacionEntrevista;
    }

    public MultipartFile getAbsolucionReclamos() {
        return absolucionReclamos;
    }

    public void setAbsolucionReclamos(MultipartFile absolucionReclamos) {
        this.absolucionReclamos = absolucionReclamos;
    }

    public MultipartFile getResultadosFinales() {
        return resultadosFinales;
    }

    public void setResultadosFinales(MultipartFile resultadosFinales) {
        this.resultadosFinales = resultadosFinales;
    }
}
