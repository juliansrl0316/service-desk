package com.poli.servicedesk.util;

import com.poli.servicedesk.enums.RolEnum;
import com.poli.servicedesk.enums.ServiceDeskErrorEnum;
import com.poli.servicedesk.enums.StatusEnum;
import com.poli.servicedesk.exception.ServiceDeskException;
import com.poli.servicedesk.model.Employee;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Service
public class ManageStatus {

    public ManageStatus() {
    }

    public Employee manageRol(Employee model) {
        return model.toBuilder()
                .rolId(RolEnum.nameFromId(model.getRolId()))
                .build();
    }

    public <T> Object setStatusName(T objectSetStatus){

        var objClass = objectSetStatus.getClass();
        Field[] fields = objClass.getDeclaredFields();
        Object status;

        try {
            for (Field field : fields) {
                Object value = getValue(field, objectSetStatus);
                if(field.getName().equals("status")){
                    status = StatusEnum.nameFromId(value.toString());
                    var method =  objectSetStatus.getClass().getMethod("setStatus", status.getClass());
                    method.invoke(objectSetStatus, status);
                }
            }
        }catch (Exception e){
            throw new ServiceDeskException(ServiceDeskErrorEnum.ERROR_CONVERTING_STATUS);
        }

        return objectSetStatus;
    }


    private static <T> Object getValue(Field field, T object) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        PropertyDescriptor propertyDescriptor;

        propertyDescriptor = new PropertyDescriptor(field.getName(), object.getClass());
        var readMethod = propertyDescriptor.getReadMethod();
        return readMethod.invoke(object);

    }

}
