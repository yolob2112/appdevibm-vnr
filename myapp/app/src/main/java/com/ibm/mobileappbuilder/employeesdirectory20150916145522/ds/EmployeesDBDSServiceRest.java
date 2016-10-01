
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
import retrofit.mime.TypedByteArray;
import retrofit.http.Part;
import retrofit.http.Multipart;

public interface EmployeesDBDSServiceRest{

	@GET("/app/57ef4a439d17e00300d4c404/r/employeesDBDS")
	void queryEmployeesDBDSItem(
		@Query("skip") String skip,
		@Query("limit") String limit,
		@Query("conditions") String conditions,
		@Query("sort") String sort,
		@Query("select") String select,
		@Query("populate") String populate,
		Callback<List<EmployeesDBDSItem>> cb);

	@GET("/app/57ef4a439d17e00300d4c404/r/employeesDBDS/{id}")
	void getEmployeesDBDSItemById(@Path("id") String id, Callback<EmployeesDBDSItem> cb);

	@DELETE("/app/57ef4a439d17e00300d4c404/r/employeesDBDS/{id}")
  void deleteEmployeesDBDSItemById(@Path("id") String id, Callback<EmployeesDBDSItem> cb);

  @POST("/app/57ef4a439d17e00300d4c404/r/employeesDBDS/deleteByIds")
  void deleteByIds(@Body List<String> ids, Callback<List<EmployeesDBDSItem>> cb);

  @POST("/app/57ef4a439d17e00300d4c404/r/employeesDBDS")
  void createEmployeesDBDSItem(@Body EmployeesDBDSItem item, Callback<EmployeesDBDSItem> cb);

  @PUT("/app/57ef4a439d17e00300d4c404/r/employeesDBDS/{id}")
  void updateEmployeesDBDSItem(@Path("id") String id, @Body EmployeesDBDSItem item, Callback<EmployeesDBDSItem> cb);

  @GET("/app/57ef4a439d17e00300d4c404/r/employeesDBDS")
  void distinct(
        @Query("distinct") String colName,
        @Query("conditions") String conditions,
        Callback<List<String>> cb);
    
    @Multipart
    @POST("/app/57ef4a439d17e00300d4c404/r/employeesDBDS")
    void createEmployeesDBDSItem(
        @Part("data") EmployeesDBDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<EmployeesDBDSItem> cb);
    
    @Multipart
    @PUT("/app/57ef4a439d17e00300d4c404/r/employeesDBDS/{id}")
    void updateEmployeesDBDSItem(
        @Path("id") String id,
        @Part("data") EmployeesDBDSItem item,
        @Part("picture") TypedByteArray picture,
        Callback<EmployeesDBDSItem> cb);
}

