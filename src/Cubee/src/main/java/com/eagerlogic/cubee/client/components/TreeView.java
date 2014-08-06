package com.eagerlogic.cubee.client.components;

import com.eagerlogic.cubee.client.events.IEventListener;
import com.eagerlogic.cubee.client.properties.BooleanProperty;
import com.eagerlogic.cubee.client.properties.Property;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dipacs
 */
public final class TreeView extends AUserControl {
    
    private final BooleanProperty rootVisible = new BooleanProperty(true);
    private final BooleanProperty multipleSelectionEnabled = new BooleanProperty(false);
    private final Property<List<AbstractTreeViewItem<?>>> selectedItems = new Property<List<AbstractTreeViewItem<?>>>(null, true, true);
    private final Property<List<AbstractTreeViewItem<?>>> selectedItemsWriter = new Property<List<AbstractTreeViewItem<?>>>(null, true, false);
    private AbstractTreeViewItem rootNode;
    
    private final IEventListener<AbstractTreeViewItem.ItemClickedEventArgs> onItemClickedEventListener = new IEventListener<AbstractTreeViewItem.ItemClickedEventArgs>() {

        @Override
        public void onFired(AbstractTreeViewItem.ItemClickedEventArgs args) {
            if (rootNode == null) {
                return;
            }
            rootNode.deselect();
            args.getItem().selectedWriterProperty().set(true);
            
            ArrayList<AbstractTreeViewItem<?>> selectedItemsList = new ArrayList<AbstractTreeViewItem<?>>();
            selectedItemsList.add(args.getItem());
            selectedItemsWriter.set(selectedItemsList);
        }
    };

    public TreeView() {
        selectedItems.initReadonlyBind(selectedItemsWriter);
    }

    public BooleanProperty rootVisibleProperty() {
        return rootVisible;
    }

    public AbstractTreeViewItem getRootNode() {
        return rootNode;
    }

    public void setRootNode(AbstractTreeViewItem rootNode) {
        this.getChildren().clear();
        if (this.rootNode != null) {
            this.rootNode.onItemClickedEvent().removeListener(onItemClickedEventListener);
        }
        this.rootNode = rootNode;
        this.getChildren().add(rootNode);
        if (this.rootNode != null) {
            this.rootNode.onItemClickedEvent().addListener(onItemClickedEventListener);
        }
    }

    public Property<List<AbstractTreeViewItem<?>>> selectedItemsProperty() {
        return selectedItems;
    }

}
