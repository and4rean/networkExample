package com.example.myapplication.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.CacheHelper;

public class InfoDialog extends DialogFragment {

    private String title;
    private String text;
    private String image;
    private String buttonText1;
    private String buttonText2;
    private View.OnClickListener btn1;
    private View.OnClickListener btn2;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static class Builder {

        private String title;
        private String text;
        private String image;
        private String buttonText1;
        private String buttonText2;
        private View.OnClickListener btn1;
        private View.OnClickListener btn2;

        public Builder() {}

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setText(String text) {
            this.text = text;
            return this;
        }
        public Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public InfoDialog build() {
            InfoDialog dialog = new InfoDialog();
            dialog.title = title;
            dialog.text = text;
            dialog.image = image;

            return dialog;
        }
    }
}
