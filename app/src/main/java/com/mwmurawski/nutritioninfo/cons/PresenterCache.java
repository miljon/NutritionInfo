package com.mwmurawski.nutritioninfo.cons;

import android.support.annotation.Nullable;

import com.mwmurawski.nutritioninfo.presenter.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

public final class PresenterCache {

    private final Map<String, BasePresenter> cachedPresenters = new HashMap<>();

    /**
     * Returns a {@code presenter} with the given key.
     *
     * @param activityName The key a presenter is supposed to have.
     * @return The {@code presenter} or {@code null} if none was found.
     */
    @Nullable
    @SuppressWarnings("unchecked")
    public <T> T getPresenter(String activityName) {
        return (T) cachedPresenters.get(activityName);
    }


    /**
     * Stores the given {@code presenter}.
     *
     * @param activityName The key to store the presenter.
     * @param presenter    Presenter to store.
     */
    public void putPresenter(String activityName, BasePresenter presenter) {
        cachedPresenters.put(activityName, presenter);
    }

    /**
     * Removes the given {@code presenter} from this cache.
     *
     * @param presenterToRemove Presenter to remove.
     */
    public void removePresenter(BasePresenter presenterToRemove) {
        for (Map.Entry<String, BasePresenter> entry : cachedPresenters.entrySet()) {
            if (presenterToRemove.equals(entry.getValue())) {
                cachedPresenters.remove(entry.getKey());
                break;
            }
        }
    }
}
