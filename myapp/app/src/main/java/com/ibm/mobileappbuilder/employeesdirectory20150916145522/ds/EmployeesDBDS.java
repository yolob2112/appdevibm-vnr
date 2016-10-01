

package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "EmployeesDBDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class EmployeesDBDS extends AppNowDatasource<EmployeesDBDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private EmployeesDBDSService service;

    public static EmployeesDBDS getInstance(SearchOptions searchOptions){
        return new EmployeesDBDS(searchOptions);
    }

    private EmployeesDBDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = EmployeesDBDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<EmployeesDBDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<EmployeesDBDSItem>>() {
                @Override
                public void onSuccess(List<EmployeesDBDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new EmployeesDBDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getEmployeesDBDSItemById(id, new Callback<EmployeesDBDSItem>() {
                @Override
                public void success(EmployeesDBDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<EmployeesDBDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<EmployeesDBDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryEmployeesDBDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<EmployeesDBDSItem>>() {
            @Override
            public void success(List<EmployeesDBDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"name", "lastname", "role", "email", "phone"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(EmployeesDBDSItem item, Listener<EmployeesDBDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createEmployeesDBDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createEmployeesDBDSItem(item, callbackFor(listener));
        
    }

    private Callback<EmployeesDBDSItem> callbackFor(final Listener<EmployeesDBDSItem> listener) {
      return new Callback<EmployeesDBDSItem>() {
          @Override
          public void success(EmployeesDBDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(EmployeesDBDSItem item, Listener<EmployeesDBDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateEmployeesDBDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateEmployeesDBDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(EmployeesDBDSItem item, final Listener<EmployeesDBDSItem> listener) {
                service.getServiceProxy().deleteEmployeesDBDSItemById(item.getIdentifiableId(), new Callback<EmployeesDBDSItem>() {
            @Override
            public void success(EmployeesDBDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<EmployeesDBDSItem> items, final Listener<EmployeesDBDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<EmployeesDBDSItem>>() {
            @Override
            public void success(List<EmployeesDBDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<EmployeesDBDSItem> items){
        List<String> ids = new ArrayList<>();
        for(EmployeesDBDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

