package com.mdnch.webmdnch.entity;


import com.mdnch.webmdnch.dto.enums.DocumentoTipo;
import jakarta.persistence.*;

@Entity
@Table(name = "convocatoria_documento",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_convocatoria", "tipo"}))
public class ConvocatoriaDocumentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_convocatoria", nullable = false)
    private ConvocatoriaCasEntity convocatoria;


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 40)
    private DocumentoTipo tipo;


    @Column(name = "titulo", length = 200)
    private String titulo;


    @Column(name = "descripcion", length = 500)
    private String descripcion;


    // Ruta relativa almacenada en BD, p.ej. "cas/1695412400_bases.pdf"
    @Column(name = "url", length = 500)
    private String url;


    @Column(name = "habilitado", nullable = false)
    private Boolean habilitado = Boolean.TRUE;


    @Column(name = "orden")
    private Integer orden;


    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public ConvocatoriaCasEntity getConvocatoria() { return convocatoria; }
    public void setConvocatoria(ConvocatoriaCasEntity convocatoria) { this.convocatoria = convocatoria; }


    public DocumentoTipo getTipo() { return tipo; }
    public void setTipo(DocumentoTipo tipo) { this.tipo = tipo; }


    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }


    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }


    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }


    public Boolean getHabilitado() { return habilitado; }
    public void setHabilitado(Boolean habilitado) { this.habilitado = habilitado; }


    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }
}