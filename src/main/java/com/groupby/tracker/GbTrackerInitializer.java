package com.groupby.tracker;

import androidx.startup.Initializer;
import android.content.Context;

import java.util.Collections;
import java.util.List;

public class GbTrackerInitializer implements Initializer<GbTracker>
{
    @Override
    public GbTracker create(Context context) {
        return GbTracker.getInstance().init(context);
    }

    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        // Defines a dependency on WorkManagerInitializer so it can be
        // initialized after WorkManager is initialized.
        return Collections.EMPTY_LIST;
    }
}
