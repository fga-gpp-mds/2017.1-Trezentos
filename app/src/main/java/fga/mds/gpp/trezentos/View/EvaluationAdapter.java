package fga.mds.gpp.trezentos.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fga.mds.gpp.trezentos.Model.Util.Evaluation;
import fga.mds.gpp.trezentos.R;

public class EvaluationAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private ArrayList<String> evaluationList;
    private Context context;
    private RecyclerView recyclerView;
    private EvaluationViewHolder holder;
    private Evaluation evaluation;

    public EvaluationAdapter(ArrayList<String> evaluationList, Context context, RecyclerView recyclerView) {
        this.evaluationList = evaluationList;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluation_item, parent, false);
        EvaluationViewHolder holder = new EvaluationViewHolder(view);
        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        holder = (EvaluationViewHolder) viewHolder;
        String evaluation = evaluationList.get(position);
        String[] itemsEvaluation = evaluation.split(" - ");

        String classEvaluationName = itemsEvaluation[0];
        String examEvaluationName = itemsEvaluation[1];
        String userAccount = itemsEvaluation[2];

        holder.userAccountName.setText("Avalie a ajuda do aluno: " + userAccount);//
        //holder.circleImageView.setImageResource(userAccount.getPhoto());
        holder.className.setText(classEvaluationName);
        holder.examName.setText(examEvaluationName);
    }

    @Override
    public int getItemCount() {
        return evaluationList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    @Override
    public void onClick(View v) {
        int itemPosition = recyclerView.getChildLayoutPosition(v);
    }


}
