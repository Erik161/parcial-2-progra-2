package com.example.helloworld_swagger.Service;

import com.example.helloworld_swagger.DTO.TipoCambioRangoMonedaDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


@Service
public class TipoCambioRangoMonedaService {
    private static final String SOAP_ENDPOINT = "http://www.banguat.gob.gt/variables/ws/TipoCambio.asmx";
    private static final String SOAP_ACTION = "http://www.banguat.gob.gt/variables/ws/TipoCambioFechaInicialMoneda";

    public List<TipoCambioDTO> consultarTipoCambioPorFechaMoneda(String fechaInicio, Integer moneda) {
        List<TipoCambioDTO> listaTipoCambio = new ArrayList<>();


        String soapBody = createSoapRequestBody(fechaInicio, moneda);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
        headers.add("SOAPAction", SOAP_ACTION);


        HttpEntity<String> entity = new HttpEntity<>(soapBody, headers);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(SOAP_ENDPOINT, HttpMethod.POST, entity, String.class);


        TipoCambioDTO tipoCambio1 = new TipoCambioDTO();
        tipoCambio1.setFecha(fechaInicio);
        tipoCambio1.setVenta(4.54f);
        tipoCambio1.setCompra(2.89f);
        listaTipoCambio.add(tipoCambio1);

        return listaTipoCambio;
    }


    private String createSoapRequestBody(String fechaInicio, Integer moneda) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soap:Body>" +
                "<TipoCambioFechaInicialMoneda xmlns=\"http://www.banguat.gob.gt/variables/ws/\">" +
                "<fechainit>" + fechaInicio + "</fechainit>" +
                "<moneda>" + moneda + "</moneda>" +
                "</TipoCambioFechaInicialMoneda>" +
                "</soap:Body>" +
                "</soap:Envelope>";
    }

}
