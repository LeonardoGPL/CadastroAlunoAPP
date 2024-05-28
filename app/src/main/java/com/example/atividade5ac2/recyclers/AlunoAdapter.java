package com.example.atividade5ac2.recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade5ac2.R;
import com.example.atividade5ac2.model.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoHolder> {
    private List<Aluno> alunos;
    private Context context;

    public AlunoAdapter(Context context, List<Aluno> alunos) {
        this.alunos = alunos;
        this.context = context;
    }

    @NonNull
    @Override
    public AlunoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aluno, parent, false);
        return new AlunoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoHolder holder, int position) {
        Aluno aluno = alunos.get(position);
        holder.textViewRa.setText("RA: " + aluno.getRa());
        holder.textViewNome.setText("Nome: " + aluno.getNome());
        holder.textViewCep.setText(aluno.getCep());
        holder.textViewLogradouro.setText(aluno.getLogradouro());
        holder.textViewComplemento.setText(aluno.getComplemento());
        holder.textViewBairro.setText(aluno.getBairro());
        holder.textViewCidade.setText(aluno.getCidade());
        holder.textViewUf.setText(aluno.getUf());
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }
}
