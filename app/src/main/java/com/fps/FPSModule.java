package com.fps;

import com.google.inject.Binder;
import com.google.inject.Module;

/**
 * Created by anissou on 16-01-09.
 */
public class FPSModule implements Module {
        @Override
        public void configure(Binder binder) {
            binder.bind(ICurrentAppData.class).to(CurrentAppData.class);
        }
}
