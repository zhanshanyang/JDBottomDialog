package com.yzs.demo.dialogdemo.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yzs.demo.dialogdemo.R;


/**
 * Created by yangzhanshan on 2018/6/14.
 */

public class BottomDialog {

    private AlertDialog alertDialog;
    private TextView tvDialogTitle;
    private TextView tvDialogMessage;
    private Button btnConfirm;
    private Button btnCancel;

    protected BottomDialog(Context context, ViewGroup viewGroup) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.BottomDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_bottom_view, viewGroup, false);
        builder.setView(view);
        alertDialog = builder.create();
        tvDialogTitle = view.findViewById(R.id.tv_dialog_title);
        tvDialogMessage = view.findViewById(R.id.tv_dialog_message);
        btnConfirm = view.findViewById(R.id.btn_confirm);
        btnCancel = view.findViewById(R.id.btn_cancel);

        Window dialogWindow = alertDialog.getWindow();
        if (dialogWindow != null) {
            dialogWindow.setGravity(Gravity.BOTTOM);
            dialogWindow.setWindowAnimations(R.style.BottomDialog_Animation);
        }
    }

    private void setView(View view) {
        alertDialog.setView(view);
    }

    private void setButton(int buttonIndex, CharSequence buttonText, DialogInterface.OnClickListener buttonListener, Object o) {
        if (buttonIndex == DialogInterface.BUTTON_POSITIVE) {
            btnConfirm.setText(buttonText);
            btnConfirm.setOnClickListener(view -> {
                buttonListener.onClick(alertDialog, buttonIndex);
                alertDialog.dismiss();
            });
        } else if (buttonIndex == DialogInterface.BUTTON_NEGATIVE) {
            btnCancel.setText(buttonText);
            btnCancel.setOnClickListener(view -> {
                buttonListener.onClick(alertDialog, buttonIndex);
                alertDialog.dismiss();
            });
        } else if (buttonIndex == DialogInterface.BUTTON_NEUTRAL) {

        }
    }

    private void setMessage(CharSequence message) {
        tvDialogMessage.setText(message);
    }

    private void setIcon(@StringRes int iconId) {

    }

    private void setIcon(Drawable icon) {

    }

    private void setTitle(CharSequence title) {
        tvDialogTitle.setText(title);
    }

    private void setCustomTitle(View customTitleView) {

    }

    private void setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {

    }

    private void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        alertDialog.setOnDismissListener(onDismissListener);
    }

    private void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        alertDialog.setOnCancelListener(onCancelListener);
    }

    private void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
        alertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
    }

    private void setCancelable(boolean cancelable) {
        alertDialog.setCancelable(cancelable);
    }

    public void show() {
        alertDialog.show();

        Window dialogWindow = alertDialog.getWindow();
        if (dialogWindow != null) {
            WindowManager.LayoutParams attributes = dialogWindow.getAttributes();
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialogWindow.setAttributes(attributes);
        }
    }

    public void dismiss() {
        alertDialog.dismiss();
    }

    public AlertDialog getAlertDialog() {
        return alertDialog;
    }

    public static class Builder {

        private AlertParams P;

        public Builder(Context context) {
            P = new AlertParams(context);
        }

        public Builder setTitle(@StringRes int titleId) {
            P.mTitle = P.mContext.getText(titleId);
            return this;
        }

        public Builder setTitle(@Nullable CharSequence title) {
            P.mTitle = title;
            return this;
        }

        public Builder setMessage(@StringRes int messageId) {
            P.mMessage = P.mContext.getText(messageId);
            return this;
        }

        /**
         * Set the message to display.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setMessage(@Nullable CharSequence message) {
            P.mMessage = message;
            return this;
        }

        public Builder setPositiveButton(@StringRes int textId, final DialogInterface.OnClickListener listener) {
            P.mPositiveButtonText = P.mContext.getText(textId);
            P.mPositiveButtonListener = listener;
            return this;
        }

        /**
         * Set a listener to be invoked when the positive button of the dialog is pressed.
         * @param text The text to display in the positive button
         * @param listener The {@link DialogInterface.OnClickListener} to use.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            P.mPositiveButtonText = text;
            P.mPositiveButtonListener = listener;
            return this;
        }

        /**
         * Set a listener to be invoked when the negative button of the dialog is pressed.
         * @param textId The resource id of the text to display in the negative button
         * @param listener The {@link DialogInterface.OnClickListener} to use.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNegativeButton(@StringRes int textId, final DialogInterface.OnClickListener listener) {
            P.mNegativeButtonText = P.mContext.getText(textId);
            P.mNegativeButtonListener = listener;
            return this;
        }

        /**
         * Set a listener to be invoked when the negative button of the dialog is pressed.
         * @param text The text to display in the negative button
         * @param listener The {@link DialogInterface.OnClickListener} to use.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            P.mNegativeButtonText = text;
            P.mNegativeButtonListener = listener;
            return this;
        }

        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable) {
            P.mCancelable = cancelable;
            return this;
        }

        /**
         * Sets the callback that will be called if the dialog is canceled.
         *
         * <p>Even in a cancelable dialog, the dialog may be dismissed for reasons other than
         * being canceled or one of the supplied choices being selected.
         * If you are interested in listening for all cases where the dialog is dismissed
         * and not just when it is canceled, see
         * {@link #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
         * setOnDismissListener}.</p>
         *
         * @return This Builder object to allow for chaining of calls to set methods
         * @see #setCancelable(boolean)
         * @see #setOnDismissListener(android.content.DialogInterface.OnDismissListener)
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnCancelListener(android.content.DialogInterface.OnCancelListener onCancelListener) {
            P.mOnCancelListener = onCancelListener;
            return this;
        }

        /**
         * Sets the callback that will be called when the dialog is dismissed for any reason.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnDismissListener(android.content.DialogInterface.OnDismissListener onDismissListener) {
            P.mOnDismissListener = onDismissListener;
            return this;
        }

        /**
         * Sets the callback that will be called if a key is dispatched to the dialog.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setOnKeyListener(android.content.DialogInterface.OnKeyListener onKeyListener) {
            P.mOnKeyListener = onKeyListener;
            return this;
        }

        /**
         * Set a custom view resource to be the contents of the Dialog. The
         * resource will be inflated, adding all top-level views to the screen.
         *
         * @param layoutResId Resource ID to be inflated.
         * @return this Builder object to allow for chaining of calls to set
         *         methods
         */
//        public Builder setView(int layoutResId) {
//            P.mView = null;
//            P.mViewLayoutResId = layoutResId;
//            return this;
//        }

        /**
         * Sets a custom view to be the contents of the alert dialog.
         * <p>
         * When using a pre-Holo theme, if the supplied view is an instance of
         * a {@link ListView} then the light background will be used.
         * <p>
         * <strong>Note:</strong> To ensure consistent styling, the custom view
         * should be inflated or constructed using the alert dialog's themed
         * context obtained via {@link }.
         *
         * @param view the view to use as the contents of the alert dialog
         * @return this Builder object to allow for chaining of calls to set
         *         methods
         */
        public Builder setView(View view) {
            P.mView = view;
            P.mViewLayoutResId = 0;
            return this;
        }

        /**
         * Creates an {@link AlertDialog} with the arguments supplied to this
         * builder.
         * <p>
         * Calling this method does not display the dialog. If no additional
         * processing is needed, {@link #show()} may be called instead to both
         * create and display the dialog.
         */
        public BottomDialog create(ViewGroup viewGroup) {
            // We can't use Dialog's 3-arg constructor with the createThemeContextWrapper param,
            // so we always have to re-set the theme
            final BottomDialog dialog = new BottomDialog(P.mContext, viewGroup);
            P.apply(dialog);
            dialog.setCancelable(P.mCancelable);
            if (P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(P.mOnCancelListener);
            dialog.setOnDismissListener(P.mOnDismissListener);
            if (P.mOnKeyListener != null) {
                dialog.setOnKeyListener(P.mOnKeyListener);
            }
            return dialog;
        }

    }

    public static class AlertParams {

        public Context mContext;

        public int mIconId = 0;
        public Drawable mIcon;
        public CharSequence mTitle;
        public View mCustomTitleView;
        public CharSequence mMessage;
        public CharSequence mPositiveButtonText;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public boolean mCancelable;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public int mViewLayoutResId;
        public View mView;

        public AlertParams(Context context) {
            mContext = context;
            mCancelable = true;
        }

        public void apply(BottomDialog dialog) {
            if (mCustomTitleView != null) {
                dialog.setCustomTitle(mCustomTitleView);
            } else {
                if (mTitle != null) {
                    dialog.setTitle(mTitle);
                }
                if (mIcon != null) {
                    dialog.setIcon(mIcon);
                }
                if (mIconId != 0) {
                    dialog.setIcon(mIconId);
                }
            }
            if (mMessage != null) {
                dialog.setMessage(mMessage);
            }
            if (mPositiveButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, mPositiveButtonText,
                        mPositiveButtonListener, null);
            }
            if (mNegativeButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, mNegativeButtonText,
                        mNegativeButtonListener, null);
            }
            if (mNeutralButtonText != null) {
                dialog.setButton(DialogInterface.BUTTON_NEUTRAL, mNeutralButtonText,
                        mNeutralButtonListener, null);
            }
            if (mView != null) {
                dialog.setView(mView);
            }/* else if (mViewLayoutResId != 0) {
                dialog.setView(mViewLayoutResId);
            }*/

            /*
            dialog.setCancelable(mCancelable);
            dialog.setOnCancelListener(mOnCancelListener);
            if (mOnKeyListener != null) {
                dialog.setOnKeyListener(mOnKeyListener);
            }
            */
        }
    }

}
