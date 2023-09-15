import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listedetachesapp.EditTacheActivity;
import com.example.listedetachesapp.R;

import java.util.List;

public class TachesAdapter extends RecyclerView.Adapter<TachesAdapter.TacheViewHolder> {

    private final List<Tache> taches;
    private RecyclerView recyclerView;

    public TachesAdapter(List<Tache> taches) {
        this.taches = taches;
    }

    @NonNull
    @Override
    public TacheViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tache_item, parent, false);
        return new TacheViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TacheViewHolder holder, int position) {
        Tache tache = taches.get(position);
        holder.bind(tache);
    }

    @Override
    public int getItemCount() {
        return taches.size();
    }

    public void setRecyclerView(RecyclerView rv) {
        recyclerView = rv;
    }

    public class TacheViewHolder extends RecyclerView.ViewHolder {

        private final TextView descriptionTextView;
        private final CheckBox checkBox;
        private final Button deleteButton;
        private final Button modifierButton;

        public TacheViewHolder(@NonNull View itemView) {
            super(itemView);

            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            checkBox = itemView.findViewById(R.id.termineCheckBox);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            modifierButton = itemView.findViewById(R.id.modifierButton);
        }

        public void bind(Tache tache) {
            descriptionTextView.setText(tache.getDescription());
            checkBox.setChecked(tache.isEstTerminee());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    tache.setEstTerminee(isChecked);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    taches.remove(position);
                    notifyItemRemoved(position);
                }
            });

            // Handle the click event for the Modifier button
            modifierButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Get the task at the clicked position
                        Tache tache = taches.get(position);

                        // Launch the editing activity or perform editing logic for the task
                        // You can use an intent to start the EditTacheActivity and pass the necessary data
                        Intent intent = new Intent(itemView.getContext(), EditTacheActivity.class);
                        intent.putExtra("position", position);
                        itemView.getContext().startActivity(intent);
                    }
                }
            });

        }
    }
}