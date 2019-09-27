package com.mapswithme.maps.widget;

import android.app.Activity;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.mapswithme.maps.R;
import com.mapswithme.util.UiUtils;
import com.mapswithme.util.Utils;

public class ToolbarController
{
  @NonNull
  private final Activity mActivity;
  @NonNull
  private final Toolbar mToolbar;
  private final View.OnClickListener mNavigationClickListener = new View.OnClickListener()
  {
    @Override
    public void onClick(View view)
    {
      onUpClick();
    }
  };

  public ToolbarController(@NonNull View root, @NonNull Activity activity)
  {
    mActivity = activity;
    mToolbar = root.findViewById(getToolbarId());

    if (useExtendedToolbar())
      UiUtils.extendViewWithStatusBar(getToolbar());
    setupNavigationListener();
    setSupportActionBar();
  }

  private void setSupportActionBar()
  {
    AppCompatActivity appCompatActivity = (AppCompatActivity) mActivity;
    appCompatActivity.setSupportActionBar(mToolbar);
  }

  protected boolean useExtendedToolbar()
  {
    return true;
  }

  private void setupNavigationListener()
  {
    View customNavigationButton = getToolbar().findViewById(R.id.back);
    if (customNavigationButton != null)
    {
      customNavigationButton.setOnClickListener(mNavigationClickListener);
    }
    else
    {
      UiUtils.showHomeUpButton(getToolbar());
      getToolbar().setNavigationOnClickListener(mNavigationClickListener);
    }
  }

  @IdRes
  private int getToolbarId()
  {
    return R.id.toolbar;
  }

  public void onUpClick()
  {
    Utils.navigateToParent(getActivity());
  }

  public ToolbarController setTitle(CharSequence title)
  {
    getSupportActionBar().setTitle(title);
    return this;
  }

  public ToolbarController setTitle(@StringRes int title)
  {
    getSupportActionBar().setTitle(title);
    return this;
  }

  @SuppressWarnings("ConstantConditions")
  @NonNull
  private ActionBar getSupportActionBar()
  {
    AppCompatActivity appCompatActivity = (AppCompatActivity) mActivity;
    return appCompatActivity.getSupportActionBar();
  }

  @NonNull
  public Activity getActivity()
  {
    return mActivity;
  }

  @NonNull
  public Toolbar getToolbar()
  {
    return mToolbar;
  }
}
