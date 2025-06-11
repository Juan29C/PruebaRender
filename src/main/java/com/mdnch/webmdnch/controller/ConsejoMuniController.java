package com.mdnch.webmdnch.controller;

import com.mdnch.webmdnch.dto.ConsejoMuniDto;
import com.mdnch.webmdnch.dto.response.ResponseBase;
import com.mdnch.webmdnch.service.impl.ConsejoMuniImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authentication")
public class ConsejoMuniController {

    @Autowired
    ConsejoMuniImpl consejoMuniService;

    @PostMapping("/consejo-muni/registrar")
    public ResponseEntity<ResponseBase<ConsejoMuniDto>> registrarConsejoMuni(@Valid @RequestBody ConsejoMuniDto consejoMuniDto) {
        consejoMuniService.registrarConsejoMuni(consejoMuniDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBase<>(true, "Consejo Municipal registrado con éxito", consejoMuniDto));
    }

    @GetMapping("/consejo-muni")
    public ResponseEntity<ResponseBase<List<ConsejoMuniDto>>>listarConsejoMuni(){
        List<ConsejoMuniDto> consejos = consejoMuniService.obtenerConsejosMuni();
        return ResponseEntity.ok(new ResponseBase<>(true, "Consejos Municipales obtenidos con éxito", consejos));
    }

    @GetMapping("/consejo-muni/{id}")
    public ResponseEntity<ResponseBase<ConsejoMuniDto>> obtenerConsejoMuniPorId(@PathVariable Integer id) {
        ConsejoMuniDto consejoMuni = consejoMuniService.obtenerConsejoMuniPorId(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Consejo Municipal encontrado", consejoMuni));
    }

    @PutMapping("/consejo-muni/{id}")
    public ResponseEntity<ResponseBase<ConsejoMuniDto>> actualizarConsejoMuni(
            @PathVariable Integer id,
            @Valid @RequestBody ConsejoMuniDto consejoMuniDto) {
        consejoMuniService.actualizarConsejoMuni(id, consejoMuniDto);
        return ResponseEntity.ok(new ResponseBase<>(true, "Consejo Municipal actualizado con éxito", consejoMuniDto));
    }

    @DeleteMapping("/consejo-muni/{id}")
    public ResponseEntity<ResponseBase<Void>> eliminarConsejoMuni(@PathVariable Integer id) {
        consejoMuniService.eliminarConsejoMuni(id);
        return ResponseEntity.ok(new ResponseBase<>(true, "Consejo Municipal eliminado con éxito", null));
    }

}
