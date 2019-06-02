package com.example.healthtrack;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DiaryService {

    @GET("diary/get")
    Call<List<Diary>> getDiaryResults();

    @POST("diary/post")
    Call<Diary> createDiary(@Body Diary diary);
}
