package fga.mds.gpp.trezentos.View;

import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import fga.mds.gpp.trezentos.Model.Evaluation;
import fga.mds.gpp.trezentos.R;

public class EvaluationViewHolder extends RecyclerView.ViewHolder
        implements RatingBar.OnRatingBarChangeListener{

    final TextView userAccountName;
    final TextView className;
    final TextView examName;
    final RatingBar ratingBar;

    public EvaluationViewHolder(View view) {
        super(view);
        className = (TextView) view.findViewById(R.id.student_class);
        examName = (TextView) view.findViewById(R.id.student_exam);
        userAccountName = (TextView) view.findViewById(R.id.student_name);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);
    }

    private void showConfirm(float rate){
        final Dialog evaluationDialog =
                new Dialog(EvaluationFragment.getInstance().getContext());
        evaluationDialog.setTitle("EvaluationConfirmation");
        evaluationDialog.setContentView(R.layout.dialog_confirm_evaluation);
        Button confirmEvaluation = (Button) evaluationDialog.findViewById(R.id.button1);
        Button cancelEvaluation = (Button) evaluationDialog.findViewById(R.id.button2);
        RatingBar ratingBarDialog = (RatingBar) evaluationDialog.findViewById(R.id.ratingBarDialog);
        ratingBarDialog.setRating(rate);
        evaluationDialog.show();

        confirmEvaluation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
            }
        });
        cancelEvaluation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                evaluationDialog.dismiss(); // dismiss the dialog
            }
        });
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        showConfirm(rating);
    }
}