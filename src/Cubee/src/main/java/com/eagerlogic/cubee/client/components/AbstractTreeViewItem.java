package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.components.AComponent;
import com.eagerlogic.cubee.client.components.AUserControl;
import com.eagerlogic.cubee.client.components.LayoutChildren;
import com.eagerlogic.cubee.client.components.Panel;
import com.eagerlogic.cubee.client.components.VBox;
import com.eagerlogic.cubee.client.events.Event;
import com.eagerlogic.cubee.client.events.EventArgs;
import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.IChangeListener;
import com.eagerlogic.cubee.client.properties.KeyFrame;
import com.eagerlogic.cubee.client.properties.Property;
import com.eagerlogic.cubee.client.properties.Timeline;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dipacs
 */
public abstract class AbstractTreeViewItem<T> extends AUserControl {
    
    static final class ItemClickedEventArgs extends EventArgs {

        public ItemClickedEventArgs(AbstractTreeViewItem<?> sender) {
            super(sender);
        }
        
        public final AbstractTreeViewItem<?> getItem() {
            return (AbstractTreeViewItem<?>) getSender();
        }
        
    }
    
    private final Event<ItemClickedEventArgs> onItemClicked = new Event<ItemClickedEventArgs>();
    private final Event<EventArgs> onChildrenChanged = new Event<EventArgs>();

    private final BooleanProperty selected = new BooleanProperty(false, false, true);
    private final BooleanProperty selectedWriter = new BooleanProperty(false, false, false);
    private final BooleanProperty expanded = new BooleanProperty(false, false, false);
    private final BooleanProperty leaf = new BooleanProperty(true, false, true);
    private final BooleanProperty leafWriter = new BooleanProperty(true, false, false);
    private final List<AbstractTreeViewItem<?>> items = new ArrayList<AbstractTreeViewItem<?>>();
    private final T value;
    
    private VBox root;
    private Panel rootComponentHolder;
    private Panel childrenPanel;
    private VBox childrenHolder;
    private AComponent rootComponent;
    
    private Timeline timeline;
    
    
    private final IEventListener<ItemClickedEventArgs> onItemClickedEventListener = new IEventListener<ItemClickedEventArgs>() {

        @Override
        public void onFired(ItemClickedEventArgs args) {
            onItemClicked.fireEvent(args);
        }
    };

    public AbstractTreeViewItem(T value) {
        this.value = value;
        
        selected.initReadonlyBind(selectedWriter);
        leaf.initReadonlyBind(leafWriter);
        
        root = new VBox();
        root.pointerTransparentProperty().set(true);
        super.getChildren().add(root);
        
        rootComponentHolder = new Panel();
        rootComponentHolder.pointerTransparentProperty().set(Boolean.TRUE);
        root.getChildren().add(rootComponentHolder);
        
        childrenPanel = new Panel();
        childrenPanel.transformCenterYProperty().set(0.0);
        childrenPanel.scaleYProperty().set(0.0);
        childrenPanel.pointerTransparentProperty().set(true);
        root.getChildren().add(childrenPanel);
        
        childrenHolder = new VBox();
        childrenHolder.translateXProperty().set(20);
        childrenHolder.pointerTransparentProperty().set(true);
        childrenPanel.getChildren().add(childrenHolder);
        
        expanded.addChangeListener(new IChangeListener() {

            @Override
            public void onChanged(Object sender) {
                startAnimation();
            }
        });
    }
    
    private void startAnimation() {
        if (timeline != null) {
            timeline.stop();
        }
        
        if (expanded.get()) {
            timeline = new Timeline(new KeyFrame(0, childrenPanel.scaleYProperty(), 0.0)
                    , new KeyFrame(200, childrenPanel.scaleYProperty(), 1.0));
        } else {
            timeline = new Timeline(new KeyFrame(0, childrenPanel.scaleYProperty(), 1.0)
                    , new KeyFrame(200, childrenPanel.scaleYProperty(), 0.0));
        }
        timeline.start();
    }
    
    private void refreshChildren() {
        childrenHolder.getChildren().clear();
        if (getItems() != null) {
            for (AbstractTreeViewItem<?> item : items) {
                childrenHolder.addEmptyCell(2);
                childrenHolder.getChildren().add(item);
            }
            leafWriter.set(items.size() < 1);
        }
    }

    public AComponent getRootComponent() {
        return rootComponent;
    }

    public void setRootComponent(AComponent rootComponent) {
        rootComponentHolder.getChildren().clear();
        this.rootComponent = rootComponent;
        rootComponentHolder.getChildren().add(rootComponent);
    }

    @Override
    @Deprecated
    protected LayoutChildren getChildren() {
        return super.getChildren();
    }

    public final BooleanProperty selectedProperty() {
        return selected;
    }
    
    final BooleanProperty selectedWriterProperty() {
        return selectedWriter;
    }

    public final BooleanProperty expandedProperty() {
        return expanded;
    }

    public final List<AbstractTreeViewItem<?>> getItems() {
        if (items == null) {
            return null;
        }
        return new ArrayList<AbstractTreeViewItem<?>>(items);
    }

//    public final void setItems(List<AbstractTreeViewItem> items) {
//        this.items = items;
//        leaf.set(items == null || items.size() < 1);
//        refreshChildren();
//    }

    public final BooleanProperty leafProperty() {
        return leaf;
    }

    final Event<ItemClickedEventArgs> onItemClickedEvent() {
        return onItemClicked;
    }
    
    protected final void fireItemClickedEvent(AbstractTreeViewItem item) {
        this.onItemClicked.fireEvent(new ItemClickedEventArgs(item));
    }
    
    public final void addChildren(AbstractTreeViewItem item) {
        if (item == null) {
            throw new NullPointerException("The item property can not be null.");
        }
        this.items.add(item);
        item.onItemClicked.addListener(onItemClickedEventListener);
        refreshChildren();
        onChildrenChanged.fireEvent(new EventArgs(this));
    }
    
    public final AbstractTreeViewItem<?> removeChildren(int index) {
        AbstractTreeViewItem<?> res = items.remove(index);
        res.onItemClicked.removeListener(onItemClickedEventListener);
        refreshChildren();
        onChildrenChanged.fireEvent(new EventArgs(this));
        return res;
    }
    
    public final void removeChildren(AbstractTreeViewItem<?> item) {
        item.onItemClicked.removeListener(onItemClickedEventListener);
        items.remove(item);
        refreshChildren();
        onChildrenChanged.fireEvent(new EventArgs(this));
    }
    
    public final void insertChildren(AbstractTreeViewItem<?> item, int index) {
        if (item == null) {
            throw new NullPointerException("The item property can not be null.");
        }
        this.items.add(index, item);
        item.onItemClicked.addListener(onItemClickedEventListener);
        refreshChildren();
        onChildrenChanged.fireEvent(new EventArgs(this));
    }
    
    public final void clearChildren() {
        for (AbstractTreeViewItem item : items) {
            item.onItemClicked.removeListener(onItemClickedEventListener);
        }
        this.items.clear();
        refreshChildren();
        onChildrenChanged.fireEvent(new EventArgs(this));
    }
    
    public final int getChildrenCount() {
        return items.size();
    }

    public Event<EventArgs> onChildrenChangedEvent() {
        return onChildrenChanged;
    }
    
    void deselect() {
        this.selectedWriter.set(false);
        for (AbstractTreeViewItem item : items) {
            item.deselect();
        }
    }

    public final T getValue() {
        return value;
    }

}
