package com.xc.service.InitServiceFactory;

public interface ServiceFactory {

    public AppService getServiceByServiceType(ServiceType serviceType);

    public void setService(ServiceType serviceType,AppService appService);
}
