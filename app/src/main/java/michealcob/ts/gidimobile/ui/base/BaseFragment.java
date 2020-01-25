package michealcob.ts.gidimobile.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import dagger.android.support.AndroidSupportInjection;
import michealcob.ts.gidimobile.utils.manager.AlertManager;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {
    private BaseActivity mActivity;
    private T mViewDataBinding;
    private V mViewModel;
    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //performDependencyInjection();
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }


    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
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

    public void setTitle(String title) {

       /* if (mRootView != null) {
            TextView txt = mRootView.findViewById(R.id.tvTitle);
            if (txt != null) {
                txt.setText(title);
            }
        }
        */
    }

    public void activeBackButton() {
       /* if (mRootView != null) {
            ImageButton button = (ImageButton) mRootView.findViewById(R.id.btBack);
            button.setOnClickListener(v -> {
                if (getActivity() != null)
                    getActivity().onBackPressed();
            });
        }
        */
    }

    public void finish() {
        getFragmentManager().beginTransaction()
                .remove(this).commit();
    }

    public void closeFragment(String tag) {
        FragmentManager manager = getChildFragmentManager();
        if (manager != null) {
            Fragment fragment = manager.findFragmentByTag(tag);
            if (fragment != null) {
                manager.beginTransaction()
                        .remove(fragment).commit();
            }
        }
    }

    public void closeLoading() {
        if (getActivity() != null)
            AlertManager.getInstance(getActivity()).closeLoading();
    }

    public void showLoading() {
        Activity activity = getActivity();
        if (activity != null && !activity.isFinishing())
            AlertManager.getInstance(activity).showLoading();
    }

    public boolean isActivityRunning() {
        return getActivity() != null && !getActivity().isFinishing();
    }

    public void showFragment(BaseFragment fragment, String tag, int idContainer) {
        this.getChildFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(idContainer, fragment, tag)
                .commit();
    }


    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }
}
