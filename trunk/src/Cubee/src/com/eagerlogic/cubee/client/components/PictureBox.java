package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.EventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.BackgroundProperty;
import com.eagerlogic.cubee.client.properties.BorderProperty;
import com.eagerlogic.cubee.client.styles.EPictureSizeMode;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.properties.PaddingProperty;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.styles.Image;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public final class PictureBox extends AComponent {

    private final IntegerProperty width = new IntegerProperty(50, false, false);
    private final IntegerProperty height = new IntegerProperty(50, false, false);
    private final Property<EPictureSizeMode> pictureSizeMode = new Property<EPictureSizeMode>(EPictureSizeMode.NORMAL, false, false);
    private final Property<Image> image = new Property<Image>(null, true, false);
    private final BackgroundProperty background = new BackgroundProperty(null, true, false);
    private final Element imgElement;

    public PictureBox() {
        super(DOM.createDiv());
        getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        imgElement = DOM.createImg();
        imgElement.getStyle().setPosition(Style.Position.ABSOLUTE);
        getElement().appendChild(imgElement);
        // TODO create one instance of change listener and replace multiple implementation
        width.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                recalculateSize();
            }
        });
        width.invalidate();
        height.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                recalculateSize();
            }
        });
        height.invalidate();
        pictureSizeMode.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                recalculateSize();
            }
        });
        pictureSizeMode.invalidate();
        image.addChangeListener(new IChangeListener() {
            @Override
            public void onChanged(Object sender) {
                if (image.get() != null) {
                    image.get().apply(imgElement);
                    if (!image.get().isLoaded()) {
                        image.get().onLoadEvent().addListener(new IEventListener<EventArgs>() {

                            @Override
                            public void onFired(EventArgs args) {
                                recalculateSize();
                            }
                        });
                    }
                } else {
                    imgElement.setAttribute("src", "");
                }
                recalculateSize();
            }
        });
        image.invalidate();
        background.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                if (background.get() == null) {
                    // TODO clear background
                } else {
                    background.get().apply(getElement());
                }
            }
        });
        background.invalidate();
    }

    private void recalculateSize() {
        getElement().getStyle().setWidth(width.get(), Style.Unit.PX);
        getElement().getStyle().setHeight(height.get(), Style.Unit.PX);
        EPictureSizeMode psm = pictureSizeMode.get();
        int imgWidth = 0;
        int imgHeight = 0;
        int picWidth = width.get();
        int picHeight = height.get();
        int cx = 0;
        int cy = 0;
        int cw = 0;
        int ch = 0;
        float imgRatio;
        float picRatio = picWidth / (float) picHeight;

        if (image.get() != null) {
            imgWidth = image.get().getWidth();
            imgHeight = image.get().getHeight();
        }
        if (imgWidth == 0 || imgHeight == 0) {
            // nothing to do here
        } else {
            imgRatio = imgWidth / (float) imgHeight;
            if (psm == EPictureSizeMode.CENTER) {
                cx = (imgWidth - picWidth) / 2;
                cy = (imgHeight - picHeight) / 2;
                cw = imgWidth;
                ch = imgHeight;
            } else if (psm == EPictureSizeMode.FILL) {
                if (imgRatio > picRatio) {
                    // fit height
                    cy = 0;
                    ch = picHeight;
                    cw = (int) (ch * imgRatio);
                    cx = (picWidth - cw) / 2;
                } else {
                    // fit width
                    cx = 0;
                    cw = picWidth;
                    ch = (int) (cw / imgRatio);
                    cy = (picHeight - ch) / 2;
                }
            } else if (psm == EPictureSizeMode.FIT_HEIGHT) {
                cy = 0;
                ch = picHeight;
                cw = (int) (ch * imgRatio);
                cx = (picWidth - cw) / 2;
            } else if (psm == EPictureSizeMode.FIT_WIDTH) {
                cx = 0;
                cw = picWidth;
                ch = (int) (cw / imgRatio);
                cy = (picHeight - ch) / 2;
            } else if (psm == EPictureSizeMode.NORMAL) {
                cx = 0;
                cy = 0;
                cw = imgWidth;
                ch = imgHeight;
            } else if (psm == EPictureSizeMode.STRETCH) {
                cx = 0;
                cy = 0;
                cw = picWidth;
                ch = picHeight;
            } else if (psm == EPictureSizeMode.ZOOM) {
                if (imgRatio > picRatio) {
                    // fit width
                    cx = 0;
                    cw = picWidth;
                    ch = (int) (cw / imgRatio);
                    cy = (picHeight - ch) / 2;
                } else {
                    // fit height
                    cy = 0;
                    ch = picHeight;
                    cw = (int) (ch * imgRatio);
                    cx = (picWidth - cw) / 2;
                }
            }
        }
        imgElement.getStyle().setLeft(cx, Style.Unit.PX);
        imgElement.getStyle().setTop(cy, Style.Unit.PX);
        imgElement.getStyle().setWidth(cw, Style.Unit.PX);
        imgElement.getStyle().setHeight(ch, Style.Unit.PX);
        requestLayout();
    }

    public Property<EPictureSizeMode> pictureSizeModeProperty() {
        return pictureSizeMode;
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    @Override
    public PaddingProperty paddingProperty() {
        return super.paddingProperty();
    }

    @Override
    public BorderProperty borderProperty() {
        return super.borderProperty();
    }

    public BackgroundProperty backgroundProperty() {
        return background;
    }

    public Property<Image> imageProperty() {
        return image;
    }
}
