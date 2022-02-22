package org.izv.ad.aurbano.flora.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.izv.ad.aurbano.flora.model.Repository;
import org.izv.ad.aurbano.flora.model.entity.Flora;

import java.util.ArrayList;

public class AddFloraViewModel extends AndroidViewModel {

    private Repository repository;

    public AddFloraViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public MutableLiveData<Long> getAddFloraLiveData() {
        return repository.getAddFloraLiveData();
    }

    public void createFlora(Flora flora) {
        repository.createFlora(flora);
    }
}
