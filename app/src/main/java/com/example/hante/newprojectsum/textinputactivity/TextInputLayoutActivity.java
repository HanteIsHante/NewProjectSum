package com.example.hante.newprojectsum.textinputactivity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.util.RegularUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TextInputLayoutActivity extends BaseActivity {

    @Bind(R.id.toolbar_textInputLayout)
    TitleBar mToolbarTextInputLayout;
    @Bind(R.id.text_input_layout)
    TextInputLayout mTextInputLayoutPhoneNumber;
    @Bind(R.id.text_input_layout_password)
    TextInputLayout mTextInputLayoutPassword;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.et_email)
    EditText mEtEmail;
    @Bind(R.id.text_input_layout_email)
    TextInputLayout mTextInputLayoutEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        mToolbarTextInputLayout.setMyCenterTitle("TextInputLayout");
        setSupportActionBar(mToolbarTextInputLayout);
        getSupportActionBar();
        if (getSupportActionBar() == null) {
            return;
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolbarTextInputLayout.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mEtPhone.addTextChangedListener(new MyTextWatcher(mEtPhone));
        mEtPassword.addTextChangedListener(new MyTextWatcher(mEtPassword));
        mEtEmail.addTextChangedListener(new MyTextWatcher(mEtEmail));
    }


    // 动态监听 输入过程

    //动态监听输入过程
    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()){
                case R.id.et_phone:
                    isPhoneNumber();
                    break;
                case R.id.et_password:
                    isPasswordRight();
                    break;
                case R.id.et_email:
                    isEmailRight();
                    break;
            }
        }


    }
    /**
     * 检查输入的邮箱是否为空以及格式是否正确
     *
     * @return true 正确 false 错误
     */
    private boolean isEmailRight() {
        String email = mEtEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email) || !RegularUtils.isEmail(email)) {
            mTextInputLayoutEmail.setErrorEnabled(true);
            mTextInputLayoutEmail.setError("Email错误");
            mTextInputLayoutEmail.requestFocus();
            return false;
        }
        mTextInputLayoutEmail.setErrorEnabled(false);
        return true;
    }

    /**
     * 检查 password
     * @return true 正确 false 错误
     */
    private boolean isPasswordRight() {
        String pwd = mEtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(pwd)) {
            mTextInputLayoutPassword.setErrorEnabled(true);
            mTextInputLayoutPassword.setError("请检查密码");
            mEtPassword.requestFocus();
            return false;
        }
        mTextInputLayoutPassword.setErrorEnabled(false);
        return true;
    }

    /**
     * 验证 手机号
     * @return true 正确 false 错误
     */
    private boolean isPhoneNumber() {
        String phone = mEtPhone.getText().toString().trim();
        if(TextUtils.isEmpty(phone)||!RegularUtils.isPhone(phone)){
            mTextInputLayoutPhoneNumber.setErrorEnabled(true);
            mTextInputLayoutPhoneNumber.setError("请检查手机号码是否正确");
            mEtPhone.requestFocus();
            return false;
        }
        mTextInputLayoutPhoneNumber.setErrorEnabled(false);
        return true;
    }

}
