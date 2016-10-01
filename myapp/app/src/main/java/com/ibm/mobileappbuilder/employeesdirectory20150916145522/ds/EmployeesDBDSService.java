
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "EmployeesDBDSService" REST Service implementation
 */
public class EmployeesDBDSService extends RestService<EmployeesDBDSServiceRest>{

    public static EmployeesDBDSService getInstance(){
          return new EmployeesDBDSService();
    }

    private EmployeesDBDSService() {
        super(EmployeesDBDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "FHKABSkx";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57ef4a439d17e00300d4c404",
                path,
                "apikey=FHKABSkx");
    }

}

