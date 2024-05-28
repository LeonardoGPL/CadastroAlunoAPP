package com.example.atividade5ac2.recyclers;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atividade5ac2.R;

public class AlunoHolder extends RecyclerView.ViewHolder {
        TextView textViewRa, textViewNome, textViewCep, textViewLogradouro, textViewComplemento, textViewBairro, textViewCidade, textViewUf;

        public AlunoHolder(@NonNull View itemView) {
            super(itemView);
            textViewRa = itemView.findViewById(R.id.textViewRa);
            textViewNome = itemView.findViewById(R.id.textViewNome);
            textViewCep = itemView.findViewById(R.id.textViewCep);
            textViewLogradouro = itemView.findViewById(R.id.textViewLogradouro);
            textViewComplemento = itemView.findViewById(R.id.textViewComplemento);
            textViewBairro = itemView.findViewById(R.id.textViewBairro);
            textViewCidade = itemView.findViewById(R.id.textViewCidade);
            textViewUf = itemView.findViewById(R.id.textViewUf);
        }

}
