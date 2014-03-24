package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.style.styles.EHAlign;
import com.eagerlogic.cubee.client.style.styles.EVAlign;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dipacs
 */
public final class VBox extends ALayout {

    private final IntegerProperty width = new IntegerProperty(null, true, false);
    //private final ArrayList<Element> wrappingPanels = new ArrayList<Element>();
    private final ArrayList<Integer> cellHeights = new ArrayList<Integer>();
    private final ArrayList<EHAlign> hAligns = new ArrayList<EHAlign>();
    private final ArrayList<EVAlign> vAligns = new ArrayList<EVAlign>();

    public VBox() {
        super(DOM.createDiv());
        getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
        this.pointerTransparentProperty().set(true);
        width.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                requestLayout();
            }
        });
    }

    public void setCellHeight(AComponent component, Integer cellHeight) {
        setCellHeight(getChildren().indexOf(component), cellHeight);
    }

    public void setCellHeight(int index, Integer cellHeight) {
        setInList(cellHeights, index, cellHeight);
        requestLayout();
    }

    public Integer getCellHeight(AComponent component) {
        return getCellHeight(getChildren().indexOf(component));
    }

    public Integer getCellHeight(int index) {
        return getFromList(cellHeights, index);
    }

    public void setCellHAlign(AComponent component, EHAlign hAlign) {
        setCellHAlign(getChildren().indexOf(component), hAlign);
    }

    public void setCellHAlign(int index, EHAlign hAlign) {
        setInList(hAligns, index, hAlign);
        requestLayout();
    }

    public EHAlign getCellHAlign(AComponent component) {
        return getCellHAlign(getChildren().indexOf(component));
    }

    public EHAlign getCellHAlign(int index) {
        return getFromList(hAligns, index);
    }

    public void setCellVAlign(AComponent component, EVAlign vAlign) {
        setCellVAlign(getChildren().indexOf(component), vAlign);
    }

    public void setCellVAlign(int index, EVAlign vAlign) {
        setInList(vAligns, index, vAlign);
        requestLayout();
    }

    public EVAlign getCellVAlign(AComponent component) {
        return getCellVAlign(getChildren().indexOf(component));
    }

    public EVAlign getCellVAlign(int index) {
        return getFromList(vAligns, index);
    }

    public void setLastCellHAlign(EHAlign hAlign) {
        this.setCellHAlign(this.getChildren().size() - 1, hAlign);
    }

    public void setLastCellVAlign(EVAlign vAlign) {
        this.setCellVAlign(this.getChildren().size() - 1, vAlign);
    }

    public void setLastCellHeight(int height) {
        this.setCellHeight(this.getChildren().size() - 1, height);
    }

    public void addEmptyCell(int height) {
        this.getChildren().add(null);
        this.setCellHeight(this.getChildren().size() - 1, height);
    }

    @Override
    protected final void onChildAdded(AComponent child) {
        if (child != null) {
            getElement().appendChild(child.getElement());
        }
        requestLayout();
    }

    public IntegerProperty widthProperty() {
        return width;
    }

    @Override
    protected final void onChildRemoved(AComponent child, int index) {
        if (child != null) {
            getElement().removeChild(child.getElement());
        }
        removeFromList(hAligns, index);
        removeFromList(vAligns, index);
        removeFromList(cellHeights, index);
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
        hAligns.clear();
        vAligns.clear();
        cellHeights.clear();
        requestLayout();
    }

    @Override
    protected void onLayout() {
        int maxWidth = -1;
        if (width.get() != null) {
            maxWidth = width.get();
        }

        int actH = 0;
        int maxW = 0;
        for (int i = 0; i < getChildren().size(); i++) {
            int childY = 0;
            AComponent child = getChildren().get(i);
            Integer cellH = getCellHeight(i);
            EVAlign vAlign = getCellVAlign(i);
            int realCellH = -1;
            if (cellH != null) {
                realCellH = cellH;
            }

            if (child == null) {
                if (realCellH > 0) {
                    actH += realCellH;
                }
            } else {
                //child.layout();
                int cw = child.boundsWidthProperty().get();
                int ch = child.boundsHeightProperty().get();
                int cl = child.translateXProperty().get();
                int ct = child.translateYProperty().get();
                int calculatedCellH = realCellH;
                if (calculatedCellH < 0) {
                    calculatedCellH = ch + ct;
                } else if (calculatedCellH < ch) {
                    calculatedCellH = ch;
                }

                childY = actH - child.translateYProperty().get();

                if (vAlign == EVAlign.MIDDLE) {
                    childY += (calculatedCellH - ch) / 2;
                } else if (vAlign == EVAlign.BOTTOM) {
                    childY += (calculatedCellH - ch);
                }
                child.setTop(childY);

                if (cw + cl > maxW) {
                    maxW = cw + cl;
                }
                actH += calculatedCellH;
            }
        }

        int realWidth = maxW;
        if (maxWidth > -1) {
            realWidth = maxWidth;
        }
        for (int i = 0; i < getChildren().size(); i++) {
            int childX = 0;
            AComponent child = getChildren().get(i);
            if (child == null) {
                continue;
            }
            EHAlign hAlign = getCellHAlign(i);
            int cw = child.boundsWidthProperty().get();
            if (hAlign == EHAlign.CENTER) {
                childX = (realWidth - cw) / 2;
            } else if (hAlign == EHAlign.RIGHT) {
                childX = (realWidth - cw);
            }

            child.setLeft(childX);
        }

        setSize(realWidth, actH);
    }

    private <T> T getFromList(List<T> list, int index) {
        if (list.size() > index) {
            return list.get(index);
        }
        return null;
    }

    private <T> void setInList(List<T> list, int index, T value) {
        while (list.size() < index) {
            list.add(null);
        }
        list.add(index, value);
    }

    private <T> void removeFromList(List<T> list, int index) {
        if (list.size() > index) {
            list.remove(index);
        }
    }

    @Override
    public LayoutChildren getChildren() {
        return super.getChildren();
    }
}
