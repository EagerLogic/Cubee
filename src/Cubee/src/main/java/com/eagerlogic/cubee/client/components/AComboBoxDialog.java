package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.style.styles.Color;

/**
 *
 * @author dipacs
 */
public abstract class AComboBoxDialog<T> extends ADialog {

    private int selectedIndex;
    private int resultIndex;
    private Runnable closeListener;

    public AComboBoxDialog(boolean autoHide) {
        super(true, autoHide);
    }

    public AComboBoxDialog(boolean autoHide, Color glassColor) {
        super(true, autoHide, glassColor);
    }

    void setCloseListener(Runnable closeListener) {
        this.closeListener = closeListener;
    }

    protected final int getResultIndex() {
        return resultIndex;
    }

    @Override
    protected final void show() {
        super.show();
    }

    @Override
    protected final void close() {
        this.resultIndex = onClose();
        super.close();
        if (this.closeListener != null) {
            this.closeListener.run();
        }
    }

    protected final void cancel() {
        if (selectedIndex < 0) {
            resultIndex = -1;
        } else {
            resultIndex = selectedIndex;
        }
        super.close();
    }

    final void onInit(int selectedIndex, T[] items, boolean emptySelectionEnabled, String emptySelectionText) {
        init(selectedIndex, items, emptySelectionEnabled, emptySelectionText);
    }

    protected abstract void init(int selectedIndex, T[] items, boolean emptySelectionEnabled, String emptySelectionText);

    protected abstract int onClose();

}
