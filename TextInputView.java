package pub.devrel.easypermissions.sample;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.TextInputEditText;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author xianglei
 * @create 下午5:26
 */
public class TextInputView extends RelativeLayout {

    private boolean canDel;
    private int maxLength;
    private int editInputType;
    private int editHintColor, editColor;
    private int editHint;
    private float editSize, editHintSize;
    private SpannableString editHintSpan;

    private TextInputEditText edit;
    private ImageView del, hide;

    private InputTextChangeListener listener;

    public TextInputView(Context context) {
        super(context);
        initView();
    }

    public TextInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextInputView);
        maxLength = array.getInteger(R.styleable.TextInputView_maxlength, 0);
        editInputType = array.getResourceId(R.styleable.TextInputView_inputType, R.id.inputType_text);
        canDel = array.getBoolean(R.styleable.TextInputView_canDel, false);
        editColor = array.getColor(R.styleable.TextInputView_edit_color, getResources().getColor(R.color.colorAccent));
        editHintColor = array.getColor(R.styleable.TextInputView_edit_color, getResources().getColor(R.color.colorAccent));
        editHint = array.getResourceId(R.styleable.TextInputView_edit_hint, 0);
        editSize = array.getDimensionPixelOffset(R.styleable.TextInputView_edit_size, 16);
        editHintSize = array.getDimension(R.styleable.TextInputView_edit_hintSize, editSize);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_text_input, this);
        edit = findViewById(R.id.input_edit);
        del = findViewById(R.id.input_del);
        hide = findViewById(R.id.input_hide);
        initEdit();
        initDel();
        initHide();
    }

    public void addTextInputChangeListener(InputTextChangeListener listener){
        this.listener = listener;
    }

    private void initEdit() {
        if(maxLength > 0){
            edit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        }
        edit.setTextSize(TypedValue.COMPLEX_UNIT_PX, editSize);
        edit.setTextColor(editColor);
        edit.setHintTextColor(editHintColor);
        if(editHint != 0) {
            edit.setHint(editHint);
            String str = edit.getHint().toString();
            editHintSpan = new SpannableString(str);
            if(editSize != editHintSize){
                editHintSpan.setSpan(new AbsoluteSizeSpan((int)editHintSize),
                        0, str.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                edit.setHint(editHintSpan);
            }
        }
    }

    private void initDel() {
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString();
                if(canDel){
                    del.setVisibility(TextUtils.isEmpty(input)? GONE : VISIBLE);
                }
                if(listener != null){
                    listener.textChanged(input);
                }
            }
        });
    }

    private void initHide() {
        if(editInputType == R.id.inputType_number){
            edit.setInputType(InputType.TYPE_CLASS_NUMBER);
        }else if(editInputType == R.id.inputType_phone){
            edit.setInputType(InputType.TYPE_CLASS_PHONE);
        }else if(editInputType == R.id.inputType_psw){
            edit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            hide.setVisibility(VISIBLE);
        }else{
            edit.setInputType(InputType.TYPE_CLASS_TEXT);
        }
        hide.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    //隐藏密码
                    edit.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    //显示密码
                    edit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
    }

    public interface InputTextChangeListener{
        void textChanged(String input);
    }
}
