package michealcob.ts.gidimobile.utils.manager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.Toast;

import michealcob.ts.gidimobile.R;


public class AlertManager {
    private static AlertManager mInstance;
    private Context mContext;
    private ProgressDialog mLoadingDialog;
    private AlertDialog mAlertDialog;
    private Dialog dialog;

    public AlertManager(Context context) {
        mContext = context;
    }

    public static AlertManager getInstance(Context context) {
        if (mInstance == null || mInstance.mContext != context) {
            mInstance = new AlertManager(context);
        }
        return mInstance;
    }

    public boolean isLoadingShowed() {
        return mLoadingDialog != null && mLoadingDialog.isShowing();
    }

    public void closeDialog() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
            mAlertDialog = null;
        }
    }

    public void showCustomDialog(){
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setContentView(R.layout.dialog_icon);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void showVoteDialog(){
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setContentView(R.layout.dialog_vote);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void cancelVoteDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }


    public void cancelCustomDialog(){
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void closeLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.cancel();
        }
        mLoadingDialog = null;
    }

    public void showLoading() {
        showLoading(true);
    }

    public void showLoading(boolean canceledOnTouchOutside) {
        if (mLoadingDialog == null || !mLoadingDialog.isShowing()) {
            mLoadingDialog = ProgressDialog.show(mContext, "",
                    mContext.getString(R.string.message_loading), true);
            mLoadingDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }

    public void showQuickToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void showQuickToast(String text, int idStyle) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }

    public void showLongToast(String text, int idStyle) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }

    public void showErrorToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
    }



    public void showOKDialog(String title, String detailText, DialogInterface.OnClickListener positiveListener) {
        if (mAlertDialog == null) {
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            dialogBuilder.setTitle(title);
            dialogBuilder.setMessage(detailText);
            dialogBuilder.setPositiveButton("OK", positiveListener);
            mAlertDialog = dialogBuilder.show();
        } else {
            mAlertDialog.show();
        }
    }

    public void showConfirmationDialog(String title, String detailText, DialogInterface.OnClickListener positiveListener) {
        if (mAlertDialog == null) {
            final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(mContext);
            dialogBuilder.setTitle(title);
            dialogBuilder.setMessage(detailText);
            dialogBuilder.setPositiveButton("YES", positiveListener);
            dialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (mAlertDialog != null)
                        mAlertDialog.dismiss();
                }
            });
            mAlertDialog = dialogBuilder.show();
        } else {
            mAlertDialog.show();
        }
    }

}
