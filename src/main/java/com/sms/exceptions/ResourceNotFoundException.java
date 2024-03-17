package com.sms.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String resourceId;
    int id;

    public ResourceNotFoundException(String resourceName, String resourceId, int id){
        super(String.format("%s with %s : %d does not exist", resourceName, resourceId, id));
        this.id = id;
        this.resourceId = resourceId;
        this.resourceName = resourceName;
    }
}
