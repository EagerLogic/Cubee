package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.components.ALayout;
import com.eagerlogic.cubee.client.components.LayoutChildren;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;

/**
 *
 * @author dipacs
 */
public final class FlowLayout extends ALayout {

    private final IntegerProperty hGap = new IntegerProperty(0, false);
    private final IntegerProperty vGap = new IntegerProperty(0, false);
    private final IntegerProperty width = new IntegerProperty(100, false);
    private final IntegerProperty height = new IntegerProperty(null, true);
    
    private final IChangeListener layoutListener = new IChangeListener() {

        @Override
        public void onChanged(Object sender) {
            requestLayout();
        }
    };

    public FlowLayout() {
        super(DOM.createDiv());
        getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        this.pointerTransparentProperty().set(true);
        width.addChangeListener(layoutListener);
        height.addChangeListener(layoutListener);
        hGap.addChangeListener(layoutListener);
        vGap.addChangeListener(layoutListener);
    }

    @Override
    protected final void onChildAdded(AComponent child) {
        if (child != null) {
            getElement().appendChild(child.getElement());
        }
        requestLayout();
    }

    @Override
    protected final void onChildRemoved(AComponent child, int index) {
        if (child != null) {
            getElement().removeChild(child.getElement());
        }
        requestLayout();
    }

    @Override
    protected final void onChildrenCleared() {
        Element root = getElement();
        Element e = getElement().getFirstChildElement();
        while (e != null) {
            root.removeChild(e);
            e = root.getFirstChildElement();
        }
        requestLayout();
    }

    @Override
    protected void onLayout() {
        int w = width.get();
        if (w < 0) {
            w = 0;
        }
        int hg = hGap.get();
        if (hg < 0) {
            hg = 0;
        }
        int vg = vGap.get();
        if (vg < 0) {
            vg = 0;
        }
        int currentX = 0;
        int currentY = 0;
        int rowHeight = 0;
        int calculatedHeight = 0;

        boolean firstRow = true;
        for (AComponent c : getChildren()) {
            int cw = c.boundsWidthProperty().get();
            int ch = c.boundsHeightProperty().get();
            if (currentX + hg + cw <= w) {
                // fits
                this.setChildLeft(c, currentX);
                this.setChildTop(c, currentY);
                if (rowHeight < ch) {
                    rowHeight = ch;
                }
                currentX += cw + hg;
            } else {
                // does not fits
                firstRow = false;
                if (currentY == 0) {
                    // first row
                    if (currentX == 0) {
                        // first col
                        this.setChildLeft(c, 0);
                        this.setChildTop(c, 0);
                        calculatedHeight = ch;
                        currentX = 0;
                        currentY = ch + vg;
                        rowHeight = ch;
                    } else {
                        // non first col
                        this.setChildLeft(c, 0);
                        this.setChildTop(c, rowHeight + vg);
                        calculatedHeight += rowHeight;
                        currentX = cw + hg;
                        currentY += rowHeight + vg;
                        rowHeight = ch;
                    }
                } else {
                    // non first row, non first item
                    this.setChildLeft(c, 0);
                    this.setChildTop(c, currentY + rowHeight + vg);
                    calculatedHeight += rowHeight + vg;
                    currentX = cw + hg;
                    currentY += rowHeight + vg;
                    rowHeight = ch;
                }
            }
        }
        if (firstRow) {
            calculatedHeight = rowHeight; 
        } else {
            calculatedHeight += rowHeight + vg;
        }
        

        if (height.get() != null) {
            calculatedHeight = height.get();
        }
        if (calculatedHeight < 0) {
            calculatedHeight = 0;
        }
        setSize(w, calculatedHeight);
    }

    public IntegerProperty hGapProperty() {
        return hGap;
    }

    public IntegerProperty vGapProperty() {
        return vGap;
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    public IntegerProperty heightProperty() {
        return height;
    }

    @Override
    public LayoutChildren getChildren() {
        return super.getChildren();
    }

}
