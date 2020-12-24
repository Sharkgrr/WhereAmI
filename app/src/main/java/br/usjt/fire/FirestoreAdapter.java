package br.usjt.fire;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

/**
 * A classe preenche o holder e infla.
 */

public class FirestoreAdapter extends FirestoreRecyclerAdapter<Local, FirestoreAdapter.LocalViewHolder> {



    private OnListLongClick onListLongClick;

    public FirestoreAdapter(@NonNull FirestoreRecyclerOptions<Local> options, OnListLongClick onListLongClick){
        super(options);
        this.onListLongClick = onListLongClick;
    }

    @Override
    protected void onBindViewHolder(@NonNull LocalViewHolder holder, int position, @NonNull Local model){
        holder.nome.setText(model.getLocal());
        holder.data.setText(model.getData());
        holder.descricao.setText(model.getDescricao());
        holder.coordenadas.setText(model.getCoordenadas());

        Log.d("Locais", holder.nome + "" + holder.data + "" + holder.descricao + "" + holder.coordenadas);
    }

    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from( parent.getContext())
                .inflate(R.layout.card_locais, parent, false);
        return new LocalViewHolder(view);
    }
    public class LocalViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        private final TextView nome;
        private final TextView data;
        private final TextView descricao;
        private final TextView coordenadas;


        public LocalViewHolder(@NonNull View itemView){
            super(itemView);
            nome = itemView.findViewById(R.id.cardNomeLugar);
            data = itemView.findViewById(R.id.cardData);
            descricao = itemView.findViewById(R.id.cardNomeDescricao);
            coordenadas = itemView.findViewById(R.id.cardNomeCoordenadas);

            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            onListLongClick.onListLongClick(getItem(getAdapterPosition()), getAdapterPosition());
            return false;
        }
    }
    public interface OnListLongClick{
        void onListLongClick(Local snapshot, int position);
    }
}
