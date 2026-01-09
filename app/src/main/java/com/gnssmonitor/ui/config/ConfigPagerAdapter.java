package com.gnssmonitor.ui.config;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
import java.util.Map;

import com.gnssmonitor.R;
import com.gnssmonitor.utils.ConfigUtils;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the tabs.
 */
public class ConfigPagerAdapter extends FragmentStateAdapter {

    @StringRes
    private static final int[] TABS_ORDER = new int[] {
            R.string.tab_system_rw,
            R.string.tab_system_ro,
            R.string.tab_dump_loc,
            R.string.tab_dump_gms,
            R.string.tab_dump_misc
    };
    private static final Map<Integer, ConfigUtils.ConfigTypes[]> TABS = Map.ofEntries(
            Map.entry(R.string.tab_system_rw, new ConfigUtils.ConfigTypes[] {
                    ConfigUtils.ConfigTypes.GPS_DEBUG,
                    ConfigUtils.ConfigTypes.SYSPROP,
                    ConfigUtils.ConfigTypes.CARRIER_CONFIG
            }),
            Map.entry(R.string.tab_system_ro, new ConfigUtils.ConfigTypes[] {
                    ConfigUtils.ConfigTypes.GPS_VENDOR,
                    ConfigUtils.ConfigTypes.RESPROP
            }),
            Map.entry(R.string.tab_dump_loc, new ConfigUtils.ConfigTypes[] {
                    ConfigUtils.ConfigTypes.DUMP_LOC
            }),
            Map.entry(R.string.tab_dump_gms, new ConfigUtils.ConfigTypes[] {
                    ConfigUtils.ConfigTypes.DUMP_GMS_LMS,
                    ConfigUtils.ConfigTypes.DUMP_GMS_LS,
                    ConfigUtils.ConfigTypes.DUMP_GMS_E911
            }),
            Map.entry(R.string.tab_dump_misc, new ConfigUtils.ConfigTypes[] {
                    ConfigUtils.ConfigTypes.DUMP_BATTERY,
                    ConfigUtils.ConfigTypes.DUMP_SUB_MGR
            })
    );

    public ConfigPagerAdapter(FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        var types = TABS.get(TABS_ORDER[position]);
        return ConfigViewerFragment.newInstance(types == null ? null : List.of(types));
    }

    @StringRes
    public int getPageTitle(int position) {
        return TABS_ORDER[position];
    }

    @Override
    public int getItemCount() {
        return TABS_ORDER.length;
    }
}