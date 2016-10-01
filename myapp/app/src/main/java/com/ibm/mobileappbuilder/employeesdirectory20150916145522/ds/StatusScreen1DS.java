

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
 * "StatusScreen1DS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class StatusScreen1DS extends AppNowDatasource<StatusScreen1DSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private StatusScreen1DSService service;

    public static StatusScreen1DS getInstance(SearchOptions searchOptions){
        return new StatusScreen1DS(searchOptions);
    }

    private StatusScreen1DS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = StatusScreen1DSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<StatusScreen1DSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<StatusScreen1DSItem>>() {
                @Override
                public void onSuccess(List<StatusScreen1DSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new StatusScreen1DSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getStatusScreen1DSItemById(id, new Callback<StatusScreen1DSItem>() {
                @Override
                public void success(StatusScreen1DSItem result, Response response) {
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
    public void getItems(final Listener<List<StatusScreen1DSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<StatusScreen1DSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryStatusScreen1DSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<StatusScreen1DSItem>>() {
            @Override
            public void success(List<StatusScreen1DSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"status", "employee"};
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
    public void create(StatusScreen1DSItem item, Listener<StatusScreen1DSItem> listener) {
                          service.getServiceProxy().createStatusScreen1DSItem(item, callbackFor(listener));
          }

    private Callback<StatusScreen1DSItem> callbackFor(final Listener<StatusScreen1DSItem> listener) {
      return new Callback<StatusScreen1DSItem>() {
          @Override
          public void success(StatusScreen1DSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(StatusScreen1DSItem item, Listener<StatusScreen1DSItem> listener) {
                          service.getServiceProxy().updateStatusScreen1DSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(StatusScreen1DSItem item, final Listener<StatusScreen1DSItem> listener) {
                service.getServiceProxy().deleteStatusScreen1DSItemById(item.getIdentifiableId(), new Callback<StatusScreen1DSItem>() {
            @Override
            public void success(StatusScreen1DSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<StatusScreen1DSItem> items, final Listener<StatusScreen1DSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<StatusScreen1DSItem>>() {
            @Override
            public void success(List<StatusScreen1DSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<StatusScreen1DSItem> items){
        List<String> ids = new ArrayList<>();
        for(StatusScreen1DSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

