package com.example.atividade5ac2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import android.os.Bundle;

import com.example.atividade5ac2.api.AlunoClient;
import com.example.atividade5ac2.api.AlunoService;
import com.example.atividade5ac2.model.Aluno;
import com.example.atividade5ac2.recyclers.AlunoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlunos;
    private AlunoAdapter alunoAdapter;
    private AlunoService alunoService;
    private FloatingActionButton buttonCadastro;
    private Button buttonRecarregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonCadastro = (FloatingActionButton) findViewById(R.id.buttonCadastro);
        buttonCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroAlunoActivity.class);
            startActivity(intent);
        });
        buttonRecarregar = (Button) findViewById(R.id.buttonRecarregar);
        buttonRecarregar.setOnClickListener(v -> {
            carregarAlunos();
        });
        recyclerViewAlunos = findViewById(R.id.recyclerViewAlunos);
        recyclerViewAlunos.setLayoutManager(new LinearLayoutManager(this));

        alunoService = AlunoClient.getRetrofitInstance().create(AlunoService.class);

        carregarAlunos();
    }

    private void carregarAlunos() {
        Call<List<Aluno>> call = alunoService.getAlunos();
        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call<List<Aluno>> call, Response<List<Aluno>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Aluno> aluno = response.body();
                    alunoAdapter = new AlunoAdapter(MainActivity.this, response.body());
                    recyclerViewAlunos.setAdapter(alunoAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar alunos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Aluno>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ErRo0", Toast.LENGTH_SHORT).show();
            }
        });
    }
}