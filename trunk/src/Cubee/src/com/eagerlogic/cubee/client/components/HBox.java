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
public class HBox extends ALayout {

	private final IntegerProperty height = new IntegerProperty(null, true, false);
	private final ArrayList<Integer> cellWidths = new ArrayList<Integer>();
	private final ArrayList<EHAlign> hAligns = new ArrayList<EHAlign>();
	private final ArrayList<EVAlign> vAligns = new ArrayList<EVAlign>();

	public HBox() {
		super(DOM.createDiv());
		getElement().getStyle().setOverflow(Style.Overflow.HIDDEN);
		this.pointerTransparentProperty().set(true);
		height.addChangeListener(new IChangeListener() {

			@Override
			public void onChanged(Object sender) {
				requestLayout();
			}
		});
	}

	public void setCellWidth(AComponent component, Integer cellHeight) {
		setCellWidth(getChildren().indexOf(component), cellHeight);
	}

	public void setCellWidth(int index, Integer cellHeight) {
		setInList(cellWidths, index, cellHeight);
		requestLayout();
	}

	public Integer getCellWidth(AComponent component) {
		return getCellWidth(getChildren().indexOf(component));
	}

	public Integer getCellWidth(int index) {
		return getFromList(cellWidths, index);
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
		removeFromList(hAligns, index);
		removeFromList(vAligns, index);
		removeFromList(cellWidths, index);
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
		cellWidths.clear();
		requestLayout();
	}

	@Override
	protected void onLayout() {
		int maxHeight = -1;
		if (height.get() != null) {
			maxHeight = height.get();
		}

		int actW = 0;
		int maxH = 0;
		for (int i = 0; i < getChildren().size(); i++) {
			int childX = 0;
			AComponent child = getChildren().get(i);
			Integer cellW = getCellWidth(i);
			EHAlign hAlign = getCellHAlign(i);
			int realCellW = -1;
			if (cellW != null) {
				realCellW = cellW;
			}

			if (child == null) {
				if (realCellW > 0) {
					actW += realCellW;
				}
			} else {
				//child.layout();
				int cw = child.boundsWidthProperty().get();
				int ch = child.boundsHeightProperty().get();
				int cl = child.translateXProperty().get();
				int ct = child.translateYProperty().get();
				int calculatedCellW = realCellW;
				if (calculatedCellW < 0) {
					calculatedCellW = cw + cl;
				} else if (calculatedCellW < cw) {
					calculatedCellW = cw;
				}
				
				childX = actW - child.translateXProperty().get();
				
				if (hAlign == EHAlign.CENTER) {
					childX += (calculatedCellW - cw) / 2;
				} else if (hAlign == EHAlign.RIGHT) {
					childX += (calculatedCellW - cw);
				}
				child.setLeft(childX);
				
				if (ch + ct > maxH) {
					maxH = ch + ct;
				}
				actW += calculatedCellW;
			}
		}
		
		int realHeight = maxH;
		if (maxHeight > -1) {
			realHeight = maxHeight;
		}
		for (int i = 0; i < getChildren().size(); i++) {
			int childY = 0;
			AComponent child = getChildren().get(i);
			if (child == null) {
				continue;
			}
			EVAlign vAlign = getCellVAlign(i);
			int ch = child.boundsHeightProperty().get();
			if (vAlign == EVAlign.MIDDLE) {
				childY += (realHeight - ch) / 2;
			} else if (vAlign == EVAlign.BOTTOM) {
				childY += (realHeight - ch);
			}
			
			child.setTop(childY);
		}
		
		setSize(actW, realHeight);
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
