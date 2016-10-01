
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

public interface StatusScreen1DSServiceRest{

	@GET("/app/57ef4a439d17e00300d4c404/r/statusScreen1DS")
	void queryStatusScreen1DSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<StatusScreen1DSItem>> cb);

	@GET("/app/57ef4a439d17e00300d4c404/r/statusScreen1DS/{id}")
	void getStatusScreen1DSItemById(@Path("id") String id, Callback<StatusScreen1DSItem> cb);

	@DELETE("/app/57ef4a439d17e00300d4c404/r/statusScreen1DS/{id}")
  void deleteStatusScreen1DSItemById(@Path("id") String id, Callback<StatusScreen1DSItem> cb);

  @POST("/app/57ef4a439d17e00300d4c404/r/statusScreen1DS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<StatusScreen1DSItem>> cb);

  @POST("/app/57ef4a439d17e00300d4c404/r/statusScreen1DS")
  void createStatusScreen1DSItem(@Body StatusScreen1DSItem item, Callback<StatusScreen1DSItem> cb);

  @PUT("/app/57ef4a439d17e00300d4c404/r/statusScreen1DS/{id}")
  void updateStatusScreen1DSItem(@Path("id") String id, @Body StatusScreen1DSItem item, Callback<StatusScreen1DSItem> cb);

  @GET("/app/57ef4a439d17e00300d4c404/r/statusScreen1DS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
}

