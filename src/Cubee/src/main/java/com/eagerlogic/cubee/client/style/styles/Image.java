package com.eagerlogic.cubee.client.style.styles;

import com.eagerlogic.cubee.client.events.EventArgs;
import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;

/**
 *
 * @author dipacs
 */
public final class Image {

    private final String url;
    private final ImageResource imageResource;
    private final boolean fromResource;
    private final com.eagerlogic.cubee.client.events.Event<EventArgs> onLoad = new com.eagerlogic.cubee.client.events.Event<EventArgs>();
    private int width;
    private int height;
    private boolean loaded;

    public Image(String url) {
        this(url, null, false);
        if (url == null) {
            throw new NullPointerException("The url parameter can not be null.");
        }
        final Element e = DOM.createImg();
        DOM.setEventListener((com.google.gwt.user.client.Element) e, new EventListener() {
            @Override
            public void onBrowserEvent(Event event) {
                if (event.getTypeInt() == Event.ONLOAD) {
                    width = Integer.parseInt(e.getPropertyString("width"));
                    height = Integer.parseInt(e.getPropertyString("height"));
                    loaded = true;
                    onLoad.fireEvent(new EventArgs(this));
                }
            }
        });
        DOM.sinkEvents((com.google.gwt.user.client.Element) e, Event.ONLOAD);
        DOM.setImgSrc((com.google.gwt.user.client.Element) e, url);
    }

    public Image(ImageResource imageResource) {
        this(null, imageResource, true);
        if (imageResource == null) {
            throw new NullPointerException("The imageResource parameter can not be null.");
        }
        width = imageResource.getWidth();
        height = imageResource.getHeight();
        loaded = true;
        onLoad.fireEvent(new EventArgs(this));
    }

    private Image(String url, ImageResource imageResource, boolean fromResource) {
        this.url = url;
        this.imageResource = imageResource;
        this.fromResource = fromResource;
    }

    public String getUrl() {
        return url;
    }

    public ImageResource getImageResource() {
        return imageResource;
    }

    public boolean isFromResource() {
        return fromResource;
    }

    public void apply(Element element) {
        if (fromResource) {
            element.setAttribute("src", imageResource.getSafeUri().asString());
        } else {
            element.setAttribute("src", url);
        }
    }

    public com.eagerlogic.cubee.client.events.Event<EventArgs> onLoadEvent() {
        return onLoad;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isLoaded() {
        return loaded;
    }
}
