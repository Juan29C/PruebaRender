package com.mdnch.webmdnch.dto.request;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class NoticiasFormRequest {
    private String titulo;
    private String categoria;
    private String descripcion;
    private MultipartFile imagen;
}
