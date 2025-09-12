package com.example.dagger_java.screens.common.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dagger_java.R;

public class ServerErrorDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.server_error_dialog_title)
                .setMessage(R.string.server_error_dialog_message)
                .setPositiveButton(R.string.server_error_dialog_button_caption,
                        (dialog, which) -> dismiss());

        return builder.create();
    }

    public static ServerErrorDialogFragment newInstance() {
        return new ServerErrorDialogFragment();
    }
}
