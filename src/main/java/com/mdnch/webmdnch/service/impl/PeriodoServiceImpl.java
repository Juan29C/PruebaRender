package com.mdnch.webmdnch.service.impl;

import com.mdnch.webmdnch.dto.request.PeriodoRequest;
import com.mdnch.webmdnch.dto.response.PeriodoResponse;
import com.mdnch.webmdnch.entity.PeriodoEntity;
import com.mdnch.webmdnch.entity.TransparenciaEntity;
import com.mdnch.webmdnch.mapper.PeriodoMapper;
import com.mdnch.webmdnch.repository.PeriodoRepository;
import com.mdnch.webmdnch.repository.TransparenciaRepository;
import com.mdnch.webmdnch.service.PeriodoService;
import com.mdnch.webmdnch.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeriodoServiceImpl implements PeriodoService {

    @Value("${documentos.urlBase}")
    private String urlBase;

    @Autowired
    private PeriodoRepository periodoRepository;

    @Autowired
    private TransparenciaRepository transparenciaRepository;

    @Autowired
    private PeriodoMapper periodoMapper;


    @Override
    public PeriodoResponse createPeriodo(PeriodoRequest request) {
        TransparenciaEntity transparencia = transparenciaRepository.findById(request.getTransparenciaId())
                .orElseThrow(() -> new IllegalArgumentException("Transparencia no encontrada con ID: " + request.getTransparenciaId()));

        String carpetaDestino = "documentos/transparencia/" + request.getTransparenciaId() + "/";

        // Manejo seguro de archivos
        String nombreArchivo1 = (request.getTrimestre1() != null && !request.getTrimestre1().isEmpty())
                ? FileUploadUtil.guardarArchivo(request.getTrimestre1(), carpetaDestino)
                : null;

        String nombreArchivo2 = (request.getTrimestre2() != null && !request.getTrimestre2().isEmpty())
                ? FileUploadUtil.guardarArchivo(request.getTrimestre2(), carpetaDestino)
                : null;

        String nombreArchivo3 = (request.getTrimestre3() != null && !request.getTrimestre3().isEmpty())
                ? FileUploadUtil.guardarArchivo(request.getTrimestre3(), carpetaDestino)
                : null;

        String nombreArchivo4 = (request.getTrimestre4() != null && !request.getTrimestre4().isEmpty())
                ? FileUploadUtil.guardarArchivo(request.getTrimestre4(), carpetaDestino)
                : null;

        PeriodoEntity entity = periodoMapper.toEntity(request);
        entity.setTrimestre1(nombreArchivo1);
        entity.setTrimestre2(nombreArchivo2);
        entity.setTrimestre3(nombreArchivo3);
        entity.setTrimestre4(nombreArchivo4);
        entity.setResponsable("ssj");
        entity.setTransparencia(transparencia);
        entity.setFechaCreacion(LocalDate.now());

        PeriodoEntity saved = periodoRepository.save(entity);
        return periodoMapper.toResponse(saved);
    }

    @Override
    public List<PeriodoResponse> getAllPeriodos() {
        return periodoRepository.findAll()
                .stream()
                .map(this::construirResponseConImagen)
                .collect(Collectors.toList());
    }

    @Override
    public PeriodoResponse getByIdPeriodo(Integer periodoId) {
        PeriodoEntity entity = periodoRepository.findById(periodoId)
                .orElseThrow(() -> new IllegalArgumentException("Periodo no encontrado con ID: " + periodoId));
        return construirResponseConImagen(entity);
    }

    @Override
    public PeriodoResponse updatePeriodo(Integer id, PeriodoRequest request) {
        PeriodoEntity entity = periodoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado con ID: " + id));

        periodoMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo1 = request.getTrimestre1();
        MultipartFile archivo2 = request.getTrimestre2();
        MultipartFile archivo3 = request.getTrimestre3();
        MultipartFile archivo4 = request.getTrimestre4();
        if(archivo1 != null && !archivo1.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo1,
                    carpetaDestino,
                    entity.getTrimestre1()
            );
            entity.setTrimestre1(nombreArchivo);
        }

        if(archivo2 != null && !archivo2.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo2,
                    carpetaDestino,
                    entity.getTrimestre2()
            );
            entity.setTrimestre2(nombreArchivo);
        }

        if(archivo3 != null && !archivo3.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo3,
                    carpetaDestino,
                    entity.getTrimestre3()
            );
            entity.setTrimestre3(nombreArchivo);
        }

        if(archivo4 != null && !archivo4.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo4,
                    carpetaDestino,
                    entity.getTrimestre4()
            );
            entity.setTrimestre4(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("young flex");
        PeriodoEntity saved = periodoRepository.saveAndFlush(entity);
        return construirResponseConImagen(saved);
    }

    @Override
    public PeriodoResponse editPeriodo(Integer periodoId, PeriodoRequest request) {
        PeriodoEntity entity = periodoRepository.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado con ID: " + periodoId));
        periodoMapper.updateEntityFromRequest(request, entity);

        MultipartFile archivo1 = request.getTrimestre1();
        MultipartFile archivo2 = request.getTrimestre2();
        MultipartFile archivo3 = request.getTrimestre3();
        MultipartFile archivo4 = request.getTrimestre4();

        if(archivo1 != null && !archivo1.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo1,
                    carpetaDestino,
                    entity.getTrimestre1()
            );
            entity.setTrimestre1(nombreArchivo);
        }

        if(archivo2 != null && !archivo2.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo2,
                    carpetaDestino,
                    entity.getTrimestre2()
            );
            entity.setTrimestre2(nombreArchivo);
        }

        if(archivo3 != null && !archivo3.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo3,
                    carpetaDestino,
                    entity.getTrimestre3()
            );
            entity.setTrimestre3(nombreArchivo);
        }

        if(archivo4 != null && !archivo4.isEmpty()) {
            String carpetaDestino = "documentos/transparencia/" + entity.getTransparencia().getTransparenciaId() + "/";
            String nombreArchivo = FileUploadUtil.guardarArchivo(
                    archivo4,
                    carpetaDestino,
                    entity.getTrimestre4()
            );
            entity.setTrimestre4(nombreArchivo);
        }

        entity.setFechaModificacion(LocalDate.now());
        entity.setResponsable("jonz");

        PeriodoEntity saved = periodoRepository.saveAndFlush(entity);
        return construirResponseConImagen(saved);

    }

    @Override
    public void deletePeriodo(Integer periodoId) {
        PeriodoEntity entity = periodoRepository.findById(periodoId)
                .orElseThrow(() -> new RuntimeException("Periodo no encontrado con ID: " + periodoId));
        periodoRepository.delete(entity);
    }


    private PeriodoResponse construirResponseConImagen(PeriodoEntity entity) {
        PeriodoResponse response = periodoMapper.toResponse(entity);

        if (entity.getTrimestre1() != null) {
            response.setTrimestre1(urlBase + "transparencia/" + entity.getTransparencia().getTransparenciaId()+ "/" + entity.getTrimestre1());
        }
        if (entity.getTrimestre2() != null) {
            response.setTrimestre2(urlBase + "transparencia/" + entity.getTransparencia().getTransparenciaId() + "/" + entity.getTrimestre2());
        }
        if (entity.getTrimestre3() != null) {
            response.setTrimestre3(urlBase + "transparencia/" + entity.getTransparencia().getTransparenciaId() + "/" + entity.getTrimestre3());
        }
        if (entity.getTrimestre4() != null) {
            response.setTrimestre4(urlBase + "transparencia/" + entity.getTransparencia().getTransparenciaId() + "/" + entity.getTrimestre4());
        }
        return response;
    }
}
