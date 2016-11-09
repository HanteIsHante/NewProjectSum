package com.example.hante.newprojectsum.textinputactivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hante.newprojectsum.BaseActivity;
import com.example.hante.newprojectsum.R;
import com.example.hante.newprojectsum.custome.TitleBar;
import com.example.hante.newprojectsum.sqlite.DBEntry;
import com.example.hante.newprojectsum.sqlite.DBHelper;
import com.example.hante.newprojectsum.util.RegularUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class TextInputLayoutActivity extends BaseActivity {

    private static final String TAG = "TextInputLayoutActivity";
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
    private DBHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        mToolbarTextInputLayout.setMyCenterTitle("TextInputLayout");
        mToolbarTextInputLayout.setMySettingText("完成");
        mToolbarTextInputLayout.setSettingTextOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIfRight();
            }
        });
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
        mDbHelper = new DBHelper(getApplicationContext());
    }

    private void checkIfRight() {
        if(!isPhoneNumber()){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isPasswordRight()){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!isEmailRight()){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            return;
        }
        save2db(mEtPhone.toString(),mEtPassword.toString(),mEtEmail.toString());
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
        getUserTableNum();
        finish();
    }

    /**
     * 添加条目
     * @param Phone 手机号
     * @param Password 密码
     * @param Email  email
     */
    private void save2db (String   Phone, String  Password, String  Email) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(DBEntry.UserTable.USER_NAME,Phone);
        Values.put(DBEntry.UserTable.USER_PASSWORD,Password);
        Values.put(DBEntry.UserTable.USER_EMAIL,Email);
        db.insert(DBEntry.UserTable.TABLE_NAME,null,Values);
        db.close();
        Log.d(TAG, "save2db: 保存到数据库");
    }

    /**
     * 查询所有
     */
    private void getUserTableNum(){
        SQLiteDatabase readDB = mDbHelper.getReadableDatabase();
        String[] projection = {
                DBEntry.UserTable._ID,
                DBEntry.UserTable.USER_NAME,
                DBEntry.UserTable.USER_PASSWORD,
                DBEntry.UserTable.USER_EMAIL
        };
        Cursor q = readDB.query(
                DBEntry.UserTable.TABLE_NAME, projection, null, null, null, null, null
        );

        if( q!= null && q.getCount() > 0){
            while((q.moveToNext())){
                String s_ID = q.getString(q.getColumnIndexOrThrow(DBEntry.UserTable._ID));
                String s_Name = q.getString(q.getColumnIndexOrThrow(DBEntry.UserTable.USER_NAME));
                String s_Pass = q.getString(q.getColumnIndexOrThrow(DBEntry.UserTable
                        .USER_PASSWORD));
                String s_Email = q.getString(q.getColumnIndexOrThrow(DBEntry.UserTable.USER_EMAIL));
                Log.d(TAG, "getUserTableNum: 列表" + s_ID + s_Name + s_Pass + s_Email);
            }
        }
        if( q!= null){
            q.close();
        }
        readDB.close();
    }


    /**
     * 修改
     * @param userName 修改条件
     * @param password  修改内容
     */
    private void updateUserTable(String userName,String password){
        SQLiteDatabase updateDB = mDbHelper.getWritableDatabase();
        ContentValues updateValues = new ContentValues();
        updateValues.put(DBEntry.UserTable.USER_PASSWORD,password);
        String selection = DBEntry.UserTable.USER_NAME + " LIKE ?";
        String[] selectionArgs = {
                userName
        };
        updateDB.update(DBEntry.UserTable.TABLE_NAME,updateValues,selection,selectionArgs);
        updateDB.close();
    }

    /**
     * 删除
     * @param userName 根据userName 查找要删除的内容
     */
    private void deleteTableItem(String userName){
        SQLiteDatabase deleteDb = mDbHelper.getWritableDatabase();
        String selection = DBEntry.UserTable.USER_NAME + " LIKE ?";
        String[] selectionArgs = {
                userName
        };
        deleteDb.delete(DBEntry.UserTable.TABLE_NAME ,selection,selectionArgs);
        deleteDb.close();
    }

    /**
     * 删除 数据库表
     */
    private void deleteTable(){
        SQLiteDatabase deleteTable = mDbHelper.getWritableDatabase();
        deleteTable.delete(DBEntry.UserTable.TABLE_NAME,null,null);
        deleteTable.close();
    }
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
        int length = pwd.length();
        Log.i(TAG, "isPasswordRight: Size" + length);
        if (TextUtils.isEmpty(pwd) || length < 6) {
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
