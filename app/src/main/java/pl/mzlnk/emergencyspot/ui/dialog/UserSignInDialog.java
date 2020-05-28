package pl.mzlnk.emergencyspot.ui.dialog;

import android.view.View;
import android.widget.EditText;

import java.util.function.BiConsumer;

import pl.mzlnk.emergencyspot.R;
import pl.mzlnk.emergencyspot.ui.view.button.NoIconButton;

public class UserSignInDialog extends BaseDialog {

    private EditText login, password;
    private NoIconButton submit;

    private BiConsumer<String, String> onSubmitListener;

    @Override
    protected int xmlLayoutResId() {
        return R.layout.d_user_sign_in;
    }

    @Override
    protected void loadViewsFromXml(View rootView) {
        this.login = rootView.findViewById(R.id.d_user_sign_in_input_login);
        this.password = rootView.findViewById(R.id.d_user_sign_in_input_password);
        this.submit = rootView.findViewById(R.id.d_user_sign_in_btn_submit);
    }

    @Override
    protected void prepareListeners() {
        this.submit.setOnClickListener(view -> {
            if(this.onSubmitListener != null) {
                this.onSubmitListener.accept(this.login.getText().toString(), this.password.getText().toString());
            }
        });
    }

    public final void setOnSubmitListener(BiConsumer<String, String> onSubmitListener) {
        this.onSubmitListener = onSubmitListener;
    }

}
