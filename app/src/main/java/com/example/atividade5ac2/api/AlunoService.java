package com.example.atividade5ac2.api;
import com.example.atividade5ac2.model.Aluno;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;
public interface AlunoService {
        @GET("aluno")
        Call<List<Aluno>> getAlunos();

        @POST("aluno")
        Call<Aluno> createAluno(@Body Aluno aluno);

}
