

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
 * "NewsScreen1DS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class NewsScreen1DS extends AppNowDatasource<NewsScreen1DSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private NewsScreen1DSService service;

    public static NewsScreen1DS getInstance(SearchOptions searchOptions){
        return new NewsScreen1DS(searchOptions);
    }

    private NewsScreen1DS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = NewsScreen1DSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<NewsScreen1DSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<NewsScreen1DSItem>>() {
                @Override
                public void onSuccess(List<NewsScreen1DSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new NewsScreen1DSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getNewsScreen1DSItemById(id, new Callback<NewsScreen1DSItem>() {
                @Override
                public void success(NewsScreen1DSItem result, Response response) {
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
    public void getItems(final Listener<List<NewsScreen1DSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<NewsScreen1DSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryNewsScreen1DSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<NewsScreen1DSItem>>() {
            @Override
            public void success(List<NewsScreen1DSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"title", "subtitle", "content"};
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
    public void create(NewsScreen1DSItem item, Listener<NewsScreen1DSItem> listener) {
                          service.getServiceProxy().createNewsScreen1DSItem(item, callbackFor(listener));
          }

    private Callback<NewsScreen1DSItem> callbackFor(final Listener<NewsScreen1DSItem> listener) {
      return new Callback<NewsScreen1DSItem>() {
          @Override
          public void success(NewsScreen1DSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(NewsScreen1DSItem item, Listener<NewsScreen1DSItem> listener) {
                          service.getServiceProxy().updateNewsScreen1DSItem(item.getIdentifiableId(), item, callbackFor(listener));
          }

    @Override
    public void deleteItem(NewsScreen1DSItem item, final Listener<NewsScreen1DSItem> listener) {
                service.getServiceProxy().deleteNewsScreen1DSItemById(item.getIdentifiableId(), new Callback<NewsScreen1DSItem>() {
            @Override
            public void success(NewsScreen1DSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<NewsScreen1DSItem> items, final Listener<NewsScreen1DSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<NewsScreen1DSItem>>() {
            @Override
            public void success(List<NewsScreen1DSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<NewsScreen1DSItem> items){
        List<String> ids = new ArrayList<>();
        for(NewsScreen1DSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

