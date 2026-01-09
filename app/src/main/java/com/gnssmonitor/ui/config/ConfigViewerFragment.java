package com.gnssmonitor.ui.config;

import static com.gnssmonitor.utils.Constants.TAG_PREFIX;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.gnssmonitor.R;
import com.gnssmonitor.databinding.FragmentReadConfigBinding;
import com.gnssmonitor.utils.ConfigUtils;

/**
 * A placeholder fragment containing a simple view.
 */
public class ConfigViewerFragment extends Fragment implements MenuProvider {
    private static final String TAG = TAG_PREFIX + "Config";
    private static final String EXTRA_ARG_CONFIG_TYPES = "config_types";

    private final List<ConfigUtils.ConfigTypes> mTypes = new ArrayList<>();
    private final Executor mExecutor = Executors.newSingleThreadExecutor();

    private ConfigViewModel viewModel;
    private FragmentReadConfigBinding binding;

    public static ConfigViewerFragment newInstance(@Nullable List<ConfigUtils.ConfigTypes> types) {
        ConfigViewerFragment fragment = new ConfigViewerFragment();
        Bundle bundle = new Bundle();
        if (types == null) {
//            types = ConfigUtils.DEFAULT_CONFIG_TYPES;
            types = new ArrayList<>();
        }
        var typeNames = new ArrayList<String>();
        for (var t : types) {
            typeNames.add(t.name());
        }
        bundle.putStringArrayList(EXTRA_ARG_CONFIG_TYPES, typeNames);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(ConfigViewModel.class);
        var args = getArguments();
        if (args == null) {
            return;
        }
        var typeNames = args.getStringArrayList(EXTRA_ARG_CONFIG_TYPES);
        if (typeNames == null) {
            return;
        }
        mTypes.clear();
        for (var typeName : typeNames) {
            try {
                var type = ConfigUtils.ConfigTypes.valueOf(typeName);
                mTypes.add(type);
            } catch (Exception e) {
                Log.w(TAG, "Tried to convert unrecognized ConfigTypes -> " + typeName, e);
            }
        }
        doUpdateData();
    }

    private void shortUserMessage(String message) {
        Log.d(TAG, String.format("shortUserMessage(%s)", message));
        var act = requireActivity();
        act.runOnUiThread(() -> Toast.makeText(act, message, Toast.LENGTH_SHORT).show());
    }

    public void doUpdateData() {
        var updateItems = String.join(", ", mTypes.stream()
                .map(ConfigUtils.ConfigTypes::name)
                .toArray(String[]::new));
        Log.d(TAG, String.format("doUpdateData(%s)", updateItems));
        mExecutor.execute(() -> {
            final String text = ConfigUtils.readConfigsByType(requireContext(), mTypes);
            requireActivity().runOnUiThread(() -> {
                viewModel.setText(text);
                shortUserMessage("Read " + updateItems.toLowerCase());
            });
        });
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentReadConfigBinding.inflate(inflater, container, false);
        viewModel.getText().observe(getViewLifecycleOwner(), binding.sectionContent::setText);
        requireActivity().addMenuProvider(this,
                getViewLifecycleOwner(), Lifecycle.State.RESUMED);

        return binding.getRoot();
    }

    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_read_config, menu);
    }

    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.menu_refresh_config) {
            doUpdateData();
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}