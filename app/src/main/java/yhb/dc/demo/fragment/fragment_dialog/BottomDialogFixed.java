package yhb.dc.demo.fragment.fragment_dialog;

import android.animation.LayoutTransition;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import yhb.dc.R;

public class BottomDialogFixed extends DialogFragment {

    private static final String TAG = "BottomDialogFixed";
    private Dialog mDialog;
    private Window mWindow;
    private View mDecorView;

    public static BottomDialogFixed newInstance() {
        Bundle args = new Bundle();
        BottomDialogFixed fragment = new BottomDialogFixed();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        setupDialog(mDialog = new Dialog(getContext(), R.style.Dialog_Fullscreen));
        mWindow = mDialog.getWindow();
        if (mWindow != null) {
            mDecorView = mWindow.getDecorView();
        }
        return mDialog;
    }

    protected void setupDialog(Dialog dialog) {
        if (dialog == null) {
            return;
        }
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.dimAmount = 0.5f;
        params.layoutAnimationParameters = null;

        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(params);
        window.setWindowAnimations(R.style.DialogAnimation);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setContentView(R.layout.bottom_dialog);
        addViews(dialog);
    }

    private CountBatch mCountBatch;

    private void addViews(Dialog dialog) {

        final ViewGroup root = dialog.findViewById(R.id.rl_root);
        root.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        final FrameLayout fl = dialog.findViewById(R.id.tv_container1);
        final FrameLayout f2 = dialog.findViewById(R.id.tv_container2);
        final FrameLayout f3 = dialog.findViewById(R.id.tv_container3);
        fl.addView(LayoutInflater.from(getContext()).inflate(R.layout.item_stardard_text_view, fl, false));
        fl.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        f2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);
        f3.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        fl.postDelayed(() -> mCountBatch.batchRun(() -> {
            TextView tv = (TextView) fl.getChildAt(0);
            if (tv != null) {
                tv.setText("卧槽1");
                tv.setOnClickListener(v -> f3.addView(LayoutInflater.from(getContext()).inflate(R.layout.item_stardard_text_view, fl, false)));
            }
        }), 0);

        f2.postDelayed(() -> mCountBatch.batchRun(() -> {
            TextView tv = (TextView) f2.getChildAt(0);
            if (tv != null) {
                tv.setText("卧槽2");
                tv.setVisibility(View.VISIBLE);
            }
        }), 500);

        mCountBatch = new CountBatch(2, 1500);

    }
}
