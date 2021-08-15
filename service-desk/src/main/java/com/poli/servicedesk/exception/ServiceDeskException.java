package com.poli.servicedesk.exception;

import com.poli.servicedesk.enums.ServiceDeskErrorEnum;

public class ServiceDeskException extends RuntimeException{

    public ServiceDeskException(ServiceDeskErrorEnum error) {
        super(error.name());
    }
}
