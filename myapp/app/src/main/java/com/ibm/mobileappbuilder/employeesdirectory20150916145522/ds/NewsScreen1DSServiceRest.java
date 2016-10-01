
package com.ibm.mobileappbuilder.employeesdirectory20150916145522.ds;
import java.util.List;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.POST;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Path;
import retrofit.http.PUT;

public interface NewsScreen1DSServiceRest{

	@GET("/app/57ef4a439d17e00300d4c404/r/newsScreen1DS")
	void queryNewsScreen1DSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<NewsScreen1DSItem>> cb);

	@GET("/app/57ef4a439d17e00300d4c404/r/newsScreen1DS/{id}")
	void getNewsScreen1DSItemById(@Path("id") String id, Callback<NewsScreen1DSItem> cb);

	@DELETE("/app/57ef4a439d17e00300d4c404/r/newsScreen1DS/{id}")
  void deleteNewsScreen1DSItemById(@Path("id") String id, Callback<NewsScreen1DSItem> cb);

  @POST("/app/57ef4a439d17e00300d4c404/r/newsScreen1DS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<NewsScreen1DSItem>> cb);

  @POST("/app/57ef4a439d17e00300d4c404/r/newsScreen1DS")
  void createNewsScreen1DSItem(@Body NewsScreen1DSItem item, Callback<NewsScreen1DSItem> cb);

  @PUT("/app/57ef4a439d17e00300d4c404/r/newsScreen1DS/{id}")
  void updateNewsScreen1DSItem(@Path("id") String id, @Body NewsScreen1DSItem item, Callback<NewsScreen1DSItem> cb);

  @GET("/app/57ef4a439d17e00300d4c404/r/newsScreen1DS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}

