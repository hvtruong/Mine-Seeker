package com.example.gamemineseeker.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.gamemineseeker.R;

public class CongratulationDialogFragment extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.congratulation_dialog, null);
        DialogInterface.OnClickListener listener = (dialog, which) -> {
            getActivity().finish();
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("Congratulation!")
                .setView(v)
                .setNeutralButton(android.R.string.ok, listener)
                .create();
    }
}
