package com.example.algamoney.api.event;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse responde;
    private Long codigo;

    public RecursoCriadoEvent(Object source, HttpServletResponse responde, Long codigo){
        super(source);
        this.responde = responde;
        this.codigo = codigo;
    }

    public HttpServletResponse getResponde() {
        return responde;
    }

    public Long getCodigo() {
        return codigo;
    }
}
