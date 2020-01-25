package michealcob.ts.gidimobile.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import dagger.android.AndroidInjection;
import michealcob.ts.gidimobile.utils.helper.NetworkUtils;
import michealcob.ts.gidimobile.utils.manager.AlertManager;


public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity implements BaseFragment.Callback {

    protected final static int REQUEST_LOGIN = -1;

    private T mViewDataBinding;
    private V mViewModel;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public View getMainView() {
        View decorView = getWindow().getDecorView();
        ViewGroup contentView = null;
        if (decorView != null)
            contentView = (ViewGroup) decorView.findViewById(android.R.id.content);
        return contentView;
    }

    public void activeBackButton() {
       /* View mainView = getMainView();
        if (mainView != null) {
            ImageView button = mainView.findViewById(R.id.btBack);
            if (button != null) {
                button.setVisibility(View.VISIBLE);
                button.setOnClickListener(v -> onBackPressed());
            }
        }
        */
    }

    public void setTitle(String title) {
        /*View mainView = getMainView();
        if (mainView != null) {
            TextView txt = mainView.findViewById(R.id.tvTitle);
            if (txt != null) {
                if (title != null && title.length() > 0)
                    txt.setText(title);
                else
                    txt.setVisibility(View.GONE);
            }
        }

         */
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void showLoading() {
        if (!isFinishing())
            AlertManager.getInstance(this).showLoading();
    }

    public void showLoading(boolean cancelable) {
        if (!isFinishing())
            AlertManager.getInstance(this).showLoading(cancelable);
    }

    public boolean isLoadingShowed() {
        return AlertManager.getInstance(this).isLoadingShowed();
    }

    public void closeLoading() {
        if (!isFinishing())
            AlertManager.getInstance(this).closeLoading();
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addFragment(BaseFragment fragment, String tag, int idContainer) {
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(idContainer, fragment, tag)
                .commit();
    }

    public void showFragment(BaseFragment fragment, String tag, int idContainer, boolean addToBackStack) {
        if (addToBackStack)
            this.getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(tag)
                    .replace(idContainer, fragment, tag)
                    .commit();
        else showFragment(fragment, tag, idContainer);
    }

    public void toggleFragment(BaseFragment fragment, boolean isShowed) {
        if (fragment != null) {
            if (isShowed) {
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .show(fragment)
                        .commit();
                if (fragment.getView() != null)
                    fragment.getView().setClickable(true);
            } else {
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .disallowAddToBackStack()
                        .hide(fragment)
                        .commit();
                if (fragment.getView() != null)
                    fragment.getView().setClickable(false);
            }
        }
    }

    public void showFragment(BaseFragment fragment, String tag, int idContainer) {
        this.getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(idContainer, fragment, tag)
                .commit();
    }

    public void closeFragment(String tag, boolean addToBackStack) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(fragment).commit();
            if (addToBackStack)
                getSupportFragmentManager().popBackStack();
        }
    }

    public void closeFragment(String tag) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(fragment).commit();
        }
    }



    public boolean isOnline() {
        return NetworkUtils.isNetworkConnected(this);
    }
}




