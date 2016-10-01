
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.employeesdirectory20150916145522.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "StatusScreen1DSService" REST Service implementation
 */
public class StatusScreen1DSService extends RestService<StatusScreen1DSServiceRest>{

    public static StatusScreen1DSService getInstance(){
          return new StatusScreen1DSService();
    }

    private StatusScreen1DSService() {
        super(StatusScreen1DSServiceRest.class);

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

