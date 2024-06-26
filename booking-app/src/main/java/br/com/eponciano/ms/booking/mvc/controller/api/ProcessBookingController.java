package br.com.eponciano.ms.booking.mvc.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.eponciano.ms.booking.commons.model.dto.ProcesssBookingDTO;
import br.com.eponciano.ms.booking.mvc.jms.ProcessBookingPublish;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller	
@RequiredArgsConstructor
@RequestMapping("/api/booking")
public class ProcessBookingController {

    @Autowired
    private ProcessBookingPublish publish;
	
    @PostMapping("/process" )
    public ResponseEntity<?> editUser(@RequestBody ProcesssBookingDTO processDTO) {
    	 try {
    		 publish.process(processDTO);
            return ResponseEntity.ok("Sucesso");
        } catch (Exception e) {
        	log.error("Erro ao processar fila",e);
            return ResponseEntity.badRequest().build();
        }
    }
    
}
