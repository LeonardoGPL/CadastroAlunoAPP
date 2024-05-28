package com.example.atividade5ac2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.atividade5ac2.api.AlunoClient;
import com.example.atividade5ac2.api.AlunoService;
import com.example.atividade5ac2.model.Aluno;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText editTextRa, editTextNome, editTextCep, editTextLogradouro, editTextComplemento, editTextBairro, editTextCidade, editTextUf;
    private Button buttonBuscarCep, buttonCadastrar, buttonVoltar;
    private RequestQueue requestQueue;
    private AlunoService alunoService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        editTextRa = findViewById(R.id.editTextRa);
        editTextNome = findViewById(R.id.editTextNome);
        editTextCep = findViewById(R.id.editTextCep);
        editTextLogradouro = findViewById(R.id.editTextLogradouro);
        editTextComplemento = findViewById(R.id.editTextComplemento);
        editTextBairro = findViewById(R.id.editTextBairro);
        editTextCidade = findViewById(R.id.editTextCidade);
        editTextUf = findViewById(R.id.editTextUf);
        buttonBuscarCep = findViewById(R.id.buttonBuscarCep);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        buttonVoltar = findViewById(R.id.buttonVoltar);

        requestQueue = Volley.newRequestQueue(this);
        alunoService = AlunoClient.getRetrofitInstance().create(AlunoService.class);

        buttonBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCep();
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarAluno();
            }
        });

        buttonVoltar.setOnClickListener(v -> {
            finish();
        });
    }

    private void buscarCep() {
        String cep = editTextCep.getText().toString();
        if (TextUtils.isEmpty(cep)) {
            Toast.makeText(this, "Informe o CEP", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            editTextLogradouro.setText(response.getString("logradouro"));
                            editTextComplemento.setText(response.getString("complemento"));
                            editTextBairro.setText(response.getString("bairro"));
                            editTextCidade.setText(response.getString("localidade"));
                            editTextUf.setText(response.getString("uf"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CadastroAlunoActivity.this, "Erro ao buscar CEP", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroAlunoActivity.this, "ErRo0", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    private void cadastrarAluno() {
        String raText = editTextRa.getText().toString();
        String nome = editTextNome.getText().toString();
        String cep = editTextCep.getText().toString();
        String logradouro = editTextLogradouro.getText().toString();
        String complemento = editTextComplemento.getText().toString();
        String bairro = editTextBairro.getText().toString();
        String cidade = editTextCidade.getText().toString();
        String uf = editTextUf.getText().toString();

        if (raText.isEmpty() || nome.isEmpty() || cep.isEmpty() || logradouro.isEmpty() || complemento.isEmpty() || bairro.isEmpty() || cidade.isEmpty() || uf.isEmpty()) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
            return;
        }
        int ra = Integer.parseInt(raText);
        Aluno aluno = new Aluno(ra, nome, cep, logradouro, complemento, bairro, cidade, uf);

        Call<Aluno> call = alunoService.createAluno(aluno);
        call.enqueue(new Callback<Aluno>() {
            @Override
            public void onResponse(Call<Aluno> call, retrofit2.Response<Aluno> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroAlunoActivity.this, "Aluno cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                } else {
                    Toast.makeText(CadastroAlunoActivity.this, "Erro ao cadastrar aluno", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Aluno> call, Throwable t) {
                Toast.makeText(CadastroAlunoActivity.this, "ErRo0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limparCampos() {
        editTextRa.setText("");
        editTextNome.setText("");
        editTextCep.setText("");
        editTextLogradouro.setText("");
        editTextComplemento.setText("");
        editTextBairro.setText("");
        editTextCidade.setText("");
        editTextUf.setText("");
    }
}