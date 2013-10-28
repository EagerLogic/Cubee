package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.IntegerProperty;
import com.eagerlogic.cubee.client.styles.EHAlign;
import com.eagerlogic.cubee.client.styles.EVAlign;
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
	private final ArrayList<Element> wrappingPanels = new ArrayList<Element>();
	private final ArrayList<Integer> cellHeights = new ArrayList<Integer>();
	private final ArrayList<EHAlign> hAligns = new ArrayList<EHAlign>();
	private final ArrayList<EVAlign> vAligns = new ArrayList<EVAlign>();

	public VBox() {
		super(DOM.createDiv());
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

	@Override
	protected final void onChildAdded(AComponent child) {
		Element e = DOM.createDiv();
		e.getStyle().setOverflow(Style.Overflow.HIDDEN);
		wrappingPanels.add(e);
		getElement().appendChild(e);
		if (child != null) {
			e.appendChild(child.getElement());
		}
		requestLayout();
	}

	@Override
	protected final void onChildRemoved(AComponent child, int index) {
		getElement().removeChild(wrappingPanels.get(index));
		wrappingPanels.remove(index);
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
		wrappingPanels.clear();
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
		for (int i = 0; i < wrappingPanels.size(); i++) {
			AComponent child = getChildren().get(i);
			Element wrappingPanel = wrappingPanels.get(i);
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
				child.layout();
				int cw = child.boundsWidthProperty().get();
				int ch = child.boundsHeightProperty().get();
				int calculatedCellH = realCellH;
				if (calculatedCellH < 0) {
					calculatedCellH = ch;
				}
				wrappingPanel.getStyle().setHeight(calculatedCellH, Style.Unit.PX);
				wrappingPanel.getStyle().setTop(actH, Style.Unit.PX);
				
				if (vAlign == EVAlign.MIDDLE) {
					int top = (calculatedCellH - ch) / 2;
					child.getElement().getStyle().setTop(top, Style.Unit.PX);
				} else if (vAlign == EVAlign.BOTTOM) {
					int top = (calculatedCellH - ch);
					child.getElement().getStyle().setTop(top, Style.Unit.PX);
				} else {
					child.getElement().getStyle().setTop(0, Style.Unit.PX);
				}
				
				if (cw > maxW) {
					maxW = cw;
				}
				actH += calculatedCellH;
			}
		}
		
		int realWidth = maxW;
		if (maxWidth > -1) {
			realWidth = maxWidth;
		}
		for (int i = 0; i < wrappingPanels.size(); i++) {
			AComponent child = getChildren().get(i);
			Element wrappingPanel = wrappingPanels.get(i);
			EHAlign hAlign = getCellHAlign(i);
			wrappingPanel.getStyle().setWidth(realWidth, Style.Unit.PX);
			int cw = child.boundsWidthProperty().get();
			if (hAlign == EHAlign.CENTER) {
				int left = (realWidth - cw) / 2;
				child.getElement().getStyle().setLeft(left, Style.Unit.PX);
			} else if (hAlign == EHAlign.RIGHT) {
				int left = (realWidth - cw);
				child.getElement().getStyle().setLeft(left, Style.Unit.PX);
			} else {
				child.getElement().getStyle().setLeft(0, Style.Unit.PX);
			}
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
