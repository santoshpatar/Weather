package com.example.weather.utils;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.R;


public class AppUtils {
    public static final boolean debugMode = true;
    private static Dialog dialog;

    public static void printMe(Object object) {
        //System.out.println("MY_TAG "+debugMode+" :: "+object);

        if (debugMode) {
            if (object.toString().length() > 4000) {
                for (int i = 0; i < object.toString().length(); i += 4000) {
                    if (i + 4000 < object.toString().length())
                        System.out.println("MY_TAG " + object.toString().substring(i, i + 4000));
                    else
                        System.out.println("MY_TAG " + object.toString().substring(i));
                }
            } else {
                System.out.println("MY_TAG " + object);
            }
        }
    }

    public static void toastMe(Context ctx, String message) {
        if (debugMode)
            Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    public static void showProgressDialog(final Context ctx, String msg, boolean isUserCancelable) {
        if (null == dialog) {
            dialog = new Dialog(ctx, R.style.TransparentDialog);
        }
        dialog.setContentView(R.layout.dialoglayout);
        dialog.setCancelable(isUserCancelable);

        android.view.WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.y = 50;
        dialog.getWindow().setAttributes(params);
        TextView text = dialog.findViewById(R.id.progressText);
        text.setText(msg);
        dialog.show();
    }

    public static boolean isProgressDialogShowing() {
        if (dialog == null)
            return false;
        return dialog.isShowing();
    }

    public static void hideProgressDialog() {
        /*
         * if (mProgressDialog != null) { mProgressDialog.dismiss();
         */
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }




}


