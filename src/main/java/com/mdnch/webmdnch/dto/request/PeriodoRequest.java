package com.mdnch.webmdnch.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class PeriodoRequest {
    private Integer transparenciaId;
    private String año;
    private MultipartFile trimestre1;
    private MultipartFile trimestre2;
    private MultipartFile trimestre3;
    private MultipartFile trimestre4;

    public Integer getTransparenciaId() {
        return transparenciaId;
    }

    public void setTransparenciaId(Integer transparenciaId) {
        this.transparenciaId = transparenciaId;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public MultipartFile getTrimestre1() {
        return trimestre1;
    }

    public void setTrimestre1(MultipartFile trimestre1) {
        this.trimestre1 = trimestre1;
    }

    public MultipartFile getTrimestre2() {
        return trimestre2;
    }

    public void setTrimestre2(MultipartFile trimestre2) {
        this.trimestre2 = trimestre2;
    }

    public MultipartFile getTrimestre3() {
        return trimestre3;
    }

    public void setTrimestre3(MultipartFile trimestre3) {
        this.trimestre3 = trimestre3;
    }

    public MultipartFile getTrimestre4() {
        return trimestre4;
    }

    public void setTrimestre4(MultipartFile trimestre4) {
        this.trimestre4 = trimestre4;
    }
}
