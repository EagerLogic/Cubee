package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;

/**
 *
 * @author dipacs
 */
public final class HTMLComponent extends AUserControl {

    private final Property<String> html = new Property<String>(null, true, false);

    public HTMLComponent() {
        html.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                if (html.get() == null) {
                    getElement().setInnerHTML("");
                } else {
                    getElement().setInnerHTML(html.get());
                }
            }
        });
    }

    public Property<String> htmlProperty() {
        return html;
    }

    @Override
    public IntegerProperty widthProperty() {
        return super.widthProperty();
    }

    @Override
    public IntegerProperty heightProperty() {
        return super.heightProperty();
    }

    @Override
    public BackgroundProperty backgroundProperty() {
        return super.backgroundProperty();
    }

    @Override
    public PaddingProperty paddingProperty() {
        return super.paddingProperty();
    }

    @Override
    public BorderProperty borderProperty() {
        return super.borderProperty();
    }

}
