package xyz.zohre.picnic.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

/**
 * This class should remain java because of a issue in dagger and providing viewModel
 * workaround: follow https://github.com/google/dagger/issues/1478
 */
public class PicnicViewModelFactory implements ViewModelProvider.Factory{

    private final Map<Class<? extends ViewModel>, Provider<ViewModel>> creators;

    @Inject
    public PicnicViewModelFactory(Map<Class<? extends ViewModel>, Provider<ViewModel>> creators) {
        this.creators = creators;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        Provider<ViewModel> provider = creators.get(modelClass);
        if (provider != null) {
            return (T) provider.get();
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
